package fr.univ_amu.yearbook.dao.impl;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.univ_amu.yearbook.bean.Person;
import fr.univ_amu.yearbook.dao.exception.DAOException;
import fr.univ_amu.yearbook.dao.exception.DatabaseManagerException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class BeanToResultSetImplTest {
	@Autowired
	BeanToResultSetImpl<Person> mapper;
	
	@Autowired
	PersonDaoImpl dao;
	
	@Test
	public void testInsertOrUpdate() throws DAOException, DatabaseManagerException {
		Person p1 = new Person();
		String[] parametersList = {"lastName", "firstName","email", "homePage", "birthDate", "pwd", "idG"};
		String query = "INSERT INTO YEARBOOK_Person (lastName, firstName, email, homepage, birthDate, pwd, idG)"
				+ "VALUES (?, ?, ?, ?, ?, PASSWORD(?), ?)";
		
		p1.setLastName("CAMARA");
		p1.setFirstName("Moussa");
		p1.setEmail("camara.moussa@localhost.fr");
		p1.setHomePage("www.camara_moussa.fr");
		p1.setBirthDate(Date.valueOf("2016-01-01"));
		p1.setPwd("moussa");
		p1.setIdG((long) 1);
		assertEquals (1, mapper.insertOrUpdate(p1, query, parametersList));
		
		Person p2 = dao.findPerson(1);
		p2.setEmail("sidediallo@yahoo.fr");
		String[] parametersListUpdate = {"email", "id"};
		String queryUpdate = "UPDATE YEARBOOK_Person SET email = ? WHERE id = ?";
		int result = mapper.insertOrUpdate(p2, queryUpdate, parametersListUpdate);
		assertEquals (1, result);
		assertNotEquals(-1, result);
	}

}
