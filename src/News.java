import java.io.Serializable;
import java.lang.*;
import java.util.List;

/**
 * Represents any news  
 */
public class News implements Serializable  { 
	private static final long serialVersionUID = 13L;
	// header
    private String topic;
    // pinned or not
    public boolean pinned;
    // researchpapers
    private List<ResearchPaper> papers;
    // news like Kbtu student win 1 place will publication by description
    private String description;
    /**
     * Constructor. News with specified topic, description, list of research papers, and pinned status.
     *
     * @param topic       The topic of the news article.
     * @param description The description of the news article.
     * @param papers      The list of research papers associated with the news.
     * @param pinned      The pinned status of the news article.
     */
    public News(String topic,String description,List<ResearchPaper> papers,boolean pinned){
    	this.topic = topic;
    	this.description = description;
    	this.papers = papers;
    	this.pinned = pinned;
    }
    /**
     * View the details of the news article.
     *
     * @return A string representation of the news article.
     */
    public String view() {
		return " Topic: " + topic + " description " + description;
    }
    /**
     * Set the topic of the news article.
     *
     * @param topic The new topic for the news article.
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }
    /**
     * Set the list of research papers associated with the news article.
     *
     * @param papers The new list of research papers.
     */
    public void setPapers(List<ResearchPaper> papers) {
        this.papers = papers;
    }
    /**
     * Set the description of the news article.
     *
     * @param description The new description for the news article.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Get the topic of the news article.
     *
     * @return The topic of the news article.
     */
    public String getTopic() {
        return topic;
    }
    /**
     * Get the list of research papers associated with the news article.
     *
     * @return The list of research papers.
     */
    public List<ResearchPaper> getPapers() {
        return papers;
    }
    /**
     * Get the description of the news article.
     *
     * @return The description of the news article.
     */
    public String getDescription() {
        return description;
    } 
}

