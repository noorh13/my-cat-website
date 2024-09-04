import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Represents a line of persons each holding a parcel. This class implements a linked list where each
 * person is a node with a parcel and a reference to the next person in the queue. This class
 * supports various operations to manipulate the line such as adding, removing, and accessing parcels.
 *
 * @param <T> The type of the parcels that persons in the line carry.
 * @author nhasan36
 * @version 1.0
 */
public class Line<T> implements List<T> {
    private Person<T> firstPerson;
    private int size;
    /**
     * Constructs an empty line with no persons.
     */
    public Line() {
        firstPerson = null;
        size = 0;
    }
    /**
     * Constructs a line with the parcels provided in an array. The order of persons in the line
     * will correspond to the order of parcels in the array.
     *
     * @param parcels An array of parcels to initialize the line.
     */
    public Line(T[] parcels) {
        this();
        if (parcels == null) {
            throw new IllegalArgumentException("Array of parcels cannot be null.");
        }
        for (T parcel : parcels) {
            if (parcel == null) {
                throw new IllegalArgumentException("Parcels in the array cannot be null.");
            }
            add(parcel);
        }
    }
    /**
    * Removes the first occurrence of the specified element from this line if it is present.
    *
    * @param element the element to be removed from the line, must not be null.
    * @return The parcel of the removed person if the element is found.
    */
    public T remove(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null.");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("Line is empty, cannot remove element.");
        }

        if (firstPerson.getParcel().equals(element)) {
            return remove();
        }
        Person<T> current = firstPerson;
        while (current.getNextPerson() != null) {
            if (current.getNextPerson().getParcel().equals(element)) {
                T removedParcel = current.getNextPerson().getParcel();
                current.setNextPerson(current.getNextPerson().getNextPerson());
                size--;
                return removedParcel;
            }
            current = current.getNextPerson();
        }

        throw new NoSuchElementException("Element not found");
    }
    @Override
    public void add(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null.");
        }
        if (isEmpty()) {
            firstPerson = new Person<>(element);
        } else {
            Person<T> current = firstPerson;
            while (current.getNextPerson() != null) {
                current = current.getNextPerson();
            }
            current.setNextPerson(new Person<>(element));
        }
        size++;
    }
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        if (index == 0) {
            firstPerson = new Person<>(element, firstPerson);
        } else {
            Person<T> current = firstPerson;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNextPerson();
            }
            current.setNextPerson(new Person<>(element, current.getNextPerson()));
        }
        size++;
    }
    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Line is empty");
        }
        T parcel = firstPerson.getParcel();
        firstPerson = firstPerson.getNextPerson();
        size--;
        return parcel;
    }
    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        if (index == 0) {
            return remove();
        }
        Person<T> current = firstPerson;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNextPerson();
        }
        T parcel = current.getNextPerson().getParcel();
        current.setNextPerson(current.getNextPerson().getNextPerson());
        size--;
        return parcel;
    }
    @Override
    public boolean contains(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null.");
        }
        return indexOf(element) != -1;
    }
    @Override
    public void clear() {
        firstPerson = null;
        size = 0;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        Person<T> current = firstPerson;
        for (int i = 0; i < index; i++) {
            current = current.getNextPerson();
        }
        return current.getParcel();
    }
    @Override
    public Iterator<T> iterator() {
        return new LineIterator<>(this);
    }
    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        if (element == null) {
            throw new IllegalArgumentException("Parcel cannot be null.");
        }

        Person<T> current = firstPerson;
        for (int i = 0; i < index; i++) {
            current = current.getNextPerson();
        }
        T oldParcel = current.getParcel();
        current.setParcel(element);
        return oldParcel;
    }
    /**
    * Retrieves the first person in the line.
    *
    * @return The first person in the line or null if the line is empty.
    */
    protected Person<T> getFirstPerson() {
        return firstPerson;
    }
    private void reverse(Person<T> current, Person<T> previous) {
        if (current == null) {
            firstPerson = previous;
            return;
        }
        Person<T> next = current.getNextPerson();
        current.setNextPerson(previous);
        reverse(next, current);
    }
    public void reverse() {
        reverse(firstPerson, null);
    }
    public T[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (T item : this) {  // Using iterator implicitly
            result[i++] = item;
        }
        return (T[]) result;
    }
}