import java.io.Serializable;
import java.lang.*;

/**
 * The Course class
 */
public class Course implements Serializable {
	private static final long serialVersionUID = 5L;
    private String courseName;
    private int lecturenum;
    private int practicenum;
    private String subject;
    private String description;
    private Elective elective;
    /**
     * Gets the subject of the course.
     *
     * @return The subject of the course.
     */
    public String getSubject() {
        return subject;
    }
    /**
     * Gets the description of the course.
     *
     * @return The description of the course.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Constructs a Course object with the specified parameters.
     *
     * @param courseName  The name of the course.
     * @param lecturenum  The number of lecture hours for the course.
     * @param practicenum The number of practical hours for the course.
     * @param subject     The subject of the course.
     * @param description A description of the course.
     * @param elective    The elective category to which the course belongs.
     */
    public Course(String courseName, int lecturenum, int practicenum, String subject, String description, Elective elective) {
    	this.courseName = courseName;
    	this.subject = subject;
    	this.lecturenum = lecturenum;
    	this.practicenum = practicenum;
    	this.description = description;
    	this.elective = elective;
    }
    /**
     * Gets the name of the course.
     *
     * @return The name of the course.
     */
    public String getCourseName() {
        return courseName;
    }
    /**
     * Sets the subject of the course.
     *
     * @param subject The new subject for the course.
     */
    public void setSubject(String subject) {
    	this.subject = subject;
    }
    /**
     * Sets the number of lecture hours for the course.
     *
     * @param lecturenum The new number of lecture hours.
     */
    public void setLecturenum(int lecturenum) {
    	this.lecturenum = lecturenum;
    }
    /**
     * Sets the number of practical hours for the course.
     *
     * @param practicenum The new number of practical hours.
     */
    public void setPracticenum(int practicenum) {
    	this.practicenum = practicenum;
    }
    /**
     * Sets the description of the course.
     *
     * @param description The new description for the course.
     */
    public void setDescription(String description) {
    	this.description = description;
    }
    /**
     * Sets the elective category to which the course belongs.
     *
     * @param elective The new elective category for the course.
     */
    public void setElective(Elective elective) {
    	this.elective = elective;
    }
}

