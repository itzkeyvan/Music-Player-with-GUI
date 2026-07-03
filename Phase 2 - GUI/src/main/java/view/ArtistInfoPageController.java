package view;

import controller.ListenerController;
import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Album;
import model.audio.Audio;
import model.audio.Music;
import model.audio.Podcast;
import model.userAccount.Artist;
import model.userAccount.ArtistType;
import model.userAccount.artist.Podcaster;
import model.userAccount.artist.Singer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ArtistInfoPageController implements Initializable {
    static private Artist artist;

    @FXML
    private VBox VBox_audiosList;

    @FXML
    private Label lblBTn_Follow;

    @FXML
    private Rectangle rectangle_ArtistProfilePicture;

    @FXML
    private Text txt_AudioType;

    @FXML
    private Text txt_NumberOfAudios;

    @FXML
    private Text txt_NumberOfTotalPlays;

    @FXML
    private Text txt_artistName;

    @FXML
    private Text txt_artistType;

    @FXML
    private Text txt_biography;

    @FXML
    private Text txt_numberOfFollowers;

    @FXML
    void followLblBtn_Clicked(MouseEvent event) {
        if (artist == null) return;
        if (lblBTn_Follow.getText().equals("Follow")) {
            ListenerController.getListenerController().followArtist(artist.getUserName());
            lblBTn_Follow.setText("Following");
        } else {
            ListenerController.getListenerController().unFollowArtist(artist.getUserName());
            lblBTn_Follow.setText("Follow");
        }
    }

    @FXML
    void reportLblBtn_Clicked(MouseEvent event) throws IOException {
        MainTemplateController.centerPath.set("reportPage");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (artist == null) return;

        rectangle_ArtistProfilePicture.setFill(new ImagePattern(artist.getProfilePicture()));

        // Update follow button based on current follow status
        if (ListenerController.getListenerController().getListener().getFollowingsList().contains(artist)) {
            lblBTn_Follow.setText("Following");
        } else {
            lblBTn_Follow.setText("Follow");
        }

        ArrayList<Audio> artistAudios = new ArrayList<>();

        if (artist.getArtistType() == ArtistType.SINGER) {
            Singer singer = (Singer) artist;
            txt_AudioType.setText("music");
            long numberOfTotalPlays = 0;
            int numberOfAudios = 0;

            for (Album album : singer.getAlbumsList()) {
                for (Music music : album.getMusicsList()) {
                    numberOfAudios++;
                    numberOfTotalPlays += music.getNumberOfPlays();
                    artistAudios.add(music);
                }
            }

            txt_NumberOfTotalPlays.setText(String.valueOf(numberOfTotalPlays));
            txt_NumberOfAudios.setText(String.valueOf(numberOfAudios));

        } else { // Podcaster
            Podcaster podcaster = (Podcaster) artist;
            txt_AudioType.setText("podcast");
            long numberOfTotalPlays = 0;
            int numberOfAudios = 0;

            for (Podcast podcast : podcaster.getPodcastsList()) {
                numberOfAudios++;
                numberOfTotalPlays += podcast.getNumberOfPlays();
                artistAudios.add(podcast);
            }

            txt_NumberOfTotalPlays.setText(String.valueOf(numberOfTotalPlays));
            txt_NumberOfAudios.setText(String.valueOf(numberOfAudios));
        }

        // Set the playlist for artist's audios (so that playbar next/prev works)
        AudioInListController.setAudiosList(artistAudios);

        // Populate the audio list
        VBox_audiosList.getChildren().clear();
        int audioNumber = 1;
        for (Audio audio : artistAudios) {
            AudioInListController.setAudio(audio);
            AudioInListController.setAudioNumber(audioNumber++);
            try {
                HBox audioInList = FXMLLoader.load(AudioInListController.class.getResource("/graphic/musicplayergraphicalprojectphase2/audioInList.fxml"));
                VBox_audiosList.getChildren().add(audioInList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        txt_artistName.setText(artist.getFirstAndLastName());
        txt_artistType.setText(artist.getArtistType().getArtistTypeInString());
        txt_biography.setText(artist.getBiography());
        txt_numberOfFollowers.setText(String.valueOf(artist.getFollowersList().size()));
    }

    public static Artist getArtist() {
        return artist;
    }

    public static void setArtist(Artist artist) {
        ArtistInfoPageController.artist = artist;
    }
}