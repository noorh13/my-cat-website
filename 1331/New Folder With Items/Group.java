/**
 * This class represents a group of visitors visiting an attraction at the
 * same time. Contains a constructor method, and methods to return the size
 * of the group and a string representation of the group.
 *
 * @author nhasan36
 * @version 1.0
 */
public class Group {
    private final String[] people;
    /**
     * Constructor method to creates a new instance of Group with the passed
     * in parameter. It is set to an array of length 0 if the input is null and
     * creates a deep copy of the input if not null.
     *
     * @param people The names of the visitors in the group.
     */
    public Group(String[] people) {
        if (people != null) {
            this.people = new String[people.length];
            for (int i = 0; i < people.length; i++) {
                this.people[i] = people[i];
            }
        } else {
            this.people = new String[0];
        }
    }
    /**
     * Returns the size of the group, which is the same as the length of
     * the people array.
     *
     * @return The size of the group.
     */
    public int size() {
        return this.people.length;
    }
    @Override
    public String toString() {
        if (this.people.length == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (String person : people) {
            result.append(person).append("/");
        }
        return result.substring(0, result.length() - 1);
    }
}