package fr.univ_amu.yearbook.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import fr.univ_amu.yearbook.dao.IResultSetToBean;
import fr.univ_amu.yearbook.dao.exception.DAOException;

/**
 * ResultSetToBeanImpl class gives an implementation 
 * for {@link fr.univ_amu.yearbook.dao.IResultSetToBean IResultSetToBean} interface.
 * 
 * @author Aboubacar Sidy DIALLO
 * @author Inoussa ZONGO
 *
 * @param <T> parameter type that represents the type of the bean.
 */
public class ResultSetToBeanImpl<T> implements IResultSetToBean<T>{
	Class<T> clazz;
	/**
	 * Constructor.
	 * @param clazz the class of the bean.
	 */
	public ResultSetToBeanImpl(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	/**
	 * @see IResultSetToBean#toBean(ResultSet)
	 */
	@Override
	public T toBean(ResultSet resultSet) throws DAOException {
		try {
			T bean = clazz.newInstance();
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columCount = metaData.getColumnCount();
			
			Method[] methods = clazz.getDeclaredMethods();
			for(int i=0;i<columCount;i++){
				String columnName = metaData.getColumnName(i+1);
				Method method = searchMethod(methods, columnName);
				if(method != null) method.invoke(bean, resultSet.getObject(i+1));
			}
			return bean;
			
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SQLException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @param methods
	 * @param columnName
	 * @return
	 */
	private Method searchMethod(Method[] methods,String columnName){
		for(Method method : methods){
			String potentialMethod = "set"+columnName;
			if(method.getName().toLowerCase().equals(potentialMethod.toLowerCase())){
				return method;
			}
		}
		return null;
	}
}
