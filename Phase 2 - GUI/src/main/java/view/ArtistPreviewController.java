package view;

import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.userAccount.Artist;

import java.net.URL;
import java.util.ResourceBundle;

public class ArtistPreviewController implements Initializable {
    private Artist artist;

    @FXML
    private Rectangle rectangle_artistPP;

    @FXML
    private Text txt_ArtistName;

    @FXML
    private Text txt_artistNumOfFollowers;

    @FXML
    void artistPreview_Clicked(MouseEvent event) {
        if (artist != null) {
            ArtistInfoPageController.setArtist(artist);
            MainTemplateController.centerPath.set("artistInfoPage");
        }
    }

    @FXML
    void artistPreview_mouseEntered(MouseEvent event) {
        txt_ArtistName.setOpacity(1);
        txt_ArtistName.setCursor(Cursor.HAND);
        rectangle_artistPP.setCursor(Cursor.HAND);
    }

    @FXML
    void artistPreview_mouseExited(MouseEvent event) {
        txt_ArtistName.setOpacity(0.8);
        txt_ArtistName.setCursor(Cursor.DEFAULT);
        rectangle_artistPP.setCursor(Cursor.DEFAULT);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // The artist will be set via setArtist() after loading
    }

    // Called by parent controller after loading FXML
    public void setArtist(Artist artist) {
        this.artist = artist;
        // Update UI after setting
        if (artist != null) {
            rectangle_artistPP.setFill(new ImagePattern(artist.getProfilePicture()));
            txt_ArtistName.setText(artist.getFirstAndLastName());
            txt_artistNumOfFollowers.setText(String.valueOf(artist.getFollowersList().size()));
            txt_ArtistName.setCursor(Cursor.HAND);
            rectangle_artistPP.setCursor(Cursor.HAND);
        }
    }
}