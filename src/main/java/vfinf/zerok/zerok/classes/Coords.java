package vfinf.zerok.zerok.classes;

import java.util.HashMap;
import java.util.Map;

public class Coords {
    private double x, y;

    public Coords(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Map<String,Double> getCoords(){
        Map<String,Double> coordsMap = new HashMap<String,Double>();
        coordsMap.put("x",x);
        coordsMap.put("y",y);

        return coordsMap;
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
