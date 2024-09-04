/**
 * This class defines the basic behaviors of videogame items a customer can check out.
 * Extends the media class.
 *
 * @author nhasan36
 * @version 1.0
 */
public class VideoGame extends Media {
    private int maxPlayers;
    private boolean needsConsole;
    /**
     * Constructs a new VideoGame object with the given parameters.
     *
     * @param genre       The genre of the video game
     * @param name        The name of the video game
     * @param rating      The audience rating of the video game
     * @param rentalPrice The rental price of the video game
     * @param maxPlayers  The maximum number of players for the video game
     * @param needsConsole Whether the game requires a console to play
     */
    public VideoGame(Genre genre, String name, int rating, double rentalPrice, int maxPlayers,
        boolean needsConsole) {
        super(genre, name, rating, rentalPrice);
        this.maxPlayers = maxPlayers;
        this.needsConsole = needsConsole;
    }
    /**
     * Constructs a new VideoGame object with default values for rental price, max players, and console requirement.
     *
     * @param genre  The genre of the video game
     * @param name   The name of the video game
     * @param rating The audience rating of the video game
     */
    public VideoGame(Genre genre, String name, int rating) {
        super(genre, name, rating, 5.0);
        this.maxPlayers = 2;
        this.needsConsole = false;
    }
    @Override
    public String toString() {
        String old = super.toString();
        String doesdoesnot = needsConsole ? "does" : "does not";
        return String.format("%s, Players: %d, %s need console", old,
            this.maxPlayers, doesdoesnot);
    }
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof VideoGame)) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        VideoGame videoGame = (VideoGame) object;
        return (this.maxPlayers == videoGame.maxPlayers && this.needsConsole == videoGame.needsConsole);
    }
    /**
     * Getter to return needsConsole.
     *
     * @return a boolean needsConsole
     */
    public boolean getNeedsConsole() {
        return this.needsConsole;
    }
}