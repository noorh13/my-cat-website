/**
 * This class defines the basic behaviors of media items a customer can check out.
 * This class is abstract, so can not be instantiated.
 *
 * @author nhasan36
 * @version 1.0
 */
public abstract class Media implements Comparable<Media> {
    private Genre genre;
    private String name;
    private int rating;
    private double rentalPrice;
    /**
     * Constructs a new Media item with the given parameters.
     *
     * @param genre       the genre of the media item
     * @param name        the name of the media item
     * @param rating      the audience rating of the media item
     * @param rentalPrice the rental price of the media item
     */
    public Media(Genre genre, String name, int rating, double rentalPrice) {
        this.genre = genre;
        this.name = name;
        this.rating = rating;
        this.rentalPrice = rentalPrice;
    }
    /**
     * Utilizes constructor chaining to construct a new Media item with a default rental price of 7.0.
     *
     * @param genre  the genre of the media item
     * @param name   the name of the media item
     * @param rating the audience rating of the media item
     */
    public Media(Genre genre, String name, int rating) {
        this(genre, name, rating, 7.0);
    }
    @Override
    public String toString() {
        return String.format("Genre: %s, Name: %s, Rating: %d, Rental Price: $%.2f", genre, name, rating, rentalPrice);
    }
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(this.getClass().equals(object.getClass()))) {
            return false;
        }
        Media media = (Media) object;
        return rating == media.rating && Double.compare(media.rentalPrice, rentalPrice) == 0
            && genre == media.genre && name.equals(media.name);
    }
    @Override
    public int compareTo(Media other) {
        if (this.genre != other.genre) {
            return this.genre.compareTo(other.genre);
        }
        if (!this.name.equals(other.name)) {
            return this.name.compareTo(other.name);
        }
        return this.rating - other.rating;
    }
    /**
     * Getter method that returns the name of the media.
     *
     * @return the String name of the media
     */
    public String getName() {
        return name;
    }
    /**
     * Getter method that returns the rating of the media.
     *
     * @return the int rating of the media
     */
    public int getRating() {
        return rating;
    }
    /**
     * Getter method that retursn the rental price of the media.
     *
     * @return the double rental price of the media
     */
    public double getRentalPrice() {
        return rentalPrice;
    }
}