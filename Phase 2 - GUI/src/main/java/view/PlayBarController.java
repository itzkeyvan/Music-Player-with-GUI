package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.io.File;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.audio.Audio;
import model.userAccount.Artist;
import controller.AdminController;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayBarController implements Initializable {

    // Static variables for shared state
    private static Audio currentAudio;
    private static ArrayList<Audio> currentPlaylist;
    private static MediaPlayer mediaPlayer;
    private static boolean isPlaying = false;
    private static boolean atEndOfMedia = false;
    private static double currentVolume = 1.0;
    private static boolean isMuted = false;
    private static double volumeBeforeMute = 1.0;

    // Static reference to the controller instance
    private static PlayBarController instance;

    // Callback for audio change
    private static Runnable onAudioChangeCallback;

    // FXML fields
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
    private Slider slider_volume;

    @FXML
    private ImageView imgView_VolumeIcon;

    // Flag to prevent recursive updates
    private boolean isVolumeSliderChanging = false;
    private boolean isProgressSliderChanging = false;

    // Volume icon paths
    private static final String VOLUME_PLAYING = "file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/volume-playing.png";
    private static final String VOLUME_OFF = "file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/volume-off.png";

    private String getValidMediaUrl(String filePath) {
        try {
            URL resourceUrl = getClass().getClassLoader().getResource(filePath);
            if (resourceUrl != null) {
                try {
                    return resourceUrl.toURI().toString();
                } catch (URISyntaxException e) {
                }
            }
            File file = new File(filePath);
            if (!file.isAbsolute()) {
                file = new File(System.getProperty("user.dir"), filePath);
            }
            URI uri = file.toURI();
            return uri.toString();
        } catch (Exception e) {
            return filePath.replace(" ", "%20");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;

        setupVolumeSlider();
        setupVolumeIcon();
        setupProgressSlider();

        if (currentAudio != null) {
            setupMediaPlayer();
            updateUI();
        } else {
            setDefaultUI();
        }

        Platform.runLater(() -> {
            updateSliderFill(slider_volume);
            updateSliderFill(slider_audioProgressBar);
        });
    }

    private void setupVolumeSlider() {
        if (slider_volume != null) {
            slider_volume.setValue(currentVolume);
            updateSliderFill(slider_volume);
            slider_volume.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (isVolumeSliderChanging) return;
                double volume = newValue.doubleValue();
                isVolumeSliderChanging = true;
                currentVolume = volume;
                if (volume == 0) {
                    isMuted = true;
                } else {
                    isMuted = false;
                    volumeBeforeMute = volume;
                }
                if (mediaPlayer != null) {
                    mediaPlayer.setVolume(volume);
                }
                updateVolumeIcon();
                updateSliderFill(slider_volume);
                isVolumeSliderChanging = false;
            });
        }
    }

    private void updateSliderFill(Slider slider) {
        if (slider == null) return;
        slider.applyCss();
        Node track = slider.lookup(".track");
        if (track == null) return;
        double min = slider.getMin();
        double max = slider.getMax();
        double value = slider.getValue();
        double percentage = max > min ? Math.max(0, Math.min(100, ((value - min) / (max - min)) * 100)) : 0;
        track.setStyle(String.format(
                "-fx-background-color: linear-gradient(to right, #1DB954 0%%, #1DB954 %1$.2f%%, #535353 %1$.2f%%, #535353 100%%);",
                percentage));
    }

    private void setupVolumeIcon() {
        if (imgView_VolumeIcon != null) {
            imgView_VolumeIcon.setOnMouseEntered(event -> {imgView_VolumeIcon.setCursor(Cursor.HAND);});
            imgView_VolumeIcon.setOnMouseClicked(event -> toggleMute());
        }
    }

    @FXML
    void volumeIconClicked(MouseEvent event) {
        toggleMute();
    }

    private void toggleMute() {
        if (isMuted) {
            isMuted = false;
            double volumeToSet = volumeBeforeMute > 0 ? volumeBeforeMute : 0.5;
            isVolumeSliderChanging = true;
            slider_volume.setValue(volumeToSet);
            currentVolume = volumeToSet;
            if (mediaPlayer != null) {
                mediaPlayer.setVolume(volumeToSet);
            }
            updateVolumeIcon();
            updateSliderFill(slider_volume);
            isVolumeSliderChanging = false;
        } else {
            volumeBeforeMute = currentVolume;
            isMuted = true;
            isVolumeSliderChanging = true;
            slider_volume.setValue(0);
            currentVolume = 0;
            if (mediaPlayer != null) {
                mediaPlayer.setVolume(0);
            }
            updateVolumeIcon();
            updateSliderFill(slider_volume);
            isVolumeSliderChanging = false;
        }
    }

    private void updateVolumeIcon() {
        if (imgView_VolumeIcon == null) return;
        String iconPath = (isMuted || currentVolume == 0) ? VOLUME_OFF : VOLUME_PLAYING;
        try {
            imgView_VolumeIcon.setImage(new Image(iconPath));
        } catch (Exception e) {
        }
    }

    private void setupProgressSlider() {
        if (slider_audioProgressBar == null) return;

        slider_audioProgressBar.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateSliderFill(slider_audioProgressBar);
            if (slider_audioProgressBar.isValueChanging() && mediaPlayer != null) {
                isProgressSliderChanging = true;
                mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
                lbl_elapsedTime.setText(formatTime(Duration.seconds(newValue.doubleValue())));
                isProgressSliderChanging = false;
            }
        });

        slider_audioProgressBar.setOnMousePressed(event -> {
            if (mediaPlayer == null) return;
            double width = slider_audioProgressBar.getWidth();
            if (width <= 0) return;
            double percent = Math.max(0, Math.min(1, event.getX() / width));
            double min = slider_audioProgressBar.getMin();
            double max = slider_audioProgressBar.getMax();
            double newValue = min + percent * (max - min);
            slider_audioProgressBar.setValue(newValue);
            mediaPlayer.seek(Duration.seconds(newValue));
            lbl_elapsedTime.setText(formatTime(Duration.seconds(newValue)));
        });

        slider_audioProgressBar.setOnMouseReleased(event -> {
            if (mediaPlayer != null) {
                double value = slider_audioProgressBar.getValue();
                mediaPlayer.seek(Duration.seconds(value));
                lbl_elapsedTime.setText(formatTime(Duration.seconds(value)));
            }
        });
    }

    private void setDefaultUI() {
        rectangle_audioCover.setFill(new ImagePattern(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/DefaultAudioCover.png")));
        btn_playORpause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Play.png"));
        btn_playORpause.setDisable(true);
        btn_next.setDisable(true);
        btn_previous.setDisable(true);
        lbl_audioName.setText("No audio playing");
        lbl_artistName.setText("");
        lbl_elapsedTime.setText("00:00");
        lbl_audioTotalTime.setText("00:00");
        slider_audioProgressBar.setValue(0);
        slider_audioProgressBar.setDisable(true);
        updateSliderFill(slider_audioProgressBar);
        updateVolumeIcon();
        MainTemplateController.setAudio(null);
        MainTemplateController.setAudiosList(null);
    }

    private void setupMediaPlayer() {
        try {
            if (currentAudio == null) {
                setDefaultUI();
                return;
            }

            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.dispose();
                mediaPlayer = null;
            }

            String mediaUrl = getValidMediaUrl(currentAudio.getAudioURL());
            Media media = new Media(mediaUrl);
            mediaPlayer = new MediaPlayer(media);

            double volumeToSet = isMuted ? 0 : currentVolume;
            mediaPlayer.setVolume(volumeToSet);

            mediaPlayer.setOnReady(() -> {
                Duration totalDuration = mediaPlayer.getTotalDuration();
                slider_audioProgressBar.setMax(totalDuration.toSeconds());
                lbl_audioTotalTime.setText(formatTime(totalDuration));
                updateSliderFill(slider_audioProgressBar);

                btn_playORpause.setDisable(false);
                btn_next.setDisable(currentPlaylist == null || currentPlaylist.size() <= 1);
                btn_previous.setDisable(currentPlaylist == null || currentPlaylist.size() <= 1);
                slider_audioProgressBar.setDisable(false);

                if (isPlaying) {
                    mediaPlayer.play();
                    btn_playORpause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Pause.png"));
                } else {
                    btn_playORpause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Play.png"));
                }

                // Refresh icons after media is ready
                AudioInListController.refreshAllIconsStatic();
            });

            mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                if (!isProgressSliderChanging && !slider_audioProgressBar.isValueChanging()) {
                    slider_audioProgressBar.setValue(newValue.toSeconds());
                }
                lbl_elapsedTime.setText(formatTime(newValue));
            });

            mediaPlayer.setOnEndOfMedia(() -> {
                atEndOfMedia = true;
                isPlaying = false;
                btn_playORpause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Play.png"));
                playNextAudio();
            });

            mediaPlayer.setOnError(() -> {
                setDefaultUI();
            });

        } catch (Exception e) {
            setDefaultUI();
        }
    }

    private void updateUI() {
        if (currentAudio != null) {
            lbl_audioName.setText(currentAudio.getAudioName());
            lbl_artistName.setText(currentAudio.getArtistName());
            if (currentAudio.getCover() != null) {
                rectangle_audioCover.setFill(new ImagePattern(currentAudio.getCover()));
            } else {
                rectangle_audioCover.setFill(new ImagePattern(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/defaultAudioCover.png")));
            }

            if (isPlaying) {
                btn_playORpause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Pause.png"));
            } else {
                btn_playORpause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Play.png"));
            }

            btn_playORpause.setDisable(false);
            // Enable/disable next/previous based on playlist size
            btn_next.setDisable(currentPlaylist == null || currentPlaylist.size() <= 1);
            btn_previous.setDisable(currentPlaylist == null || currentPlaylist.size() <= 1);
            slider_audioProgressBar.setDisable(false);
            updateVolumeIcon();

            // Update icons in AudioInListController
            AudioInListController.refreshAllIconsStatic();
        } else {
            setDefaultUI();
        }
    }

    // ============ STATIC METHODS ============

    public static void setOnAudioChangeCallback(Runnable callback) {
        onAudioChangeCallback = callback;
    }

    public static void setAudio(Audio audio) {
        if (audio == currentAudio) {
            return;
        }
        currentAudio = audio;
        if (instance != null) {
            instance.updateUI();
        }
    }

    public static void setPlaylist(ArrayList<Audio> playlist) {
        if (playlist == currentPlaylist) {
            return;
        }
        currentPlaylist = playlist;
        if (instance != null) {
            instance.updateUI();
            // Enable/disable next/previous buttons based on playlist size
            if (instance.btn_next != null) {
                instance.btn_next.setDisable(currentPlaylist == null || currentPlaylist.size() <= 1);
            }
            if (instance.btn_previous != null) {
                instance.btn_previous.setDisable(currentPlaylist == null || currentPlaylist.size() <= 1);
            }
        }
    }

    public static Audio getAudio() {
        return currentAudio;
    }

    public static ArrayList<Audio> getAudiosList() {
        return currentPlaylist;
    }

    public static boolean isPlaying() {
        return isPlaying;
    }

    public static void togglePlayPause() {
        if (mediaPlayer == null || currentAudio == null) {
            return;
        }

        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
            isPlaying = false;
            updatePlayButtonUI();
        } else {
            if (atEndOfMedia) {
                mediaPlayer.seek(Duration.ZERO);
                atEndOfMedia = false;
            }
            mediaPlayer.play();
            isPlaying = true;
            updatePlayButtonUI();
        }

        // Simply update all icons in AudioInListController
        AudioInListController.refreshAllIconsStatic();

        if (onAudioChangeCallback != null) {
            onAudioChangeCallback.run();
        }
    }

    public static void playAudio(Audio audio, ArrayList<Audio> playlist) {
        if (audio == null) return;

        // Increment play count
        audio.setNumberOfPlays(audio.getNumberOfPlays() + 1);

        currentAudio = audio;
        currentPlaylist = playlist;
        MainTemplateController.setAudio(audio);
        MainTemplateController.setAudiosList(playlist);
        isPlaying = true;
        atEndOfMedia = false;

        if (instance != null) {
            instance.setupMediaPlayer();
            instance.updateUI();
            if (mediaPlayer != null) {
                mediaPlayer.play();
                updatePlayButtonUI();
            }
        }

        // Update play counts in AudioInListController
        AudioInListController.updatePlaysDisplayForAllInstances();

        // Update all icons - this sets the playing song to PAUSE and all others to PLAY
        AudioInListController.refreshAllIconsStatic();

        if (onAudioChangeCallback != null) {
            onAudioChangeCallback.run();
        }
    }

    public static void resetPlaybar() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            mediaPlayer = null;
        }
        isPlaying = false;
        currentAudio = null;
        currentPlaylist = null;
        if (instance != null) {
            instance.setDefaultUI();
        }
        AudioInListController.refreshAllIconsStatic();
        if (onAudioChangeCallback != null) {
            onAudioChangeCallback.run();
        }
    }

    private static void updatePlayButtonUI() {
        if (instance != null && instance.btn_playORpause != null) {
            if (isPlaying) {
                instance.btn_playORpause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Pause.png"));
            } else {
                instance.btn_playORpause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Play.png"));
            }
        }
    }

    // ============ FXML EVENT HANDLERS ============

    @FXML
    void audioNameLbl_Clicked(MouseEvent event) {
        if (currentAudio == null) return;
        AudioPlayPageController.setAudio(currentAudio);
        MainTemplateController.centerPath.set("audioPlayPage");
    }

    @FXML
    void audioNameLbl_mouseEntered(MouseEvent event) {
        if (currentAudio == null) return;
        lbl_audioName.setUnderline(true);
        lbl_audioName.setCursor(Cursor.HAND);
    }

    @FXML
    void audioNameLbl_mouseExited(MouseEvent event) {
        lbl_audioName.setUnderline(false);
        lbl_audioName.setCursor(Cursor.DEFAULT);
    }

    @FXML
    void artistNameLbl_Clicked(MouseEvent event) {
        if (currentAudio == null) return;

        String artistName = currentAudio.getArtistName();
        for (Artist artist : AdminController.getAdminController().getArtistsArrayList()) {
            if (artist.getFirstAndLastName().equals(artistName)) {
                ArtistInfoPageController.setArtist(artist);
                MainTemplateController.centerPath.set("artistInfoPage");
                return;
            }
        }
        // If artist not found by full name, try by username
        for (Artist artist : AdminController.getAdminController().getArtistsArrayList()) {
            if (artist.getUserName().equals(artistName)) {
                ArtistInfoPageController.setArtist(artist);
                MainTemplateController.centerPath.set("artistInfoPage");
                return;
            }
        }
    }

    @FXML
    void artistNameLbl_mouseEntered(MouseEvent event) {
        if (currentAudio == null) return;
        lbl_artistName.setUnderline(true);
        lbl_artistName.setCursor(Cursor.HAND);
    }

    @FXML
    void artistNameLbl_mouseExited(MouseEvent event) {
        lbl_artistName.setUnderline(false);
        lbl_artistName.setCursor(Cursor.DEFAULT);
    }

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

    public void playNextAudio() {
        if (currentPlaylist == null || currentPlaylist.isEmpty()) return;
        if (currentAudio == null) return;

        int currentIndex = currentPlaylist.indexOf(currentAudio);
        Audio nextAudio;
        if (currentIndex == currentPlaylist.size() - 1) {
            nextAudio = currentPlaylist.get(0);
        } else {
            nextAudio = currentPlaylist.get(currentIndex + 1);
        }

        // Play the next audio
        playAudio(nextAudio, currentPlaylist);
    }

    public void playPreviousAudio() {
        if (currentPlaylist == null || currentPlaylist.isEmpty()) return;
        if (currentAudio == null) return;

        int currentIndex = currentPlaylist.indexOf(currentAudio);
        Audio previousAudio;
        if (currentIndex == 0) {
            previousAudio = currentPlaylist.get(currentPlaylist.size() - 1);
        } else {
            previousAudio = currentPlaylist.get(currentIndex - 1);
        }

        // Play the previous audio
        playAudio(previousAudio, currentPlaylist);
    }

    private String formatTime(Duration duration) {
        if (duration == null) return "00:00";
        int intSeconds = (int) Math.floor(duration.toSeconds());
        int minutes = intSeconds / 60;
        int seconds = intSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}