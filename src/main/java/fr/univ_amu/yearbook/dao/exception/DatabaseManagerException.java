package fr.univ_amu.yearbook.dao.exception;

public class DatabaseManagerException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseManagerException(String message){
		super(message);
	}
	
	public DatabaseManagerException(Throwable cause){
		super(cause);
	}
	
	public DatabaseManagerException(String message, Throwable cause){
		super(message,cause);
	}
}
