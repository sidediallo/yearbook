package fr.univ_amu.yearbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import fr.univ_amu.yearbook.bean.Person;
import fr.univ_amu.yearbook.dao.IPersonDao;
import fr.univ_amu.yearbook.dao.exception.DatabaseManagerException;
import fr.univ_amu.yearbook.dao.exception.DAOException;

/**
 * <b>PersonDao</b> est la classe qui implemente l'interface
 * IPersonDao.
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
public class PersonDao implements IPersonDao {
	
	/**
	 * L'objet qui établi la connection avec la base.
	 * 
	 * @see 
	 */
	DatabaseManagerImpl tools;
	
	/**
	 * Initialisation de l'objet tools.
	 * @throws DatabaseManagerException 
	 */
	public void init() throws DatabaseManagerException {
		tools = new DatabaseManagerImpl();
		tools.init();
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
	public Person findPerson(long id) throws DAOException, DatabaseManagerException {
		
		try (Connection conn = tools.newConnection()) {
			ResultSetToBeanImpl<Person> mapper = new ResultSetToBeanImpl<Person>(Person.class);
			String query = "SELECT * FROM YEARBOOK_Person WHERE idP = ?";
			PreparedStatement st = conn.prepareStatement(query);
			
			st.setLong(1, id);
			ResultSet rs = st.executeQuery();
			
			return mapper.toBean(rs); 
		} catch (SQLException e){
			throw new DAOException(e.getMessage());
		}
	}

	/**
	 * Retourne la liste des personnes existantes.
	 * 
	 * @return
	 * 		La liste de personnes.
	 * @throws DAOException S'il n'y a aucune personne.
	 * @throws DatabaseManagerException Si la connection n'est pas établie.
	 */
	public Collection<Person> findAllPersons() throws DAOException, DatabaseManagerException {
		
		try (Connection conn = tools.newConnection()) {
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
	public void saveOrUpdatePerson(Person p) throws DAOException {
		String query = "SELECT * FROM YEARBOOK_Person";
		Statement st;
		ResultSet rs;
		
		boolean test = true;
		
		if (test) {
			query = "INSERT INTO JEE_Person (lastName, firstName, email, homePage, birthDate, pwdG, idG)"
					+ "VALUES (?, ?, ?, ?, STR_TO_DATE(?,'%Y/%m/%d'), PASSWORD(?), ?)";
		}
		else {
			query = "UPDATE JEE_Person SET lastName = ?, firstName = ?, email = ?, homePage = ?, birthDate = ?, pwdG = ?, idG = ?"
					+ "WHERE idP = ?";
		}
		
		try (Connection c = tools.newConnection()) {
			st = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(query);
			
			// À implémenter
			
			rs.updateRow();
		} catch (SQLException | DatabaseManagerException e){
			try {
				throw new DAOException("error : no row updated or inserted");
			} catch (DAOException e1) {
				e1.getMessage();
			}
		}
	}

	/**
	 * Suppression de la personne associé à l'identifiant id.
	 * 
	 * @param id L'id correspondant à la personne.
	 */
	public void removePerson(long id) {
		String query = "DELETE FROM YEARBOOK_Person WHERE idP = ?";
		PreparedStatement st;
		
		try (Connection conn = tools.newConnection()) {
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
	public void removePerson(Person p) {
		removePerson(p.getId());
	}

	/**
	 * Suppression de toutes les personnes de la base.
	 */
	public void removeAllPersons() {
		String query = "SELECT * FROM YEARBOOK_Person";
		Statement st;
		ResultSet rs;
		
		try (Connection conn = tools.newConnection()) {
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
	public int countPersons() throws DAOException, DatabaseManagerException {
		return findAllPersons().size();
	}
}