package view;

import graphic.musicplayergraphicalprojectphase2.Main;
import interfaces.GeneralOperations;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.audio.Audio;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainTemplateController implements Initializable, GeneralOperations {
    private static Audio audio;
    private static ArrayList<Audio> audiosList;
    @FXML
    private static BorderPane BorderPane_mainTemplate;

    @FXML
    private HBox HBox_Search;

    @FXML
    private ImageView imgViewBtn_Exit;

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
    private ImageView btn_next;

    @FXML
    private ImageView btn_playORpause;

    @FXML
    private ImageView btn_previous;

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
    private Label lbl_artistName;

    @FXML
    private Label lbl_audioName;

    @FXML
    private Label lbl_audioTotalTime;

    @FXML
    private Label lbl_elapsedTime;

    @FXML
    private Rectangle rectangle_audioCover;

    @FXML
    private Slider slider_audioProgressBar;

    @FXML
    private TextField textField_Search;

    //Initialize-----------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        if(Main.isLoggedIn())
        {
            lblBtn_SignUp.setDisable(true);
            lblBtn_Login.setDisable(true);
            lblBtn_Logout.setDisable(false);
        }
        if(!Main.isLoggedIn())
        {
            lblBtn_SignUp.setDisable(false);
            lblBtn_Login.setDisable(false);
            lblBtn_Logout.setDisable(true);
        }
        setupMediaPlayer();
        setupSlider();
        setupAudioCover();
        if(Main.getCenterNodesHistory().size()==1)
            imgView_Back.setDisable(true);
        else
            imgView_Back.setDisable(false);
    }

    //Side Bar-------------------------------------------------------------------
    @FXML
    void homeBtn_Clicked(MouseEvent event)
    {
        HBox_Search.setVisible(false);
        try {
            Main.setCurrentCenterNode(FXMLLoader.load(MainTemplateController.class.getResource("homePage.fxml")));
            Main.getCenterNodesHistory().add(Main.getCurrentCenterNode());
            MainTemplateController.getBorderPane_mainTemplate().setCenter(Main.getCurrentCenterNode());
            Scene scene=new Scene(FXMLLoader.load(MainTemplateController.class.getResource("mainTemplate.fxml")));
            Main.getStage().setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void searchBtn_Clicked(MouseEvent event)
    {
        HBox_Search.setVisible(true);
    }

    @FXML
    void libraryBtn_Clicked(MouseEvent event)
    {
        HBox_Search.setVisible(false);
        try {
            Main.setCurrentCenterNode(FXMLLoader.load(MainTemplateController.class.getResource("listenerPanel.fxml")));
            Main.getCenterNodesHistory().add(Main.getCurrentCenterNode());
            MainTemplateController.getBorderPane_mainTemplate().setCenter(Main.getCurrentCenterNode());
            Scene scene=new Scene(FXMLLoader.load(MainTemplateController.class.getResource("mainTemplate.fxml")));
            Main.getStage().setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void audiosBtn_Clicked(MouseEvent event)
    {
        HBox_Search.setVisible(false);
        try {
            Main.setCurrentCenterNode(FXMLLoader.load(MainTemplateController.class.getResource("allAudiosList.fxml")));
            Main.getCenterNodesHistory().add(Main.getCurrentCenterNode());
            MainTemplateController.getBorderPane_mainTemplate().setCenter(Main.getCurrentCenterNode());
            Scene scene=new Scene(FXMLLoader.load(MainTemplateController.class.getResource("mainTemplate.fxml")));
            Main.getStage().setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void artistsBtn_Clicked(MouseEvent event)
    {
        HBox_Search.setVisible(false);
        try {
            Main.setCurrentCenterNode(FXMLLoader.load(MainTemplateController.class.getResource("listenerPanel.fxml")));
            Main.getCenterNodesHistory().add(Main.getCurrentCenterNode());
            MainTemplateController.getBorderPane_mainTemplate().setCenter(Main.getCurrentCenterNode());
            Scene scene=new Scene(FXMLLoader.load(MainTemplateController.class.getResource("mainTemplate.fxml")));
            Main.getStage().setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void playlistsBtn_Clicked(MouseEvent event)
    {
        HBox_Search.setVisible(false);
        try {
            Main.setCurrentCenterNode(FXMLLoader.load(ArtistsPageController.class.getResource("Artists.fxml")));
            Main.getCenterNodesHistory().add(Main.getCurrentCenterNode());
            MainTemplateController.getBorderPane_mainTemplate().setCenter(Main.getCurrentCenterNode());
            Scene scene=new Scene(FXMLLoader.load(MainTemplateController.class.getResource("mainTemplate.fxml")));
            Main.getStage().setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //------------------------------------------------------------
    //Top Bar-----------------------------------------------------
    @FXML
    void BackButton_Clicked(MouseEvent event)
    {
        backTo();
    }
    @FXML
    void exitImgViewBtn_Clicked(MouseEvent event)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Program Exit");
        alert.setHeaderText(null);
        alert.setContentText("Have a good day!");
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/Exit.png"));
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                System.exit(0);
            }
        });
    }

    @FXML
    void searchImgViewBtn_clicked(MouseEvent event)
    {
        search();
    }

    @FXML
    void signUpBtn_clicked(MouseEvent event)
    {
        signup();
    }

    @FXML
    void loginBtn_clicked(MouseEvent event)
    {
        login();
    }

    @FXML
    void logoutBtn_clicked(MouseEvent event)
    {
        logout();
    }
    //---------------------------------------------------------
    //Play Bar-------------------------------------------------

    @FXML
    void nextBtn_clicked(MouseEvent event) {
        playNextAudio();
    }

    @FXML
    void previousBtn_clicked(MouseEvent event) {
        playPreviousAudio();
    }

    @FXML
    void playORpauseBtn_clicked(MouseEvent event) {
        togglePlayPause();
    }

    @FXML
    void playORpause_keyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            togglePlayPause();
        }
    }

    private MediaPlayer mediaPlayer;
    private boolean atEndOfMedia = false;

    private void setupMediaPlayer() {
        Media media = new Media(getAudio().getAudioURL());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnReady(() -> {
            Duration totalDuration = mediaPlayer.getTotalDuration();
            slider_audioProgressBar.setMax(totalDuration.toSeconds());
            lbl_audioTotalTime.setText(formatTime(totalDuration));
        });

        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            if (!slider_audioProgressBar.isValueChanging()) {
                slider_audioProgressBar.setValue(newValue.toSeconds());
            }
            lbl_elapsedTime.setText(formatTime(newValue));
        });

        mediaPlayer.setOnEndOfMedia(() -> {
            atEndOfMedia = true;
            playNextAudio();
        });
    }

    private void setupSlider() {
        slider_audioProgressBar.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (slider_audioProgressBar.isValueChanging()) {
                mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
            }
        });
    }

    private void setupAudioCover() {
        if (audio.getCover() != null) {
            rectangle_audioCover.setFill(new ImagePattern(getAudio().getCover()));
        } else {
            rectangle_audioCover.setFill(new ImagePattern(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/defaultAudioCover.png")));
        }
    }

    private void playNextAudio() {
        int currentIndex = audiosList.indexOf(getAudio());
        if (currentIndex == audiosList.size() - 1) { // at the end of the list
            setAudio(audiosList.get(0)); // play the first audio in the list
        } else {
            setAudio(audiosList.get(currentIndex + 1)); // play the next audio
        }
        reloadAudio();
    }

    private void playPreviousAudio() {
        int currentIndex = audiosList.indexOf(getAudio());
        if (currentIndex == 0) { // at the start of the list
            setAudio(audiosList.get(audiosList.size() - 1)); // play the last audio in the list
        } else {
            setAudio(audiosList.get(currentIndex - 1)); // play the previous audio
        }
        reloadAudio();
    }

    private void togglePlayPause() {
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
            btn_playORpause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Play.png"));
        } else {
            if (atEndOfMedia) {
                mediaPlayer.seek(Duration.ZERO);
                atEndOfMedia = false;
            }
            mediaPlayer.play();
            btn_playORpause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Pause.png"));
        }
    }

    private void reloadAudio() {
        mediaPlayer.stop();
        setupMediaPlayer();
        mediaPlayer.play();
        lbl_audioName.setText(getAudio().getAudioName());
        lbl_artistName.setText(getAudio().getArtistName());
        setupAudioCover();
        btn_playORpause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Pause.png"));
    }

    private String formatTime(Duration duration) {
        int intSeconds = (int) Math.floor(duration.toSeconds());
        int minutes = intSeconds / 60;
        int seconds = intSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
//------------------------------------------------------------
    public static Audio getAudio() {
        return audio;
    }

    public static void setAudio(Audio audio) {
        MainTemplateController.audio = audio;
    }

    public static ArrayList<Audio> getAudiosList() {
        return audiosList;
    }

    public static void setAudiosList(ArrayList<Audio> audiosList) {
        MainTemplateController.audiosList = audiosList;
    }

    public static BorderPane getBorderPane_mainTemplate() {
        return BorderPane_mainTemplate;
    }

    public static void setBorderPane_mainTemplate(BorderPane borderPane_mainTemplate) {
        BorderPane_mainTemplate = borderPane_mainTemplate;
    }

    @Override
    public void backTo()
    {
        if(Main.getCenterNodesHistory().size()!=1)
            MainTemplateController.getBorderPane_mainTemplate().setCenter(Main.getCenterNodesHistory().get(Main.getCenterNodesHistory().indexOf(Main.getCurrentCenterNode())-1));
        try {
            Scene scene=new Scene(FXMLLoader.load(MainTemplateController.class.getResource("mainTemplate.fxml")));
            Main.getStage().setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void logout()
    {
        Main.setLoggedIn(false);
        try {
            Main.setCurrentCenterNode(FXMLLoader.load(MainTemplateController.class.getResource("homePage.fxml")));
            Main.getCenterNodesHistory().add(Main.getCurrentCenterNode());
            MainTemplateController.getBorderPane_mainTemplate().setCenter(Main.getCurrentCenterNode());
            Scene scene=new Scene(FXMLLoader.load(MainTemplateController.class.getResource("mainTemplate.fxml")));
            Main.getStage().setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void login()
    {
        try {
            Main.setCurrentCenterNode(FXMLLoader.load(MainTemplateController.class.getResource("loginPage.fxml")));
            Main.getCenterNodesHistory().add(Main.getCurrentCenterNode());
            MainTemplateController.getBorderPane_mainTemplate().setCenter(Main.getCurrentCenterNode());
            Scene scene=new Scene(FXMLLoader.load(MainTemplateController.class.getResource("mainTemplate.fxml")));
            Main.getStage().setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void signup()
    {
        try {
            Main.setCurrentCenterNode(FXMLLoader.load(MainTemplateController.class.getResource("signUpPage.fxml")));
            Main.getCenterNodesHistory().add(Main.getCurrentCenterNode());
            MainTemplateController.getBorderPane_mainTemplate().setCenter(Main.getCurrentCenterNode());
            Scene scene=new Scene(FXMLLoader.load(MainTemplateController.class.getResource("mainTemplate.fxml")));
            Main.getStage().setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void search()
    {
        try {
            SearchPageController.setSearchedTerm(textField_Search.getText());
            Main.setCurrentCenterNode(FXMLLoader.load(MainTemplateController.class.getResource("searchPage.fxml")));
            Main.getCenterNodesHistory().add(Main.getCurrentCenterNode());
            MainTemplateController.getBorderPane_mainTemplate().setCenter(Main.getCurrentCenterNode());
            Scene scene=new Scene(FXMLLoader.load(MainTemplateController.class.getResource("mainTemplate.fxml")));
            Main.getStage().setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
