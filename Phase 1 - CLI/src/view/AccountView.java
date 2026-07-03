package view;

import controller.AdminController;
import controller.ListenerController;
import controller.PodcasterController;
import controller.SingerController;
import model.DataBase;
import model.userAccount.*;
import model.userAccount.artist.Podcaster;
import model.userAccount.artist.Singer;

import java.sql.Date;
import java.util.Scanner;

public class AccountView
{
    private static AccountView accountView=new AccountView();

    public static AccountView getAccountView() {
        return accountView;
    }

    public static void setAccountView(AccountView accountView) {
        AccountView.accountView = accountView;
    }

    Scanner scan = new Scanner(System.in);
    public static void print(Object obj)
    {
        System.out.println(obj);
    }

    public void showFirstMenu()
    {
        print("\nPlease enter your command.\nSignup -L|S|P -[username] -[password] -[name] -[email] -[phone number] -[birth date] -[bio(for artists only)]\nLogin -[username] -[password]\nExit");
        String answer=scan.nextLine();
        String[] entries=answer.split(" -");
        switch (entries[0])
        {
            case "Signup":
            {
                Date date=Date.valueOf(entries[7]);
                if(entries[1].equalsIgnoreCase("L"))  //Listener
                {
                    String signupResult=ListenerController.getListenerController().signUp(entries[2],entries[3],entries[4],entries[5],entries[6],date);
                    if(signupResult.equals("Signup successful. You have been rewarded $50 of credit!"))
                    {
                        print(signupResult);
                        ListenerView listenerView = ListenerView.getListenerView();
                        setAccountView(listenerView);
                        listenerView.showMenu();
                    }
                    else
                    {
                        print(signupResult);
                        showFirstMenu();
                    }

                }
                else if (entries[1].equalsIgnoreCase("S"))  //Singer
                {
                    String signupResult= SingerController.getSingerController().signUp(entries[2],entries[3],entries[4],entries[5],entries[6],date,entries[8], ArtistType.SINGER);
                    if(signupResult.equals("Signup successful."))
                    {
                        print(signupResult);
                        SingerView singerView = SingerView.getSingerView();
                        setAccountView(singerView);
                        singerView.showMenu();
                    }
                    else
                    {
                        print(signupResult);
                        showFirstMenu();
                    }
                }
                else if(entries[1].equalsIgnoreCase("P"))  //Podcaster
                {
                    String signupResult=PodcasterController.getPodcasterController().signUp(entries[2],entries[3],entries[4],entries[5],entries[6],date,entries[8], ArtistType.PODCASTER);
                    if(signupResult.equals("Signup successful."))
                    {
                        print(signupResult);
                        PodcasterView podcasterView = PodcasterView.getPodcasterView();
                        setAccountView(podcasterView);
                        podcasterView.showMenu();
                    }
                    else
                    {
                        print(signupResult);
                        showFirstMenu();
                    }
                }
                else
                {
                    print("Enter L,S or P correctly.");
                    showFirstMenu();
                }
            }
            case "Login":
            {
                for(UserAccount user:DataBase.getDataBase().getUsersList())
                {
                    if (user.getUserName().equals(entries[1]))
                    {
                        if (user.getPassword().equals(entries[2]))
                        {
                            if (user.getUserType().equals(UserType.ADMIN))
                            {
                                print(AdminController.getAdminController().logIn(user));
                                AdminView adminView = AdminView.getAdminView();
                                setAccountView(adminView);
                                adminView.showMenu();
                            }
                            else if (user.getUserType().equals(UserType.ARTIST))
                            {
                                if(((Artist)user).getArtistType().equals(ArtistType.SINGER))
                                {
                                    print(SingerController.getSingerController().logIn(user));
                                    SingerView singerView = SingerView.getSingerView();
                                    setAccountView(singerView);
                                    singerView.showMenu();
                                }
                                else  //Podcaster.
                                {
                                    {
                                        print(PodcasterController.getPodcasterController().logIn(user));
                                        PodcasterView podcasterView = PodcasterView.getPodcasterView();
                                        setAccountView(podcasterView);
                                        podcasterView.showMenu();
                                    }
                                }

                            }
                            else   //Listener
                            {
                                print(ListenerController.getListenerController().logIn(user));
                                ListenerView listenerView = ListenerView.getListenerView();
                                setAccountView(listenerView);
                                listenerView.showMenu();
                            }
                        }
                        else
                        {
                            print("Entered password is incorrect.");
                            showFirstMenu();
                        }
                    }
                }
                print("Username not found.");
                showFirstMenu();
            }
            case "Exit":
            {
                System.exit(0);
            }
            default:
            {
                print("Wrong command.");
                showFirstMenu();
            }
        }
    }
}
