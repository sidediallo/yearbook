package fr.univ_amu.yearbook.dao.impl;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.univ_amu.yearbook.bean.Group;
import fr.univ_amu.yearbook.dao.exception.DatabaseManagerException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class ResultSetToBeanImplTest {
	@Autowired
	DatabaseManagerImpl connManager;
	Connection conn;
	ResultSetToBeanImpl<Group> rsTobean;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		conn = connManager.newConnection();
		rsTobean = new ResultSetToBeanImpl<>(Group.class);
	}

	@After
	public void tearDown() throws Exception {
		rsTobean = null;
		connManager.closeConneection(conn);
	}

	@Test
	public void testToBean() throws DatabaseManagerException, SQLException {
		PreparedStatement pstm = conn.prepareStatement("INSERT INTO YEARBOOK_Group values(?,?)",Statement.RETURN_GENERATED_KEYS);
		pstm.setObject(1, null);
		Timestamp timestamp =  new Timestamp(System.currentTimeMillis());
		String groupName = "Group "+timestamp.toString();
		pstm.setString(2, groupName);
		pstm.executeUpdate();
		ResultSet pkrs = pstm.getGeneratedKeys();
		
		pkrs.next();
		Long groupId = pkrs.getLong(1);
		
		PreparedStatement pstm2 = conn.prepareStatement("SELECT * FROM YEARBOOK_Group WHERE id = ?");
		pstm2.setLong(1, groupId);
		
		ResultSet rs = pstm2.executeQuery();
		
		rs.next();
		Group group = rsTobean.toBean(rs);
		
		
		assertTrue(groupId == group.getId());
		assertTrue(groupName.equals(group.getName()));
		
		//Cleaning database
		PreparedStatement removeQuery = conn.prepareStatement("DELETE FROM yearbook_group where id = ?;");
		removeQuery.setObject(1, group.getId());
		removeQuery.executeUpdate();
	}

}
