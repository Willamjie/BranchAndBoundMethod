package helpers;

import pojo.Connector;
import pojo.Field;
import pojo.Link;
import pojo.Pin;

import java.util.ArrayList;
import java.util.List;

public class FieldHelper {

    public static List<Connector> getConnectorsBetween(Field field, Connector firstConnector, Connector secondConnector) {
        //TODO Check and correct connector's position?
        //TODO Field move to dependencies.

        List<Connector> result = new ArrayList<Connector>();

        for (Connector connector : field.getConnectors()) {
            if ((connector.getX() > firstConnector.getX()) && (connector.getX() < secondConnector.getX())) {
                result.add(connector);
            }
        }
        return result;
    }

    public static void prepareLinks(Field field) {
        List<Link> links = field.getLinks();
        List<Link> invalidLinks = new ArrayList<Link>();
        for (Link link : links) {
            Connector firstConnector = link.getFirstPin().getContainer();
            Connector secondConnector = link.getSecondPin().getContainer();
            if (!ConnectorHelper.isSiblingConnectors(field, firstConnector, secondConnector) && !firstConnector.equals(secondConnector) ) {
                if (firstConnector.getX() > secondConnector.getX()) {
                    Pin tempPin = link.getFirstPin();
                    link.setFirstPin(link.getSecondPin());
                    link.setSecondPin(tempPin);
                }
            } else {
                invalidLinks.add(link);
            }
        }
    }

}
