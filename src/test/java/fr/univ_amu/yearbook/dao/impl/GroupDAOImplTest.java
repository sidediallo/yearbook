package fr.univ_amu.yearbook.dao.impl;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.univ_amu.yearbook.bean.Group;
import fr.univ_amu.yearbook.dao.IDatabaseManager;
import fr.univ_amu.yearbook.dao.IGroupDAO;
import fr.univ_amu.yearbook.dao.exception.DAOException;
import fr.univ_amu.yearbook.bean.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class GroupDAOImplTest {

	@Autowired
	IGroupDAO groupDAO;
	Connection conn;

	@Autowired
	IDatabaseManager dm;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		conn = dm.newConnection();
	}

	@After
	public void tearDown() throws Exception {
		dm.closeConneection(conn);
	}

	@Test
	public void testFindInteger() {
		Group group = groupDAO.find(1L);
		assertTrue(group != null);
		assertTrue(group.getId() == 1);
		assertEquals(group.getName(), "M2 FED 2015/2016");

		Group group2 = groupDAO.find(Long.MAX_VALUE);
		assertNull(group2);
	}

	@Test
	public void testFind() {
		Collection<Group> groups = groupDAO.find();
		assertEquals(groups.size(),9);
	}

	@Test
	public void testSaveOrUpdate() throws SQLException {
		//insert
		Group group = new Group();
		Timestamp timestamp =  new Timestamp(System.currentTimeMillis());
		String groupName = "Group "+timestamp.toString();
		group.setName(groupName);
		Group savedGroup = groupDAO.saveOrUpdate(group);
		assertEquals(savedGroup.getName(), group.getName());
		assertNotNull(savedGroup.getId());

		Statement countQuery = conn.createStatement();
		ResultSet countRS = countQuery.executeQuery("SELECT COUNT(*) FROM yearbook_group;");
		countRS.next();
		Integer groupCount = countRS.getInt(1);
		assertEquals(groupCount, new Integer(10));

		//update the group
		String newName = "New group name";
		savedGroup.setName(newName);
		Group updatedGroup = groupDAO.saveOrUpdate(savedGroup);
		assertNotNull(updatedGroup);
		assertEquals(savedGroup.getName(), newName);

		//Cleaning db
		PreparedStatement removeQuery = conn.prepareStatement("DELETE FROM yearbook_group where id = ?;");
		removeQuery.setObject(1, updatedGroup.getId());
		removeQuery.executeUpdate();
	}

	@Test
	public void testDeleteInteger() throws SQLException {
		//Add a new group
		PreparedStatement pstm = conn.prepareStatement("INSERT INTO yearbook_group values(?,?)",Statement.RETURN_GENERATED_KEYS);
		pstm.setObject(1, null);
		Timestamp timestamp =  new Timestamp(System.currentTimeMillis());
		String groupName = "Group "+timestamp.toString();
		pstm.setObject(2, groupName);
		pstm.executeUpdate();
		ResultSet pkrs = pstm.getGeneratedKeys();
		pkrs.next();
		Long groupId = pkrs.getLong(1);
		
		//Count row
		Statement countQuery = conn.createStatement();
		ResultSet countRS = countQuery.executeQuery("SELECT COUNT(*) FROM yearbook_group;");
		countRS.next();
		Integer groupCount = countRS.getInt(1);
		
		//Deleting row
		int deleteStatus = groupDAO.delete(groupId);
		
		//Count row
		ResultSet countRS2 = countQuery.executeQuery("SELECT COUNT(*) FROM yearbook_group;");
		countRS2.next();
		Integer groupCount2 = countRS2.getInt(1);
		
		assertTrue(deleteStatus == 1);
		assertTrue(groupCount2 == groupCount-1);
		
		//Deleting non existing row
		int deleteStatus2 = groupDAO.delete(groupId+1);
		assertTrue(deleteStatus2 == 0);
	}

	@Test
	public void testDeleteGroup() {
		Group group = new Group();
		group.setId(13L);
		int num = groupDAO.delete(group);
		assertTrue(num == 0 || num ==1);
	}

	@Test
	public void testGetPersonsInteger(){
		Collection<Person> persons = groupDAO.findPersons(1L);
		assertTrue(persons.size()==3);
		
		Collection<Person> persons2 = groupDAO.findPersons(7L);
		assertTrue(persons2.size()==1);
		
		Collection<Person> persons3 = groupDAO.findPersons(8L);
		assertTrue(persons3.isEmpty());
	}

	@Test
	public void testGetPersonsGroup(){
		Group group = new Group();
		group.setId(1L);
		Collection<Person> persons = groupDAO.findPersons(group);
		assertTrue(persons.size()==3);
	}
}
