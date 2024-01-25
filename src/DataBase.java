
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

public class DataBase implements Serializable { 
	private static final long serialVersionUID = 6L;
	private static transient DataBase instance;//private static DataBase instance;
	private HashMap<String,User> users;
	private Vector<CanBeResearcher> researchers;
	private Dean dean;
	private Manager manager;
	private Admin admin;
	private TechSupportSpecialist techSupport;
	private HashMap<String,Course> courses;
	private List<Integer> rooms;
	private List<News> newsl;
	private HashMap<String,Bachelor> students;
	private HashMap<String,Teacher> teachers;
	private Set<Lesson> lessons;
	private Language language;
	private Vector<LogFile> logfiles; 
	public DataBase() {
		rooms = new ArrayList<>();
		newsl = new ArrayList<>();
		language = Language.RU;
		users = new HashMap<>();
		courses = new HashMap<>();
		students = new HashMap<>();
		teachers = new HashMap<>();
		lessons = new HashSet<>();
		logfiles = new Vector<>();
		researchers = new Vector<>();
	}
    static {
    	Path path = Paths.get("data.txt");
    	if (Files.exists(path)) {
    		try {
    			instance = read();
    		} catch (Exception e) {
    			e.printStackTrace();
    	    }
    	 } else {
    	    instance = new DataBase();
    	 }
	}
    public void deleteData() {
    	String filePath = "data.txt";
        File file = new File(filePath);
        if (file.exists()) {
        	if (file.delete()) {
        		System.out.println("File deleted");
        	} else {
        		System.out.println("Cant delete file");
        	}
        } else {
        	System.out.println("there is no such file");
        }
        
    }
    public static synchronized DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }
    private Object readResolve() {
        return this;
    }
	public static DataBase read() throws IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream("data.txt");
		try (ObjectInputStream oin = new ObjectInputStream(fis)) {
			return (DataBase) oin.readObject();
		}
	}
	public static void write()throws IOException{
		FileOutputStream fos = new FileOutputStream("data.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(instance);
		oos.close();
	}
	public void removeUser(String UserID) {
		users.remove(UserID);
		teachers.remove(UserID);
		students.remove(UserID);
	}
	public User registerUser(String UserID, String password, UserType userType) {
		if(users.containsKey(UserID)) {
			System.out.println("There is duplicate ID");
			return null; 
		}
		else {
		    User user = null;      
		    switch (userType) {
		        case Dean:
		            user = new Dean(UserID, password);
		            dean = (Dean) user;
		            break;
		        case Admin:
		            user = new Admin(UserID, password);
		            admin = (Admin) user;
		            break;
		        case Manager:
		            user = new Manager(UserID, password);
		            manager = (Manager) user;
		            break;
		        case Teacher:
		            user = new Teacher(UserID, password);
		            Teacher teacher = (Teacher) user;
		            teachers.put(UserID, teacher);
		            break;
		        case Bachelor:        
		            user = new Bachelor(UserID, password);
		            Bachelor bachelor = (Bachelor) user;
		            students.put(UserID, bachelor);
		            break;
		        case TechSupportSpecialist:
		            user = new TechSupportSpecialist(UserID, password);
		            techSupport = (TechSupportSpecialist) user;
		            break;
		        case GraduateStudent:
		            user = new GraduateStudent(UserID, password);
		            Bachelor graduateStudent = (Bachelor) user;
		            students.put(UserID, graduateStudent);
		            break;
		    }
		    if (user != null) {
		        users.put(UserID, user);
		        return user;
		    } 
		    else {
		        return null;
		    }
		}
	}
	public boolean login(String iD, String pswrd) {
	    User user = getUser(iD);
	    if (user != null) {
	        return user.login(iD,pswrd);
	    } else {
	        System.out.println("User not found.");
	        return false;
	    }
	}
	public User getUser(String ID) {
	    if (users.containsKey(ID)) {
	        return users.get(ID);
	    } 
	    else {
	        System.out.println("User with ID = " + ID + " not found! ");
	        return null;
	    }
	}
	public Course getCourse(String courseName) {
	    return courses.get(courseName);
	}
	public Admin getAdmin() {
	    return admin;
	}
	public Manager getManager() {
	    return manager;
	}
	public Dean getDean() {
	    return dean;
	}
	public TechSupportSpecialist getTeachSupportSp() {
		return techSupport;
	}
	public Bachelor getStudent(String iD) {
	    if (students.containsKey(iD)) {
	        return students.get(iD);
	    }
	    else {
	        System.out.println("Student with ID = " + iD + " not found!");
	        return null;
	    }
	}
	public Teacher getTeacher(String ID) {
		if (teachers.containsKey(ID)) {
	        return teachers.get(ID);
	    } 
		else {
	        System.out.println("Teacher with ID = " + ID + " not found!");
	        return null;
	    }
	}
	public void addRoom(int room) {
		rooms.add(room);
	}
	public void registerStudentForCourse(String IDofStudent, String courseName) {
		Bachelor student = getStudent(IDofStudent);
		Course c = getCourse(courseName);
		String subj = c.getSubject();
		Semester s = new Semester();
		s.addMark(subj, new Mark(subj));
		student.getTranscript().getSemester(student.getSemester()).addMark(subj,new Mark(subj));
		Iterator<Map.Entry<String, Teacher>> iterator = teachers.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Teacher> entry = iterator.next();
			Teacher teacher = entry.getValue();
            if (teacher.canAdd()) {
            	teacher.addStudent(IDofStudent,student);
				student.addCourse(courseName);
				student.getTranscript().addSemester(s);
				break;
            }
            else { 	
            	continue;
            }
        }
	}
	public void registerTeacherForCourse(String IDofTeacher, String courseName) {
		Teacher teacher = getTeacher(IDofTeacher);
		Course course = getCourse(courseName);
		if(courses.containsKey(courseName)){
			teacher.setCourse(course);
		}
	}
	public void addLessonsToTeacher(Lesson newLesson, String IDofTeacher) {
		if(lessons.add(newLesson)) {
			Teacher teacher = getTeacher(IDofTeacher);
			teacher.addLesson(newLesson);
	    }
	}
	public void addLessonsToStudents() {
		boolean avialable = true;
		for (Map.Entry<String, Teacher> entry : teachers.entrySet()) {
			if(entry.getValue().getLessons().isEmpty()) {
				avialable = false;
				System.out.println(" Some teacher dont have lessons! ");
				return;
			}
		}
		if(avialable) {
			for (Map.Entry<String, Teacher> entry : teachers.entrySet()) {
				Teacher teacher = entry.getValue();
				teacher.addLessonsForStudents();
			}
		}
	}
	public String viewAllCourses() {
		String res = " Courses: ";
		for (Map.Entry<String, Course> entry : courses.entrySet()) {
			Course course = entry.getValue();
			String courseName = entry.getKey();
			String r = " course: " + courseName + " description: " + course.getDescription() + "\n";
			res += r; 
		}
		return res;
	}
	public String getInfo(String IDofStudentOrTeacher) {
		User user = getUser(IDofStudentOrTeacher);
	    if(user instanceof Teacher) {
	    	Teacher t = (Teacher) user;
	    	return t.getInfo();
	    } 
	    else if(user instanceof GraduateStudent){
	   	    GraduateStudent g = (GraduateStudent) user;
	   	    return g.getInfo();
	   	}
	    else if(user instanceof Bachelor) {
	    	Bachelor b = (Bachelor)user;
	    	return b.getInfo();
	    }
	    return " No info ";
	}
	public void addCourse(String cn, Course c) {
		courses.put(cn,c);
	}
	public void removeTeacherLesson(Lesson lesson, String IDofTeacher) {
		Teacher t = getTeacher(IDofTeacher);
		List<Lesson> l = t.getLessons();
		l.remove(lesson);
	}
	public void removeCourse(String courseName) {
		courses.remove(courseName);
		for (Map.Entry<String, Teacher> entry : teachers.entrySet()) {
			Teacher teacher = entry.getValue();
			if(teacher.getCourse().getCourseName()==courseName) {
				teacher.removeCourse();
			}
		}
	}
	public void removeStudentLessson(String IDofStudent, Lesson lesson) {
		Bachelor b = getStudent( IDofStudent);
		List<Lesson> l = b.getLessons();
		l.remove(lesson);
	}
	public List<Bachelor> getStudents() {
	    return new ArrayList<>(students.values());
	}
	public double[] averageMarks(int semester,String subject) {
		int totalFirst = 0, totalSecond = 0, totalFinal = 0;
		List<Bachelor> bl = getStudents();
		int countStudents = 0; 
	    for(Bachelor b : bl) {
	    	List<Mark> marks = b.getMarks(semester,subject);
		    for (Mark mark : marks) {
		    	totalFirst += mark.getFirst();
		    	totalSecond += mark.getSecond();
		    	totalFinal += mark.getFinal();
		    }
		    countStudents++;
	    }
		double averageFirst = (double) totalFirst / countStudents;
		double averageSecond = (double) totalSecond / countStudents;
		double averageFinal = (double) totalFinal / countStudents;
		return new double[]{averageFirst, averageSecond, averageFinal};
	}
	public void addNews(News news) {
		newsl.add(news);
	}
	public List<News> getNews() {
	    return newsl;
	}
	public List<User> getUsers() {
	    return new ArrayList<>(users.values());
	}
	public List<Teacher> getTeachers() {
		return new ArrayList<>(teachers.values());
	}
	public List<Bachelor> getAllStudents() {
		return new ArrayList<>(students.values());
	}
	public void addLog(LogFile a) {
		logfiles.add(a);
	}
	public void viewUserActions(String userID) {
        User user = users.get(userID);
        if (user != null) {
            for (LogFile l : logfiles) {
            	if(l.iD.equals(userID)) {
            		System.out.println(l.getLog());
            	}
            }
        } else {
            System.out.println("User not found.");
        }
    } 
	public String updateUser(String iD) {
		String res = "";
		User user = getUser(iD);
		String newName = user.getName();
		String newSurname = user.getSurname();
		UserType userType = user.getUserType(); 
		String journal = user.getJournal();
		if (user != null) {
			user.setName(newName);
			user.setSurname(newSurname);
			user.setUserType(userType);
			user.subscribe(journal);
			res += "User " + iD + " updated successfully.";
		}
		return res;
	}
	public void addResearcher(CanBeResearcher researcher) {
		researchers.add(researcher);
	}
}


