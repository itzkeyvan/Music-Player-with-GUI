package model.userAccount.artist;
import javafx.scene.image.Image;
import model.Album;
import model.audio.Music;
import model.audio.Podcast;
import model.userAccount.Artist;
import model.userAccount.ArtistType;

import java.util.ArrayList;
import java.util.Date;

public class Podcaster extends Artist
{
    private ArrayList<Podcast> podcastsList =new ArrayList<Podcast>();
    public Podcaster(String userName, String password, String firstAndLastName, String email, String phoneNumber, Date birthDate, String biography, Image profilePicture)

    {
        super(userName, password, firstAndLastName, email, phoneNumber, birthDate, biography,profilePicture);
        setArtistType(ArtistType.PODCASTER);
    }
    public String showWorks()
    {
        StringBuilder sb=new StringBuilder();
        for(Podcast podcast:podcastsList)
            sb.append(podcast.toString()+"\n");
        return sb.toString();
    }
    public ArrayList<Podcast> getPodcastsList() {
        return podcastsList;
    }

    public void setPodcastsList(ArrayList<Podcast> podcastsList) {
        this.podcastsList = podcastsList;
    }
}
