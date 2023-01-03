package files.pe.types;

import java.util.Date;

import dorkbox.bytes.UInteger;
import dorkbox.os.OS;

public class TimeDate extends ByteDefinition<Date> {

    private final UInteger value;

    public TimeDate(UInteger value, String descriptiveName) {
        super(descriptiveName);
        this.value = value;
    }

    @Override
    public final Date get() {
        long millis = this.value.longValue() * 1000;
        return new Date(millis);
    }

    @Override
    public void format(StringBuilder b) {
        b.append(getDescriptiveName()).append(": ").append(get().toString()).append(OS.LINE_SEPARATOR);
    }
}
