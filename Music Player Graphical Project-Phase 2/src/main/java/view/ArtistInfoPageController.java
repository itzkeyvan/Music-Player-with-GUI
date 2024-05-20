package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ArtistInfoPageController {

    @FXML
    private VBox VBox_audiosList;

    @FXML
    private ImageView imgView_PlayOrPauseInList;

    @FXML
    private Label lblBTn_Follow;

    @FXML
    private Label lblBTn_Report;

    @FXML
    private Rectangle rectangle_AudioCover;

    @FXML
    private Text txt_AudioType;

    @FXML
    private Text txt_NumberOfAudios;

    @FXML
    private Text txt_NumberOfTotalPlays;

    @FXML
    private Text txt_artistName;

    @FXML
    private Text txt_artistType;

    @FXML
    private Text txt_audioName1;

    @FXML
    private Text txt_audioNumberOfPlays1;

    @FXML
    private Text txt_biography;

    @FXML
    private Text txt_numberOfFollowers;

    @FXML
    void PlayOrPauseInList_Clicked(MouseEvent event) {

    }

    @FXML
    void followLblBtn_Clicked(MouseEvent event) {

    }

    @FXML
    void reportLblBtn_Clicked(MouseEvent event) {

    }

}
