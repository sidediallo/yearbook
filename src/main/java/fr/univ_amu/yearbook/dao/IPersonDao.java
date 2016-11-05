package fr.univ_amu.yearbook.dao;

import java.util.Collection;

import fr.univ_amu.yearbook.bean.Person;
import fr.univ_amu.yearbook.dao.exception.DAOException;

/**
 * <b>IPersonDao</b> est l'interface qui gère les méthodes
 * du DAO.
 *
 * @see Person
 * @see DAOException
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
	 * 		La personne dont l'indentifiant est rentré en paramètre de la méthode ou null. 
	 * @throws DAOException Si la personne rattachée à l'id n'existe pas ou si la connexion échoue.
	 */
	public Person findPerson(long id) throws DAOException;
	
	/**
	 * Retourne la liste des personnes existantes.
	 * 
	 * @return
	 * 		La liste de personnes.
	 * @throws DAOException S'il n'y a aucune personne dans la base ou si la connexion échoue.
	 */
	public Collection<Person> findAllPersons() throws DAOException;
	
	/**
	 * Création ou mise à jour des données d'une personne.
	 * 
	 * @param p La personne.
	 * @see Person
	 */
	public void saveOrUpdatePerson(Person p);
	
	/**
	 * Suppression de la personne associé à l'identifiant id.
	 * 
	 * @param id L'id correspondant à la personne.
	 * @throws DAOException Si la personne qu'on souhaite supprimer n'existe pas dans la bdd ou un pb de connexion.
	 */
	public void removePerson(long id) throws DAOException;
	
	/**
	 * Suppression de la personne.
	 * 
	 * @param p La personne à supprimer.
	 * @see Person
	 */
	public void removePerson(Person p);
	
	/**
	 * Suppression de toutes les personnes de la base.
	 * 
	 * @throws DAOException Si aucune personne n'est dans la base ou s'il y'a un pb de connexion
	 */
	public void removeAllPersons() throws DAOException;
	
	/**
	 * Calcul le nombre de personnes de la base.
	 * 
	 * @return Le nombre de personne.
	 */
	public int countPersons();
}