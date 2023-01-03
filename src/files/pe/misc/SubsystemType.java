package files.pe.misc;

import dorkbox.bytes.UShort;

public enum SubsystemType {
    IMAGE_SYSTEM_UNKNOWN(0, "unknown subsystem"),
    IMAGE_SUBSYSTEM_NATIVE(1, "Device drivers and native Windows processes"),
    IMAGE_SUBSYSTEM_WINDOWS_GUI(2, "The Windows graphical user interface (GUI) subsystem"),
    IMAGE_SUBSYSTEM_WINDOWS_CUI(3, "The Windows character subsystem"),
    IMAGE_SUBSYSTEM_POSIX_CUI(7, "The Posix character subsystem"),
    IMAGE_SUBSYSTEM_WINDOWS_CE_GUI(9, "Windows CE"),
    IMAGE_SUBSYSTEM_EFI_APPLICATION(10, "An Extensible Firmware Interface (EFI) application"),
    IMAGE_SUBSYSTEM_EFI_BOOT_SERVICE_DRIVER(11, "An EFI driver with boot services"),
    IMAGE_SUBSYSTEM_EFI_RUNTIME_DRIVER(12, "An EFI driver with run-time services"),
    IMAGE_SUBSYSTEM_EFI_ROM(13, "An EFI ROM image"),
    IMAGE_SUBSYSTEM_XBOX(14, "XBOX"),
    ;

    private final int intValue;
    private final String description;

    SubsystemType(int intValue, String description) {
        this.intValue = intValue;
        this.description = description;
    }

    public static SubsystemType get(UShort value) {
        int valueAsInt = value.intValue();

        for (SubsystemType c : values()) {
            if (c.intValue == valueAsInt) {
                return c;
            }
        }

        return null;
    }

    public String getDescription() {
        return this.description;
    }
}
