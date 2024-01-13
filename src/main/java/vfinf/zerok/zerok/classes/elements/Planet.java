package vfinf.zerok.zerok.classes.elements;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import vfinf.zerok.zerok.classes.Coords;
import vfinf.zerok.zerok.classes.graphics.NeonPath;

public class Planet extends MapObject {
    // octagon
    //private final static String path = "M0 50 L15 15 L50 0 L85 15 L100 50 L85 85 L50 100 L15 85 Z";

    // dodecagon
    private final static String path = "M50,5L71.18,11.18L88.82,28.82L94.99,50L88.82,71.18L71.18,88.82L50,94.99L28.82,88.82L11.18,71.18L5.01,50L11.18,28.82L28.82,11.18Z";
    private double mass;
    private final static int offsetX = 50;
    private final static int offsetY = 50;

    public Planet(Coords coords, double mass, String name) {
        super(null, coords, name);
        this.mass = mass;
        setRenderedPath(new NeonPath(path, getCoords().getX()-offsetX, getCoords().getY()-offsetY, 0, Color.WHITE));
    }
    public Planet(Pane pane, Coords coords, double mass, String name) {
        super(pane, coords, name);
        this.mass = mass;
        setRenderedPath(new NeonPath(path, getCoords().getX()-offsetX, getCoords().getY()-offsetY, 0, Color.WHITE));
    }
    public Planet(Pane pane, Coords coords, double mass, String name, double size) {
        super(pane, coords, name);
        this.mass = mass;
        setRenderedPath(new NeonPath(path, getCoords().getX()-offsetX, getCoords().getY()-offsetY, 0, size, Color.WHITE));
    }

    public void setPathCoords(Coords coords){
        setCoords(coords);
        renderedPath.setCoords(coords.getX()-offsetX, coords.getY()-offsetY);
    }

    public double getMass() {
        return mass;
    }
    public void setMass(double mass) {
        this.mass = mass;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "mass=" + mass +
                ", name='" + name + '\'' +
                ", coords=" + coords +
                ", rotation="+ getRotation() +
                '}';
    }
}