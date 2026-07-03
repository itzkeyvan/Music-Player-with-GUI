package view;

import controller.ListenerController;
import exceptions.InvalidFormatException;
import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class SignUpPageController {

    @FXML
    private DatePicker datePicker_birthDate;


    @FXML
    private HBox hBox_bio;

    @FXML
    private Label lblBtn_SingUp;

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
        if(txtField_userName.getText().isEmpty()||txtField_password.getText().isEmpty()||txtField_firstAndLastName.getText().isEmpty()|| txtField_email.getText().isEmpty()|| txtField_phoneNumber.getText().isEmpty()|| datePicker_birthDate.getValue()==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("You should fill all the fields.");
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Error.png"));
            alert.showAndWait();
            return;
        }
        if (role.getSelectedToggle() == radioBtn_listener) {
            try
            {
                String result = ListenerController.getListenerController().signUp(txtField_userName.getText(), txtField_password.getText(), txtField_firstAndLastName.getText(), txtField_email.getText(), txtField_phoneNumber.getText(), Date.from(datePicker_birthDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                if (result.equals("Signup successful. You have been rewarded $50 of credit!")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    Main.setCenterNodesHistory(new ArrayList<>());
                    alert.setContentText("Signup successful. You have been rewarded $50 of credit!");
                    Main.setLoggedIn(true);
                    Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                    alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Tick.png"));
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            try {
                                Stage stage = Main.getStage();
                                double width = stage.getWidth();
                                double height = stage.getHeight();

                                Parent root = FXMLLoader.load(MainTemplateController.class.getResource("/graphic/musicplayergraphicalprojectphase2/mainTemplate.fxml"));

                                Scene scene = stage.getScene();
                                if (scene != null) {
                                    scene.setRoot(root);
                                } else {
                                    stage.setScene(new Scene(root, width, height));
                                }

                                MainTemplateController.centerPath.set("favouriteGenresPage");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }
                else if(result.equals("This username is already taken."))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("This username is already taken.");
                    Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                    alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Error.png"));
                    alert.showAndWait();
                }
                else if(result.equals("This email is already taken."))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("This email is already taken.");
                    Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                    alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Error.png"));
                    alert.showAndWait();
                }
            }
            catch (InvalidFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Error.png"));
                alert.showAndWait();
            }
        } else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Only listeners can sign up currently!");
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Error.png"));
            alert.showAndWait();
        }
    }
}
