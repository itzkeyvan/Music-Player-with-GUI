package controller;

import model.Album;
import model.DataBase;
import model.Genre;
import model.audio.Music;
import model.userAccount.UserAccount;
import model.userAccount.artist.Singer;

import java.util.Calendar;
import java.util.Date;

public class SingerController extends ArtistController
{
    private static SingerController singerController;
    private SingerController(){
        super();
    }
    public static SingerController getSingerController()
    {
        if(singerController ==null)
        {
            singerController =new SingerController();
        }
        return singerController;
    }

    private Singer singer;

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    //---------------------
    public String logIn(UserAccount user)
    {
        setArtist((Singer)user);
        singer=(Singer)user;
        return "Logged-in as singer.";
    }
    public String newAlbum(String albumName)
    {
        for(Album album:singer.getAlbumsList())
        {
            if(album.getAlbumName().equals(albumName))
                return "An album with this name already exists.";
        }
        Album album=new Album(albumName,singer.getFirstAndLastName());
        singer.getAlbumsList().add(album);
        return "Album created successfully.";
    }
    public String publishMusic(String title, Genre genre, String lyrics, String URL, String cover, String albumID)
    {
        for(Album album:singer.getAlbumsList())
            for(Music music:album.getMusicsList())
                if(music.getAudioName().equals(title)&&albumID.equals(album.getAlbumID()))
                    return "This song is already published.";
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        Music music=new Music(title,singer.getFirstAndLastName(),calendar.getTime(),genre,URL,cover,lyrics);
        for(Album album:singer.getAlbumsList())
            if(album.getAlbumID().equals(albumID))
            {
                album.getMusicsList().add(music);
                DataBase.getDataBase().getAudiosList().add(music);
                return "Music published successfully.";
            }
        return "Album not Found";
    }
    public String numberOfPlaysOfEachAudio()
    {
        StringBuilder sb=new StringBuilder();
        int audioCounter=0;
            for(Album album:singer.getAlbumsList())
            {
                audioCounter += album.getMusicsList().size();
                sb.append("\n\nAlbum " + album.getAlbumName() + ":\n");
                for (Music music : album.getMusicsList())
                    sb.append("Music Name: " + music.getAudioName() + " | ID: " + music.getAudioID() + " | Genre: "+music.getGenre().getGenreName() +" | Number of plays: " + music.getNumberOfPlays() + " | Number of likes: " + music.getNumberOfLikes() + "\n");
            }
        if(audioCounter==0)
            return "You haven't published any musics.";
        return sb.toString();
    }
    public void logout()
    {
        ArtistController.getArtistController().logout();
        singer=null;
    }
}
