package vfinf.zerok.zerok.networkTest.classes.graphics;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import vfinf.zerok.zerok.networkTest.classes.MapObjectSerializer;

import java.io.Serializable;

public class NeonPath implements Serializable {
    private static final double STR_WIDTH = 5;
    private String pathData;
    private final SVGPath halation;
    private final SVGPath highlight;

    public NeonPath(String pathData, double x, double y, double rotation ,Color color) {
        this.pathData = pathData;

        halation = createShadows(pathData, rotation, color, 1);
        highlight = createLines(pathData, rotation, color, 1);

        halation.setLayoutX(x); halation.setLayoutY(y);
        highlight.setLayoutX(x); highlight.setLayoutY(y);
    }

    /**
     * For giving the path an equal size
     */
    public NeonPath(String pathData, double x, double y, double rotation , double size, Color color) {
        this.pathData = pathData;

        halation = createShadows(pathData, rotation, color, size);
        highlight = createLines(pathData, rotation, color, size);

        halation.setLayoutX(x); halation.setLayoutY(y);
        highlight.setLayoutX(x); highlight.setLayoutY(y);
    }

    private SVGPath createShadows(String pathData, double rotation, Color color, double size){
        SVGPath path = new SVGPath();
        path.setContent(pathData);
        path.setScaleX(size);
        path.setScaleY(size);

        path.setStrokeWidth(STR_WIDTH/size);
        path.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);
        path.setStrokeLineJoin(javafx.scene.shape.StrokeLineJoin.ROUND);
        path.setBlendMode(BlendMode.SCREEN);
        path.setStroke(color);
        path.setRotate(rotation);
        path.setFill(Color.TRANSPARENT);

        DropShadow shadow = new DropShadow();
        shadow.setColor(color);
        shadow.setRadius(STR_WIDTH);
        shadow.setSpread(0.3);

        // color otherwise looks too full
        if(color == Color.WHITE || color == Color.YELLOW){
            path.setStrokeWidth((STR_WIDTH/size)/2);
            shadow.setRadius((STR_WIDTH/size)*2);
            shadow.setSpread(0.6);
        }

        path.setEffect(shadow);
        return path;
    }
    private SVGPath createLines(String pathData, double rotation, Color color, double size){
        if(color == Color.WHITE || color == Color.YELLOW) return new SVGPath();

        SVGPath path = new SVGPath();
        path.setContent(pathData);
        path.setScaleX(size);
        path.setScaleY(size);

        path.setOpacity(0.90);
        path.setStrokeWidth((STR_WIDTH/2.5)/size);
        path.setStroke(color.brighter());
        path.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);
        path.setStrokeLineJoin(javafx.scene.shape.StrokeLineJoin.ROUND);
        path.setBlendMode(BlendMode.SCREEN);
        path.setFill(Color.TRANSPARENT);
        path.setRotate(rotation);

        return path;
    }

    public void setCoords(double x, double y){
        halation.setLayoutX(x); halation.setLayoutY(y);
        highlight.setLayoutX(x); highlight.setLayoutY(y);
    }

    public void setRotation(double rotation){
        halation.setRotate(rotation);
        highlight.setRotate(rotation);
    }

    public SVGPath getHalation() {
        return halation;
    }

    public SVGPath getHighlight() {
        return highlight;
    }

    @Override
    public String toString() {
        return "NeonPath{" +
                "pathData='" + pathData + '\'' +
                ", halation=" + halation +
                ", highlight=" + highlight +
                '}';
    }
}
