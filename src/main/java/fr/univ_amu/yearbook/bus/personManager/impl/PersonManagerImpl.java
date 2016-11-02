package fr.univ_amu.yearbook.bus.personManager.impl;

import java.util.Collection;

import fr.univ_amu.yearbook.bean.Person;
import fr.univ_amu.yearbook.bus.personManager.IPersonManager;
import fr.univ_amu.yearbook.bus.personManager.exception.PersonManagerException;
import fr.univ_amu.yearbook.dao.exception.DatabaseManagerException;
import fr.univ_amu.yearbook.dao.exception.DAOException;
import fr.univ_amu.yearbook.dao.impl.PersonDaoImpl;

/**
 * <b>PersonManagerImpl</b> implémente IPersonManager.
 *
 * @see Person
 * @see PersonManagerException
 * @see IPersonManager
 *  
 * @author Aboubacar Sidy DIALLO & Inoussa ZONGO
 * @version 1.0
 *
 */
public class PersonManagerImpl implements IPersonManager {
	
	/**
	 * L'objet qui établi le lien avec la PersonDao.
	 * 
	 * @see PersonManagerImpl#PersonManagerImpl()
	 * @see PersonManagerImpl#init()
	 * @see PersonManagerImpl#findPerson(long)
	 * @see PersonManagerImpl#findAllPersons()
	 * @see PersonManagerImpl#saveOrUpdatePerson(Person)
	 * @see PersonManagerImpl#removePerson(long)
	 * @see PersonManagerImpl#removePerson(Person)
	 * @see PersonManagerImpl#removeAllPersons()
	 */
	private PersonDaoImpl dao;
	
	/**
	 * Constructeur par défaut de la classe.
	 */
	public PersonManagerImpl() {
		super();
	}
	
	/**
	 * Initialisation de la PersonDao
	 * 
	 * @throws DatabaseManagerException Si une exception est levée.
	 */
	public void init() throws DatabaseManagerException {
		dao = new PersonDaoImpl();
		dao.init();
	}

	/**
	 * Recherche et renvoie la personne associée à l'identifiant.
	 * 
	 * @param id L'id de la personne.
	 * @return
	 * 		La personne dont l'indentifiant est rentré en paramètre de la méthode. 
	 * @throws PersonManagerException Si la personne rattachée à l'id n'existe pas.
	 */
	@Override
	public Person findPerson(long id) throws PersonManagerException {
		try {
			return dao.findPerson(id);
		} catch (DAOException | DatabaseManagerException e) {
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
	@Override
	public Collection<Person> findAllPersons() {
		try {
			return dao.findAllPersons();
		} catch (DAOException | DatabaseManagerException e) {
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
	@Override
	public void saveOrUpdatePerson(Person p) throws PersonManagerException {
		try {
			dao.saveOrUpdatePerson(p);
		} catch (DAOException e) {
			e.getMessage();
		}
	}

	/**
	 * Suppression de la personne associé à l'identifiant id.
	 * 
	 * @param id L'id correspondant à la personne.
	 * @throws PersonManagerException Si la personne rattachée à l'id n'existe pas.
	 */
	@Override
	public void removePerson(long id) throws PersonManagerException {
		dao.removePerson(id);
	}

	/**
	 * Suppression de la personne.
	 * 
	 * @param p La personne à supprimer
	 * @throws PersonManagerException Si la personne n'existe pas.
	 */
	@Override
	public void removePerson(Person p) throws PersonManagerException {
		dao.removePerson(p);
	}

	/**
	 * Suppression de toute les personnes de la base.
	 */
	@Override
	public void removeAllPersons() {
		dao.removeAllPersons();
	}

	/**
	 * Retourne la PersonDao.
	 * 
	 * @return la PersonDao.
	 */
	public PersonDaoImpl getDao() {
		return dao;
	}

	/**
	 * Mise à jour de PersonDaoImpl.
	 * 
	 * @param dao La nouvelle PersonDao.
	 */
	public void setDao(PersonDaoImpl dao) {
		this.dao = dao;
	}
}
