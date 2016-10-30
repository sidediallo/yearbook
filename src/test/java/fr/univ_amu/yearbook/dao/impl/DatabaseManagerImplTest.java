package fr.univ_amu.yearbook.dao.impl;


import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.univ_amu.yearbook.dao.exception.DatabaseManagerException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class DatabaseManagerImplTest {
	
	@Autowired
	DatabaseManagerImpl connManager;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNewConnection() throws DatabaseManagerException {
		Connection conn = connManager.newConnection();
		assertTrue("Connection is null", conn != null);
		assertTrue(connManager.getNumActiveConnection() == 1);
		assertTrue(connManager.getNumIdleConnection() == 4);
		
		Connection conn2 = connManager.newConnection();
		assertTrue("Connection is null", conn2 != null);
		assertTrue(connManager.getNumActiveConnection() == 2);
		assertTrue(connManager.getNumIdleConnection() == 3);
		
		connManager.closeConneection(conn2);
		assertTrue(connManager.getNumActiveConnection() == 1);
		assertTrue(connManager.getNumIdleConnection() == 4);
	}

	@Test
	public void testCloseConneection() throws DatabaseManagerException {
		Connection conn = connManager.newConnection();
		assertTrue("Connection is null", conn != null);
		assertTrue(connManager.getNumActiveConnection() == 1);
		assertTrue(connManager.getNumIdleConnection() == 4);
		
		connManager.closeConneection(conn);
		assertTrue(connManager.getNumActiveConnection() == 0);
		assertTrue(connManager.getNumIdleConnection() == 5);
	}

}
