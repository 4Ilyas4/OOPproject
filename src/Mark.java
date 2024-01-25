import java.io.Serializable;
import java.lang.*;
/**
 * Represents a mark for a specific subject.
 */
public class Mark implements Serializable  {
	private static final long serialVersionUID = 12L;
	// 1att
	private double firstatt = 0;
	// 2att
	private double secondatt = 0;
	// final mark
	private double finalmrk = 0;
	// marktype 
	private int marktype;
	// subject
	private String subject;
	
	/**
     * Constructor for creating a Mark object with a specified subject.
     *
     * @param subject The subject for which the mark is recorded.
     */
	public Mark(String subject) {
		this.subject = subject;
	}
	/**
     * Constructor for creating a Mark object with specified subject, point, and mark type.
     *
     * @param subject  The subject for which the mark is recorded.
     * @param point    The point value of the mark.
     * @param marktype The type of the mark (1 for first attestation, 2 for second attestation, 3 for final mark).
     */
	public Mark(String subject,double point,int marktype) {
		this.subject = subject;
		if(marktype == 1) {
			this.setFirst(point);
		}
		if(marktype == 2) {
			this.setSecond(point);
		}
		if(marktype == 3) {
			this.setFinal(point);
		}
	}
	/**
     * Set the point value for the first attestation.
     *
     * @param first points for the first attestation.
     */
	public void setFirst(double first) {
		this.firstatt = first;
	}
	 /**
     * Set the point value for the second attestation.
     *
     * @param second points value for the second attestation.
     */
	public void setSecond(double second) {
		this.secondatt = second;
	}
	/**
     * Set the point value for the final mark.
     *
     * @param fin points value for the final mark.
     */
	public void setFinal(double fin) {
		this.finalmrk = fin;
	}
	/**
     * Get the point value for the first attestation.
     *
     * @return The point value for the first attestation.
     */
	public double getFirst() {
	    return firstatt;
	}
	/**
     * Get the point value for the second attestation.
     *
     * @return The point value for the second attestation.
     */
	public double getSecond() {
	    return secondatt;
	}
	/**
     * Get the point value for the final
     *
     * @return points for the final
     */
	public double getFinal() {
	    return finalmrk;
	}
	/**
     * Get the subject for which the mark is recorded.
     *
     * @return The subject for which the mark is recorded.
     */
	public String getSubject() {
	    return subject;
	}
	/**
     * Get the total points. 1att+2att+final
     *
     * @return The total point value.
     */
	private double getTotal() {
		return getFirst() + getSecond() + getFinal();
	}
	/**
     * Get the letter representation of the total mark.
     *
     * @return The letter representation of the total mark.
     */
	public String getTotalMark() {
		String letter = "";
		if (this.getTotal() >= 95 && this.getTotal() <= 100) 
            letter = "A";
        if (this.getTotal() >= 90) 
        	letter = "A-"; 
        if (this.getTotal() >= 85) 
        	letter = "B+"; 
        if (this.getTotal() >= 80) 
        	letter = "B"; 
        if (this.getTotal() >= 75) 
        	letter = "B-"; 
        if (this.getTotal() >= 70) 
        	letter = "C+"; 
        if (this.getTotal() >= 65) 
        	letter = "C"; 
        if (this.getTotal() >= 60) 
        	letter = "C-"; 
        if (this.getTotal() >= 55) 
        	letter = "D+"; 
        if (this.getTotal() >= 50) 
        	letter = "D"; 
        else letter = "F";        
         return letter;
	}
	/**
     * Get a string representation of the mark.
     *
     * @return A string representation of the mark.
     */
	public String viewMark() {
		// TODO Auto-generated method stub
		return " subject " + subject + " first attestation : " + firstatt + " second attestation : " + secondatt + " final : " + finalmrk + " totalLetterMark: " +  getTotalMark();
	}
}

