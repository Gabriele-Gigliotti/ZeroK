package vfinf.zerok.zerok.networkTest.classes.graphics;

import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class NeonLine {
    private static final double STR_WIDTH = 5;
    private Line halation;
    private Line highlight;

    public NeonLine(double startX, double startY, double endX, double endY, Color color) {
        super();
        highlight = createLine(startX,startY,endX,endY,color);
        halation = createShadow(startX,startY,endX,endY,color);
    }
    public NeonLine(double startX, double startY, double endX, double endY) {
        highlight = createLine(startX,startY,endX,endY,Color.WHITE);
        halation = createShadow(startX,startY,endX,endY,Color.WHITE);
    }
    private Line createShadow(double startX, double startY, double endX, double endY, Color color){
        Line line = new Line(startX,startY,endX,endY);

        line.setStrokeWidth(STR_WIDTH);
        line.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);
        line.setStrokeLineJoin(javafx.scene.shape.StrokeLineJoin.ROUND);
        line.setBlendMode(BlendMode.SCREEN);
        line.setStroke(color);

        DropShadow shadow = new DropShadow();
        shadow.setColor(color);
        shadow.setRadius(STR_WIDTH);
        shadow.setSpread(0.3);

        // color otherwise looks too full
        if(color == Color.WHITE || color == Color.YELLOW){
            line.setStrokeWidth((STR_WIDTH)/2);
            shadow.setRadius((STR_WIDTH)*2);
            shadow.setSpread(0.6);
        }

        line.setEffect(shadow);
        return line;
    }
    private Line createLine(double startX, double startY, double endX, double endY, Color color){
        Line line = new Line(startX,startY,endX,endY);

        line.setOpacity(0.90);
        line.setStrokeWidth(STR_WIDTH/2.5);
        line.setStroke(Color.WHITE);
        line.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);
        line.setStrokeLineJoin(javafx.scene.shape.StrokeLineJoin.ROUND);
        line.setBlendMode(BlendMode.SCREEN);

        return line;
    }

    public Line getHalation() {
        return halation;
    }

    public Line getHighlight() {
        return highlight;
    }
}
