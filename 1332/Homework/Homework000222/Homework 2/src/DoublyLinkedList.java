/**
 * Your implementation of a non-circular DoublyLinkedList with a tail pointer.
 *
 * @author Noor Hasan
 * @version 1.0
 * @userid nhasan36
 * @GTID 903791273
 *
 * Collaborators: None
 *
 * Resources: None
 */
public class DoublyLinkedList<T> {

    // Do not add new instance variables or modify existing ones.
    private DoublyLinkedListNode<T> head;
    private DoublyLinkedListNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the specified index. Don't forget to consider whether
     * traversing the list from the head or tail is more efficient!
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index is out of bounds!");
        }
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null!");
        }
        if (index == 0) {
            addToFront(data);
            return;
        }
        if (index == size) {
            addToBack(data);
            return;
        }
        DoublyLinkedListNode<T> curr;
        if (index < size / 2) {
            curr = head;
            for (int j = 0; j < index; j++) {
                curr = curr.getNext();
            }
        } else {
            curr = tail;
            for (int j = size - 1; j > index; j--) {
                curr = curr.getPrevious();
            }
        }
        DoublyLinkedListNode<T> x = new DoublyLinkedListNode<>(data, curr.getPrevious(), curr);
        curr.getPrevious().setNext(x);
        curr.setPrevious(x);
        size++;
    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null!");
        }
        if (size == 0) {
            head = new DoublyLinkedListNode<>(data, null, null);
            tail = head;
        } else {
            DoublyLinkedListNode<T> x = new DoublyLinkedListNode<>(data, null, head);
            head.setPrevious(x);
            head = x;
        }
        size++;
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null!");
        }
        if (size == 0) {
            head = new DoublyLinkedListNode<>(data, null, null);
            tail = head;
        } else {
            DoublyLinkedListNode<T> x = new DoublyLinkedListNode<>(data, tail, null);
            tail.setNext(x);
            tail = x;
        }
        size++;
    }

    /**
     * Removes and returns the element at the specified index. Don't forget to
     * consider whether traversing the list from the head or tail is more
     * efficient!
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index is out of bounds!");
        }
        if (index == 0) {
            return removeFromFront();
        }
        if (index == size - 1) {
            return removeFromBack();
        }
        DoublyLinkedListNode<T> curr;
        if (index < size / 2) {
            curr = head;
            for (int j = 0; j < index; j++) {
                curr = curr.getNext();
            }
        } else {
            curr = tail;
            for (int j = size - 1; j > index; j--) {
                curr = curr.getPrevious();
            }
        }
        curr.getPrevious().setNext(curr.getNext());
        curr.getNext().setPrevious(curr.getPrevious());
        size--;
        return curr.getData();
    }
    /**
     * Removes and returns the first element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("The list is empty.");
        }
        DoublyLinkedListNode<T> curr = head;
        head = head.getNext();
        if (head == null) {
            tail = null;
        } else {
            head.setPrevious(null);
        }
        size--;
        return curr.getData();
    }
    /**
     * Removes and returns the last element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("The list is empty.");
        }
        DoublyLinkedListNode<T> curr = tail;
        tail = tail.getPrevious();
        if (tail == null) {
            head = null;
        } else {
            tail.setNext(null);
        }
        size--;
        return curr.getData();
    }
    /**
     * Returns the element at the specified index. Don't forget to consider
     * whether traversing the list from the head or tail is more efficient!
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index is out of bounds!");
        }
        if (index == 0) {
            return head.getData();
        }
        if (index == size - 1) {
            return tail.getData();
        }
        DoublyLinkedListNode<T> curr;
        if (index < size / 2) {
            curr = head;
            for (int j = 0; j < index; j++) {
                curr = curr.getNext();
            }
        } else {
            curr = tail;
            for (int j = size - 1; j > index; j--) {
                curr = curr.getPrevious();
            }
        }
        return curr.getData();
    }
    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(1) if data is in the tail and O(n) for all other cases.
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        }
        DoublyLinkedListNode<T> curr = tail;
        while (curr != null) {
            if (curr.getData().equals(data)) {
                if (curr == head && curr == tail) {
                    head = null;
                    tail = null;
                } else if (curr == head) {
                    head = head.getNext();
                    if (head != null) {
                        head.setPrevious(null);
                    }
                } else if (curr == tail) {
                    tail = tail.getPrevious();
                    if (tail != null) {
                        tail.setNext(null);
                    }
                } else {
                    curr.getPrevious().setNext(curr.getNext());
                    curr.getNext().setPrevious(curr.getPrevious());
                }
                size--;
                return curr.getData(); // Return the removed data
            }
            curr = curr.getPrevious(); // Move to the previous node
        }
        throw new java.util.NoSuchElementException("Data not found in the list.");
    }
    /**
     * Returns an array representation of the linked list. If the list is
     * size 0, return an empty array.
     *
     * Must be O(n) for all cases.
     *
     * @return an array of length size holding all of the objects in the
     * list in the same order
     */
    public Object[] toArray() {
        if (size == 0) {
            return new Object[0];
        }
        Object[] array = new Object[size];
        DoublyLinkedListNode<T> current = head;
        for (int j = 0; j < size; j++) {
            array[j] = current.getData();
            current = current.getNext();
        }
        return array;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public DoublyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public DoublyLinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}

