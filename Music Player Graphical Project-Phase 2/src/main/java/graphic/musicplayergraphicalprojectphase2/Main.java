package graphic.musicplayergraphicalprojectphase2;

import controller.PodcasterController;
import controller.SingerController;
import exceptions.InvalidFormatException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.DataBase;
import model.userAccount.ArtistType;
import model.userAccount.artist.Podcaster;
import model.userAccount.artist.Singer;
import view.HomePageController;
import view.MainTemplateController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Main extends Application
{
    static private Stage stage;
    static private Scene scene;
    static private Parent centerNode;
    static private boolean loggedIn = false;
    @Override
    public void start(Stage stage) throws IOException
    {
        setStage(stage);
        Image logo=new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/SpotifyLogo.png");
        setCenterNode(FXMLLoader.load(HomePageController.class.getResource("homePage.fxml")));
        BorderPane mainTemplate= FXMLLoader.load(MainTemplateController.class.getResource("mainTemplate.fxml"));
        mainTemplate.setCenter(centerNode);
        Scene scene = new Scene(mainTemplate, 745, 509);
        setScene(scene);
        getStage().setTitle("Music PLayer");
        getStage().getIcons().add(logo);
        getStage().setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            SingerController.getSingerController().signUp("BeyonceKnowles", "queenB123!", "Beyoncé Knowles", "beyonce@gmail.com", "09123456789", dateFormat.parse("1981-09-04"), "Beyoncé Giselle Knowles-Carter is an American singer, songwriter, and actress.", ArtistType.SINGER);
            SingerController.getSingerController().signUp("BeyonceKnowles", "queenB123!", "Beyoncé Knowles", "beyonce@gmail.com", "09123456789", dateFormat.parse("1981-09-04"), "Beyoncé Giselle Knowles-Carter is an American singer, songwriter, and actress.", ArtistType.SINGER);
            SingerController.getSingerController().signUp("AdeleAdkins", "adele21!", "Adele Adkins", "adele@gmail.com", "09122334455", dateFormat.parse("1988-05-05"), "Adele Laurie Blue Adkins is an English singer and songwriter.", ArtistType.SINGER);
            SingerController.getSingerController().signUp("EdSheeran", "edSheeran1!", "Ed Sheeran", "edsheeran@gmail.com", "09123456780", dateFormat.parse("1991-02-17"), "Edward Christopher Sheeran is an English singer-songwriter.", ArtistType.SINGER);
            SingerController.getSingerController().signUp("ArianaGrande", "arianaG123!", "Ariana Grande", "arianagrande@gmail.com", "09122334456", dateFormat.parse("1993-06-26"), "Ariana Grande-Butera is an American singer and actress.", ArtistType.SINGER);
            SingerController.getSingerController().signUp("JustinBieber", "justinB22!", "Justin Bieber", "justinbieber@gmail.com", "09121112222", dateFormat.parse("1994-03-01"), "Justin Drew Bieber is a Canadian singer and songwriter.", ArtistType.SINGER);
            SingerController.getSingerController().signUp("KatyPerry", "katyP123!", "Katy Perry", "katyperry@gmail.com", "09123334444", dateFormat.parse("1984-10-25"), "Katheryn Elizabeth Hudson, known professionally as Katy Perry, is an American singer, songwriter, and television judge.", ArtistType.SINGER);
            SingerController.getSingerController().signUp("BrunoMars", "brunoM123!", "Bruno Mars", "brunomars@gmail.com", "09121113333", dateFormat.parse("1985-10-08"), "Peter Gene Hernandez, known professionally as Bruno Mars, is an American singer, songwriter, record producer, and dancer.", ArtistType.SINGER);
            SingerController.getSingerController().signUp("LadyGaga", "ladyGaga123!", "Lady Gaga", "ladygaga@gmail.com", "09121114444", dateFormat.parse("1986-03-28"), "Stefani Joanne Angelina Germanotta, known professionally as Lady Gaga, is an American singer, songwriter, and actress.", ArtistType.SINGER);
            SingerController.getSingerController().signUp("TaylorSwift", "taylorSwift22!", "Taylor Swift", "taylorswift@gmail.com", "09121111111", dateFormat.parse("1989-12-13"), "Taylor Alison Swift is an American singer-songwriter.", ArtistType.SINGER);
            SingerController.getSingerController().signUp("RihannaFenty", "rihannaR123!", "Rihanna Fenty", "rihanna@gmail.com", "09123335555", dateFormat.parse("1988-02-20"), "Robyn Rihanna Fenty is a Barbadian singer, actress, and businesswoman.", ArtistType.SINGER);
            SingerController.getSingerController().signUp("DrakeGraham", "drakeD123!", "Drake Graham", "drake@gmail.com", "09123336666", dateFormat.parse("1986-10-24"), "Aubrey Drake Graham is a Canadian rapper, singer, songwriter, and actor.", ArtistType.SINGER);
            SingerController.getSingerController().signUp("BillieEilish", "billieE123!", "Billie Eilish", "billieeilish@gmail.com", "09123337777", dateFormat.parse("2001-12-18"), "Billie Eilish Pirate Baird O'Connell is an American singer-songwriter.", ArtistType.SINGER);
            SingerController.getSingerController().signUp("ShakiraMebarak", "shakiraS123!", "Shakira Mebarak", "shakira@gmail.com", "09123338888", dateFormat.parse("1977-02-02"), "Shakira Isabel Mebarak Ripoll is a Colombian singer and songwriter.", ArtistType.SINGER);
            SingerController.getSingerController().signUp("TheWeeknd", "weekndT123!", "The Weeknd", "theweeknd@gmail.com", "09123339999", dateFormat.parse("1990-02-16"), "Abel Makkonen Tesfaye, known professionally as The Weeknd, is a Canadian singer, songwriter, and record producer.", ArtistType.SINGER);
            SingerController.getSingerController().signUp("SelenaGomez", "selenaG123!", "Selena Gomez", "selenagomez@gmail.com", "09123330000", dateFormat.parse("1992-07-22"), "Selena Marie Gomez is an American singer, songwriter, and actress.", ArtistType.SINGER);
            SingerController.getSingerController().signUp("PostMalone", "postM123!", "Post Malone", "postmalone@gmail.com", "09123331111", dateFormat.parse("1995-07-04"), "Austin Richard Post, known professionally as Post Malone, is an American rapper, singer, and songwriter.", ArtistType.SINGER);
            SingerController.getSingerController().signUp("DuaLipa", "duaL123!", "Dua Lipa", "dualipa@gmail.com", "09123332222", dateFormat.parse("1995-08-22"), "Dua Lipa is an English singer and songwriter.", ArtistType.SINGER);
            SingerController.getSingerController().signUp("ShawnMendes", "shawnM123!", "Shawn Mendes", "shawnmendes@gmail.com", "09123333333", dateFormat.parse("1998-08-08"), "Shawn Peter Raul Mendes is a Canadian singer and songwriter.", ArtistType.SINGER);
            SingerController.getSingerController().signUp("CamilaCabello", "camilaC123!", "Camila Cabello", "camilacabello@gmail.com", "09123334444", dateFormat.parse("1997-03-03"), "Karla Camila Cabello Estrabao is a Cuban-American singer and songwriter.", ArtistType.SINGER);
            SingerController.getSingerController().signUp("LizzoBeating", "lizzo123!", "Lizzo Beating", "lizzo@gmail.com", "09123335555", dateFormat.parse("1988-04-27"), "Melissa Viviane Jefferson, known professionally as Lizzo, is an American singer, rapper, and songwriter.", ArtistType.SINGER);
            PodcasterController.getPodcasterController().signUp("JoeRogan", "joeRogan123!", "Joe Rogan", "joerogan@gmail.com", "09121119999", dateFormat.parse("1967-08-11"), "Joe Rogan is an American comedian, podcast host, and mixed martial arts commentator.", ArtistType.PODCASTER);
            PodcasterController.getPodcasterController().signUp("MarcMaron", "marcMaron123!", "Marc Maron", "marcmaron@gmail.com", "09121118888", dateFormat.parse("1963-09-27"), "Marc David Maron is an American stand-up comedian, podcaster, writer, and actor.", ArtistType.PODCASTER);
            PodcasterController.getPodcasterController().signUp("SarahKoenig", "sarahKoenig123!", "Sarah Koenig", "sarahkoenig@gmail.com", "09121117777", dateFormat.parse("1969-07-09"), "Sarah Koenig is an American journalist, public radio personality, and the host of the podcast Serial.", ArtistType.PODCASTER);
            PodcasterController.getPodcasterController().signUp("DanCarlin", "danCarlin123!", "Dan Carlin", "dancarlin@gmail.com", "09121116666", dateFormat.parse("1965-11-14"), "Dan Carlin is an American political commentator and podcaster.", ArtistType.PODCASTER);
            PodcasterController.getPodcasterController().signUp("IraGlass", "iraGlass123!", "Ira Glass", "iraglass@gmail.com", "09121115555", dateFormat.parse("1959-03-03"), "Ira Jeffrey Glass is an American public radio personality and the host and producer of the radio and television show This American Life.", ArtistType.PODCASTER);
            PodcasterController.getPodcasterController().signUp("RomanMars", "romanMars123!", "Roman Mars", "romanmars@gmail.com", "09121114444", dateFormat.parse("1974-10-16"), "Roman Mars is an American radio producer and host of the podcast 99% Invisible.", ArtistType.PODCASTER);
            PodcasterController.getPodcasterController().signUp("PhoebeJudge", "phoebeJudge123!", "Phoebe Judge", "phoebejudge@gmail.com", "09121113333", dateFormat.parse("1983-09-02"), "Phoebe Judge is an American journalist, best known as the host and co-creator of the podcast Criminal.", ArtistType.PODCASTER);
            PodcasterController.getPodcasterController().signUp("GuyRaz", "guyRaz123!", "Guy Raz", "guyraz@gmail.com", "09121112222", dateFormat.parse("1975-11-09"), "Guy Raz is an American journalist, correspondent, and radio host.", ArtistType.PODCASTER);
            PodcasterController.getPodcasterController().signUp("AnnaFaris", "annaFaris123!", "Anna Faris", "annafaris@gmail.com", "09121111111", dateFormat.parse("1976-11-29"), "Anna Kay Faris is an American actress, podcaster, and writer.", ArtistType.PODCASTER);
            PodcasterController.getPodcasterController().signUp("AlexBlumberg", "alexBlumberg123!", "Alex Blumberg", "alexblumberg@gmail.com", "09121110000", dateFormat.parse("1970-07-03"), "Alex Blumberg is an American entrepreneur, radio journalist, and CEO of Gimlet Media.", ArtistType.PODCASTER);
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

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        Main.scene = scene;
    }

    public static Parent getCenterNode() {
        return centerNode;
    }

    public static void setCenterNode(Parent centerNode) {
        Main.centerNode = centerNode;
    }

    public static boolean isLoggedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(boolean loggedIn) {
        Main.loggedIn = loggedIn;
    }
}