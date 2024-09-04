/**
 * Represents an exception that is thrown when a film has already been watched.
 * This is a unchecked exception, so it does not need to be declared in the method signature.
 *
 * @author nhasan36
 * @version 1.0
 */
public class AlreadyWatchedException extends RuntimeException {
    /**
     * Constructor that utilizes constructor chaining and which contains a string
     * saying the movie has already been watched.
     */
    public AlreadyWatchedException() {
        super("This movie has already been watched.");
    }
}