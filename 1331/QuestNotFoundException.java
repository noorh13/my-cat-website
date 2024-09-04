/**
 * Represents an exception that is thrown when a quest is not found.
 * This is a unchecked exception, so it does not need to be declared in the method signature.
 *
 * @author nhasan36
 * @version 1.0
 */
public class QuestNotFoundException extends Exception {
    /**
    * Constructor that takes in a string as a parameter, which is the exception's
    * message.
    *
    * @param string the message of the exception
    */
    public QuestNotFoundException(String string) {
        super(string);
    }
    /**
    * Constructor that takes in no parameter and returns a default message.
    */
    public QuestNotFoundException() {
        super("Selected Quest Not Found");
    }
}