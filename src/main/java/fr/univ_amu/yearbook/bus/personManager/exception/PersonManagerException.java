package main.java.fr.univ_amu.yearbook.bus.personManager.exception;

/**
 * <b>PersonManagerException</b> est la classe qui gère les différentes
 * exceptions liées à la gestion du PersonManager.
 * 
 * @see Exception
 * 
 * @author Aboubacar Sidy DIALLO & Inoussa ZONGO
 * @version 1.0
 *
 */
public class PersonManagerException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur par défaut de la classe PersonManagerException.
	 */
	public PersonManagerException() {
		super();
	}

	/**
	 * Constructeur de la classe PersonManagerException.
	 * 
	 * @param message Le message à retourner.
	 */
	public PersonManagerException(String message) {
		super(message);
	}

	/**
	 * Constructeur de la classe PersonManagerException.
	 * 
	 * @param cause La cause de l'exception.
	 */
	public PersonManagerException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructeur de la classe PersonManagerException.
	 * 
	 * @param message Le message à retourner.
	 * @param cause La cause de l'exception.
	 */
	public PersonManagerException(String message, Throwable cause) {
		super(message, cause);
	}
}