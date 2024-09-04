/**
 * This class defines the basic behaviors of movie item a customer can check out.
 * Extends the media class.
 *
 * @author nhasan36
 * @version 1.0
 */
public class Movie extends Media {
    private int runtime;
    /**
     * Constructs a new Movie object with the given parameters.
     *
     * @param genre       The genre of the movie
     * @param name        The name of the movie
     * @param rating      The audience rating of the movie
     * @param rentalPrice The rental price of the movie
     * @param runtime     The runtime of the movie in minutes
     */
    public Movie(Genre genre, String name, int rating, double rentalPrice, int runtime) {
        super(genre, name, rating, rentalPrice);
        this.runtime = runtime;
    }
    /**
     * Constructs a new Movie object with the given parameters and a default rental price
     * and runtime.
     *
     * @param genre       The genre of the movie
     * @param name        The name of the movie
     * @param rating      The audience rating of the movie
     */
    public Movie(Genre genre, String name, int rating) {
        super(genre, name, rating, 5.0);
        this.runtime = 111;
    }
    @Override
    public String toString() {
        String old = super.toString();
        return String.format("%s, Runtime: %d minutes",
                old, runtime);
    }
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Movie)) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        Movie movie = (Movie) object;
        return runtime == movie.runtime;
    }
}