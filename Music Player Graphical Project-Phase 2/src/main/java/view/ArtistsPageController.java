package view;

import controller.AdminController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import model.userAccount.Artist;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ArtistsPageController implements Initializable {

    @FXML
    private VBox VBox_ArtistsList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        for(Artist artist:AdminController.getAdminController().getArtistsArrayList())
        {
            ArtistPreviewController.setArtist(artist);
            Parent artistPreview;
            try {
                artistPreview = FXMLLoader.load(AudioInListController.class.getResource("artistPreview.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            VBox_ArtistsList.getChildren().add(artistPreview);
        }
    }
}
