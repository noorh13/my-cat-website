/**
 * Abstract class which is a representation of a device developed by the company.
 * Devices will process Tasks, keep a list, and make sure the CPU cost are withing
 * bounds. Implementations of this class should inherit its functionality.
 * This class contains constructor methods, a method to process tasks, a method to
 * compare two devices to each other, and a method to return a string representation
 * of a device.
 *
 * @author nhasan36
 * @version 1.0
 */
public abstract class Device {
    private final int serialNumber;
    protected final int cpuCapacity;
    protected int cpuRemaining;
    protected Task[] tasks;
    /**
     * A constructor method with a given serial number, CPU capacity, and length of the task array.
     *
     * @param serialNumber The serial number of a device.
     * @param cpuCapacity  The CPU capacity of a device.
     * @param taskLength   The length of the tasks array.
     */
    public Device(int serialNumber, int cpuCapacity, int taskLength) {
        this.tasks = new Task[taskLength];
        this.serialNumber = serialNumber;
        this.cpuCapacity = cpuCapacity;
        this.cpuRemaining = cpuCapacity;
    }
    /**
     * A constructor method with a given serial number and length of the task array.
     * Defaults the CPU capacity and CPU remaining to 512.
     *
     * @param serialNumber The serial number of a device.
     * @param taskLength   The length of the tasks array.
     */
    public Device(int serialNumber, int taskLength) {
        this.tasks = new Task[taskLength];
        this.serialNumber = serialNumber;
        this.cpuCapacity = 512;
        this.cpuRemaining = 512;
    }
    /**
     * A method to determine if a task can be added to a device. With no implementation
     * in this class.
     *
     * @param task The task that can be added or not.
     * @return true if the task can be added, false otherwise.
     */
    public abstract boolean canAddTask(Task task);
    /**
     * A method to add a task to a device if it can be added.
     *
     * @param task The task to be added.
     * @return true if the task was successfully added, false otherwise.
     */
    public abstract boolean addTask(Task task);
    /**
     * A method to process a task given and adds it's CPU cost to the cpu remaining
     * of the device.
     *
     * @param task The task to be processed.
     * @return true if the task was found and processed, false otherwise.
     */
    public boolean processTask(Task task) {
        if (task == null) {
            return false;
        }
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null && tasks[i].equals(task)) {
                tasks[i] = null;
                cpuRemaining += task.getCpuCost();
                System.out.println("Processed: " + task.toString());
                return true;
            }
        } return false;
    }
    /**
     * Method to determine whether two devices are equal to each other.
     * This is done by comparing whether the two devices have the same serial number,
     * CPU capacity, and CPU remaining.
     *
     * @param object The object to which an instance of device will be compared to.
     * @return true if the devices are the same, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!this.getClass().equals(object.getClass())) {
            return false;
        }
        Device other = (Device) object;
        return this.serialNumber == other.serialNumber && this.cpuCapacity
            == other.cpuCapacity && this.cpuRemaining == other.cpuRemaining;
    }
     /**
     * Returns a string representation of a device.
     *
     * @return A string containing the serial number, CPU remaining, and CPU
     * capacity of a device.
     */
    @Override
    public String toString() {
        String deviceToString = String.format("Device with serial number %d has %d of"
            + " %d CPU remaining.", this.serialNumber, this.cpuRemaining, this.cpuCapacity);
        return deviceToString;
    }
}