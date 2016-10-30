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
	ResultSetToBeanImpl<Group> rsTobean;
	
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
	public void testToBean() throws DatabaseManagerException, SQLException {
		Connection conn = connManager.newConnection();
		PreparedStatement pstm = conn.prepareStatement("INSERT INTO groupe values(?,?)",Statement.RETURN_GENERATED_KEYS);
		pstm.setObject(1, null);
		Timestamp timestamp =  new Timestamp(System.currentTimeMillis());
		String groupName = "Group "+timestamp.toString();
		pstm.setString(2, groupName);
		pstm.executeUpdate();
		ResultSet pkrs = pstm.getGeneratedKeys();
		
		pkrs.next();
		Integer groupId = pkrs.getInt(1);
		
		PreparedStatement pstm2 = conn.prepareStatement("SELECT * FROM groupe WHERE id = ?");
		pstm2.setInt(1, groupId);
		
		ResultSet rs = pstm2.executeQuery();
		
		rsTobean = new ResultSetToBeanImpl<>(Group.class);
		rs.next();
		Group group = rsTobean.toBean(rs);
		
		
		assertTrue(groupId == group.getId());
		assertTrue(groupName.equals(group.getName()));
		
		connManager.closeConneection(conn);
	}

}
