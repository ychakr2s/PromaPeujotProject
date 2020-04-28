package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {

    @FXML
    private Button btnGraphUpload;
    @FXML
    private TextField eps;

    @FXML
    private TextField mu;

    @FXML
    private TextArea display;

//    @FXML
//    void uploadsingle(ActionEvent event) {
//
//    }

    public void uploadsingle(javafx.event.ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
//        fc.getExtensionFilters().add(new CommonDialogs.ExtensionFilter("er"));
        File f = fc.showOpenDialog(null);

        if (f != null) {
            System.out.println("hier: " + f.getAbsolutePath());
        }
    }

    public void getEingabe(javafx.event.ActionEvent actionEvent) {
        String muu = mu.getText();
        String epps = eps.getText();
        display.setText(muu);
        System.out.println("hier: " + muu + " " + epps);
    }
}
