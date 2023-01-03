package files.pe.types;

import dorkbox.bytes.UShort;
import dorkbox.os.OS;
import files.pe.misc.MagicNumberType;

public class MagicNumber extends ByteDefinition<MagicNumberType> {

    private final UShort value;

    public MagicNumber(UShort value, String descriptiveName) {
        super(descriptiveName);
        this.value = value;
    }

    @Override
    public final MagicNumberType get() {
        return MagicNumberType.get(this.value);
    }

    @Override
    public final void format(StringBuilder b) {
        b.append(getDescriptiveName()).append(": ")
         .append(this.value).append(" --> ").append(get().getDescription())
         .append(OS.LINE_SEPARATOR);
    }
}
