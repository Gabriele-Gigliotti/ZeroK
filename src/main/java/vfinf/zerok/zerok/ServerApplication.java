package vfinf.zerok.zerok;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import vfinf.zerok.zerok.networkTest.classes.Coords;
import vfinf.zerok.zerok.networkTest.classes.MapObject;
import vfinf.zerok.zerok.networkTest.classes.Planet;
import vfinf.zerok.zerok.networkTest.classes.graphics.NeonLine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/*
Socket connessione=null;
Random r= new Random();

try{
    ServerSocket serverSocket=new ServerSocket(3333);
    while (true) {
        System.out.println("In attesa di connessione...");
        connessione=serverSocket.accept();
        System.out.println("Connessione accettata da "+connessione);
        }
}catch (IOException e){
    e.printStackTrace();
}
*/
public class ServerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Pane pane = new Pane(/*fxmlLoader.load()*/);
        Scene scene = new Scene(pane, 1280, 720);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        pane.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        Planet earth = new Planet(pane, new Coords(360, 360),10,"Terra", 1);
        Planet moon = new Planet(pane, new Coords(360, 100),0.12,"Luna", 0.5);

        NeonLine a = new NeonLine(720,0,720,720);
        pane.getChildren().add(a.getHalation());
        pane.getChildren().add(a.getHighlight());
        animate(earth, moon);

    }

    public static void main(String[] args) {
        launch();
    }

    private void animate(Planet earth, Planet moon){

        int totalMoves = 100;
        final double[] movesCounter = {0};

        Duration duration = Duration.millis(1024);

        Timeline timeline = new Timeline(new KeyFrame(duration, event -> {
            double cnt = movesCounter[0];
            double rawX = Math.cos(cnt);
            double rawY = Math.sin(cnt);
            double x = rawX*300+360;
            double y = rawY*300+360;

            if (movesCounter[0] < totalMoves) {
                moon.setPathCoords(new Coords(x,y));
                moon.setRotation(Math.toDegrees((Math.atan(rawY/rawX))));
                earth.setRotation(cnt*100);

                moon.paintHighlight().paintHalation();
                earth.paintHalation().paintHighlight();

                //movesCounter[0] = cnt+ ((double)80/500000);
                movesCounter[0] = cnt+ ((double)80/10000);
                sendMap(new MapObject[]{earth,moon});
            } else {
                // Stop the timeline when the desired number of moves is reached
                try {
                    stop();
                } catch (Exception e) {}
            }
        }));

        // Set the timeline to repeat until the desired number of moves is reached
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setOnFinished(event -> timeline.stop());
        // Start the timeline
        timeline.play();
    }

    private void sendMap(MapObject[] map) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonstring = null;
        try {
            jsonstring = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(jsonstring);
    }
}