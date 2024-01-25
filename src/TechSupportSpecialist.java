import java.io.Serializable;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;
/**
 * The `TechSupportSpecialist` class represents a technical support specialist who can handle orders for assistance.
 */
public class TechSupportSpecialist extends Employee  implements Serializable {
	private static final long serialVersionUID = 21L;
	// list order
    private List<Order> orders;
    /**
     * Constructs a new TechSupportSpecialist with ID and password.
     *
     * @param ID       The ID of the tech support specialist.
     * @param password The password of the tech support specialist.
     */
    public TechSupportSpecialist(String ID, String password) {
		super(ID, password, UserType.TechSupportSpecialist);
		this.orders = new ArrayList<Order>();
		// TODO Auto-generated constructor stub
	}
    /**
     * Accepts the given order and sets its accepted status to true.
     *
     * @param order The order to be accepted.
     * @return A message indicating that the order has been accepted.
     */
    public String acceptOrder(Order order) {
    	order.setAccepted(true);
    	orders.remove(order);
        return "Order accepted: " + order.getOrderDescription();
    }
    /**
     * Rejects the given order.
     *
     * @param order The order to be rejected.
     * @return A message indicating that the order has been rejected.
     */
    public String rejectOrder(Order order) {
        orders.remove(order);
        return "Order rejected: " + order.getOrderDescription();
    }
    /**
     * Adds the given order to the list of orders assigned to the tech support specialist.
     *
     * @param order The order to be added.
     */
    public void getHelp(Order order) {
        orders.add(order);
    }
    /**
     * Retrieves the list of orders assigned to the tech support specialist.
     *
     * @return The list of orders assigned to the tech support specialist.
     */
    public List<Order> viewOrders() {
        return orders;
    }
}

