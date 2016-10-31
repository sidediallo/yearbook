package fr.univ_amu.yearbook.dao.impl;

import java.sql.ResultSet;

import fr.univ_amu.yearbook.dao.IBeanToResultSet;
import fr.univ_amu.yearbook.dao.exception.DAOException;

/**
 * <b>BeanToResultSet</b> implémente l'interface
 * {@link IBeanToResultSet#toResultSet(Object, String, String[])}
 *
 * @see DAOException
 *  
 * @author Aboubacar Sidy DIALLO & Inoussa ZONGO
 * @version 1.0
 *
 */
public class BeanToResultSet<T> implements IBeanToResultSet<T> {
	
	/**
	 * 
	 * @param bean Le bean.
	 * @param query La requête.
	 * @param parametersList La liste des paramètres.
	 * @return Le ResultSet correspondant ou null.
	 * @throws DAOException Si une exception est levée.
	 */
	public int toResultSet(T bean, String query, String[] parametersList) throws DAOException {
		return 1;
	}
}
