/**
 * A class to represent a laptop created by the company which is an extension
 * of the Device class. Methods in this class include two constructors, a method
 * to determine the number of required buffer slots, a method to determine whether
 * a task can be added to a laptop, a method to count the number of not null spaces
 * in the tasks array, a method to add the task, a method to determine if two laptops are equal,
 * a method to return a string representation of a laptop. All the methods but
 * the constructors, the buffer slots required method, and counting not null spaces method are
 * overriden from the Device class.
 *
 * @author nhasan36
 * @version 1.0
 */
public class Laptop extends Device {
    private boolean overclockable;
    private boolean overclocked = false;
    /**
     * This method is a constructor for a laptop which takes in the laptops
     * serial number, CPU capacity, a length for the array of tasks apart of this
     * laptop, and a boolean for whether the laptop is overclockable.
     *
     * @param serialNumber  The serial number for a laptop.
     * @param cpuCapacity   The CPU for a laptop.
     * @param tasksLength   The length of the array of tasks for a laptop.
     * @param overclockable A boolean indicating if the laptop can be overclocked.
     */
    public Laptop(int serialNumber, int cpuCapacity, int tasksLength,
        boolean overclockable) {
        super(serialNumber, cpuCapacity, tasksLength);
        this.overclockable = overclockable;
    }
    /**
     * This method is a constructor for a laptop which takes in the laptops
     * serial number, CPU capacity, and a length for the array of tasks apart of this
     * laptop. Overclockable is defaulted to false.
     *
     * @param serialNumber  The serial number for a laptop.
     * @param cpuCapacity   The CPU for a laptop.
     * @param tasksLength   The length of the array of tasks for a laptop.
     */
    public Laptop(int serialNumber, int cpuCapacity, int tasksLength) {
        this(serialNumber, cpuCapacity, tasksLength, false);
    }
    /**
     * This method returns the number of buffer slots necessary for the laptop
     * to continue functioning optimally.
     *
     * @param cpuRemaining The current CPU of the laptop.
     * @return The number of buffer slots required.
     */
    public int bufferSlotsRequired(int cpuRemaining) {
        if (this.tasks.length <= 4) {
            return 0;
        }
        if (cpuRemaining < 128) {
            return 2;
        }
        return 1;
    }
    /**
     * This method determines if a task can be added to the laptop's tasks array, based on available
     * slots in the task array, if their is enough CPU remaining in the laptop, and if the laptop
     * is overclockable or has been overclocked.
     *
     * @param task The task to be evaluated for addition.
     * @return true if there is enough CPU remaining and an empty slot for the task, false otherwise.
     */
    @Override
    public boolean canAddTask(Task task) {
        int remainingCpu = this.cpuRemaining;
        if (overclockable && !overclocked) {
            remainingCpu += this.cpuCapacity / 4;
        }
        boolean enoughCpu = task.getCpuCost() <= remainingCpu;
        boolean enoughSlots = (tasks.length - countnotNullSpots()) > bufferSlotsRequired(remainingCpu);
        return enoughCpu && enoughSlots;
    }
    /**
     * This helper method counts the number of not null spaces in the tasks array.
     *
     * @return The amount of not null tasks.
     */
    private int countnotNullSpots() {
        int notNullSpots = 0;
        for (int i = 0; i < this.tasks.length; i++) {
            if (tasks[i] != null) {
                notNullSpots += 1;
            }
        } return notNullSpots;
    }
    /**
     * This method adds a task to the laptop. If it does, then overcloked and overclockable
     * are updated.
     *
     * @param task The task to be added.
     * @return true if the task was successfully added, false otherwise.
     */
    @Override
    public boolean addTask(Task task) {
        if (!canAddTask(task)) {
            return false;
        }
        for (int i = 0; i < this.tasks.length; i++) {
            if (this.tasks[i] == null) {
                this.tasks[i] = task;
                if (overclockable && !overclocked && this.cpuRemaining < task.getCpuCost()) {
                    this.cpuRemaining += this.cpuCapacity / 4;
                    overclocked = true;
                    overclockable = false;
                }
                this.cpuRemaining -= task.getCpuCost();
                return true;
            }
        } return false;
    }
    /**
     * Method to determine whether two laptops are equal to each other.
     * This is done by comparing whether the two laptops have the same serial number,
     * CPU capacity, CPU remaining, and overclockability.
     *
     * @param object The object to which an instance of laptop will be compared to.
     * @return true if the laptops are the same, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (!super.equals(object) || !(this.getClass().equals(object.getClass()))) {
            return false;
        }
        Laptop other = (Laptop) object;
        return this.overclockable == other.overclockable;
    }
    /**
     * Returns a string representation of a laptop.
     *
     * @return A string containing the serial number, CPU remaining, CPU
     * capacity of a cellphone, and whether it has overclocking.
     */
    @Override
    public String toString() {
        String doesordoesnot = overclockable ? "does" : "does not";
        String laptopToString = String.format(super.toString() + " This laptop"
            + " %s have overclocking.", doesordoesnot);
        return laptopToString;
    }
}