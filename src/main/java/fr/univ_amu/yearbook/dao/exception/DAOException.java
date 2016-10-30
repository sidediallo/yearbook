package fr.univ_amu.yearbook.dao.exception;

/**
 * DAOException class is designed to represent and encapsulate the exceptions from the 
 * differents operations to datasources.
 * 
 * @author Utilisateur
 *@version 1.0
 */
public class DAOException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor.<br>
	 * Creates a new DAOException by indicating its cause.
	 * @see java.lang.Throwable
	 * @param the source of the exception
	 */
	public DAOException(Throwable cause){
		super(cause);
	}
	
	/**
	 * Constructor.<br>
	 * Creates a new DAOException by giving a message for the excepion.
	 * @param message
	 */
	public DAOException(String message){
		super(message);
	}
	
	/**
	 * Constructor.<br>
	 * Creates a new DAOException by giving a message and the cause of the exception.
	 * @see java.lang.Throwable
	 * @param message
	 * @param cause
	 */
	public DAOException(String message, Throwable cause){
		super(message,cause);
	}
}
