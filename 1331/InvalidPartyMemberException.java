/**
 * Represents an exception that is thrown when a party member is invalid.
 * This is a checked exception, so it is declared in the method signature.
 *
 * @author nhasan36
 * @version 1.0
 */
public class InvalidPartyMemberException extends RuntimeException {
    /**
    * Constructor that takes in a string as a parameter, which is the exception's
    * message.
    *
    * @param string the message of the exception
    */
    public InvalidPartyMemberException(String string) {
        super(string);
    }
    /**
    * Constructor that takes in no parameter and returns a default message.
    */
    public InvalidPartyMemberException() {
        super("Invalid party member!");
    }
}