package main.java.fr.univ_amu.yearbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import main.java.fr.univ_amu.yearbook.bean.Person;
import main.java.fr.univ_amu.yearbook.dao.exception.PersonDaoException;
import main.java.fr.univ_amu.yearbook.interfaces.IPersonDao;
import main.java.fr.univ_amu.yearbook.tools.JdbcTools;
import main.java.fr.univ_amu.yearbook.tools.exception.JdbcToolsException;

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
 * @see PersonDaoException
 * @see IPersonDao
 * @see JdbcTools
 * @see JdbcToolsException
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
	JdbcTools tools;
	
	/**
	 * Initialisation de l'objet tools.
	 */
	public void init() {
		tools = new JdbcTools();
		tools.init();
	}
	
	/**
	 * Recherche et renvoie la personne associée à l'identifiant.
	 * 
	 * @param id L'id de la personne.
	 * @return
	 * 		La personne dont l'indentifiant est rentré en paramètre de la méthode. 
	 * @throws PersonDaoException Si la personne rattachée à l'id n'existe pas.
	 * @throws JdbcToolsException Si la connection n'est pas établie.
	 */
	public Person findPerson(long id) throws PersonDaoException, JdbcToolsException {
		Person p = new Person();
		ResultSet rs;
		String query = "SELECT * FROM JEE_Person WHERE idP = ?";
		
		try (Connection c = tools.newConnection()) {
			PreparedStatement st = c.prepareStatement(query);
			st.setLong(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				p.setId(rs.getLong(1));
				p.setLastName(rs.getString(2));
				p.setFirstName(rs.getString(3));
				p.setEmail(rs.getString(4));
				p.setHomePage(rs.getString(5));
				p.setBirthDate(rs.getDate(6));
				p.setPwd(rs.getString(7));
				p.setIdG(rs.getLong(8));
			}
		} catch (SQLException e){
			throw new PersonDaoException();
		}

		return p;
	}

	/**
	 * Retourne la liste des personnes existantes.
	 * 
	 * @return
	 * 		La liste de personnes.
	 * @throws PersonDaoException S'il n'y a aucune personne.
	 * @throws JdbcToolsException Si la connection n'est pas établie.
	 */
	public Collection<Person> findAllPersons() throws PersonDaoException, JdbcToolsException {
		Collection<Person> cp = new LinkedList<Person>();
		Person p = new Person();
		ResultSet rs;
		String query = "SELECT * FROM JEE_Person";
		
		try (Connection c = tools.newConnection()) {
			Statement st = c.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				p.setId(rs.getLong(1));
				p.setLastName(rs.getString(2));
				p.setFirstName(rs.getString(3));
				p.setEmail(rs.getString(4));
				p.setHomePage(rs.getString(5));
				p.setBirthDate(rs.getDate(6));
				p.setPwd(rs.getString(7));
				p.setIdG(rs.getLong(8));
				
				cp.add(p);
			}
			return cp;
		} catch (SQLException e){
			throw new PersonDaoException();
		}
	}

	/**
	 * Création ou mise à jour d'une personne.
	 * 
	 * @param p La personne.
	 * @throws PersonDaoException Si la personne qu'on souhaite rajouter existe déjà
	 * 		   ou si la personne qu'on souhaite mettre à jour n'existe pas.
	 * @see Person
	 */
	public void saveOrUpdatePerson(Person p, boolean option) throws PersonDaoException {
		String query = "SELECT * FROM JEE_Person";
		Statement st;
		ResultSet rs;
		
		if (option) {
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
		} catch (SQLException | JdbcToolsException e){
			try {
				throw new PersonDaoException("error : no row updated or inserted");
			} catch (PersonDaoException e1) {
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
		String query = "DELETE FROM JEE_Person WHERE idP = ?";
		PreparedStatement st;
		
		try (Connection c = tools.newConnection()) {
			st = c.prepareStatement(query);
			st.setLong(1, id);
			st.executeUpdate();
		} catch (SQLException | JdbcToolsException e){
			try {
				throw new PersonDaoException("error : no row deleted");
			} catch (PersonDaoException e1) {
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
		String query = "SELECT * FROM JEE_Person";
		Statement st;
		ResultSet rs;
		
		try (Connection c = tools.newConnection()) {
			st = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				rs.deleteRow();
			}
			
		} catch (SQLException | JdbcToolsException e){
			try {
				throw new PersonDaoException("error : no row deleted");
			} catch (PersonDaoException e1) {
				e1.getMessage();
			}
		}
	}
	
	/**
	 * Calcul le nombre de personnes de la base.
	 * 
	 * @return Le nombre de personne.
	 * @throws PersonDaoException Si exception levé avant.
	 * @throws JdbcToolsException Si la connection n'est pas établie.
	 */
	public int countPersons() throws PersonDaoException, JdbcToolsException {
		return findAllPersons().size();
	}
}