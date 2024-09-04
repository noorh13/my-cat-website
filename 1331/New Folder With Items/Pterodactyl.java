/**
 * Pterodactyl is a subclass of Dinosaur that adds a new property to represent the flight ceiling,
 * which is the maximum height the Pterodactyl can fly. It inherits the other variables from
 * it's parent class. It contains various constructor methods. The methods in this class create the
 * size of the enclosure, returns the string representation of the Pterodactyl, as well as getters
 * and setters for all the variables.
 *
 * @author nhasan36
 * @version 1.0
 */
public class Pterodactyl extends Dinosaur {
    private double flightCeiling;
    /**
     * Constructs a new Pterodactyl with given name, physical dimensions, and flight ceiling.
     *
     * @param name          the name of the Pterodactyl
     * @param height        the height of the Pterodactyl
     * @param width         the width of the Pterodactyl
     * @param weight        the weight of the Pterodactyl
     * @param flightCeiling the maximum flight ceiling of the Pterodactyl
     */
    public Pterodactyl(String name, double height, double width, double weight, double flightCeiling) {
        super(name, height, width, weight);
        setFlightCeiling(flightCeiling);
    }
    /**
     * Constructs a new Pterodactyl with a default height and weight, but given name and width.
     *
     * @param name  the name of the Pterodactyl
     * @param width the width of the Pterodactyl
     */
    public Pterodactyl(String name, double width) {
        this(name, 15, width, 1000, 50);
    }
    /**
     * Constructs a new Pterodactyl with a default height, width, weight, and flight ceiling, but
     * specified name.
     *
     * @param name the name of the Pterodactyl
     */
    public Pterodactyl(String name) {
        this(name, 15, 12, 1000, 50);
    }
    /**
     * Copy constructor in order to create a new Pterodactyl based on an existing one.
     *
     * @param other another Pterodactyl object to copy properties from
     */
    public Pterodactyl(Pterodactyl other) {
        this(other.name, other.getHeight(), other.getWidth(), other.getWeight(), other.flightCeiling);
    }
    /**
     * Calculates and returns the size of the enclosure for the Pterodactyl.
     *
     * @return the calculated enclosure size
     */
    public double enclosureSize() {
        double size = ((4 * getWidth() * getHeight()) + this.flightCeiling);
        return size;
    }
    /**
     * Returns a string representation of the Pterodactyl with its flying ability, required
     * enclosure size, and food requirement.
     *
     * @return a descriptive string for the Pterodactyl
     */
    public String toString() {
        String pterString = String.format(
            "%s can fly %f feet into the air! %s requires a %f square foot enclosure and %f pounds of food.",
            this.name, this.flightCeiling, this.name, enclosureSize(), calculateFood());
        return pterString;
    }
    /**
     * Gets the flight ceiling of the Pterodactyl.
     *
     * @return the flight ceiling
     */
    public double getFlightCeiling() {
        return flightCeiling;
    }
    /**
     * Sets the flight ceiling of the Pterodactyl while also ensuring it is within the accepted range.
     * If the provided value is out of range, it defaults to 50.0.
     *
     * @param flightCeiling the new flight ceiling to set
     */
    public void setFlightCeiling(double flightCeiling) {
        if (flightCeiling >= 10.0 && flightCeiling <= 100.0) {
            this.flightCeiling = flightCeiling;
        } else {
            this.flightCeiling = 50.0;
        }
    }
}