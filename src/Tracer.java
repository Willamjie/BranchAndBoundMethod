import helpers.FieldHelper;
import pojo.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Tracer {

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
