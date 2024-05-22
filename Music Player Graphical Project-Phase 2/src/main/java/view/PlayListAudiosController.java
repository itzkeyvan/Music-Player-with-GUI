package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class PlayListAudiosController {

    @FXML
    private Label Btn_DeletePlaylist;

    @FXML
    private VBox VBox_audiosList;

    @FXML
    private Rectangle rectangle_AudioCover;

    @FXML
    private Text txt_AlbumTotalLength;

    @FXML
    private Text txt_CreatorUserName;

    @FXML
    private Text txt_NumberOfSongs;

    @FXML
    private Text txt_PlayListName;

    @FXML
    private Text txt_artistName1;

    @FXML
    private Text txt_audioName1;

    @FXML
    private Text txt_audioNumberOfPlays1;

    @FXML
    void PlayOrPauseInList_Clicked(MouseEvent event) {

    }

    @FXML
    void deletePlaylistBtn_Clicked(MouseEvent event) {

    }

}
