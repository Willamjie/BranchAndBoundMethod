import pojo.Field;
import utils.IOUtils;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Field field = IOUtils.readField("D://test.json");
        Field solution = Algorithm.execute(field);
        IOUtils.writeField(solution, "D://result.json");
    }
}
