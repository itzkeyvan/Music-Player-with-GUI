package graphic.musicplayergraphicalprojectphase2;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import controller.AdminController;
import controller.ListenerController;
import controller.PodcasterController;
import controller.SingerController;
import exceptions.InvalidFormatException;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Genre;
import model.userAccount.ArtistType;
import view.MainTemplateController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Main extends Application
{
    static private Stage stage;
    static private ArrayList<StringProperty> centerNodesHistory = new ArrayList<>();
    static private StringProperty currentCenterNode = new SimpleStringProperty();
    static private boolean loggedIn = false;

    @Override
    public void start(Stage stage) throws IOException
    {
        setStage(stage);
        Image logo = new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/AppLogo.png");
        Parent root = FXMLLoader.load(MainTemplateController.class.getResource("/graphic/musicplayergraphicalprojectphase2/mainTemplate.fxml"));
        MainTemplateController.centerPath.set("notLoggedInBackground");
        Scene scene = new Scene(root, 944, 682);
        getStage().setTitle("Music Player");
        getStage().getIcons().add(logo);
        getStage().setResizable(false);
        getStage().setScene(scene);
        getStage().show();
    }

    public static void main(String[] args) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            SingerController.getSingerController().signUp("VibeTracks", "vibeTRackssdas21!", "Vibe Tracks", "vibetracks@gmail.com", "09122334455", dateFormat.parse("1988-04-05"), "Vibe Tracks Artist Biography", ArtistType.SINGER,new Image("file:src/main/resources/audios/Vibe Tracks/pp.png"));
            SingerController.getSingerController().newAlbum("Vibing");
            SingerController.getSingerController().publishMusic("Universal", dateFormat.parse("2015-10-23"),Genre.POP,"Universal Music Lyrics","src/main/resources/audios/Vibe Tracks/Universal.mp3",new Image("file:src/main/resources/audios/Vibe Tracks/Universal.png"),"Vibe Tracks_Vibing");
            SingerController.getSingerController().publishMusic("Sugar", dateFormat.parse("2015-11-20"),Genre.POP,"Sugar Music Lyrics","src/main/resources/audios/Vibe Tracks/Sugar.mp3",new Image("file:src/main/resources/audios/Vibe Tracks/Sugar.png"),"Vibe Tracks_Vibing");
            SingerController.getSingerController().logout();

            SingerController.getSingerController().signUp("OtisMcDonald", "otisMcDonald2020!", "Otis McDonald", "otismcdonald@gmail.com", "09125224248", dateFormat.parse("1990-10-25"), "Otis McDonald Artist Biography", ArtistType.SINGER,new Image("file:src/main/resources/audios/Otis McDonald/Otis McDonald.png"));
            SingerController.getSingerController().newAlbum("After Hours");
            SingerController.getSingerController().publishMusic("Otis McDonald", dateFormat.parse("2018-10-20"),Genre.HIPHOP,"Otis McDonald Music Lyrics","src/main/resources/audios/Otis McDonald/Otis McDonald.mp3",new Image("file:src/main/resources/audios/Otis McDonald/Otis McDonald.png"),"Otis McDonald_After Hours");
            SingerController.getSingerController().logout();

            SingerController.getSingerController().signUp("SilentPartner", "SiLenTpartnEr122!", "Silent Partner", "silentpartner@gmail.com", "09122344232", dateFormat.parse("1995-02-12"), "Silent Partner Artist Biography", ArtistType.SINGER,new Image("file:src/main/resources/audios/Silent Partner/pp.png"));
            SingerController.getSingerController().newAlbum("Jazzination");
            SingerController.getSingerController().publishMusic("Chances", dateFormat.parse("2018-02-04"),Genre.JAZZ,"Chances Music Lyrics","src/main/resources/audios/Silent Partner/Chances.mp3",new Image("file:src/main/resources/audios/Silent Partner/Chances.png"),"Silent Partner_Jazzination");
            SingerController.getSingerController().logout();

            PodcasterController.getPodcasterController().signUp("NastelBom", "nastelBom2452!", "Nastel Bom", "nastelbom@gmail.com", "09123249582", dateFormat.parse("1992-02-05"), "Nastel Bom Podcaster Biography", ArtistType.PODCASTER,new Image("file:src/main/resources/audios/NastelBom/pp.png"));
            PodcasterController.getPodcasterController().publishPodcast("Podcast", dateFormat.parse("2024-02-18"),Genre.SOCIETY,"Podcast Caption","src/main/resources/audios/NastelBom/Podcast.mp3",new Image("file:src/main/resources/audios/NastelBom/Podcast.png"));
            PodcasterController.getPodcasterController().logout();

            ListenerController.getListenerController().signUp("test", "testPass2024!", "Test Account", "test@gmail.com", "09022222222", dateFormat.parse("2005-01-01"));
            String[] genres = {"Pop", "Hiphop", "Jazz", "Rock"};
            ListenerController.getListenerController().setFavouriteGenres(genres);

            AdminController.getAdminController().defineAdmin("admin", "adminPass2024!");
        } catch (ParseException|InvalidFormatException e) {
            e.printStackTrace();
        }
        launch();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(boolean loggedIn) {
        Main.loggedIn = loggedIn;
    }

    public static ArrayList<StringProperty> getCenterNodesHistory() {
        return centerNodesHistory;
    }

    public static void setCenterNodesHistory(ArrayList<StringProperty> centerNodesHistory) {
        Main.centerNodesHistory = centerNodesHistory;
    }

    public static StringProperty getCurrentCenterNode() {
        return currentCenterNode;
    }

    public static void setCurrentCenterNode(StringProperty currentCenterNode) {
        Main.currentCenterNode = currentCenterNode;
    }
}