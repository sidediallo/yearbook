package fr.univ_amu.yearbook.dao;

import java.util.Collection;

import fr.univ_amu.yearbook.bean.Group;
import fr.univ_amu.yearbook.dao.exception.DAOException;
import fr.univ_amu.yearbook.bean.Person;

/**
 * The IGroupDAO provides CRUD(create, read, update and delete) operations for the beans {@link Group}
 * 
 * @author ZONGO
 *@version 1.0
 */
public interface IGroupDAO {
	/**
	 * Returns the group whose identifer is given in parameter.
	 * @param id the identifier of the group.
	 * @return the group whose identifier is given in parameter.
	 * @throws DAOException
	 */
	public Group find(Long id) throws DAOException;
	
	/**
	 * Returns all groups from datasource.
	 * @return all group in datasource.
	 * @throws DAOException
	 */
	public Collection<Group> find() throws DAOException;
	
	/**
	 * This method is responsible of saving or updating a Group instance.<br>
	 * In case the id of the group is set, the method makes an update of the 
	 * group in datasource identified by the group id with the group in parameter.<br>
	 * If the id of the group is null, the method insert the group in datasource and return the group with its assigned identifier.<br>
	 * @param group the group to save or update
	 * @return the Group that have been saved.
	 * @throws DAOException
	 */
	public Group saveOrUpdate(Group group) throws DAOException;
	/**
	 * Removes the group identified by the parameter from datasource.
	 * @param id identifier of the group.
	 * @return the number of group affected by the operation.
	 * @throws DAOException
	 */
	public int delete(Long id) throws DAOException;
	
	/**
	 * removes the group in parameter from datasource.
	 * @param group the group to delete.
	 * @return the number of group affected by the operation.
	 * @throws DAOException
	 */
	public int delete(Group group) throws DAOException;
	
	/**
	 * Returns all the {@link fr.univ_amu.yearbook.bean.Person persons} in the group whose identifier is given in parameter.
	 * @param id identifier of the group.
	 * @return all the {@link fr.univ_amu.yearbook.bean.Person persons} in the group whose identifier is given in parameter.
	 * @throws DAOException
	 */
	public Collection<Person> findPersons(Long id) throws DAOException;
	
	/**
	 * Returns all the {@link fr.univ_amu.yearbook.bean.Person persons} in this group.
	 * @param group the group to find all persons.
	 * @return all the {@link fr.univ_amu.yearbook.bean.Person persons} in this group.
	 * @throws DAOException
	 */
	public Collection<Person> findPersons(Group group) throws DAOException;
}
