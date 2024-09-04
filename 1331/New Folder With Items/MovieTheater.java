import java.util.ArrayList;

/**
 * A class representing a movie theatre that contains a list of movies currently showing
 * and movies that have been watched.
 *
 * @author nhasan36
 * @version 1.0
 */
public class MovieTheater {
    private ArrayList<String> movies;
    private ArrayList<String> watched;
    /**
    * Constructor that takes in two ArrayLists for movies and watched movies and assigns them
    * to an instance of movies and watched movies. If what is passed in is false the ArrayList
    * will be empty.
    *
    * @param movies the list of movies currently showing
    * @param watched the list of movies already watched
    */
    public MovieTheater(ArrayList<String> movies, ArrayList<String> watched) {
        this.movies = (movies == null) ? new ArrayList<>() : new ArrayList<>(movies);
        this.watched = (watched == null) ? new ArrayList<>() : new ArrayList<>(watched);
    }
    /**
     * Checks if all movies in the interestingMovies list are playing in the theater.
     * Throws a FilmNotFoundException if any movie in the interestingMovies list is not
     * found in the theater's movies list.
     *
     * @param interestingMovies the list of movies to check against the theater's list
     */
    public void throwIfMoviesMissing(ArrayList<String> interestingMovies) throws FilmNotFoundException {
        if (interestingMovies == null) {
            throw new IllegalArgumentException("InterestingMovies is null");
        }
        for (String movie : interestingMovies) {
            if (!movies.contains(movie)) {
                throw new FilmNotFoundException(movie);
            }
        }
    }
    /**
     * Watches a movie in the theater. The movie is removed from the list of movies and added to the
     * list of watched movies. If the movie is not in the list of movies or has already been watched,
     * an exception is thrown.
     *
     * @param movie the movie to watch
     */
    public void watchMovie(String movie) throws FilmNotFoundException, AlreadyWatchedException {
        if (movie == null) {
            throw new IllegalArgumentException("Movie name is null.");
        }
        if (!movies.contains(movie)) {
            throw new FilmNotFoundException(movie);
        }
        if (watched.contains(movie)) {
            throw new AlreadyWatchedException();
        }

        movies.remove(movie);
        watched.add(movie);
    }
    /**
     * Selects movies from a list of recommended movies that are currently showing and not yet watched.
     *
     * @param recommendedMovies the list of recommended movies to consider
     * @return a list of movies that the user will see, based on the recommendations and current showings
     */
    public ArrayList<String> selectRecommended(ArrayList<String> recommendedMovies) {
        if (recommendedMovies == null) {
            throw new IllegalArgumentException("Recommended movies list cannot be null.");
        }

        ArrayList<String> willSee = new ArrayList<>();
        for (String movie : recommendedMovies) {
            if (movies.contains(movie) && !watched.contains(movie)) {
                willSee.add(movie);
            }
        }

        return willSee;
    }
    /**
     * Main method to test the MovieTheatre class.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ArrayList<String> movies = new ArrayList<>();
        movies.add("Movie 1");
        movies.add("Movie 2");
        movies.add("Movie 3");
        movies.add("Movie 4");
        movies.add("Movie 5");

        ArrayList<String> watched = new ArrayList<>();
        watched.add("Movie 1");
        watched.add("Movie 6");

        ArrayList<String> recommended = new ArrayList<>();
        recommended.add("Movie 2");
        recommended.add("Movie 7");
        recommended.add("Movie 6");

        MovieTheater theater = new MovieTheater(movies, watched);

        try {
            theater.throwIfMoviesMissing(movies);
            theater.watchMovie("Movie 2");
            theater.watchMovie("Movie 6");
            theater.watchMovie("Movie 7");
            ArrayList<String> willSee = theater.selectRecommended(recommended);
            for (String movie : willSee) {
                System.out.println(movie);
            }
        } catch (FilmNotFoundException error) {
            System.out.println(error.getMessage());
        } catch (AlreadyWatchedException error) {
            System.out.println(error.getMessage());
        } finally {
            System.out.println("Took a look at the movies!");
        }
    }
}