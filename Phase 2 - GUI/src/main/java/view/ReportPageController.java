package view;

import controller.ListenerController;
import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.userAccount.Artist;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportPageController implements Initializable {
    @FXML
    private Label lblBtn_Report;

    @FXML
    private Label lblBtn_cancel;

    @FXML
    private TextArea txtArea_Description;

    @FXML
    private Text txt_reportedArtistName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Artist artist = ArtistInfoPageController.getArtist();
        if (artist != null) {
            txt_reportedArtistName.setText(artist.getFirstAndLastName());
        }
    }

    @FXML
    void ReportBtnInReportPage_clicked(MouseEvent event) {
        String description = txtArea_Description.getText().trim();

        // Validate description
        if (description.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a description for the report.");
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Error.png"));
            alert.showAndWait();
            return;
        }

        // Get the artist being reported
        Artist artist = ArtistInfoPageController.getArtist();
        if (artist == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No artist selected to report.");
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Error.png"));
            alert.showAndWait();
            goBackToArtistPage();
            return;
        }

        // Submit the report
        String result = ListenerController.getListenerController().reportArtist(
                artist.getUserName(),
                description
        );

        // Show success/error message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Report Status");
        alert.setHeaderText(null);
        alert.setContentText(result);
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Tick.png"));
        alert.showAndWait();

        // Go back to artist info page after reporting
        goBackToArtistPage();
    }

    @FXML
    void cancelBtn_clicked(MouseEvent event) {
        goBackToArtistPage();
    }

    private void goBackToArtistPage() {
        // Navigate back to the artist info page
        MainTemplateController.centerPath.set("artistInfoPage");
    }
}