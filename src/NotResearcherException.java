/**
 * The NotResearcherException class is an exception thrown to indicate that an operation requires an object
 * to be a researcher, but it is not.
 */
public class NotResearcherException extends Exception  {
	/**
     * Constructs a NotResearcherException with the specified detail message.
     *
     * @param message The detail message.
     */
    public NotResearcherException(String message) {
        super(message);
    }
}
