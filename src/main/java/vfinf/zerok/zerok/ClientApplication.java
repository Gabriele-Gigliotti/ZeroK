package vfinf.zerok.zerok;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import vfinf.zerok.zerok.classes.Coords;
import vfinf.zerok.zerok.classes.TestUtils;
import vfinf.zerok.zerok.classes.elements.MapObject;
import vfinf.zerok.zerok.classes.elements.Planet;
import vfinf.zerok.zerok.classes.elements.PlanetPosition;
import vfinf.zerok.zerok.classes.graphics.NeonLine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;

public class ClientApplication extends Application {
    ObjectInputStream reader;
    ObjectOutputStream writer;
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1280, 720);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        pane.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        Planet earth = new Planet(pane, new Coords(360, 360),10,"Terra", 1);
        Planet moon = new Planet(pane, new Coords(360, 100),0.12,"Luna", 0.5);
        MapObject[] objects = {earth,moon};
        MapObject.paintAll(objects);

        NeonLine a = new NeonLine(720,0,720,720);
        pane.getChildren().add(a.getHalation());
        pane.getChildren().add(a.getHighlight());


        try{
            Socket socket = new Socket("127.0.0.1", 8271);
            reader = new ObjectInputStream(socket.getInputStream());
            writer = new ObjectOutputStream(socket.getOutputStream());
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        test(objects);
    }

    private void test(MapObject[] objects) throws IOException, ClassNotFoundException {
        Duration duration = Duration.millis(1082);
        Timeline timeline = new Timeline(new KeyFrame(duration, event -> {
            PlanetPosition[] received = new PlanetPosition[0];
            try {
                received = (PlanetPosition[]) reader.readObject(); } catch (Exception e) {}
            for (int i = 0; i < received.length; i++) {
                MapObject element = objects[i];
                element.setPathCoords(new Coords(received[i].getX(), received[i].getY()));
                element.setRotation(received[i].getRotation());
            }
            TestUtils.printR(Arrays.toString(objects));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setOnFinished(event -> timeline.stop());
        // Start the timeline
        timeline.play();
    }

    public static void main(String[] args) {
        launch();
    }
}