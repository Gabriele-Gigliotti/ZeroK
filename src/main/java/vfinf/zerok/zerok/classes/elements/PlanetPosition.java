package vfinf.zerok.zerok.classes.elements;

import java.io.Serializable;

public class PlanetPosition implements Serializable {
    private double rotation;
    private double x;
    private double y;

    public PlanetPosition(double rotation, double x, double y) {
        this.rotation = rotation;
        this.x = x;
        this.y = y;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
