package fr.univ_amu.yearbook.dao;

import java.sql.ResultSet;

import fr.univ_amu.yearbook.dao.exception.DAOException;

/**
 * The IResultsetToBean interfaces defines method to convert a result to a java bean.
 * 
 * @author Aboubacar Sidy DIALLO
 * @author Inoussa ZONGO
 * @version 1.0
 */
public interface IResultSetToBean<T>{
	/**
	 * Method that convert a resultSet to a bean.
	 * 
	 * @param rs ResultSet to convert to bean.
	 * @return the bean result of the conversion of the resultSet.
	 * @throws DAOException
	 */
	public T toBean(ResultSet rs) throws DAOException;
}
