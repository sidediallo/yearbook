package fr.univ_amu.yearbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import fr.univ_amu.yearbook.bean.Group;
import fr.univ_amu.yearbook.bean.Person;
import fr.univ_amu.yearbook.dao.IBeanToResultSet;
import fr.univ_amu.yearbook.dao.IDatabaseManager;
import fr.univ_amu.yearbook.dao.IGroupDAO;
import fr.univ_amu.yearbook.dao.IResultSetToBean;
import fr.univ_amu.yearbook.dao.exception.DatabaseManagerException;
import fr.univ_amu.yearbook.dao.exception.DAOException;

/**
 * The GroupDAOImpl is the default implementation of the interface {@link IGroupDAO}.
 * @see IGroupDAO
 * 
 * @author ZONGO
 * @version 1.0
 *
 */
@Service("groupDAO")
@Primary
public class GroupDAOImpl implements IGroupDAO {
	
	@Autowired
	IDatabaseManager dm;
	
	@Autowired
	IBeanToResultSet<Group> btrs;
	
	/**
	 * Default constructor.
	 */
	public GroupDAOImpl() {
		
	}
	
	@PostConstruct
	public void init(){
		
	}
	
	@PostConstruct
	public void close(){
		
	}
	
	/**
	 * @see IGroupDAO#find(Long)
	 */
	@Override
	public Group find(Long id) throws DAOException {
		if(id==null) throw new DAOException("The group id is null");
		Connection conn = null;
		try {
			conn = dm.newConnection();
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM yearbook_group WHERE id = ?;");
			stm.setLong(1, id);
			
			ResultSet rs = stm.executeQuery();
			Group group = null;
			if(rs.next()){
				IResultSetToBean<Group> rstb = new ResultSetToBeanImpl<Group>(Group.class);
				group = rstb.toBean(rs);
			}
			return group;
			
		} catch (DatabaseManagerException e) {
			throw new DAOException("unable to get conection to datasource",e);
		}catch (SQLException e) {
			throw new DAOException("Error when performing find operation",e);
		}finally {
			dm.closeConneection(conn);
		}
	}

	/**
	 * @see IGroupDAO#find()
	 */
	@Override
	public Collection<Group> find() throws DAOException {
		Connection conn = null;
		try {
			conn = dm.newConnection();
			Statement stm = conn.createStatement();
			
			ResultSet rs = stm.executeQuery("SELECT * FROM yearbook_group;");
			ArrayList<Group> groups = new ArrayList<>();
			IResultSetToBean<Group> rstb = new ResultSetToBeanImpl<Group>(Group.class);
			while(rs.next()){
				Group group = rstb.toBean(rs);
				groups.add(group);
			}
			return groups;
			
		} catch (DatabaseManagerException e) {
			throw new DAOException("unable to get conection to datasource",e);
		}catch (SQLException e) {
			throw new DAOException("Error when performing find operation",e);
		}finally {
			dm.closeConneection(conn);
		}
	}

	/**
	 * @see IGroupDAO#saveOrUpdate(Group)
	 */
	@Override
	public Group saveOrUpdate(Group group) throws DAOException {
		try {
			String query;
			if(group.getId() == null){
				query = "INSERT INTO yearbook_group values(?,?);";
				btrs.insertOrUpdate(group, query, new String[]{"id","name"});
			}
			else {
				query = "UPDATE yearbook_group SET name = ? where id = ?";
				btrs.insertOrUpdate(group, query, new String[]{"name","id"});
			}
			return group;
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(),e);
		}
	}

	/**
	 * @see IGroupDAO#delete(Long)
	 */
	@Override
	public int delete(Long id) throws DAOException {
		Connection conn = null;
		try {
			conn = dm.newConnection();
			PreparedStatement stm = conn.prepareStatement("DELETE FROM yearbook_group WHERE id = ?;");
			stm.setObject(1, id);
			return stm.executeUpdate();
		} catch (DatabaseManagerException e) {
			throw new DAOException("unable to get conection to datasource",e);
		}catch (SQLException e) {
			throw new DAOException("Error when performing delete",e);
		}
		finally {
			dm.closeConneection(conn);
		}
	}

	/**
	 * @see IGroupDAO#delete(Group)
	 */
	@Override
	public int delete(Group group) throws DAOException {
		if(group.getId() == null) throw new DAOException("Unable to remove the group : the group id is null");
		return delete(group.getId());
	}

	/**
	 * @see IGroupDAO#findPersons(Long)
	 */
	@Override
	public Collection<Person> findPersons(Long id) throws DAOException {
		Connection conn = null;
		
		try {
			conn = dm.newConnection();
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM yearbook_person WHERE idg = ?;");
			stm.setObject(1, id);
			ResultSet rs = stm.executeQuery();
			Collection<Person> persons = new ArrayList<>();
			IResultSetToBean<Person> rstb = new ResultSetToBeanImpl<>(Person.class);
			while (rs.next()) {
				Person person = rstb.toBean(rs);
				persons.add(person);
			}
			return persons;
		}catch (DatabaseManagerException e) {
			throw new DAOException("unable to get conection to datasource",e);
		}catch (SQLException e) {
			throw new DAOException("Error when performing find",e);
		}finally {
			dm.closeConneection(conn);
		}
	}

	/**
	 * @see IGroupDAO#findPersons(Group)
	 */
	@Override
	public Collection<Person> findPersons(Group group) throws DAOException {
		if(group.getId() == null) throw new DAOException("Unable to find persons in the group : the group id is null");
		return findPersons(group.getId());
	}
	

}
