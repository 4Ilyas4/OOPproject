import java.io.Serializable;
import java.util.Comparator;
import java.util.Vector;
// decorator
/**
 * decorator. each employee inherits canberesearcher and when he calls becomeresearcher he will get all the functionality of a researcher.
 */
public class CanBeResearcher extends User implements ResearcherM, Serializable {
	private static final long serialVersionUID = 3L;
	/**
     * constructor CanBeResearcher.
     * @param ID id
     * @param password password.
     * @param userType user type.
     */
    public CanBeResearcher(String ID, String password, UserType userType) {
		super(ID, password, userType);
		// TODO Auto-generated constructor stub
	}
    /**
     * is not a researcher bassicaly
     */
    protected Researcher researcher = null;
    /**
     * A method that allows the user to become a researcher in the system
     */
    public void becomeResearcher() {
    	this.researcher = new Researcher();
    	db.addResearcher(this);
    }
    /**
     * Calculates the Hinde index for the researcher.
     */
	@Override
    public void calculateHind() {
    	researcher.calculateHind();
    }
	/**
     * Outputs a list of scientific papers in sorted order using the specified comparator.
     * @param c A comparator for sorting.
     * @return A row with information about scientific papers.
     */
	@Override
    public String printPapers(Comparator<ResearchPaper> c) {
    	return researcher.printPapers(c);
    }
	/**
     * Publishes research papers with a specified topic and description.
     * @param topic Research Paper Topics.
     * @param description Research Paper Description.
     */
	@Override
    public void publishPapers(String topic,String description) {
    	researcher.publishPapers(topic,description);
    }
	/**
     * Adds a new research project with the specified topic.
     * @param topic Research Paper Topics.
     */
	@Override
	public void addResearch(String topic) {
		researcher.addResearch(topic);
	}
	/**
     * Adds a new research paper with the specified topic, title, authors, journal, number of pages, and date.
     * @param topic Research Paper Topics.
     * @param title Research Paper Title.
     * @param authors Authors of the research paper.
     * @param journal The journal in which the research paper is published.
     * @param pages Number of pages of the research paper.
     * @param date Date of publication of the research paper.
     */
	@Override
	public void addPaper(String topic,String title, String authors, String journal, int pages, String date) {
		researcher.addPaper(topic,title, authors, journal, pages, date);
		
	}
	/**
     * Returns the Hind index for the researcher.
     * @return hind index
     */
	public int getHind() {
		return researcher.getHind();
	}
	/**
     * Returns a Researcher 
     * @return  Researcher.
     */
	public Researcher getResearcher() {
		return researcher;
	}
	/**
     * Returns a list of research papers
     * @return  list of  papers.
     */
	public String justprintPapers() {
		return researcher.justprintPapers();
	}
	/**
     * Gets the ResearchProject with the specified topic.
     *
     * @param topic The topic of the ResearchProject to retrieve.
     * @return The ResearchProject with the specified topic.
     */
	@Override
	public ResearchProject getResearchProject(String topic) {
		// TODO Auto-generated method stub
		return researcher.getResearchProject(topic);
	}
	/**
     * Gets the ResearchPaper with the specified title.
     *
     * @param title The title of the ResearchPaper to retrieve.
     * @return The ResearchPaper with the specified title.
     */
	@Override
	public ResearchPaper getResearchPaper(String title) {
		// TODO Auto-generated method stub
		return researcher.getResearchPaper(title);
	}
	/**
     * inc num of citations
     * @param title title
     * @param r 
     */
	@Override
	public void citate(String title, CanBeResearcher r) {
		// TODO Auto-generated method stub
		researcher.citate(title,r);
	}
	/**
     * Obtains a citation for a research paper in the specified format.
     * @param title Research Paper Title.
     * @param f Citation Format.
     * @return A quote line or an empty line if no work is found.
     */
	@Override
	public String getCitation(String title,Format f) {
		// TODO Auto-generated method stub
		return researcher.getCitation(title,f);
	}
	/**
     * Returns a vector of projects
     * @return A vecotr of projects.
     */
	public Vector<ResearchProject> getResearchProjects() {
		// TODO Auto-generated method stub
		return researcher.getResearchProjects();
	}
	/**
     * Returns a vector of papers
     * @return A vector of papers.
     */
	public Vector<ResearchPaper> getResearchPapers() {
		// TODO Auto-generated method stub
		return researcher.getResearchPapers();
	}
	/**
     * Returns research proj
     * @return research proj
     */
	public ResearchProject getResearchProject() {
		// TODO Auto-generated method stub
		return researcher.getResearchProject();
	}
}

