package fr.univ_amu.yearbook.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import fr.univ_amu.yearbook.dao.IDatabaseManager;
import fr.univ_amu.yearbook.dao.exception.DatabaseManagerException;


/**
 * The DatabaseManagerImpl class in a implementation of the interface {@link IDatabaseManager}.<br>
 * This implementation use a connection pool to provides connections to a database.<br>
 * The connection pool and database properties are defined in a configuration file.
 * 
 * @author Utilisateur
 *@version 1.0
 *@see IDatabaseManager
 */
@Service("connectionManager")
@Primary
public class DatabaseManagerImpl implements IDatabaseManager {
	final String URL_PROPERTY = "url";
	final String USER_PROPERTY = "user";
	final String PASSWORD_PROPERTY = "password";
	final String MIN_CONNECTION_SIZE_PROPERTY = "minConnectionSize";
	final String MAX_CONNECTION_SIZE_PROPERTY = "maxConnectionSize";
	
	
	BasicDataSource ds;
	
	/*
	 * The database and connection pool configuration file.
	 * The default configuration file is dao.properties.
	 */
	@Value("dao.properties")
	private String dbConfFile;
	
	public DatabaseManagerImpl() {
		
	}
	
	@PostConstruct
	public void init() throws DatabaseManagerException{
		String url;
		String user;
		String password;
		Integer minConnectionSize;
		Integer maxConnectionSize;
		
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream file = classLoader.getResourceAsStream(dbConfFile);
		
		if(file == null){
			throw new DatabaseManagerException("file "+dbConfFile+" is missing");
		}
		
		try {
			properties.load(file);
			url = properties.getProperty(URL_PROPERTY);
			user = properties.getProperty(USER_PROPERTY);
			password = properties.getProperty(PASSWORD_PROPERTY);
			minConnectionSize = Integer.parseInt(properties.getProperty(MIN_CONNECTION_SIZE_PROPERTY));
			maxConnectionSize = Integer.parseInt(properties.getProperty(MAX_CONNECTION_SIZE_PROPERTY));
		} catch (IOException | NumberFormatException e) {
			throw new DatabaseManagerException("Error when reading file"+dbConfFile,e);
		}
		

		
		ds = new BasicDataSource();
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(password);
		ds.setInitialSize(minConnectionSize);
		ds.setMaxActive(maxConnectionSize);
	}

	@PreDestroy
	public void close() throws DatabaseManagerException{
		try {
			ds.close();
		} catch (SQLException e) {
			throw new DatabaseManagerException(e);
		}
	}
	
	/**
	 * @see IDatabaseManager#newConnection()
	 */
	@Override
	public Connection newConnection() throws DatabaseManagerException {
		try {
			Connection connection = ds.getConnection();
			return connection;
		} catch (SQLException e) {
			throw new DatabaseManagerException("Unable to create connection",e);
		}
	}

	/**
	 * @see IDatabaseManager#closeConneection(Connection)
	 */
	@Override
	public void closeConneection(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				
			}
		}
	}

	/**
	 * 
	 * @return the number of active connections in the pool.
	 */
	public int getNumActiveConnection(){
		return ds.getNumActive();
	}
	
	/**
	 * 
	 * @return the number of idle connection in the pool.
	 */
	public int getNumIdleConnection(){
		return ds.getNumIdle();
	}
	
	/**
	 * 
	 * @return the location of database and connection pool configuration file.
	 */
	public String getDbConfFile() {
		return dbConfFile;
	}

	/**
	 * 
	 * @param dbConfFile the database and connection pool configuration file location.
	 */
	public void setDbConfFile(String dbConfFile) {
		this.dbConfFile = dbConfFile;
	}
	
}
