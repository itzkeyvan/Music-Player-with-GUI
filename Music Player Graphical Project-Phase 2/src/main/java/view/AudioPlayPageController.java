package view;

import controller.ListenerController;
import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Playlist;
import model.audio.Audio;
import model.audio.AudioType;
import model.audio.Music;
import model.audio.Podcast;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class AudioPlayPageController implements Initializable
{
    private static Audio audio;

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
    void LikeBtn_Clicked(MouseEvent event)
    {
        if(ListenerController.getListenerController().getListener().getLikedAudios().containsKey(audio)&&ListenerController.getListenerController().getListener().getLikedAudios().get(audio).equals(false)) //not liked
        {
            ListenerController.getListenerController().likeAudio(audio.getAudioID());
            imageViewBtn_Like.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/NotLiked.png"));
        }
        else  //liked
        {
            ListenerController.getListenerController().getListener().getLikedAudios().remove(audio);
            imageViewBtn_Like.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Liked.png"));
        }
    }
    @FXML
    void PlayOrPauseBtn_Clicked(MouseEvent event)
    {
        if(!PlayBarController.getAudio().equals(audio))  //Not playing
        {
            imageViewBtn_PlayOrPause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/WhitePause.png"));
            PlayBarController.setAudio(audio);
            PlayBarController.togglePlayPause();
        }
        else   //Playing
        {
            imageViewBtn_PlayOrPause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/WhitePlay.png"));
            PlayBarController.togglePlayPause();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        rectangle_AudioCover.setFill(new ImagePattern(audio.getCover()));
        txt_ArtistUserName.setText(audio.getArtistName());
        txt_AudioLength.setText(audio.getAudioLength()/60 + ":"+audio.getAudioLength()%60);
        txt_AudioName.setText(audio.getAudioName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        txt_ReleaseDate.setText(dateFormat.format(audio.getReleaseDate()));
        txt_AudioLength.setText(audio.getAudioLength()/60 + ":"+audio.getAudioLength()%60);
        lbl_AudioType.setText(audio.getAudioType().getAudioTypeInString());
        if(audio.getAudioType().equals(AudioType.MUSIC))
        {
            Music music=(Music)audio;
            lbl_LyricsOrBio.setText("Lyrics");
            txt_LyricsOrBio.setText(music.getLyrics());
        }
        else
        {
            Podcast podcast=(Podcast)audio;
            lbl_LyricsOrBio.setText("Caption");
            txt_LyricsOrBio.setText(podcast.getCaption());
        }
        PlayBarController.getMediaPlayer().statusProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == MediaPlayer.Status.PLAYING&&PlayBarController.getAudio().equals(audio))  //Media is playing
            {
                imageViewBtn_PlayOrPause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/GreenPause.png"));
            }
            else
            {
                imageViewBtn_PlayOrPause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/GreenPlay.png"));
            }
        });
        if(ListenerController.getListenerController().getListener().getLikedAudios().containsKey(audio)&&ListenerController.getListenerController().getListener().getLikedAudios().get(audio).equals(false)) //not liked
            imageViewBtn_Like.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/NotLiked.png"));
        else  //liked
            imageViewBtn_Like.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Liked.png"));
        for(Playlist playlist: ListenerController.getListenerController().getListener().getPlaylistsList())
        {
            MenuItem menuItem=new MenuItem(playlist.getPlaylistName());
            menuBtn_AddToPlayList.getItems().add(menuItem);
            menuItem.setOnAction(e->
            {
                if(playlist.getAudiosList().contains(audio))
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("This audio is already added to the selected playlist.");
                    Main.setLoggedIn(true);
                    Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                    alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Tick.png"));
                    alert.showAndWait();
                }
                else
                {
                    playlist.getAudiosList().add(audio);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Audio added to the selected playlist successfully.");
                    Main.setLoggedIn(true);
                    Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                    alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Tick.png"));
                    alert.showAndWait();
                }
            });
        }
    }

    public static Audio getAudio() {
        return audio;
    }

    public static void setAudio(Audio audio) {
        AudioPlayPageController.audio = audio;
    }
}
