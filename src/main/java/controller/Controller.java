package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import service.Algorithm.scanAlgorithm;
import service.Graph.graph;
import service.ReadFile.readGraph;

import java.io.File;

public class Controller {

    @FXML
    private Button btnGraphPath;

    @FXML
    private TextField eps;

    @FXML
    private TextField mu;

    @FXML
    private TextArea display;

    public void uploadsingle(javafx.event.ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();

        File f = fc.showOpenDialog(null);

        if (f != null) {
            System.out.println("hier: " + f.getAbsolutePath());
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void getEingabe(javafx.event.ActionEvent actionEvent) {

        String muu = mu.getText();
        String epsilon = eps.getText();

        System.out.println("oben: " + muu + " " + epsilon + " " + isNumeric(muu) + " " + isNumeric(epsilon));

        if (isNumeric(muu) && isNumeric(epsilon)) {

            System.out.println("oben: " + muu + " " + epsilon + " " + isNumeric(muu) + " " + isNumeric(epsilon));

            float epps = Float.parseFloat(epsilon);
            float myu = Float.parseFloat(muu);
            if (epps < 1 && epps > 0) {

                String filename = "D:\\Projects\\jalal_Software\\ScanSoftware\\src\\main\\java\\Input_Files\\testGraph.txt";

                readGraph rd = new readGraph();
                graph gr = rd.selfGenerated(filename);
                System.out.println(gr.toString());
                scanAlgorithm sc = new scanAlgorithm(gr, epps, myu);
                sc.executeScanAlgorithm();
                display.setText(sc.toString());
            } else {
                display.setText("Die Eingabe ist falsch von drinn");
            }
        } else {
            display.setText("Die Eingabe ist falsch");
        }

        System.out.println("hier: " + muu + " " + epsilon);
    }

}
