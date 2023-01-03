package files.pe.headers.resources;

import files.pe.ByteArray;
import files.pe.headers.Header;
import files.pe.headers.SectionTableEntry;
import files.pe.types.DWORD;

public class ResourceDataEntry extends Header {

    public final DWORD OFFSET_TO_DATA; // The address of a unit of resource data in the Resource Data area.
    public final DWORD SIZE;
    public final DWORD CODE_PAGE;
    public final DWORD RESERVED;

    private final SectionTableEntry section;

    public ResourceDataEntry(ByteArray bytes, SectionTableEntry section) {
        this.section = section;

        this.OFFSET_TO_DATA = new DWORD(bytes.readUInt(4), "offsetToData");
        this.SIZE = new DWORD(bytes.readUInt(4), "Size");
        this.CODE_PAGE = new DWORD(bytes.readUInt(4), "CodePage");
        this.RESERVED = new DWORD(bytes.readUInt(4), "Reserved");
    }

    public byte[] getData(ByteArray bytes) {

        long dataOffset = this.section.POINTER_TO_RAW_DATA.get().longValue() + this.OFFSET_TO_DATA.get().longValue() - this.section.VIRTUAL_ADDRESS.get().longValue();

        if (dataOffset > Integer.MAX_VALUE) {
            throw new RuntimeException("Unable to set offset to more than 2gb!");
        }

        int saved = bytes.position();
        bytes.seek((int) dataOffset);

        long bytesToCopyLong = this.SIZE.get().longValue();
        if (bytesToCopyLong > Integer.MAX_VALUE) {
            throw new RuntimeException("Unable to copy more than 2gb of bytes!");
        }

        byte[] copyBytes = bytes.copyBytes((int)bytesToCopyLong);
        bytes.seek(saved);
        return copyBytes;
    }
}
