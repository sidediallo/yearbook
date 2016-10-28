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
import fr.univ_amu.yearbook.dao.exception.ConnectionManagerException;


@Service("connectionManager")
@Primary
public class DatabaseManagerImpl implements IDatabaseManager {
	final String URL_PROPERTY = "url";
	final String USER_PROPERTY = "user";
	final String PASSWORD_PROPERTY = "password";
	final String MIN_CONNECTION_SIZE_PROPERTY = "minConnectionSize";
	final String MAX_CONNECTION_SIZE_PROPERTY = "maxConnectionSize";
	
	private String url;
	private String user;
	private String password;
	private Integer minConnectionSize;
	private Integer maxConnectionSize;
	
	BasicDataSource ds;
	
	@Value("dao.properties")
	private String dbConfFile;
	
	public DatabaseManagerImpl() {
		
	}
	
	@PostConstruct
	public void init() throws ConnectionManagerException{
		
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream file = classLoader.getResourceAsStream(dbConfFile);
		
		if(file == null){
			throw new ConnectionManagerException("file "+dbConfFile+" is missing");
		}
		
		try {
			properties.load(file);
			this.url = properties.getProperty(URL_PROPERTY);
			this.user = properties.getProperty(USER_PROPERTY);
			this.password = properties.getProperty(PASSWORD_PROPERTY);
			this.minConnectionSize = Integer.parseInt(properties.getProperty(MIN_CONNECTION_SIZE_PROPERTY));
			this.maxConnectionSize = Integer.parseInt(properties.getProperty(MAX_CONNECTION_SIZE_PROPERTY));
		} catch (IOException | NumberFormatException e) {
			throw new ConnectionManagerException("Error when reading file"+dbConfFile,e);
		}
		

		
		ds = new BasicDataSource();
		ds.setUrl(this.url);
		ds.setUsername(this.user);
		ds.setPassword(this.password);
		ds.setInitialSize(this.minConnectionSize);
		ds.setMaxActive(this.maxConnectionSize);
	}

	@PreDestroy
	public void close() throws ConnectionManagerException{
		try {
			ds.close();
		} catch (SQLException e) {
			throw new ConnectionManagerException(e);
		}
	}
	
	@Override
	public Connection newConnection() throws ConnectionManagerException {
		try {
			Connection connection = ds.getConnection();
			return connection;
		} catch (SQLException e) {
			throw new ConnectionManagerException("Unable to create connection",e);
		}
	}

	@Override
	public void closeConneection(Connection conn) throws ConnectionManagerException {
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				throw new ConnectionManagerException("Error when closing connection",e);
			}
		}
	}

	public int getNumActiveConnection(){
		return ds.getNumActive();
	}
	
	public int getNumIdleConnection(){
		return ds.getNumIdle();
	}
	
	public String getDbConfFile() {
		return dbConfFile;
	}

	public void setDbConfFile(String dbConfFile) {
		this.dbConfFile = dbConfFile;
	}
	
}
