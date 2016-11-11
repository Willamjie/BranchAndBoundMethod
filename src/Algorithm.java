import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.tuple.Pair;
import pojo.*;

import java.util.*;
import java.util.stream.Collectors;

public class Algorithm {

    public static Field execute(Field field) {
        findBestSolution(field);
        List<Field> bound = new LinkedList<Field>();
        bound.add(field);
        return getSolution(bound);
    }

    private static Field getSolution(List<Field> bound) {
        Field bestField = getBestField(bound);
        if (!isAllowableField(bestField)) {
            if (hasValuableLooseTraces(bestField)) {
                //FIXME Actually we don't need to remove bestField and we just need to add new field with retraced candidate trace
                bound.remove(bestField);
                Trace firstCandidate = getCandidateToFix(bestField);
                Tracer.traceToTop(bestField, firstCandidate.getLink()).setFixed(true);
                Field secondBestField = SerializationUtils.clone(bestField);

                Trace secondCandidate = getCandidateToFix(secondBestField);
                Tracer.traceToTop(secondBestField, secondCandidate.getLink()).setFixed(true);

                bound.add(bestField);
                bound.add(secondBestField);

                bestField = getSolution(bound);
            } else {
                bound.remove(bestField);
                bestField = getSolution(bound);
            }
        }
        return bestField;
    }

    private static Trace getCandidateToFix(Field field) {
        List<Channel> overloadedChannels = new ArrayList<Channel>();
        for (Connector connector : field.getConnectors()) {
            Channel topChannel = connector.getTopChannel();
            Channel bottomChannel = connector.getBottomChannel();

            if (topChannel.isOverloaded()) overloadedChannels.add(topChannel);
            if (bottomChannel.isOverloaded()) overloadedChannels.add(bottomChannel);
        }

        Map<Trace, Integer> tracesEntrance = new HashMap<Trace, Integer>();

        for (Channel channel : overloadedChannels) {
            for (Trace trace : channel.getTraces()) {
                Integer entrance = tracesEntrance.get(trace);
                if (entrance != null) {
                    entrance += 1;
                } else {
                    entrance = 1;
                }
                tracesEntrance.put(trace, entrance);
            }
        }

        //FIXME I just want to sleep
        return overloadedChannels.get(0).getTraces().get(0);

    }

    private static boolean hasValuableLooseTraces(Field field) {
        for (Connector connector : field.getConnectors()) {
            Channel topChannel = connector.getTopChannel();
            Channel bottomChannel = connector.getBottomChannel();
            if (topChannel.isOverloaded()) {
                for (Trace trace : topChannel.getTraces()) {
                    if (!trace.isFixed()) return true;
                }
            }
            if (bottomChannel.isOverloaded()) {
                for (Trace trace : bottomChannel.getTraces()) {
                    if (!trace.isFixed()) return true;
                }
            }
        }
        return false;
    }

    private static boolean isAllowableField(Field field) {
        for (Connector connector : field.getConnectors()) {
            if (connector.getTopChannel().isOverloaded() || connector.getBottomChannel().isOverloaded()) {
                return false;
            }
        }
        return true;
    }

    private static Field getBestField(List<Field> bound) {
        Field bestField = bound.get(0);
        for (Field field : bound) {
            if (field.getGrade() < bestField.getGrade()) {
                bestField = field;
            }
        }
        return bestField;
    }

    private static void findBestSolution(Field field) {
        for (Link link : field.getLinks()) {
            Integer topLength = Tracer.calculateTopLength(link);
            Integer bottomLength = Tracer.calculateBottomLength(link);
            Trace trace = null;
            if (topLength < bottomLength) {
                trace = Tracer.traceToTop(field, link);
            } else {
                trace = Tracer.traceToBottom(field, link);
            }
            trace.setTopLength(topLength);
            trace.setBottomLength(bottomLength);
        }
    }

}
