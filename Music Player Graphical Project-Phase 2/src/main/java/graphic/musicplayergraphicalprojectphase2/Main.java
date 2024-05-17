package graphic.musicplayergraphicalprojectphase2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application
{
    static private Stage stage;
    static private Scene scene;
    @Override
    public void start(Stage stage) throws IOException
    {
        Image logo=new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/Spotify_App_Logo.svg.png");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("playBar.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Music PLayer");
        stage.getIcons().add(logo);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        Main.scene = scene;
    }
}