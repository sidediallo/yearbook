package fr.univ_amu.yearbook.dao.exception;

/**
 * <b>PersonDaoException</b> est la classe qui gère les différentes
 * exceptions liées à la gestion du DaoPerson.
 * 
 * @see Exception
 * 
 * @author Aboubacar Sidy DIALLO & Inoussa ZONGO
 * @version 1.0
 *
 */
public class PersonDaoException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur par défaut de la classe PersonDaoException.
	 */
	public PersonDaoException() {
		super();
	}

	/**
	 * Constructeur de la classe PersonDaoException.
	 * 
	 * @param message Le message à retourner.
	 */
	public PersonDaoException(String message) {
		super(message);
	}

	/**
	 * Constructeur de la classe PersonDaoException.
	 * 
	 * @param cause La cause de l'exception.
	 */
	public PersonDaoException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructeur de la classe PersonDaoException.
	 * 
	 * @param message Le message à retourner.
	 * @param cause La cause de l'exception.
	 */
	public PersonDaoException(String message, Throwable cause) {
		super(message, cause);
	}
}