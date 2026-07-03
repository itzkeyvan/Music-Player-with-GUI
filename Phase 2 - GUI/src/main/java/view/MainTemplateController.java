package view;

import graphic.musicplayergraphicalprojectphase2.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import model.interfaces.GeneralOperations;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import model.audio.Audio;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainTemplateController implements Initializable, GeneralOperations {
    private static MainTemplateController controllerInstance;

    private static Audio currentAudio;
    private static ArrayList<Audio> currentPlaylist;
    public static StringProperty centerPath = new SimpleStringProperty();
    public static StringProperty bottomPath = new SimpleStringProperty();

    @FXML
    private BorderPane BorderPane_mainTemplate;

    @FXML
    private AnchorPane centerPane;

    @FXML
    private HBox HBox_Search;

    @FXML
    private ImageView imgViewBtn_Exit;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controllerInstance = this;

        centerPath.addListener((observable, oldValue, newValue) -> {
            try {
                // Skip if it's the refresh marker
                if ("_refresh_".equals(newValue)) {
                    return;
                }

                Parent parent = FXMLLoader.load(MainTemplateController.class.getResource("/graphic/musicplayergraphicalprojectphase2/" + newValue + ".fxml"));

                // Only add to history if it's not a duplicate of the last page
                if (Main.getCenterNodesHistory().isEmpty() ||
                        !Main.getCenterNodesHistory().get(Main.getCenterNodesHistory().size() - 1).getValue().equals(newValue)) {
                    Main.getCenterNodesHistory().add(new SimpleStringProperty(newValue));
                }

                Main.setCurrentCenterNode(new SimpleStringProperty(newValue));
                centerPane.getChildren().clear();
                AnchorPane.setTopAnchor(parent, 0.0);
                AnchorPane.setBottomAnchor(parent, 0.0);
                AnchorPane.setLeftAnchor(parent, 0.0);
                AnchorPane.setRightAnchor(parent, 0.0);
                centerPane.getChildren().add(parent);

                // Update back button state
                updateBackButtonState();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        bottomPath.addListener((observable, oldValue, newValue) -> {
            loadBottomBar(newValue);
        });

        updateLoginState();

        // Initialize back button state
        updateBackButtonState();

        // Load the playbar initially
        loadBottomBar("playBar");
    }

    private void loadBottomBar(String fxmlName) {
        try {
            Parent parent = FXMLLoader.load(PlayBarController.class.getResource("/graphic/musicplayergraphicalprojectphase2/" + fxmlName + ".fxml"));
            BorderPane_mainTemplate.setBottom(parent);

            // Set callback to refresh icons when audio changes
            PlayBarController.setOnAudioChangeCallback(() -> {
                AudioInListController.refreshAllIconsStatic();
                AudioInListController.updatePlaysDisplayForAllInstances();
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateBackButtonState() {
        // Enable back button if we have more than 1 page in history
        if (Main.getCenterNodesHistory().size() > 1) {
            imgView_Back.setDisable(false);
            imgView_Back.setOpacity(1.0);
        } else {
            imgView_Back.setDisable(true);
            imgView_Back.setOpacity(0.4);
        }
    }

    // ============ STATIC METHODS FOR OTHER CONTROLLERS ============

    public static void setAudio(Audio audio) {
        currentAudio = audio;
        PlayBarController.setAudio(audio);
    }

    public static void setAudiosList(ArrayList<Audio> playlist) {
        currentPlaylist = playlist;
        PlayBarController.setPlaylist(playlist);
    }

    public static Audio getAudio() {
        return currentAudio;
    }

    public static ArrayList<Audio> getAudiosList() {
        return currentPlaylist;
    }

    public static void togglePlayPauseStatic() {
        PlayBarController.togglePlayPause();
    }

    public static void playCurrentAudioStatic() {
        if (currentAudio != null) {
            PlayBarController.setAudio(currentAudio);
            PlayBarController.setPlaylist(currentPlaylist);
            PlayBarController.playAudio(currentAudio, currentPlaylist);
        }
    }

    public static boolean isPlayingStatic() {
        return PlayBarController.isPlaying();
    }

    public static void reloadCurrentPage() {
        String currentPage = centerPath.get();
        if (currentPage != null && !currentPage.isEmpty() && !currentPage.equals("_refresh_")) {
            // Remove current page from history to avoid duplicates
            if (!Main.getCenterNodesHistory().isEmpty()) {
                Main.getCenterNodesHistory().remove(Main.getCenterNodesHistory().size() - 1);
            }
            // Set the page again to reload it
            centerPath.set(currentPage);
        }
    }

    public static void resetPlaybar() {
        PlayBarController.resetPlaybar();
    }

    // ============ SIDE BAR METHODS ============

    @FXML
    void homeBtn_Clicked(MouseEvent event) {
        if (!Main.isLoggedIn()) {
            HBox_Search.setVisible(false);
            MainTemplateController.centerPath.set("notLoggedInBackground");
        } else {
            HBox_Search.setVisible(false);
            MainTemplateController.centerPath.set("homePage");
        }
    }

    @FXML
    void searchBtn_Clicked(MouseEvent event) {
        HBox_Search.setVisible(true);
    }

    @FXML
    void libraryBtn_Clicked(MouseEvent event) {
        HBox_Search.setVisible(false);
        MainTemplateController.centerPath.set("listenerPanel");
    }

    @FXML
    void audiosBtn_Clicked(MouseEvent event) {
        HBox_Search.setVisible(false);
        MainTemplateController.centerPath.set("allAudiosList");
    }

    @FXML
    void artistsBtn_Clicked(MouseEvent event) {
        HBox_Search.setVisible(false);
        MainTemplateController.centerPath.set("Artists");
    }

    // ============ TOP BAR METHODS ============

    @FXML
    void BackButton_Clicked(MouseEvent event) {
        backTo();
    }

    @FXML
    void exitImgViewBtn_Clicked(MouseEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Program Exit");
        alert.setHeaderText(null);
        alert.setContentText("Have a nice day!");
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/Exit.png"));
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                System.exit(0);
            }
        });
    }

    @FXML
    void searchImgViewBtn_clicked(MouseEvent event) {
        search();
    }

    @FXML
    void signUpBtn_clicked(MouseEvent event) {
        signup();
    }

    @FXML
    void loginBtn_clicked(MouseEvent event) {
        login();
    }

    @FXML
    void logoutBtn_clicked(MouseEvent event) {
        logout();
    }

    // ============ HELPER METHODS ============

    private void updateLoginState() {
        if (Main.isLoggedIn()) {
            btn_artists.setDisable(false);
            btn_audios.setDisable(false);
            btn_search.setDisable(false);
            btn_library.setDisable(false);
            lblBtn_SignUp.setDisable(true);
            lblBtn_Login.setDisable(true);
            lblBtn_Logout.setDisable(false);
        } else {
            btn_artists.setDisable(true);
            btn_audios.setDisable(true);
            btn_search.setDisable(true);
            btn_library.setDisable(true);
            lblBtn_SignUp.setDisable(false);
            lblBtn_Login.setDisable(false);
            lblBtn_Logout.setDisable(true);
        }
    }

    // ============ INTERFACE METHODS ============

    @Override
    public void backTo() {
        if (Main.getCenterNodesHistory().size() > 1) {
            // Remove the current page from history
            Main.getCenterNodesHistory().remove(Main.getCenterNodesHistory().size() - 1);

            // Get the previous page
            String previousPage = Main.getCenterNodesHistory().get(Main.getCenterNodesHistory().size() - 1).getValue();

            // Navigate to the previous page
            MainTemplateController.centerPath.set(previousPage);

            // Update back button state
            updateBackButtonState();
        }
    }

    @Override
    public void logout() {
        // Stop playback and reset
        PlayBarController.resetPlaybar();
        currentAudio = null;
        currentPlaylist = null;

        Main.setLoggedIn(false);
        Main.setCenterNodesHistory(new ArrayList<>());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Logged out successfully.");
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/Tick.png"));
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    Stage stage = Main.getStage();
                    double width = stage.getWidth();
                    double height = stage.getHeight();
                    Parent root = FXMLLoader.load(MainTemplateController.class.getResource("/graphic/musicplayergraphicalprojectphase2/mainTemplate.fxml"));
                    Scene scene = stage.getScene();
                    if (scene != null) {
                        scene.setRoot(root);
                    } else {
                        stage.setScene(new Scene(root, width, height));
                    }
                    MainTemplateController.centerPath.set("notLoggedInBackground");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public void login() {
        MainTemplateController.centerPath.set("loginPage");
    }

    @Override
    public void signup() {
        MainTemplateController.centerPath.set("signUpPage");
    }

    @Override
    public void search() {
        String searchText = textField_Search.getText();
        if (searchText == null || searchText.trim().isEmpty()) {
            return;
        }
        SearchPageController.setSearchedTerm(searchText.trim());
        MainTemplateController.centerPath.set("searchPage");
    }
}