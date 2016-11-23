package fr.univ_amu.yearbook.bus.exception;

/**
 * <b>ManagerException</b> est la classe qui gère les différentes
 * exceptions liées à la gestion du Manager.
 * 
 * @see Exception
 * 
 * @author Aboubacar Sidy DIALLO
 * @author Inoussa ZONGO
 * @version 1.0
 *
 */
public class ManagerException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur par défaut de la classe ManagerException.
	 */
	public ManagerException() {
		super();
	}

	/**
	 * Constructeur de la classe ManagerException.
	 * 
	 * @param message Le message à retourner.
	 */
	public ManagerException(String message) {
		super(message);
	}

	/**
	 * Constructeur de la classe ManagerException.
	 * 
	 * @param cause La cause de l'exception.
	 */
	public ManagerException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructeur de la classe ManagerException.
	 * 
	 * @param message Le message à retourner.
	 * @param cause La cause de l'exception.
	 */
	public ManagerException(String message, Throwable cause) {
		super(message, cause);
	}
}