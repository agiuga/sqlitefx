package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    	Parent root = FXMLLoader.load(getClass().getResource("NewUserView.fxml"));
        primaryStage.setTitle("Volunteer System");

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.setMaximized(false);// keeps stage window same size as preview in scenebuilder
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
