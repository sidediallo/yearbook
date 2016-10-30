package fr.univ_amu.yearbook.dao.exception;

/**
 * DAOException class is designed to represent and encapsulate the exceptions from the 
 * differents operations to datasources.
 * 
 * @author ZONGO
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
	 * @param source the source of the exception.
	 */
	public DAOException(Throwable cause){
		super(cause);
	}
	
	/**
	 * Constructor.<br>
	 * Creates a new DAOException by giving a message for the excepion.
	 * @param message the message for this this exception.
	 */
	public DAOException(String message){
		super(message);
	}
	
	/**
	 * Constructor.<br>
	 * Creates a new DAOException by giving a message and the cause of the exception.
	 * @param message the message fot this exception.
	 * @param cause the cause of of the exception.
	 */
	public DAOException(String message, Throwable cause){
		super(message,cause);
	}
}
