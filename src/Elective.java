import java.io.Serializable;
import java.lang.*;
/**
 * An enumeration representing the type of course
 */
public enum Elective implements Serializable  {
	/**
     * non-departmental course
     */
    Free,

    /**
     * faculty course
     */
    Major,

    /**
     * non-departmental course.
     */
    Minor;
}

