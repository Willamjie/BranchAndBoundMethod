import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.Field;

import java.io.File;
import java.io.IOException;

public class JSONReader {

    public static Field readFromFile(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Field field = mapper.readValue(new File(path), Field.class);
        return field;
    }

}
