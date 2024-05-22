package controller;

import exceptions.FreeAccountLimitException;
import exceptions.InvalidFormatException;
import exceptions.LackOfCreditException;
import model.*;
import model.audio.*;
import model.userAccount.*;
import model.userAccount.artist.Podcaster;
import model.userAccount.artist.Singer;
import model.userAccount.listener.FreeListener;
import model.userAccount.listener.PremiumListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListenerController
{
    private static ListenerController listenerController;
    private ListenerController(){}
    public static ListenerController getListenerController()
    {
        if(listenerController ==null)
        {
            listenerController =new ListenerController();
        }
        return listenerController;
    }

    public static void setListenerController(ListenerController listenerController) {
        ListenerController.listenerController = listenerController;
    }

    private Listener listener;
    public Listener getListener() {
        return listener;
    }
    public void setListener(Listener listener) {
        this.listener = listener;
    }
    //-----------------
    public String signUp(String userName, String password, String firstAndLastName, String email, String phoneNumber, Date birthDate) throws InvalidFormatException {
        for(UserAccount user:DataBase.getDataBase().getUsersList())
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
        FreeListener listener=new FreeListener(userName,password,firstAndLastName,email,phoneNumber,birthDate);
        logIn(listener);
        setListenerController(listenerController);
        DataBase.getDataBase().getUsersList().add(listener);
        listener.setAccountCredit(50);
        return "Signup successful. You have been rewarded $50 of credit!"; //Please enter at least 1 and at most 4 of your favourite genres from: ROCK,POP,JAZZ,HIPHOP,COUNTRY,TRUECRIME,SOCIETY,INTERVIEW,HISTORY
    }
    public String setFavouriteGenres(String[] genres)
    {
        for(String genreName:genres)
        {
            genreName=genreName.toLowerCase();
            for(Genre genre:Genre.values())
            {
                if(genre.getGenreName().equals(genreName))
                {
                    listener.getGenresScores().replace(genre,listener.getGenresScores().get(genre)+40);         //------------------------------------------------
                    listener.getFavouriteGenres().add(genre);
                    break;
                }
            }
        }
        return "Genres added to favourites successfully.";
    }
    public String logIn(UserAccount user)
    {
        listener=(Listener)user;
        return "Logged-in as listener.";
    }
    public String newPlayList(String playlistName) throws FreeAccountLimitException {
        if(listener.getSubscriptionExpirationDate()==null&&listener.getPlaylistsList().size()==FreeListener.getCreatingPlaylistsLimit())
            throw new FreeAccountLimitException();
        Playlist playList=new Playlist(playlistName,listener.getFirstAndLastName());
        listener.getPlaylistsList().add(playList);
        return "Playlist successfully created.";
    }
    public String addAudioToPlaylist(String playlistName,int audioID) throws FreeAccountLimitException {
        for(Playlist playlist:listener.getPlaylistsList())
            if(playlist.getPlaylistName().equals(playlistName))
                for(Audio audio:playlist.getAudiosList())
                    if(audio.getAudioID()==audioID)
                        return "This audio has been already added to this playlist.";
        for(Playlist playlist:listener.getPlaylistsList())
            if(playlist.getPlaylistName().equals(playlistName))
            {
                if((listener.getSubscriptionExpirationDate()==null&&playlist.getAudiosList().size()==FreeListener.getAddingMusicsToPlaylistLimit()))
                    throw new FreeAccountLimitException();
                for (Audio audio : DataBase.getDataBase().getAudiosList())
                    if (audio.getAudioID() == audioID)
                    {
                        if(!listener.getGenresScores().containsKey(audio.getGenre()))
                        {
                            listener.getGenresScores().put(audio.getGenre(),0);
                        }
                        listener.getGenresScores().replace(audio.getGenre(),listener.getGenresScores().get(audio.getGenre())+3);           //------------------------------------------
                        playlist.getAudiosList().add(audio);
                        return "Audio added to playlist successfully.";
                    }
                return "Audio not found";
            }
        return "Playlist not found.";
    }
    public String playAudio(int audioID)
    {
        for (Audio audio : DataBase.getDataBase().getAudiosList())
            if (audio.getAudioID() == audioID)  //found.
            {
                listener.getNumberOfPlays().putIfAbsent(audio,0);
                listener.getNumberOfPlaysByGenre().putIfAbsent(audio.getGenre(),0);
                if(!listener.getGenresScores().containsKey(audio.getGenre()))
                {
                    listener.getGenresScores().put(audio.getGenre(),0);
                }
                listener.getNumberOfPlays().replace(audio,listener.getNumberOfPlays().get(audio)+1);
                listener.getNumberOfPlaysByGenre().replace(audio.getGenre(),listener.getNumberOfPlaysByGenre().get(audio.getGenre())+1);
                listener.getGenresScores().replace(audio.getGenre(),listener.getGenresScores().get(audio.getGenre())+1);           //------------------------------------------
                audio.setNumberOfPlays(audio.getNumberOfPlays()+1);
                return "Audio played successfully.";
            }
        return "Audio not found";
    }
    public String likeAudio(int audioID)
    {
        for (Audio audio : DataBase.getDataBase().getAudiosList())
            if (audio.getAudioID() == audioID)  //found.
            {
                if(!listener.getGenresScores().containsKey(audio.getGenre()))
                {
                    listener.getGenresScores().put(audio.getGenre(),0);
                }
                listener.getGenresScores().replace(audio.getGenre(),listener.getGenresScores().get(audio.getGenre())+3);           //------------------------------------------
                if(listener.getLikedAudios().containsKey(audio))
                    return "This audio has already been liked.";
                else
                {
                    listener.getLikedAudios().putIfAbsent(audio, true);
                    audio.setNumberOfLikes(audio.getNumberOfLikes() + 1);
                    return "Audio liked successfully.";
                }
            }
        return "Audio not found";
    }
    public String searchInAudios(String entry)
    {
        StringBuilder result=new StringBuilder();
        for (Audio audio : DataBase.getDataBase().getAudiosList())
            if (audio.getAudioName().equals(entry)||audio.getArtistName().equals(entry))  //found.
            {
                result.append(audio.toString());
            }
        if(result.isEmpty())
            return "Audio not found";
        else
            return result.toString();
    }
    public String sortAudios()
    {
    ArrayList<Audio> al=DataBase.getDataBase().getAudiosList();
        for(int i=0;i<al.size()-1;i++)
            for(int j=0;j<al.size()-1-i;j++)
                if(al.get(j).compareTo(al.get(j+1))<0)
                {
                    Audio temp=al.get(j);
                    al.set(j,al.get(j+1));
                    al.set(j+1,temp);
                }
        StringBuilder result=new StringBuilder();
        for(Audio audio:al)
        {
            result.append(audio.toString()+"\n");
        }
        return "Sort complete:\n\n" +result;
    }
    public String filterAudiosByArtistOrGenre(String filterType,String filterBy)
    {
        ArrayList<Audio> filteredAudios=new ArrayList<>();
        if(filterType.equalsIgnoreCase("A"))  //Artist
        {
            int filteredAudiosCount=0;
            for(Audio audio:DataBase.getDataBase().getAudiosList())
            {
                if(audio.getArtistName().equalsIgnoreCase(filterBy))  //artist found
                {
                    filteredAudios.add(audio);
                    filteredAudiosCount++;
                }
            }
            if(filteredAudiosCount==0)
                return "No audios found by this artist.";
            StringBuilder result=new StringBuilder();
            for(Audio audio:filteredAudios)
            {
                result.append(audio.toString()+"\n");
            }
            return "Filter complete:\n\n" +result;
        }
        else  //Genre
        {
            int filteredAudiosCount=0;
            for(Audio audio:DataBase.getDataBase().getAudiosList())
            {
                if(audio.getGenre().getGenreName().equalsIgnoreCase(filterBy))  //genre found
                {
                    filteredAudios.add(audio);
                    filteredAudiosCount++;
                }
            }
            if(filteredAudiosCount==0)
                return "No audios found by this genre.";
            StringBuilder result=new StringBuilder();
            for(Audio audio:filteredAudios)
            {
                result.append(audio.toString()+"\n");
            }
            return "Filter complete:\n\n" +result;
        }
    }
    public String filterByDate(Date filterDate)
    {
        ArrayList<Audio> filteredAudios=new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        int j=0;
        for(Audio audio:DataBase.getDataBase().getAudiosList())
        {
            LocalDate localDate = audio.getReleaseDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            String formattedDate = localDate.format(formatter);
            LocalDate parsedDate = LocalDate.parse(formattedDate, formatter);
            Date convertedUtilDate = java.util.Date.from(parsedDate.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
            if(convertedUtilDate.equals(filterDate))  //date found
            {
                filteredAudios.add(audio);
                j++;
            }
        }
        if(j==0)
            return "No audios found by this date.";
        StringBuilder result=new StringBuilder();
        for(Audio audio:filteredAudios)
        {
            result.append(audio.toString()+"\n");
        }
        return "Filter complete:\n\n" +result;
    }
    public String showFollowingsList()
    {
        if(listener.getFollowingsList().isEmpty())
            return "You don't follow any user.";
        StringBuilder sb=new StringBuilder();
        for(UserAccount user:listener.getFollowingsList())
            sb.append(user.toString());
        return sb.toString();
    }
    public String reportArtist(String artistUserName,String description)
    {
        for(Artist artist:AdminController.getAdminController().getArtistsArrayList())
            if(artist.getUserName().equals(artistUserName))
            {
                Report report=new Report(listener,artist,description);
                DataBase.getDataBase().getReportsList().add(report);
                return "Artist reported successfully";
            }
        return "Artist not found.";
    }
    public String showArtistsList()
    {
        StringBuilder sb=new StringBuilder();
        for(Artist artist:AdminController.getAdminController().getArtistsArrayList())
            sb.append(artist.toString()+"\n\n");
        return sb.toString();
    }
    public String showArtist(String userName)
    {
        for(Artist artist:AdminController.getAdminController().getArtistsArrayList())
            if(artist.getUserName().equals(userName))
            {
                if(artist.getArtistType().equals(ArtistType.SINGER))
                {
                    Singer singer=(Singer)artist;
                    return singer+"\n"+singer.showWorks();
                }
                else
                {
                    Podcaster podcaster=(Podcaster)artist;
                    return podcaster+"\n"+podcaster.showWorks();
                }
            }
        return "Artist not found";
    }
    public String followArtist(String userName)
    {
        for(Artist artist:AdminController.getAdminController().getArtistsArrayList())
            if(artist.getUserName().equals(userName))
            {
                if(listener.getFollowingsList().contains(artist))
                    return "You have already followed this artist.";
                artist.getFollowersList().add(listener);
                listener.getFollowingsList().add(artist);
                return "Artist followed successfully.";
            }
        return "Artist not found";
    }
    public String showLyrics(int audioID)
    {
        for (Audio audio : DataBase.getDataBase().getAudiosList())
        {
            if (audio.getAudioID() == audioID && audio.getAudioType().equals(AudioType.MUSIC))  //found.
            {
                Music music = (Music) audio;
                return music.getLyrics();
            }
            if (audio.getAudioID() == audioID && audio.getAudioType().equals(AudioType.PODCAST))
                return "Podcasts don't have lyrics.";
        }
        return "Music not found.";
    }
    public String showPlaylists()
    {
        if(listener.getPlaylistsList().isEmpty())
            return "You have no playlists.";
        StringBuilder sb=new StringBuilder();
        for(Playlist playlist:listener.getPlaylistsList())
            sb.append(playlist.toString()+"\n");
        return "Your playlists:\n\n"+sb;
    }
    public String selectAndShowAPlaylist(String playlistName)
    {
        for(Playlist playlist:listener.getPlaylistsList())
            if(playlist.getPlaylistName().equals(playlistName))
                return playlist.toString();
        return "Playlist not found.";
    }
    public ArrayList<Audio> getSuggestedAudios()
    {
        ArrayList<Audio> suggestedAudios=new ArrayList<>();
        Map.Entry<Genre,Integer>[]genreScores=listener.getGenresScores().entrySet().toArray(new Map.Entry[listener.getGenresScores().size()]);
        for(int i=0;i<genreScores.length-1;i++)
            for(int j=0;j<genreScores.length-1-i;j++)
                if(genreScores[j].getValue()<genreScores[j+1].getValue())   //descending.
                {
                    Map.Entry<Genre, Integer> temp=genreScores[j];
                    genreScores[j]=genreScores[j+1];
                    genreScores[j+1]=temp;
                }
        if(DataBase.getDataBase().getAudiosList().size()>=10)
        {
            int i = 0;
            if (listener.getFollowingsList().isEmpty()) {
                for (Map.Entry<Genre, Integer> genreScoresEntry : genreScores)
                    for (Audio audio : DataBase.getDataBase().getAudiosList()) {
                        if (audio.getGenre() == genreScoresEntry.getKey())
                            suggestedAudios.add(audio);
                        i++;
                    }
            }
            else
            {
                for (Map.Entry<Genre, Integer> genreScoresEntry : genreScores) {
                    for (Artist artist : listener.getFollowingsList())
                        if (artist.getArtistType() == ArtistType.SINGER) {
                            Singer singer = (Singer) artist;
                            for (Album album : singer.getAlbumsList())
                                for (Music music : album.getMusicsList())
                                    if (music.getGenre() == genreScoresEntry.getKey()) {
                                        suggestedAudios.add(music);
                                        i++;
                                    }
                        }
                    if (i == 6)
                        break;
                }
                for (Map.Entry<Genre, Integer> genreScoresEntry : genreScores) {
                    for (Artist artist : listener.getFollowingsList())
                        if (artist.getArtistType() == ArtistType.PODCASTER) {
                            Podcaster podcaster = (Podcaster) artist;
                            for (Podcast podcast : podcaster.getPodcastsList())
                                if (podcast.getGenre() == genreScoresEntry.getKey()) {
                                    suggestedAudios.add(podcast);
                                    i++;
                                }
                        }
                    if (i == 9)
                        break;
                }
            }
        }
        else
        {
            throw new RuntimeException();
        }
        return suggestedAudios;
    }
    public String buyOrRenewSubscription(PremiumPlans premiumPlan) throws LackOfCreditException {
        if(listener.getAccountCredit()>=premiumPlan.getPlanPrice())
        {
            Calendar calendar = Calendar.getInstance();
            if (listener.getPremiumPlan() == null)  //buy
            {
                listener = ((FreeListener) listener).upgradeToPremium(premiumPlan);
                calendar.setTime(new Date());
                calendar.add(Calendar.DAY_OF_MONTH, premiumPlan.getNumberOfDays());
                listener.setSubscriptionExpirationDate(calendar.getTime());
                System.gc();
                listener.setAccountCredit(listener.getAccountCredit()-premiumPlan.getPlanPrice());
                return "You are now premium!";
            }
            else   //renew
            {
                calendar.setTime(listener.getSubscriptionExpirationDate());
                calendar.add(Calendar.DAY_OF_MONTH,premiumPlan.getNumberOfDays());
                listener.setPremiumPlan(premiumPlan);
                listener.setSubscriptionExpirationDate(calendar.getTime());
                ((PremiumListener)listener).setSubscriptionLeftDays(((PremiumListener) listener).getSubscriptionLeftDays()+premiumPlan.getNumberOfDays());
                listener.setAccountCredit(listener.getAccountCredit()-premiumPlan.getPlanPrice());
                return "Your premium subscription has been extended by "+premiumPlan.getNumberOfDays()+" days.";
            }
        }
        else
            throw new LackOfCreditException();
    }
    public String increaseAccountCredit(int value)
    {
        listener.setAccountCredit(listener.getAccountCredit()+value);
        return "Your account's credit increased by $"+value+".";
    }
    public String showAccountInfo()
    {
        return listener.toString()+"\nEmail: "+listener.getEmail()+" | Password: "+listener.getPassword()+" | Phone number: "+listener.getPhoneNumber();
    }
    public String showPremium()
    {
        if(listener.getPremiumPlan()==null)
            return "You are not premium.";
        else
        {
            ((PremiumListener)listener).setSubscriptionLeftDays(((PremiumListener)listener).getSubscriptionLeftDays()-1);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(listener.getSubscriptionExpirationDate());
            calendar.add(Calendar.DAY_OF_MONTH,-1);
            listener.setSubscriptionExpirationDate(calendar.getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
            return "Subsciption type: " + listener.getPremiumPlan()+"\nYour subscription has "+((PremiumListener) listener).getSubscriptionLeftDays()+" days left and ends at "+dateFormat.format(listener.getSubscriptionExpirationDate());
        }
    }
    public String showAllAudiosList()
    {
        StringBuilder sb=new StringBuilder();
        sb.append("Musics:\n");
        for(Audio audio:DataBase.getDataBase().getAudiosList())
            if(audio.getAudioType()==AudioType.MUSIC)
                sb.append(audio.toString()+"\n");
        sb.append("\nPodcasts:\n");
        for(Audio audio:DataBase.getDataBase().getAudiosList())
            if(audio.getAudioType()==AudioType.PODCAST)
                sb.append(audio.toString());
        return sb.toString();
    }
    public void logout()
    {
        listener=null;
    }
}