package fr.univ_amu.yearbook.dao;

import java.sql.Connection;

import fr.univ_amu.yearbook.dao.exception.ConnectionManagerException;

public interface IConnectionManager {
	public Connection newConnection() throws ConnectionManagerException;
	public void closeConneection(Connection conn) throws ConnectionManagerException;
}
