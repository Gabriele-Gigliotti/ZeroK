package vfinf.zerok.zerok;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import vfinf.zerok.zerok.classes.Coords;
import vfinf.zerok.zerok.classes.TestUtils;
import vfinf.zerok.zerok.classes.elements.JsonedPlanetPosition;
import vfinf.zerok.zerok.classes.elements.MapObject;
import vfinf.zerok.zerok.classes.elements.Planet;
import vfinf.zerok.zerok.classes.elements.PlanetPosition;
import vfinf.zerok.zerok.classes.graphics.NeonLine;
import vfinf.zerok.zerok.classes.graphics.NeonObject;
import vfinf.zerok.zerok.classes.graphics.NeonPath;

import java.io.IOException;
import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class ClientApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private MapObject[] jsonToMapObject(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonedPlanetPosition test = mapper.readValue(json, JsonedPlanetPosition.class);
        System.out.println(test.getPos()[0].getRotation());
        return null;
    }

    public static void main(String[] args) {
        launch();
    }
}