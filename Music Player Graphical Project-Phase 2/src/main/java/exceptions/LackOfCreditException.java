package exceptions;

public class LackOfCreditException extends Exception
{
    public LackOfCreditException()
    {
        super("Free account limit reached");
    }
    public LackOfCreditException(String message)
    {
        super(message);
    }
}
