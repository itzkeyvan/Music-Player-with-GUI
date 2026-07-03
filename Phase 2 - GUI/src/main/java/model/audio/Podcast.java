package model.audio;

import javafx.scene.image.Image;
import model.Genre;

import java.util.Date;

public class Podcast extends Audio
{
    private String caption;
    public Podcast(String audioName, String artistName, Date releaseDate, Genre genre, String audioURL, Image cover, String caption) throws Exception {
        super(audioName, artistName,  releaseDate, genre, audioURL, cover);
        this.caption=caption;
        setAudioType(AudioType.PODCAST);
    }
    public String getCaption()
    {
        return caption;
    }
    public void setCaption(String caption)
    {
        this.caption = caption;
    }
}
