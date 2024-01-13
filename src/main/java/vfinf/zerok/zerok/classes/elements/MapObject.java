package vfinf.zerok.zerok.classes.elements;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javafx.scene.layout.Pane;
import vfinf.zerok.zerok.classes.Coords;
import vfinf.zerok.zerok.classes.graphics.NeonObject;

public abstract class MapObject extends NeonObject {
    protected String name;

    //private double velocity; //spostamento/tempo

    public MapObject(Pane pane, Coords coords, String name) {
        super(pane,coords);
        this.name = name;
    }

    public void setPathCoords(Coords coords){
        setCoords(coords);
        renderedPath.setCoords(coords.getX(), coords.getY());
    }

    public Coords getCoords() {
        return super.getCoords();
    }

    public void setCoords(Coords coords) {
        super.setCoords(coords);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
