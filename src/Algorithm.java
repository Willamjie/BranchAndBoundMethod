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
        Field secondBestField = SerializationUtils.clone(bestField);
        if (!isAllowableField(bestField)) {
            if (hasValuableLooseTraces(bestField)) {
                //FIXME Actually we don't need to remove bestField and we just need to add new field with retraced candidate trace
                bound.remove(bestField);
                Trace firstCandidate = getCandidateToFix(bestField);
                Tracer.trace(bestField, firstCandidate, true).setFixed(true);

                Trace secondCandidate = getCandidateToFix(secondBestField);
                Tracer.trace(secondBestField, secondCandidate, false).setFixed(true);

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
        List<Trace> candidateTraces = new LinkedList<>();
        for (Connector connector : field.getConnectors()) {
            Channel topChannel = connector.getTopChannel();
            Channel bottomChannel = connector.getBottomChannel();

            if (topChannel.isOverloaded()) candidateTraces.addAll(topChannel.getTraces());
            if (bottomChannel.isOverloaded()) candidateTraces.addAll(bottomChannel.getTraces());
        }

        Map<Trace, Long> tracesEntrance =  candidateTraces.stream()
                .filter((trace) -> !trace.isFixed())
                .collect(
                        Collectors.toMap(
                                (value) -> value,
                                (value) -> candidateTraces.stream().filter((candidate) -> candidate.equals(value)).count()
                        )
                );

        List<Map.Entry<Trace, Long>> sortedTraces = tracesEntrance.entrySet().stream()
                .sorted(new Comparator<Map.Entry<Trace, Long>>() {
                    @Override
                    public int compare(Map.Entry<Trace, Long> first, Map.Entry<Trace, Long> second) {
                        if (first.getValue() == second.getValue()) {
                            if (first.getKey().getLength() == second.getKey().getLength()) {
                                return first.getKey().getLink().getThickness().compareTo(second.getKey().getLink().getThickness());
                            } else {
                                return first.getKey().getLength().compareTo(second.getKey().getLength());
                            }
                        } else {
                            return first.getValue().compareTo(second.getValue());
                        }
                    }
                })
                .collect(Collectors.toList());

        Trace bestCandidate = sortedTraces.stream()
                .filter((entry) -> entry.getValue() == sortedTraces.get(0).getValue())
                .findFirst()
                .map((entry) -> entry.getKey())
                .get();

        return bestCandidate;

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

            Trace trace = new Trace();
            trace.setLink(link);
            Tracer.trace(field, trace, topLength < bottomLength);

            field.addTrace(trace);
            trace.setTopLength(topLength);
            trace.setBottomLength(bottomLength);
        }
    }

}
