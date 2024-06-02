package view;

import controller.UserController;
import exceptions.failedLoginExceptions.UserNotFoundException;
import exceptions.failedLoginExceptions.WrongPasswordException;
import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginPageController {

    @FXML
    private Label lblBtn_Login;

    @FXML
    private TextField txtField_password;

    @FXML
    private TextField txtField_userName;

    @FXML
    void loginBtn_clicked(MouseEvent event)
    {
        if(txtField_userName.getText().isEmpty()||txtField_password.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("You should fill all the fields.");
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Error.png"));
            alert.showAndWait();
            return;
        }
        try {
            String result=UserController.getUserController().logIn(txtField_userName.getText(),txtField_password.getText());
            if(result.equals("Logged in as a listener."))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                Main.setCenterNodesHistory(new ArrayList<>());
                alert.setContentText("Logged in as a listener.");
                Main.setLoggedIn(true);
                Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Tick.png"));
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {
                            Parent root=FXMLLoader.load(MainTemplateController.class.getResource("/graphic/musicplayergraphicalprojectphase2/mainTemplate.fxml"));
                            Main.getStage().setScene(new Scene(root,745, 547));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        MainTemplateController.centerPath.set("homePage");
                    }
                });
            }
        }
        catch (WrongPasswordException|UserNotFoundException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Error.png"));
            alert.showAndWait();
        }
    }

}
