import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents the academic transcript of a student, including information about each semester's performance.
 */
public class Transcript implements Serializable {
	private static final long serialVersionUID = 22L;
	/** The list of semesters included in the transcript. */
    private List<Semester> semesters;
    // semes
    /** The overall GPA (Grade Point Average) of the student. */
    private double gpa;
    // count of credits
    /** The total number of credits earned by the student. */
    private int credits;
    /**
     * Constructs an empty transcript with initial values.
     */
    public Transcript() {
    	credits = 0;
    	gpa = 0;
    	semesters = new ArrayList<>();
    }
    /**
     * Generates a formatted string of the transcript, including information about each semester's performance,
     * total GPA, and total credits earned.
     *
     * @return The formatted string representation of the transcript.
     */
    public String viewTranscript() {
    	String res = "";
    	for(Semester s : semesters) {
    		res += "Semester: " + s.getSemester() + s.viewSemester();
    	}
    	return (res + " total gpa : " + gpa + " total credits : " + credits);
    }
    /**
     * Calculates the overall GPA by averaging the GPAs of all included semesters.
     */
    public void totalGpa() {
    	double res = 0;
    	int cnt = 0;
    	for(Semester s : semesters) {
    		res += s.getGpa();
    		cnt++;
    	}
    	gpa = res/cnt;
    }
    /**
     * Calculates the total number of credits earned by summing the credits of all included semesters.
     */
    public void totalCredits() {
    	int res = 0;
    	int cnt = 0;
    	for(Semester s : semesters) {
    		res +=s.getCredits();
    		cnt++;
    	}
    	gpa = res/cnt;
    }
    /**
     * Adds a new semester to the transcript.
     *
     * @param s The semester to be added.
     */
    public void addSemester(Semester s) {
    	semesters.add(s);
    }
    /**
     * Retrieves the list of semesters included in the transcript.
     *
     * @return The list of semesters.
     */
    public List<Semester> getSemesters() {
        return semesters;
    }
    /**
     * Retrieves a specific semester from the transcript based on the semester number.
     *
     * @param semester The semester number.
     * @return The specified semester.
     */
	public Semester getSemester(int semester) {
		Semester res = new Semester();
		Semester it;
		for(int i=0;i<semesters.size();i++) {
			it = semesters.get(i);
			if(it.getSemester() == semester) {
				res = it;
			}
		}
		return res;
	}
	/**
     * Retrieves the overall GPA of the student.
     *
     * @return The overall GPA.
     */
    public double getGpa() {
    	return gpa;
    }
    /**
     * Retrieves the total number of credits earned by the student.
     *
     * @return The total credits earned.
     */
    public int getCredits() {
    	return credits;
    }
}

