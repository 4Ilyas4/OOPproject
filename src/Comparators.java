import java.util.Comparator;
/**
 * The Comparators class provides static methods to create comparators for sorting ResearchPaper objects.
 */
public class Comparators {
    // Sort by article length
	/**
     * Creates a comparator to sort ResearchPaper objects by article length 
     *
     * @return Comparator for sorting by article length.
     */
    public static Comparator<ResearchPaper> sortByArticleLength() {
   	 	return Comparator.comparingInt(ResearchPaper::getPages);
    } 
    // Sort by date
    /**
     * Creates a comparator to sort ResearchPaper objects by  date.
     *
     * @return Comparator for sorting by date.
     */
    public static Comparator<ResearchPaper> sortByDate() {
    	return Comparator.comparing(ResearchPaper::getDate);
    }
     // Sort by citations
    /**
     * Creates a comparator to sort ResearchPaper objects by citations.
     *
     * @return Comparator for sorting by citations.
     */
    public static Comparator<ResearchPaper> sortByCitations() {
    	return Comparator.comparingInt(ResearchPaper::getCitations);
    }
}

