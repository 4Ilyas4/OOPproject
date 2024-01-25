
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Represents a semester
 */
public class Semester implements Serializable {
	
	private static final long serialVersionUID = 19L;
	// gpa
    private double gpa = 0;
    // The credits earned in the semester.
    private int credits = 0;
    // The semester number.
    private int semester = 1;

    private HashMap<String,Mark> marks;
    /**
     * Constructs a Semester object with an empty marks HashMap.
     */
    public Semester() {
    	this.marks = new HashMap<>();
    	
    }
    /**
     * Constructs a Semester object with specified GPA, credits, and semester.
     *
     * @param gpa      The GPA for the semester.
     * @param credits  The credits earned in the semester.
     * @param semester The semester number.
     */
    public Semester(double gpa ,int credits, int semester) {
    	this.gpa = gpa;
    	this.credits = credits;
    	this.semester = semester;
    	this.marks = new HashMap<>();
    }
    /**
     * shows grades for each course in the semester
     *
     * @return A string representation of the semester.
     */
    public String viewSemester() {
    	String res = "";
    	List<Mark> semmarks = new ArrayList<>(marks.values());
    	for (Mark mrk : semmarks) {
			res += mrk.viewMark() + "\n";
		}
    	return res;
    }
    /**
     * Add GPA to the semester
     *
     * @param add The GPA to be added.
     */
    public void addGpa(int add) {
    	if(gpa < 4) {
    		gpa += add;
    	}
    }
    /**
     * Add credits to the semester
     *
     * @param add The credits to be added.
     */
    public void addCredits(int add) {
    	if(credits < 21) {
    		credits += add;
    	}
    }
    /**
     * Set the semester number.
     *
     * @param semester The semester number.
     */
    public void setSemester(int semester) {
    	this.semester = semester;
    }
    /**
     * Add a mark for a specific subject to the semester.
     *
     * @param subj The subject for which the mark is added.
     * @param mrk  The Mark object representing the student's performance.
     */
    public void addMark(String subj,Mark mrk) {
    	if(!marks.containsValue(mrk)) {
    		marks.put(subj,mrk);
    	}
    }
    /**
     * Set the marks for the semester using a HashMap.
     *
     * @param marks The HashMap containing subjects as keys and corresponding marks as values.
     */
    public void setMarks(HashMap<String,Mark> marks) {
    	this.marks = marks;
    }
    /**
     * Get the GPA of the semester.
     *
     * @return The GPA of the semester.
     */
    public double getGpa() {
    	return gpa;
    }
    /**
     * Get the total credits earned in the semester.
     *
     * @return The total credits earned in the semester.
     */
    public int getCredits() {
    	return credits;
    }
    /**
     * Get a list of marks for all subjects in the semester.
     *
     * @return A list of Mark objects representing the student's performance.
     */
    public List<Mark> getMarks() {
        return new ArrayList<>(marks.values());
    }
    /**
     * Get the Mark object for a specific subject.
     *
     * @param subj The subject for which the mark we need to know
     * @return The Mark object representing the student's performance in the specified subject.
     */
    public Mark getMarkBySubject(String subj) {
    	return marks.get(subj);
    }
    /**
     * Get the semester number.
     *
     * @return The semester number.
     */
    public int getSemester() {
    	return semester;
    }
    /**
     * Get a list of marks for a specific subject in the semester.
     *
     * @param subject The subject for which marks are retrieved.
     * @return A list of Mark objects representing the student's performance in the specified subject.
     */
	public List<Mark> getMarks(String subject) {
		List<Mark> marksForSubject = new ArrayList<>();
		for (HashMap.Entry<String, Mark> entry : marks.entrySet()) {
			String entrySubject = entry.getKey();
			if (entrySubject.equals(subject)) {
				marksForSubject.add(entry.getValue());
			}
		}
		return marksForSubject;	
	}
}

