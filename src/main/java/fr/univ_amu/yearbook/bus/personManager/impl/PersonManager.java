package fr.univ_amu.yearbook.bus.personManager.impl;

import java.util.Collection;

import fr.univ_amu.yearbook.bean.Person;
import fr.univ_amu.yearbook.bus.personManager.IPersonManager;
import fr.univ_amu.yearbook.bus.personManager.exception.PersonManagerException;
import fr.univ_amu.yearbook.dao.exception.DatabaseManagerException;
import fr.univ_amu.yearbook.dao.exception.PersonDaoException;
import fr.univ_amu.yearbook.dao.impl.PersonDao;

/**
 * <b>PersonManager</b> implémente IPersonManager.
 *
 * @see Person
 * @see PersonManagerException
 * @see IPersonManager
 *  
 * @author Aboubacar Sidy DIALLO & Inoussa ZONGO
 * @version 1.0
 *
 */
public class PersonManager implements IPersonManager {
	private PersonDao dao = new PersonDao();

	/**
	 * Recherche et renvoie la personne associée à l'identifiant.
	 * 
	 * @param id L'id de la personne.
	 * @return
	 * 		La personne dont l'indentifiant est rentré en paramètre de la méthode. 
	 * @throws PersonManagerException Si la personne rattachée à l'id n'existe pas.
	 */
	public Person findPerson(long id) throws PersonManagerException {
		try {
			return dao.findPerson(id);
		} catch (PersonDaoException | DatabaseManagerException e) {
			e.getMessage();
		}
		return null;
	}

	/**
	 * Retourne la liste des personnes existantes.
	 * 
	 * @return
	 * 		La liste de personnes.
	 */
	public Collection<Person> findAllPersons() {
		try {
			return dao.findAllPersons();
		} catch (PersonDaoException | DatabaseManagerException e) {
			e.getMessage();
		}
		return null;
	}

	/**
	 * Création ou mise à jour d'une personne.
	 * 
	 * @param p La personne.
	 * @throws PersonManagerException Si la personne qu'on souhaite rajouter existe déjà
	 * 		   ou si la personne qu'on souhaite mettre à jour n'existe pas.
	 * @see Person
	 */
	public void saveOrUpdatePerson(Person p) throws PersonManagerException {
		try {
			dao.saveOrUpdatePerson(p);
		} catch (PersonDaoException e) {
			e.getMessage();
		}
	}

	/**
	 * Suppression de la personne associé à l'identifiant id.
	 * 
	 * @param id L'id correspondant à la personne.
	 * @throws PersonManagerException Si la personne rattachée à l'id n'existe pas.
	 */
	public void removePerson(long id) throws PersonManagerException {
		dao.removePerson(id);
	}

	/**
	 * Suppression de la personne.
	 * 
	 * @param p La personne à supprimer
	 * @throws PersonManagerException Si la personne n'existe pas.
	 */
	public void removePerson(Person p) throws PersonManagerException {
		dao.removePerson(p);
	}

	/**
	 * Suppression de toute les personnes de la base.
	 */
	public void removeAllPersons() {
		dao.removeAllPersons();
	}
}
