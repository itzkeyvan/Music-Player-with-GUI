package controller;

import exceptions.InvalidFormatException;
import javafx.scene.image.Image;
import model.Album;
import model.DataBase;
import model.audio.Music;
import model.audio.Podcast;
import model.userAccount.*;
import model.userAccount.artist.Podcaster;
import model.userAccount.artist.Singer;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ArtistController
{
    private static ArtistController artistController;
    ArtistController(){}
    public static ArtistController getArtistController()
    {
        if(artistController ==null)
        {
            artistController = new ArtistController() {
                @Override
                public String numberOfPlaysOfEachAudio() {
                    return null;
                }
            };
        }
        return artistController;
    }
    private Artist artist;
    public Artist getArtist() {
        return artist;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    public String signUp(String userName, String password, String firstAndLastName, String email, String phoneNumber, Date birthDate, String biography, ArtistType artistType, Image profilePicture) throws InvalidFormatException {
        for(UserAccount user: DataBase.getDataBase().getUsersList())
        {
            if (user.getUserName().equals(userName))
                return "This username is already taken.";
            if(user.getEmail().equals(email))
                return "This email is already taken.";
        }
        Pattern emailPattern=Pattern.compile("^(www\\.)?(?=.*[a-zA-Z])[a-zA-Z0-9]{4,25}@[a-zA-Z]{2,}\\.[a-zA-Z]{2,}$");
        Matcher emailMatcher=emailPattern.matcher(email);
        Pattern phoneNumberPattern=Pattern.compile("09\\d{9}");
        Matcher phoneNumberMatcher=phoneNumberPattern.matcher(phoneNumber);
        Pattern passwordPattern=Pattern.compile("(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$");
        Matcher passwordMatcher=passwordPattern.matcher(password);
        if(!emailMatcher.matches())
            throw new InvalidFormatException("Invalid email format.");
        if(!phoneNumberMatcher.matches())
            throw new InvalidFormatException("Invalid phone number format.");
        if (!passwordMatcher.matches())
            throw new InvalidFormatException("Weak password.");
        if(artistType==ArtistType.SINGER)
        {
            Singer singer = new Singer(userName, password, firstAndLastName, email, phoneNumber, birthDate, biography,profilePicture);
            DataBase.getDataBase().getUsersList().add(singer);
            setArtist(singer);
            SingerController.getSingerController().logIn(singer);
        }
        else
        {
            Podcaster podcaster = new Podcaster(userName, password, firstAndLastName, email, phoneNumber, birthDate, biography,profilePicture);
            DataBase.getDataBase().getUsersList().add(podcaster);
            setArtist(podcaster);
            PodcasterController.getPodcasterController().logIn(podcaster);
        }
        return "Signup successful.";
    }
    public String showFollowers()
    {
        StringBuilder sb=new StringBuilder();
        for(UserAccount user:artist.getFollowersList())
        {
            sb.append(user.toString()+"\n");
        }
        if(artist.getFollowersList().isEmpty())
            return "You don't have any followers.";
        return "Number of followers: "+artist.getFollowersList().size()+"\nFollowers:\n"+sb;
    }
    public abstract String numberOfPlaysOfEachAudio();
    public int numberOfTotalPlays()
    {
        int result=0;
        if(artist.getArtistType()==ArtistType.SINGER)
        {
            for(Album album:((Singer)artist).getAlbumsList())
                for(Music music:album.getMusicsList())
                    result+= music.getNumberOfPlays();
        }
        else
        {
            for(Podcast podcast:((Podcaster)artist).getPodcastsList())
                result+= podcast.getNumberOfPlays();
        }
        return result;
    }
    public double calculateIncome()
    {
        if(artist.getArtistType()==ArtistType.SINGER)
        {
            return numberOfTotalPlays()*0.4;
        }
        else
        {
            return numberOfTotalPlays()*0.5;
        }
    }
    public String showAccountInfo()
    {
        return artist.toString()+"\nEmail: "+artist.getEmail()+" | Password: "+artist.getPassword()+" | Phone number: "+artist.getPhoneNumber();
    }
    public void logout()
    {
        artist=null;
    }
}
