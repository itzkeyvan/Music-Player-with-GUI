package view;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class SignUpPageController {

    @FXML
    private DatePicker datePicker_birthDate;

    @FXML
    private RadioButton radioBtn_listener;

    @FXML
    private RadioButton radioBtn_podcaster;

    @FXML
    private RadioButton radioBtn_singer;

    @FXML
    private ToggleGroup role;

    @FXML
    private TextArea txtArea_Bio;

    @FXML
    private TextField txtField_email;

    @FXML
    private TextField txtField_firstAndLastName;

    @FXML
    private TextField txtField_password;

    @FXML
    private TextField txtField_phoneNumber;

    @FXML
    private TextField txtField_userName;

}
