package view;

import controller.ListenerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.DataBase;
import model.audio.Audio;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchPageController implements Initializable {

    static private String searchedTerm;

    @FXML
    private VBox VBox_searchResultList;

    public static String getSearchedTerm() {
        return searchedTerm;
    }

    public static void setSearchedTerm(String searchedTerm) {
        SearchPageController.searchedTerm = searchedTerm;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Clear existing instances to avoid stale references
        AudioInListController.clearInstances();

        // Clear existing results
        VBox_searchResultList.getChildren().clear();

        int audioNumber = 1;
        ArrayList<Audio> searchResults = ListenerController.getListenerController().searchInAudios(searchedTerm);

        // Set the playlist for search results
        AudioInListController.setAudiosList(searchResults);

        for (Audio audio : searchResults) {
            AudioInListController.setAudio(audio);
            AudioInListController.setAudioNumber(audioNumber++);
            try {
                HBox audioInList = FXMLLoader.load(AudioInListController.class.getResource("/graphic/musicplayergraphicalprojectphase2/audioInList.fxml"));
                VBox_searchResultList.getChildren().add(audioInList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Refresh icons after loading
        AudioInListController.refreshAllIconsStatic();
    }
}