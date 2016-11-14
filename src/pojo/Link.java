package pojo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;

public class Link implements Serializable{

    public Link() {}

    public Link(Pin firstPin, Pin secondPin, Integer thickness) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
        this.thickness = thickness;
        this.topLength = calculateTopLength(this);
        this.bottomLength = calculateBottomLength(this);
    }

    private Pin firstPin;
    private Pin secondPin;
    private Integer thickness;
    private Integer topLength;
    private Integer bottomLength;

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


    private Integer calculateBottomLength(Link link) {
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

    private Integer calculateTopLength(Link link) {
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
