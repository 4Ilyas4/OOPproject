import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
/**
 * The Researcher class represents a user in the system with the role of a researcher.
 * It implements the ResearcherM interface, providing methods to work with research projects and papers.
 */
public class Researcher implements ResearcherM ,Serializable {
	private static final long serialVersionUID = 16L;
	private DataBase db;	
	private int Hind;			
    private Vector<ResearchPaper> researchPapers;
    private Vector<ResearchProject> researchProjects;    
    private ResearchProject researchProject;
    /**
     * Constructs a Researcher object and initializes the necessary data structures.
     */
    public Researcher() {
    	db = DataBase.getInstance();
    	researchPapers = new Vector<>();
    	researchProjects = new Vector<>();
    }  
    /**
     * Gets research project.
     *
     * @return The ResearchProject.
     */
    public ResearchProject getResearchProject() {
    	return researchProject;
    }
    /**
     * Gets the ResearchProject with the specified topic.
     *
     * @param topic The topic of the ResearchProject to retrieve.
     * @return The ResearchProject with the specified topic.
     */
    public ResearchProject getResearchProject(String topic) {
    	ResearchProject rp = null;
    	for(ResearchProject re : researchProjects) {
    		if(re.getTopic().equals(topic)) {
    			rp = re;
    			break;
    		}
    	}
    	return rp;
    }
    /**
     * Gets the ResearchPaper with the specified title.
     *
     * @param title The title of the ResearchPaper to retrieve.
     * @return The ResearchPaper with the specified title.
     */
    public ResearchPaper getResearchPaper(String title) {
    	ResearchPaper rpp = null;
    	for(ResearchPaper re : researchPapers) {
    		if(re.getTitle().equals(title)) {
    			rpp = re;
    			break;
    		}
    	}
    	return rpp;
    }
    @Override
    /**
     * Checks if the current Researcher is equal to the specified object.
     *
     * @param o The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        CanBeResearcher other = (CanBeResearcher) o;
        return Hind == other.getHind() &&
                Objects.equals(db, other.db) &&
                Objects.equals(Hind, other.getHind()) &&
                Objects.equals(researchPapers, other.getResearchPapers()) &&
                Objects.equals(researchProjects, other.getResearchProjects()) &&
                Objects.equals(researchProject, other.getResearchProject());
    }
    /**
     * Increases the number of citations for the specified ResearchPaper.
     *
     * @param title The title of the ResearchPaper.
     * @param r     The researcher to attribute the citation.
     */
    public void citate(String title, CanBeResearcher r) {
    	ResearchPaper rpp = r.getResearchPaper(title);
    	if(!this.equals(r)) {
	    	if(rpp != null) {
	    		rpp.addCitations();
	    	}
	    	else {
	    		System.out.println("There is no such paper");
	    	}
	    	return ;
    	}
    	System.out.println("There is no such Researcher");
    }
    /**
     * Gets the citation for a ResearchPaper in the specified format.
     *
     * @param title The title of the ResearchPaper.
     * @param f     The citation format.
     * @return A citation string.
     */
	public String getCitation(String title,Format f) {
		 ResearchPaper rp = getResearchPaper(title);
		 return rp.getCitation(f);
    }
	 /**
     * Calculates the H-index for the researcher based on their research papers' citations.
     */
    public void calculateHind() {
        if (researchPapers != null && !researchPapers.isEmpty()) {
            Collections.sort(researchPapers, (p1, p2) -> Integer.compare(p2.getCitations(), p1.getCitations()));
            for (int i = 0; i < researchPapers.size(); i++) {
                if (researchPapers.get(i).getCitations() < i + 1) {
                    Hind = i;
                    break;
                }
                Hind = i + 1;
            }
        } else {
            Hind = 0;
        }
    }
    /**
     * Gets the H-index of the researcher.
     *
     * @return The H-index.
     */
    public int getHind() {
        return Hind;
    }
    /**
     * Prints the list of research papers based on the provided comparator.
     *
     * @param c The comparator to sort the papers.
     * @return A string representation of the sorted research papers.
     */
    public String printPapers(Comparator<ResearchPaper> c) {
        String result = "";
        if (c == null) {
            for (ResearchPaper paper : researchPapers) {
                result += paper.getTitle()+"\n";
            }
            return result;
        } 
        researchPapers.sort(c);
        return justprintPapers();
    }
    /**
     * Adds a new research project to the researcher's list.
     *
     * @param topic The topic of the new research project.
     */
    public void addResearch(String topic) { 
    	ResearchProject researchProject = new ResearchProject(topic);
    	researchProjects.add(researchProject);
 	
    }
    /**
     * Adds a new research paper to the specified research project.
     *
     * @param topic   The topic of the research project.
     * @param title   The title of the research paper.
     * @param authors The authors of the research paper.
     * @param journal The journal of the research paper.
     * @param pages   The number of pages of the research paper.
     * @param date    The publication date of the research paper.
     */
    public void addPaper(String topic,String title, String authors, String journal, int pages, String date) {
    	ResearchProject rp = null;
    	for(ResearchProject r : researchProjects) {
    		if(r.getTopic().equals(topic)) {
    			rp = r;
    			break;
    		}
    	}
    	if(rp != null) {
	    	ResearchPaper r = new ResearchPaper(title,authors,journal,pages,date);
	    	researchPapers.add(r);
	    	rp.addPublishedPaper(r);
    	}
    	else {
    		System.out.println("There is no such research project with such topic !");
    	}
    }
    /**
     * Publishes research papers from the specified research project as news.
     *
     * @param topic       The topic of the research project.
     * @param description The description of the news.
     */
    public void publishPapers(String topic,String description) {
    	ResearchProject rp = null;
    	for(ResearchProject r : researchProjects) {
    		if(r.getTopic().equals(topic)) {
    			rp = r;
    			break;
    		}
    	}
    	if(rp != null) {
	    	List<ResearchPaper> papers = rp.getPublishedPapers();
	    	News n = new News(topic,description,papers,true);
	    	db.getManager().addNewsM(n);
    	}
    	else {
    		System.out.println("There is no such research project with such topic !");
    	}
    }
    /**
     * Prints a string representation of the research papers.
     *
     * @return A string representation of the research papers.
     */
    public String justprintPapers() {
    	String result = "";
        for (ResearchPaper paper : researchPapers) {
            result += paper.getTitle() + " - Date: " + paper.getDate() + " - Citations: " + paper.getCitations() + " - Pages: " + paper.getPages() + "\n";
        }
		return result;
    }
    /**
     * Gets the list of research projects.
     *
     * @return The list of research projects.
     */
	public Vector<ResearchProject> getResearchProjects() {
		return researchProjects;
	}
	/**
     * Gets the list of research papers.
     *
     * @return The list of research papers.
     */
	public Vector<ResearchPaper> getResearchPapers() {
		return researchPapers;
	}
	public void addResearch(ResearchProject r) {
		researchProjects.add(r);	
	}

}

