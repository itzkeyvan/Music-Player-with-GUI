package view;

import controller.ListenerController;
import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
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
        for(Audio audio:ListenerController.getListenerController().searchInAudios(searchedTerm));
        {
            int audioNumber=1;
            ArrayList<Audio> al=new ArrayList<>(DataBase.getDataBase().getAudiosList());
            for(int i=0;i<al.size()-1;i++)
                for(int j=0;j<al.size()-1-i;j++)
                    if(al.get(j).getNumberOfLikes()<al.get(j+1).getNumberOfLikes())
                    {
                        Audio temp=al.get(j);
                        al.set(j,al.get(j+1));
                        al.set(j+1,temp);
                    }
            for(Audio audio:al)
            {
                AudioInListController audioInListController=new AudioInListController();
                AudioInListController.setAudio(audio);
                audioInListController.setTxt_audioNumber(new Text(Integer.toString(audioNumber++)));
                HBox audioInList= null;
                try {
                    audioInList = FXMLLoader.load(AudioInListController.class.getResource("audioInList.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                VBox_searchResultList.getChildren().add(audioInList);
            }
        }
    }
}
