
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * A class representing an administrator in the system.
 */
public class Admin extends Employee implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/** A list to store user IDs for removal or update. */
	List<String> toRemove;
	/**
     * Constructor for creating an Admin
     *
     * @param ID       admin's id
     * @param password admin's password
     */
	public Admin(String ID,String  password){
		super(ID,password ,UserType.Admin);
		toRemove = new ArrayList<>();
	}
	/**
     * View log files about user actions. 
     *
     * @param ID The ID of the user for whom to view log files.
     */
    public void viewLogFilesAboutUserActions(String ID) {
    	db.viewUserActions(ID);
    }
    /**
     * Remove a user from the system.
     *
     * @param ID The ID of the user to be removed.
     */
    public void removeUser(String ID) {
    	db.removeUser(ID);
    }
    /**
     * Update information about a user in the system.
     *
     * @param ID The ID of the user to be updated.
     */
    public void updateUser(String ID) {
    	db.updateUser(ID);
    }
    /**
     * Get the list of all users in the system.
     *
     * @return A list of User objects representing all users in the system.
     */
    public List<User> getUsers() {
        return db.getUsers();
    }
    /**
     * Add a user ID to the list for removal or update.
     *
     * @param ID The ID of the user to be added to the list.
     */
    public void addToRemove(String ID) {
    	toRemove.add(ID);
    }
    /**
     * View the list of user IDs to be removed or updated.
     */
    public void viewToRemove() {
    	if(!toRemove.isEmpty()) {
    		for(String ID: toRemove) {
    			System.out.println(ID);
    		}
    	}
    	else {
    		System.out.println("There is no users to remove or update! ");
    	}
    }
}

