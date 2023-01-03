package files.pe.types;

import dorkbox.os.OS;

public class HeaderDefinition extends ByteDefinition<String> {

    public HeaderDefinition(String descriptiveName) {
        super(descriptiveName);
    }

    @Override
    public String get() {
        return getDescriptiveName();
    }

    @Override
    public void format(StringBuilder b) {
        b.append(OS.LINE_SEPARATOR).append(get()).append(OS.LINE_SEPARATOR)
         .append(".......................").append(OS.LINE_SEPARATOR).append(OS.LINE_SEPARATOR);
    }
}
