package vfinf.zerok.zerok.classes.elements;

import java.io.Serializable;

public class JsonedPlanetPosition implements Serializable {
    private PlanetPosition[] pos;

    public JsonedPlanetPosition(PlanetPosition[] pos) {
        this.pos = pos;
    }

    public PlanetPosition[] getPos() {
        return pos;
    }

    public void setPos(PlanetPosition[] pos) {
        this.pos = pos;
    }
}
