package vfinf.zerok.zerok;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import vfinf.zerok.zerok.networkTest.classes.Coords;
import vfinf.zerok.zerok.networkTest.classes.Planet;

import java.io.IOException;
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
        Scene scene = new Scene(pane, 200, 200);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        pane.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        Planet planet = new Planet(new Coords(100,100));
        planet.paintHalation(pane);
        planet.paintHighlight(pane);

        ObjectMapper mapper = new ObjectMapper();
        String jsonstring = mapper.writeValueAsString(planet);
        System.out.println(jsonstring);
    }

    public static void main(String[] args) {
        launch();
    }
}
