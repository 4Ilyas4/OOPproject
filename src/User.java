import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;




/**
 * An abstract class that represents a user in the system. 
 */
public abstract class User implements Comparable<User>, Serializable {
	private static final long serialVersionUID = 23L;
	
	// Field for accessing the database
    protected DataBase db;
    
    // Basic User Characteristics
    private String ID;
    private String hashedPassword;
    private String userName;
    private String userSurname;
    //  manager,admin,teacher,bachelor,techsupport,specialist,graduatestudent,dean
    private UserType userType;
    // what university journal is subscribed to
    private String journal;   
    
    
    /**
     * User data output
     *
     * @return id, name, surname, usertype, journal
     */
    @Override
    public String toString() {
        return "User: " + " ID: " + ID + " userName " + userName + " userSurname " + userSurname + " userType " + userType + " journal " + journal;
    }
    @Override
    /**
     * Checks if the current object is equal to another object.
     *
     * @param o comparison object
     * @return true, if the objects are equal, otherwise false.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(ID, user.ID);
    }
    
    /**
     * Compares the current user to another user by ID.
     *
     * @param o Another user for comparison.
     * @return Comparison Result.
     */
    @Override
    public int compareTo(User o) { 
    	return this.ID.compareTo(o.ID);
    }
    
    
    /**
     * Constructor. the user is created by id, password, and typeuser.
     *
     * @param ID       user's id
     * @param password user's password .
     * @param userType User type manager,admin,teacher,bachelor,techsupport,specialist,graduatestudent,dean
     */
    public User(String ID,String  password ,UserType userType) {
    	this.db = DataBase.getInstance(); 
    	this.ID = ID;
    	this.hashedPassword = hashPassword(password);
    	this.userType = userType;
    }
    
    /**
     * To access the system you need to log in. login requires an id and password. To check this, we hash the password by creating a variable InhashedPassword. If the hashed password corresponds to the entered InhashedPassword, then we login
     *
     * @param ID 
     * @param password Password for verification.
     * @return  true if id and password are correct, i.e. such user exists and his password is entered correctly, otherwise false.
     */
    public boolean login(String ID, String password) {
    	String InhashedPassword = hashPassword(password);
    	return InhashedPassword.equals(this.hashedPassword);
    }
    
    
    /**
     * method for password hashing. ordinary polynomial function. 1_000_000_000_007 simple and large number. needed to avoid storing huge numbers
     *
     * @param password .
     * @return  returns the password hash
     */
    public String hashPassword(String password) {
        long hashValue = 0;
        long pPow = 1;

        for (char c : password.toCharArray()) {
            hashValue = (hashValue + (c - 'a' + 1) * pPow) % 1_000_000_007; 
            pPow = (pPow * 31) % 1_000_000_007;
        }

        return String.valueOf(hashValue);
    }
    
    
    /**
     * Changes the current user password to a new one.The new password is hashed too
     *
     * @param newPassword The user's new password.
     */
    public void changePassword(String newPassword) {
        this.hashedPassword = hashPassword(newPassword);
    }
    
    
    /**
     * User type check
     *
     * @param type User type to check. manager,admin,teacher,bachelor,techsupport,specialist,graduatestudent,dean
     * @return  true if the current user matches the specified type, otherwise false.
     */
    public boolean isOfType(UserType type) {
        return this.userType == type;
    }
    /**
     * Adds a user action to the log with the current date and time.
     *
     * @param action User Action.
     */
    public void addActions(String action) {
        LocalDateTime curdate = LocalDateTime.now();
        DateTimeFormatter dates = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String date = curdate.format(dates);
        LogFile l = new LogFile(this.getID(),date,action);
        db.addLog(l);
    }
    
    /**
     * so that each user can view the news. if the news is pinned, it will be displayed.
     *
     * @return News line
     */
    public String viewNews() {
    	String res = " !News! ";
    	List<News> news = db.getNews();
    	for(News n : news) {
    		if(n.pinned) {
    			res += n.view();
    		}
    	}
    	for(News n : news) {
    		if(!n.pinned) {
    			res += n.view();
    		}
    	}
    	return res;
    }
    
    /**
     * Returns the id of the user.
     *
     * @return user id
     */
    public String getID() {
        return ID;
    }
    /**
     * Returns the username of the user.
     *
     * @return User Name.
     */
    public String getName() {
        return userName;
    }   
    /**
     * Sets the last name of the user.
     *
     * @param surname Surname of the user.
     */
    public void setSurname(String sauron) {
    	this.userSurname = sauron;
    } 
    /**
     * Sets the username of the user.
     *
     * @param name username.
     */
    public void setName(String sauron) {
    	this.userName = sauron;
    }
    /**
     * Returns surname of the user.
     *
     * @return user's surname.
     */
    public String getSurname() {
        return userSurname;
    }
    /**
     * returns type of the user
     *
     * @return user's type
     */
    public UserType getUserType() {
        return userType;
    }
    
    /**
     * user makes a request to the support service with a description of the problem and the level of urgency. This request is sent to a technician and the user must wait for a response
     *
     * @param orderDescription Description of the support request.
     * @param urgencyLevel The level of urgency of the request.
     */
    public void getSupport(String orderDescription, UrgencyLevel urgencyLevel) {
    	Order order = new Order(orderDescription, urgencyLevel);
    	db.getTeachSupportSp().getHelp(order);
    }
    /**
     * Subscribes the user to the specified log.
     *
     * @param journal The name of the journal to subscribe to.
     */
    public void subscribe(String journal) { 
    	this.journal = journal;
    }

    /**
     * Sets the new user type.
     *
     * @param userType2 A new type of user.
     */
	public void setUserType(UserType userType2) {
		 this.userType = userType2;
	}
	/**
     * Returns the name of the magazine to which the user is subscribed.
     *
     * @return The name of the journal to which the user subscribes.
     */
	public String getJournal() {
		return this.journal;
	}
	/**
     * Returns the technician
     *
     * @return Возвращает технического специалиста
     */
	public Object getTeachSupportSp() {
		if(db != null) {
			return db.getTeachSupportSp();
		}
		return null;
	}
}

