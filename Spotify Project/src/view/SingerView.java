package view;

import controller.SingerController;
import model.Genre;

import java.util.Scanner;

public class SingerView extends AccountView
{
    private static SingerView singerView=new SingerView();
    public static SingerView getSingerView() {
        return singerView;
    }
    public static void setSingerView(SingerView singerView) {
        SingerView.singerView = singerView;
    }

    Scanner scan = new Scanner(System.in);
    public static void print(Object obj)
    {
        System.out.println(obj);
    }
    public void showMenu()
    {
        print("\nPlease enter your command.\nAccountInfo\tFollowers\tViewsStatistics\tCalculateEarnings\tNewAlbum -[name]\n.Publish -M -[title] -[genre] -[lyric|caption] -[link] -[cover] -[album ID](Publish Music or Podcast)\n");
        String answer = scan.nextLine();
        String[] entries=answer.split(" -");
        switch (entries[0])
        {
            case "Followers":
            {
                print(SingerController.getSingerController().showFollowers());
                showMenu();
            }
            case "ViewsStatistics":
            {
                print(SingerController.getSingerController().numberOfPlaysOfEachAudio());
                showMenu();
            }
            case "CalculateEarnings":
            {
                print("Your income is: $"+ SingerController.getSingerController().calculateIncome());
                showMenu();
            }
            case "NewAlbum":
            {
                print(SingerController.getSingerController().newAlbum(entries[1]));
                showMenu();
            }
            case "Publish":
            {
                print(SingerController.getSingerController().publishMusic(entries[2],stringtoGenre(entries[3]),entries[4],entries[5],entries[6],entries[7]));
                showMenu();
            }
            case "AccountInfo":
            {
                print(SingerController.getSingerController().showAccountInfo());
                showMenu();
            }
            case "Logout":
            {
                SingerController.getSingerController().logout();
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
    public Genre stringtoGenre(String genreName) {
        for (Genre genre : Genre.values())
        {
            if (genre.getGenreName().equalsIgnoreCase(genreName))
            {
                return genre;
            }
        }
        return null;
    }
}
