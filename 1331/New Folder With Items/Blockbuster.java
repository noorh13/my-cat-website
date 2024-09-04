import java.util.ArrayList;
/**
 * Represents a Blockbuster store with an inventory of media items like movies and video games.
 *
 * @author nhasan36
 * @version 1.0
 */
public class Blockbuster {
    private ArrayList<Media> inventory;
    /**
     * Constructs a Blockbuster store with an empty inventory.
     */
    public Blockbuster() {
        this.inventory = new ArrayList<>();
    }
    /**
     * Adds a media item to the store's inventory.
     *
     * @param media The media item to be added to the inventory
     */
    public void addMedia(Media media) {
        inventory.add(media);
    }
    /**
     * Removes the first occurrence of a specified media item from the inventory.
     *
     * @param media The media item to be removed
     * @return The media item removed from the inventory, or null if it was not found
     */
    public Media removeMedia(Media media) {
        for (int i = 0; i < inventory.size(); i++) {
            Media compare = inventory.get(i);
            if (compare.equals(media)) {
                inventory.remove(i);
                return compare;
            }
        }
        return null;
    }
    /**
     * Method to sort the media in the inventory arraylist.
     */
    public void sortMedia() {
        for (int i = 0; i < inventory.size(); i++) {
            for (int j = 0; j < inventory.size() - i - 1; j++) {
                if (inventory.get(j).compareTo(inventory.get(j + 1)) > 0) {
                    Media temp = inventory.get(j);
                    inventory.set(j, inventory.get(j + 1));
                    inventory.set(j + 1, temp);
                }
            }
        }
    }
    /**
     * Method that uses a binary search to compare an inputted media until the same one is found
     * in the arraylist.
     *
     * @param media The media item to be searched for
     * @return The item that has the same genre, name, and rating
     */
    public Media findMedia(Media media) {
        int low = 0;
        int high = inventory.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            Media midVal = inventory.get(mid);
            int cmp = midVal.compareTo(media);

            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return midVal; // media found
            }
        }
        return null; // media not found
    }
    /**
     * Returns the most popular movie based on audience rating.
     *
     * @return The most popular movie
     */
    public Movie getMostPopularMovie() {
        Movie mostPopular = null;
        for (Media media : inventory) {
            if (media instanceof Movie) {
                Movie movie = (Movie) media;
                if (mostPopular == null || movie.getRating() > mostPopular.getRating()
                    || (movie.getRating() == mostPopular.getRating()
                    && movie.getName().compareTo(mostPopular.getName()) < 0)) {
                    mostPopular = movie;
                }
            }
        }

        return mostPopular;
    }
}