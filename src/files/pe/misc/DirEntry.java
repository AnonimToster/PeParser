package files.pe.misc;

public enum DirEntry {

    EXPORT("Export Directory"),
    IMPORT("Import Directory"),
    RESOURCE("Resource Directory"),
    EXCEPTION("Exception Directory"),
    SECURITY("Security Directory"),
    BASERELOC("Base Relocation Table"),
    DEBUG("Debug Directory"),
    COPYRIGHT("Description String"),
    GLOBALPTR("Machine Value (MIPS GP)"),
    TLS("TLS Directory"),
    LOAD_CONFIG("Load Configuration Directory"),
    ;

    private final String description;

    DirEntry(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
