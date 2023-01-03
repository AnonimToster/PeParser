package files.pe.misc;

import dorkbox.bytes.UShort;

public enum MagicNumberType {
    NONE("", "ERROR, unable to recognize magic number"),
    PE32("10B", "PE32, normal executable file"),
    PE32_PLUS("20B", "PE32+ executable"),
    ROM("107", "ROM image"),
    ;

    private final String hexValue;
    private final String description;

    MagicNumberType(String hexValue, String description) {
        this.hexValue = hexValue.toLowerCase();
        this.description = description;
    }

    public static MagicNumberType get(UShort value) {
        String key = value.toHexString();

        for (MagicNumberType mt : values()) {
            if (key.equals(mt.hexValue)) {
                return mt;
            }
        }

        return NONE;
    }

    public String getDescription() {
        return this.description;
    }
}
