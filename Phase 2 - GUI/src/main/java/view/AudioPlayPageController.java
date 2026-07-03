package view;

import controller.AdminController;
import controller.ListenerController;
import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Playlist;
import model.audio.Audio;
import model.audio.AudioType;
import model.audio.Music;
import model.audio.Podcast;
import model.userAccount.Artist;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AudioPlayPageController implements Initializable {
    private static Audio audio;

    @FXML
    private Text txt_artistName;

    @FXML
    private Text txt_Genre;  // Added for genre

    @FXML
    private MenuButton menuBtn_AddToPlayList;

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
    private Text txt_AudioLength;

    @FXML
    private Text txt_AudioName;

    @FXML
    private Text txt_LyricsOrBio;

    @FXML
    private Text txt_ReleaseDate;

    @FXML
    private Text txt_NumberOfTotalPlays;

    @FXML
    private Text txt_NumberOfTotalLikes;

    @FXML
    void LikeBtn_Clicked(MouseEvent event) {
        if (audio == null) return;

        if (ListenerController.getListenerController().getListener().getLikedAudios().containsKey(audio) &&
                ListenerController.getListenerController().getListener().getLikedAudios().get(audio)) {
            // Unlike
            ListenerController.getListenerController().getListener().getLikedAudios().remove(audio);
            audio.setNumberOfLikes(audio.getNumberOfLikes() - 1);
            imageViewBtn_Like.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/NotLiked.png"));
        } else {
            // Like
            ListenerController.getListenerController().likeAudio(audio.getAudioID());
            imageViewBtn_Like.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Liked.png"));
        }

        // Update the likes display
        updateStats();
    }

    @FXML
    void PlayOrPauseBtn_Clicked(MouseEvent event) {
        if (audio == null) return;

        ArrayList<Audio> playlist = new ArrayList<>();
        playlist.add(audio);

        Audio playingAudio = MainTemplateController.getAudio();

        if (playingAudio != null && playingAudio.getAudioID() == audio.getAudioID()) {
            MainTemplateController.togglePlayPauseStatic();

            if (MainTemplateController.isPlayingStatic()) {
                imageViewBtn_PlayOrPause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/WhitePause.png"));
            } else {
                imageViewBtn_PlayOrPause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/WhitePlay.png"));
            }

            AudioInListController.refreshAllIconsStatic();
        } else {
            imageViewBtn_PlayOrPause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/WhitePause.png"));
            MainTemplateController.setAudio(audio);
            MainTemplateController.setAudiosList(playlist);
            MainTemplateController.playCurrentAudioStatic();
            // Update plays display
            updateStats();
        }
    }

    private void updateStats() {
        if (audio != null) {
            txt_NumberOfTotalPlays.setText(String.valueOf(audio.getNumberOfPlays()));
            txt_NumberOfTotalLikes.setText(String.valueOf(audio.getNumberOfLikes()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (audio == null) return;

        rectangle_AudioCover.setFill(new ImagePattern(audio.getCover()));

        txt_artistName.setText(audio.getArtistName());
        txt_AudioName.setText(audio.getAudioName());
        txt_Genre.setText(audio.getGenre().getGenreName());  // Set the genre

        long minutes = audio.getAudioLength() / 60;
        long seconds = audio.getAudioLength() % 60;
        txt_AudioLength.setText(String.format("%02d:%02d", minutes, seconds));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        txt_ReleaseDate.setText(dateFormat.format(audio.getReleaseDate()));

        lbl_AudioType.setText(audio.getAudioType().getAudioTypeInString());

        // Set plays and likes
        txt_NumberOfTotalPlays.setText(String.valueOf(audio.getNumberOfPlays()));
        txt_NumberOfTotalLikes.setText(String.valueOf(audio.getNumberOfLikes()));

        if (audio.getAudioType().equals(AudioType.MUSIC)) {
            Music music = (Music) audio;
            lbl_LyricsOrBio.setText("Lyrics");
            txt_LyricsOrBio.setText(music.getLyrics());
        } else {
            Podcast podcast = (Podcast) audio;
            lbl_LyricsOrBio.setText("Caption");
            txt_LyricsOrBio.setText(podcast.getCaption());
        }

        // Set like button state
        if (ListenerController.getListenerController().getListener().getLikedAudios().containsKey(audio) &&
                ListenerController.getListenerController().getListener().getLikedAudios().get(audio)) {
            imageViewBtn_Like.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Liked.png"));
        } else {
            imageViewBtn_Like.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/NotLiked.png"));
        }

        // Set play/pause button state
        Audio playingAudio = MainTemplateController.getAudio();
        if (playingAudio != null && playingAudio.getAudioID() == audio.getAudioID() && MainTemplateController.isPlayingStatic()) {
            imageViewBtn_PlayOrPause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/WhitePause.png"));
        } else {
            imageViewBtn_PlayOrPause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/WhitePlay.png"));
        }

        // Populate playlist menu
        populatePlaylistMenu();
    }

    private void populatePlaylistMenu() {
        menuBtn_AddToPlayList.getItems().clear();

        // Add "Remove from playlists" option first
        MenuItem removeFromPlaylistsItem = new MenuItem("❌ Remove from all playlists");
        removeFromPlaylistsItem.setStyle("-fx-text-fill: #ff6b6b; -fx-font-weight: bold;");
        removeFromPlaylistsItem.setOnAction(e -> {
            removeAudioFromAllPlaylists();
        });
        menuBtn_AddToPlayList.getItems().add(removeFromPlaylistsItem);

        // Add separator
        SeparatorMenuItem separator = new SeparatorMenuItem();
        menuBtn_AddToPlayList.getItems().add(separator);

        // Add playlist options
        for (Playlist playlist : ListenerController.getListenerController().getListener().getPlaylistsList()) {
            MenuItem menuItem = new MenuItem(playlist.getPlaylistName());

            // Check if audio is already in this playlist
            if (playlist.getAudiosList().contains(audio)) {
                menuItem.setText(playlist.getPlaylistName() + " ✓");
            }

            menuItem.setOnAction(e -> {
                if (playlist.getAudiosList().contains(audio)) {
                    // If already in playlist, remove it
                    playlist.getAudiosList().remove(audio);
                    showAlert("Success", "Audio removed from playlist: " + playlist.getPlaylistName());
                } else {
                    // Add to playlist
                    playlist.getAudiosList().add(audio);
                    showAlert("Success", "Audio added to playlist: " + playlist.getPlaylistName());
                }
                // Refresh the menu to update checkmarks
                populatePlaylistMenu();
            });
            menuBtn_AddToPlayList.getItems().add(menuItem);
        }

        // If no playlists exist, show a message
        if (ListenerController.getListenerController().getListener().getPlaylistsList().isEmpty()) {
            MenuItem noPlaylistItem = new MenuItem("No playlists available");
            noPlaylistItem.setDisable(true);
            noPlaylistItem.setStyle("-fx-text-fill: #666666; -fx-font-style: italic;");
            menuBtn_AddToPlayList.getItems().add(noPlaylistItem);
        }
    }

    private void removeAudioFromAllPlaylists() {
        int removedCount = 0;
        for (Playlist playlist : ListenerController.getListenerController().getListener().getPlaylistsList()) {
            if (playlist.getAudiosList().contains(audio)) {
                playlist.getAudiosList().remove(audio);
                removedCount++;
            }
        }

        if (removedCount > 0) {
            showAlert("Success", "Audio removed from " + removedCount + " playlist(s).");
            // Refresh the menu to update checkmarks
            populatePlaylistMenu();
        } else {
            showAlert("Info", "This audio is not in any playlist.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Tick.png"));
        alert.showAndWait();
    }

    public static Audio getAudio() {
        return audio;
    }

    public static void setAudio(Audio audio) {
        AudioPlayPageController.audio = audio;
    }

    @FXML
    void artistName_Clicked(MouseEvent event) {
        String artistName = audio.getArtistName();
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
        txt_artistName.setOpacity(0.8);
        txt_artistName.setCursor(Cursor.HAND);
    }

    @FXML
    void artistName_MouseExited(MouseEvent event) {
        txt_artistName.setOpacity(1.0);
        txt_artistName.setCursor(Cursor.DEFAULT);
    }
}