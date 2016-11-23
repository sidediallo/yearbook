package fr.univ_amu.yearbook.dao;

import java.sql.Connection;

import fr.univ_amu.yearbook.dao.exception.DatabaseManagerException;

/**
 * The IDatabaseManager interface provide operations 
 * to get connections to database and close them properly.
 * 
 * @author Aboubacar Sidy DIALLO
 * @author Inoussa ZONGO
 * @version 1.0
 *
 */
public interface IDatabaseManager {
	/**
	 * gives a conection to a database.
	 * 
	 * @return a connection to a database
	 * @throws DatabaseManagerException
	 */
	public Connection newConnection() throws DatabaseManagerException;
	
	/**
	 * closes properly a connection.
	 * 
	 * @param conn the connection to close.
	 */
	public void closeConneection(Connection conn);
}
