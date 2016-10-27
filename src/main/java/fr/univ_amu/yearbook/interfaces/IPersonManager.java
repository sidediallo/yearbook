package main.java.fr.univ_amu.yearbook.interfaces;

import java.util.Collection;

import main.java.fr.univ_amu.yearbook.bean.Person;
import main.java.fr.univ_amu.yearbook.bus.personManager.exception.PersonManagerException;

/**
 * <b>IPersonManager</b> est l'interface qui gère les méthodes
 * du PersonManager.
 *
 * @see Person
 * @see PersonManagerException
 *  
 * @author Aboubacar Sidy DIALLO & Inoussa ZONGO
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
	 * @throws PersonManagerException Si la personne rattachée à l'id n'existe pas.
	 */
	public Person findPerson(long id) throws PersonManagerException;
	
	/**
	 * Retourne la liste des personnes existantes.
	 * 
	 * @return
	 * 		La liste de personnes.
	 */
	public Collection<Person> findAllPersons();
	
	/**
	 * Création ou mise à jour d'une personne.
	 * 
	 * @param p La personne.
	 * @throws PersonManagerException Si la personne qu'on souhaite rajouter existe déjà
	 * 		   ou si la personne qu'on souhaite mettre à jour n'existe pas.
	 * @see Person
	 */
	public void saveOrUpdatePerson(Person p) throws PersonManagerException;
	
	/**
	 * Suppression de la personne associé à l'identifiant id.
	 * 
	 * @param id L'id correspondant à la personne.
	 * @throws PersonManagerException Si la personne rattachée à l'id n'existe pas.
	 */
	public void removePerson(long id) throws PersonManagerException;
	
	/**
	 * Suppression de la personne.
	 * 
	 * @param p La personne à supprimer
	 * @throws PersonManagerException Si la personne n'existe pas.
	 */
	public void removePerson(Person p) throws PersonManagerException;
	
	/**
	 * Suppression de toute les personnes de la base.
	 */
	public void removeAllPersons();
}