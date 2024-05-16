import model.userAccount.Admin;
import view.AccountView;

public class Main {
    public static void main(String[] args)
    {
        Admin admin=Admin.getAdmin("itzKeyvan","Keyvan05","Keyvan Behravan","itzkeyvan@gmail.com","09022222222","2001-09-11");
        AccountView accountView=new AccountView();
        accountView.showFirstMenu();
    }
}