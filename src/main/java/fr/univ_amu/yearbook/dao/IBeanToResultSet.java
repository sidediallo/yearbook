package fr.univ_amu.yearbook.dao;

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
	 * Fait la màj de la table correspondante au bean dans la bdd.
	 * 
	 * @param bean Le bean.
	 * @param query La requête.
	 * @param parametersList La liste des paramètres.
	 * @return Le nombre de lignes modifiées ou -1.
	 * @throws DAOException Si une exception est levée.
	 */
	public int insertOrUpdate(T bean, String query, String[] parametersList) throws DAOException;
}
