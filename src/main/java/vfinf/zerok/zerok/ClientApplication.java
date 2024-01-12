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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class ClientApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/
        Pane pane = new Pane(/*fxmlLoader.load()*/);
        Scene scene = new Scene(pane, 1280, 720);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        pane.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        Planet earth = new Planet(pane, new Coords(360, 360),10,"Terra", 1);
        Planet moon = new Planet(pane, new Coords(360, 100),0.12,"Luna", 0.5);
        MapObject[] objects = {earth,moon};

        NeonLine a = new NeonLine(720,0,720,720);
        pane.getChildren().add(a.getHalation());
        pane.getChildren().add(a.getHighlight());

        try{
            Socket socket = new Socket("127.0.0.1", 8271);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String recieved = reader.readLine();
                TestUtils.printR(recieved);
                NeonObject.paintAll(jsonToMapObject(recieved));
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
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