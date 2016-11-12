package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.Field;

import java.io.File;
import java.io.IOException;

public class IOUtils {

    public static Field readField(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(path), Field.class);
    }

    public static void writeField(Field field, String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(path), field);
    }

}
