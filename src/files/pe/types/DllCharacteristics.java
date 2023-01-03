package files.pe.types;

import dorkbox.bytes.UShort;
import dorkbox.os.OS;
import files.pe.headers.flags.DllCharacteristicsType;

public class DllCharacteristics extends ByteDefinition<DllCharacteristicsType[]> {

    private final UShort value;

    public DllCharacteristics(UShort value, String descriptiveName) {
        super(descriptiveName);
        this.value = value;
    }

    @Override
    public final DllCharacteristicsType[] get() {
        return DllCharacteristicsType.get(this.value);
    }

    @Override
    public void format(StringBuilder b) {
        DllCharacteristicsType[] characteristics = get();


        b.append(getDescriptiveName()).append(":").append(OS.LINE_SEPARATOR);

        if (characteristics.length > 0) {
            for (DllCharacteristicsType c : characteristics) {
                b.append("\t * ").append(c.getDescription()).append(OS.LINE_SEPARATOR);
            }
        } else {
            b.append("\t * none").append(OS.LINE_SEPARATOR);
        }
        b.append(OS.LINE_SEPARATOR);
    }
}
