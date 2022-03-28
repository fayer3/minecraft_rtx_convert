package minecraft_converter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        MainController c = loader.getController();
        primaryStage.setTitle("Minecraft Java to bedrock converter");
        primaryStage.setScene(new Scene(root));
        primaryStage.setOnCloseRequest( event -> c.close());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
