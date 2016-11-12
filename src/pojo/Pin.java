package pojo;

import java.io.Serializable;

public class Pin implements Serializable{

    public Pin() {

    }

    public Pin(int x, int y) {
        this.y = y;
        this.x = x;
    }

    @Deprecated
    public Pin(int x, int y, Connector container) {
        this.y = y;
        this.x = x;
        this.container = container;
    }

    private int x;
    private int y;

    private Connector container;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Connector getContainer() {
        return container;
    }

    public void setContainer(Connector container) {
        this.container = container;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pin pin = (Pin) o;

        if (x != pin.x) return false;
        if (y != pin.y) return false;
        return !(container != null ? !container.equals(pin.container) : pin.container != null);

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + (container != null ? container.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pin{" +
                "x=" + x +
                ", y=" + y +
                ", connector=[" + container.getX() +
                ", " + container.getY() +
                "]}";
    }
}
