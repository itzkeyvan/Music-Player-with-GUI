package view;

import controller.ListenerController;
import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Playlist;
import model.userAccount.Artist;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class ListenerPanelController implements Initializable {
    private static ListenerPanelController instance;

    @FXML
    private VBox VBox_FollowingArtistsList;

    @FXML
    private HBox HBoxForPlaylists_ListenerPanel;

    @FXML
    private Button btn_explorePremium;

    @FXML
    private Button btn_newPlaylist;

    @FXML
    private Label lbl_Credit;

    @FXML
    private Label lbl_Email;

    @FXML
    private Label lbl_FavouriteGenres;

    @FXML
    private Label lbl_FirstAndLastName;

    @FXML
    private Label lbl_Password;

    @FXML
    private Label lbl_PhoneNumber;

    @FXML
    private Label lbl_PremiumExpirationDate;

    @FXML
    private HBox HBox_premiumExpirationDate;

    @FXML
    private Label lbl_PremiumPlan;

    @FXML
    private Label lbl_UserName;

    @FXML
    void explorePremiumBtn_Clicked(MouseEvent event) {
        MainTemplateController.centerPath.set("explorePremiumPage");
    }

    @FXML
    void newPlaylistBtnClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(CreateNewPlayListController.class.getResource("/graphic/musicplayergraphicalprojectphase2/createNewPlayListPage.fxml"));
            Scene scene = new Scene(root, 300, 200);
            Stage playListCreationStage = new Stage();
            playListCreationStage.initModality(Modality.APPLICATION_MODAL);
            playListCreationStage.setScene(scene);
            playListCreationStage.showAndWait();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
        // Clear existing content
        HBoxForPlaylists_ListenerPanel.getChildren().clear();
        VBox_FollowingArtistsList.getChildren().clear();

        // Account info
        lbl_Credit.setText(String.valueOf(ListenerController.getListenerController().getListener().getAccountCredit()));
        lbl_Email.setText(ListenerController.getListenerController().getListener().getEmail());
        lbl_Password.setText(ListenerController.getListenerController().getListener().getPassword());
        lbl_PhoneNumber.setText(ListenerController.getListenerController().getListener().getPhoneNumber());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ListenerController.getListenerController().getListener().getFavouriteGenres().size(); i++) {
            sb.append(ListenerController.getListenerController().getListener().getFavouriteGenres().get(i).getGenreName());
            if (i == ListenerController.getListenerController().getListener().getFavouriteGenres().size() - 1)
                break;
            sb.append(", ");
        }
        lbl_FavouriteGenres.setText(sb.toString());
        lbl_FirstAndLastName.setText(ListenerController.getListenerController().getListener().getFirstAndLastName());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        if (ListenerController.getListenerController().getListener().getPremiumPlan() == null) {
            HBox_premiumExpirationDate.setVisible(false);
            lbl_PremiumPlan.setText("Not premium");
        } else {
            HBox_premiumExpirationDate.setVisible(true);
            lbl_PremiumExpirationDate.setText(dateFormat.format(ListenerController.getListenerController().getListener().getSubscriptionExpirationDate()));
            lbl_PremiumPlan.setText(ListenerController.getListenerController().getListener().getPremiumPlan().getPlanName());
        }
        lbl_UserName.setText(ListenerController.getListenerController().getListener().getUserName());

        // Load playlists - using instance-based approach
        for (Playlist playlist : ListenerController.getListenerController().getListener().getPlaylistsList()) {
            try {
                FXMLLoader loader = new FXMLLoader(PlayListPreviewController.class.getResource("/graphic/musicplayergraphicalprojectphase2/playListPreview.fxml"));
                Parent root = loader.load();
                PlayListPreviewController controller = loader.getController();
                controller.setPlaylist(playlist);
                HBoxForPlaylists_ListenerPanel.getChildren().add(root);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        // Load following artists
        for (Artist artist : ListenerController.getListenerController().getListener().getFollowingsList()) {
            try {
                FXMLLoader loader = new FXMLLoader(ArtistPreviewController.class.getResource("/graphic/musicplayergraphicalprojectphase2/artistPreview.fxml"));
                Parent root = loader.load();
                ArtistPreviewController controller = loader.getController();
                controller.setArtist(artist);
                VBox_FollowingArtistsList.getChildren().add(root);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void updatePlaylists() {
        if (instance != null) {
            instance.refreshPlaylists();
        }
    }

    private void refreshPlaylists() {
        // Clear existing playlists
        HBoxForPlaylists_ListenerPanel.getChildren().clear();

        // Reload playlists
        for (Playlist playlist : ListenerController.getListenerController().getListener().getPlaylistsList()) {
            try {
                FXMLLoader loader = new FXMLLoader(PlayListPreviewController.class.getResource("/graphic/musicplayergraphicalprojectphase2/playListPreview.fxml"));
                Parent root = loader.load();
                PlayListPreviewController controller = loader.getController();
                controller.setPlaylist(playlist);
                HBoxForPlaylists_ListenerPanel.getChildren().add(root);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}