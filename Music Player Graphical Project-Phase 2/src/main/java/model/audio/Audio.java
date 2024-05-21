package model.audio;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import javafx.scene.image.Image;
import model.Genre;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Audio implements Comparable
{
    private static int audioCounter=0;
    private int audioID;
    private AudioType audioType;
    private String audioName;
    private String artistName;
    private int numberOfPlays;
    private int numberOfLikes;
    private Date releaseDate;
    private Genre genre;
    private String audioURL;
    private final Image cover;
    public Audio(String audioName,String artistName,Date releaseDate,Genre genre,String audioURL,Image cover)
    {
        this.audioID=(++audioCounter);
        this.audioName=audioName;
        this.artistName=artistName;
        this.releaseDate=releaseDate;
        this.genre=genre;
        this.audioURL=audioURL;
        this.cover=cover;
    }

    public int getAudioID() {
        return audioID;
    }
    public void setAudioID(int audioID) {
        this.audioID = audioID;
    }
    public String getAudioName()
    {
        return audioName;
    }
    public void setAudioName(String audioName)
    {
        this.audioName = audioName;
    }
    public String getArtistName()
    {
        return artistName;
    }
    public void setArtistName(String artistName)
    {
        this.artistName = artistName;
    }
    public int getNumberOfPlays()
    {
        return numberOfPlays;
    }
    public void setNumberOfPlays(int numberOfPlays)
    {
        this.numberOfPlays = numberOfPlays;
    }
    public int getNumberOfLikes()
    {
        return numberOfLikes;
    }
    public void setNumberOfLikes(int numberOfLikes)
    {
        this.numberOfLikes = numberOfLikes;
    }
    public Date getReleaseDate()
    {
        return releaseDate;
    }
    public void setReleaseDate(Date releaseDate)
    {
        this.releaseDate = releaseDate;
    }
    public Genre getGenre()
    {
        return genre;
    }
    public void setGenre(Genre genre)
    {
        this.genre = genre;
    }

    public String getAudioURL()
    {
        return audioURL;
    }

    public void setAudioURL(String audioURL)
    {
        this.audioURL = audioURL;
    }

    public Image getCover()
    {
        return cover;
    }

    public AudioType getAudioType() {
        return audioType;
    }

    public void setAudioType(AudioType audioType) {
        this.audioType = audioType;
    }

    public static int getAudioCounter() {
        return audioCounter;
    }

    @Override
    public int compareTo(Object o)
    {
        Audio audio2=(Audio)o;
        if((this.getAudioName().compareTo(audio2.getAudioName())<0)||(this.getAudioName().equals(audio2.getAudioName())&&this.getNumberOfLikes()>audio2.getNumberOfLikes())||(this.getAudioName().equals(audio2.getAudioName())&&this.getNumberOfLikes()>audio2.getNumberOfLikes()&&this.getAudioType().equals(AudioType.MUSIC)&&audio2.getAudioType().equals(AudioType.PODCAST))||(this.getAudioName().equals(audio2.getAudioName())&&this.getNumberOfLikes()>audio2.getNumberOfLikes()&&this.getAudioType().equals(audio2.getAudioType())&&this.getNumberOfPlays()>audio2.getNumberOfPlays()))
            return 1;
        else if(this.getAudioName().equals(audio2.getAudioName())&&this.getNumberOfLikes()==audio2.getNumberOfLikes()&&this.getAudioType().equals(audio2.getAudioType())&&this.getNumberOfPlays()==audio2.getNumberOfPlays())
            return 0;
        else
            return -1;
    }

    @Override
    public String toString()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return "Audio name: "+audioName+" | Audio ID: "+audioID+" | Artist: "+artistName+" | Genre: "+genre.getGenreName()+" | Audio type: "+audioType.toString()+"\nRelease date: "+dateFormat.format(releaseDate)+" | Number of likes: "+numberOfLikes+" | Number of plays: "+numberOfPlays;
    }
}
