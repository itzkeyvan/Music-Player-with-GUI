package view;

import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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

public class HomePageController implements Initializable
{
    @FXML
    private VBox VBox_homePage_audiosSortedByLikesList;

    @FXML
    private Label lbl_homePageHboxLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        int audioNumber=1;
        if(!Main.isLoggedIn())
        {
            lbl_homePageHboxLabel.setText("Most liked audios");
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
                VBox_homePage_audiosSortedByLikesList.getChildren().add(audioInList);
            }
        }
        else
        {
            lbl_homePageHboxLabel.setText("Audios for you");
            for(Audio audio:Main.getListenerController().getSuggestedAudios())
            {
                AudioInListController audioInListController=new AudioInListController();
                AudioInListController.setAudio(audio);
                audioInListController.setTxt_audioNumber(new Text(Integer.toString(audioNumber++)));
                HBox audioInList = null;
                try {
                    audioInList = FXMLLoader.load(AudioInListController.class.getResource("audioInList.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                VBox_homePage_audiosSortedByLikesList.getChildren().add(audioInList);
            }
        }
    }
}
