import java.io.Serializable;
import java.lang.*;
/**
 * A class that represents an order in the system for the techsupport. an order is a request for help
 * The order can be sent to the support service with a description of the problem and the level of urgency. the techsupport further decides what to do with it.
 */
public class Order implements Serializable  {
	private static final long serialVersionUID = 14L;
    // whether the order is processed or not. by default the created request is not processed
	private boolean accepted = false;
	// Order Description
	private String orderDescription;	
    // Order urgency level
	private UrgencyLevel urgencyLevel;	
	
	/**
     * Designer to create an order with a given description and urgency level.
     *
     * @param orderDescription Order Description.
     * @param urgencyLevel     The level of urgency of the order.
     */
    public Order(String orderDescription, UrgencyLevel urgencyLevel) {
    	this.orderDescription = orderDescription;
	    this.urgencyLevel = urgencyLevel;
	} 
    /**
     * Returns the description of the order.
     *
     * @return Order Description.
     */
    public String getOrderDescription() {
    	return orderDescription;
    } 
    /**
     * Sets a new order description.
     *
     * @param orderDescription New order description.
     */
    public void setOrderDescription(String orderDescription) {
    	this.orderDescription = orderDescription;
    } 
    /**
     * Returns the urgency level of the order.
     *
     * @return The level of urgency of the order.
     */
    public UrgencyLevel getUrgencyLevel() {
    	return urgencyLevel;
    } 
    /**
     * Sets a new order urgency level.
     *
     * @param urgencyLevel A new level of order urgency.
     */
    public void setUrgencyLevel(UrgencyLevel urgencyLevel) {
    	this.urgencyLevel = urgencyLevel;
    }  
    /**
     * Checks to see if the order has been accepted.
     *
     * @return true if the order is accepted, otherwise false.
     */
	public boolean isAccepted() {
		return accepted;
	}
	/**
     * Sets the status of the order.
     *
     * @param accepted order status.
     */
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
}

