package vfinf.zerok.zerok.networkTest.classes;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

@JsonSerialize(using = MapObjectSerializer.class)
public abstract class MapObject {
    private Pane pane;
    private Coords coords;
    private String name;
    private double mass;
    //private double velocity; //spostamento/tempo

    public MapObject(Pane pane, Coords coords, double mass, String name) {
        this.pane = pane;
        this.coords = coords;
        this.name = name;
        this.mass = mass;
    }

    public Planet paintHalation() {return null;}
    public Planet paintHighlight() {return null;}

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public Pane getPane() {
        return pane;
    }
}
