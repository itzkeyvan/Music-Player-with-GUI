package model.userAccount;

import controller.ArtistController;

import java.util.ArrayList;
import java.util.Date;

public abstract class Artist extends UserAccount
{
    private double income;
    private ArrayList<UserAccount> followersList =new ArrayList<UserAccount>();
    private String biography;
    private ArtistType artistType;
    private int numberOfTotalPlays;
    public Artist(String userName, String password, String firstAndLastName, String email, String phoneNumber, Date birthDate, String biography)
    {
        super(userName, password, firstAndLastName, email, phoneNumber, birthDate);
        setBiography(biography);
        setUserType(UserType.ARTIST);
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public ArrayList<UserAccount> getFollowersList() {
        return followersList;
    }

    public void setFollowersList(ArrayList<UserAccount> followersList) {
        this.followersList = followersList;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public ArtistType getArtistType() {
        return artistType;
    }

    public void setArtistType(ArtistType artistType) {
        this.artistType = artistType;
    }

    public int getNumberOfTotalPlays()
    {
        ArtistController.getArtistController().setArtist(this);
        this.numberOfTotalPlays=ArtistController.getArtistController().numberOfTotalPlays();
        return numberOfTotalPlays;
    }

    @Override
    public String toString()
    {
        return "Name: "+getFirstAndLastName()+" | username: "+getUserName()+" | Number of followers: "+getFollowersList().size()+" | Number of total plays: "+getNumberOfTotalPlays()+ " | Biography: "+biography;
    }
}
