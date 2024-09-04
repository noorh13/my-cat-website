/**
 * A subclass of Dinosaur with added attributes of speed and pack, but inherting its other
 * attributes. This class contains multiple types of constructor methods, as well as methods
 * to determine enclosure size, food intake, and a string representation. Also has getters
 * and setters for the variables.
 *
 * @author nhasan36
 * @version 1.0
 */
public class Velociraptor extends Dinosaur {
    private int speed = 30;
    private Pack pack;
    /**
     * Constructs a new Velociraptor with the given name, physical dimensions, speed, and pack.
     * If the speed is less than zero, it defaults to 30.
     *
     * @param name    the name of the Velociraptor
     * @param height  the height of the Velociraptor
     * @param width   the width of the Velociraptor
     * @param weight  the weight of the Velociraptor
     * @param speed   the speed of the Velociraptor
     * @param pack    the pack of the Velociraptor
     */
    public Velociraptor(String name, double height, double width, double weight, int speed, Pack pack) {
        super(name, height, width, weight);
        if (speed < 0) {
            this.speed = 30;
        } else {
            this.speed = speed;
        }
        this.pack = pack;
    }
    /**
     * Constructs a new Velociraptor with a default pack and speed, but given name and height.
     *
     * @param name   the name of the Velociraptor
     * @param height the height of the Velociraptor
     */
    public Velociraptor(String name, double height) {
        this(name, height, 20, 1000, 30, null);
    }
    /**
     * Copy constructor in order to create a new Velociraptor based on an existing one.
     *
     * @param other another Velociraptor object to copy properties from
     */
    public Velociraptor(Velociraptor other) {
        this(other.name, other.getHeight(), other.getWidth(), other.getWeight(), other.speed, other.pack);
    }
    /**
     * Calculates and returns the required size of the enclosure for the Velociraptor.
     * If the Velociraptor is part of a pack, the size is multiplied by the pack size.
     *
     * @return the calculated enclosure size
     */
    public double enclosureSize() {
        double area;
        if (pack == null) {
            area = (4 * this.getWidth()) * this.getHeight();
        } else {
            area = this.pack.getSize() * this.getHeight() * this.getWidth();
        }
        return area;
    }
    /**
     * Calculates and returns the amount of food required for the Velociraptor based on its weight and speed.
     *
     * @return the calculated food requirement
     */
    public double calculateFood() {
        double food;
        food = this.getWeight() * this.speed * this.getHeight();
        return food;
    }
    /**
     * Returns a string representation of the Velociraptor with its pack information, enclosure size,
     * and food requirement.
     *
     * @return a descriptive string for the Velociraptor
     */
    public String toString() {
        String velString;
        if (pack == null) {
            velString = String.format("%s requires a %f square foot enclosure and %f pounds of food.",
                this.name, enclosureSize(), calculateFood());
        } else {
            velString = String.format(
                "%s is a family of dinosaurs of size %d! %s requires a %f"
                + " square foot enclosure and %f pounds of food.",
                this.pack.getPackName(), this.pack.getSize(), this.name, enclosureSize(), calculateFood());
        }
        return velString;
    }
    /**
     * Gets the speed of the Velociraptor.
     *
     * @return the speed of the Velociraptor
     */
    public int getSpeed() {
        return speed;
    }
    /**
     * Sets the speed of the Velociraptor. If the provided speed is less than zero, it defaults to 30.
     *
     * @param speed the new speed to set
     */
    public void setSpeed(int speed) {
        if (speed < 0) {
            this.speed = 30;
        } else {
            this.speed = speed;
        }
    }
    /**
     * Gets the pack of the Velociraptor.
     *
     * @return the pack to which the Velociraptor belongs
     */
    public Pack getPack() {
        return pack;
    }
    /**
     * Sets the pack of the Velociraptor.
     *
     * @param pack the new pack to set
     */
    public void setPack(Pack pack) {
        this.pack = pack;
    }
}