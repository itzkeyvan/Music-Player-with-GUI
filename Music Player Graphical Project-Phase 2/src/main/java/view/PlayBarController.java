package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import java.io.ByteArrayInputStream;
import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;

public class PlayBarController implements Initializable
{

    @FXML
    private ImageView btn_next;

    @FXML
    private ImageView btn_playORpause;

    @FXML
    private ImageView btn_previous;

    @FXML
    private ImageView imgView_musicCover;

    @FXML
    private Label lbl_artistName;

    @FXML
    private Label lbl_audioName;

    @FXML
    private Label lbl_audioTotalTime;

    @FXML
    private Label lbl_elapsedTime;

    @FXML
    private Slider slider_audioProgressBar;
    @FXML
    void nextBtn_clicked(MouseEvent event)
    {


    }

    @FXML
    void playORpauseBtn_clicked(MouseEvent event)
    {
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
            btn_playORpause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Play.png"));
        } else {
            if (atEndOfMedia) {
                mediaPlayer.seek(Duration.ZERO);
                atEndOfMedia = false;
            }
            mediaPlayer.play();
            btn_playORpause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Pause.png"));
        }
    }
    @FXML
    void playORpause_keyPressed(KeyEvent event)
    {
        if(event.getCode()== KeyCode.SPACE)
        {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                mediaPlayer.pause();
                btn_playORpause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Play.png"));
            }
            else
            {
                if (atEndOfMedia)
                {
                    mediaPlayer.seek(Duration.ZERO);
                    atEndOfMedia = false;
                }
                mediaPlayer.play();
                btn_playORpause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Pause.png"));
            }
        }
    }

    @FXML
    void previousBtn_clicked(MouseEvent event) {

    }
    private String formatTime(Duration duration) {
        int intSeconds = (int) Math.floor(duration.toSeconds());
        int minutes = intSeconds / 60;
        int seconds = intSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private MediaPlayer mediaPlayer;
    private boolean atEndOfMedia = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Media media = new Media("file:C:\\Users\\LENOVO LOQ\\Downloads\\Telegram Desktop\\Catchybeatz, Isam - Ba Man Bia.mp3");
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnReady(() -> {
            Duration totalDuration = mediaPlayer.getTotalDuration();
            slider_audioProgressBar.setMax(totalDuration.toSeconds());
            lbl_audioTotalTime.setText(formatTime(totalDuration));
        });

        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            if (!slider_audioProgressBar.isValueChanging()) {
                slider_audioProgressBar.setValue(newValue.toSeconds());
            }
            lbl_elapsedTime.setText(formatTime(newValue));
        });

        slider_audioProgressBar.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (slider_audioProgressBar.isValueChanging()) {
                mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
            }
        });

        mediaPlayer.setOnEndOfMedia(() -> {
            atEndOfMedia = true;
            btn_playORpause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Play.png"));
        });
        //--------------------------  audio image:
        Image albumArtImage = extractAlbumArt("file:C:\\Users\\LENOVO LOQ\\Downloads\\Telegram Desktop\\Catchybeatz, Isam - Ba Man Bia.mp3");

        if (albumArtImage != null) {}
        else
        {
            imgView_musicCover.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/defaultAudioCover.png"));
        }
    }
    public static Image extractAlbumArt(String filePath) {
        try {
            Mp3File mp3File = new Mp3File(filePath);
            if (mp3File.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3File.getId3v2Tag();
                byte[] albumImageData = id3v2Tag.getAlbumImage();
                if (albumImageData != null) {
                    return new Image(new ByteArrayInputStream(albumImageData));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
