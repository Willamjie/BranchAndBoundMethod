package pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Field implements Serializable{

    public Field() {

    }

    public Field(List<Connector> connectors, List<Link> links) {
        this.connectors = connectors;
        this.links = links;
    }

    private List<Connector> connectors = new ArrayList<Connector>();
    private List<Trace> traces = new ArrayList<Trace>();
    private List<Link> links = new ArrayList<Link>();

    public List<Connector> getConnectors() {
        return connectors;
    }

    public void setConnectors(ArrayList<Connector> connectors) {
        this.connectors = connectors;
    }

    public List<Trace> getTraces() {
        return traces;
    }

    public void setTraces(List<Trace> traces) {
        this.traces = traces;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

    public void addTrace(Trace trace) {
        this.traces.add(trace);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        if (connectors != null ? !connectors.equals(field.connectors) : field.connectors != null) return false;
        if (traces != null ? !traces.equals(field.traces) : field.traces != null) return false;
        return !(links != null ? !links.equals(field.links) : field.links != null);

    }

    @Override
    public int hashCode() {
        int result = connectors != null ? connectors.hashCode() : 0;
        result = 31 * result + (traces != null ? traces.hashCode() : 0);
        result = 31 * result + (links != null ? links.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Field{" +
                "connectors=" + connectors +
                ", traces=" + traces +
                ", links=" + links +
                '}';
    }

    public Integer getGrade() {
        Integer grade = 0;
        for (Trace trace : traces) {
            if (trace.getPath().get(0).isTop()) {
                grade += trace.getTopLength();
            } else {
                grade += trace.getBottomLength();
            }
        }
        return grade;
    }
}
