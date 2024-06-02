package graphic.musicplayergraphicalprojectphase2;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import controller.PodcasterController;
import controller.SingerController;
import exceptions.InvalidFormatException;
import javafx.application.Application;
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
    static private ArrayList<StringProperty> centerNodesHistory=new ArrayList<>();
    static private StringProperty currentCenterNode;
    static private boolean loggedIn = false;

    @Override
    public void start(Stage stage) throws IOException
    {

        setStage(stage);
        Image logo=new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/SpotifyLogo.png");
        Parent root=FXMLLoader.load(MainTemplateController.class.getResource("/graphic/musicplayergraphicalprojectphase2/mainTemplate.fxml"));
        MainTemplateController.centerPath.set("notLoggedInBackground");
        Scene scene = new Scene(root, 745, 547);
        getStage().setTitle("Music Player");
        getStage().getIcons().add(logo);
        getStage().setScene(scene);
        getStage().show();
    }

    public static void main(String[] args) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            SingerController.getSingerController().signUp("AdeleAdkins", "adeldSdSdadsade21!", "Adele Adkins", "adele@gmail.com", "09122334455", dateFormat.parse("1988-05-05"), "Adele Laurie Blue Adkins is an English singer and songwriter.", ArtistType.SINGER,new Image("file:src/main/resources/audios/Adele/pp.png"));
            SingerController.getSingerController().newAlbum("25");
            SingerController.getSingerController().publishMusic("Hello", Genre.POP,"Hello, it's me I was wondering if after all these years you'd like to meet To go over everything They say that time's supposed to heal ya, but I ain't done much healing Hello, can you hear me? I'm in California dreaming about who we used to be When we were younger and free I've forgotten how it felt before the world fell at our feet There's such a difference between us And a million miles Hello from the other side I must've called a thousand times To tell you I'm sorry for everything that I've done But when I call, you never seem to be home Hello from the outside At least I can say that I've tried To tell you I'm sorry for breaking your heart But it don't matter, it clearly doesn't tear you apart anymore Hello, how are you? It's so typical of me to talk about myself, I'm sorry I hope that you're well Did you ever make it out of that town where nothing ever happened? It's no secret that the both of us Are running out of time So hello from the other side (other side) I must've called a thousand times (thousand times) To tell you I'm sorry for everything that I've done But when I call, you never seem to be home Hello from the outside (outside) At least I can say that I've tried (I've tried) To tell you I'm sorry for breaking your heart But it don't matter, it clearly doesn't tear you apart anymore Ooh (lows, lows, lows, lows), anymore (Highs, highs, highs, highs) Ooh (lows, lows, lows, lows), anymore (Highs, highs, highs, highs) Ooh (lows, lows, lows, lows), anymore (Highs, highs, highs, highs) Anymore (lows, lows, lows, lows) Hello from the other side (other side) I must've called a thousand times (thousand times) To tell you I'm sorry for everything that I've done But when I call, you never seem to be home Hello from the outside (outside) At least I can say that I've tried (I've tried) To tell you I'm sorry for breaking your heart But it don't matter, it clearly doesn't tear you apart anymore","src/main/resources/audios/Adele/Hello.mp3",new Image("file:src/main/resources/audios/Adele/Hello.jpg"),"Adele Adkins_25");
            SingerController.getSingerController().publishMusic("When We Were Young", Genre.POP,"Everybody loves the things you do From the way you talk To the way you move Everybody here is watching you 'Cause you feel like home You're like a dream come true But if by chance you're here alone Can I have a moment? Before I go? 'Cause I've been by myself all night long Hoping you're someone I used to know You look like a movie You sound like a song My God this reminds me, of when we were young Let me photograph you in this light In case it is the last time That we might be exactly like we were Before we realized We were scared of getting old It made us restless It was just like a movie It was just like a song I was so scared to face my fears Nobody told me that you'd be here And I'd swear you moved overseas That's what you said, when you left me You still look like a movie You still sound like a song My God, this reminds me, of when we were young Let me photograph you in this light In case it is the last time That we might be exactly like we were Before we realized We were sad of getting old It made us restless It was just like a movie It was just like a song When we were young (When we were young) When we were young (When we were young) It's hard to win me back Everything just takes me back To when you were there To when you were there And a part of me keeps holding on Just in case it hasn't gone I guess I still care Do you still care? It was just like a movie It was just like a song My God, this reminds me Of when we were young When we were young (When we were young) When we were young (When we were young) Let me photograph you in this light In case it is the last time That we might be exactly like we were Before we realized We were sad of getting old It made us restless Oh I'm so mad I'm getting old It makes me reckless It was just like a movie It was just like a song When we were young","src/main/resources/audios/Adele/When We Were Young.mp3",new Image("file:src/main/resources/audios/Adele/wwwy.jpg"),"Adele Adkins_25");
            SingerController.getSingerController().logout();

            SingerController.getSingerController().signUp("EdSheeran", "edSheSean231!", "Ed Sheeran", "edsheeran@gmail.com", "09123456780", dateFormat.parse("1991-02-17"), "Edward Christopher Sheeran is an English singer-songwriter.", ArtistType.SINGER,new Image("file:src/main/resources/audios/Ed Sheeran/pp.jpg"));
            SingerController.getSingerController().newAlbum("÷");
            SingerController.getSingerController().publishMusic("Perfect", Genre.POP,"I found a love, for me Darling, just dive right in and follow my lead Well, I found a girl, beautiful and sweet Oh, I never knew you were the someone waiting for me 'Cause we were just kids when we fell in love Not knowing what it was I will not give you up this time But darling, just kiss me slow Your heart is all I own And in your eyes, you're holding mine Baby, I'm dancing in the dark With you between my arms Barefoot on the grass Listening to our favourite song When you said you looked a mess I whispered underneath my breath But you heard it Darling, you look perfect tonight Well, I found a woman, stronger than anyone I know She shares my dreams, I hope that someday I'll share her home I found a lover, to carry more than just my secrets To carry love, to carry children of our own We are still kids, but we're so in love Fighting against all odds I know we'll be alright this time Darling, just hold my hand Be my girl, I'll be your man I see my future in your eyes Baby, I'm dancing in the dark With you between my arms Barefoot on the grass Listening to our favorite song When I saw you in that dress, looking so beautiful I don't deserve this Darling, you look perfect tonight Baby, I'm dancing in the dark With you between my arms Barefoot on the grass Listening to our favorite song I have faith in what I see Now I know I have met an angel in person And she looks perfect I don't deserve this You look perfect tonight","src/main/resources/audios/Ed Sheeran/Perfect.mp3",new Image("file:src/main/resources/audios/Ed Sheeran/p.jpg"),"Ed Sheeran_÷");
            SingerController.getSingerController().publishMusic("Shape of you", Genre.POP,"The club isn't the best place to find a lover So the bar is where I go Me and my friends at the table doing shots Drinking fast and then we talk slow Come over and start up a conversation with just me And trust me I'll give it a chance now Take my hand, stop, put Van the Man on the jukebox And then we start to dance, and now I'm singing like Girl, you know I want your love Your love was handmade for somebody like me Come on now, follow my lead I may be crazy, don't mind me Say, boy, let's not talk too much Grab on my waist and put that body on me Come on now, follow my lead Come, come on now, follow my lead I'm in love with the shape of you We push and pull like a magnet do Although my heart is falling too I'm in love with your body And last night you were in my room And now my bedsheets smell like you Every day discovering something brand new I'm in love with your body (Oh-I-oh-I-oh-I-oh-I) I'm in love with your body (Oh-I-oh-I-oh-I-oh-I) I'm in love with your body (Oh-I-oh-I-oh-I-oh-I) I'm in love with your body Every day discovering something brand new I'm in love with the shape of you One week in we let the story begin We're going out on our first date You and me are thrifty, so go all you can eat Fill up your bag and I fill up a plate We talk for hours and hours about the sweet and the sour And how your family is doing okay And leave and get in a taxi, then kiss in the backseat Tell the driver make the radio play, and I'm singing like Girl, you know I want your love Your love was handmade for somebody like me Come on now, follow my lead I may be crazy, don't mind me Say, boy, let's not talk too much Grab on my waist and put that body on me Come on now, follow my lead Come, come on now, follow my lead I'm in love with the shape of you We push and pull like a magnet do Although my heart is falling too I'm in love with your body And last night you were in my room And now my bedsheets smell like you Every day discovering something brand new I'm in love with your body (Oh-I-oh-I-oh-I-oh-I) I'm in love with your body (Oh-I-oh-I-oh-I-oh-I) I'm in love with your body (Oh-I-oh-I-oh-I-oh-I) I'm in love with your body Every day discovering something brand new I'm in love with the shape of you Come on, be my baby, come on Come on, be my baby, come on Come on, be my baby, come on Come on, be my baby, come on Come on, be my baby, come on Come on, be my baby, come on Come on, be my baby, come on Come on, be my baby, come on I'm in love with the shape of you We push and pull like a magnet do Although my heart is falling too I'm in love with your body And last night you were in my room And now my bedsheets smell like you Every day discovering something brand new I'm in love with your body Come on, be my baby, come on Come on (I'm in love with your body), be my baby, come on Come on, be my baby, come on Come on (I'm in love with your body), be my baby, come on Come on, be my baby, come on Come on (I'm in love with your body), be my baby, come on Every day discovering something brand new I'm in love with the shape of you","src/main/resources/audios/Ed Sheeran/Shape Of You.mp3",new Image("file:src/main/resources/audios/Ed Sheeran/soy.jpg"),"Ed Sheeran_÷");
            SingerController.getSingerController().logout();

            SingerController.getSingerController().signUp("BillieJoeArmstrong", "greendSSay123", "Billie Joe Armstrong", "billiejoe@gmail.com", "09334455667", dateFormat.parse("1972-02-17"), "Billie Joe Armstrong is an American singer, songwriter, and guitarist, known as the lead vocalist and guitarist of the rock band Green Day.", ArtistType.SINGER,new Image("file:src/main/resources/audios/Billie joe armstrong/pp.jpg"));
            SingerController.getSingerController().newAlbum("American Idiot");
            SingerController.getSingerController().publishMusic("American Idiot", Genre.ROCK, "Don't wanna be an American idiot Don't want a nation under the new media And can you hear the sound of hysteria? The subliminal mindfuck America Welcome to a new kind of tension All across the alien nation Where everything isn't meant to be okay In television dreams of tomorrow We're not the ones who're meant to follow For that's enough to argue Well, maybe I'm the faggot, America I'm not a part of a redneck agenda Now everybody, do the propaganda And sing along to the age of paranoia Welcome to a new kind of tension All across the alien nation Where everything isn't meant to be okay In television dreams of tomorrow We're not the ones who're meant to follow For that's enough to argue Don't wanna be an American idiot One nation controlled by the media Information age of hysteria It's calling out to idiot America Welcome to a new kind of tension All across the alien nation Where everything isn't meant to be okay In television dreams of tomorrow We're not the ones who're meant to follow For that's enough to argue", "src/main/resources/audios/Billie joe armstrong/American Idiot.mp3", new Image("file:src/main/resources/audios/Billie joe armstrong/cover.png"), "Billie Joe Armstrong_American Idiot");
            SingerController.getSingerController().publishMusic("Boulevard of Broken Dreams", Genre.ROCK, "I walk a lonely road The only one that I have ever known Don't know where it goes But it's home to me, and I walk alone I walk this empty street On the Boulevard of Broken Dreams Where the city sleeps And I'm the only one, and I walk alone I walk alone, I walk alone I walk alone, I walk a- My shadow's the only one that walks beside me My shallow heart's the only thing that's beating Sometimes, I wish someone out there will find me 'Til then, I walk alone Ah-ah, ah-ah, ah-ah, ah-ah Ah-ah, ah-ah, ah-ah I'm walking down the line That divides me somewhere in my mind On the borderline Of the edge, and where I walk alone Read between the lines What's fucked up, and everything's alright Check my vital signs To know I'm still alive, and I walk alone I walk alone, I walk alone I walk alone, I walk a- My shadow's the only one that walks beside me My shallow heart's the only thing that's beating Sometimes, I wish someone out there will find me 'Til then, I walk alone Ah-ah, ah-ah, ah-ah, ah-ah Ah-ah, ah-ah, I walk alone, I walk a- I walk this empty street On the Boulevard of Broken Dreams Where the city sleeps And I'm the only one, and I walk a- My shadow's the only one that walks beside me My shallow heart's the only thing that's beating Sometimes, I wish someone out there will find me 'Til then, I walk alone", "src/main/resources/audios/Billie joe armstrong/Boulevard Of Broken Dreams.mp3", new Image("file:src/main/resources/audios/Billie joe armstrong/cover.png"), "Billie Joe Armstrong_American Idiot");
            SingerController.getSingerController().logout();

            SingerController.getSingerController().signUp("KendrickLamar", "hiphopKDing22!", "Kendrick Lamar", "kendrick@gmail.com", "09445566778", dateFormat.parse("1987-06-17"), "Kendrick Lamar Duckworth is an American rapper, songwriter, and record producer.", ArtistType.SINGER,new Image("file:src/main/resources/audios/Kendrick lamar/pp.jpg"));
            SingerController.getSingerController().newAlbum("DAMN.");
            SingerController.getSingerController().publishMusic("HUMBLE.", Genre.HIPHOP, "Nobody pray for me It been that day for me Way Yeah, yeah! Ayy, I remember syrup sandwiches and crime allowances Finesse on 'em with some counterfeits, but now I'm countin' this Parmesan where my accountant lives, in fact, I'm downin' this D'USSÉ with my boo bae tastes like Kool-Aid for the analysts Girl, I can buy yo' ass the world with my paystub I know that it's good, won't you sit it on my taste bluds? I get way too petty once you let me do the extras Pull up on your block, then break it down, we playin' Tetris A.m. to the p.m., p.m. to the a.m., funk Eat up your per diem, you just gotta hate 'em, funk If I quit your BM, I still ride Mercedes, funk If I quit this season, I still be the greatest, funk My left stroke just went viral Right stroke put lil' baby in a spiral Soprano C, we like to keep it on a high note It's levels to it, you and I know Tell 'em, be humble (hol' up) Sit down (hol' up, hol' up, lil', hol' up) Be humble (hol' up) Sit down (hol' up, sit down, lil', sit down, lil') Be humble (hol' up, hol' up) Sit down (hol' up, hol' up, lil', hol' up) Be humble (hol' up) Sit down (hol' up, hol' up, hol' up) Be humble (hol' up, hol' up) Sit down (hol' up, hol' up, lil', hol' up, lil') Be humble (hol' up) Sit down (hol' up, sit down, lil', sit down, lil') Be humble (hol' up, hol' up) Sit down (hol' up, hol' up, lil', hol' up) Be humble (hol' up) Sit down (hol' up, hol' up, hol' up, hol' up) Who dat -a thinkin' that he frontin' on Man-Man? (Man-Man) Get the f- off my stage, I'm the Sandman (Sandman) Get the f- off my (ayy), that ain't right I make a play blowing up your whole life I'm so, so sick and tired of the Photoshop Show me somethin' natural like afro on Richard Pryor Show me somethin' natural, I wanna feel some stretch marks Still I take you down right on your mama couch in Polo socks Ayy, this shit way too crazy, ayy, you do not amaze me, ayy I blew cool from AC, ayy, Obama just paged me, ayy I don't fabricate it, ayy, most of y'all be fakin', ayy I stay modest 'bout it, ayy, she elaborate it, ayy This that Grey Poupon, that Evian, that TED Talk, ayy Watch my soul speak, you let the meds talk, ayy If I kill a, uhm, it won't be the alcohol, ayy I'm the realest, uhm, after all Tell 'em, be humble (hol' up) Sit down (hol' up, hol' up, lil') Be humble (hol' up) Sit down (hol' up, sit down, lil', sit down, lil') Tell 'em sit down (hol' up, hol' up, lil' hol' up) Be humble (hol' up) Sit down (hol' up, hol' up, hol' up, hol' up) Be humble (hol' up, hol' up) Sit down (hol' up, hol' up, lil' hol' up, lil') Be humble (hol' up) Sit down (hol' up, sit down, lil', sit down, lil') Be humble (hol' up, hol' up) Sit down (hol' up, hol' up, lil' hol' up) Be humble (hol' up, hol' up) Sit down (hol' up, hol' up, hol' up)", "src/main/resources/audios/Kendrick lamar/HUMBLE..mp3", new Image("file:src/main/resources/audios/Kendrick lamar/humble.jpg"), "Kendrick Lamar_DAMN.");
            SingerController.getSingerController().publishMusic("DNA.", Genre.HIPHOP, "I got, I got, I got, I got Loyalty, got royalty inside my DNA Quarter piece, got war, and peace inside my DNA I got power, poison, pain, and joy inside my DNA I got hustle, though, ambition flow inside my DNA I was born like this Since one like this, immaculate conception I transform like this, perform like this Was Yeshua new weapon I don't contemplate, I meditate Then off your-, off your head This that put-the-kids-to-bed This that I got, I got, I got, I got Realness, I just kill sh- 'cause it's in my DNA I got millions, I got riches buildin' in my DNA I got dark, I got evil that rot inside my DNA I got off, I got troublesome heart inside my DNA I just win again, then, win again like Wimbledon I serve Yeah, that's him again, the sound that engine in is like a bird You see fireworks and Corvette tire skrrt the boulevard I know how you work, I know just who you are See, you's a, you's a, you's a B-, your hormones prolly switch inside your DNA Problem is, all that sucker shit inside your DNA Daddy prolly snitched, heritage inside your DNA Backbone don't exist, born outside a jellyfish, I gauge See, my pedigree most definitely don't tolerate the front Sh- I've been through prolly offend you This is Paula's oldest son I know murder, conviction, burners, boosters Burglars, ballers, dead, redemption Scholars, fathers dead with kids, and I wish I was fed forgiveness Yeah, yeah, yeah, yeah, soldier's DNA (I'ma soldier DNA) Born inside the beast My expertise checked out in second grade When I was nine, on cell, motel We didn't have nowhere to stay At 29, I've done so well, hit cartwheel in my estate And I'm gon' shine like I'm supposed to antisocial, extrovert And excellent mean the extra work And absentness what the fuck you heard And pessimists never struck my nerve And Nazareth gonna plead this case The reason my power's here on earth Salute the truth, when the prophet say I-I got loyalty, got royalty inside my DNA (this is why I say that hip hop) (Has done more damage to young African Americans) (I got loyalty, got royalty inside my DNA) I live a better life, I'm rollin' several dice, f- your life (Than racism in recent years) (I got loyalty, got royalty inside my DNA) (I live a be-, - your life) This is my heritage, all I'm inheritin' (five, four, three, two, one) Money and power, the Mecca of marriages Tell me somethin' (give me some ganja) You motherfuckers can't tell me nothin' I'd rather die than to listen to you My DNA not for imitation, your DNA, an abomination This how it is when you're in the Matrix Dodgin' bullets, reapin' what you sow And stackin' up the footage, livin' on the go, and sleepin' in a villa Sippin' from a Grammy and walkin' in the buildin' Diamond in the ceilin', marble on the floors Beach inside the window, peekin' out the window Baby in the pool, godfather goals Only Lord knows I've been goin' hammer Dodgin' paparazzi, freakin' through the cameras Eat at Four Daughters, Brock wearin' sandals Yoga on a Monday, stretchin' to Nirvana Watchin' all the snakes, curvin' all the fakes Phone never on, I don't conversate I don't compromise, I just penetrate Sex, money, murder, these are the breaks These are the times, level number nine Look up in the sky, .10 is on the way Sentence on the way, killings on the way Motherf-ker, I got winners on the way You ain't sh- without a body on your belt You ain't sh- without a ticket on your plate You ain't sick enough to pull it on yourself You ain't rich enough to hit the lot and skate Tell me when destruction gonna be my fate Gonna be your fate, gonna be our faith? Peace to the world, let it rotate Sex, money, murder, our DNA","src/main/resources/audios/Kendrick lamar/DNA..mp3",new Image("file:src/main/resources/audios/Kendrick lamar/dna.jpg"), "Kendrick Lamar_DAMN.");
            SingerController.getSingerController().logout();

            PodcasterController.getPodcasterController().signUp("MortezaHossein", "MHAhlou12!", "Morteza Hossein", "mortezahossein@gmail.com", "09121119999", dateFormat.parse("1967-08-11"), "I'm a spotify podcaster.", ArtistType.PODCASTER,new Image("file:src/main/resources/audios/Morteza Hossein/pp.png"));
            PodcasterController.getPodcasterController().publishPodcast("How to have a healthy lifestyle",Genre.SOCIETY,"How to have a healthy lifestyle","src/main/resources/audios/Morteza Hossein/How to have a healthy lifestyle.MP3",new Image("file:src/main/resources/audios/Morteza Hossein/pic.jpg"));
            PodcasterController.getPodcasterController().logout();

            PodcasterController.getPodcasterController().signUp("HosseinAura", "HoesseinAura123!", "Hossein Aura", "hosseinaura@gmail.com", "09121118888", dateFormat.parse("1963-09-27"), "Hi dear friends, welcome to In Noghteh!", ArtistType.PODCASTER,new Image("file:src/main/resources/audios/Hossein Aura/pp.jpg"));
            PodcasterController.getPodcasterController().publishPodcast("Real Happiness",Genre.INTERVIEW,"Real Happiness","src/main/resources/audios/Hossein Aura/Real happiness.MP3",new Image("file:src/main/resources/audios/Hossein Aura/in.png"));
            PodcasterController.getPodcasterController().publishPodcast("Hope",Genre.SOCIETY,"Hope","src/main/resources/audios/Hossein Aura/Hope.MP3",new Image("file:src/main/resources/audios/Hossein Aura/in.png"));
            PodcasterController.getPodcasterController().logout();
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

    public static Image extractAlbumArt(String filePath) {
        try {
            Mp3File mp3File = new Mp3File(filePath);
            if (mp3File.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3File.getId3v2Tag();
                byte[] albumImageData = id3v2Tag.getAlbumImage();
                if (albumImageData != null) {
                    return new Image(new ByteArrayInputStream(albumImageData));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Image("file:src/main/resources/graphic/musicplayergraphicalprojectphase2/PngAndJpg/PlayBar/DefaultAudioCover.png");
    }
    public static String getAudioDurationInString(Double audioLengthInSeconds)
    {
        return audioLengthInSeconds/60+":"+audioLengthInSeconds%60;
    }
}
//Main.setCurrentCenterNode(FXMLLoader.load(ReportPageController.class.getResource("reportPage.fxml")));
//        Main.getCenterNodesHistory().add(Main.getCurrentCenterNode());