import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Class that will read and write to the database.
 *
 * @author nhasan36
 * @version 1.0
 */
public class Party {
    /**
    * Private helper method that processes the string of info taken in and returns a PartyMember object.
    *
    * @param info the string of info
    * @return a PartyMember object determined from the parameter
    */
    private static PartyMember processInfo(String info) throws InvalidPartyMemberException, NumberFormatException {
        String[] keyValuePairs = info.split(",\\s*");
        String[] parsedValues = new String[keyValuePairs.length];
        for (int i = 0; i < keyValuePairs.length; i++) {
            String[] keyValue = keyValuePairs[i].split(":\\s*");
            if (keyValue.length < 2) {
                throw new IllegalArgumentException("Key-Value pair not formatted correctly: " + keyValuePairs[i]);
            }
            parsedValues[i] = keyValue[1];
        }
        String characterClass = parsedValues[0];
        String name = parsedValues[1];
        int health = Integer.parseInt(parsedValues[2]);
        int baseAttack = Integer.parseInt(parsedValues[3]);
        if ("Warrior".equals(characterClass)) {
            String weapon = parsedValues[4];
            int armorClass = Integer.parseInt(parsedValues[5]);
            return new Warrior(name, health, baseAttack, weapon, armorClass);
        } else if ("Mage".equals(characterClass)) {
            int spellAttack = Integer.parseInt(parsedValues[4]);
            int spellSlots = Integer.parseInt(parsedValues[5]);
            return new Mage(name, health, baseAttack, spellAttack, spellSlots);
        } else {
            throw new InvalidPartyMemberException("Invalid character class: " + characterClass);
        }
    }
    /**
    * Method that takes in a String object representing a file name to read from and returns
    * an ArrayList of PartyMember objects found in it.
    *
    * @param fileName the String representing the name of the file to read from
    * @return an ArrayList of PartyMember objects
    */
    public static ArrayList<PartyMember> recruitParty(String fileName) throws FileNotFoundException {
        if (fileName == null) {
            throw new FileNotFoundException("File name cannot be null");
        }

        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("File does not exist");
        }

        ArrayList<PartyMember> partyMembers = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    partyMembers.add(processInfo(line));
                }
            }
        }

        return partyMembers;
    }
    /**
    * Method that takes in a String of the name of the file that needs to be written to, adding
    * current members found in the file to an ArrayList and then adding the new members before
    * writing the updated list back to the file.
    *
    * @param fileName the name of the file where the party members are stored
    * @param newPartyMembers ArrayList of the new PartyMember objects that need to be added
    * @return a boolean based on whether the roster was successfully updated
    */
    public static boolean partyRoster(String fileName, ArrayList<PartyMember> newPartyMembers) throws IOException {
        ArrayList<PartyMember> existingPartyMembers = recruitParty(fileName);
        existingPartyMembers.addAll(newPartyMembers);

        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (PartyMember member : existingPartyMembers) {
                writer.println(member);
            }
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }
    /**
    * Method that takes in a String of the name of the quest and an ArrayList of PartyMember
    * objects that will be tested to see whether they can complete the quest.
    *
    * @param questName the name of the quest the party will be sent on
    * @param partyMembers ArrayList of the PartyMember objects being sent on the quest
    * @return a boolean based on whether the party succeeded in the quest
    */
    public static boolean getQuest(String questName, ArrayList<PartyMember> partyMembers)
        throws FileNotFoundException, QuestNotFoundException {
        File file = new File("quest.csv");
        if (!file.exists()) {
            throw new FileNotFoundException("File quest.csv not found");
        }

        ArrayList<String> questLines = new ArrayList<>();
        boolean questFound = false;
        boolean partySucceeded = false;
        int questLevel = 0;
        int partyLevel = partyQuestLevel(partyMembers);
        String reward = "";

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] questDetails = line.split(": ");
                String currentQuestName = questDetails[0];
                if (currentQuestName.equalsIgnoreCase(questName)) {
                    questFound = true;
                    String[] levelAndReward = questDetails[1].split(", ");
                    questLevel = Integer.parseInt(levelAndReward[0]);
                    reward = levelAndReward[1];
                } else {
                    questLines.add(line);
                }
            }
        }

        if (!questFound) {
            throw new QuestNotFoundException("Quest '" + questName + "' not found.");
        }

        if (partyLevel >= questLevel) {
            System.out.println("Success! Your party gained " + reward + " coins. This calls for a trip to the Tavern!");
            partySucceeded = true;
            try (PrintWriter writer = new PrintWriter(file)) {
                for (String line : questLines) {
                    writer.println(line);
                }
            }
        } else {
            System.out.println("Failure... Your party was defeated. Better Luck Next Time!");
        }

        return partySucceeded;
    }
    /**
    * A private helper method that will return the total quest level of all the party members
    * from the PartyMember objects arraylist being passed in.
    *
    * @param partyMembers an ArrayList of PartyMember objects
    * @return an int of the total quest level
    */
    private static int partyQuestLevel(ArrayList<PartyMember> partyMembers) {
        if (partyMembers == null || partyMembers.isEmpty()) {
            return -1;
        }

        int totalLevel = 0;
        for (PartyMember member : partyMembers) {
            totalLevel += member.questLevel();
        }
        return totalLevel;
    }
    /**
    * The main method the class that tests the rest of the code. Creates a list of
    * PartyMember objects and writes them to a file. It adds a PartyMember object to the
    * file, reads the file, and prints out each PartyMember object's information.
    *
    * @param args not used
    */
    public static void main(String[] args) {
        Warrior warrior1 = new Warrior("Aragorn", 100, 15, "Sword", 5);
        Warrior warrior2 = new Warrior("Boromir", 90, 12, "Sword", 4);
        Warrior warrior3 = new Warrior("Gimli", 100, 18, "Axe", 6);
        Mage mage1 = new Mage("Gandalf", 100, 10, 20, 5);
        Mage mage2 = new Mage("Saruman", 90, 15, 25, 4);
        Mage mage3 = new Mage("Radagast", 80, 10, 15, 3);

        try (PrintWriter writer = new PrintWriter("TestParty.csv")) {
            writer.println(warrior1);
            writer.println(warrior2);
            writer.println(warrior3);
            writer.println(mage1);
            writer.println(mage2);
            writer.println(mage3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Warrior warrior4 = new Warrior("Eowyn", 85, 13, "Sword", 5);
        try (PrintWriter writer = new PrintWriter("TestParty.csv")) {
            writer.println(warrior4);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<PartyMember> party = null;
        try {
            party = recruitParty("TestParty.csv");
        } catch (FileNotFoundException e) {
            System.err.println("TestParty.csv file not found.");
            return;
        }

        for (PartyMember member : party) {
            System.out.println(member);
        }

        try {
            boolean success = getQuest("The Ultimate Challenge", party);
            if (success) {
                System.out.println("The party has successfully completed the quest!");
            } else {
                System.out.println("The party has failed the quest.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Quest file not found.");
        } catch (QuestNotFoundException e) {
            System.err.println("Quest not found: " + e.getMessage());
        }
    }
}