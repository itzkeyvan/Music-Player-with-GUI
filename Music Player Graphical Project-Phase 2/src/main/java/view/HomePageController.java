package view;

import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.DataBase;
import model.audio.Audio;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomePageController implements Initializable
{
    @FXML
    private VBox VBox_homePage_audiosSortedByLikesList;

    @FXML
    private ImageView imgView_PlayOrPauseInList;

    @FXML
    private Label lbl_homePageHboxLabel;

    @FXML
    private Text txt_homePage_artistName;

    @FXML
    private Text txt_homePage_audioName;

    @FXML
    private Text txt_homePage_audioNumberOfLikes;

    @FXML
    void PlayOrPauseInList_Clicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        if(!Main.isLoggedIn())
        {
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


            }
        }
    }
}
