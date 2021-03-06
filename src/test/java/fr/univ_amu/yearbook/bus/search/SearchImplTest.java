package fr.univ_amu.yearbook.bus.search;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.univ_amu.yearbook.bean.Group;
import fr.univ_amu.yearbook.dao.IGroupDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/spring.xml"})
public class SearchImplTest {
	
	@Autowired
	IGroupDAO groupDAO;
	
	@Autowired
	ISearch<Group> searcher;

	
	@Test
	public void testFilter() {
		Collection<Group> groups = groupDAO.find();
		Collection<Group> filteredGroups =  searcher.filter(groups, (Group object) ->{
				if(object.getName().contains("FED")) return true;
				else return false;
			}
		);
		assertTrue(filteredGroups.size() == 1); 
		
		Collection<Group> filteredGroups2 = searcher.filter(groups, (Group object) -> {
				if(object.getName().length()==16) return true;
				else return false;
			}
		);
		assertTrue(filteredGroups2.size() == 6);
	}

}
