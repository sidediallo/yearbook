package fr.univ_amu.yearbook.dao;

import java.sql.Connection;

import fr.univ_amu.yearbook.dao.exception.DatabaseManagerException;

public interface IDatabaseManager {
	public Connection newConnection() throws DatabaseManagerException;
	public void closeConneection(Connection conn);
}
