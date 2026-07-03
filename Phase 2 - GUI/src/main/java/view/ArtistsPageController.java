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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VBox_ArtistsList.getChildren().clear();

        for (Artist artist : AdminController.getAdminController().getArtistsArrayList()) {
            try {
                FXMLLoader loader = new FXMLLoader(ArtistPreviewController.class.getResource("/graphic/musicplayergraphicalprojectphase2/artistPreview.fxml"));
                Parent artistPreview = loader.load();
                ArtistPreviewController controller = loader.getController();
                controller.setArtist(artist);
                VBox_ArtistsList.getChildren().add(artistPreview);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}