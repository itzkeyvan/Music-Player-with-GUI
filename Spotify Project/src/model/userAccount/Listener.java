package model.userAccount;

import model.Genre;
import model.Playlist;
import model.PremiumPlans;
import model.audio.Audio;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class Listener extends UserAccount
{
    private int accountCredit;
    private PremiumPlans premiumPlan;
    private ArrayList<Playlist> playlistsList =new ArrayList<Playlist>();
    private ArrayList<Artist> followingsList =new ArrayList<Artist>();
    private Map<Audio,Integer> numberOfPlays =new HashMap<>();
    private Map<Audio,Boolean> likedAudios =new HashMap<>();
    private Date subscriptionExpirationDate;
    private ArrayList<Genre> favouriteGenres=new ArrayList<Genre>();
    private Map<Genre,Integer> numberOfPlaysByGenre =new HashMap<>();
    private Map<Genre,Integer> genresScores =new HashMap<>();
    public Listener(String userName, String password, String firstAndLastName, String email, String phoneNumber, Date birthDate,PremiumPlans premiumPlan)
    {
        super(userName, password, firstAndLastName, email, phoneNumber, birthDate);
        this.setPremiumPlan(premiumPlan);
        setUserType(UserType.LISTENER);
        this.numberOfPlaysByGenre.put(Genre.ROCK,0);
        this.numberOfPlaysByGenre.put(Genre.POP,0);
        this.numberOfPlaysByGenre.put(Genre.JAZZ,0);
        this.numberOfPlaysByGenre.put(Genre.HIPHOP,0);
        this.numberOfPlaysByGenre.put(Genre.COUNTRY,0);
        this.numberOfPlaysByGenre.put(Genre.TRUECRIME,0);
        this.numberOfPlaysByGenre.put(Genre.SOCIETY,0);
        this.numberOfPlaysByGenre.put(Genre.INTERVIEW,0);
        this.numberOfPlaysByGenre.put(Genre.HISTORY,0);
        this.genresScores.put(Genre.ROCK,0);
        this.genresScores.put(Genre.POP,0);
        this.genresScores.put(Genre.JAZZ,0);
        this.genresScores.put(Genre.HIPHOP,0);
        this.genresScores.put(Genre.COUNTRY,0);
        this.genresScores.put(Genre.TRUECRIME,0);
        this.genresScores.put(Genre.SOCIETY,0);
        this.genresScores.put(Genre.INTERVIEW,0);
        this.genresScores.put(Genre.HISTORY,0);
    }

    public int getAccountCredit() {
        return accountCredit;
    }

    public void setAccountCredit(int accountCredit) {
        this.accountCredit = accountCredit;
    }

    public PremiumPlans getPremiumPlan() {
        return premiumPlan;
    }

    public void setPremiumPlan(PremiumPlans premiumPlan) {
        this.premiumPlan = premiumPlan;
    }

    public ArrayList<Playlist> getPlaylistsList() {
        return playlistsList;
    }

    public void setPlaylistsList(ArrayList<Playlist> playlistsList) {
        this.playlistsList = playlistsList;
    }

    public Map<Audio, Integer> getNumberOfPlays() {
        return numberOfPlays;
    }

    public void setNumberOfPlays(Map<Audio, Integer> numberOfPlays) {
        this.numberOfPlays = numberOfPlays;
    }

    public Date getSubscriptionExpirationDate() {
        return subscriptionExpirationDate;
    }

    public void setSubscriptionExpirationDate(Date subscriptionExpirationDate)
    {
        this.subscriptionExpirationDate=subscriptionExpirationDate;
    }
    public ArrayList<Genre> getFavouriteGenres() {
        return favouriteGenres;
    }
    public void setFavouriteGenres(ArrayList<Genre> favouriteGenres) {
        this.favouriteGenres = favouriteGenres;
    }

    public ArrayList<Artist> getFollowingsList() {
        return followingsList;
    }

    public void setFollowingsList(ArrayList<Artist> followingsList) {
        this.followingsList = followingsList;
    }

    public Map<Genre, Integer> getNumberOfPlaysByGenre() {
        return numberOfPlaysByGenre;
    }

    public void setNumberOfPlaysByGenre(Map<Genre, Integer> numberOfPlaysByGenre) {
        this.numberOfPlaysByGenre = numberOfPlaysByGenre;
    }

    public Map<Audio, Boolean> getLikedAudios() {
        return likedAudios;
    }

    public void setLikedAudios(Map<Audio, Boolean> numberOfLikes) {
        this.likedAudios = numberOfLikes;
    }

    public Map<Genre, Integer> getGenresScores() {
        return genresScores;
    }

    public void setGenresScores(Map<Genre, Integer> genresScores) {
        this.genresScores = genresScores;
    }
    @Override
    public String toString()
    {
        return "Name: "+getFirstAndLastName()+" | username: "+getUserName()+" | credit: "+getAccountCredit()+"\nPremium plan: "+premiumPlan+" | Number of total plays: "+numberOfPlays.size();
    }
}
