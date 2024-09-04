/**
 * This class is the driver class to test the functionality of all the code
 * implemented. It contains a main method that will create different instances
 * of Attraction and Rollercoaster. It creates arrays of a list of people of
 * different sizes. It also tests several of the different methods in the Attraction
 * and RollerCoaster classes.
 *
 * @author nhasan36
 * @version 1.0
 */
public class TripGuide {
    /**
     * Main method to contain the created instances and testing of the methods.
     * It creates an array of Attractions that contain RollerCoasters. Creates groups
     * of varying sizes to test the admit functionality. It also tests the rateAndExit
     * function. It also utilizes the compareTo method in order to compare two
     * different attractions to each other.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Attraction[] attractions = new Attraction[] {
            new Attraction("attraction1", 2.99),
            new Attraction("attraction2", 2.99),
            new RollerCoaster("rollercoaster1", 2.99, 78),
            new Attraction("attraction3", 4.50),
            new RollerCoaster("rollercoaster2", 5.00, 30),
            new RollerCoaster("rollercoaster3", 3.00, 40)
        };
        String[] groupOf4 = {"Alice", "Bob", "Charlie", "Diana"};
        String[] groupOf5 = {"Eva", "Frank", "Grace", "Hank", "Irene"};
        String[] groupOf12 = {"Jake", "Kara", "Liam", "Mia", "Noah", "Olivia",
            "Pete", "Quinn", "Ryan", "Sara", "Tom", "Uma"};
        String[] groupOf25 = new String[25];
        for (int i = 0; i < groupOf25.length; i++) {
            groupOf25[i] = "Visitor " + (i + 1);
        }
        attractions[0].admit(groupOf4);
        attractions[0].admit(groupOf5);
        attractions[1].admit(groupOf4);
        attractions[1].admit(groupOf5);
        attractions[2].admit(groupOf12);
        attractions[3].admit(groupOf5);
        attractions[4].admit(groupOf25);

        for (Attraction attraction : attractions) {
            System.out.println("Attraction before rateandexit: " + attraction.toString());
            System.out.print("Visitor before rateandexit: ");
            attraction.printVisitors();
        }

        attractions[0].rateAndExit(0, 8);
        attractions[0].rateAndExit(0, 8);
        attractions[1].rateAndExit(0, 8);
        attractions[1].rateAndExit(0, 8);
        attractions[2].rateAndExit(0, 5);
        attractions[3].rateAndExit(0, 10);

        for (Attraction attraction : attractions) {
            System.out.println("Attraction after rateandexit: " + attraction.toString());
            System.out.print("Visitor after rateandexit: ");
            attraction.printVisitors();
        }
        int comparisonResult = attractions[0].compareTo(attractions[1]);
        System.out.println("Comparison Result (attraction[0] vs attraction[1]): " + comparisonResult);
        int comparisonResult2 = attractions[0].compareTo(attractions[2]);
        System.out.println("Comparison Result (attraction[0] vs attraction[2]): " + comparisonResult2);
        int comparisonResult3 = attractions[2].compareTo(attractions[3]);
        System.out.println("Comparison Result (attraction[2] vs attraction[3]): " + comparisonResult3);
    }
}