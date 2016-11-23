package fr.univ_amu.yearbook.bus.personManager;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.univ_amu.yearbook.bean.Person;
import fr.univ_amu.yearbook.bus.exception.ManagerException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring_test.xml"})
public class PersonManagerImplTest {
	@Autowired
	IPersonManager personManager;
	
	@Test
	public void testFindPerson() throws ManagerException {
		List<Person> p = (List<Person>) personManager.findAllPersons();
		long id = Long.MAX_VALUE;
		Person p1 = personManager.findPerson(p.get(0).getId());
		
		Person p2 = personManager.findPerson(id);
		
		assertNotNull(p1);
		assertTrue("true", p1 instanceof Person);
	
		assertNull(p2);
		assertFalse("false", p2 instanceof Person);
	}

	@Test
	public void testFindAllPersons() throws ManagerException {
		List<Person> p = (List<Person>) personManager.findAllPersons();
		
		assertNotNull(p);
		assertEquals(8, p.size());
	}

	@Test
	public void testSaveOrUpdatePerson() throws ManagerException {
		int count1 = personManager.countPersons();
		int count2;
		Person p1 = new Person();
		
		// ajout d'une personne
		p1.setLastName("JOBS");
		p1.setFirstName("Steve");
		p1.setEmail("steve.jobs@localhost.fr");
		p1.setHomePage("www.steve_jobs.fr");
		p1.setBirthDate(Date.valueOf("2016-01-01"));
		p1.setPwd("steve");
		p1.setIdG((long) 2);
		personManager.saveOrUpdatePerson(p1);
		
		count2 = personManager.countPersons();
		assertNotNull(p1.getId());
		assertEquals(count1 + 1, count2);
		assertNotEquals(count1, count2);

		// màj des données d'une personne
		Person p2 = personManager.findPerson(p1.getId());
		
		p2.setEmail("other_mail@localhost.fr");
		personManager.saveOrUpdatePerson(p2);
		
		Person p2Bis = personManager.findPerson(p1.getId());
		assertEquals(p2.getEmail(), p2Bis.getEmail());
		
		personManager.removePerson(p1);
	}

	@Test
	public void testRemovePersonLong() throws ManagerException {
		int count1;
		int count2;
		Person p1 = new Person();
		
		// ajout d'une personne
		p1.setLastName("JOBS");
		p1.setFirstName("Steve");
		p1.setEmail("steve.jobs@localhost.fr");
		p1.setHomePage("www.steve_jobs.fr");
		p1.setBirthDate(Date.valueOf("2016-01-01"));
		p1.setPwd("steve");
		p1.setIdG((long) 2);
		personManager.saveOrUpdatePerson(p1);
		
		count1 = personManager.countPersons();
		personManager.removePerson(p1.getId());
		count2 = personManager.countPersons();
		
		assertEquals(count1 - 1, count2);
		assertNotEquals(count1, count2);
	}

	@Test
	public void testRemovePersonPerson() throws ManagerException {
		Person p1 = new Person();
		
		// ajout d'une personne
		p1.setLastName("JOBS");
		p1.setFirstName("Steve");
		p1.setEmail("steve.jobs@localhost.fr");
		p1.setHomePage("www.steve_jobs.fr");
		p1.setBirthDate(Date.valueOf("2016-01-01"));
		p1.setPwd("steve");
		p1.setIdG((long) 2);
		personManager.saveOrUpdatePerson(p1);
		
		int count1 = personManager.countPersons();
		int count2;
		
		personManager.removePerson(p1);
		count2 = personManager.countPersons();
		
		assertEquals(count1 - 1, count2);
		assertNotEquals(count1, count2);
	}

	@Test
	public void testRemoveAllPersons() throws ManagerException {
		List<Person> people = (List<Person>) personManager.findAllPersons();
		
		
		int count1 = personManager.countPersons();
		int count2;
		
		personManager.removeAllPersons();
		count2 = personManager.countPersons();
		
		assertEquals(0, count2);
		assertNotEquals(count1, count2);
		
		for (Person p : people) {
			p.setId(null);
			personManager.saveOrUpdatePerson(p);
		}
		
		assertEquals(8, personManager.findAllPersons().size());
	}

	@Test
	public void testcountPersons() throws ManagerException {
		int countP = personManager.countPersons();
		
		assertEquals(8, countP);
	}
}