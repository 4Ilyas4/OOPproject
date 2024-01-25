import java.io.Serializable;
import java.lang.*;
/**
 * An enumeration representing the format for citation output.
 */
public enum Format implements Serializable  {
	/**
     * Plain text format for citation output.
     */
    PLAIN_TEXT,

    /**
     * BibTeX format for citation output.
     */
    BIBTEX;
}

