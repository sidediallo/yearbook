package main.java.fr.univ_amu.yearbook.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import main.java.fr.univ_amu.yearbook.tools.exception.JdbcToolsException;

/**
 * <b>JdbcTools</b> est la classe qui permet d'initialiser et de créer
 * une connection à une base de données à travers JDBC.
 * 
 * <p>
 * JdbcTools est caractérisée par :
 * <ul>
 * <li>Un initialisateur des paramètres de connection.</li>
 * </ul>
 * </p>
 * 
 * @see Tools
 * @see JdbcToolsException
 * 
 * @author Aboubacar Sidy DIALLO & Inoussa ZONGO
 * @version 1.0
 *
 */
public class JdbcTools {
	
	/**
	 * L'objet qui gère le paramétrage des données de connection.
	 */
	private Tools tools;
	
	/**
	 * Constructeur par défaut de la classe JdbcTools.
	 */
	public JdbcTools() {
		super();
	}
	
	/**
	 * Initialise les paramètres de connection.
	 */
	public void init() {
		tools = new Tools();
		tools.init();
	}
	
	/**
	 * Crée et retourne une connection à une base de données.
	 * 
	 * @return La connection.
	 * @throws JdbcToolsException Si la connection échoue.
	 */
	public Connection newConnection() throws JdbcToolsException {
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(tools.getUrl(), tools.getUser(), tools.getPwd());
		} catch(SQLException e) {
			throw new JdbcToolsException("error : connection not found!");
		}
		return connection;
	}
	
	/**
	 * Ferme la connection à une base de données.
	 * 
	 * @param c La connection en cours.
	 * @throws JdbcToolsException S'il n'y avais pas de connection.
	 */
	public void quietClose(Connection c) throws JdbcToolsException {
		try {
			if (c != null && !c.isClosed())
				c.close();
		} catch (SQLException e) {
			throw new JdbcToolsException("error : no connection!");
		}
	}
}