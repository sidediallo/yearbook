package fr.univ_amu.yearbook.dao.exception;

public class ConnectionManagerException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConnectionManagerException(String message){
		super(message);
	}
	
	public ConnectionManagerException(Throwable cause){
		super(cause);
	}
	
	public ConnectionManagerException(String message, Throwable cause){
		super(message,cause);
	}
}
