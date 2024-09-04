/**
 * Interface that declares a method to allow for the behavior of addmitance
 * to be required for an implementing class.
 *
 * @author nhasan36
 * @version 1.0
 */
public interface Admittable {
     /**
     * Admits a group of visitors based on their names.
     *
     * @param visitorsNames The names of the visitors to be admitted.
     */
    void admit(String[] visitorsNames);
}