import java.util.Comparator;
/**
 * An interface that introduces the researcher to the system.
 */
public interface ResearcherM {
	/**
     * Obtains a research project on an assigned topic.
     * 
     * @param topic Research Project Topic.
     * @return researchProject
     */
	public ResearchProject getResearchProject(String topic);
	/**
     * Obtains a research paper on a given title.
     * 
     * @param title Research Paper Title.
     * @return ResearchPaper
     */
	public ResearchPaper getResearchPaper(String title);
	/**
     * add citate
     * 
     * @param title Research Paper Title.
     * @param r researcher
     */
	public void citate(String title,CanBeResearcher r);
	/**
     * Obtains a citation for a research paper in the specified format.
     * 
     * @param title Research Paper Title.
     * @param f format
     * @return citation
     */
	public String getCitation(String title,Format f);
	/**
     * calculate h index of paper
     */
    public void calculateHind();
    /**
     *	Outputs a list of scientific papers in sorted order using the specified comparator.
     *
     * @param comparator A comparator for sorting.
     * @return string with info
     */
    public String printPapers(Comparator<ResearchPaper> comparator);
    /**
     * publish paper with topic and decr
     * 
     * @param topic topic of paper
     * @param description descr of paper
     */
    public void publishPapers(String topic, String description);
    /**
     * new research project
     * 
     * @param topic  topic of new project
     */
    public void addResearch(String topic);
    /**
     * Adds a new research paper with the specified topic, title, authors, journal, number of pages, and date.
     * 
     * @param topic topic of paper
     * @param title title of paper
     * @param authors  authors of paper
     * @param journal journal that published paper.
     * @param pages count of pages
     * @param date  date when was published
     */
    public void addPaper(String topic,String title, String authors, String journal, int pages, String date);
}