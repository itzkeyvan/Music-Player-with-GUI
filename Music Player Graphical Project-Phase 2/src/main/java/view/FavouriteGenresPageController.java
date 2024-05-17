package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class FavouriteGenresPageController implements Initializable
{

    @FXML
    private Label lblBtn_doneGenresSelection;

    @FXML
    private ToggleButton tglBtn_Country;

    @FXML
    private ToggleButton tglBtn_Hiphop;

    @FXML
    private ToggleButton tglBtn_History;

    @FXML
    private ToggleButton tglBtn_Interview;

    @FXML
    private ToggleButton tglBtn_Jazz;

    @FXML
    private ToggleButton tglBtn_Pop;

    @FXML
    private ToggleButton tglBtn_Rock;

    @FXML
    private ToggleButton tglBtn_Society;

    @FXML
    private ToggleButton tglBtn_TrueCrime;

    @FXML
    void doneGenresSelectionBtn_clicked(MouseEvent event) {

    }
    private int selectedCount = 0;
    private final int maxSelections = 4;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleButton[] toggleButtons = {
                tglBtn_Country, tglBtn_Hiphop, tglBtn_History,
                tglBtn_Interview, tglBtn_Jazz, tglBtn_Pop,
                tglBtn_Rock, tglBtn_Society, tglBtn_TrueCrime
        };

        for (ToggleButton toggleButton : toggleButtons) {
            toggleButton.setOnAction(event -> {
                if (toggleButton.isSelected()) {
                    if (selectedCount >= maxSelections) {
                        toggleButton.setSelected(false);
                    } else {
                        selectedCount++;
                    }
                } else {
                    selectedCount--;
                }
            });
        }

    }
}
