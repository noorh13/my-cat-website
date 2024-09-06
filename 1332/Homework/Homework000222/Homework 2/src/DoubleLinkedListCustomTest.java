import junit.framework.AssertionFailedError;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.Assert.*;

@SuppressWarnings("SizeReplaceableByIsEmpty")
public class DoubleLinkedListCustomTest {

    private DoublyLinkedList<IndexItem> list;

    @Before
    public void setUp() {
        list = new DoublyLinkedList<>();
    }

    @Test
    public void testAddAtIndex() {
        list.addAtIndex(0, new IndexItem(1));   // start
        list.addAtIndex(0, new IndexItem(0));   // start
        list.addAtIndex(2, new IndexItem(4));   // end
        list.addAtIndex(3, new IndexItem(5));   // end
        list.addAtIndex(2, new IndexItem(3));   // middle
        list.addAtIndex(2, new IndexItem(2));   // middle

        assertEquals(6, list.size());
        assertValidLinkedList(list);
    }

    @Test
    public void test1000ToFront() {
        for (int i = 0; i < 1000; i++) {
            list.addToFront(new IndexItem(1000-i));
        }

        assertEquals(1000, list.size());
        assertValidLinkedList(list);
    }

    @Test
    public void test1000ToBack() {
        for (int i = 0; i < 1000; i++) {
            list.addToBack(new IndexItem(i));
        }

        assertEquals(1000, list.size());
        assertValidLinkedList(list);
    }

    @Test
    public void testInsert1000() {
        list.addToFront(new IndexItem(0));
        for (int i = 0; i < 999; i++) {
            list.addAtIndex(1, new IndexItem(1000-i));
        }

        assertEquals(1000, list.size());
        assertValidLinkedList(list);
    }

