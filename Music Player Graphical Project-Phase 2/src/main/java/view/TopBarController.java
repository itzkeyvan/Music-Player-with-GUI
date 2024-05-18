package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.input.MouseEvent;

public class TopBarController {

    @FXML
    private HBox HBox_Search;

    @FXML
    private ImageView imgView_Back;

    @FXML
    private ImageView imgView_Search;

    @FXML
    private Label lblBtn_Login;

    @FXML
    private Label lblBtn_Logout;

    @FXML
    private Label lblBtn_SignUp;

    @FXML
    private TextField textField_Search;

    @FXML
    void BackButton_Clicked(MouseEvent event) {

    }

    @FXML
    void loginBtn_clicked(MouseEvent event) {

    }

    @FXML
    void logoutBtn_clicked(MouseEvent event) {

    }

    @FXML
    void signUpBtn_clicked(MouseEvent event) {

    }
    @FXML
    public void initialize() {
        imgView_Search.setOpacity(0.5);

        // Add listeners to TextField
        textField_Search.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                imgView_Search.setOpacity(1.0);
            } else {
                if (textField_Search.getText().isEmpty()) {
                    imgView_Search.setOpacity(0.5);
                }
            }
        });

        textField_Search.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty()) {
                if (!textField_Search.isFocused()) {
                    imgView_Search.setOpacity(0.5);
                }
            } else {
                imgView_Search.setOpacity(1.0);
            }
        });

        HBox_Search.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            if (!textField_Search.isFocused()) {
                imgView_Search.setOpacity(1.0);
            }
        });
        HBox_Search.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            if (!textField_Search.isFocused() && textField_Search.getText().isEmpty()) {
                imgView_Search.setOpacity(0.5);
            }
        });

        textField_Search.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                imgView_Search.setOpacity(1.0);
            } else {
                if (!HBox_Search.isHover() && textField_Search.getText().isEmpty()) {
                    imgView_Search.setOpacity(0.5);
                }
            }
        });
    }
}
