package exceptions.failedLoginExceptions;

public class WrongPasswordException extends FailedLoginException
{
    public WrongPasswordException()
    {
        super("Wrong password.");
    }
    public WrongPasswordException(String message)
    {
        super(message);
    }
}
