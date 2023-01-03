package files.pe.types;


import dorkbox.bytes.UShort;
import dorkbox.os.OS;

public class WORD extends ByteDefinition<UShort> {

    private final UShort value;

    public WORD(UShort value, String descriptiveName) {
        super(descriptiveName);

        this.value = value;
    }

    @Override
    public final UShort get() {
        return this.value;
    }

    @Override
    public final void format(StringBuilder b) {
        b.append(getDescriptiveName()).append(": ")
         .append(this.value).append(" (0x").append(this.value.toHexString()).append(")")
         .append(OS.LINE_SEPARATOR);
    }
}
