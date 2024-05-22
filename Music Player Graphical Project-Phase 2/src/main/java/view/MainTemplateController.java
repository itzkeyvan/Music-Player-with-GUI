package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainTemplateController {

    @FXML
    private static BorderPane BorderPane_mainTemplate;

    @FXML
    private HBox HBox_Search;

    @FXML
    private HBox btn_Playlists;

    @FXML
    private HBox btn_artists;

    @FXML
    private HBox btn_audios;

    @FXML
    private HBox btn_home;

    @FXML
    private HBox btn_library;

    @FXML
    private HBox btn_search;

    @FXML
    private ImageView imgView_Back;

    @FXML
    private ImageView imgView_Search;

    @FXML
    private Label lblBtn_Login;

    @FXML
    private Label lblBtn_Logout;

    @FXML
    private Label lblBtn_SignUp;

    @FXML
    private TextField textField_Search;

    @FXML
    void BackButton_Clicked(MouseEvent event) {

    }

    @FXML
    void artistsBtn_Clicked(MouseEvent event) {

    }

    @FXML
    void audiosBtn_Clicked(MouseEvent event) {

    }

    @FXML
    void homeBtn_Clicked(MouseEvent event) {

    }

    @FXML
    void libraryBtn_Clicked(MouseEvent event) {

    }

    @FXML
    void loginBtn_clicked(MouseEvent event) {

    }

    @FXML
    void logoutBtn_clicked(MouseEvent event) {

    }

    @FXML
    void playlistsBtn_Clicked(MouseEvent event) {

    }

    @FXML
    void searchBtn_Clicked(MouseEvent event) {

    }

    @FXML
    void signUpBtn_clicked(MouseEvent event) {

    }

    public static BorderPane getBorderPane_mainTemplate() {
        return BorderPane_mainTemplate;
    }

    public static void setBorderPane_mainTemplate(BorderPane borderPane_mainTemplate) {
        BorderPane_mainTemplate = borderPane_mainTemplate;
    }
}
