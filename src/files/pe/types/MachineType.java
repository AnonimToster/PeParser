package files.pe.types;

import dorkbox.bytes.UShort;
import dorkbox.os.OS;
import files.pe.misc.MachineTypeType;

public class MachineType extends ByteDefinition<MachineTypeType> {

    private final UShort value;

    public MachineType(UShort value, String descriptiveName) {
        super(descriptiveName);

        this.value = value;
    }

    @Override
    public final MachineTypeType get() {
        return MachineTypeType.get(this.value);
    }

    @Override
    public void format(StringBuilder b) {
        b.append(getDescriptiveName()).append(": ").append(get().getDescription()).append(OS.LINE_SEPARATOR);
    }
}
