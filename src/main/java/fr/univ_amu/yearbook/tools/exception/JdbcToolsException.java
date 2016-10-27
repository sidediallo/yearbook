package main.java.fr.univ_amu.yearbook.tools.exception;

/**
 * <b>JdbcToolsException</b> est la classe qui gère les différentes
 * exceptions liées à la gestion des connections à une base de données.
 * 
 * @see Exception
 * 
 * @author Aboubacar Sidy DIALLO & Inoussa ZONGO
 * @version 1.0
 *
 */
public class JdbcToolsException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur par défaut de la classe JdbcToolsException.
	 */
	public JdbcToolsException() {
		super();
	}

	/**
	 * Constructeur de la classe JdbcToolsException.
	 * 
	 * @param message Le message à retourner.
	 */
	public JdbcToolsException(String message) {
		super(message);
	}

	/**
	 * Constructeur de la classe JdbcToolsException.
	 * 
	 * @param cause La cause de l'exception.
	 */
	public JdbcToolsException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructeur de la classe JdbcToolsException.
	 * 
	 * @param message Le message à retourner.
	 * @param cause La cause de l'exception.
	 */
	public JdbcToolsException(String message, Throwable cause) {
		super(message, cause);
	}
}