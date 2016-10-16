import helpers.FieldHelper;
import pojo.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Tracer {

    public static Integer calculateTopLength(Link link) {
        Integer firstContainerWidth = link.getFirstPin().getContainer().getWidth();
        Integer firstContainerX = link.getFirstPin().getContainer().getX();
        Integer secondContainerX = link.getSecondPin().getContainer().getX();
        Integer firstPinX = link.getFirstPin().getX();
        Integer firstPinY = link.getFirstPin().getY();
        Integer secondPinX = link.getSecondPin().getX();
        Integer secondPinY = link.getSecondPin().getY();

        Integer firstStage = firstContainerWidth - firstPinX + firstPinY;
        Integer secondStage = secondContainerX - firstContainerX - firstContainerWidth;
        Integer thirdStage = secondPinX + secondPinY;

        return firstStage + secondStage + thirdStage;
    }

    public static Integer calculateBottomLength(Link link) {
        Integer firstContainerWidth = link.getFirstPin().getContainer().getWidth();
        Integer firstContainerHeight = link.getFirstPin().getContainer().getHeight();
        Integer secondContainerHeight = link.getSecondPin().getContainer().getHeight();
        Integer firstContainerX = link.getFirstPin().getContainer().getX();
        Integer secondContainerX = link.getSecondPin().getContainer().getX();
        Integer firstPinX = link.getFirstPin().getX();
        Integer firstPinY = link.getFirstPin().getY();
        Integer secondPinX = link.getSecondPin().getX();
        Integer secondPinY = link.getSecondPin().getY();

        Integer firstStage = firstContainerHeight - firstPinY + firstContainerWidth - firstPinX;
        Integer secondStage = secondContainerX - firstContainerX - firstContainerWidth;
        Integer thirdStage = secondContainerHeight - secondPinY + secondPinX;

        return firstStage + secondStage + thirdStage;
    }

    public static void traceToTop(Field field, Link link) {
        List<Connector> connectors = FieldHelper.getConnectorsBetween(field, link.getFirstPin().getContainer(), link.getSecondPin().getContainer());
        List<Channel> path = connectors.stream().map(new Function<Connector, Channel>() {
            @Override
            public Channel apply(Connector connector) {
                Channel channel = connector.getTopChannel();
                channel.increaseOccupancy(link.getThickness());
                return channel;
            }
        }).collect(Collectors.toList());

        field.addTrace(new Trace(path, link));
    }

    public static void traceToBottom(Field field, Link link) {
        List<Connector> connectors = FieldHelper.getConnectorsBetween(field, link.getFirstPin().getContainer(), link.getSecondPin().getContainer());
        List<Channel> path = connectors.stream().map(new Function<Connector, Channel>() {
            @Override
            public Channel apply(Connector connector) {
                Channel channel = connector.getBottomChannel();
                channel.increaseOccupancy(link.getThickness());
                return channel;
            }
        }).collect(Collectors.toList());

        field.addTrace(new Trace(path, link));
    }
}
