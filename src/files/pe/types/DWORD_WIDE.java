package files.pe.types;


import dorkbox.bytes.ULong;
import dorkbox.os.OS;

public class DWORD_WIDE extends ByteDefinition<ULong> {

    private final ULong value;

    public DWORD_WIDE(ULong value, String descriptiveName) {
        super(descriptiveName);
        this.value = value;
    }

    @Override
    public final ULong get() {
        return this.value;
    }

    @Override
    public void format(StringBuilder b) {
        b.append(getDescriptiveName()).append(": ")
         .append(this.value).append(" (0x").append(this.value.toHexString()).append(")")
         .append(OS.LINE_SEPARATOR);
    }
}
