package view;

import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.userAccount.Artist;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ArtistPreviewController implements Initializable
{
    static private Artist artist;

    @FXML
    private Rectangle rectangle_artistPP;

    @FXML
    private Text txt_ArtistName;

    @FXML
    private Text txt_artistNumOfFollowers;

    @FXML
    void artistNameTxt_Clicked(MouseEvent event) throws IOException {
        ArtistInfoPageController.setArtist(artist);
        MainTemplateController.centerPath.set("artistInfoPage");
    }

    @FXML
    void artistNameTxt_mouseEntered(MouseEvent event) {
        txt_ArtistName.setOpacity(1);
    }

    @FXML
    void artistNameTxt_mouseExited(MouseEvent event) {
        txt_ArtistName.setOpacity(0.8);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        rectangle_artistPP.setFill(new ImagePattern(artist.getProfilePicture()));
        txt_ArtistName.setText(artist.getFirstAndLastName());
        txt_artistNumOfFollowers.setText(String.valueOf(artist.getFollowersList().size()));
    }

    public static Artist getArtist() {
        return artist;
    }

    public static void setArtist(Artist artist) {
        ArtistPreviewController.artist = artist;
    }
}