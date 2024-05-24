package view;

import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.audio.Audio;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AudioInListController implements Initializable
{
    private static Audio audio;
    private static boolean numOfLikes;
    @FXML
    private ImageView imgView_PlayOrPauseInList;

    @FXML
    private Rectangle rectangle_AudioCover;

    @FXML
    private Text txt_artistName;

    @FXML
    private Text txt_audioLength;

    @FXML
    private Text txt_audioName;

    @FXML
    private Text txt_audioNumber;

    @FXML
    private Text txt_audioNumberOfPlaysOrLikes;

    @FXML
    void PlayOrPauseInListBtn_Clicked(MouseEvent event) throws IOException
    {
        if(!PlayBarController.getAudio().equals(audio))  //Not playing
        {
            imgView_PlayOrPauseInList.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/WhitePause.png"));
            PlayBarController.setAudio(audio);
            PlayBarController.togglePlayPause();
        }
        else   //Playing
        {
            imgView_PlayOrPauseInList.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/WhitePlay.png"));
            PlayBarController.togglePlayPause();
        }
    }

    @FXML
    void audioNameTxt_clicked(MouseEvent event) throws IOException {
        AudioPlayPageController.setAudio(audio);
        Main.setCurrentCenterNode(FXMLLoader.load(AudioPlayPageController.class.getResource("AudioPlayPage.fxml")));
        Main.getCenterNodesHistory().add(Main.getCurrentCenterNode());
        MainTemplateController.getBorderPane_mainTemplate().setCenter(Main.getCurrentCenterNode());
        Scene scene = new Scene(MainTemplateController.getBorderPane_mainTemplate(), 745, 547);
        Main.getStage().setScene(scene);
    }
    @FXML
    void audioNameTxt_mouseEntered(MouseEvent event)
    {
        txt_audioName.setOpacity(1.0);
    }

    @FXML
    void audioNameTxt_mouseExited(MouseEvent event)
    {
        txt_audioName.setOpacity(0.8);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        if(PlayBarController.getAudio()==audio)   //Already playing
            imgView_PlayOrPauseInList.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/WhitePause.png"));
        else
            imgView_PlayOrPauseInList.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/WhitePlay.png"));
        rectangle_AudioCover.setFill(new ImagePattern(audio.getCover()));
        txt_artistName.setText(audio.getArtistName());
        txt_audioName.setText(audio.getAudioName());
        if(isNumOfLikes())
            txt_audioNumberOfPlaysOrLikes.setText(String.valueOf(audio.getNumberOfLikes()));
        else
            txt_audioNumberOfPlaysOrLikes.setText(String.valueOf(audio.getNumberOfPlays()));
        txt_audioLength.setText(audio.getAudioLength()/60+":"+ audio.getAudioLength()%60);
    }

    public static Audio getAudio() {
        return audio;
    }

    public static void setAudio(Audio audio) {
        AudioInListController.audio = audio;
    }

    public Text getTxt_audioNumber() {
        return txt_audioNumber;
    }

    public void setTxt_audioNumber(Text txt_audioNumber) {
        this.txt_audioNumber = txt_audioNumber;
    }

    public static boolean isNumOfLikes() {
        return numOfLikes;
    }

    public static void setNumOfLikes(boolean numOfPlaysOrLikes) {
        AudioInListController.numOfLikes = numOfPlaysOrLikes;
    }
}
