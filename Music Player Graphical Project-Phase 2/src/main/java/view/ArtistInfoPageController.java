package view;

import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Album;
import model.audio.Music;
import model.audio.Podcast;
import model.userAccount.Artist;
import model.userAccount.ArtistType;
import model.userAccount.artist.Podcaster;
import model.userAccount.artist.Singer;

import java.net.URL;
import java.util.ResourceBundle;

public class ArtistInfoPageController implements Initializable
{
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
    void PlayOrPauseInList_Clicked(MouseEvent event) {

    }

    @FXML
    void followLblBtn_Clicked(MouseEvent event)
    {
        if(lblBTn_Follow.getText().equals("Follow"))
        {
            Main.getListenerController().followArtist(artist.getUserName());
            lblBTn_Follow.setText("Following");
        }
        else
        {

        }
    }

    @FXML
    void reportLblBtn_Clicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        rectangle_ArtistProfilePicture.setFill(new ImagePattern(artist.getProfilePicture()));
        if(Main.getListener().getFollowingsList().contains(artist))
            lblBTn_Follow.setText("Following");
        else
            lblBTn_Follow.setText("Follow");
        if(artist.getArtistType()== ArtistType.SINGER)
        {
            Singer singer=(Singer)artist;
            txt_AudioType.setText("music");
            long numberOfTotalPlays=0;
            int numberOfAudios=0;
            for(Album album: singer.getAlbumsList())
            {
                numberOfAudios++;
                for(Music music:album.getMusicsList())
                    numberOfTotalPlays+=music.getNumberOfPlays();
            }
            txt_NumberOfTotalPlays.setText(String.valueOf(numberOfTotalPlays));
            txt_NumberOfAudios.setText(String.valueOf(numberOfAudios));
        }
        else  //Podcaster
        {
            txt_AudioType.setText("podcast");
            Podcaster podcaster=(Podcaster) artist;
            txt_AudioType.setText("podcast");
            long numberOfTotalPlays=0;
            int numberOfAudios=0;
            for(Podcast podcast: podcaster.getPodcastsList())
            {
                numberOfAudios++;
                numberOfTotalPlays+=podcast.getNumberOfPlays();
            }
            txt_NumberOfTotalPlays.setText(String.valueOf(numberOfTotalPlays));
            txt_NumberOfAudios.setText(String.valueOf(numberOfAudios));
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
