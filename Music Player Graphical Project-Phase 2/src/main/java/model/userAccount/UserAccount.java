package model.userAccount;
import java.util.Date;

public abstract class UserAccount
{
    private String userName;
    private String password;
    private String firstAndLastName;
    private String email;
    private String phoneNumber;
    private Date birthDate;
    private UserType userType;
    public UserAccount(String userName,String password,String firstAndLastName,String email,String phoneNumber,Date birthDate)
    {
        this.userName=userName;
        this.password=password;
        this.firstAndLastName=firstAndLastName;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.birthDate=birthDate;
    }
    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getFirstAndLastName()
    {
        return firstAndLastName;
    }
    public void setFirstAndLastName(String firstAndLastName)
    {
        this.firstAndLastName = firstAndLastName;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    public Date getBirthDate()
    {
        return birthDate;
    }
    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }
}
