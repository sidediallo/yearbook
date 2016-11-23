package fr.univ_amu.yearbook.bus.personManager;

import java.util.Collection;

import fr.univ_amu.yearbook.bean.Person;
import fr.univ_amu.yearbook.bus.exception.ManagerException;

/**
 * <b>IPersonManager</b> est l'interface qui gère le manager
 * IPersonManager.
 *
 * @see Person
 * @see ManagerException
 *  
 * @author Aboubacar Sidy DIALLO
 * @author Inoussa ZONGO
 * @version 1.0
 *
 */
public interface IPersonManager {
	
	/**
	 * Recherche et renvoie la personne associée à l'identifiant.
	 * 
	 * @param id L'id de la personne.
	 * @return
	 * 		La personne dont l'indentifiant est rentré en paramètre de la méthode. 
	 * @throws ManagerException Si la personne rattachée à l'id n'existe pas.
	 */
	public Person findPerson(long id) throws ManagerException;
	
	/**
	 * Retourne la liste des personnes existantes.
	 * 
	 * @return
	 * 		La liste des personnes.
	 * @throws ManagerException Si la bd est vide ou si une autre exception est levée. 
	 */
	public Collection<Person> findAllPersons() throws ManagerException;
	
	/**
	 * Création ou mise à jour d'une personne.
	 * 
	 * @param p La personne.
	 * @throws ManagerException Si la personne qu'on souhaite rajouter existe déjà
	 * 		   ou si la personne qu'on souhaite mettre à jour n'existe pas.
	 * @see Person
	 */
	public void saveOrUpdatePerson(Person p) throws ManagerException;
	
	/**
	 * Suppression de la personne associé à l'identifiant id.
	 * 
	 * @param id L'id correspondant à la personne.
	 * @throws ManagerException Si la personne rattachée à l'id n'existe pas.
	 */
	public void removePerson(long id) throws ManagerException;
	
	/**
	 * Suppression de la personne.
	 * 
	 * @param p La personne à supprimer
	 * @throws ManagerException Si la personne n'existe pas.
	 * @see Person
	 */
	public void removePerson(Person p) throws ManagerException;
	
	/**
	 * Suppression de toute les personnes de la base.
	 * @throws ManagerException Si la bd est vide ou si une autre type d'exception est levée. 
	 */
	public void removeAllPersons() throws ManagerException;
	
	/**
	 * Calcul le nombre de personnes de la base.
	 * 
	 * @return Le nombre de personne.
	 * @throws ManagerException Si la connexion échoue. 
	 */
	public int countPersons() throws ManagerException;
}