/**
 * This class represents an attraction you are visiting and implements
 * admittable. It follows the contract set by the Comparable interface
 * using generics. It contains constructor methods, as well as multiple
 * other methods.
 *
 * @author nhasan36
 * @version 1.0
 */
public class Attraction implements Admittable, Comparable<Attraction> {
    private final String name;
    private long sumRatings = 0;
    private int numRatings = 0;
    private final double admissionFee;
    private Group[] visitors = new Group[5];
    /**
     * Constructor method to creates a new instance of Attraction with the passed
     * in parameter. It is set to a name of "No name" if the input is null or blank and
     * admissionFee to 0 is the passed in admissionFee is negative.
     *
     * @param name The names of the visitors in the group.
     * @param admissionFee The fee for the attraction.
     */
    public Attraction(String name, double admissionFee) {
        this.name = (name == null || name.isBlank()) ? "No name" : name;
        this.admissionFee = (admissionFee < 0) ? 0 : admissionFee;
    }
    /**
     * Constructor method to creates a new instance of Attraction with the passed
     * in parameter. It is set to a name of "No name" if the input is null or blank and
     * admissionFee to 0 is the passed in admissionFee is negative. Uses constructor
     * chaining.
     *
     * @param name The names of the visitors in the group.
     */
    public Attraction(String name) {
        this(name, 5.25);
    }
    @Override
    public void admit(String[] visitorNames) {
        int startIndex = 0;
        while (startIndex < visitorNames.length) {
            int groupSize = Math.min((visitorNames.length - startIndex), 5);
            String[] groupMembers = new String[groupSize];
            for (int i = 0; i < groupSize; i++) {
                groupMembers[i] = visitorNames[startIndex + i];
            }
            Group newGroup = new Group(groupMembers);
            boolean added = false;
            for (int i = 0; i < visitors.length; i++) {
                if (visitors[i] == null) {
                    visitors[i] = newGroup;
                    added = true;
                    break;
                }
            }

            if (!added) {
                resizeVisitorsArray();
                visitors[findFirstNull(visitors)] = newGroup;
            }
            startIndex += groupSize;
        }
    }
    private void resizeVisitorsArray() {
        Group[] newVisitors = new Group[visitors.length * 2];
        for (int i = 0; i < visitors.length; i++) {
            newVisitors[i] = visitors[i];
        }
        visitors = newVisitors;
    }
    private int findFirstNull(Group[] groups) {
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] == null) {
                return i;
            }
        }
        return groups.length;
    }
    /**
     * Method that takes in an int index and int rating, removing the group
     * of visitors at that index from the attraction and updating sumRatings
     * and numRatings based off their rating.
     *
     * @param index The index of the group being removed.
     * @param rating The rating of the attraction of that group.
     */
    public void rateAndExit(int index, int rating) {
        if (index < 0 || index >= visitors.length || visitors[index] == null) {
            System.out.println("Could not update rating. Index invalid.");
            return;
        }
        if (rating < 1 || rating > 10) {
            if (rating < 1) {
                rating = 1;
            } else if (rating > 10) {
                rating = 10;
            }
        }
        sumRatings += rating;
        numRatings++;
        for (int i = index; i < visitors.length - 1; i++) {
            visitors[i] = visitors[i + 1];
        }
        visitors[visitors.length - 1] = null;
    }
    /**
     * This method returns the average rating rounded to two decimal places
     * If there were no ratings, then it returns zero.
     *
     * @return The average rating rounded to two decimal places.
     */
    public double averageRating() {
        if (numRatings == 0) {
            return 0.0;
        }
        double average = (double) sumRatings / numRatings;
        return Math.round(average * 100.0) / 100.0;
    }
    /**
     * Prints out information about the attraction and the current visitors.
     */
    public void printVisitors() {
        System.out.println(this.toString());
        for (int i = 0; i < visitors.length && visitors[i] != null; i++) {
            System.out.println("Group " + (i + 1) + ": " + visitors[i].toString());
        }
    }
    @Override
    public String toString() {
        return name + "/" + averageRating() + "/" + admissionFee;
    }
    @Override
    public int compareTo(Attraction other) {
        if (other == null) {
            return -1;
        }
        double diffRating = averageRating() - other.averageRating();
        if (diffRating != 0) {
            return diffRating > 0 ? -1 : 1;
        } else {
            double diffFee = admissionFee - other.admissionFee;
            if (diffFee == 0) {
                return 0;
            } else {
                return diffFee > 0 ? 1 : -1;
            }
        }
    }
    /**
     * Getter method that returns the visitors Group[].
     *
     * @return Visitors Group array.
     */
    protected Group[] getVisitors() {
        return visitors;
    }
}