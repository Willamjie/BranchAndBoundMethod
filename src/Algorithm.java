import pojo.Field;
import pojo.Link;

public class Algorithm {

    public static Field execute(Field field) {
        findBestSolution(field);
        return field;
    }

    private static void findBestSolution(Field field) {
        for (Link link : field.getLinks()) {
            if (Tracer.calculateTopLength(link) < Tracer.calculateBottomLength(link)) {
                Tracer.traceToTop(field, link);
            } else {
                Tracer.traceToBottom(field, link);
            }
        }
    }

}
