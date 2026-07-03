package view;

import controller.ListenerController;
import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.DataBase;
import model.audio.Audio;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomePageController implements Initializable
{
    @FXML
    private VBox VBox_homePage_audiosSortedByLikesList;

    @FXML
    private Label lbl_homePageHboxLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Clear existing instances to avoid stale references
        AudioInListController.clearInstances();

        int audioNumber = 1;
        ArrayList<Audio> audioList = new ArrayList<>();

        if (!Main.isLoggedIn()) {
            AudioInListController.setNumOfLikes(true);
            lbl_homePageHboxLabel.setText("Most liked audios");

            // Sort by likes
            audioList = new ArrayList<>(DataBase.getDataBase().getAudiosList());
            audioList.sort((a1, a2) -> Integer.compare(a2.getNumberOfLikes(), a1.getNumberOfLikes()));
        } else {
            lbl_homePageHboxLabel.setText("Audios for you");
            audioList = ListenerController.getListenerController().getSuggestedAudios();
        }

        // Set the playlist for all audio items in this list
        AudioInListController.setAudiosList(audioList);

        for (Audio audio : audioList) {
            AudioInListController.setAudio(audio);
            AudioInListController.setAudioNumber(audioNumber++);
            try {
                HBox audioInList = FXMLLoader.load(AudioInListController.class.getResource("/graphic/musicplayergraphicalprojectphase2/audioInList.fxml"));
                VBox_homePage_audiosSortedByLikesList.getChildren().add(audioInList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        AudioInListController.setNumOfLikes(false);

        // Refresh icons after loading all items
        AudioInListController.refreshAllIconsStatic();
    }
}