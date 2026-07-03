package model;

import model.audio.Audio;
import model.userAccount.UserAccount;

import java.util.ArrayList;

public class DataBase
{
    private ArrayList<UserAccount> usersList=new ArrayList<UserAccount>();
    private ArrayList<Audio> audiosList=new ArrayList<Audio>();
    private ArrayList<Report> reportsList=new ArrayList<Report>();
    private static DataBase dataBase;
    private DataBase() {}
    public static DataBase getDataBase()
    {
        if(dataBase==null)
            dataBase=new DataBase();
        return dataBase;
    }

    public ArrayList<UserAccount> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<UserAccount> usersList) {
        this.usersList = usersList;
    }

    public ArrayList<Audio> getAudiosList()
    {
        return audiosList;
    }

    public void setAudiosList(ArrayList<Audio> audiosList) {
        this.audiosList = audiosList;
    }

    public ArrayList<Report> getReportsList() {
        return reportsList;
    }

    public void setReportsList(ArrayList<Report> reportsList) {
        this.reportsList = reportsList;
    }
}
