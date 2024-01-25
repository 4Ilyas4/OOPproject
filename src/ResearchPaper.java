
import java.io.Serializable;
import java.util.UUID;
/**
 * A class that represents a scientific article in the system.
 */
public class ResearchPaper implements Serializable  { 
	private static final long serialVersionUID = 17L;
	
	// count of citations
    private int citations;
    // title of researchpaper
    private String title;
    // authors of research paper
    private String authors;
    // journal that published this paper
    private String journal;
    // count of pages
    private int pages;
    // date of publickation
    private String date;
    /**
     * A constructor for creating a scientific article with specified parameters.
     *
     * @param title   Article Title.
     * @param authors Authors of the article.
     * @param journal Journal of article publication.
     * @param pages   Number of pages in the article.
     * @param date    Publication date of article
     */
    public ResearchPaper(String title, String authors, String journal, int pages, String date) {
    	this.title = title;
    	this.authors = authors;
    	this.journal = journal;
    	this.pages = pages;
    	this.date = date;
    	this.citations = 0;
    }
    /**
     * Generates a unique identifier (DOI) for an article. DOI (Digital Object Identifier) is a unique identifier used to permanently identify and reference a scientific article. provides a reliable reference for citation.
     *
     * @return DOI significance e4f72eaf-ff7e-4b2e-93e7-61d1748e062e example.
     */
    private String generateDOI() {
        String uniqueID = UUID.randomUUID().toString();
        return "DOI:" + uniqueID;
    }
    /**
     * Generates a key for BibTeX based on authors and publication date. For example, authors Ilyas Mansurov and Aral Askarov published a paper in 2018. bibtex key will be IMAA18
     *
     * @return Key for BibTeX.
     */
    private String generateBibtexKey() {
        // Генерация ключа для BibTeX
        String[] authorNames = authors.split(" ");
        StringBuilder key = new StringBuilder();
        for (String name : authorNames) {
            if (!name.isEmpty()) {
                key.append(name.charAt(0));
            }
        }
        key.append(date.substring(date.length() - 2)); // Добавляем последние две цифры года публикации
        return key.toString().toLowerCase(); // Возвращаем в нижнем регистре для унификации ключа
    }
    
    /**
     * Returns the article data in the specified format.
     *
     * @param f Citation format (PLAIN_TEXT or BIBTEX).
     * @return A string representing the citation in the specified format.
     */
    public String getCitation(Format f) {
    	if (f == Format.PLAIN_TEXT) {
    		return String.format("%s. %s. %s, %d (%s). DOI: %s.", authors, title, journal, pages, date, generateDOI());
    	} else if (f == Format.BIBTEX) {
    		return String.format("@article{%s,\n  title = {%s},\n  author = {%s},\n  journal = {%s},\n  pages = {%d},\n  year = {%s},\n}", generateBibtexKey(), title, authors, journal, pages, date);
    	} else {
    		return "Unknown format";
    	}
    }
    /**
     * Returns the number of citations for the article.
     *
     * @return Number of citations.
     */
    public int getCitations() {
    	return citations;
    }
    
    /**
     * Increases the number of citations for an article by one.
     */
    public void addCitations() {
    	citations++;
    }
    /**
     * Returns the publication date of the article.
     *
     * @return Number of pages.
     */
    public String getDate() {
    	return date;
    }
    /**
     * Returns the number of pages in the article.
     *
     * @return Number of pages.
     */
    public int getPages() {
        return pages;
    }
    /**
     * Sets the number of pages in the article.
     *
     * @param pages Number of pages.
     */
    public void setPages(int pages) {
        this.pages = pages;
    }
    /**
     * Returns the title of the article.
     *
     * @return Article Title.
     */
	public String getTitle() {
		return title;
	}
	/**
     * Sets the title of the article.
     *
     * @param title article title.
     */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
     * Returns a list of the authors of the article.
     *
     * @return List of authors.
     */
	public String getAuthors() {
		return authors;
	}
	/**
     * Sets the list of authors of the article.
     *
     * @param authors List of authors.
     */
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	/**
     * Returns the journal that published the article
     *
     * @return Journal of Publication.
     */
	public String getJournal() {
		return journal;
	}
	/**
     * Sets the publication log of the article.
     *
     * @param journal Journal of Publication.
     */
	public void setJournal(String journal) {
		this.journal = journal;
	}
	/**
     * Sets the publication date of the article.
     *
     * @param date Publication Date.
     */
	public void setDate(String date) {
		this.date = date;
	}
}

