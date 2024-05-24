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
import java.net.URL;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.audio.Audio;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayBarController implements Initializable {
    private static Audio audio;
    private static ArrayList<Audio> audiosList;

    @FXML
    private ImageView btn_next;

    @FXML
    private ImageView btn_playORpause;

    @FXML
    private ImageView btn_previous;

    @FXML
    private Rectangle rectangle_audioCover;

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
    void nextBtn_clicked(MouseEvent event) {
        playNextAudio();
    }

    @FXML
    void previousBtn_clicked(MouseEvent event) {
        playPreviousAudio();
    }

    @FXML
    void playORpauseBtn_clicked(MouseEvent event) {
        togglePlayPause();
    }

    @FXML
    void playORpause_keyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            togglePlayPause();
        }
    }

    private MediaPlayer mediaPlayer;
    private boolean atEndOfMedia = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        btn_playORpause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Pause.png"));
        setupMediaPlayer();
        setupSlider();
        setupAudioCover();
    }

    private void setupMediaPlayer() {
        Media media = new Media(getAudio().getAudioURL());
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

        mediaPlayer.setOnEndOfMedia(() -> {
            atEndOfMedia = true;
            playNextAudio();
        });
    }

    private void setupSlider() {
        slider_audioProgressBar.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (slider_audioProgressBar.isValueChanging()) {
                mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
            }
        });
    }

    private void setupAudioCover() {
        if (audio.getCover() != null) {
            rectangle_audioCover.setFill(new ImagePattern(getAudio().getCover()));
        } else {
            rectangle_audioCover.setFill(new ImagePattern(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/defaultAudioCover.png")));
        }
    }

    private void playNextAudio() {
        int currentIndex = audiosList.indexOf(getAudio());
        if (currentIndex == audiosList.size() - 1) { // at the end of the list
            setAudio(audiosList.get(0)); // play the first audio in the list
        } else {
            setAudio(audiosList.get(currentIndex + 1)); // play the next audio
        }
        reloadAudio();
    }

    private void playPreviousAudio() {
        int currentIndex = audiosList.indexOf(getAudio());
        if (currentIndex == 0) { // at the start of the list
            setAudio(audiosList.get(audiosList.size() - 1)); // play the last audio in the list
        } else {
            setAudio(audiosList.get(currentIndex - 1)); // play the previous audio
        }
        reloadAudio();
    }

    private void togglePlayPause() {
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

    private void reloadAudio() {
        mediaPlayer.stop();
        setupMediaPlayer();
        mediaPlayer.play();
        lbl_audioName.setText(getAudio().getAudioName());
        lbl_artistName.setText(getAudio().getArtistName());
        setupAudioCover();
        btn_playORpause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Pause.png"));
    }

    private String formatTime(Duration duration) {
        int intSeconds = (int) Math.floor(duration.toSeconds());
        int minutes = intSeconds / 60;
        int seconds = intSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public static Audio getAudio() {
        return audio;
    }

    public static void setAudio(Audio audio) {
        PlayBarController.audio = audio;
    }

    public static ArrayList<Audio> getAudiosList() {
        return audiosList;
    }

    public static void setAudiosList(ArrayList<Audio> audiosList) {
        PlayBarController.audiosList = audiosList;
    }
}