package model;

import model.userAccount.Artist;
import model.userAccount.UserAccount;

public class Report
{
    UserAccount reporter;
    Artist reportedArtist;
    String description;
    public Report(UserAccount reporter,Artist reportedArtist,String description)
    {
        this.reporter=reporter;
        this.reportedArtist=reportedArtist;
        this.description=description;
    }
    @Override
    public String toString()
    {
        return "Reporter: "+reporter.toString()+"\nReported artist: "+reportedArtist.toString()+"\nDescription: "+description;
    }
}
