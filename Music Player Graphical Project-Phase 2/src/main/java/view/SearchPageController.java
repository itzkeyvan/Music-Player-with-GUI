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
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        for(Audio audio:ListenerController.getListenerController().searchInAudios(searchedTerm))
        {
            AudioInListController.setAudio(audio);
            HBox audioInList= null;
            try {
                audioInList = FXMLLoader.load(AudioInListController.class.getResource("/graphic/musicplayergraphicalprojectphase2/audioInList.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            VBox_searchResultList.getChildren().add(audioInList);
        }
    }
}
