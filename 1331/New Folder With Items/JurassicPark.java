/**
 * Test driver file in order to test the Dinosaur, Pterodactyl, Pack, and Velociraptor classes.
 * Creates 2 new instances of Dinosaur, 2 new instances of Pterodactyl, 2 new instances of
 * Velociraptor, and a new instance of Pack. It creates 2 copies of instances.
 *
 * @author nhasan36
 * @version 1.0
 */
public class JurassicPark {
    /**
     * The main method where the different instances of the different classes are created
     * and tested.
     *
     * @param args command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        Dinosaur mommydino = new Dinosaur("mommydino", 30.6, 45.8, 8999.9);
        Dinosaur babydino = new Dinosaur("babydino", 15.3, 20.5, 3000.7);

        Pterodactyl mommyptero = new Pterodactyl("mommyptero", 8.0);
        Pterodactyl babyptero = new Pterodactyl("babyptero");

        Pack pack1 = new Pack(5, "Pack1");
        Velociraptor mommyraptor = new Velociraptor("mommyraptor", 6.4, 8.3, 200.2, 35, pack1);
        Velociraptor babyraptor = new Velociraptor("babyraptor", 7);

        Dinosaur mommyCopy = new Dinosaur(mommydino);
        System.out.println(mommyCopy.toString());
        Velociraptor raptorCopy = new Velociraptor(mommyraptor);
        System.out.println(raptorCopy.toString());

        mommyCopy.setHeight(60);
        System.out.println(mommyCopy.toString());
        raptorCopy.setSpeed(55);
        System.out.println(raptorCopy.toString());

        System.out.println(mommydino.buildEnclosure());
        System.out.println(babydino.buildEnclosure());
        System.out.println(mommyptero.buildEnclosure());
        System.out.println(babyptero.buildEnclosure());
        System.out.println(mommyraptor.buildEnclosure());
        System.out.println(babyraptor.buildEnclosure());

        System.out.println(mommyraptor.toString());
        System.out.println(babyraptor.toString());
    }
}