import java.io.Serializable;
import java.lang.*;
/**
 * An enumeration representing the different types of users in the system.
 * Each user type defines their role or status in the system.
 */
public enum UserType implements Serializable  {
	/**
     * System Manager
     */
    Manager,

    /**
     * System Administrator.
     */
    Admin,

    /**
     * Educator
     */
    Teacher,

    /**
     * Bachelor's degree
     */
    Bachelor,

    /**
     * Technical Support Specialist.
     */
    TechSupportSpecialist,

    /**
     * master's student, doctoral student
     */
    GraduateStudent,

    /**
     * Dean of Faculty.
     */
    Dean;
}

