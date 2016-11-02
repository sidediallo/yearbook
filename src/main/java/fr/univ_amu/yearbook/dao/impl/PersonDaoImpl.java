package fr.univ_amu.yearbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.univ_amu.yearbook.bean.Person;
import fr.univ_amu.yearbook.dao.IPersonDao;
import fr.univ_amu.yearbook.dao.exception.DatabaseManagerException;
import fr.univ_amu.yearbook.dao.exception.DAOException;

/**
 * <b>PersonDaoImpl</b> est la classe qui implemente l'interface
 * {@link IPersonDao}.
 * 
 * <p>
 * Cette classe est caractérisée par :
 * <ul>
 * <li>Un objet qui crée une connection à la base de données.</li>
 * </ul>
 * </p>
 * 
 * @see Person
 * @see DAOException
 * @see IPersonDao
 * @see DatabaseManagerImpl
 * @see DatabaseManagerException
 * @see ResultSetToBeanImpl
 * 
 * @author Aboubacar Sidy DIALLO & Inoussa ZONGO
 * @version 1.0
 *
 */
@Repository("personDaoImpl")
public class PersonDaoImpl implements IPersonDao {
	
	/**
	 * L'objet qui établi la connection avec la base.
	 * 
	 * @see {@link #getDbManager()}
	 * @see {@link #setDbManager(DatabaseManagerImpl)}
	 * @see {@link #init()}
	 * @see {@link #findPerson(long)}
	 * @see {@link #PersonDaoImpl#findAllPersons()}
	 * @see {@link #PersonDaoImpl#removePerson(long)}
	 * 
	 */
	@Autowired
	private DatabaseManagerImpl dbManager;
	
	/**
	 * Le constructeur par défaut de la classe.
	 */
	public PersonDaoImpl() {
		super();
	}
	
	/**
	 * Initialisation de l'objet dbManager.
	 * @throws DatabaseManagerException 
	 */
	@PostConstruct
	public void init() throws DatabaseManagerException {
		dbManager.init();
	}
	
	/**
	 * Recherche et renvoie la personne associée à l'identifiant.
	 * 
	 * @param id L'id de la personne.
	 * @return
	 * 		La personne dont l'indentifiant est rentré en paramètre de la méthode. 
	 * @throws DAOException Si la personne rattachée à l'id n'existe pas.
	 * @throws DatabaseManagerException Si la connection n'est pas établie.
	 */
	@Override
	public Person findPerson(long id) throws DAOException, DatabaseManagerException {
		
		try (Connection conn = dbManager.newConnection()) {
			ResultSetToBeanImpl<Person> mapper = new ResultSetToBeanImpl<Person>(Person.class);
			String query = "SELECT * FROM YEARBOOK_Person WHERE id = ?";			
			PreparedStatement st = conn.prepareStatement(query);
			
			st.setLong(1, id);
			ResultSet rs = st.executeQuery();
			
			if (rs.next())
				return mapper.toBean(rs);
		} catch (SQLException e){
			throw new DAOException(e.getMessage());
		}
		return null;
	}

	/**
	 * Retourne la liste des personnes existantes.
	 * 
	 * @return
	 * 		La liste de personnes.
	 * @throws DAOException S'il n'y a aucune personne.
	 * @throws DatabaseManagerException Si la connection n'est pas établie.
	 */
	@Override
	public Collection<Person> findAllPersons() throws DAOException, DatabaseManagerException {
		
		try (Connection conn = dbManager.newConnection()) {
			String query = "SELECT * FROM YEARBOOK_Person";
			Collection<Person> people = new LinkedList<Person>();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				people.add(findPerson(rs.getLong(1)));
			}
			return people;
		} catch (SQLException e){
			throw new DAOException(e.getCause());
		}
	}

	/**
	 * Création ou mise à jour d'une personne.
	 * 
	 * @param p La personne.
	 * @throws DAOException Si la personne qu'on souhaite rajouter existe déjà
	 * 		   ou si la personne qu'on souhaite mettre à jour n'existe pas.
	 * @see Person
	 */
	@Override
	public void saveOrUpdatePerson(Person p) throws DAOException {
		BeanToResultSetImpl<Person> mapp = new BeanToResultSetImpl<Person>();
		
		String query = "";
		String[] parametersList = {""};
		
		mapp.insertOrUpdate(p, query, parametersList);
	}

	/**
	 * Suppression de la personne associé à l'identifiant id.
	 * 
	 * @param id L'id correspondant à la personne.
	 */
	@Override
	public void removePerson(long id) {
		String query = "DELETE FROM YEARBOOK_Person WHERE id = ?";
		PreparedStatement st;
		
		try (Connection conn = dbManager.newConnection()) {
			st = conn.prepareStatement(query);
			st.setLong(1, id);
			st.executeUpdate();
		} catch (SQLException | DatabaseManagerException e){
			try {
				throw new DAOException("error : no row deleted");
			} catch (DAOException e1) {
				e1.getMessage();
			}
		}
	}

	/**
	 * Suppression de la personne.
	 * 
	 * @param p La personne à supprimer
	 * @see Person
	 */
	@Override
	public void removePerson(Person p) {
		removePerson(p.getId());
	}

	/**
	 * Suppression de toutes les personnes de la base.
	 */
	@Override
	public void removeAllPersons() {
		String query = "SELECT * FROM YEARBOOK_Person";
		Statement st;
		ResultSet rs;
		
		try (Connection conn = dbManager.newConnection()) {
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				rs.deleteRow();
			}
			
		} catch (SQLException | DatabaseManagerException e){
			try {
				throw new DAOException("error : no row deleted");
			} catch (DAOException e1) {
				e1.getMessage();
			}
		}
	}
	
	/**
	 * Calcul le nombre de personnes de la base.
	 * 
	 * @return Le nombre de personne.
	 * @throws DAOException Si exception levé avant.
	 * @throws DatabaseManagerException Si la connection n'est pas établie.
	 */
	@Override
	public int countPersons() throws DAOException, DatabaseManagerException {
		return findAllPersons().size();
	}

	/**
	 * Retourne le DataBaseManager.
	 * 
	 * @return Le DataBaseManager.
	 */
	public DatabaseManagerImpl getDbManager() {
		return dbManager;
	}

	/**
	 * Mise à jour du DataBaseManager.
	 * 
	 * @param dbManager Le nouveau DataBaseManager.
	 */
	public void setDbManager(DatabaseManagerImpl dbManager) {
		this.dbManager = dbManager;
	}
}