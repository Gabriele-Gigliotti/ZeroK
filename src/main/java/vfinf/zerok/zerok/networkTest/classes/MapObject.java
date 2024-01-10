package vfinf.zerok.zerok.networkTest.classes;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javafx.scene.layout.Pane;

@JsonSerialize(using = MapObjectSerializer.class)
public abstract class MapObject {
    private Coords coords;

    public MapObject(Coords coords) {
        this.coords = coords;
    }

    void paintHalation(Pane pane) {}
    void paintHighlight(Pane pane) {}

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }
}
