package controller;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.CheckListComposants;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ComposantController implements Initializable {
    private ArrayList<ComboBox<String>> ArrayListCombo;
    private String[] selectedItem; // = new String[22];
    @FXML
    private ComboBox<String> comp1;
    ObservableList<String> compo1 = FXCollections.observableArrayList("0", "OK", "M", "R");

    @FXML
    private ComboBox<String> comp2;
    ObservableList<String> compo2 = FXCollections.observableArrayList("0", "OK", "M", "R", "S", "F", "2S", "2F");
    @FXML
    private ComboBox<String> comp3;
    ObservableList<String> compo3 = FXCollections.observableArrayList("0", "OK", "M", "R", "S", "F", "2S", "2F");
    @FXML
    private ComboBox<String> comp4;
    ObservableList<String> compo4 = FXCollections.observableArrayList("0", "OK", "M", "R", "S", "F", "2S", "2F");
    @FXML
    private ComboBox<String> comp5;
    ObservableList<String> compo5 = FXCollections.observableArrayList("0", "OK", "M", "R", "S", "F", "2S", "2F");
    @FXML
    private ComboBox<String> comp6;
    ObservableList<String> compo6 = FXCollections.observableArrayList("0", "OK", "M", "R", "S", "F", "2S", "2F");
    @FXML
    private ComboBox<String> comp7;
    ObservableList<String> compo7 = FXCollections.observableArrayList("0", "OK", "M", "R", "S", "F", "2S", "2F");
    @FXML
    private ComboBox<String> comp8;
    ObservableList<String> compo8 = FXCollections.observableArrayList("0", "OK", "M", "R", "S", "F", "2S", "2F");
    @FXML
    private ComboBox<String> comp9;
    ObservableList<String> compo9 = FXCollections.observableArrayList("0", "OK", "M", "R", "S", "F", "2S", "2F");
    @FXML
    private ComboBox<String> comp10;
    ObservableList<String> compo10 = FXCollections.observableArrayList("0", "OK", "M", "R", "S", "F", "2S", "2F");
    @FXML
    private ComboBox<String> comp11;
    ObservableList<String> compo11 = FXCollections.observableArrayList("0", "OK", "M", "R", "S", "F", "2S", "2F");
    @FXML
    private ComboBox<String> comp12;
    ObservableList<String> compo12 = FXCollections.observableArrayList("0", "OK", "M", "R", "S", "F", "2S", "2F");
    @FXML
    private ComboBox<String> comp13;
    ObservableList<String> compo13 = FXCollections.observableArrayList("0", "OK", "M", "R", "S", "F", "2S", "2F");
    @FXML
    private ComboBox<String> comp14;
    ObservableList<String> compo14 = FXCollections.observableArrayList("0", "OK", "M", "R", "S", "F", "2S", "2F");
    @FXML
    private ComboBox<String> comp15;
    ObservableList<String> compo15 = FXCollections.observableArrayList("0", "OK", "M", "R", "S", "F", "2S", "2F");
    @FXML
    private ComboBox<String> comp16;
    ObservableList<String> compo16 = FXCollections.observableArrayList("0", "OK", "M", "R", "S", "F", "2S", "2F");
    @FXML
    private ComboBox<String> comp17;
    ObservableList<String> compo17 = FXCollections.observableArrayList("0", "OK", "M", "R", "S", "F", "2S", "2F");
    @FXML
    private ComboBox<String> comp18;
    ObservableList<String> compo18 = FXCollections.observableArrayList("0", "OK", "M", "R", "S", "F", "2S", "2F");
    @FXML
    private ComboBox<String> comp19;
    ObservableList<String> compo19 = FXCollections.observableArrayList("0", "OK", "M", "R", "S", "F", "2S", "2F");
    @FXML
    private ComboBox<String> comp20;
    ObservableList<String> compo20 = FXCollections.observableArrayList("0", "OK", "M", "R", "S", "F", "2S", "2F");
    @FXML
    private ComboBox<String> comp21;
    ObservableList<String> compo21 = FXCollections.observableArrayList("0", "OK", "M", "R", "S", "F", "2S", "2F");
    @FXML
    private ComboBox<String> comp22;
    ObservableList<String> compo22 = FXCollections.observableArrayList("0", "OK", "M", "R", "S", "F", "2S", "2F");

    @FXML
    private Button compValEnr;

    @FXML
    private TextField showMessage;

    @FXML
    private Button composantRetoucher;

    private ControlProcessor controlProcessor;

    private CheckListComposants control;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comp1.setItems(compo1);
        comp2.setItems(compo2);
        comp3.setItems(compo3);
        comp4.setItems(compo4);
        comp5.setItems(compo5);
        comp6.setItems(compo6);
        comp7.setItems(compo7);
        comp8.setItems(compo8);
        comp9.setItems(compo9);
        comp10.setItems(compo10);
        comp11.setItems(compo11);
        comp12.setItems(compo12);
        comp13.setItems(compo13);
        comp14.setItems(compo14);
        comp15.setItems(compo15);
        comp16.setItems(compo16);
        comp17.setItems(compo17);
        comp18.setItems(compo18);
        comp19.setItems(compo19);
        comp20.setItems(compo20);
        comp21.setItems(compo21);
        comp22.setItems(compo22);

        for (ComboBox<String> com : getArrayListCombo()) {
            generalComp(com);
            System.out.println(com.getValue() + selectedItem);

        }


        getSelectedValue();

        controlProcessor = ControlProcessor.getInstance();
        control = (CheckListComposants) controlProcessor.getControl();
        selectedItem = control.getComposants();
        for (int i = 0; i < 22; i++) {
//            generalComp(com);
            if (null != selectedItem[i]) {
                System.out.println(i + " test. " + selectedItem[i]);
                System.out.println(i + " getArray. " + getArrayListCombo().get(i).getValue());
                getArrayListCombo().get(i).setPromptText(selectedItem[i]);
//                selectColorToRework(selectedItem[i]);
                System.out.println("sele: " + selectColorToRework(selectedItem[i]).toString());
                setColorToColoredBox(getArrayListCombo().get(i), selectedItem[i]);
//                setColor(com);
            }
        }
    }

    public ArrayList<ComboBox<String>> getArrayListCombo() {
        ArrayListCombo = new ArrayList<>();

        ArrayListCombo.add(comp1);
        ArrayListCombo.add(comp2);
        ArrayListCombo.add(comp3);
        ArrayListCombo.add(comp4);
        ArrayListCombo.add(comp5);
        ArrayListCombo.add(comp6);
        ArrayListCombo.add(comp7);
        ArrayListCombo.add(comp8);
        ArrayListCombo.add(comp9);
        ArrayListCombo.add(comp10);
        ArrayListCombo.add(comp11);
        ArrayListCombo.add(comp12);
        ArrayListCombo.add(comp13);
        ArrayListCombo.add(comp14);
        ArrayListCombo.add(comp15);
        ArrayListCombo.add(comp16);
        ArrayListCombo.add(comp17);
        ArrayListCombo.add(comp18);
        ArrayListCombo.add(comp19);
        ArrayListCombo.add(comp20);
        ArrayListCombo.add(comp21);
        ArrayListCombo.add(comp22);

        return this.ArrayListCombo;
    }

    // This Methode shorts call of Methods (select the right color and colors the Backgound of the selected status)
    public void generalComp(ComboBox<String> comb) {
        selectColor(comb);
        setColor(comb);
    }

    public void setColorToColoredBox(ComboBox<String> comp, String status) {
        switch (status) {
            case "OK":
                comp.setStyle("-fx-background-color: GREEN");
                break;
            case "M":
                comp.setStyle("-fx-background-color: RED");
                break;
            case "R":
                comp.setStyle("-fx-background-color: PINK");
                break;
            case "S":
            case "2S":
                comp.setStyle("-fx-background-color: ORANGE");
                break;
            case "F":
            case "2F":
                comp.setStyle("-fx-background-color: YELLOW");
                break;
            default:
                break;
        }

    }

    // This Method sets Color and colors each status with deferent color.
    public void setColor(ComboBox<String> comp) {

        comp.buttonCellProperty().bind(Bindings.createObjectBinding(() -> {
            final Color finalColor = selectColor(comp);

            // Get the arrow button of the combo-box
            final StackPane arrowButton = (StackPane) comp.lookup(".arrow-button");

            return new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setBackground(Background.EMPTY);
                        setText("");
                    } else {
                        setBackground(new Background(new BackgroundFill(finalColor, CornerRadii.EMPTY, Insets.EMPTY)));
                        setText(item);
                    }

                    // Set the background of the arrow also
                    if (arrowButton != null)
                        arrowButton.setBackground(getBackground());
                }
            };
        }, comp.valueProperty()));
    }

    // This Method select the intended and suitable Colors
    public Color selectColor(ComboBox<String> comb) {
        int indexOf = comb.getItems().indexOf(comb.getValue());

        Color color = Color.TRANSPARENT;

        switch (indexOf) {
            case 1:
                color = Color.GREEN;
                break;
            case 2:
                color = Color.RED;
                break;
            case 3:
                color = Color.PINK;
                break;
            case 4:
                color = Color.ORANGE;
                break;
            case 5:
                color = Color.YELLOW;
                break;
            case 6:
                color = Color.ORANGE;
                break;
            case 7:
                color = Color.YELLOW;
                break;
            default:
                break;
        }
        return color;
    }

    // This Method select the intended and suitable Colors
    public Color selectColorToRework(String status) {

        Color color = Color.TRANSPARENT;

        switch (status) {
            case "OK":
                color = Color.GREEN;
                break;
            case "M":
                color = Color.RED;
                break;
            case "R":
                color = Color.PINK;
                break;
            case "S":
                color = Color.ORANGE;
                break;
            case "F":
                color = Color.YELLOW;
                break;
            case "2S":
                color = Color.ORANGE;
                break;
            case "2F":
                color = Color.YELLOW;
                break;
            default:
                break;
        }
        return color;
    }

    /*
     ** This Method gets the selected Status from User and saved in an ArrayList.
     */
    public String[] getSelectedValue() {

        // get the Selected Item, which the User put it.
        for (int i = 0; i < 22; i++) {
            int finalI = i;
            getArrayListCombo().get(i).getSelectionModel().selectedItemProperty()
                    .addListener(new ChangeListener<String>() {
                        public void changed(ObservableValue<? extends String> observable,
                                            String oldValue, String newValue) {
                            control.setComposants(finalI, newValue);
//                            System.out.println(finalI + " Value is: " + newValue);
                            selectedItem[finalI] = newValue;
                        }
                    });
        }
        return selectedItem;
    }

    /*
     ** This Method does the Rework of control, which is done by a Operator.
     ** when Pieces not ok (not plausible) has to be corrected
     */
    @FXML
    public void actionRework(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
        if (event.getSource() == composantRetoucher) {
            if (isAllSelected()) {
                control.getStauts().setRetouched();
                controlProcessor.saveControl();
                controlProcessor.dropControl();
                stage = (Stage) composantRetoucher.getScene().getWindow();
                stage.setTitle("Composant");
                URL url = new File("src/com/resources/fxml/controle.fxml").toURI().toURL();
                root = FXMLLoader.load(url);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                showMessage.setText("Ne pas tout est selectionne");
            }
        }
    }

    /*
     ** This Method saves and validate the Status of the checked Composants
     ** when the user try to validate the executed Control, checks whether all points are checked and whether all points are ok.
     ** if it is not, than will be send to rework.
     */
    @FXML
    public void ValidSave(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
        if (event.getSource() == compValEnr) {
            if (isAllSelected()) {
                if (controlProcessor.validate()) {
                    showMessage.setText("oui tout est selectionne");
                    controlProcessor.saveControl();
                    controlProcessor.dropControl();
                    stage = (Stage) compValEnr.getScene().getWindow();
                    stage.setTitle("Composant");
                    URL url = new File("src/com/resources/fxml/controle.fxml").toURI().toURL();
                    root = FXMLLoader.load(url);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    compValEnr.setDisable(true);
                    showMessage.setText("Tout dois etre OK Envoyez au retouche svp!");
                }
            } else {
                showMessage.setText("Ne pas tout est selectionne");
            }
        }
    }

    // check whether all of Values are selected
    public Boolean isAllSelected() {
        for (String arr : getSelectedValue()) {
            if (null == arr || arr == "0") {
                return false;
            }
        }
        return true;
    }

}
