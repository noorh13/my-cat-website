/**
 * Represents a pack of dinosaurs, including the size of the pack and its name. Contains
 * a constructor method. Contains methods to create a string representation, as well as
 * getters for the variables.
 *
 * @author nhasan36
 * @version 1.0
 */
public class Pack {
    private final int size;
    private final String packName;
    /**
     * Constructs a Pack with the given size and name.
     * If the size is negative, it defaults to 4.
     * If the packName is null, blank, or empty, it defaults to "The Power Pack".
     *
     * @param size     the size of the pack (number of dinosaurs)
     * @param packName the name of the pack
     */
    public Pack(int size, String packName) {
        if (size >= 0) {
            this.size = size;
        } else {
            this.size = 4;
        }
        if (packName == null || packName.isBlank() || packName.isEmpty()) {
            this.packName = "The Power Pack";
        } else {
            this.packName = packName;
        }
    }
    /**
     * Returns a string representation of the pack including its name and size.
     *
     * @return a string describing the pack
     */
    public String toString() {
        String packString = String.format("%s is a family of dinosaurs of size %d!", this.packName, this.size);
        return packString;
    }
    /**
     * Gets the size of the pack.
     *
     * @return the size of the pack
     */
    public int getSize() {
        return this.size;
    }
    /**
     * Gets the name of the pack.
     *
     * @return the name of the pack
     */
    public String getPackName() {
        return this.packName;
    }
}