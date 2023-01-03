package files.pe.types;

import dorkbox.bytes.UInteger;
import dorkbox.os.OS;
import files.pe.ByteArray;
import files.pe.headers.Header;
import files.pe.headers.SectionTableEntry;
import files.pe.misc.DirEntry;

public class ImageDataDir extends ByteDefinition<UInteger> {

    private final DirEntry entry;

    private TInteger virtualAddress;
    private TInteger size;

    private SectionTableEntry section;
    public Header data;

    /** 8 bytes each */
    public ImageDataDir(ByteArray bytes, DirEntry entry) {
        super(entry.getDescription());
        this.entry = entry;

        this.virtualAddress = new TInteger(bytes.readUInt(4), "Virtual Address");
        this.size = new TInteger(bytes.readUInt(4), "Size");
    }

    public DirEntry getType() {
        return this.entry;
    }

    @Override
    public UInteger get() {
        return this.virtualAddress.get();
    }

    public UInteger getSize() {
        return this.size.get();
    }

    @Override
    public void format(StringBuilder b) {
        b.append(getDescriptiveName()).append(": ").append(OS.LINE_SEPARATOR)
         .append("\t").append("address: ").append(this.virtualAddress).append(" (0x").append(this.virtualAddress.get().toHexString()).append(")").append(OS.LINE_SEPARATOR)
         .append("\t").append("size: ").append(this.size.get()).append(" (0x").append(this.size.get().toHexString()).append(")").append(OS.LINE_SEPARATOR);
    }

    public void setSection(SectionTableEntry section) {
        this.section = section;
    }

    public SectionTableEntry getSection() {
        return this.section;
    }
}
