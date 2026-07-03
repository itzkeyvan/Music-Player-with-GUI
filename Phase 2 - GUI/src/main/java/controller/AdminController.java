package controller;

import model.DataBase;
import model.Report;
import model.audio.Audio;
import model.userAccount.*;
import java.util.ArrayList;

public class AdminController
{
    private static AdminController adminController;
    private AdminController(){}
    public static AdminController getAdminController()
    {
        if(adminController ==null)
        {
            adminController =new AdminController();
        }
        return adminController;
    }
    private Admin admin;
    public Admin getAdmin() {
        return admin;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    //-----------------
    public String logIn(UserAccount user)
    {
        admin=(Admin)user;
        return "Logged-in as admin.";
    }

    public void defineAdmin(String username, String password) {
        this.admin = Admin.getAdmin(username, password, "System Administrator", "admin@spotify.com", "09120000000", "2005-08-18");
    }

    public String showAudiosByLikes()
    {
        ArrayList<Audio> al=DataBase.getDataBase().getAudiosList();
        for(int i=0;i<al.size()-1;i++)
            for(int j=0;j<al.size()-1-i;j++)
                if(al.get(j).getNumberOfLikes()>al.get(j+1).getNumberOfLikes())
                {
                    Audio temp=al.get(j);
                    al.set(j,al.get(j+1));
                    al.set(j+1,temp);
                }
        StringBuilder sb=new StringBuilder();
        for(Audio audio:al)
        {
            sb.append(audio.toString()+"\n");
        }
        return sb.toString();
    }
    public String showAudios()
    {
        ArrayList<Audio> al=DataBase.getDataBase().getAudiosList();
        StringBuilder sb=new StringBuilder();
        for(Audio audio:al)
        {
            sb.append(audio.toString()+"\n");
        }
        return sb.toString();
    }
    public String showAnAudio(int audioID)
    {
        for(Audio audio:DataBase.getDataBase().getAudiosList())
        {
            if(audio.getAudioID()==audioID)
                return audio.toString();
        }
        return "Audio not found.";
    }
    public String viewArtistsByFollowers()
    {
        ArrayList<Artist> al= getArtistsArrayList();
        for(int i=0;i<al.size()-1;i++)
            for(int j=0;j<al.size()-1-i;j++)
                if(al.get(j).getFollowersList().size()>al.get(j+1).getFollowersList().size())
                {
                    Artist temp=al.get(j);
                    al.set(j,al.get(j+1));
                    al.set(j+1,temp);
                }
        StringBuilder sb=new StringBuilder();
        for(Artist artist:al)
        {
            sb.append(artist.toString()+"\n");
        }
        return sb.toString();
    }
    public String showAnArtist(String artistUserName)
    {
        for(Artist artist: getArtistsArrayList())
        {
            if(artist.getUserName().equals(artistUserName))
                return artist.toString();
        }
        return "Artist not found.";
    }
    public String showReports()
    {
        StringBuilder sb=new StringBuilder();
        for(Report report:DataBase.getDataBase().getReportsList())
            sb.append(report.toString()+"\n\n");
        if(DataBase.getDataBase().getReportsList().isEmpty())
            return "No reports registered.";
        else
            return sb.toString();
    }
    public String showAccountInfo()
    {
        return admin.toString();
    }
    public void logout()
    {
        admin=null;
    }
    public ArrayList<Artist> getArtistsArrayList()
    {
        ArrayList<Artist> al=new ArrayList<>();
        for(UserAccount userAccount:DataBase.getDataBase().getUsersList())
            if(userAccount.getUserType().equals(UserType.ARTIST))
                al.add((Artist) userAccount);
        return al;
    }
}