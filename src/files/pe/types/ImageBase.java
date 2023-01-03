package files.pe.types;

import dorkbox.bytes.UInteger;
import dorkbox.os.OS;
import files.pe.misc.ImageBaseType;

public class ImageBase extends ByteDefinition<UInteger> {

    private final UInteger value;

    public ImageBase(UInteger value, String descriptiveName) {
        super(descriptiveName);
        this.value = value;
    }

    @Override
    public final UInteger get() {
        return this.value;
    }

    @Override
    public void format(StringBuilder b) {
        ImageBaseType imageBase = ImageBaseType.get(this.value);
        b.append(getDescriptiveName()).append(": ")
         .append(this.value).append(" (0x").append(this.value.toHexString()).append(") (");

        if (imageBase != null) {
            b.append(imageBase.getDescription());
        } else {
            b.append("no image base default");
        }
        b.append(")").append(OS.LINE_SEPARATOR);
    }
}
