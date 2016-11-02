package fr.univ_amu.yearbook.dao.impl;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.univ_amu.yearbook.bean.Person;
import fr.univ_amu.yearbook.dao.exception.DatabaseManagerException;
import fr.univ_amu.yearbook.dao.exception.DAOException;
import fr.univ_amu.yearbook.dao.impl.PersonDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class PersonDaoTest {
	@Autowired
	PersonDaoImpl personDao;
	
	@Test
	public void testFindPerson() throws DAOException, DatabaseManagerException {
		long id = Long.MAX_VALUE;
		Person p1 = personDao.findPerson(1);
		Person p2 = personDao.findPerson(id);
		
		assertNotNull(p1);
		assertTrue("true", p1 instanceof Person);
	
		assertNull(p2);
		assertFalse("false", p2 instanceof Person);
	}

	@Test
	public void testFindAllPersons() throws DAOException, DatabaseManagerException {
		List<Person> p = (List<Person>) personDao.findAllPersons();
		
		assertNotNull(p);
		assertEquals(4, p.size());
	}

	@Ignore
	public void testSaveOrUpdatePerson() throws DAOException, DatabaseManagerException {
		int count1 = personDao.countPersons();
		int count2;
		Person p1 = new Person();
		
		p1.setLastName("BAH");
		p1.setFirstName("Mamadou Gando");
		p1.setEmail("bah.mamadou@localhost.fr");
		p1.setHomePage("www.bah_mamadou.fr");
		p1.setBirthDate(Date.valueOf("2016-01-01"));
		p1.setPwd("mamadou");
		p1.setIdG((long) 1);
		personDao.saveOrUpdatePerson(p1);
		
		count2 = personDao.countPersons();
		
		assertEquals(count1 + 1, count2);
		assertNotEquals(count1, count2);
	}

	@Ignore
	public void testRemovePersonLong() throws DAOException, DatabaseManagerException {
		int count1 = personDao.countPersons();
		int count2;
		
		personDao.removePerson(4);
		count2 = personDao.countPersons();
		
		assertEquals(count1 - 1, count2);
		assertNotEquals(count1, count2);
	}

	@Ignore
	public void testRemovePersonPerson() throws DAOException, DatabaseManagerException {
		Person p = new Person();
		int count1 = personDao.countPersons();
		int count2;
		
		p.setId((long) 3);
		personDao.removePerson(p);
		count2 = personDao.countPersons();
		
		assertEquals(count1 - 1, count2);
		assertNotEquals(count1, count2);
	}

	@Ignore
	public void testRemoveAllPersons() throws DAOException, DatabaseManagerException {
		int count1 = personDao.countPersons();
		int count2;
		
		personDao.removeAllPersons();
		count2 = personDao.countPersons();
		
		assertEquals(0, count2);
		assertNotEquals(count1, count2);
	}

	@Test
	public void testcountPersons() throws DAOException, DatabaseManagerException {
		int countP = personDao.countPersons();
		
		assertEquals(3, countP);
	}
}