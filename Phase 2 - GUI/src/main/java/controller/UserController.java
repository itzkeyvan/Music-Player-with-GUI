package controller;

import exceptions.failedLoginExceptions.*;
import model.DataBase;
import model.userAccount.Artist;
import model.userAccount.ArtistType;
import model.userAccount.UserAccount;
import model.userAccount.UserType;

public class UserController
{
    private static UserController userController;
    private UserController(){
        super();
    }
    public static UserController getUserController()
    {
        if(userController ==null)
        {
            userController =new UserController();
        }
        return userController;
    }
    UserAccount userAccount;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    //---------------------------
    public String logIn(String userName,String password) throws WrongPasswordException, UserNotFoundException {
        for(UserAccount user: DataBase.getDataBase().getUsersList())
        {
            if (user.getUserName().equals(userName))
            {
                if (user.getPassword().equals(password))
                {
                    if (user.getUserType().equals(UserType.ADMIN))
                    {
                        AdminController.getAdminController().logIn(user);
                        return "Logged in as an admin.";
                    }
                    else if (user.getUserType().equals(UserType.ARTIST))
                    {
                        if(((Artist)user).getArtistType().equals(ArtistType.SINGER))
                        {
                            SingerController.getSingerController().logIn(user);
                            return "Logged in as a singer.";
                        }
                        else  //Podcaster.
                        {
                            PodcasterController.getPodcasterController().logIn(user);
                            return "Logged in as a podcaster.";
                        }
                    }
                    else   //Listener
                    {
                        ListenerController.getListenerController().logIn(user);
                        return "Logged in as a listener.";
                    }
                }
                else
                {
                    throw new WrongPasswordException();
                }
            }
        }
        throw new UserNotFoundException();
    }
}
