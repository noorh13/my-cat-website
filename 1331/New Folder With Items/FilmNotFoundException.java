/**
 * Represents an exception that is thrown when a requested film is not found in the movie theater's current listings.
 * This is a checked exception, so it is declared in the method signature.
 *
 * @author nhasan36
 * @version 1.0
 */
public class FilmNotFoundException extends Exception {
    /**
     * Constructor that utilizes constructor chaining and which contains a string
     * saying the movie is not found in the movie theater.
     *
     * @param movie the name of the movie that caused this exception
     */
    public FilmNotFoundException(String movie) {
        super(movie + " is not playing at this movie theater.");
    }
}