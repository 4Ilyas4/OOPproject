import java.io.Serializable;
import java.util.Objects;
/**
 * represents a lesson within an academic schedule.
 * 
 */
public class Lesson implements Serializable {
	private static final long serialVersionUID = 9L;
    /** The subject of the lesson */
    private String subject;
    /** The time at which the lesson occurs */
    private String time;
    /** The type of the lesson (e.g., lecture, pract). */
    private LessonType lessonType;
    /** The room number where the lesson takes place. */
    private int room;
    /**
     * Constructor. `Lesson` object with the specified subject, time, lesson type, and room number.
     *
     * @param subject    The subject of the lesson.
     * @param time       The time at which the lesson occurs.
     * @param lessonType The type of the lesson 
     * @param room       The room number where the lesson takes place.
     */
    public Lesson(String subject, String time, LessonType lessonType, int room) {
    	this.subject = subject;
    	this.time = time;
    	this.lessonType = lessonType;
    	this.room = room;
    }
    /**
     * Gets a string representation of the lesson.
     *
     * @return A string containing information about the lesson.
     */
    public String viewLesson() {
    	String res = "";
    	res += " subject : " + subject + " time : " + time + " lessonType : " + lessonType + " room  : " + room ;
    	return res;
    }
    /**
     * Gets the time at which the lesson occurs.
     *
     * @return The time of the lesson.
     */
    public String getTime() {
        return time;
    }
    /**
     * Gets the room number where the lesson takes place.
     *
     * @return The room number.
     */
    public int getRoom() {
        return room;
    }
    /**
     * Sets the time at which the lesson occurs.
     *
     * @param time The new time for the lesson.
     */
    public void setTime(String time) {
    	this.time = time;
    }
    /**
     * Sets the room number where the lesson takes place.
     *
     * @param room The new room number for the lesson.
     */
    public void setRoom(int room) {
    	this.room = room;
    } 
    /**
     * equals with another object
     *
     * @param o The object to compare.
     * @return `true` if the objects are equal, `false` otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return room == lesson.room &&
                Objects.equals(subject, lesson.subject) &&
                Objects.equals(time, lesson.time) &&
                lessonType.equals(lesson.lessonType);
    }
}

