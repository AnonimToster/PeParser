package files.pe.headers.flags;

import java.util.ArrayList;
import java.util.List;

import dorkbox.bytes.UShort;

public enum Characteristics {

    IMAGE_FILE_RELOCS_STRIPPED("1", "Resource information is stripped from the file"),
    IMAGE_FILE_EXECUTABLE_IMAGE("2", "The file is executable (no unresoled external references"),
    IMAGE_FILE_LINE_NUMS_STRIPPED("4", "COFF line numbers are stripped from the file (DEPRECATED)"),
    IMAGE_FILE_LOCAL_SYMS_STRIPPED("8", "COFF local symbols are stripped form the file (DEPRECATED)"),
    IMAGE_FILE_AGGRESSIVE_WS_TRIM("10", "Aggressively trim working set (DEPRECATED for Windows 2000 and later)"),
    IMAGE_FILE_LARGE_ADDRESS_AWARE("20", "Application can handle larger than 2 GB addresses."),
    IMAGE_FILE_RESERVED("40", "Use of this flag is reserved."),
    IMAGE_FILE_BYTES_REVERSED_LO("80", "Bytes of the word are reversed (REVERSED LO)"),
    IMAGE_FILE_32BIT_MACHINE("100", "Machine is based on a 32-bit-word architecture."),
    IMAGE_FILE_DEBUG_STRIPPED("200", "Debugging is removed from the file."),
    IMAGE_FILE_REMOVABLE_RUN_FROM_SWAP("400", "If the image is on removable media, fully load it and copy it to the swap file."),
    IMAGE_FILE_NET_RUN_FROM_SWAP("800", "If the image is on network media, fully load it and copy it to the swap file."),
    IMAGE_FILE_SYSTEM("1000", "The image file is a system file, (such as a driver) and not a user program."),
    IMAGE_FILE_DLL("2000", "The image file is a dynamic-link library (DLL). Such files are considered executable files for almost all purposes, although they cannot be directly run."),
    IMAGE_FILE_UP_SYSTEM_ONLY("4000", "The file should be run only on a uniprocessor machine."),
    IMAGE_FILE_BYTES_REVERSED_HI("8000", "Bytes of the word are reversed (REVERSED HI)"),
    ;

    private final String hexValue;
    private final String description;

    Characteristics(String hexValue, String description) {
        this.hexValue = hexValue;
        this.description = description;
    }

    public static Characteristics[] get(UShort key) {

        List<Characteristics> chars = new ArrayList<Characteristics>(0);
        int keyAsInt = key.intValue();

        for (Characteristics c : values()) {
            long mask = Long.parseLong(c.hexValue, 16);
            if ((keyAsInt & mask) != 0) {
                chars.add(c);
            }
        }

        return chars.toArray(new Characteristics[0]);
    }

    public String getDescription() {
        return this.description;
    }
}
