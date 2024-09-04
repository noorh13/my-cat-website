/**
 * A class to represent a cellphone created by the company which is an extension
 * of the Device class. Methods in this class include two constructors, a method to
 * determine whether a task can be added to a cellphone, a method to add the task,
 * a method to process the task, a method to determine if two cellphones are equal,
 * a method to return a string representation of a cellphone. All the methods but
 * the constructors are overriden from the Device class.
 *
 * @author nhasan36
 * @version 1.0
 */
public class CellPhone extends Device {
    private int tasksCompleted = 0;
    /**
     * This method is a constructor for a cellphone which takes in the cellphones
     * serial number, CPU capacity, and a length for the array of tasks apart of this
     * cellphone.
     *
     * @param serialNumber The serial number for a cellphone.
     * @param cpuCapacity  The CPU capacity for a cellphone.
     * @param taskLength   The length of the array of tasks for a cellphone.
     */
    public CellPhone(int serialNumber, int cpuCapacity, int taskLength) {
        super(serialNumber, cpuCapacity, taskLength);
    }
    /**
     * This method is a constructor for a cellphone which takes in the cellphones
     * serial number, and CPU capacity. Defaults the length of the array of tasks to
     * 10.
     *
     * @param serialNumber The serial number for a cellphone.
     * @param cpuCapacity  The CPU capacity for a cellphone.
     */
    public CellPhone(int serialNumber, int cpuCapacity) {
        super(serialNumber, cpuCapacity, 10);
    }
    /**
     * This method determines if a task can be added to the cell phone's tasks array, based on available
     * slots in the task array and if their is enough CPU remaining in the cellphone.
     *
     * @param task The task to be evaluated for addition.
     * @return true if there is enough CPU remaining and an empty slot for the task, false otherwise.
     */
    @Override
    public boolean canAddTask(Task task) {
        boolean nullInArray = false;
        boolean cpuGood = false;
        for (int i = 0; i < this.tasks.length; i++) {
            if (tasks[i] == null) {
                nullInArray = true;
            }
        }
        if (task.getCpuCost() <= this.cpuRemaining) {
            cpuGood = true;
        }
        return (nullInArray && cpuGood);
    }
    /**
     * This method adds a task to the cellphone's array of tasks, if the task can be added.
     * The CPU of the cellphone is update to reflect any addition of a new task.
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
            if (tasks[i] == null) {
                tasks[i] = task;
                this.cpuRemaining -= task.getCpuCost();
                return true;
            }
        } return false;
    }
     /**
     * A method to process a task given and adds it's CPU cost to the cpu remaining
     * of the cellphone. Increments the number of tasks completed each time a task is
     * processed.
     *
     * @param task The task to be processed.
     * @return true if the task was found and processed, false otherwise.
     */
    @Override
    public boolean processTask(Task task) {
        boolean taskprocessed = super.processTask(task);
        if (taskprocessed) {
            tasksCompleted += 1;
        }
        return taskprocessed;
    }
     /**
     * Method to determine whether two cellphones are equal to each other.
     * This is done by comparing whether the two cellphones have the same serial number,
     * CPU capacity, CPU remaining, and tasks completed.
     *
     * @param object The object to which an instance of cellphone will be compared to.
     * @return true if the cellphones are the same, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (!super.equals(object) || !(this.getClass().equals(object.getClass()))) {
            return false;
        }
        CellPhone other = (CellPhone) object;
        return this.tasksCompleted == other.tasksCompleted;
    }
    /**
     * Returns a string representation of a cellphone.
     *
     * @return A string containing the serial number, CPU remaining, CPU
     * capacity of a cellphone, and tasks remaining.
     */
    @Override
    public String toString() {
        String cellPhoneString = String.format(super.toString()
            + " It has completed %d tasks.", tasksCompleted);
        return cellPhoneString;
    }
}