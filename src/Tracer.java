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

    public static Trace trace(Field field, Trace trace, boolean isTop) {
        List<Connector> connectors = FieldHelper.getConnectorsBetween(field, trace.getLink().getFirstPin().getContainer(),
                trace.getLink().getSecondPin().getContainer());

        removeTraceFromChannels(field, trace);

        List<Channel> path = connectors.stream().map(new Function<Connector, Channel>() {
            @Override
            public Channel apply(Connector connector) {
                Channel channel = null;
                if (isTop) {
                    channel = connector.getTopChannel();
                } else {
                    channel = connector.getBottomChannel();
                }
                return channel;
            }
        }).collect(Collectors.toList());

        trace.setPath(path);
        setTraceToPath(path, trace);

        return trace;
    }

    private static void setTraceToPath(List<Channel> path, Trace trace) {
        for (Channel channel : path) {
            channel.getTraces().add(trace);
        }
    }

    private static void removeTraceFromChannels(Field field, Trace trace) {
        for (Connector connector : field.getConnectors()) {
            connector.getTopChannel().getTraces().remove(trace);
            connector.getBottomChannel().getTraces().remove(trace);
        }
    }
}
