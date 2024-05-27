package view;

import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Playlist;
import model.audio.Audio;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayListAudiosController implements Initializable
{
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
    void deletePlaylistBtn_Clicked(MouseEvent event)
    {
        Main.getListener().getPlaylistsList().remove(playlist);
        Main.getStage().setScene(new Scene(Main.getCenterNodesHistory().get(Main.getCenterNodesHistory().size()-2)));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        txt_CreatorUserName.setText(playlist.getPlaylistCreatorName());
        txt_NumberOfAudios.setText(String.valueOf(playlist.getAudiosList().size()));
        txt_PlayListName.setText(playlist.getPlaylistName());
        double playListLength=0;
        for(Audio audio:playlist.getAudiosList())
        {
            playListLength+=(audio.getAudioLength());
        }
        txt_PlayListTotalLength.setText(String.valueOf(playListLength));
        rectangle_PlayListCover.setFill(new ImagePattern(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/SideBar/Playlist.png")));
    }

    public static Playlist getPlaylist() {
        return playlist;
    }

    public static void setPlaylist(Playlist playlist) {
        PlayListAudiosController.playlist = playlist;
    }
}
