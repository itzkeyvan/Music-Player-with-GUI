package model;
import model.audio.Audio;

import java.util.ArrayList;
import java.util.Iterator;

public class Playlist implements Iterable<Audio>
{
    private String playlistID;
    private String playlistName;
    private String playlistCreatorName;
    private ArrayList<Audio> audiosList =new ArrayList<Audio>();
    public Playlist(String playlistName,String playlistCreatorName)
    {
        this.playlistName=playlistName;
        this.playlistCreatorName=playlistCreatorName;
        this.playlistID=playlistCreatorName+"_"+playlistName;
    }
    public void setAudiosList(ArrayList<Audio> audiosList) {
        this.audiosList = audiosList;
    }

    public ArrayList<Audio> getAudiosList() {
        return audiosList;
    }

    public String getPlaylistID() {
        return playlistID;
    }

    public String getPlaylistCreatorName() {
        return playlistCreatorName;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistCreatorName(String playlistCreatorName) {
        this.playlistCreatorName = playlistCreatorName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public void setPlaylistID(String playlistID) {
        this.playlistID = playlistID;
    }

    @Override
    public Iterator<Audio> iterator() {
        return audiosList.iterator();
    }

    @Override
    public String toString()
    {
        StringBuilder tmp =new StringBuilder();
        for(Audio audio : this.audiosList)
            tmp.append(audio.toString()+"\n");
        return "Playlist name: "+playlistName+" | ID: "+playlistID+" | Creator: "+playlistCreatorName+" | Audios list:\n"+tmp;
    }
}