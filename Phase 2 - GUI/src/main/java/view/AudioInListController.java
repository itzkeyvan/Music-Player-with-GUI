package view;

import controller.AdminController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.audio.Audio;
import model.userAccount.Artist;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AudioInListController implements Initializable {
    // Instance-specific fields (not static)
    private Audio currentAudio;
    private int currentAudioNumber;

    // Static fields for shared state
    private static boolean numOfLikes;
    private static ArrayList<Audio> currentPlaylist;

    // Keep track of all AudioInListController instances to update icons
    private static final ArrayList<AudioInListController> instances = new ArrayList<>();

    // Pending data for the next instance to be created
    private static Audio pendingAudio;
    private static int pendingAudioNumber;

    @FXML
    private ImageView imgView_PlayOrPauseInList;

    @FXML
    private Rectangle rectangle_AudioCover;

    @FXML
    private Text txt_artistName;

    @FXML
    private Text txt_audioLength;

    @FXML
    private Text txt_audioName;

    @FXML
    private Text txt_audioNumber;

    @FXML
    private Text txt_audioNumberOfPlaysOrLikes;

    @FXML
    private HBox hbox_audioInfo;

    @FXML
    void PlayOrPauseInListBtn_Clicked(MouseEvent event) {
        if (currentAudio == null) return;

        if (currentPlaylist == null || !currentPlaylist.contains(currentAudio)) {
            currentPlaylist = new ArrayList<>();
            currentPlaylist.add(currentAudio);
        }

        Audio playingAudio = MainTemplateController.getAudio();

        if (playingAudio != null && playingAudio.getAudioID() == currentAudio.getAudioID()) {
            MainTemplateController.togglePlayPauseStatic();
        } else {
            MainTemplateController.setAudio(currentAudio);
            MainTemplateController.setAudiosList(currentPlaylist);
            MainTemplateController.playCurrentAudioStatic();
        }

        updateAllIcons();
    }

    @FXML
    void hbox_audioInfo_Clicked(MouseEvent event) {
        if (currentAudio != null) {
            AudioPlayPageController.setAudio(currentAudio);
            MainTemplateController.centerPath.set("audioPlayPage");
        }
    }

    @FXML
    void hbox_audioInfo_MouseEntered(MouseEvent event) {
        txt_audioName.setOpacity(1.0);
        hbox_audioInfo.setCursor(Cursor.HAND);
    }

    @FXML
    void hbox_audioInfo_MouseExited(MouseEvent event) {
        txt_audioName.setOpacity(0.8);
        hbox_audioInfo.setCursor(Cursor.DEFAULT);
    }

    @FXML
    void artistName_Clicked(MouseEvent event) {
        if (currentAudio == null) return;

        String artistName = currentAudio.getArtistName();
        for (Artist artist : AdminController.getAdminController().getArtistsArrayList()) {
            if (artist.getFirstAndLastName().equals(artistName)) {
                ArtistInfoPageController.setArtist(artist);
                MainTemplateController.centerPath.set("artistInfoPage");
                return;
            }
        }
        for (Artist artist : AdminController.getAdminController().getArtistsArrayList()) {
            if (artist.getUserName().equals(artistName)) {
                ArtistInfoPageController.setArtist(artist);
                MainTemplateController.centerPath.set("artistInfoPage");
                return;
            }
        }
    }

    @FXML
    void artistName_MouseEntered(MouseEvent event) {
        txt_artistName.setOpacity(1.0);
        txt_artistName.setCursor(Cursor.HAND);
    }

    @FXML
    void artistName_MouseExited(MouseEvent event) {
        txt_artistName.setOpacity(0.8);
        txt_artistName.setCursor(Cursor.DEFAULT);
    }

    // Simple method to update all icons - checks if each audio is the one currently playing
    public static void updateAllIcons() {
        Audio currentlyPlaying = MainTemplateController.getAudio();
        boolean isCurrentlyPlaying = MainTemplateController.isPlayingStatic();

        if (currentlyPlaying == null) {
            // No audio playing, set all to PLAY
            for (AudioInListController instance : instances) {
                if (instance != null && instance.imgView_PlayOrPauseInList != null) {
                    instance.imgView_PlayOrPauseInList.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/WhitePlay.png"));
                }
            }
            return;
        }

        for (AudioInListController instance : instances) {
            if (instance == null || instance.imgView_PlayOrPauseInList == null) continue;
            if (instance.currentAudio == null) continue;

            // Compare by audio ID (unique identifier)
            if (instance.currentAudio.getAudioID() == currentlyPlaying.getAudioID()) {
                // This is the playing audio
                instance.imgView_PlayOrPauseInList.setImage(
                        isCurrentlyPlaying ?
                                new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/WhitePause.png") :
                                new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/WhitePlay.png")
                );
            } else {
                // Not playing
                instance.imgView_PlayOrPauseInList.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/WhitePlay.png"));
            }
        }
    }

    public static void updatePlaysDisplayForAllInstances() {
        for (AudioInListController instance : instances) {
            if (instance == null || instance.currentAudio == null) continue;
            if (instance.txt_audioNumberOfPlaysOrLikes == null) continue;

            if (isNumOfLikes()) {
                instance.txt_audioNumberOfPlaysOrLikes.setText(String.valueOf(instance.currentAudio.getNumberOfLikes()));
            } else {
                instance.txt_audioNumberOfPlaysOrLikes.setText(String.valueOf(instance.currentAudio.getNumberOfPlays()));
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.currentAudio = pendingAudio;
        this.currentAudioNumber = pendingAudioNumber;

        pendingAudio = null;
        pendingAudioNumber = 0;

        // Remove any existing instance with the same audio ID
        if (currentAudio != null) {
            instances.removeIf(instance -> instance.currentAudio != null && instance.currentAudio.getAudioID() == currentAudio.getAudioID());
        }
        instances.add(this);

        if (currentAudio == null) return;

        if (currentAudio.getCover() != null) {
            rectangle_AudioCover.setFill(new ImagePattern(currentAudio.getCover()));
        } else {
            rectangle_AudioCover.setFill(new ImagePattern(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/defaultAudioCover.png")));
        }

        txt_artistName.setText(currentAudio.getArtistName());
        txt_audioName.setText(currentAudio.getAudioName());
        txt_audioNumber.setText(String.valueOf(currentAudioNumber));

        if (isNumOfLikes()) {
            txt_audioNumberOfPlaysOrLikes.setText(String.valueOf(currentAudio.getNumberOfLikes()));
        } else {
            txt_audioNumberOfPlaysOrLikes.setText(String.valueOf(currentAudio.getNumberOfPlays()));
        }

        long minutes = currentAudio.getAudioLength() / 60;
        long seconds = currentAudio.getAudioLength() % 60;
        txt_audioLength.setText(String.format("%02d:%02d", minutes, seconds));

        // Update icons after initialization
        updateAllIcons();
    }

    // Public static methods for external use
    public static void refreshAllIconsStatic() {
        updateAllIcons();
    }

    public static void resetAllIcons() {
        for (AudioInListController instance : instances) {
            if (instance != null && instance.imgView_PlayOrPauseInList != null) {
                instance.imgView_PlayOrPauseInList.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/WhitePlay.png"));
            }
        }
    }

    public static void clearInstances() {
        instances.clear();
    }

    public static void setAudio(Audio audio) {
        pendingAudio = audio;
    }

    public static void setAudioNumber(int audioNumber) {
        pendingAudioNumber = audioNumber;
    }

    public static void setNumOfLikes(boolean numOfPlaysOrLikes) {
        AudioInListController.numOfLikes = numOfPlaysOrLikes;
    }

    public static boolean isNumOfLikes() {
        return numOfLikes;
    }

    public static void setAudiosList(ArrayList<Audio> audiosList) {
        AudioInListController.currentPlaylist = audiosList;
    }

    public static ArrayList<Audio> getAudiosList() {
        return currentPlaylist;
    }

    public static int getAudioNumber() {
        if (!instances.isEmpty()) {
            return instances.get(instances.size() - 1).currentAudioNumber;
        }
        return 0;
    }
}