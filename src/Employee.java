import java.io.Serializable;
/**
 * An abstract class representing an employee in the system.
 */
public abstract class Employee extends CanBeResearcher implements Serializable  {
	private static final long serialVersionUID = 7L;
	// if a message is sent to an employee, it is stored in a message
	String message;
	/**
     * Constructor. Creates an employee with the specified parameters.
     *
     * @param ID       employee's id
     * @param password employee's password
     * @param userType (manager,admin,teacher,techsupport,specialist,dean).
     */
	public Employee(String ID, String password, UserType userType) {
		super(ID, password, userType);
		// TODO Auto-generated constructor stub
	}
	/**
     * Sends a message to another employee.
     *
     * @param message Message to send.
     * @param e       Recipient of the message.
     */
    public void sendMessage(String message, Employee e) { 
        e.addMsg(" message: {" + message + "} from employee : " +  e.getName());  
       } 
    /**
     * Adds a message to the current message.
     *
     * @param s Message to add.
     */
    public void addMsg(String s) {
    	message += s + "\n";
    }
    /**
     * Reviews the current message.
     *
     * @return Current Message.
     */
    public String viewMessage() {
    	return message;
    }
    /**
     * Sends a request to the Dean with a specified description and level of urgency.
     *
     * @param requestDescription Request Description.
     * @param urgencyLevel       The level of urgency of the request.
     */
    public void requestToDean(String requestDescription,UrgencyLevel urgencyLevel) {
    	Request r = new Request(requestDescription,urgencyLevel);
    	db.getDean().addRequest(r);
    }
}

