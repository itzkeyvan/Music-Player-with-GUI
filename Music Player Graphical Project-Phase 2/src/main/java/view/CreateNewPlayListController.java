package view;

import controller.ListenerController;
import exceptions.FreeAccountLimitException;
import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


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
            alert.showAndWait();
        } catch (FreeAccountLimitException e) {
            System.out.println(e.getMessage());
        }
        Stage stage = (Stage) lblBTn_CreatePlatList.getScene().getWindow();
        stage.close();
    }
}