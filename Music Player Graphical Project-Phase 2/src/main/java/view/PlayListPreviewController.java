package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Playlist;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayListPreviewController implements Initializable
{
    static private Playlist playlist;
    @FXML
    private AnchorPane anchorPane_playListPreview;

    @FXML
    private Text txt_PlayListName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        txt_PlayListName.setText(playlist.getPlaylistName());
    }

    public static Playlist getPlaylist() {
        return playlist;
    }

    public static void setPlaylist(Playlist playlist) {
        PlayListPreviewController.playlist = playlist;
    }
}
