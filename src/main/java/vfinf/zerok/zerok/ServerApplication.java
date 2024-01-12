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
import javafx.stage.Stage;
import javafx.util.Duration;
import vfinf.zerok.zerok.classes.Coords;
import vfinf.zerok.zerok.classes.TestUtils;
import vfinf.zerok.zerok.classes.elements.MapObject;
import vfinf.zerok.zerok.classes.elements.Planet;
import vfinf.zerok.zerok.classes.graphics.NeonLine;
import vfinf.zerok.zerok.classes.graphics.NeonObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Random;

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
        Socket connessione=null;
        boolean ready = false;

        Planet earth = new Planet(new Coords(360, 360),10,"Terra");
        Planet moon = new Planet(new Coords(360, 100),0.12,"Luna");
        MapObject[] objects = {earth,moon};

        try{
            ServerSocket serverSocket=new ServerSocket(8271);
            while (!ready) {
                TestUtils.printR("In attesa di connessione...");
                connessione=serverSocket.accept();
                TestUtils.printR("Connessione accettata da "+connessione);
                ready = true;

                reader = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
                writer = new PrintWriter(connessione.getOutputStream(), true);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        animate(earth,moon);
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

        writer.println("{\"pos\":"+jsonstring+'}');
    }
}