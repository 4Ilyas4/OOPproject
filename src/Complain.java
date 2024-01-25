import java.io.Serializable;
import java.lang.*;
/**
 * The Complaint class represents a complaint to student.

 */
public class Complain implements Serializable {
	private static final long serialVersionUID = 4L;
	// urgencylevel of complain
    private UrgencyLevel urgencyLevel ;
    // description of complain
    private String description;
    // student's id complained about
    private String IDofStudent;
    /**
     * Constructor. Complaint must have urgencylevel, descrip of complain and id of student
     *
     * @param urgencyLevel The urgency level of the complaint.
     * @param description  The description of the complaint.
     * @param IDofStudent  The ID of the student submitting the complaint.
     */
    public Complain(UrgencyLevel urgencyLevel, String description, String IDofStudent){
    	this.urgencyLevel = urgencyLevel;
    	this.description = description;
    	this.IDofStudent = IDofStudent;
    }
    /**
     * Gets the urgency level of the complaint.
     *
     * @return The urgency level.
     */
    public UrgencyLevel getUrgencylevel() {
    	return urgencyLevel;
    }
    /**
     * Gets the description of the complaint.
     *
     * @return The description.
     */
    public String getDescription() {
    	return description;
    }
    /**
     * Gets the ID 
     *
     * @return The student's ID.
     */
    public String getIDofStudent() {
    	return IDofStudent;
    }
}

