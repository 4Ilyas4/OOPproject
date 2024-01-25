import java.io.Serializable;
import java.lang.*;
/**
 * An enumeration representing the urgency levels of orders in the system.
 * Each level of urgency determines the level of importance or urgency of the orders.
 */
public enum UrgencyLevel implements Serializable  {
	/**
     * Low level of urgency.
     */
    LOW,

    /**
     * Medium level of urgency.
     */
    MEDIUM,

    /**
     * High level of urgency.
     */
    HIGH;
}

