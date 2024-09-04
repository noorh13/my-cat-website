import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
/**
 * Represents a team consisting of members.
 * This class provides methods to manage and interact with the team's members.
 *
 * @author nhasan36
 * @version 1.0
 */
public class Team {
    private Member[] members;
    /**
     * Constructs a new Team object with a deep copy of the provided members array.
     *
     * @param array The array of Member instances to be copied into the team.
     */
    public Team(Member[] array) throws IllegalArgumentException {
        if (array == null) {
            throw new IllegalArgumentException("The array is null");
        }
        this.members = new Member[array.length];
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                throw new IllegalArgumentException("The array contains null elements");
            }
            this.members[i] = new Member(array[i]);
        }
    }
    /**
     * Sorts the members of the team in non-decreasing order using the merge sort algorithm.
     * This method is a part of the class's sorting operations and it sorts the members array in place.
     */
    public void mergeSortMembers() {
        this.members = mergeSort(this.members);
    }
    /**
     * Helper method to perform merge sort on a given array of members.
     *
     * @param array The array of Member instances to be sorted.
     * @return A new sorted array of Member instances.
     */
    private Member[] mergeSort(Member[] array) {
        if (array.length <= 1) {
            return array;
        }
        int mid = array.length / 2;
        Member[] left = HWUtils.copyOfRange(array, 0, mid);
        Member[] right = HWUtils.copyOfRange(array, mid, array.length);
        left = mergeSort(left);
        right = mergeSort(right);
        return HWUtils.merge(left, right);
    }
    /**
     * Merges multiple arrays of members with the current team's members array and sorts them.
     *
     * @param input A 2-D array of Member instances to be merged and sorted with the team's members.
     */
    public void mergeAllMembers(Member[][] input) {
        for (Member[] array : input) {
            this.members = HWUtils.merge(this.members, mergeSort(array));
        }
    }
    /**
     * Searches for a specific member in the team's members array.
     *
     * @param member The Member instance to search for in the team.
     * @return The matching Member instance from the team.
     */
    public Member searchMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("Member cannot be null");
        }
        mergeSortMembers();
        return binarySearch(0, members.length - 1, member);
    }
    /**
     * Helper method to perform a binary search on the members array.
     *
     * @param left The left boundary for the search interval.
     * @param right The right boundary for the search interval.
     * @param target The Member instance to search for.
     * @return The found Member instance.
     */
    private Member binarySearch(int left, int right, Member target) {
        if (right < left) {
            throw new NoSuchElementException("Member not found");
        }
        int mid = left + (right - left) / 2;
        Member midMember = members[mid];
        int comparison = midMember.compareTo(target);
        if (comparison == 0) {
            return midMember;
        } else if (comparison < 0) {
            return binarySearch(mid + 1, right, target);
        } else {
            return binarySearch(left, mid - 1, target);
        }
    }
    /**
     * Creates a deep copy of the team's members array and returns it in reversed sorted order.
     *
     * @return A new array of Member instances in reversed order.
     */
    public Member[] reverseMembers() {
        Member[] reversed = new Member[members.length];
        for (int i = 0; i < members.length; i++) {
            reversed[i] = new Member(members[members.length - 1 - i]); // Using the copy constructor
        }
        return reversed;
    }
    /**
     * Selects leaders for the FRONTEND and BACKEND subgroups from the team's members.
     * Each subgroup leader is chosen randomly from their respective subgroup members.
     *
     * @return An ArrayList containing two Member instances
     */
    public ArrayList<Member> selectLeaderboard() {
        ArrayList<Member> frontEnd = new ArrayList<>();
        ArrayList<Member> backEnd = new ArrayList<>();
        selectLeaderboardRecursively(0, frontEnd, backEnd);
        if (frontEnd.isEmpty() || backEnd.isEmpty()) {
            throw new NoSuchElementException("Failed to select leaders for both subgroups.");
        }
        Random random = new Random();
        ArrayList<Member> leaders = new ArrayList<>();
        leaders.add(frontEnd.get(random.nextInt(frontEnd.size())));
        leaders.add(backEnd.get(random.nextInt(backEnd.size())));
        return leaders;
    }
    /**
     * Recursively selects members from the team to create two ArrayLists, one for each subgroup.
     * This method is used internally to aid in selecting leaders for the selectLeaderboard method.
     *
     * @param index The current index in the members array being inspected.
     * @param frontEnd An ArrayList to collect members of the FRONTEND subgroup.
     * @param backEnd An ArrayList to collect members of the BACKEND subgroup.
     */
    private void selectLeaderboardRecursively(int index, ArrayList<Member> frontEnd, ArrayList<Member> backEnd) {
        if (index >= members.length) {
            return;
        }
        Member current = members[index];
        if (current.getSubgroup() == Group.FRONTEND) {
            frontEnd.add(current);
        } else if (current.getSubgroup() == Group.BACKEND) {
            backEnd.add(current);
        }

        selectLeaderboardRecursively(index + 1, frontEnd, backEnd);
    }

}