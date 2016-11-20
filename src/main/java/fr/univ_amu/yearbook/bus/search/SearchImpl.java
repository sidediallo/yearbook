package fr.univ_amu.yearbook.bus.search;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("searchService")
@Primary

/**
 * The SearchImpl class is an implementation of  the interface {@link fr.univ_amu.yearbook.bus.search.ISearch ISearch}.
 * 
 * @author ZONGO
 * @version 1.0
 *
 * @param <T> parameter type on which the interface operations should be applied.
 */
public class SearchImpl<T> implements ISearch<T> {
	
	/**
	 * Default constructor.
	 */
	public SearchImpl() {
		
	}
	
	@PostConstruct
	public void init(){
		
	}
	
	@PreDestroy
	public void close(){
		
	}

	@Override
	public Collection<T> filter(Collection<T> objects, IPredicate<T> predicate) {
		ArrayList<T> filteredObjects = new ArrayList<>();
		for(T object : objects){
			if(predicate.apply(object)) filteredObjects.add(object);
		}
		return filteredObjects;
	}

}
