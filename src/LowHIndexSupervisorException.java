/**
 * The LowHIndexSupervisorException is an exception class that is thrown when a supervisor's h-index is too low.
 */
public class LowHIndexSupervisorException extends Exception  {
	/**
     * Constructs a LowHIndexSupervisorException with the specified error message.
     *
     * @param message The error message explaining the low h-index of the supervisor.
     */
    public LowHIndexSupervisorException(String message) {
        super(message);
    }
}
