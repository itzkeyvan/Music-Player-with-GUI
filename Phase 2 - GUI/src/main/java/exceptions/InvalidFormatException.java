package exceptions;

public class InvalidFormatException extends Exception
{
    public InvalidFormatException()
    {
        super("Free account limit reached");
    }
    public InvalidFormatException(String message)
    {
        super(message);
    }
}
