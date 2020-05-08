package controller;

import service.Graph.graph;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import service.Algorithm.scanAlgorithm;
import service.ReadFile.readGraph;

import java.io.File;

public class Controller {

    @FXML
    private TextField eps;

    @FXML
    private TextField mu;

    @FXML
    private TextArea display;

    private String filePath;

    private float m;
    private float ep;

    private graph gr;

    public Controller() {
        this.filePath = "";
        this.m = 0;
        this.ep = 0;
    }

    /*
     ** This Method get the Path of the File when it is uploaded
     */
    public void uploadsingle(javafx.event.ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();

        File f = fc.showOpenDialog(null);

        if (f != null) {
            filePath = f.getAbsolutePath();
            System.out.println("hier: " + filePath);
        }
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /*
     ** This Method checks Whether the Input is valid or not.
     ** it initializes the Mu and the Epsilon values
     */
    private boolean inputReady(String mu, String ep) {
        if (isNumeric(mu) && isNumeric(ep)) {
            this.m = Float.parseFloat(mu);
            this.ep = Float.parseFloat(ep);
            return true;
        }
        return false;
    }

    /*
     ** This Method checks whether the Graph can be created or not
     ** It initializes the Graph
     */
    private boolean createGraph(String path) {
        readGraph rd = new readGraph();
        try {
            this.gr = rd.initialaizeGraf(path);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*
     ** This Method executes the ScanAlgorithm with the Condition that mu and eps und the Graph are valid
     */
    public void execute(javafx.event.ActionEvent actionEvent) {

        String muu = mu.getText();
        String epsilon = eps.getText();

        if (!filePath.isEmpty() && inputReady(muu, epsilon) && createGraph(filePath) && gr != null) {

            scanAlgorithm sc = new scanAlgorithm(gr, ep, m);
            sc.executeScanAlgorithm();
            display.setText(sc.toString());

        } else {
            display.setText("Die Eingabe ist falsch");
        }

    }

}
