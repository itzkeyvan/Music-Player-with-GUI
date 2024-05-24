package model.userAccount.artist;
import javafx.scene.image.Image;
import model.Album;
import model.DataBase;
import model.audio.Music;
import model.userAccount.Artist;
import model.userAccount.ArtistType;

import java.util.ArrayList;
import java.util.Date;


public class Singer extends Artist
{
    private ArrayList<Album> albumsList =new ArrayList<Album>();
    public Singer(String userName, String password, String firstAndLastName, String email, String phoneNumber, Date birthDate, String biography, Image profilePicture)
    {
        super(userName, password, firstAndLastName, email, phoneNumber, birthDate, biography,profilePicture);
        setArtistType(ArtistType.SINGER);
    }
    public String showWorks()
    {
        StringBuilder sb=new StringBuilder();
        for(Album album:albumsList)
            for(Music music:album.getMusicsList())
                sb.append(music.toString()+"\n");
        return sb.toString();
    }
    public ArrayList<Album> getAlbumsList() {
        return albumsList;
    }

    public void setAlbumsList(ArrayList<Album> albumsList) {
        this.albumsList = albumsList;
    }
}
