package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.audio.Audio;

import java.net.URL;
import java.util.ResourceBundle;

public class AudioPlayPageController implements Initializable
{
    private static Audio audio;

    @FXML
    private ImageView imageViewBtn_AddOrRemoveFromPlayList;

    @FXML
    private ImageView imageViewBtn_Like;

    @FXML
    private ImageView imageViewBtn_PlayOrPause;

    @FXML
    private Label lbl_AudioType;

    @FXML
    private Label lbl_LyricsOrBio;

    @FXML
    private Rectangle rectangle_AudioCover;

    @FXML
    private Text txt_ArtistUserName;

    @FXML
    private Text txt_AudioLength;

    @FXML
    private Text txt_AudioName;

    @FXML
    private Text txt_LyricsOrBio;

    @FXML
    private Text txt_ReleaseDate;

    @FXML
    void AddOrRemoveFromPlaylistBtn_Clicked(MouseEvent event) {

    }

    @FXML
    void LikeBtn_Clicked(MouseEvent event) {

    }

    @FXML
    void PlayOrPauseBtn_Clicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        rectangle_AudioCover.setFill(new ImagePattern(audio.getCover()));
        txt_ArtistUserName.setText(audio.getArtistName());
        txt_AudioLength.setText(audio.getAudioLength()/60 + ":"+audio.getAudioLength()%60);
        txt_AudioName.setText(audio.getAudioName());
    }

    public static Audio getAudio() {
        return audio;
    }

    public static void setAudio(Audio audio) {
        AudioPlayPageController.audio = audio;
    }
}
