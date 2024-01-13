package vfinf.zerok.zerok;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import vfinf.zerok.zerok.classes.Coords;
import vfinf.zerok.zerok.classes.TestUtils;
import vfinf.zerok.zerok.classes.elements.MapObject;
import vfinf.zerok.zerok.classes.elements.Planet;
import vfinf.zerok.zerok.classes.elements.PlanetPosition;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;


public class ServerApplication extends Application {
    ObjectInputStream reader;
    ObjectOutputStream writer;

    @Override
    public void start(Stage stage) throws IOException {
        Socket socket =null;
        boolean ready = false;

        Planet earth = new Planet(new Coords(360, 360),10,"Terra");
        Planet moon = new Planet(new Coords(360, 100),0.12,"Luna");
        MapObject[] objects = {earth,moon};

        try{
            ServerSocket serverSocket=new ServerSocket(8271);

            while (!ready) {
                TestUtils.printR("In attesa di connessione...");
                 socket=serverSocket.accept();
                TestUtils.printR("Connessione accettata da "+socket);
                writer = new ObjectOutputStream(socket.getOutputStream());
                reader = new ObjectInputStream(socket.getInputStream());
                ready = true;
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

        Duration duration = Duration.millis(1082);

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
                try {
                    sendMap(new MapObject[]{earth,moon});
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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

    private void sendMap(MapObject[] map) throws IOException {
        PlanetPosition[] toSend = new PlanetPosition[map.length];
        for (int i=0; i<toSend.length; i++){
            toSend[i] = new PlanetPosition(
                    map[i].getRenderedPath().getRotation(),
                    map[i].getCoords().getX(),
                    map[i].getCoords().getY()
            );
        }

        TestUtils.printR(Arrays.toString(toSend));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = writer;
        objectOutputStream.writeObject(toSend);
        objectOutputStream.flush();

        //byte[] serializedObject = byteArrayOutputStream.toByteArray();
        //writer.write(serializedObject);
    }
}