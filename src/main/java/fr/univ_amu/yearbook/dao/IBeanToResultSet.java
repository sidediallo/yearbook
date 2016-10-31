package fr.univ_amu.yearbook.dao;

import java.sql.ResultSet;

import fr.univ_amu.yearbook.dao.exception.DAOException;

/**
 * <b>IBeanToResultSet</b> est l'interface qui permet de créer
 * à partir d'un bean un ResultSet.
 *
 * @see DAOException
 *  
 * @author Aboubacar Sidy DIALLO & Inoussa ZONGO
 * @version 1.0
 *
 */
public interface IBeanToResultSet<T> {
	
	/**
	 * 
	 * @param bean Le bean.
	 * @param query La requête.
	 * @param parametersList La liste des paramètres.
	 * @return Le ResultSet correspondant ou null.
	 * @throws DAOException Si une exception est levée.
	 */
	ResultSet toResultSet(T bean, String query, String[] parametersList) throws DAOException;
}
