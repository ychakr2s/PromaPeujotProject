package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.CheckListComposants;
import model.Control;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import static java.lang.Integer.valueOf;

public class ControlViewer implements Initializable {
    //private static String docDate;
    /*
     ** This is located in First Page and when you click this Button goes to Composant.
     */
    @FXML
    private Button btn1;
    @FXML
    private TextArea date;
    @FXML
    private TextArea conShift;
    @FXML
    private TextArea chassis;
    @FXML
    private TextArea gravure;
    @FXML
    private TextArea status;
    @FXML
    private ComboBox<String> controleurControl;
    ObservableList<String> controlleur = FXCollections.observableArrayList("Mounia", "Hanane", "Fatimazehra", "Wissal",
            "Imane", "Ouassima", "Zineb", "Najlae", "Ali", "Brahim", "Mouhamed");
    private ControlProcessor controlProcessor;

    private Control control;

    private String selectedControleur = "";

    private String docDate;

    public void fillCombo() {
        controleurControl.setItems(controlleur);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;

        if (event.getSource() == btn1) {
            /*
             ** The Input must be checked before the User click Button Commencer le controle
             */
            if (gravure.getText().isEmpty() || chassis.getText().isEmpty()) {
                if (chassis.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, " Le numméro du chassis manque\nSaisir et continuer svp!");
                } else {
                    JOptionPane.showMessageDialog(null, " La gravure manque\nSaisir et continuer svp!");
                }
            } else if (checkChassisInputIsInt(chassis.getText()) && checkGravureInputIsConform(gravure.getText())) {
                if (null == control.getOperator()) {
                    JOptionPane.showMessageDialog(null, " Selectionez votre nom svp!");
                } else {
                    control.getStauts().setStarted();
                    stage = (Stage) btn1.getScene().getWindow();
                    stage.setTitle("Composant");
                    URL url = new File("src/com/resources/fxml/qcl.fxml").toURI().toURL();
                    root = FXMLLoader.load(url);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            }
        }
    }

    @FXML
    private void readControl(ActionEvent event) throws Exception {
        if (event.getSource() == controleurControl) {
            boolean resultCheckChassis = false;
            boolean resultCheckGravure = false;
            if (gravure.getText().isEmpty() || chassis.getText().isEmpty()) {
                if (chassis.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, " Le numméro du chassis manque\nSaisir et continuer svp!");
                } else {
                    JOptionPane.showMessageDialog(null, " La gravure manque\nSaisir et continuer svp!");
                }
            } else {
                if (checkChassisInputIsInt(chassis.getText())) {
                    int chass = valueOf(chassis.getText());
                    controlProcessor.getControl().getNumChassis().setNumChassis(chass);
                    resultCheckChassis = true;
                }
                if (checkGravureInputIsConform(gravure.getText())) {
                    String grav = gravure.getText();
                    controlProcessor.getControl().getGravure().setGravure(grav);
                    resultCheckGravure = true;
                }
            }
            if (resultCheckChassis && resultCheckGravure) {
                controlProcessor.createControl();
                status.setText(controlProcessor.getControl().getStauts().statusToString());
            }
        }
    }

    private boolean checkGravureInputIsConform(String text) {
        //TODO check routine for the gravure value conformity
        return true;
    }

    private boolean checkChassisInputIsInt(String input) {
        boolean result;
        try {
            int tmp = Integer.parseInt(input);
            result = true;
        } catch (NumberFormatException ex) {
            result = false;
            JOptionPane.showMessageDialog(null, " Le numméro du chassis n'accepte que les chiffre" +
                    "\nSaisir et continuer svp!");
        }
        return result;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String startDate = getStartDate();
        int shift = getShift();
        control = new CheckListComposants(docDate, shift, 0, null, null, 0);
        controlProcessor = ControlProcessor.getInstance(control);

        fillCombo();
        getSelectedControleur();

        String statusX = controlProcessor.getControl().getStauts().statusToString();
        status.setText(statusX);
        date.setText(startDate);
        conShift.setText(shiftToString(shift));
    }

    private int getShift() {
        int shift = 0;
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter forma = DateTimeFormatter.ofPattern("HH");
        String formattedDate = currentTime.format(forma);
        int time = Integer.parseInt(formattedDate);
        if (time >= 6 && time < 14) {
            shift = 1;
        } else if (time >= 14 && time < 22) {
            shift = 2;
        } else {
            shift = 3;
        }
        return shift;
    }

    private String shiftToString(int shift) {
        String result = "";
        switch (shift) {
            case 1:
                result = "A";
            case 2:
                result = "B";
            case 3:
                result = "C";
        }
        return result;
    }

    private String getStartDate() {
        Date date = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
        String formatedDate = formater.format(date);
        docDate = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(date);
        return formatedDate;
    }

    /*
     * This Method gets the selected Controleur
     */
    public String getSelectedControleur() {
        controleurControl.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> observable,
                                        String oldValue, String newValue) {
                        selectedControleur = newValue;
                        control.setOperatorByName(selectedControleur);
                    }
                });
        return selectedControleur;
    }
}
