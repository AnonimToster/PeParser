package files.pe.types;

import dorkbox.bytes.UInteger;
import dorkbox.os.OS;
import files.pe.headers.flags.SectionCharacteristicsType;

public class SectionCharacteristics extends ByteDefinition<SectionCharacteristicsType[]> {

    private final UInteger value;

    public SectionCharacteristics(UInteger value, String descriptiveName) {
        super(descriptiveName);
        this.value = value;
    }

    @Override
    public final SectionCharacteristicsType[] get() {
        return SectionCharacteristicsType.get(this.value);
    }

    @Override
    public void format(StringBuilder b) {
        SectionCharacteristicsType[] characteristics = get();

        b.append(getDescriptiveName()).append(": ").append(OS.LINE_SEPARATOR);

        if (characteristics.length > 0) {
            for (SectionCharacteristicsType c : characteristics) {
                b.append("\t * ").append(c.getDescription()).append(OS.LINE_SEPARATOR);
            }
        } else {
            b.append("\t * none").append(OS.LINE_SEPARATOR);
        }

        b.append(OS.LINE_SEPARATOR);
    }
}
