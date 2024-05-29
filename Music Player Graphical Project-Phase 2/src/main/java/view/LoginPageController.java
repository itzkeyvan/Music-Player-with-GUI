package view;

import controller.UserController;
import exceptions.failedLoginExceptions.UserNotFoundException;
import exceptions.failedLoginExceptions.WrongPasswordException;
import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

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
        try {
            String result=UserController.getUserController().logIn(txtField_userName.getText(),txtField_password.getText());
            if(result.equals("Logged in as a listener."))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Logged in as a listener.");
                Main.setLoggedIn(true);
                Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Tick.png"));
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {
                            Main.setCurrentCenterNode(FXMLLoader.load(FavouriteGenresPageController.class.getResource("homePage.fxml")));
                            MainTemplateController.getBorderPane_mainTemplate().setCenter(Main.getCurrentCenterNode());
                            Scene scene = new Scene(FXMLLoader.load(MainTemplateController.class.getResource("mainTemplate.fxml")));
                            Main.getStage().setScene(scene);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
            }
        }
        catch (WrongPasswordException|UserNotFoundException e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Error.png"));
            alert.showAndWait();
        }
    }

}
