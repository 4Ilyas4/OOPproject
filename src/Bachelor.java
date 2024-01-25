
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents a Bachelor user in the system.
 */
public class Bachelor extends CanBeResearcher implements Serializable  {	
	private static final long serialVersionUID = 2L;
	
	private int semesternum;
	private List<Lesson> lessons;
	private String studentOrganization;
	private List<String> courses;
	private Transcript transcript;
	private String info;
	/**
     * Constructor for the Bachelor class with the UserType GraduateStudent.
     *
     * @param ID       ID of the bachelor.
     * @param password Password of the bachelor.
     */
	public Bachelor(String ID, String password, UserType graduatestudent) {
		super(ID, password, UserType.GraduateStudent);
		transcript = new Transcript();
		courses = new ArrayList<>();
		lessons = new ArrayList<>(); 
	}
	/**
     * Constructor for the Bachelor class with the UserType Bachelor.
     *
     * @param ID       ID of the bachelor.
     * @param password Password of the bachelor.
     */
	public Bachelor(String ID, String password) {
		super(ID, password, UserType.Bachelor);
		transcript = new Transcript();
		courses = new ArrayList<>();
		lessons = new ArrayList<>(); 
	}
	
	/**
     * Views the lessons enrolled by the bachelor.
     *
     * @return String representation of the enrolled lessons.
     */
	public String viewLessons() {
		String res = "";
		for(Lesson l : lessons) {
			res += l.viewLesson() + "\n";
		}
		return res;
	}
	/**
     * Views the courses available in the system.
     *
     * @return String representation of all available courses.
     */
	public String viewCourses() {
		return db.viewAllCourses();
	}
	/**
     * Rates a teacher with the specified ID.
     *
     * @param ID   ID of the teacher to rate.
     * @param rate Rating value.
     * @return Empty string if the operation is successful, an error message otherwise.
     */
	public String rateTeacher(String ID, int rate) {
		if(rate <= 100 && rate >= 0) {
			Teacher t = db.getTeacher(ID);
			if(t!=null) {
				t.setRate(rate);
			}
			else {
				return "There is no such Teacher";
			}
			return "";
		}
		else {
			return "Rate is not correct";
		}
	}
	/**
     * Gets the marks of the bachelor for a specific semester and subject.
     *
     * @param semester Semester number.
     * @param subject  Subject for which marks are requested.
     * @return List of marks for the specified semester and subject.
     */
	public List<Mark> getMarks(int semester, String subject) {
		List<Mark> M = this.getTranscript().getSemester(semester).getMarks(subject);
		return M;
	}
	/**
     * Views the marks of the bachelor for a specific semester.
     *
     * @param semester Semester number.
     * @return String representation of the marks for the specified semester.
     */
	public String viewMarks(int semester) {
		String res = "";
		Transcript t = this.getTranscript();
		if(t != null) {
			List<Mark> M = this.getTranscript().getSemester(semester).getMarks();
			for(Mark m : M) {
				res += m.viewMark() + "\n";
			}
		}
		return res;
	}
	/**
     * Registers the bachelor for a specific course.
     *
     * @param cn Course name to register for.
     */
	public void registerForCourse(String cn) {
		db.getManager().getManageStudent(cn,this.getID());
	}
	/**
     * Sets the student organization of the bachelor.
     *
     * @param storg Student organization to set.
     */
	public void setStudentOrganization(String storg) {
		this.studentOrganization = storg;
	}
	/**
     * Gets the transcript of the bachelor.
     *
     * @return Transcript object representing the academic performance of the bachelor.
     */
	public Transcript getTranscript() {
		return transcript;
	}
	/**
     * Views the transcript of the bachelor.
     *
     * @return String representation of the academic transcript.
     */
	public String viewTranscript() {
		return transcript.viewTranscript();

	}
	/**
     * Views information about a teacher with the specified ID.
     *
     * @param ID ID of the teacher.
     * @return String representation of the teacher's information or an error message if the teacher is not found.
     */
	public String viewInfoAboutTeacher(String ID) {
		Teacher t = db.getTeacher(ID);
		if(t!=null) {
			return t.getInfo();
		}
		else {
			return "There is no such Teacher";
		}
	}
	/**
     * Gets  information about the bachelor.
     *
     * @return String representation of  information.
     */
	public String getInfo() {
	    return info;
	}
	/**
     * Sets the transcript for the bachelor.
     *
     * @param t Transcript object to set.
     */
	public void setTranscript(Transcript t) {
		this.transcript = t;
	}
	/**
     * Gets list of courses
     *
     * @return list of courses
     */
	public List<String> getCourses() {
		return courses;
	}
	/**
     * adding a new course to student
     *
     * @param coursename name of course
     */
	public void addCourse(String coursename) {
		courses.add(coursename);
	}
	/**
     * Gets list of lessons
     *
     * @return list of lessong
     */
	public List<Lesson> getLessons() {
		return lessons;
	}
	/**
     * set lesson 
     *
     * @return set lesson
     */
	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}
	/**
     * add new lesson
     *
     * @param lessons1 adding list of lessons
     */
	public void addLessons(List<Lesson> lessons1) {
		for(Lesson l : lessons1) {
			this.lessons.add(l);
		}
	}
	/**
     * get semestr 
     *
     * @return get semestr
     */
	public int getSemester() {
		return semesternum;
	}
	
}

