package files.pe.types;

public abstract class ByteDefinition<T> {

    private final String descriptiveName;

    public ByteDefinition(String descriptiveName) {
        this.descriptiveName = descriptiveName;
    }

    public final String getDescriptiveName() {
        return this.descriptiveName;
    }

    public abstract T get();
    public abstract void format(StringBuilder b);

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        format(b);
        return b.toString();
    }
}
