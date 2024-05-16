package view;

import controller.AdminController;
import model.DataBase;

import java.util.Scanner;

public class AdminView extends AccountView
{
    private static AdminView adminView=new AdminView();

    private AdminView() {}

    public static AdminView getAdminView()
    {
        if (adminView == null)
            adminView = new AdminView();
        return adminView;
    }

    public static void setAdminView(AdminView adminView) {
        AdminView.adminView = adminView;
    }

    Scanner scan = new Scanner(System.in);
    public static void print(Object obj)
    {
        System.out.println(obj);
    }
    public void showMenu()
    {
        print("\nPlease enter your command.\nAccountInfo\tStatistics\tAudios\tAudio -[audio’s ID]\tArtists\nArtist -[username]\tReports\tLogout");
        String answer = scan.nextLine();
        String[] entries=answer.split(" -");
        switch (entries[0])
        {
            case "Statistics":
            {
                print(AdminController.getAdminController().showAudiosByLikes());
                showMenu();
            }
            case "Audios":
            {
                print(AdminController.getAdminController().showAudios());
                showMenu();
            }
            case "Audio":
            {

                print(AdminController.getAdminController().showAnAudio(Integer.parseInt(entries[1])));
                showMenu();
            }
            case "Artists":
            {

                print(AdminController.getAdminController().viewArtistsByFollowers());
                showMenu();
            }
            case "Artist":
            {

                print(AdminController.getAdminController().showAnArtist(entries[1]));
                showMenu();
            }
            case "Reports":
            {
                print(AdminController.getAdminController().showReports());
                showMenu();
            }
            case "AccountInfo":
            {
                print(AdminController.getAdminController().showAccountInfo());
                showMenu();
            }
            case "Logout":
            {
                AdminController.getAdminController().logout();
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
