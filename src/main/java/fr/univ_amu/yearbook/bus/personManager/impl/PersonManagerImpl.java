package fr.univ_amu.yearbook.bus.personManager.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univ_amu.yearbook.bean.Person;
import fr.univ_amu.yearbook.bus.exception.ManagerException;
import fr.univ_amu.yearbook.bus.personManager.IPersonManager;
import fr.univ_amu.yearbook.dao.IPersonDAO;
import fr.univ_amu.yearbook.dao.exception.DAOException;

/**
 * <b>PersonManagerImpl</b> implémente le manager IPersonManager.
 * 
 * @see Person
 * @see ManagerException
 *  
 * @author Aboubacar Sidy DIALLO
 * @author Inoussa ZONGO
 * @version 1.0
 *
 */
@Service("personManager")
public class PersonManagerImpl implements IPersonManager {
	
	/**
	 * L'objet qui établi le lien avec la PersonDao.
	 * 
	 * @see #findPerson(long)
	 * @see #findAllPersons()
	 * @see #saveOrUpdatePerson(Person)
	 * @see #removePerson(long)
	 * @see #removePerson(Person)
	 * @see #removeAllPersons()
	 */
	@Autowired
	private IPersonDAO dao;
	
	/**
	 * Constructeur par défaut de la classe.
	 */
	public PersonManagerImpl() {
		super();
	}

	/**
	 * Recherche et renvoie la personne associée à l'identifiant.
	 * 
	 * @param id L'id de la personne.
	 * @return
	 * 		La personne dont l'indentifiant est rentré en paramètre de la méthode. 
	 * @throws ManagerException Si la personne rattachée à l'id n'existe pas.
	 */
	@Override
	public Person findPerson(long id) throws ManagerException {
		try {
			return dao.findPerson(id);
		} catch (DAOException e) {
			throw new ManagerException(e.getCause());
		}
	}

	/**
	 * Retourne la liste des personnes existantes.
	 * 
	 * @return
	 * 		La liste des personnes.
	 * @throws ManagerException Si la bd est vide ou si une autre exception est levée. 
	 */
	@Override
	public Collection<Person> findAllPersons() throws ManagerException {
		try {
			return dao.findAllPersons();
		} catch (DAOException e) {
			throw new ManagerException(e.getCause());
		}
	}

	/**
	 * Création ou mise à jour d'une personne.
	 * 
	 * @param p La personne.
	 * @throws ManagerException Si la personne qu'on souhaite rajouter existe déjà
	 * 		   ou si la personne qu'on souhaite mettre à jour n'existe pas.
	 * @see Person
	 */
	@Override
	public void saveOrUpdatePerson(Person p) throws ManagerException {
		try {
			dao.saveOrUpdatePerson(p);
		} catch (DAOException e) {
			throw new ManagerException(e.getCause());
		}
	}

	/**
	 * Suppression de la personne associé à l'identifiant id.
	 * 
	 * @param id L'id correspondant à la personne.
	 * @throws ManagerException Si la personne rattachée à l'id n'existe pas.
	 */
	@Override
	public void removePerson(long id) throws ManagerException {
		try {
			dao.removePerson(id);
		} catch (DAOException e) {
			throw new ManagerException(e.getCause());
		}
	}

	/**
	 * Suppression de la personne.
	 * 
	 * @param p La personne à supprimer
	 * @throws ManagerException Si la personne n'existe pas.
	 * @see Person
	 */
	@Override
	public void removePerson(Person p) throws ManagerException {
		try {
			dao.removePerson(p);
		} catch (DAOException e) {
			throw new ManagerException(e.getCause());
		}
	}

	/**
	 * Suppression de toute les personnes de la base.
	 * @throws ManagerException Si la bd est vide ou si une autre type d'exception est levée. 
	 */
	@Override
	public void removeAllPersons() throws ManagerException {
		try {
			dao.removeAllPersons();
		} catch (DAOException e) {
			throw new ManagerException(e.getCause());
		}
	}
	
	/**
	 * Calcul le nombre de personnes de la base.
	 * 
	 * @return Le nombre de personne.
	 * @throws ManagerException Si la connexion échoue. 
	 */
	@Override
	public int countPersons() throws ManagerException {
		try {
			return dao.countPersons();
		} catch (DAOException e) {
			throw new ManagerException(e.getCause());
		}
	}

	/**
	 * Retourne une instance de la classe qui implémente l'interface IPersonDao.
	 * 
	 * @return L'instance.
	 */
	public IPersonDAO getDao() {
		return dao;
	}

	/**
	 * Mise à jour de l'instance de la classe qui implémente l'interface IPersonDao.
	 * 
	 * @param dao La nouvelle instance.
	 */
	public void setDao(IPersonDAO dao) {
		this.dao = dao;
	}
}
