import java.io.Serializable;
/**
 *  class that creates log file. 
 */
public class LogFile implements Serializable {
	private static final long serialVersionUID = 10L;
	// Поля класса. лог имеет айди, время и действие.
	String iD;
	String date;
	String action;
	
	/**
     * logfile is created by id, password and typeuser.
     *
     * @param ID    id of logfiler.
     * @param date  date of log.
     * @param action description of logfile
     */
	public LogFile(String ID,String date, String action) {
		this.iD = ID;
		this.date = date;
		this.action = action;
	}
	/**
     * Returns the log info
     *
     * @return log info. id, data and acion
     */
	public String getLog() {
		return "ID: " + iD + " Date: " + date + " _ " + " action: " + action;
	}
}

