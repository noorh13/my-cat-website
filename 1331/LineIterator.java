import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Provides an iterator for the Line class, allowing traversal of the collection of persons each holding a parcel.
 * This iterator ensures that the parcels can be accessed in the order the persons appear in the line.
 *
 * @param <T> The type of parcels held by persons in the line.
 * @author nhasan36
 * @version 1.0
 */
public class LineIterator<T> implements Iterator<T> {
    private Person<T> nextPerson;
    /**
     * Constructs a new iterator for the given line. Initializes the iterator to start at the first person
     * in the line.
     *
     * @param line The line to iterate over.
     */
    public LineIterator(Line<T> line) {
        this.nextPerson = line.getFirstPerson();
    }
    @Override
    public boolean hasNext() {
        return nextPerson != null;
    }
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in line");
        }
        T parcel = nextPerson.getParcel();
        nextPerson = nextPerson.getNextPerson();
        return parcel;
    }
}