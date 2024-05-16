package view;

import controller.PodcasterController;
import controller.SingerController;
import model.Genre;

import java.util.Scanner;

public class PodcasterView extends AccountView
{
    private static PodcasterView podcasterView=new PodcasterView();
    public static PodcasterView getPodcasterView() {
        return podcasterView;
    }
    public static void setPodcasterView(PodcasterView podcasterView) {
        PodcasterView.podcasterView = podcasterView;
    }

    Scanner scan = new Scanner(System.in);
    public static void print(Object obj)
    {
        System.out.println(obj);
    }
    public void showMenu()
    {
        print("\nPlease enter your command.\nAccountInfo\tFollowers\tViewsStatistics\tCalculateEarnings\n.Publish -P -[title] -[genre] -[lyric|caption] -[link] -[cover] -[album ID](Publish Music or Podcast)\n");
        String answer = scan.nextLine();
        String[] entries=answer.split(" -");
        switch (entries[0])
        {
            case "Followers":
            {
                print(PodcasterController.getPodcasterController().showFollowers());
                showMenu();
            }
            case "ViewsStatistics":
            {
                print(PodcasterController.getPodcasterController().numberOfPlaysOfEachAudio());
                showMenu();
            }
            case "CalculateEarnings":
            {
                print("Your income is: $"+ PodcasterController.getPodcasterController().calculateIncome());
                showMenu();
            }
            case "Publish":
            {
                print(PodcasterController.getPodcasterController().publishPodcast(entries[2],stringtoGenre(entries[3]),entries[4],entries[5],entries[6]));
                showMenu();
            }
            case "AccountInfo":
            {
                print(PodcasterController.getPodcasterController().showAccountInfo());
                showMenu();
            }
            case "Logout":
            {
                PodcasterController.getPodcasterController().logout();
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