package files.pe.types;

import dorkbox.bytes.UShort;
import dorkbox.os.OS;
import files.pe.headers.flags.Characteristics;

public class CoffCharacteristics extends ByteDefinition<Characteristics[]> {

    private final UShort value;

    public CoffCharacteristics(UShort value, String descriptiveName) {
        super(descriptiveName);
        this.value = value;
    }

    @Override
    public final Characteristics[] get() {
        return Characteristics.get(this.value);
    }

    @Override
    public void format(StringBuilder b) {
        Characteristics[] characteristics = get();


        b.append(getDescriptiveName()).append(":").append(OS.LINE_SEPARATOR);

        if (characteristics.length > 0) {
            for (Characteristics c : characteristics) {
                b.append("\t * ").append(c.getDescription()).append(OS.LINE_SEPARATOR);
            }
        } else {
            b.append("\t * none").append(OS.LINE_SEPARATOR);
        }
        b.append(OS.LINE_SEPARATOR);
    }
}
