package controller;

import model.Album;
import model.DataBase;
import model.Genre;
import model.audio.Music;
import model.audio.Podcast;
import model.userAccount.ArtistType;
import model.userAccount.UserAccount;
import model.userAccount.artist.Podcaster;
import model.userAccount.artist.Singer;

import java.util.Calendar;
import java.util.Date;

public class PodcasterController extends ArtistController
{
    private static PodcasterController podcasterController;
    private PodcasterController(){
        super();
    }
    public static PodcasterController getPodcasterController()
    {
        if(podcasterController ==null)
        {
            podcasterController =new PodcasterController();
        }
        return podcasterController;
    }
    Podcaster podcaster;

    public Podcaster getPodcaster() {
        return podcaster;
    }

    public void setPodcaster(Podcaster podcaster) {
        this.podcaster = podcaster;
    }
    //---------------------------
    public String logIn(UserAccount user)
    {
        setArtist((Podcaster)user);
        podcaster=(Podcaster)user;
        return "Logged-in as podcaster.";
    }
    public String publishPodcast(String title, Genre genre, String caption, String URL, String cover)
    {
        for(Podcast podcast: podcaster.getPodcastsList())
            if(podcast.getAudioName().equals(title))
                return "This podcast is already published.";
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        Podcast podcast=new Podcast(title,podcaster.getFirstAndLastName(),calendar.getTime(),genre,URL,cover,caption);
        DataBase.getDataBase().getAudiosList().add(podcast);
        podcaster.getPodcastsList().add(podcast);
        return "Podcast published successfully.";
    }
    public String numberOfPlaysOfEachAudio()
    {
        StringBuilder sb=new StringBuilder();
        for(Podcast podcast:podcaster.getPodcastsList())
            sb.append("Podcast Name: "+podcast.getAudioName()+" | ID: "+podcast.getAudioID()+" | Number of plays: "+podcast.getNumberOfPlays()+" | Number of likes: "+podcast.getNumberOfLikes()+"\n");
        if(podcaster.getPodcastsList().isEmpty())
            return "You haven't published any podcasts.";
        return sb.toString();
    }
    public void logout()
    {
        ArtistController.getArtistController().logout();
        podcaster=null;
    }
}