    @Test
    public void testAddAtWrongIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.addAtIndex(-1, new IndexItem(1)));
        assertThrows(IndexOutOfBoundsException.class, () -> list.addAtIndex(1, new IndexItem(0)));

        for (int i = 0; i < 5; i++) {
            list.addToBack(new IndexItem(i));
        }

        assertThrows(IndexOutOfBoundsException.class, () -> list.addAtIndex(-1, new IndexItem(0)));
        assertThrows(IndexOutOfBoundsException.class, () -> list.addAtIndex(6, new IndexItem(0)));

        assertEquals(5, list.size());
        assertValidLinkedList(list);
    }

    @Test
    public void testAddNull() {
        assertThrows(IllegalArgumentException.class, () -> list.addAtIndex(0, null));
        assertThrows(IllegalArgumentException.class, () -> list.addToBack(null));
        assertThrows(IllegalArgumentException.class, () -> list.addToFront(null));

        for (int i = 0; i < 5; i++) {
            list.addToBack(new IndexItem(i));
        }

        assertThrows(IllegalArgumentException.class, () -> list.addAtIndex(0, null));
        assertThrows(IllegalArgumentException.class, () -> list.addToBack(null));
        assertThrows(IllegalArgumentException.class, () -> list.addToFront(null));

        assertEquals(5, list.size());
        assertValidLinkedList(list);
    }

    @Test
    public void testRemoveIndex() {
        list.addToFront(new IndexItem(0));
        list.removeAtIndex(0);

        assertEmpty(list);

        list.addToFront(new IndexItem(1));
        list.addToFront(new IndexItem(0));
        list.removeAtIndex(1);
        assertHead(list.getHead());
        assertTail(list.getTail());
        assertSame(list.getHead(), list.getTail());
        list.removeAtIndex(0);

        assertEmpty(list);

        list.addToFront(new IndexItem(1));
        list.addToFront(new IndexItem(0));
        list.removeAtIndex(0);
        assertHead(list.getHead());
        assertTail(list.getTail());
        assertSame(list.getHead(), list.getTail());
        list.removeAtIndex(0);

        assertEmpty(list);

        list.addToBack(new IndexItem(342341));
        list.addToBack(new IndexItem(0));
        list.addToBack(new IndexItem(1));
        list.addToBack(new IndexItem(-82329));
        list.addToBack(new IndexItem(2));
        list.addToBack(new IndexItem(3));
        list.addToBack(new IndexItem(4));
        list.addToBack(new IndexItem(-84394021));

        list.removeAtIndex(0);
        list.removeAtIndex(2);
        list.removeAtIndex(5);

        assertEquals(5, list.size());
        assertValidLinkedList(list);
    }

    @Test
    public void testRemoveFront() {
        list.addToFront(new IndexItem(0));
        list.removeFromFront();

        assertEmpty(list);

        list.addToFront(new IndexItem(1));
        list.addToFront(new IndexItem(0));
        list.removeFromFront();
        assertHead(list.getHead());
        assertTail(list.getTail());
        assertSame(list.getHead(), list.getTail());
        list.removeFromFront();

        assertEmpty(list);

        for (int i = 0; i < 25; i++) {
            list.addToBack(new IndexItem(i));
        }

        for (int i = 0; i < 25; i++) {
            list.removeFromFront();
            assertEquals(24-i, list.size());
            assertValidLinkedList(list);
        }

        assertEmpty(list);
    }

    @Test
    public void testRemoveBack() {
        list.addToFront(new IndexItem(0));
        list.removeFromBack();

        assertEmpty(list);

        list.addToFront(new IndexItem(1));
        list.addToFront(new IndexItem(0));
        list.removeFromBack();
        assertHead(list.getHead());
        assertTail(list.getTail());
        assertSame(list.getHead(), list.getTail());
        list.removeFromBack();

        assertEmpty(list);

        for (int i = 0; i < 25; i++) {
            list.addToBack(new IndexItem(i));
        }

        for (int i = 0; i < 25; i++) {
            list.removeFromBack();
            assertEquals(24-i, list.size());
            assertValidLinkedList(list);
        }

        assertEmpty(list);
    }

    @SuppressWarnings("DuplicatedCode")
    @Test
    public void removeWrongIndex() {
        assertThrows(NoSuchElementException.class, () -> list.removeFromFront());
        assertEmpty(list);

        assertThrows(NoSuchElementException.class, () -> list.removeFromBack());
        assertEmpty(list);

        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAtIndex(0));
        assertEmpty(list);

        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAtIndex(-1));
        assertEmpty(list);

        for (int i = 0; i < 5; i++) {
            list.addToBack(new IndexItem(i));
        }

        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAtIndex(5));
        assertEquals(5, list.size());

        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAtIndex(-1));
        assertEquals(5, list.size());

        for (int i = 0; i < 5; i++) {
            list.removeFromFront();
        }

        assertEmpty(list);

        assertThrows(NoSuchElementException.class, () -> list.removeFromFront());
        assertEmpty(list);

        assertThrows(NoSuchElementException.class, () -> list.removeFromBack());
        assertEmpty(list);

        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAtIndex(0));
        assertEmpty(list);

        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAtIndex(-1));
        assertEmpty(list);

        assertValidLinkedList(list);
    }

    @Test
    public void testClear() {
        for (int i = 0; i < 50; i++) {
            list.addToBack(new IndexItem(i));
        }

        list.clear();
        assertEmpty(list);

        list.addToFront(new IndexItem(0));

        list.clear();
        assertEmpty(list);

        list.clear();
        assertEmpty(list);

        list.addToFront(new IndexItem(0));
        list.removeFromFront();

        list.clear();
        assertEmpty(list);
    }

    @Test
    public void testRemoveLastOccurrenceWithOneItem() {
        IndexItem element = new IndexItem(0);
        list.addToFront(element);
        assertSame(element, list.removeLastOccurrence(new IndexItem(0)));

        assertEmpty(list);
    }

    @Test
    public void testRemoveLastOccurrenceWithDuplicatesItems() {
        IndexItem target = new IndexItem(3);

        list.addToBack(new IndexItem(0));
        list.addToBack(new IndexItem(1));
        list.addToBack(new IndexItem(2));
        list.addToBack(new IndexItem(3));
        list.addToBack(new IndexItem(4));
        list.addToBack(new IndexItem(5));
        list.addToBack(target);
        list.addToBack(new IndexItem(6));

        assertSame(target, list.removeLastOccurrence(new IndexItem(3)));
        assertEquals(7, list.size());
        assertValidLinkedList(list);

        list.clear();

        IndexItem[] itemsInOrder = new IndexItem[30];
        for (int i = 0; i < 50; i++) {
            list.addToBack(new IndexItem(i));
        }
        for (int i = 0; i < 30; i++) {
            itemsInOrder[i] = new IndexItem(-5843412);
            list.addToBack(itemsInOrder[i]);
        }
        for (int i = 0; i < 50; i++) {
            list.addToBack(new IndexItem(80+i));
        }

        assertEquals(130, list.size());

        for (int i = 29; i >= 0; i--) {
            assertSame(itemsInOrder[i], list.removeLastOccurrence(new IndexItem(-5843412)));
            assertEquals(100+i, list.size());
        }

        assertValidLinkedList(list);
    }

    @Test
    public void testRemoveMissingItem() {
        assertThrows(NoSuchElementException.class, () -> list.removeLastOccurrence(new IndexItem(0)));

        for (int i = 0; i < 10; i++) {
            list.addToBack(new IndexItem(i));
        }

        assertThrows(NoSuchElementException.class, () -> list.removeLastOccurrence(new IndexItem(-1)));
        assertEquals(10, list.size());
        assertValidLinkedList(list);

        assertThrows(NoSuchElementException.class, () -> list.removeLastOccurrence(new IndexItem(10)));
        assertEquals(10, list.size());
        assertValidLinkedList(list);

        list.clear();

        assertThrows(NoSuchElementException.class, () -> list.removeLastOccurrence(new IndexItem(0)));

        assertEmpty(list);
    }

    @Test
    public void testRemoveNullItem() {
        assertThrows(IllegalArgumentException.class, () -> list.removeLastOccurrence(null));
        assertEmpty(list);

        for (int i = 0; i < 5; i++) {
            list.addToBack(new IndexItem(i));
        }

        assertThrows(IllegalArgumentException.class, () -> list.removeLastOccurrence(null));
        assertValidLinkedList(list);

        assertThrows(IllegalArgumentException.class, () -> list.removeLastOccurrence(null));
        assertValidLinkedList(list);
    }

    @Test
    public void testEmptyArray() {
        Object[] array = list.toArray();
        assertEquals(0, array.length);
        assertEmpty(list);

        array = list.toArray();
        assertEquals(0, array.length);
        assertEmpty(list);
    }

    @Test
    public void testOneItemArray() {
        IndexItem element = new IndexItem(1232134);

        list.addToFront(element);
        Object[] array = list.toArray();
        assertEquals(1, array.length);
        assertSame(element, array[0]);
        assertEquals(1, list.size());
        assertSame(list.getHead().getData(), array[0]);
        assertSame(list.getTail().getData(), array[0]);

        array = list.toArray();
        assertEquals(1, array.length);
        assertSame(element, array[0]);
        assertEquals(1, list.size());
        assertSame(list.getHead().getData(), array[0]);
        assertSame(list.getTail().getData(), array[0]);
    }

    @Test
    public void testManyItemArray() {
        for (int i = 0; i < 250; i++) {
            list.addToBack(new IndexItem(i));
        }

        Object[] array = list.toArray();

        for (int i = 0; i < 250; i++) {
            assertSame(array[i], list.get(i));
            assertEquals(i, list.get(i).index);
        }

        assertSame(list.getHead().getData(), array[0]);
        assertSame(list.getTail().getData(), array[249]);
    }

    private void assertThrows(Class<? extends Exception> exceptionClass, Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            if (!exceptionClass.isInstance(e)) {
                throw new AssertionFailedError("Runnable did not throw correct exception");
            }
            return;
        }
        throw new AssertionFailedError("Runnable did not throw exception");
    }

    private void assertEmpty(DoublyLinkedList<?> list) {
        assertTrue(list.isEmpty());
        assertNull(list.getHead());
        assertNull(list.getTail());
        assertEquals(0, list.size());
    }

    private void assertValidLinkedList(DoublyLinkedList<IndexItem> list) {
        assertHead(list.getHead());
        assertTail(list.getTail());

        assertEquals(list.isEmpty(), list.size() == 0);
        if (list.isEmpty()) { return; }

        int index = list.getHead().getData().index;
        DoublyLinkedListNode<IndexItem> ptr = list.getHead();
        for (int i = 1; i < list.size(); i++) {
            ptr = ptr.getNext();

            assertSame(ptr.getData(), list.get(i));
            assertTrue(ptr.getData().index > index);
            index = ptr.getData().index;
        }
    }

    private void assertHead(DoublyLinkedListNode<?> node) {
        if (list.size() != 0) {
            assertNull(node.getPrevious());
            assertSame(node.getData(), list.get(0));
        } else {
            assertNull(node);
        }
    }

    private void assertTail(DoublyLinkedListNode<?> node) {
        if (list.size() != 0) {
            assertNull(node.getNext());
            assertSame(node.getData(), list.get(list.size() - 1));
        } else {
            assertNull(node);
        }
    }

    private static class IndexItem {
        int index;

        public IndexItem(int index) {
            this.index = index;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof IndexItem indexItem)) return false;
            return index == indexItem.index;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(index);
        }
    }
}
