package pojo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Channel implements Serializable {

    public Channel(){}

    public Channel(int maxCapacity, boolean isTop) {
        this.maxCapacity = maxCapacity;
        this.isTop = isTop;
    }

    @Deprecated
    public Channel(Connector connector, int maxCapacity, boolean isTop) {
        this.connector = connector;
        this.maxCapacity = maxCapacity;
        this.isTop = isTop;
    }

    private Connector connector;
    private int maxCapacity;
    private boolean isTop;
    private List<Trace> traces = new LinkedList<Trace>();

    public List<Trace> getTraces() {
        return traces;
    }

    public void setTraces(List<Trace> traces) {
        this.traces = traces;
    }

    @JsonIgnore
    public int getOccupancy() {
        return traces.stream().collect(Collectors.summingInt((trace) -> trace.getLink().getThickness()));
    }

    public Connector getConnector() {
        return connector;
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Channel channel = (Channel) o;

        if (isTop != channel.isTop) return false;
        return !(connector != null ? !connector.equals(channel.connector) : channel.connector != null);

    }

    @Override
    public int hashCode() {
        int result = connector != null ? connector.hashCode() : 0;
        result = 31 * result + (isTop ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "connector=" + connector.getX() + "_" + connector.getY() +
                ", occupancy=" + getOccupancy() +
                ", maxCapacity=" + maxCapacity +
                ", isTop=" + isTop +
                '}';
    }

    @JsonIgnore
    public boolean isOverloaded() {
        return getOccupancy() > this.maxCapacity;
    }
}
