package fr.univ_amu.yearbook.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.univ_amu.yearbook.dao.IBeanToResultSet;
import fr.univ_amu.yearbook.dao.exception.DAOException;
import fr.univ_amu.yearbook.dao.exception.DatabaseManagerException;

/**
 * <b>BeanToResultSetImpl</b> implémente l'interface
 * {@link IBeanToResultSet#toResultSet(Object, String, String[])}.
 * Elle permet la màj de la table correspondante au bean dans la bdd.
 *
 * @see DAOException
 *  
 * @author Aboubacar Sidy DIALLO & Inoussa ZONGO
 * @version 1.0
 *
 */
public class BeanToResultSetImpl<T> implements IBeanToResultSet<T> {
	
	/**
	 * Gère la connexion à la base de données.
	 * 
	 * @see BeanToResultSetImpl<T>#BeanToResultSetImpl()
	 * @see BeanToResultSetImpl<T>#insertOrUpdate(T, String, String[])
	 */
	DatabaseManagerImpl dbManager;
	
	/**
	 * Constructeur par défaut de la classe
	 */
	public BeanToResultSetImpl() {
		dbManager = new DatabaseManagerImpl();
		try {
			dbManager.init();
		} catch (DatabaseManagerException e) {
			e.getMessage();
		}
	}
	
	/**
	 * Fait la màj de la table correspondante au bean dans la bdd.
	 * 
	 * @param bean Le bean.
	 * @param query La requête.
	 * @param parametersList La liste des paramètres.
	 * @return Le nombre de lignes modifiées ou -1.
	 * @throws DAOException Si une exception est levée.
	 */
	public int insertOrUpdate(T bean, String query, String[] parametersList) throws DAOException {
		try (Connection c = dbManager.newConnection()){
			PreparedStatement st = c.prepareStatement(query);
			Method[] methods = bean.getClass().getMethods();
			
			for (int i = 0; i < parametersList.length; i++) {
				for (Method m : methods) {
					if (isGetter(m) && m.getName().compareToIgnoreCase("get" + parametersList[i]) == 0) {
						st.setObject(i+1, m.invoke(bean));
					}
				}
			}
			return st.executeUpdate();
		} catch(SQLException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | DatabaseManagerException e) {
			e.getMessage();
		}
		return -1;
	}
	
	/**
	 * Vérifie qu'une méthode est un getter.
	 * 
	 * @param method La méthode.
	 * @return True si c'est un getter et false sinon.
	 */
	public boolean isGetter(Method method) {
		return (method.getName().startsWith("get"))
				&& (method.getParameterTypes().length == 0)
				&& (!Void.class.equals(method.getReturnType()));
	}
}