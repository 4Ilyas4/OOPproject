import java.io.Serializable;
import java.util.Vector;

/**
 * The GraduateStudent class represents a graduate student in the system
 */
public class GraduateStudent extends Bachelor implements ResearcherM, Serializable  {
	private static final long serialVersionUID = 8L;
	private String info;
    private Vector<ResearchProject> diplomaProjects;    
    public CanBeResearcher supervisor;
    /**
     * Constructs a GraduateStudent object with the specified ID and password. automatically become researcher
     *
     * @param ID       The ID of the graduate student.
     * @param password The password of the graduate student.
     */
    public GraduateStudent(String ID, String password) {
		super(ID, password, UserType.GraduateStudent);
		this.becomeResearcher();
		diplomaProjects = new Vector<>();
	}
    /**
     * Gets the supervisor of the graduate student.
     *
     * @return The supervisor of the graduate student.
     */
    public CanBeResearcher getSupervisor() {
    	return supervisor; 
    }  
    /**
     * Sets the supervisor of the graduate student and checks if the supervisor's h-index is sufficient.
     *
     * @param supervisor The supervisor to be set.
     * @throws LowHIndexSupervisorException If the supervisor's h-index is too low.
     */
    public void setSupervisor(CanBeResearcher supervisor) throws LowHIndexSupervisorException {
    	if(supervisor != null) {
	        supervisor.calculateHind(); 
	        if (supervisor.getHind() < 3) {
	            throw new LowHIndexSupervisorException("Supervisor's h-index is too low!");
	        }
    	}
    	else {
    		System.out.println("There is no supervisor!");
    	}
    } 
    /**
     * Gets the additional information about the graduate student.
     *
     * @return Additional information about the graduate student.
     */
    public String getInfo() {
		return info;
    }
    /**
     * Gets the vector of diploma projects associated with the graduate student.
     *
     * @return The vector of diploma projects.
     */
	public Vector<ResearchProject> getDiplomaProjects() {
		return diplomaProjects;
	}
	/**
     * Adds a diploma project to the vector of diploma projects.
     *
     * @param r The diploma project to be added.
     */
	public void addDiplomaProject(ResearchProject r) {
		diplomaProjects.add(r);
		researcher.addResearch(r);
	}
	/**
     * Sets the vector of diploma projects associated with the graduate student.
     *
     * @param diplomaProjects The vector of diploma projects to be set.
     */
	public void setDiplomaProjects(Vector<ResearchProject> diplomaProjects) {
		this.diplomaProjects = diplomaProjects;
	}
}

