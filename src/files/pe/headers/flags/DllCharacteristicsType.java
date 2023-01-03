package files.pe.headers.flags;

import java.util.ArrayList;
import java.util.List;

import dorkbox.bytes.UShort;

public enum DllCharacteristicsType {
    IMAGE_DLL_CHARACTERISTICS_DYNAMIC_BASE("40", "DLL can be relocated at load time."),
    IMAGE_DLL_CHARACTERISTICS_FORCE_INTEGRITY("80", "Code Integrity checks are enforced."),
    IMAGE_DLL_CHARACTERISTICS_NX_COMPAT("100", "Image is NX compatible."),
    IMAGE_DLL_CHARACTERISTICS_ISOLATION("200", "Isolation aware, but do not isolate the image."),
    IMAGE_DLLCHARACTERISTICS_NO_SEH("400", "Does not use structured exception (SE) handling. No SE handler may be called in this image."),
    IMAGE_DLLCHARACTERISTICS_NO_BIND("800", "Do not bind the image."),
    IMAGE_DLLCHARACTERISTICS_WDM_DRIVER("2000", "A WDM driver."),
    IMAGE_DLLCHARACTERISTICS_TERMINAL_SERVER_AWARE("8000", "Terminal Server aware."),
    ;

    private final String hexValue;
    private final String description;

    DllCharacteristicsType(String hexValue, String description) {
        this.hexValue = hexValue;
        this.description = description;
    }

    public static DllCharacteristicsType[] get(UShort key) {
        List<DllCharacteristicsType> chars = new ArrayList<DllCharacteristicsType>(0);
        int keyAsInt = key.intValue();

        for (DllCharacteristicsType c : values()) {
            long mask = Long.parseLong(c.hexValue, 16);
            if ((keyAsInt & mask) != 0) {
                chars.add(c);
            }
        }

        return chars.toArray(new DllCharacteristicsType[0]);
    }

    public String getDescription() {
        return this.description;
    }
}
