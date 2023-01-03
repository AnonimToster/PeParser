package files.pe.types;

import dorkbox.bytes.UInteger;
import dorkbox.bytes.ULong;
import dorkbox.os.OS;
import files.pe.misc.ImageBaseType;

public class ImageBase_Wide extends ByteDefinition<ULong> {

    private final ULong value;

    public ImageBase_Wide(ULong value, String descriptiveName) {
        super(descriptiveName);
        this.value = value;
    }

    @Override
    public final ULong get() {
        return this.value;
    }

    @Override
    public void format(StringBuilder b) {
        ImageBaseType imageBase = ImageBaseType.get(UInteger.valueOf(this.value.longValue()));

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
