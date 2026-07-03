package model.audio;

import model.Genre;

import java.util.Date;

public class Music extends Audio
{
    private String lyrics;
    public Music(String audioName,String artistName,Date releaseDate,Genre genre,String audioURL,String cover,String lyrics)
    {
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
