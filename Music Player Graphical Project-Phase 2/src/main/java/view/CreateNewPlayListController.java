package view;

import controller.ListenerController;
import exceptions.FreeAccountLimitException;
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


public class CreateNewPlayListController {

    @FXML
    private Label lblBTn_CancelCreatingPlayList;

    @FXML
    private Label lblBTn_CreatePlatList;

    @FXML
    private TextField txtField_PlayListName;

    @FXML
    void cancelCreatingPlayListLblBtn_Clicked(MouseEvent event)
    {
        Stage stage = (Stage) lblBTn_CancelCreatingPlayList.getScene().getWindow();
        stage.close();
    }

    @FXML
    void createPlayListLblBtn_Clicked(MouseEvent event)
    {
        try {
            ListenerController.getListenerController().newPlayList(txtField_PlayListName.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Playlist: "+txtField_PlayListName.getText()+" created successfully.");
            Main.setLoggedIn(true);
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Tick.png"));
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        MainTemplateController.centerPath.set("listenerPanel");
                        Parent root= FXMLLoader.load(MainTemplateController.class.getResource("/graphic/musicplayergraphicalprojectphase2/mainTemplate.fxml"));
                        Main.getStage().setScene(new Scene(root,745, 547));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    MainTemplateController.centerPath.set("listenerPanel");
                }
            });
        } catch (FreeAccountLimitException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Error.png"));
            alert.showAndWait();
        }
        Stage stage = (Stage) lblBTn_CreatePlatList.getScene().getWindow();
        stage.close();
    }
}