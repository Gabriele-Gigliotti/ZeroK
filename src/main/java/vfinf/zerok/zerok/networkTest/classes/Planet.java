package vfinf.zerok.zerok.networkTest.classes;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import vfinf.zerok.zerok.networkTest.classes.graphics.NeonPath;

public class Planet extends MapObject {
    // octagon
    //private final static String path = "M0 50 L15 15 L50 0 L85 15 L100 50 L85 85 L50 100 L15 85 Z";

    // dodecagon
    private final static String path = "M50,5L71.18,11.18L88.82,28.82L94.99,50L88.82,71.18L71.18,88.82L50,94.99L28.82,88.82L11.18,71.18L5.01,50L11.18,28.82L28.82,11.18Z";
    private NeonPath renderedPath;
    private final static int offsetX = 50;
    private final static int offsetY = 50;

    public Planet(Pane pane, Coords coords, double mass, String name) {
        super(pane, coords, mass, name);
        renderedPath = new NeonPath(path, getCoords().getX()-offsetX, getCoords().getY()-offsetY, 0, Color.WHITE);
    }

    public Planet(Pane pane, Coords coords, double mass, String name, double size) {
        super(pane, coords, mass, name);
        renderedPath = new NeonPath(path, getCoords().getX()-offsetX, getCoords().getY()-offsetY, 0, size, Color.WHITE);
    }

    @Override
    public Planet paintHalation(){
        if(super.getPane().getChildren().contains(renderedPath.getHalation())){super.getPane().getChildren().remove(renderedPath.getHalation());}
        super.getPane().getChildren().add(renderedPath.getHalation());
        return this;
    }

    @Override
    public Planet paintHighlight(){
        if(super.getPane().getChildren().contains(renderedPath.getHighlight())){super.getPane().getChildren().remove(renderedPath.getHighlight());}
        super.getPane().getChildren().add(renderedPath.getHighlight());
        return this;
    }

    public void setPathCoords(Coords coords){
        super.setCoords(coords);
        renderedPath.setCoords(coords.getX()-offsetX, coords.getY()-offsetY);
    }

    public void setRotation(double rotation){
        renderedPath.setRotation(rotation);
    }

    public NeonPath getRenderedPath() {
        return renderedPath;
    }

    public void setRenderedPath(NeonPath renderedPath) {
        this.renderedPath = renderedPath;
    }
}