package json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class JsonReaderImpl implements JsonReader {

    public <T> Optional<T> read(Class<T> objectClass, String sourceURL) throws IOException {
        URL url = new URL(sourceURL);
        ObjectMapper objectMapper = new ObjectMapper();
        return Optional.of(objectMapper.readValue(url,objectClass));
    }
}
