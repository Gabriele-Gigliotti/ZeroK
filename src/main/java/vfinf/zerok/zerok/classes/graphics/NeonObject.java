package vfinf.zerok.zerok.classes.graphics;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javafx.scene.layout.Pane;
import vfinf.zerok.zerok.classes.Coords;
import vfinf.zerok.zerok.classes.serializers.NeonObjectSerializer;

@JsonSerialize(using = NeonObjectSerializer.class)
public abstract class NeonObject {
    protected Pane pane;
    protected Coords coords;
    protected NeonPath renderedPath;

    public NeonObject(Pane pane, Coords coords) {
        this.pane = pane;
        this.coords = coords;
    }
    public static void paintAll(NeonObject[] objects){
        for (NeonObject o:objects) {o.paintHalation();}
        for (NeonObject o:objects) {o.paintHighlight();}
    }
    public NeonObject paintHalation() {
        if(pane.getChildren().contains(renderedPath.getHalation())){pane.getChildren().remove(renderedPath.getHalation());}
        pane.getChildren().add(renderedPath.getHalation());
        return this;
    }
    public NeonObject paintHighlight() {
        if(pane.getChildren().contains(renderedPath.getHighlight())){pane.getChildren().remove(renderedPath.getHighlight());}
        pane.getChildren().add(renderedPath.getHighlight());
        return this;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }
    public NeonPath getRenderedPath() {
        return renderedPath;
    }

    public void setRenderedPath(NeonPath renderedPath) {
        this.renderedPath = renderedPath;
    }

}
