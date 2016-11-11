package pojo;

import java.util.List;

public class Channel {

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
    private int occupancy;
    private int maxCapacity;
    private boolean isTop;
    private List<Trace> traces;

    public List<Trace> getTraces() {
        return traces;
    }

    public void setTraces(List<Trace> traces) {
        this.traces = traces;
    }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
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

    public void increaseOccupancy(Integer value) {
        occupancy = occupancy + value;
    }

    public void decreaseOccupancy(Integer value) {
        occupancy = occupancy - value;
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
                "connector=" + connector +
                ", occupancy=" + occupancy +
                ", maxCapacity=" + maxCapacity +
                ", isTop=" + isTop +
                ", traces=" + traces +
                '}';
    }

    public boolean isOverloaded() {
        return this.occupancy > this.maxCapacity;
    }
}
