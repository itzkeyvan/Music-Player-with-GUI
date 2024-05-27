package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.DataBase;
import model.audio.Audio;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AllAudiosListController implements Initializable {

    @FXML
    private VBox VBox_allAudiosList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        int audioNumber=1;
        for(Audio audio: DataBase.getDataBase().getAudiosList())
        {
            AudioInListController audioInListController=new AudioInListController();
            AudioInListController.setAudio(audio);
            audioInListController.setTxt_audioNumber(new Text(Integer.toString(audioNumber++)));
            Parent audioInList;
            try {
                audioInList = FXMLLoader.load(AudioInListController.class.getResource("audioInList.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            VBox_allAudiosList.getChildren().add(audioInList);
        }
    }
}
