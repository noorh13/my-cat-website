/**
 * A driver class created in order to test the Task, CellPhone, and Laptop classes.
 * This is done by creating instances of CellPhone and Laptop as well as instances
 * of Task in order to test the instances of CellPhone and Laptop with. The methods
 * that are tested are the add task method, process task method, toString method, and
 * equals method. These are tested for both the CellPhone instances and Laptop instances.
 *
 * @author nhasan36
 * @version 1.0
 */
public class Driver {
    /**
     * Main method to contain the created instances and testing of the methods.
     * Tasks are created in order to test the CellPhone and Laptop instaces with,
     * which are then used to test the methods. The string representation of the
     * instances of CellPhone and Laptop are printed out. The instances of CellPhone
     * are compared to each other and the instances of Laptop are compared to each other.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Creating tasks
        Task task1 = new Task("Task 1", 32);
        Task task2 = new Task("Task 2", 4);
        Task task3 = new Task("Task 3", 15);
        Task task4 = new Task("Task 4", 44);

        // Creating cellphones
        CellPhone cellPhone1 = new CellPhone(12345, 600, 6);
        CellPhone cellPhone2 = new CellPhone(2453804, 30, 9);

        // Testing CellPhone methods
        System.out.println("adding task to cellPhone1: " + cellPhone1.addTask(task3));
        System.out.println("adding task to cellPhone2: " + cellPhone2.addTask(task1));
        System.out.println("processing task for cellPhone1: " + cellPhone1.processTask(task3));
        System.out.println("processing task for cellPhone2: " + cellPhone2.processTask(task1));
        System.out.println("toString for cellPhone1: " + cellPhone1.toString());
        System.out.println("toString for cellPhone2: " + cellPhone2.toString());
        System.out.println("checking equality for cellphones: " + cellPhone1.equals(cellPhone2));

        // Creating Laptops
        Laptop laptop1 = new Laptop(12134, 750, 8, true);
        Laptop laptop2 = new Laptop(79234, 400, 3);

        // Testing laptop methods
        System.out.println("adding task to laptop1: " + laptop1.addTask(task4));
        System.out.println("adding task to laptop2: " + laptop2.addTask(task2));
        System.out.println("processing task for laptop1: " + laptop1.processTask(task4));
        System.out.println("processing task for laptop2: " + laptop2.processTask(task2));
        System.out.println("toString for laptop1: " + laptop1.toString());
        System.out.println("toString for laptop2: " + laptop2.toString());
        System.out.println("checking equality for laptops: " + laptop1.equals(laptop2));
    }
}