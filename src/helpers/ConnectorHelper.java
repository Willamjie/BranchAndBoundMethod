package helpers;

import pojo.Connector;
import pojo.Field;

import java.util.List;

public class ConnectorHelper {

    public static boolean isSiblingConnectors(Field field, Connector firstConnector, Connector secondConnector) {
        List<Connector> connectors = field.getConnectors();
        for (int i = 0; i < connectors.size(); i++) {
            if (connectors.get(i).equals(firstConnector)) {
                if ((i < connectors.size() - 1) && (connectors.get(i + 1).equals(secondConnector))) {
                    return true;
                }
            }
        }
        return false;
    }

}
