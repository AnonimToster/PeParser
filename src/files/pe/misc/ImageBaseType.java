package files.pe.misc;

import dorkbox.bytes.UInteger;

public enum ImageBaseType {
    IMAGE_BASE_DEFAULT(0x10000000L, "DLL default"),
    IMAGE_BASE_WIN_CE(0x00010000L, "default for Windows CE EXEs"),
    IMAGE_BASE_WIN(0x00400000L, "default for Windows NT, 2000, XP, 95, 98 and Me"),
    ;

    private final long value;
    private final String description;

    ImageBaseType(long value, String description) {
        this.value = value;
        this.description = description;
    }

    public static ImageBaseType get(UInteger key) {
        long keyAsLong = key.longValue();

        for (ImageBaseType c : values()) {
            if (keyAsLong == c.value) {
                return c;
            }
        }

        return null;
    }

    public String getDescription() {
        return this.description;
    }
}
