/**
 * Represents a person in a queue, holding a parcel of type T. This class is a component of
 * a singly linked list where each person has a parcel and a reference to the next person in line.
 *
 * @param <T> The type of the parcel this person carries.
 * @author nhasan36
 * @version 1.0
 */
public class Person<T> {
    private T parcel;
    private Person<T> nextPerson;
    /**
     * Constructs a new Person with a parcel and a reference to the next person in line.
     *
     * @param parcel The parcel that this person carries. Cannot be null.
     * @param nextPerson The next person in the queue. Can be null if this person is the last in line.
     */
    public Person(T parcel, Person<T> nextPerson) {
        setParcel(parcel);
        this.nextPerson = nextPerson;
    }
    /**
     * Constructs a new Person with a parcel and no next person (null nextPerson).
     * This is used for adding a person at the end of the line.
     *
     * @param parcel The parcel that this person carries. Cannot be null.
     */
    public Person(T parcel) {
        this(parcel, null);
    }
    /**
     * Returns the parcel that this person carries.
     *
     * @return The parcel held by this person.
     */
    public T getParcel() {
        return parcel;
    }
    /**
     * Sets the parcel that this person carries. The parcel cannot be null.
     *
     * @param parcel The new parcel for this person to carry.
     */
    public void setParcel(T parcel) {
        if (parcel == null) {
            throw new IllegalArgumentException("Parcel cannot be null");
        }
        this.parcel = parcel;
    }
    /**
     * Returns the next person in line.
     *
     * @return The next person, or null if there is no next person.
     */
    public Person<T> getNextPerson() {
        return nextPerson;
    }
    /**
     * Sets the reference to the next person in line. This can be used to add someone
     * into the line or remove someone by changing the next person reference.
     *
     * @param nextPerson The next person in line, or null if this is the last person.
     */
    public void setNextPerson(Person<T> nextPerson) {
        this.nextPerson = nextPerson;
    }
}