package view;

import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.Parent;
import model.interfaces.GeneralOperations;
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
    public  static StringProperty centerPath=new SimpleStringProperty();
    public  static StringProperty bottomPath=new SimpleStringProperty();

    @FXML
    private BorderPane BorderPane_mainTemplate;

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
        centerPath.addListener((observable, oldValue, newValue) -> {
            try {
                Parent parent = FXMLLoader.load(MainTemplateController.class.getResource("/graphic/musicplayergraphicalprojectphase2/" + newValue + ".fxml"));
                Main.getCenterNodesHistory().add(new SimpleStringProperty(newValue));
                Main.setCurrentCenterNode(new SimpleStringProperty(newValue));
                BorderPane_mainTemplate.setCenter(parent);
                if(Main.getCenterNodesHistory().size()>=1)
                {
                    imgView_Back.setDisable(false);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        bottomPath.addListener((observable, oldValue, newValue) -> {
            try {
                Parent parent = FXMLLoader.load(PlayBarController.class.getResource("/graphic/musicplayergraphicalprojectphase2/" + newValue + ".fxml"));
                BorderPane_mainTemplate.setBottom(parent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        if(Main.isLoggedIn())
        {
            btn_artists.setDisable(false);
            btn_audios.setDisable(false);
            btn_search.setDisable(false);
            btn_Playlists.setDisable(false);
            btn_library.setDisable(false);
            lblBtn_SignUp.setDisable(true);
            lblBtn_Login.setDisable(true);
            lblBtn_Logout.setDisable(false);
            btn_Playlists.setDisable(false);
            btn_library.setDisable(false);
        }
        else
        {
            btn_artists.setDisable(true);
            btn_audios.setDisable(true);
            btn_search.setDisable(true);
            btn_Playlists.setDisable(true);
            btn_library.setDisable(true);
            lblBtn_SignUp.setDisable(false);
            lblBtn_Login.setDisable(false);
            lblBtn_Logout.setDisable(true);
        }
        if(audio!=null)
        {
            btn_next.setDisable(false);
            btn_previous.setDisable(false);
            btn_playORpause.setDisable(false);
            setupMediaPlayer();
            setupSlider();
            setupAudioCover();
        }
        else
        {
            rectangle_audioCover.setFill(new ImagePattern(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/DefaultAudioCover.png")));
            btn_playORpause.setImage(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Play.png"));
            btn_playORpause.setDisable(true);
            btn_next.setDisable(true);
            btn_previous.setDisable(true);
        }
        if(Main.getCenterNodesHistory().size()<=1)
            imgView_Back.setDisable(true);
        else
            imgView_Back.setDisable(false);
    }

    //Side Bar-------------------------------------------------------------------
    @FXML
    void homeBtn_Clicked(MouseEvent event)
    {
        if(!Main.isLoggedIn())
        {
            HBox_Search.setVisible(false);
            MainTemplateController.centerPath.set("notLoggedInBackground");
        }
        else
        {
            HBox_Search.setVisible(false);
            MainTemplateController.centerPath.set("homePage");
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
        MainTemplateController.centerPath.set("listenerPanel");
    }

    @FXML
    void audiosBtn_Clicked(MouseEvent event)
    {
        HBox_Search.setVisible(false);
        MainTemplateController.centerPath.set("allAudiosList");
    }

    @FXML
    void artistsBtn_Clicked(MouseEvent event)
    {
        HBox_Search.setVisible(false);
        MainTemplateController.centerPath.set("Artists");
    }
    @FXML
    void playlistsBtn_Clicked(MouseEvent event)
    {
        HBox_Search.setVisible(false);
        MainTemplateController.centerPath.set("listenerPanel");
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
        Media media = new Media(AudioInListController.getAudio().getAudioURL());
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
        if (currentIndex == audiosList.size() - 1) {
            setAudio(audiosList.get(0));
        } else {
            setAudio(audiosList.get(currentIndex + 1));
        }
        reloadAudio();
    }

    private void playPreviousAudio() {
        int currentIndex = audiosList.indexOf(getAudio());
        if (currentIndex == 0) { // at the start of the list
            setAudio(audiosList.get(audiosList.size() - 1));
        } else {
            setAudio(audiosList.get(currentIndex - 1));
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

    public BorderPane getBorderPane_mainTemplate() {
        return BorderPane_mainTemplate;
    }

    public void setBorderPane_mainTemplate(BorderPane borderPane_mainTemplate) {
        BorderPane_mainTemplate = borderPane_mainTemplate;
    }

    public HBox getBtn_library() {
        return btn_library;
    }

    public HBox getBtn_Playlists() {
        return btn_Playlists;
    }

    @Override
    public void backTo()
    {
        if(Main.getCenterNodesHistory().size()>1)
        {
            for (int i = Main.getCenterNodesHistory().size()-1; i >=0; i--)
            {
                if(Main.getCenterNodesHistory().get(i).equals(Main.getCurrentCenterNode()))
                    MainTemplateController.centerPath.set(String.valueOf(Main.getCenterNodesHistory().get(i-1)));

            }
        }
    }

    @Override
    public void logout()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Logged out successfully.");
        Main.setLoggedIn(false);
        Main.setCenterNodesHistory(new ArrayList<>());
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Tick.png"));
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    Parent root=FXMLLoader.load(MainTemplateController.class.getResource("/graphic/musicplayergraphicalprojectphase2/mainTemplate.fxml"));
                    Main.getStage().setScene(new Scene(root,745, 547));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                MainTemplateController.centerPath.set("notLoggedInBackground");
            }
        });
    }

    @Override
    public void login()
    {
        MainTemplateController.centerPath.set("loginPage");
    }

    @Override
    public void signup()
    {
        MainTemplateController.centerPath.set("signUpPage");
    }

    @Override
    public void search()
    {
        SearchPageController.setSearchedTerm(textField_Search.getText());
        MainTemplateController.centerPath.set("searchPage");
    }
}
