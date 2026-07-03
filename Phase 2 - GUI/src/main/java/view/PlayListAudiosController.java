package view;

import controller.ListenerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Playlist;
import model.audio.Audio;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayListAudiosController implements Initializable {
    private static Playlist playlist;

    @FXML
    private Label Btn_DeletePlaylist;

    @FXML
    private VBox VBox_audiosList;

    @FXML
    private Rectangle rectangle_PlayListCover;

    @FXML
    private Text txt_CreatorUserName;

    @FXML
    private Text txt_NumberOfAudios;

    @FXML
    private Text txt_PlayListName;

    @FXML
    private Text txt_PlayListTotalLength;

    @FXML
    void deletePlaylistBtn_Clicked(MouseEvent event) {
        if (playlist != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Playlist");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("Do you want to delete the playlist: " + playlist.getPlaylistName() + "?");
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Error.png"));

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Remove the playlist
                    ListenerController.getListenerController().getListener().getPlaylistsList().remove(playlist);

                    // Force reload listenerPanel
                    MainTemplateController.centerPath.set("_refresh_");
                    MainTemplateController.centerPath.set("listenerPanel");
                }
            });
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (playlist == null) return;

        // Set playlist info
        txt_CreatorUserName.setText(playlist.getPlaylistCreatorName());
        txt_NumberOfAudios.setText(String.valueOf(playlist.getAudiosList().size()));
        txt_PlayListName.setText(playlist.getPlaylistName());

        long totalLength = 0;
        for (Audio audio : playlist.getAudiosList()) {
            totalLength += audio.getAudioLength();
        }
        long minutes = totalLength / 60;
        long seconds = totalLength % 60;
        txt_PlayListTotalLength.setText(String.format("%02d:%02d", minutes, seconds));

        rectangle_PlayListCover.setFill(new ImagePattern(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/SideBar/Playlist.png")));

        // Populate the audio list
        VBox_audiosList.getChildren().clear();
        int audioNumber = 1;
        // Set the playlist for all audio items in this list (so next/previous works)
        AudioInListController.setAudiosList(playlist.getAudiosList());

        for (Audio audio : playlist.getAudiosList()) {
            AudioInListController.setAudio(audio);
            AudioInListController.setAudioNumber(audioNumber++);
            try {
                HBox audioInList = FXMLLoader.load(AudioInListController.class.getResource("/graphic/musicplayergraphicalprojectphase2/audioInList.fxml"));
                VBox_audiosList.getChildren().add(audioInList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Playlist getPlaylist() {
        return playlist;
    }

    public static void setPlaylist(Playlist playlist) {
        PlayListAudiosController.playlist = playlist;
    }
}