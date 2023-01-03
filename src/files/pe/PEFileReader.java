package files.pe;

import dorkbox.os.OS;
import files.pe.headers.*;
import files.pe.headers.resources.ResourceDirectoryHeader;
import files.pe.misc.DirEntry;
import files.pe.types.ByteDefinition;
import files.pe.types.ImageDataDir;

import java.io.*;

public class PEFileReader {

    private static final int PE_OFFSET_LOCATION = 0x3c;
    private static final byte[] PE_SIG = "PE\0\0".getBytes();

    public ByteArray fileBytes = null;

    private COFFFileHeader coffHeader;
    public OptionalHeader optionalHeader;
    private SectionTable sectionTable;
    private boolean invalidFile;


    public PEFileReader(String fileName) {
        File file = new File(fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fromInputStream(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fromInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(8192);

        byte[] buffer = new byte[4096];
        int read = 0;
        while ((read = inputStream.read(buffer)) > 0) {
            baos.write(buffer, 0, read);
        }
        baos.flush();
        inputStream.close();

        byte[] bytes = baos.toByteArray();
        invalidFile = bytes.length == 0;

        this.fileBytes = new ByteArray(bytes);

        if (isPE()) {
            int offset = getPEOffset() + PE_SIG.length;
            this.fileBytes.seek(offset);

            this.coffHeader = new COFFFileHeader(this.fileBytes);
            this.optionalHeader = new OptionalHeader(this.fileBytes);

            int numberOfEntries = this.coffHeader.NumberOfSections.get().intValue();
            this.sectionTable = new SectionTable(this.fileBytes, numberOfEntries);

            for (SectionTableEntry section : this.sectionTable.sections) {
                long sectionAddress = section.VIRTUAL_ADDRESS.get().longValue();
                long sectionSize = section.SIZE_OF_RAW_DATA.get().longValue();

                for (ImageDataDir entry : this.optionalHeader.tables) {
                    long optionAddress = entry.get().longValue();

                    if (sectionAddress <= optionAddress &&
                            sectionAddress + sectionSize > optionAddress) {

                        entry.setSection(section);
                        break;
                    }
                }
            }

            for (ImageDataDir entry : this.optionalHeader.tables) {
                if (entry.getType() == DirEntry.RESOURCE) {

                    SectionTableEntry section = entry.getSection();
                    if (section != null) {
                        long delta = section.VIRTUAL_ADDRESS.get().longValue() - section.POINTER_TO_RAW_DATA.get().longValue();
                        long offsetInFile = entry.get().longValue() - delta;

                        if (offsetInFile > Integer.MAX_VALUE) {
                            throw new RuntimeException("Unable to set offset to more than 2gb!");
                        }

                        this.fileBytes.seek((int) offsetInFile);
                        this.fileBytes.mark();

                        Header root = new ResourceDirectoryHeader(this.fileBytes, section, 0);
                        entry.data = root;
                    }
                }
            }
        }
    }

    public String extractPEInfo() {
        if (isPE()) {
            StringBuilder b = new StringBuilder();

            b.append("PE signature offset: ").append(getPEOffset()).append(OS.LINE_SEPARATOR)
                    .append("PE signature correct: ").append("yes").append(OS.LINE_SEPARATOR)
                    .append(OS.LINE_SEPARATOR)
                    .append("----------------").append(OS.LINE_SEPARATOR)
                    .append("COFF header info").append(OS.LINE_SEPARATOR)
                    .append("----------------").append(OS.LINE_SEPARATOR);

            for (ByteDefinition<?> bd : this.coffHeader.headers) {
                bd.format(b);
            }
            b.append(OS.LINE_SEPARATOR);

            b.append("--------------------").append(OS.LINE_SEPARATOR)
                    .append("Optional header info").append(OS.LINE_SEPARATOR)
                    .append("--------------------").append(OS.LINE_SEPARATOR);

            for (ByteDefinition<?> bd : this.optionalHeader.headers) {
                try {
                    bd.format(b);
                }
                catch (Exception e) {

                }
            }
            b.append(OS.LINE_SEPARATOR);


            b.append(OS.LINE_SEPARATOR)
                    .append("-------------").append(OS.LINE_SEPARATOR)
                    .append("Section Table").append(OS.LINE_SEPARATOR)
                    .append("-------------").append(OS.LINE_SEPARATOR)
                    .append(OS.LINE_SEPARATOR);

            for (SectionTableEntry section : this.sectionTable.sections) {
                for (ByteDefinition<?> bd : section.headers) {
                    bd.format(b);
                }
            }

            b.append(OS.LINE_SEPARATOR);
            return b.toString();
        } else {
            return "PE signature not found. The given file is not a PE file." + OS.LINE_SEPARATOR;
        }
    }

    private int getPEOffset() {
        this.fileBytes.mark();
        this.fileBytes.seek(PE_OFFSET_LOCATION);
        int read = this.fileBytes.readUShort(2).intValue();
        this.fileBytes.reset();
        return read;
    }

    public boolean isPE() {
        if (invalidFile) {
            return false;
        }

        int saved = -1;
        try {

            int offset = getPEOffset();
            saved = this.fileBytes.position();

            this.fileBytes.seek(0);

            for (int i = 0; i < PE_SIG.length; i++) {
                if (this.fileBytes.readRaw(offset + i) != PE_SIG[i]) {
                    return false;
                }
            }

            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (saved != -1) {
                this.fileBytes.seek(saved);
            }
        }
    }
}

