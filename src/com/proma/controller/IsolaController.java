package controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class IsolaController implements Initializable {
    private ArrayList<ComboBox<String>> ArrayListCombo;
    @FXML
    private ComboBox<String> gi1;
    ObservableList<String> gi1controlleur = FXCollections.observableArrayList("0", "OK", "NOK");
    @FXML
    private ComboBox<String> gi2;
    ObservableList<String> gi12controlleur = FXCollections.observableArrayList("0", "OK", "NOK");
    @FXML
    private ComboBox<String> gi3;
    ObservableList<String> gi3controlleur = FXCollections.observableArrayList("0", "OK", "NOK");
    @FXML
    private ComboBox<String> gi4;
    ObservableList<String> gi4controlleur = FXCollections.observableArrayList("0", "OK", "NOK");

    @FXML
    public void changeColor(MouseEvent event) {
        System.out.println("clicked on ");
//        listView.set

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gi1.setItems(gi1controlleur);
        gi2.setItems(gi12controlleur);
        gi3.setItems(gi3controlleur);
        gi4.setItems(gi4controlleur);
        for (ComboBox<String> com : getArrayListCombo()) {
            generalComp(com);
        }
    }

    public ArrayList<ComboBox<String>> getArrayListCombo() {

        ArrayListCombo = new ArrayList<>();

        ArrayListCombo.add(gi1);
        ArrayListCombo.add(gi2);
        ArrayListCombo.add(gi3);
        ArrayListCombo.add(gi4);
        return this.ArrayListCombo;
    }

    public void generalComp(ComboBox<String> comb) {
        selectColor(comb);
        setColor(comb);
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
}
