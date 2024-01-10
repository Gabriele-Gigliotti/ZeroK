package vfinf.zerok.zerok.networkTest.classes;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import vfinf.zerok.zerok.networkTest.classes.graphics.NeonPath;

public class Planet extends MapObject {
    private final static String path = "M0 50 L15 15 L50 0 L85 15 L100 50 L85 85 L50 100 L15 85 Z";
    private NeonPath renderedPath;
    private final static int offsetX = 50;
    private final static int offsetY = 50;

    public Planet(Coords coords) {
        super(coords);
        renderedPath = new NeonPath(path, getCoords().getX()-offsetX, getCoords().getY()-offsetY, 0, Color.WHITE);
    }

    @Override
    public void paintHalation(Pane pane){
        pane.getChildren().add(renderedPath.getHalation());
    }

    @Override
    public void paintHighlight(Pane pane){
        pane.getChildren().add(renderedPath.getHighlight());
    }

    public NeonPath getRenderedPath() {
        return renderedPath;
    }

    public void setRenderedPath(NeonPath renderedPath) {
        this.renderedPath = renderedPath;
    }
}