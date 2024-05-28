package view;

import controller.ListenerController;
import exceptions.InvalidFormatException;
import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;

public class SignUpPageController {

    @FXML
    private DatePicker datePicker_birthDate;


    @FXML
    private HBox hBox_bio;

    @FXML
    private Label lblBtn_SingUP;

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

    @FXML
    void initialize() {
        role.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == radioBtn_singer || newValue == radioBtn_podcaster) {
                hBox_bio.setVisible(true);
            } else {
                hBox_bio.setVisible(false);
            }
        });
    }

    @FXML
    void signUpBtn_clicked(MouseEvent event) {
        if (role.getSelectedToggle() == radioBtn_listener) {
            try
            {
                String result = ListenerController.getListenerController().signUp(txtField_userName.getText(), txtField_password.getText(), txtField_firstAndLastName.getText(), txtField_email.getText(), txtField_phoneNumber.getText(), Date.from(datePicker_birthDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                if (result.equals("Signup successful. You have been rewarded $50 of credit!")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Signup successful. You have been rewarded $50 of credit!");
                    Main.setLoggedIn(true);
                    Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                    alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Tick.png"));
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            try {
                                Main.setCurrentCenterNode(FXMLLoader.load(Main.class.getResource("homePage.fxml")));
                                Scene scene = new Scene(FXMLLoader.load(Main.class.getResource("mainTemplate.fxml")));
                                Main.getStage().setScene(scene);
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    });
                }
                else if(result.equals("This username is already taken."))
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("This username is already taken.");
                    Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                    alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Error.png"));
                    alert.showAndWait();
                }
                else if(result.equals("This email is already taken."))
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("This email is already taken.");
                    Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                    alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Error.png"));
                    alert.showAndWait();
                }
            }
            catch (InvalidFormatException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Error.png"));
                alert.showAndWait();
            }
        } else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Only listeners can sign up currently!");
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Error.png"));
            alert.showAndWait();
        }
    }
}
