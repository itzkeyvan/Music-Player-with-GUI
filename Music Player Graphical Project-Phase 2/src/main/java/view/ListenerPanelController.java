package view;

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




// Not complete!
public class ListenerPanelController implements Initializable
{

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
    private Label lbl_PremiumPlan;

    @FXML
    private Label lbl_UserName;

    @FXML
    void explorePremiumBtn_Clicked(MouseEvent event)
    {
        try {
            Main.setCurrentCenterNode(FXMLLoader.load(ExplorePremiumPageController.class.getResource("explorePremiumPage.fxml")));
            Main.getCenterNodesHistory().add(Main.getCurrentCenterNode());
            MainTemplateController.getBorderPane_mainTemplate().setCenter(Main.getCurrentCenterNode());
            Scene scene=new Scene(FXMLLoader.load(MainTemplateController.class.getResource("mainTamplate.fxml")));
            Main.getStage().setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void newPlaylistBtnClicked(MouseEvent event)
    {
        try {
            Parent root=FXMLLoader.load(CreateNewPlayListController.class.getResource("createNewPlayListPage.fxml"));
            Scene scene=new Scene(root,300,200);
            Stage playListCreationStage = new Stage();
            playListCreationStage.initModality(Modality.APPLICATION_MODAL);
            playListCreationStage.setScene(scene);
            playListCreationStage.showAndWait();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        lbl_Credit.setText(String.valueOf(Main.getListener().getAccountCredit()));
        lbl_Email.setText(Main.getListener().getEmail());
        lbl_Password.setText(Main.getListener().getPassword());
        lbl_PhoneNumber.setText(Main.getListener().getPhoneNumber());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Main.getListener().getFavouriteGenres().size(); i++)
        {
            sb.append(Main.getListener().getFavouriteGenres().get(i));
            if(i==Main.getListener().getFavouriteGenres().size() - 1)
                break;
            sb.append(", ");
        }
        lbl_FavouriteGenres.setText(sb.toString());
        lbl_FirstAndLastName.setText(Main.getListener().getFirstAndLastName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        lbl_PremiumExpirationDate.setText(dateFormat.format(Main.getListener().getSubscriptionExpirationDate()));
        if(Main.getListener().getPremiumPlan()==null)
        {
            lbl_PremiumExpirationDate.setText("Not premium");
            lbl_PremiumPlan.setText("Not premium");
        }
        else
        {
            lbl_PremiumExpirationDate.setText(dateFormat.format(Main.getListener().getSubscriptionExpirationDate()));
            lbl_PremiumPlan.setText(Main.getListener().getPremiumPlan().name());
        }
        lbl_UserName.setText(Main.getListener().getUserName());
        for(Playlist playlist:Main.getListener().getPlaylistsList())
        {
            PlayListPreviewController.setPlaylist(playlist);
            try {
                Parent root =FXMLLoader.load(PlayListPreviewController.class.getResource("playListPreview.fxml"));
                HBoxForPlaylists_ListenerPanel.getChildren().add(root);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        for(Artist artist:Main.getListener().getFollowingsList())
        {
            ArtistPreviewController.setArtist(artist);
            try {
                Parent root =FXMLLoader.load(PlayListPreviewController.class.getResource("playListPreview.fxml"));
                VBox_FollowingArtistsList.getChildren().add(root);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
