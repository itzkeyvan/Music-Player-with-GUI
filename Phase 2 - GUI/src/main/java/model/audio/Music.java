package model.audio;

import javafx.scene.image.Image;
import model.Genre;

import java.util.Date;

public class Music extends Audio
{
    private String lyrics;
    public Music(String audioName, String artistName, Date releaseDate, Genre genre, String audioURL, Image cover, String lyrics) throws Exception {
        super(audioName, artistName,  releaseDate, genre, audioURL, cover);
        this.lyrics=lyrics;
        setAudioType(AudioType.MUSIC);
    }
    public String getLyrics()
    {
        return lyrics;
    }
    public void setLyrics(String lyrics)
    {
        this.lyrics = lyrics;
    }
}
