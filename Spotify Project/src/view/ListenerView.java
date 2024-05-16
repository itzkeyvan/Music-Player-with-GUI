package view;

import controller.AdminController;
import controller.ListenerController;
import model.PremiumPlans;
import model.userAccount.listener.FreeListener;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ListenerView extends AccountView
{
    private static ListenerView listenerView=new ListenerView();
    public static ListenerView getListenerView() {
        return listenerView;
    }
    public static void setListenerView(ListenerView listenerView) {
        ListenerView.listenerView = listenerView;
    }

    Scanner scan = new Scanner(System.in);
    public static void print(Object obj)
    {
        System.out.println(obj);
    }
    public void showMenu() {
        print("\nPlease enter your command.\nAccountInfo\tGetSuggestions\tArtists\tArtist -[username]\tFollow -[username]\nSearch -[artist name OR audio’s title]\tSort -L|P(based on Likes or Plays)\nFilter -A|G|D -[filter by](Filter by Artist, Genre or Date(Enter the date in dd-MM-yyyy format!))\nAdd -[playlist’s name] -[audio’s ID]\tShowPlaylists\tPlay -[audio’s ID]\nLike -[audio’s ID]\tLyric -[audio’s ID]\tNewPlaylist -[playlist’s name]\nFollowings\tReport -[artist’s username] -[explanation]\tIncreaseCredit -[value]\nGetPremium -[package]\tShowPremium\tLogout\nFavouriteGenres -[favourite genres separated with comma(,)]");
        String answer = scan.nextLine();
        String[] entries=answer.split(" -");
        switch (entries[0]) {
            case "GetSuggestions": {
                print(ListenerController.getListenerController().showSuggestedAudios());
                showMenu();
            }
            case "FavoriteGenres":
            {
                String[] genres=entries[1].split(",");
                print(ListenerController.getListenerController().setFavouriteGenres(genres));
                showMenu();
            }
            case "Artists": {
                print(ListenerController.getListenerController().showArtistsList());
                showMenu();
            }
            case "Artist": {
                print(ListenerController.getListenerController().showArtist(entries[1]));
                showMenu();
            }
            case "Follow": {
                print(ListenerController.getListenerController().followArtist(entries[1]));
                showMenu();
            }
            case "Search": {
                print(ListenerController.getListenerController().searchInAudios(entries[1]));
                showMenu();
            }
            case "Sort": {
                print(ListenerController.getListenerController().sortAudios(entries[1]));
                showMenu();
            }
            case "Filter":
            {
                if (entries[1].equals("D"))
                {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate localDate = LocalDate.parse(entries[2], formatter);
                    Date date=Date.valueOf(localDate);
                    print(ListenerController.getListenerController().filterByDate(date));
                }
                else
                {
                    print(ListenerController.getListenerController().filterAudiosByArtistOrGenre(entries[1], entries[2]));
                }
                showMenu();
            }
            case "Add": {
                print(ListenerController.getListenerController().addAudioToPlaylist(entries[1], Integer.parseInt(entries[2])));
                showMenu();
            }
            case "ShowPlaylists": {
                print(ListenerController.getListenerController().showPlaylists());
                showMenu();
            }
            case "SelectPlaylist": {
                print(ListenerController.getListenerController().selectAndShowAPlaylist(entries[1]));
                showMenu();
            }
            case "Play": {
                print(ListenerController.getListenerController().playAudio(Integer.parseInt(entries[1])));
                showMenu();
            }
            case "Like": {
                print(ListenerController.getListenerController().likeAudio(Integer.parseInt(entries[1])));
                showMenu();
            }
            case "Lyric": {
                print(ListenerController.getListenerController().showLyrics(Integer.parseInt(entries[1])));
                showMenu();
            }
            case "NewPlaylist": {
                print(ListenerController.getListenerController().newPlayList(entries[1]));
                showMenu();
            }
            case "Followings": {
                print(ListenerController.getListenerController().showFollowingsList());
                showMenu();
            }
            case "Report": {
                print(ListenerController.getListenerController().reportArtist(entries[1], entries[2]));
                showMenu();
            }
            case "IncreaseCredit": {
                print(ListenerController.getListenerController().increaseAccountCredit(Integer.parseInt(entries[1])));
                showMenu();
            }
            case "GetPremium": {
                PremiumPlans plan;
                if (entries[1].equals("30")) {
                    plan = PremiumPlans.ONEMONTH;
                    print(ListenerController.getListenerController().buyOrRenewSubscription(plan));
                } else if (entries[1].equals("60")) {
                    plan = PremiumPlans.TWOMONTHS;
                    print(ListenerController.getListenerController().buyOrRenewSubscription(plan));
                } else if (entries[1].equals("180")) {
                    plan = PremiumPlans.SIXMONTHS;
                    print(ListenerController.getListenerController().buyOrRenewSubscription(plan));
                } else
                    print("Wrong number of days.");

                showMenu();
            }
            case "AccountInfo":
            {
                print(ListenerController.getListenerController().showAccountInfo());
                showMenu();
            }
            case "ShowPremium":
            {
                print(ListenerController.getListenerController().showPremium());
                showMenu();
            }
            case "Logout": {
                ListenerController.getListenerController().logout();
                print("Logged out successfully.");
                AccountView.setAccountView(new AccountView());
                AccountView.getAccountView().showFirstMenu();
            }
            default:
            {
                print("Wrong command.");
                showMenu();
            }
        }
    }
}
