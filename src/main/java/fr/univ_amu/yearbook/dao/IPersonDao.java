package fr.univ_amu.yearbook.dao;

import java.util.Collection;

import fr.univ_amu.yearbook.bean.Person;
import fr.univ_amu.yearbook.dao.exception.DatabaseManagerException;
import fr.univ_amu.yearbook.dao.exception.PersonDaoException;

/**
 * <b>IPersonDao</b> est l'interface qui gère les méthodes
 * du DAO.
 *
 * @see Person
 * @see PersonDaoException
 *  
 * @author Aboubacar Sidy DIALLO & Inoussa ZONGO
 * @version 1.0
 *
 */
public interface IPersonDao {
	
	/**
	 * Recherche et renvoie la personne associée à l'identifiant.
	 * 
	 * @param id L'id de la personne.
	 * @return
	 * 		La personne dont l'indentifiant est rentré en paramètre de la méthode. 
	 * @throws PersonDaoException Si la personne rattachée à l'id n'existe pas.
	 * @throws JdbcToolsException Si la connection n'est pas établie.
	 */
	public Person findPerson(long id) throws PersonDaoException, DatabaseManagerException;
	
	/**
	 * Retourne la liste des personnes existantes.
	 * 
	 * @return
	 * 		La liste de personnes.
	 * @throws PersonDaoException S'il n'y a aucune personne.
	 * @throws JdbcToolsException Si la connection n'est pas établie.
	 */
	public Collection<Person> findAllPersons() throws PersonDaoException, DatabaseManagerException;
	
	/**
	 * Création ou mise à jour d'une personne.
	 * 
	 * @param p La personne.
	 * @throws PersonDaoException Si la personne qu'on souhaite rajouter existe déjà
	 * 		   ou si la personne qu'on souhaite mettre à jour n'existe pas.
	 * @see Person
	 */
	public void saveOrUpdatePerson(Person p, boolean option) throws PersonDaoException;
	
	/**
	 * Suppression de la personne associé à l'identifiant id.
	 * 
	 * @param id L'id correspondant à la personne.
	 */
	public void removePerson(long id);
	
	/**
	 * Suppression de la personne.
	 * 
	 * @param p La personne à supprimer
	 * @throws PersonDaoException Si la personne n'existe pas.
	 * @see Person
	 */
	public void removePerson(Person p);
	
	/**
	 * Suppression de toute les personnes de la base.
	 */
	public void removeAllPersons();
	
	/**
	 * Calcul le nombre de personnes de la base.
	 * 
	 * @return Le nombre de personne.
	 * @throws PersonDaoException Si exception levé avant.
	 * @throws JdbcToolsException Si la connection n'est pas établie.
	 */
	public int countPersons() throws PersonDaoException, DatabaseManagerException;
}