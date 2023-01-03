package files.pe.types;

import dorkbox.bytes.UShort;
import dorkbox.os.OS;
import files.pe.misc.SubsystemType;

public class Subsystem extends ByteDefinition<SubsystemType> {

    private final UShort value;

    public Subsystem(UShort value, String descriptiveName) {
        super(descriptiveName);
        this.value = value;
    }

    @Override
    public final SubsystemType get() {
        return SubsystemType.get(this.value);
    }

    @Override
    public void format(StringBuilder b) {
        SubsystemType s = get();

        if (s != null) {
            b.append(getDescriptiveName()).append(": ").append(s.getDescription()).append(OS.LINE_SEPARATOR);
        } else {
            b.append("ERROR, no subsystem description for value: ").append(this.value).append(OS.LINE_SEPARATOR);
        }
    }
}
