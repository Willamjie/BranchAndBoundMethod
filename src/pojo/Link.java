package pojo;

public class Link {

    public Link() {}

    public Link(Pin firstPin, Pin secondPin, Integer thickness) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
        this.thickness = thickness;
    }

    private Pin firstPin;
    private Pin secondPin;
    private Integer thickness;

    public Pin getFirstPin() {
        return firstPin;
    }

    public void setFirstPin(Pin firstPin) {
        this.firstPin = firstPin;
    }

    public Pin getSecondPin() {
        return secondPin;
    }

    public void setSecondPin(Pin secondPin) {
        this.secondPin = secondPin;
    }

    public Integer getThickness() {
        return thickness;
    }

    public void setThickness(Integer thickness) {
        this.thickness = thickness;
    }

    //TODO Try to minimize it.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        if (firstPin != null) {
            if (!firstPin.equals(link.firstPin)) {
                if (!firstPin.equals(link.secondPin) || !secondPin.equals(link.firstPin)) {
                    return false;
                }
            } else if (link.firstPin != null){
                return false;
            }
        }

        if (secondPin != null) {
            if (!secondPin.equals(link.secondPin)) {
                if (!secondPin.equals(link.firstPin) || !firstPin.equals(link.secondPin)) {
                    return false;
                }
            } else if (link.secondPin != null){
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstPin != null ? firstPin.hashCode() : 0;
        result = 31 * result + (secondPin != null ? secondPin.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Link{" +
                "firstPin=" + firstPin +
                ", secondPin=" + secondPin +
                ", thickness=" + thickness +
                '}';
    }
}
