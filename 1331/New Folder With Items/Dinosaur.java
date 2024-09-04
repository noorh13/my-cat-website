/**
 * This class defines a Dinosaur object which will be a parenct class for child classes
 * Pterodactyl and Velociraptor. This class creates a Dinosaur using different constructor methods
 * with methods to calculate enclosure size, the amount of food required for the Dinosaur, build an
 * enclosure, determine the number of plates required for each Dinosaur, as well as getters
 * and setters for all the variables.
 *
 * @author nhasan36
 * @version 1.0
 */
public class Dinosaur {
    protected final String name;
    private double height;
    private double width;
    private double weight;
    protected static int totalEnclosures;
    /**
     * Constructs a new Dinosaur with parameters for every variable to describe the
     * dinosaur. If the provided name is null or blank, the default name "Dino" is
     * assigned.
     *
     * @param name   the name of the dinosaur
     * @param height the height of the dinosaur in units
     * @param width  the width of the dinosaur in units
     * @param weight the weight of the dinosaur in units
     */
    public Dinosaur(String name, double height, double width, double weight) {
        if (name == null || name.isBlank() || name.isEmpty()) {
            this.name = "Dino";
        } else {
            this.name = name;
        }
        this.height = height;
        this.width = width;
        this.weight = weight;
    }
    /**
     * A default constructor which creates a new Dinosaur that has the default values
     * "Dino" for name, 15 for height, 20 for width, and 1000 for weight.
     */
    public Dinosaur() {
        this("Dino", 15, 20, 1000);
    }
    /**
     * Copy constructor in order to create a new Dinosaur based on an existing one.
     *
     * @param other another Dinosaur object to copy attributes from
     */
    public Dinosaur(Dinosaur other) {
        this(other.name, other.height, other.width, other.weight);
    }
    /**
     * Calculates and returns the size of the enclosure needed for the dinosaur.
     *
     * @return the calculated enclosure size
     */
    public double enclosureSize() {
        double area = (10 * this.width) * this.height;
        return area;
    }
    /**
     * Calculates and returns the amount of food required for the dinosaur.
     *
     * @return the calculated food the dinosaur requires
     */
    public double calculateFood() {
        double food = (this.weight * this.width) * this.height;
        return food;
    }
     /**
     * Provides a string representation of the Dinosaur object, with
     * its required enclosure size and food amount.
     *
     * @return a string with the dinosaur name, its enclosure size, and amount of food
     * needed for it
     */
    public String toString() {
        String dinoString = String.format("%s requires a %f square foot enclosure and %f pounds of food.",
            this.name, enclosureSize(), calculateFood());
        return dinoString;
    }
    /**
     * Decides whether the dinosaur can be added to the park based on
     * food and enclosure size costs, and increments the total enclosures
     * if it is added, since an enclosure is built everytime the Dinosaur meets
     * certain requirements.
     *
     * @return a string stating whether the dinosaur was added to the park or not
     */
    public String buildEnclosure() {
        if (calculateFood() > 80000 || enclosureSize() > 6000) {
            String encString = String.format(" %s is too expensive for the park!", this.name);
            return this.toString() + encString;
        } else {
            String encString = String.format(" %s has been added to the park!", this.name);
            totalEnclosures++;
            return this.toString() + encString;
        }
    }
    /**
     * Calculates the number of identification plates required for the dinosaur
     * based on the digits in its dimensions and weight.
     *
     * @return the total number of plates required
     */
    public int numPlatesRequired() {
        int minPlates;
        int heightPlates = 0;
        int widthPlates = 0;
        int weightPlates = 0;
        for (int i = 0; i < String.valueOf(this.height).length(); i++) {
            heightPlates++;
        }
        for (int i = 0; i < String.valueOf(this.width).length(); i++) {
            widthPlates++;
        }
        for (int i = 0; i < String.valueOf(this.weight).length(); i++) {
            weightPlates++;
        }
        minPlates = heightPlates + widthPlates + weightPlates;
        return minPlates;
    }
    /**
     * Gets the height of the dinosaur.
     *
     * @return the height of the dinosaur
     */
    public double getHeight() {
        return height;
    }
    /**
     * Gets the width of the dinosaur.
     *
     * @return the width of the dinosaur
     */
    public double getWidth() {
        return width;
    }
    /**
     * Gets the weight of the dinosaur.
     *
     * @return the weight of the dinosaur
     */
    public double getWeight() {
        return weight;
    }
    /**
     * Gets the name of the dinosaur.
     *
     * @return the name of the dinosaur
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the total number of enclosures in the park.
     *
     * @return the total number of enclosures
     */
    public int getTotalEnclosures() {
        return totalEnclosures;
    }
    /**
     * Sets the height of the dinosaur.
     *
     * @param height the new height to set
     */
    public void setHeight(double height) {
        this.height = height;
    }
    /**
     * Sets the width of the dinosaur.
     *
     * @param width the new width to set
     */
    public void setWidth(double width) {
        this.width = width;
    }
     /**
     * Sets the weight of the dinosaur.
     *
     * @param weight the new weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
    /**
     * Sets the total number of enclosures in the park.
     *
     * @param totalEnclosures the new total number of enclosures to set
     */
    public void setTotalEnclosures(int totalEnclosures) {
        this.totalEnclosures = totalEnclosures;
    }

}