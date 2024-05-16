package exceptions.failedLoginExceptions;

public class UserNotFoundException extends FailedLoginException
{
    public UserNotFoundException()
    {
        super("User not found.");
    }
    public UserNotFoundException(String message)
    {
        super(message);
    }
}
