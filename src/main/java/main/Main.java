package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        URL url = new File("src/main/resources/fxml/scan.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        stage.setTitle("Scan Application");

        Scene primaryScene = new Scene(root, 980, 800);
        stage.setScene(primaryScene);

        stage.show();
    }

    public static void main(String[] args) {
        // Here you can work with args - command line parameters
        Application.launch(args);
    }
}