/**
 * This class is the processing task that a device can handle.
 * This class is immutable, ensuring that once a task is created, its properties cannot be changed and
 * that it cannot be inherited from. This class contains a constructor method
 * as well as methods to override the equals and toString methods from the Object class.
 * It also contains a getter method.
 *
 * @author nhasan36
 * @version 1.0
 */
public final class Task {
    private final String name;
    private final int cpuCost;
    /**
     * Constructor method to create a new task with the variables, name and
     * CPU cost. If name is null, it is set to be "GEN_TASK" and if CPU cost
     * is less than 8 it is set to be 8.
     *
     * @param name The name of the task. If null, set to "GEN_TASK".
     * @param cpuCost The CPU cost of the task. If less than 8, set to 8.
     */
    public Task(String name, int cpuCost) {
        if (name == null) {
            this.name = name;
        } else {
            this.name = "GEN_TASK";
        }
        if (cpuCost >= 8) {
            this.cpuCost = cpuCost;
        } else {
            this.cpuCost = 8;
        }
    }
    /**
     * Method to determine whether two tasks are equal to each other.
     * This is done by comparing whether the two tasks have the same name
     * and CPU cost.
     *
     * @param object The object to which an instance of task will be compared to.
     * @return true if the tasks are the same, false otherwise.
     */
    @Override 
    public boolean equals(Object object) {
        Task other = (Task) object;
        if (!this.getClass().equals(object.getClass())) {
            return false;
        }
        Task other1 = (Task) object;
        return this.name.equals(other.name) && this.cpuCost == other.cpuCost;
    }
    /**
     * Returns a string representation of a task.
     *
     * @return A string containing the name and CPU cost of a task.
     */
    @Override
    public String toString() {
        String taskString = String.format("%s has CPU cost of %d", this.name, this.cpuCost);
        return taskString;
    }
     /**
     * Getter method that returns the CPU cost of a task.
     *
     * @return The CPU cost of a task.
     */
    public int getCpuCost() {
        return this.cpuCost;
    }
}