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
 * <li>Un mapper qui gère la mise à jour en base à partir d'un bean.</li>
 * </ul>
 * </p>
 * 
 * @see Person
 * @see DAOException
 * @see IPersonDao
 * @see DatabaseManagerImpl
 * @see DatabaseManagerException
 * @see BeanToResultSetImpl
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
	 * @see {@link #findAllPersons()}
	 * @see {@link #removePerson(long)}
	 * @see {@link #removeAllPersons()}
	 */
	@Autowired
	private DatabaseManagerImpl dbManager;
	
	/**
	 * Le mapper qui gère la mise à jour en base à partir d'un bean.
	 * 
	 * @see {@link #getMapper()}
	 * @see {@link #setMapp(BeanToResultSetImpl)}
	 * @see {@link #saveOrUpdatePerson(Person)} 
	 */
	@Autowired
	private BeanToResultSetImpl<Person> mapper;
	
	/**
	 * Le constructeur par défaut de la classe.
	 */
	public PersonDaoImpl() {
		super();
	}
	
	/**
	 * Initialisation de l'objet dbManager.
	 * 
	 * @throws DAOException Si la connexion n'a pas lieu.
	 */
	@PostConstruct
	public void init() throws DAOException {
		
		try {
			dbManager.init();
		} catch (DatabaseManagerException e) {
			throw new DAOException(e.getCause());
		}
	}
	
	/**
	 * Recherche et renvoie la personne associée à l'identifiant.
	 * 
	 * @param id L'id de la personne.
	 * @return
	 * 		La personne dont l'indentifiant est rentré en paramètre de la méthode ou null. 
	 * @throws DAOException Si la personne rattachée à l'id n'existe pas ou si la connexion échoue.
	 */
	@Override
	public Person findPerson(long id) throws DAOException {
		
		try (Connection conn = dbManager.newConnection()) {
			ResultSetToBeanImpl<Person> mapper = new ResultSetToBeanImpl<Person>(Person.class);
			String query = "SELECT * FROM YEARBOOK_Person WHERE id = ?";			
			PreparedStatement st = conn.prepareStatement(query);
			Person p = null;
			
			st.setLong(1, id);
			ResultSet rs = st.executeQuery();
			
			if (rs.next())
				p = mapper.toBean(rs);
			dbManager.closeConneection(conn);
			return p;
		} catch (SQLException | DatabaseManagerException e){
			throw new DAOException(e.getCause());
		}
	}

	/**
	 * Retourne la liste des personnes existantes.
	 * 
	 * @return
	 * 		La liste de personnes.
	 * @throws DAOException S'il n'y a aucune personne dans la base ou si la connexion échoue.
	 */
	@Override
	public Collection<Person> findAllPersons() throws DAOException {
		
		try (Connection conn = dbManager.newConnection()) {
			String query = "SELECT * FROM YEARBOOK_Person";
			Collection<Person> people = new LinkedList<Person>();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				people.add(findPerson(rs.getLong(1)));
			}
			dbManager.closeConneection(conn);
			return people;
		} catch (SQLException | DatabaseManagerException e){
			throw new DAOException(e.getCause());
		}
	}

	/**
	 * Création ou mise à jour des données d'une personne.
	 * 
	 * @param p La personne.
	 * @see Person
	 */
	@Override
	public void saveOrUpdatePerson(Person p) {
		
		if (p.getId() == null) {
			String[] columnNameList = {"lastName", "firstName","email", "homePage", "birthDate", "pwd", "idG"};
			String query = "INSERT INTO YEARBOOK_Person (lastName, firstName, email, homePage, birthDate, pwd, idG)"
					+ "VALUES (?, ?, ?, ?, ?, PASSWORD(?), ?)";
			mapper.insertOrUpdate(p, query, columnNameList);
		}
		else {
			String[] columnNameList = {"lastName", "firstName","email", "homePage", "birthDate", "pwd", "idG", "id"};
			String query = "UPDATE YEARBOOK_Person "
					+ "SET lastName = ?, firstName = ?, email = ?, homePage = ?, birthDate = ?, pwd = ?, idG = ? "
					+ "WHERE id = ?";
			mapper.insertOrUpdate(p, query, columnNameList);
		}
	}

	/**
	 * Suppression de la personne associé à l'identifiant id.
	 * 
	 * @param id L'id correspondant à la personne.
	 * @throws DAOException Si la personne qu'on souhaite supprimer n'existe pas dans la bdd ou un pb de connexion.
	 */
	@Override
	public void removePerson(long id) throws DAOException {
		
		try (Connection conn = dbManager.newConnection()) {
			String query = "DELETE FROM YEARBOOK_Person WHERE id = ?";
			PreparedStatement st = conn.prepareStatement(query);
			
			st.setLong(1, id);
			st.executeUpdate();
			dbManager.closeConneection(conn);
		} catch (SQLException | DatabaseManagerException e){
			throw new DAOException(e.getCause());
		}
	}

	/**
	 * Suppression de la personne.
	 * 
	 * @param p La personne à supprimer.
	 * @see Person
	 */
	@Override
	public void removePerson(Person p) {
		if (p != null)
			removePerson(p.getId());
	}

	/**
	 * Suppression de toutes les personnes de la base.
	 * 
	 * @throws DAOException Si aucune personne n'est dans la base ou s'il y'a un pb de connexion
	 */
	@Override
	public void removeAllPersons() throws DAOException {
		
		try (Connection conn = dbManager.newConnection()) {
			String query = "SELECT * FROM YEARBOOK_Person";
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				rs.deleteRow();
			}
			dbManager.closeConneection(conn);
		} catch (SQLException | DatabaseManagerException e){
			throw new DAOException(e.getCause());
		}
	}
	
	/**
	 * Calcul le nombre de personnes de la base.
	 * 
	 * @return Le nombre de personne.
	 */
	@Override
	public int countPersons() {
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

	/**
	 * Retourne le mapper.
	 * 
	 * @return Le mapper.
	 */
	public BeanToResultSetImpl<Person> getMapper() {
		return mapper;
	}

	/**
	 * Mise à jour du mapper.
	 * 
	 * @param mapper Le nouveau mapper.
	 */
	public void setMapper(BeanToResultSetImpl<Person> mapper) {
		this.mapper = mapper;
	}
}