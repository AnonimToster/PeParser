package files.pe.types;

import dorkbox.bytes.UInteger;
import dorkbox.os.OS;
import files.pe.ByteArray;

public class ImageDataDirExtra extends ByteDefinition<UInteger> {

    private TInteger virtualAddress;
    private TInteger size;

    /** 8 bytes each */
    public ImageDataDirExtra(ByteArray bytes, String description) {
        super(description);

        this.virtualAddress = new TInteger(bytes.readUInt(4), "Virtual Address");
        this.size = new TInteger(bytes.readUInt(4), "Size");
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
}
