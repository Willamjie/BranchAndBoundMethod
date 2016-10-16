import pojo.Field;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Field field = JSONReader.readFromFile("D://test.json");
        System.out.println(field);
        Algorithm.execute(field);
        System.out.println(field);
    }
}
