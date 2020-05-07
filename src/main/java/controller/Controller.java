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
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Controller {

    @FXML
    private Button btnGraphPath;

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

    public void uploadsingle(javafx.event.ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();

        File f = fc.showOpenDialog(null);

        if (f != null) {
            filePath = f.getAbsolutePath();
            System.out.println("hier: " + filePath);
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

    public boolean inputReady(String mu, String ep) {
//        mu = this.mu.getText();
//        ep = this.eps.getText();
        if (isNumeric(mu) && isNumeric(ep)) {
            this.m = Float.parseFloat(mu);
            this.ep = Float.parseFloat(ep);
            return true;
        }
        System.out.println("EPS is bigg");
        return false;
    }

    public boolean createGraph(String path) {
        try {
            readGraph rd = new readGraph();
            this.gr = rd.selfGenerated(path);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkFile(String pfad) {
        File file = new File(pfad);

        Path path = FileSystems.getDefault().getPath(pfad);
        System.out.println(" IS : " + Files.isWritable(path));

        boolean b = file.isFile() && file.canWrite() && file.canRead() && Files.isWritable(path);
        return b;

    }

    public void execute(javafx.event.ActionEvent actionEvent) {

        String muu = mu.getText();
        String epsilon = eps.getText();

        if (!filePath.isEmpty() && inputReady(muu, epsilon) && createGraph(filePath)) {
            System.out.println(gr.toString());
            scanAlgorithm sc = new scanAlgorithm(gr, ep, m);
            System.out.println(ep + " EP. " + " M: " + m);
            sc.executeScanAlgorithm();
            System.out.println(sc.toString());
            display.setText(sc.toString());

        } else {
            display.setText("Die Eingabe ist falsch");
        }

        System.out.println("hier: " + muu + " " + epsilon);
    }

}
