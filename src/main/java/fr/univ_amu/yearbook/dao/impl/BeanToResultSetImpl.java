package fr.univ_amu.yearbook.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.univ_amu.yearbook.dao.IBeanToResultSet;
import fr.univ_amu.yearbook.dao.IDatabaseManager;
import fr.univ_amu.yearbook.dao.exception.DAOException;
import fr.univ_amu.yearbook.dao.exception.DatabaseManagerException;

/**
 * <b>BeanToResultSetImpl</b> implémente l'interface
 * {@link IBeanToResultSet}.
 * Elle permet la màj de la table correspondante au bean dans la bdd.
 *
 * @see DAOException
 * @see IDatabaseManager
 *  
 * @author Aboubacar Sidy DIALLO
 * @author Inoussa ZONGO
 * @version 1.0
 *
 */
@Repository("beanToResultSetImpl")
public class BeanToResultSetImpl<T> implements IBeanToResultSet<T> {
	
	/**
	 * Gère la connexion à la base de données.
	 * 
	 * @see #getDbManager()
	 * @see #setDbManager(IDatabaseManager)
	 * @see #insertOrUpdate(T, String, String[])
	 */
	@Autowired
	private IDatabaseManager dbManager;
	
	/**
	 * Constructeur par défaut de la classe
	 */
	public BeanToResultSetImpl() {
		super();
	}
	
	/**
	 * Fait la màj de la table correspondante au bean dans la bdd.
	 * 
	 * @param bean Le bean.
	 * @param query La requête.
	 * @param columnNameList La liste des paramètres.
	 * @return Le nombre de lignes modifiées.
	 * @throws DAOException Si une exception est levée.
	 */
	@Override
	public int insertOrUpdate(T bean, String query, String[] columnNameList) throws DAOException {
		try (Connection c = dbManager.newConnection()){
			PreparedStatement st = c.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			Method[] methods = bean.getClass().getMethods();
			
			for (int i = 0; i < columnNameList.length; i++) {
				for (Method m : methods) {
					if (isGetter(m) && m.getName().compareToIgnoreCase("get" + columnNameList[i]) == 0) {
						st.setObject(i+1, m.invoke(bean));
					}
				}
			}
			int nbRow = st.executeUpdate();
			
			ResultSet keyRs = st.getGeneratedKeys();
			if(keyRs.next()){
				bean.getClass().getMethod("setId",Long.class).invoke(bean, keyRs.getObject(1));
			}
			return nbRow;
		} catch(SQLException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | DatabaseManagerException | NoSuchMethodException | SecurityException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}
	
	/**
	 * Vérifie qu'une méthode est un getter.
	 * 
	 * @param method La méthode.
	 * @return True si c'est un getter et false sinon.
	 */
	private boolean isGetter(Method method) {
		return (method.getName().startsWith("get"))
				&& (method.getParameterTypes().length == 0)
				&& (!Void.class.equals(method.getReturnType()));
	}
	
	/**
	 * Retourne une instance de l'interface IDataBaseManager
	 * 
	 * @return L'instance de IDataBaseManager.
	 */
	public IDatabaseManager getDbManager() {
		return dbManager;
	}

	/**
	 * Mise à jour de l'instance de IDataBaseManager.
	 * 
	 * @param dbManager La nouvelle instance de IDataBaseManager.
	 */
	public void setDbManager(IDatabaseManager dbManager) {
		this.dbManager = dbManager;
	}
}