package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.DataBase;
import model.audio.Audio;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AllAudiosListController implements Initializable {

    @FXML
    private VBox VBox_allAudiosList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AudioInListController.clearInstances();

        int audioNumber = 1;
        ArrayList<Audio> allAudios = DataBase.getDataBase().getAudiosList();

        // Set the playlist for all audio items
        AudioInListController.setAudiosList(allAudios);
        // Also set it in MainTemplateController for the playbar
        MainTemplateController.setAudiosList(allAudios);

        for (Audio audio : allAudios) {
            AudioInListController.setAudio(audio);
            AudioInListController.setAudioNumber(audioNumber++);
            try {
                Parent audioInList = FXMLLoader.load(AudioInListController.class.getResource("/graphic/musicplayergraphicalprojectphase2/audioInList.fxml"));
                VBox_allAudiosList.getChildren().add(audioInList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        AudioInListController.refreshAllIconsStatic();
    }
}