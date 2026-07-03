package exceptions.failedLoginExceptions;

public class FailedLoginException extends Exception
{
    public FailedLoginException()
    {
        super("Login failed.");
    }
    public FailedLoginException(String message)
    {
        super("Failed login exception _ "+message);
    }
}
