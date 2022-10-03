package json;

import java.io.IOException;
import java.util.Optional;

public interface JsonReader {

    <T> Optional<T> read(Class<T> objectClass, String sourceUrl) throws IOException;
}
