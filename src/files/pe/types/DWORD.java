package files.pe.types;


import dorkbox.bytes.UInteger;
import dorkbox.os.OS;

public class DWORD extends ByteDefinition<UInteger> {

    private final UInteger value;

    public DWORD(UInteger value, String descriptiveName) {
        super(descriptiveName);
        this.value = value;
    }

    @Override
    public final UInteger get() {
        return this.value;
    }

    @Override
    public void format(StringBuilder b) {
        b.append(getDescriptiveName()).append(": ")
         .append(this.value).append(" (0x").append(this.value.toHexString()).append(")")
         .append(OS.LINE_SEPARATOR);
    }
}
