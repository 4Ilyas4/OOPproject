import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * The UniversitySystem class represents the main class for the university system.
 * It initializes the database and provides methods to register users and start the user interface.
 */
public class UniversitySystem {
    private static DataBase db;
    private static UserUI userUI;
    /**
     * Constructs a UniversitySystem and initializes the database instance.
     */
    public UniversitySystem() {
        UniversitySystem.db = DataBase.getInstance();
    }
    /**
     * Registers a user in the system.
     *
     * @param ID       The user ID.
     * @param password The user password.
     * @param userType The type of user (e.g., Admin, Dean, GraduateStudent).
     */
    public void registerUser(String ID, String password, UserType userType) {
        db.registerUser(ID, password, userType);
    }
    /**
     * Starts the user interface of the university system.
     */
    public static void startUserUI() {
        userUI = new UserUI(db);
    }
    /**
     * The main method to initialize the university system, register some users, and start the user interface.
     *
     * @param args The command line arguments.
     */
	public static void main(String[] args) { 
		UniversitySystem system = new UniversitySystem();
		DataBase db = DataBase.getInstance();
		User user1 = db.registerUser("1","123",UserType.Admin);
		User user2 = db.registerUser("2","321",UserType.Dean);
		User user3 = db.registerUser("3","abc",UserType.TechSupportSpecialist);
		User user4 = db.registerUser("4","cba",UserType.Manager);
		User user5 = db.registerUser("5","def",UserType.GraduateStudent);
		User user6 = db.registerUser("6","fed",UserType.GraduateStudent);
		User user7 = db.registerUser("7","qwe",UserType.GraduateStudent);
		User user8 = db.registerUser("8","ewq",UserType.Bachelor);
		User user9 = db.registerUser("9","ipo",UserType.Bachelor);
		User user10 = db.registerUser("10","poi",UserType.Bachelor);
		User user11 = db.registerUser("11","kjh",UserType.Bachelor);
		User user12 = db.registerUser("12","hkj",UserType.Bachelor);
		User user13 = db.registerUser("13","hoi",UserType.TechSupportSpecialist);
		User user14 = db.registerUser("14","cba",UserType.Teacher);
		User user15 = db.registerUser("15","cba",UserType.Teacher);
		User user16 = db.registerUser("16","cba",UserType.Teacher);
		startUserUI();
	}	
}
