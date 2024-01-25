
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

class Pair implements Serializable {
	private static final long serialVersionUID = 11L;
	private String a;
	private String b;
	
	public Pair(String a,String b){
		this.a = a;
		this.b = b;
	}
	public String getFirst() {
		return a;
	}
	public String getSecond() {
		return b;
	}
};
/**
 * The Manager class represents a manager in the system
 */
public class Manager extends Employee implements Serializable  {	
	private static final long serialVersionUID = 10L;
    private Vector<Pair> teachersManageQueue;
    private Vector<Pair> studentsManageQueue;
    /**
     * Constructs a Manager object with the specified ID and password.
     *
     * @param ID       The unique identifier for the manager.
     * @param password The password associated with the manager's account.
     */
    public Manager(String ID, String password) {
		super(ID, password, UserType.Manager);
		teachersManageQueue = new Vector<>();
		studentsManageQueue = new Vector<>();
	} 
    /**
     * Generates a report for the specified semester and subject, displaying the average mark for each mark type.
     *
     * @param semester The semester for which the report is generated.
     * @param subject  The subject for which the report is generated.
     * @return A report displaying the average mark for each mark type.
     */
    public String report(int semester, String subject) {
    	String res = "";
    	double[] marks = db.averageMarks(semester, subject);
    	for(double m : marks) {
    		 res +=" subject: " + subject + " average mark for each mark type : " + m + "\n";
    	}
    	return res; 
    }
    /**
     * Assigns a teacher to a course based on the teacher's ID.
     *
     * @param ID The ID of the teacher to be assigned to the course.
     */
    public void assignCourseTeacher(String ID) {
        int index = -1;
        for (int i = 0; i < teachersManageQueue.size(); i++) {
            if (teachersManageQueue.get(i).getFirst().equals(ID)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            Pair p = teachersManageQueue.remove(index);
            db.registerTeacherForCourse(ID, p.getSecond());
        } else {
            System.out.println("ID not found in teachersManageQueue");
        }
    }
    /**
     * Approves student registration for a course based on the student's ID.
     *
     * @param ID The ID of the student whose registration is approved.
     */
    public void approveStudentRegistration(String ID) {
        int index = -1;
        for (int i = 0; i < studentsManageQueue.size(); i++) {
            if (studentsManageQueue.get(i).getFirst().equals(ID)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            Pair p = studentsManageQueue.remove(index);
            db.registerStudentForCourse(ID, p.getSecond());
        } else {
            System.out.println("ID not found in studentsManageQueue");
        }
    }
    /**
     * Adds a new course to the system for student registration.
     *
     * @param courseName   The name of the course.
     * @param lecturenum   The number of lectures for the course.
     * @param practicenum  The number of practical sessions for the course.
     * @param subject      The subject of the course.
     * @param description  The description of the course.
     * @param elective     The elective status of the course.
     */
    public void addCourseForRegistration(String courseName, int lecturenum, int practicenum, String subject,
        String description, Elective elective) {
        Course cours = new Course(courseName,lecturenum,practicenum,subject,description,elective);
        db.addCourse(courseName,cours);
    }
    /**
     * Adds a teacher-course pair to the manager's queue for further processing.
     *
     * @param courseName The name of the course.
     * @param teacherId  The ID of the teacher.
     */
    public void getManageTeacher(String courseName, String TeacherId) {
    	teachersManageQueue.add(new Pair(TeacherId,courseName));
    }
    /**
     * Adds a student-course pair to the manager's queue for further processing.
     *
     * @param courseName The name of the course.
     * @param studentId  The ID of the student.
     */
    public void getManageStudent(String courseName, String StudentID) {
    	studentsManageQueue.add(new Pair(StudentID,courseName));
    }
    /**
     * Displays the student registration queue.
     *
     * @return A string representation of the student registration queue.
     */
    public String viewStudentQueue() {	
    	String res = "";
		for(Pair p : studentsManageQueue) {
			String r = " Student ID: " + p.getFirst() + " CourseName " + p.getSecond() + "\n";
			System.out.println(r);
			res += r;
		}
		return res;
    }
    /**
     * Displays the teacher assignment queue.
     *
     * @return A string representation of the teacher assignment queue.
     */
    public String viewTeacherQueue() {
    	String res = "";
		for(Pair p : teachersManageQueue ) {
			String r = " Teacher ID: " + p.getFirst() + " CourseName " + p.getSecond() + "\n";
			System.out.println(r);
			res += r;
		}	
		return res;
    }
    /**
     * Displays the information about teachers and the courses they are assigned to.
     *
     * @return A string representation of the information about teachers and their assigned courses.
     */
    public String viewTeachersAndCourses() {
    	String res = "";
    	List<Teacher> teachers = db.getTeachers();
    	for(Teacher t : teachers) {
    		res += " Teacher: " + t.getName() + " Course: " + t.getCourse() + "\n";
    	}
    	return res;
    }
    /**
     * Assigns a lesson to a teacher.
     *
     * @param subject      The subject of the lesson.
     * @param lessonType   The type of the lesson (lecture or practice).
     * @param time         The time of the lesson.
     * @param room         The room number of the lesson.
     * @param IDofTeacher  The ID of the teacher to whom the lesson is assigned.
     */
    public void assignLessonToTeacher(String subject, LessonType lessonType, 
            String time, int room,
        String IDofTeacher) {
    	Lesson lesson = new Lesson(subject, time, lessonType, room);
        db.addLessonsToTeacher(lesson,IDofTeacher);
    }
    /**
     * Removes a lesson from a teacher's schedule.
     *
     * @param lesson       The lesson to be removed.
     * @param IDofTeacher  The ID of the teacher from whose schedule the lesson is removed.
     */
    public void removeTeacherLesson(Lesson lesson,
        String IDofTeacher) {
        db.removeTeacherLesson(lesson,IDofTeacher);
    }
    /**
     * Displays information about a student taught by a specific teacher.
     *
     * @param IDofTeacher  The ID of the teacher.
     * @param IDofStudent  The ID of the student.
     * @return A string representation of the information about the student.
     */
    public String viewInfoAboutTeachersStudent(String IDofTeacher,String IDofStudent) {
    	 return db.getTeacher(IDofTeacher).getStudent(IDofStudent).getInfo();
    }
    /**
     * Adds lessons to all students in the system.
     */
    public void addLessonsToStudents() {
    	db.addLessonsToStudents();
    }
    /**
     * Removes a lesson from a student's schedule.
     *
     * @param IDofStudent  The ID of the student.
     * @param subject      The subject of the lesson.
     * @param lessonType   The type of the lesson (lecture or practice).
     * @param time         The time of the lesson.
     * @param room         The room number of the lesson.
     */
    public void removeStudentLesson(String IDofStudent, String subject, LessonType lessonType, 
            String time, int room) {
    	Lesson lesson = new Lesson(subject,time,lessonType,room);
        db.removeStudentLessson(IDofStudent,lesson);
    }
    /**
     * Adds news to the system.
     *
     * @param news The news to be added.
     */
    public void addNewsM(News news) {
    	db.addNews(news);
    }
    /**
     * Adds news to the system with the specified topic and description.
     *
     * @param topic       The topic of the news.
     * @param description The description of the news.
     */
    public void addNews(String topic,String description) {
    	News news = new News(topic,description,null,false);
    	db.addNews(news);
    }
}

