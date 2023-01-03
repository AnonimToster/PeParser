package files.pe.headers.resources;

import files.pe.ByteArray;
import files.pe.headers.Header;
import files.pe.headers.SectionTableEntry;
import files.pe.types.DWORD;
import files.pe.types.ResourceDirName;

public class ResourceDirectoryEntry extends Header {

    public static final int HEADER_SIZE = 8;

    private static final int DATA_IS_DIRECTORY_MASK = 0x80000000;
    private static final int ENTRY_OFFSET_MASK = 0x7FFFFFFF;

    public final ResourceDirName NAME;

    public final DWORD DATA_OFFSET;

    public final boolean isDirectory;

    public final int level;

    public ResourceDirectoryHeader directory = null;
    public ResourceDataEntry resourceDataEntry = null;

    public ResourceDirectoryEntry(ByteArray bytes, SectionTableEntry section, int level) {
        this.level = level;

        this.NAME = h(new ResourceDirName(bytes.readUInt(4), "name", bytes, level));
        this.DATA_OFFSET = h(new DWORD(bytes.readUInt(4), "data offset"));

        long dataOffset = ENTRY_OFFSET_MASK & this.DATA_OFFSET.get().longValue();
        if (dataOffset == 0L) {

            this.isDirectory = false;
            return;
        }

        if (dataOffset > Integer.MAX_VALUE) {
            throw new RuntimeException("Unable to set offset to more than 2gb!");
        }

        this.isDirectory = 0L != (DATA_IS_DIRECTORY_MASK & this.DATA_OFFSET.get().longValue());

        int saved = bytes.position();
        bytes.seek(bytes.marked() + (int) dataOffset);

        if (this.isDirectory) {
            this.directory = new ResourceDirectoryHeader(bytes, section, level);
        } else {
            this.resourceDataEntry = new ResourceDataEntry(bytes, section);
        }

        bytes.seek(saved);
    }
}
