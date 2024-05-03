package model;
import model.audio.Music;
import java.util.ArrayList;

public class Album
{
    private final String albumID;
    private String albumName;
    private String artistName;
    private ArrayList<Music> musicsList =new ArrayList<Music>();
    public Album(String albumName,String artistName)
    {
        this.albumName=albumName;
        this.artistName=artistName;
        this.albumID=artistName+"_"+albumName;
    }
    public String getAlbumID() {
        return albumID;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public ArrayList<Music> getMusicsList() {
        return musicsList;
    }

    public void setMusicsList(ArrayList<Music> musicsList) {
        this.musicsList = musicsList;
    }

    @Override
    public String toString()
    {
        StringBuilder tmp =new StringBuilder();
        for(Music music : this.musicsList)
            tmp.append(music.toString()+"\n");
        return "Album name: "+albumName+" | ID: "+albumID+" | Artist: "+artistName+" | Musics list:\n"+tmp;
    }
}