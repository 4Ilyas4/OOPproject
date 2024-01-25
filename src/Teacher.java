import java.io.Serializable;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
/**
*/
import java.util.List;
import java.util.Vector;
/**
 * teacher class
 */
public class Teacher extends Employee implements Serializable  {
	private static final long serialVersionUID = 20L;
	// a course taught by a teacher
	private Course course;
	// lesson's list
	private List<Lesson> lessons;
	// count of student taught by a teacher
	private int maxstudentsnum;	
	// subject
	private String subject;
	// rate of teacher
	private int rate;
	// rooms
	private List<Integer> rooms;
	// teacher type phd...
	private TeacherType teacherType;
	// info about teacher
	private String info;
	
	private HashMap<String,Bachelor> students; // get part of students from allstudents in db by registration by adding
	
	/**
     * Constructor for creating a teacher with the given ID and password.
     * @param ID The unique identifier for the teacher.
     * @param password The password for authentication.
     */
	public Teacher(String ID, String password) {
		super(ID, password, UserType.Teacher);
		this.lessons = new ArrayList<>();
		this.rooms = new ArrayList<>();
		this.students = new HashMap<>();
		this.teacherType = TeacherType.MASTER;
		this.maxstudentsnum = 10;
	}
	/**
     * Constructor for creating a teacher with specified parameters.
     * @param userName The name of the teacher.
     * @param UserID The unique identifier for the teacher.
     * @param userType The type of the user (Teacher).
     * @param course The course associated with the teacher.
     * @param maxstudentsnum The maximum number of students the teacher can have.
     * @param rate The rate of the teacher.
     * @param teacherType The type of the teacher (e.g., MASTER, PROFESSOR).
     */
	public Teacher(String userName,String UserID, UserType userType,Course course,int maxstudentsnum,
			int rate,TeacherType teacherType) {
		super(userName,UserID,userType);
		this.lessons = new ArrayList<>();
		this.rooms = new ArrayList<>();
		this.students = new HashMap<>();
		this.course = course;
		this.maxstudentsnum = maxstudentsnum;
		this.setSubject(course.getSubject());
		this.setRate(rate);
		this.teacherType = teacherType;
	}
	/**
     * Sets the rate of the teacher.
     * @param rate The rate to be set.
     */
	public void setRate(int rate) {
		this.rate = (this.rate + rate)/2;
	}
	/**
     * Gets the rate of the teacher.
     * @return The rate of the teacher.
     */
	public int getRate() {
		return this.rate;
	}
	/**
     * Views the lessons taught by the teacher.
     * @return A string representation of the lessons taught by the teacher.
     */
	public String viewLessons() {
		String res = " Lessons "+"\n";
		for(Lesson l : lessons) {
			res += l.viewLesson() + "\n";
		}
		return res;
	}
	/**
     * Views the courses associated with the teacher.
     * @return A string representation of the courses associated with the teacher.
     */
	public String viewCourses() {
		return db.viewAllCourses();
	}	
	/**
     * Views information about a specific student.
     * @param ID The unique identifier of the student.
     * @return Information about the specified student.
     */
	public String viewInfoAboutStudent(String ID) {
		return getStudent(ID).getInfo();
	}
	/**
     * Manages a course with the specified course name.
     * @param courseName The name of the course to be managed.
     */
	public void manageCourse(String courseName) {
		db.getManager().getManageTeacher(courseName,this.getID());
	}
	/**
     * Sends complaints to the dean.
     * @param urlevel The urgency level of the complaint.
     * @param description The description of the complaint.
     * @param IDofStudent The unique identifier of the student filing the complaint.
     */
	public void sendComplaints(UrgencyLevel urlevel, String description, String IDofStudent) {
		Complain c = new Complain(urlevel, description, IDofStudent);
		db.getDean().addComplaint(c);
	}
	/**
     * Puts a mark for a student.
     * @param ID The unique identifier of the student.
     * @param marktype The type of the mark (1 for first attestation, 2 for second attestation, 3 for final mark).
     * @param point The point value of the mark.
     * @param semester The semester for which the mark is assigned.
     */
	public void putMark(String ID, int marktype, double point, int semester) {
		Bachelor student = getStudent(ID);
		Mark mark = student.getTranscript().getSemester(semester).getMarkBySubject(this.subject);
		if(marktype == 1) {
			mark.setFirst(point);
		}else if(marktype == 2) {
			mark.setSecond(point);	
		}
		else if(marktype == 3) {
			mark.setFinal(point);
		}
	}
	/**
     * Gets information about the teacher.
     * @return Information about the teacher.
     */
	public String getInfo() {
	    return info;
	}
	/**
     * Gets the associated course.
     * @return The associated course.
     */
	public Course getCourse() {
	    return course;
	}
	/**
     * Gets the maximum number of students the teacher can have.
     * @return The maximum number of students.
     */
	public int getMaxStudents() {
	    return maxstudentsnum;
	}
	/**
     * Gets the list of rooms where the teacher has lessons.
     * @return The list of rooms.
     */
	public List<Integer> getRooms() {
		return rooms;
	}
	/**
     * Gets the type of the teacher (e.g., MASTER, PROFESSOR).
     * @return The type of the teacher.
     */
	public TeacherType getType() {
		return teacherType;
	}
	/**
     * Gets the list of lessons taught by the teacher.
     * @return The list of lessons.
     */
	public List<Lesson> getLessons() {
		return lessons;
	}
	/**
     * Sets the associated course.
     * @param course The course to be set.
     */
	public void setCourse(Course course) {
		this.course = course;
	}
	/**
     * Adds a room to the list of rooms where the teacher has lessons.
     * @param room The room to be added.
     */
	public void addRoom(int room) {
		rooms.add(room);
	}
	/**
     * Gets the list of students associated with the teacher.
     * @return The list of students.
     */
	public List<Bachelor> getStudents() {
		return new ArrayList<>(students.values());
	}
	/**
     * Sets the subject associated with the teacher.
     * @param s The subject to be set.
     */
	public void setSubject(String s) {
		this.subject = s;
	}
	/**
     * Gets a specific student based on the unique identifier.
     * @param iD The unique identifier of the student.
     * @return The student object.
     */
	public Bachelor getStudent(String iD) {
		return students.get(iD);
	}
	/**
     * Gets the number of students associated with the teacher.
     * @return The number of students.
     */
	public int getStudentsNum() {
		return students.size();
	}
	/**
     * Adds a student to the list of associated students.
     * @param ID The unique identifier of the student.
     * @param b The student object to be added.
     */
	public void addStudent(String ID, Bachelor b) {
		students.put(ID, b);
	}
	/**
     * Adds a student to the list of associated students.
     * @param b The student object to be added.
     */
	public void addStudent(Bachelor b) {
		students.put(b.getID(),b);
	}

    /**
     * Checks if the teacher can add more students.
     * @return True if the teacher can add more students, false otherwise.
     */
	public boolean canAdd() {
		return students.size() < maxstudentsnum;
	}
	/**
     * Adds a lesson to the list of lessons taught by the teacher.
     * @param e The lesson to be added.
     */
	public void addLesson(Lesson e) {
		lessons.add(e);
	}
	/**
     * Adds lessons taught by the teacher to all associated students.
     * @return True if lessons are added successfully, false otherwise.
     */
	public boolean addLessonsForStudents() {
		if(!this.lessons.isEmpty()) {
			for (HashMap.Entry<String, Bachelor> entry : students.entrySet()) {
				Bachelor student = entry.getValue();
				student.addLessons(this.lessons);
        	}
			return true;
		}
		else {
			return false;
		}
	}
	 /**
     * Removes the associated course from the teacher.
     */
	public void removeCourse() {
		this.course = null;	
		this.lessons = null;
	}
}


