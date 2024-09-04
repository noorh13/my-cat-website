/**
 * This class represents a subclass of Attraction.
 *
 * @author nhasan36
 * @version 1.0
 */
public class RollerCoaster extends Attraction {
    private final int maxCapacity;
    private int occupancy = 0;
    /**
     * Constructor method to creates a new instance of Rollercoaster with passed
     * in parameters. It is set to a maxcapacity of 25 if the input is less than
     * 25.
     *
     * @param name The names of the rollercoaster.
     * @param admissionFee The fee for the attraction.
     * @param maxCapacity The capacity for the number of visitors in a rollercoaster.
     */
    public RollerCoaster(String name, double admissionFee, int maxCapacity) {
        super(name, admissionFee);
        if (maxCapacity < 25) {
            this.maxCapacity = 25;
        } else {
            this.maxCapacity = maxCapacity;
        }
    }
    /**
     * Constructor method to creates a new instance of Rollercoaster with passed
     * in parameters. Chains to the 3-arg constructor.
     *
     * @param name The names of the rollercoaster.
     */
    public RollerCoaster(String name) {
        this(name, 5.25, 25);
    }
    @Override
    public void admit(String[] visitorNames) {
        if (occupancy + visitorNames.length > maxCapacity) {
            System.out.println("RollerCoaster has reached maximum capacity. Please visit another time!");
            return;
        }

        super.admit(visitorNames);
        occupancy += visitorNames.length;
    }
    @Override
    public void rateAndExit(int index, int rating) {
        if (index < 0 || index >= getVisitors().length || getVisitors()[index] == null) {
            System.out.println("Could not update rating. Index invalid.");
            return;
        }

        int groupSize = getVisitors()[index].size();
        super.rateAndExit(index, rating);
        occupancy -= groupSize;
    }
    /**
     * This method returns the percentage of occupancy, rounded to 2 decimal
     * places.
     *
     * @return The average percentage of occupancy rounded to 2 decimal places.
     */
    public double percentOccupancy() {
        double percent = (double) occupancy / maxCapacity * 100;
        return Math.round(percent * 100.0) / 100.0;
    }
    @Override
    public String toString() {
        return "RollerCoaster: " + super.toString() + "/" + percentOccupancy() + "%";
    }

}