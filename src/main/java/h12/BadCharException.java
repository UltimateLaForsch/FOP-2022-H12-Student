package h12;

public class BadCharException extends RuntimeException
{
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param eChar the character part of the message
     * @param eInt the integer part of the message
     */
    public BadCharException(char eChar, int eInt)
    {
        super("Bad char '" + eChar + "' at position " + eInt);
    }
}
