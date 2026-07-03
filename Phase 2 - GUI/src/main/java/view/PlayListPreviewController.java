package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Playlist;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayListPreviewController implements Initializable {
    private Playlist playlist;  // Make it non-static

    @FXML
    private AnchorPane anchorPane_playListPreview;

    @FXML
    private Text txt_PlayListName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // The playlist will be set via setPlaylist() after loading
    }

    // Called by parent controller after loading FXML
    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
        // Update UI after setting
        if (playlist != null) {
            txt_PlayListName.setText(playlist.getPlaylistName());
            // Make the whole anchor pane clickable
            anchorPane_playListPreview.setOnMouseClicked(this::playListPreviewClicked);
            anchorPane_playListPreview.setCursor(Cursor.HAND);
            // Also make the text clickable
            txt_PlayListName.setOnMouseClicked(this::playListPreviewClicked);
            txt_PlayListName.setCursor(Cursor.HAND);
        }
    }

    private void playListPreviewClicked(MouseEvent event) {
        if (playlist != null) {
            PlayListAudiosController.setPlaylist(playlist);
            MainTemplateController.centerPath.set("playListAudios");
        }
    }

    // Static method for backward compatibility (will be removed)
    public static void setPlaylistStatic(Playlist playlist) {
        // This is deprecated - use the instance method instead
    }
}