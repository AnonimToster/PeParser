package files.pe.headers;

import java.util.ArrayList;
import java.util.List;

import files.pe.types.ByteDefinition;

public class Header {
    public List<ByteDefinition<?>> headers = new ArrayList<ByteDefinition<?>>(0);

    public Header() {
    }

    protected <T extends ByteDefinition<?>> T h(T object) {
        this.headers.add(object);
        return object;
    }
}
