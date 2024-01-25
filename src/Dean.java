import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * The `Dean` class represents a dean in an educational institution.
 */

public class Dean extends Employee implements Serializable  {
	private static final long serialVersionUID = 1L;
	// list of complaints tha dean must handle
	private List<Complain> complaints;
	// list of requests
    private List<Request> requests;
    /**
     * Constructs a new `Dean` object with the specified ID and password.
     *
     * @param ID       The unique identifier for the dean.
     * @param password The password associated with the dean's account.
     */
    public Dean(String ID, String password) {
		super(ID, password, UserType.Dean);
		requests = new ArrayList<>();
		complaints = new ArrayList<>();
	}
    /**
     * Signs a request, indicating approval by the dean.
     *
     * @param r The request to be signed.
     * @return A string message indicating the status of the request signing process.
     */
    public String signRequest(Request r) {
    	requests.remove(r);
    	return " Request signedd ";
    }
    /**
     * Rejects a request, indicating disapproval by the dean.
     *
     * @param r The request to be rejected.
     * @return A string message indicating the status of the request rejection process.
     */
    public String rejectRequest(Request r) {
    	requests.remove(r);
    	return " Request unsigned ";
    }
    /**
     * Accepts a complaint, taking appropriate actions based on the complaint.
     *
     * @param c The complaint to be accepted.
     * @return A string message indicating the status of the complaint acceptance process.
     */
    public String acceptComplaint(Complain c) {
    	complaints.remove(c);
    	db.getAdmin().addToRemove(c.getIDofStudent());
    	return " Complain accepted ";
    }
    /**
     * Rejects a complaint, indicating that no further action will be taken.
     *
     * @param c The complaint to be rejected.
     * @return A string message indicating the status of the complaint rejection process.
     */
    public String rejectComplaint(Complain c) {
    	complaints.remove(c);
    	return " Complain rejected ";
    }
    /**
     * Adds a complaint to the list of complaints.
     *
     * @param c The complaint to be added.
     */
    public void addComplaint(Complain c) {
    	complaints.add(c);
    }
    /**
     * Adds a request to the list of requests.
     *
     * @param r The request to be added.
     */
    public void addRequest(Request r) {
    	requests.add(r);
    }
    /**
     * Gets the list of complaints submitted to the dean.
     *
     * @return The list of complaints.
     */
    public List<Complain> getComplaints() {
        return complaints;
    }
    /**
     * Gets the list of requests submitted to the dean.
     *
     * @return The list of requests.
     */
    public List<Request> getRequests() {
        return requests;
    }
}

