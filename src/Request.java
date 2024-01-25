import java.io.Serializable;
/**
 * The Request represents a request made by a user, specifying a description and urgency level.
 */
public class Request implements Serializable {
	private static final long serialVersionUID = 15L;
	// request desctription
    private String requestDescription;
    // urgencylevel
    private UrgencyLevel urgencyLevel;    
    /**
     * Constructs a new Request with the given description and urgency level.
     *
     * @param requestDescription The description of the request.
     * @param urgencyLevel       The urgency level of the request.
     */
    public Request(String requestDescription,UrgencyLevel urgencyLevel) {
    	this.setRequestDescription(requestDescription);
    	this.setUrgencyLevel(urgencyLevel);
    }
    /**
     * Gets the description of the request.
     *
     * @return The description of the request.
     */
	public String getRequestDescription() {
		return requestDescription;
	}
	/**
     * Sets the description of the request.
     *
     * @param requestDescription The new description for the request.
     */
	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}
	/**
     * Gets the urgency level of the request.
     *
     * @return The urgency level of the request.
     */
	public UrgencyLevel getUrgencyLevel() {
		return urgencyLevel;
	}
	/**
     * Sets the urgency level of the request.
     *
     * @param urgencyLevel The new urgency level for the request.
     */
	public void setUrgencyLevel(UrgencyLevel urgencyLevel) {
		this.urgencyLevel = urgencyLevel;
	}   
}

