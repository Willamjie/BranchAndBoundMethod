package pojo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Trace implements Serializable{

    public Trace() {
    }

    public Trace(List<Channel> path, Link link) {
        this.path = path;
        this.link = link;
    }

    private List<Channel> path = new ArrayList<Channel>();
    private Link link;
    private boolean fixed;
    private Integer topLength;
    private Integer bottomLength;

    public Integer getTopLength() {
        return topLength;
    }

    public void setTopLength(Integer topLength) {
        this.topLength = topLength;
    }

    public Integer getBottomLength() {
        return bottomLength;
    }

    public void setBottomLength(Integer bottomLength) {
        this.bottomLength = bottomLength;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public List<Channel> getPath() {
        return path;
    }

    public void setPath(List<Channel> path) {
        this.path = path;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Integer getLength() {
        if (path.get(0).isTop()) {
            return topLength;
        } else {
            return bottomLength;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trace trace = (Trace) o;

        if (fixed != trace.fixed) return false;
        if (path != null ? !path.equals(trace.path) : trace.path != null) return false;
        return !(link != null ? !link.equals(trace.link) : trace.link != null);

    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (fixed ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Trace{" +
                "path=" + path +
                ", link=" + link +
                ", fixed=" + fixed +
                ", topLength=" + topLength +
                ", bottomLength=" + bottomLength +
                '}';
    }
}

