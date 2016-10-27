package test.java.fr.univ_amu.yearbook.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import main.java.fr.univ_amu.yearbook.bean.Person;
import main.java.fr.univ_amu.yearbook.dao.exception.PersonDaoException;
import main.java.fr.univ_amu.yearbook.dao.impl.PersonDao;
import main.java.fr.univ_amu.yearbook.tools.exception.JdbcToolsException;

public class PersonDaoTest {
	PersonDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("AVANT");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("APRÈS");
	}

	@Before
	public void setUp() throws Exception {
		dao = new PersonDao();
		dao.init();
	}

	@After
	public void tearDown() throws Exception {
		dao = null;
	}

	@Ignore
	public void testFindPerson() throws PersonDaoException, JdbcToolsException {
		Person p1 = dao.findPerson(1);
		Person p2 = dao.findPerson(30);
		
		System.out.println("p1 = " + p1.getId() + " " + p1.getLastName() + " " + p1.getFirstName());
		System.out.println("p2 = " + p2.getId() + " " + p2.getLastName() + " " + p2.getFirstName());
	}

	@Ignore
	public void testFindAllPersons() throws PersonDaoException, JdbcToolsException {
		List<Person> p = (List<Person>) dao.findAllPersons();
		assertEquals(4, p.size());
	}

	@Test
	public void testSaveOrUpdatePerson() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testRemovePersonLong() throws PersonDaoException, JdbcToolsException {
		int count = dao.countPersons();
		
		dao.removePerson(10);
		assertEquals(count - 1, dao.countPersons());
	}

	@Ignore
	public void testRemovePersonPerson() throws PersonDaoException, JdbcToolsException {
		Person p = new Person();
		int count = dao.countPersons();
		
		p.setId(2);
		dao.removePerson(p);
		assertEquals(count - 1, dao.countPersons());
	}

	@Ignore
	public void testRemoveAllPersons() throws PersonDaoException, JdbcToolsException {
		dao.removeAllPersons();
		assertEquals(0, dao.countPersons());
	}

	@Ignore
	public void testNumberOfPersons() throws PersonDaoException, JdbcToolsException {
		assertEquals(10,dao.countPersons());
	}
}