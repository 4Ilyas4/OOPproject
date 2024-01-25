import java.io.Serializable;
import java.lang.*;
import java.util.Vector;
/**
 * A class presenting a research project.
 */
public class ResearchProject implements Serializable  {
	private static final long serialVersionUID = 18L;
	// Research Project Topic.
    private String topic;
    // A vector of published research articles.
    private Vector<ResearchPaper> publishedPapers;
    // Project Participant Vector.
    private Vector<CanBeResearcher> participants;
    /**
     * Constructor of the ResearchProject class. When you set up a research project, there are no participants. Just a topic, and then you recruit researchers.
     *
     * @param topic Research Project Topic.
     */
    public ResearchProject(String topic) {
    	this.topic = topic;
    	publishedPapers = new Vector<>();
    	participants = new Vector<>();
    }
    /**
     * Sets the topic of the research project.
     *
     * @param t A new research project topic.
     */
    public void setTopic(String t) {
    	this.topic = t;
    }
    /**
     * Returns the topic of the research project.
     *
     * @return Research Project Topic.
     */
    public String getTopic() {
    	return topic;
    }
    /**
     * Adds a participant to a research project.
     *
     * @param researcher Project Participant.
     * @throws NotResearcherException If the participant is not a researcher.
     */
    public void addParticipant(CanBeResearcher researcher) throws NotResearcherException {
        if (!(researcher instanceof CanBeResearcher && researcher.getResearcher() != null)) {
            throw new NotResearcherException("Only researchers can join!");
        }
        participants.add(researcher);
    }
    /**
     * Returns a vector of published research articles.
     *
     * @return A vector of published research articles.
     */
	public Vector<ResearchPaper> getPublishedPapers() {
		return publishedPapers;
	}
	/**
     * Sets a vector of published research articles.
     *
     * @param publishedPapers A new vector of published research articles.
     */
	public void setPublishedPapers(Vector<ResearchPaper> publishedPapers) {
		this.publishedPapers = publishedPapers;
	}
	/**
     * Adds a published research article to the project.
     *
     * @param publishedPaper A published research article.
     */
	public void addPublishedPaper(ResearchPaper publishedPaper) {
		this.publishedPapers.add(publishedPaper);
	}

}

