package model.userAccount;
import model.DataBase;

import java.sql.Date;

public class Admin extends UserAccount
{
    private static Admin admin;
    private Admin(String userName, String password, String firstAndLastName, String email, String phoneNumber, Date birthDate)
    {
        super(userName, password, firstAndLastName, email, phoneNumber, birthDate);
        setUserType(UserType.ADMIN);
    }
    public static Admin getAdmin(String userName, String password, String firstAndLastName, String email, String phoneNumber,String birthDate)
    {
        if (admin == null)
        {
            admin = new Admin(userName, password, firstAndLastName, email, phoneNumber, Date.valueOf(birthDate));
            DataBase.getDataBase().getUsersList().add(admin);
        }
        return admin;
    }
    @Override
    public String toString()
    {
        return "Name: "+getFirstAndLastName()+" | username: "+getUserName()+" | Email: "+getEmail()+" | Phone number: "+admin.getPhoneNumber();
    }
}
