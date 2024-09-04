import java.util.ArrayList;
/**
 * Defines the behaviors and states of Olivia, related to her media rental activities.
 *
 * @author nhasan36
 * @version 1.0
 */
public class Olivia {
    private static double budget;
    private static ArrayList<Media> cart = new ArrayList<>();
    private static boolean canUseConsole;
    /**
     * Method to add a media item to Olivia's cart from a given Blockbuster store.
     *
     * @param media the media item Olivia wants to rent
     * @param store the Blockbuster store where Olivia is shopping
     * @return true if the media item was successfully added to the cart, false otherwise
     */
    public static boolean addToCart(Media media, Blockbuster store) {
        if (media != null && store != null && budget >= media.getRentalPrice()) {
            if (media instanceof VideoGame && !canUseConsole && ((VideoGame) media).getNeedsConsole()) {
                return false;
            }
            Media itemInStore = store.findMedia(media);
            if (itemInStore != null && budget >= itemInStore.getRentalPrice()) {
                cart.add(itemInStore);
                store.removeMedia(itemInStore);
                budget -= itemInStore.getRentalPrice();
                return true;
            }
        }
        return false;
    }
    /**
     * Removes a media item from Olivia's cart and returns it to the Blockbuster store.
     *
     * @param media the media item to remove from the cart
     * @param store the Blockbuster store to return the media item to
     */
    public static void changeMind(Media media, Blockbuster store) {
        if (media == null || store == null) {
            return;
        }
        for (int i = 0; i < cart.size(); i++) {
            Media item = cart.get(i);
            if (item.equals(media)) {
                cart.remove(i);
                store.addMedia(item);
                budget += item.getRentalPrice();
                return;
            }
        }
    }
}