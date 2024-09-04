import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Quickly written set of tests for ArrayList that ensures the proper errors are thrown and the growth mechanism works
 *
 * @author Theo Halpern
 * @version 0.1
 */
public class ArrayListStudentTest2 {

    private static final int TIMEOUT = 200;
    private ArrayList<String> list;
    private ArrayList<String> list2;

    @Before
    public void setUp() {
        list = new ArrayList<>();
        list.addToBack("added first");
        list.addToBack("added second");
        list.addToBack("added third");
        list.addToBack("added forth");

        list2 = new ArrayList<>();
    }

    @Test(timeout = TIMEOUT)
    public void messageFromTheCreator() {
        System.out.println("Thank you for using TheoTest");
        System.out.println("Send any issues to thalpern3@gatech.edu");
        System.out.println("consider connecting with Theo on LinkedIn");
        System.out.println("https://www.linkedin.com/in/theo-halpern/");
    }

    @Test(timeout = TIMEOUT)
    public void testInitializationWithInteger() {
        ArrayList<Integer> integerList = new ArrayList<>();
        assertEquals(0, integerList.size());
        assertArrayEquals(new Integer[ArrayList.INITIAL_CAPACITY],
                integerList.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testInitializationWithExampleUserClass() {
        ArrayList<ExampleUserClass> userList = new ArrayList<>();
        assertEquals(0, userList.size());
        assertArrayEquals(new ExampleUserClass[ArrayList.INITIAL_CAPACITY],
                userList.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void addAtIndexThrowsOutOfBoundsError() {
        assertThrows(IndexOutOfBoundsException.class, () ->
                list.addAtIndex(-1, "tooFarFoward"));
        assertThrows(IndexOutOfBoundsException.class, () ->
                list.addAtIndex(-5, "wayTooFarFoward"));
        assertThrows(IndexOutOfBoundsException.class, () ->
                list.addAtIndex(5, "tooFarBack"));
        assertThrows(IndexOutOfBoundsException.class, () ->
                list.addAtIndex(6, "wayTooFarBack"));
    }

    @Test(timeout = TIMEOUT)
    public void addAtIndexThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                list.addAtIndex(0, null));

    }

    @Test(timeout = TIMEOUT)
    public void addToFrontThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                list.addToFront(null));

    }

    @Test(timeout = TIMEOUT)
    public void addToBackThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                list.addToFront(null));

    }

    @Test(timeout = TIMEOUT)
    public void removeAtIndexThrowsIllegalArgumentException() {
        assertThrows(IndexOutOfBoundsException.class, () ->
                list.removeAtIndex(-1));
        assertThrows(IndexOutOfBoundsException.class, () ->
                list.removeAtIndex(-5));
        assertThrows(IndexOutOfBoundsException.class, () ->
                list.removeAtIndex(5));
        assertThrows(IndexOutOfBoundsException.class, () ->
                list.removeAtIndex(6));

    }

    @Test(timeout = TIMEOUT)
    public void removeFromFrontThrowsIllegalArgumentException() {
        for (int i = 0; i < 4; i++) {
            list.removeFromFront();
        }
        assertThrows(NoSuchElementException.class, () ->
                list.removeFromFront());

    }

    @Test(timeout = TIMEOUT)
    public void removeFromBackThrowsIllegalArgumentException() {
        for (int i = 0; i < 4; i++) {
            list.removeFromBack();
        }
        assertThrows(NoSuchElementException.class, () ->
                list.removeFromBack());

    }

    @Test(timeout = TIMEOUT)
    public void getThrowsIllegalArgumentException() {
        assertThrows(IndexOutOfBoundsException.class, () ->
                list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () ->
                list.get(-5));
        assertThrows(IndexOutOfBoundsException.class, () ->
                list.get(5));
        assertThrows(IndexOutOfBoundsException.class, () ->
                list.get(6));

    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexWith1000Items() {
        for (int i = 0; i < 1000; i++) {
            list.addAtIndex(2, "this is the " + (1001 - i) + "th item");
        }
        assertEquals(1004, list.size());

        for (int i = 2; i < 1002; i++) {
            assertEquals("this is the " + (i) + "th item", list.get(i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFrontWith1000Items() {
        for (int i = 0; i < 1000; i++) {
            list.addToFront("this is the " + (1000 - i - 1) + "th item");
        }
        assertEquals(1004, list.size());

        for (int i = 0; i < 1000; i++) {
            assertEquals("this is the " + (i) + "th item", list.get(i));
        }
    }
    @Test(timeout = TIMEOUT)
    public void testAddToBackWith1000Items() {

        for (int i = 0; i < 1000; i++) {
            list.addToBack("this is the " + (i + 4) + "th item");
        }
        assertEquals(1004, list.size());

        for (int i = 4; i < 1000; i++) {
            assertEquals("this is the " + (i) + "th item", list.get(i));
        }
    }

    private static class ExampleUserClass {
        private int age;
        private String name;
        private UUID id;
        private ArrayList<ExampleUserClass> friends;

        /**
         * Creates a new user with friends
         *
         * @param age     the user's age
         * @param name    the user's name
         * @param friends an ArrayList of the user's friends
         */
        public ExampleUserClass(int age, String name, ArrayList<ExampleUserClass> friends) {
            this.age = age;
            this.name = name;
            this.id = UUID.randomUUID();
            this.friends = friends;
            for (int i = 0; i < friends.size(); i++) {
                friends.get(i).friends.addToBack(this);
            }
        }
    }

    // Every test case from this point on is one I created myself. (Not included in original download)

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexWith1000Items() {
        String removedData = list.removeAtIndex(2);
        assertEquals("added third", removedData);
        System.out.println(removedData);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {
        String removedData = list.removeFromFront();
        assertEquals("added first", removedData);
        System.out.println(removedData);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack() {
        String removedData = list.removeFromBack();
        assertEquals("added forth", removedData);
        System.out.println(removedData);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromIndexThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () ->
                list.removeAtIndex(6));
    }

    @Test(timeout = TIMEOUT)
    public void testGetIndex() {
        String retrievedData = list.get(2);
        assertEquals("added third", retrievedData);
        System.out.print(retrievedData);
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        assertFalse(list.isEmpty());
        assertTrue(list2.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        list.clear();
        assertEquals(0, list.size());
    }
}
