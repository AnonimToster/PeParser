package files.pe.types;

import dorkbox.os.OS;
import files.pe.ByteArray;

public class AsciiString extends ByteDefinition<String> {

    private final String value;

    public AsciiString(ByteArray bytes, int byteLength, String descriptiveName) {
        super(descriptiveName);

        byte[] stringBytes = bytes.copyBytes(byteLength);
        this.value = new String(stringBytes, OS.US_ASCII).trim();
    }

    @Override
    public final String get() {
        return this.value;
    }

    @Override
    public void format(StringBuilder b) {
        b.append(getDescriptiveName()).append(": ")
         .append(this.value)
         .append(OS.LINE_SEPARATOR);
    }
}
