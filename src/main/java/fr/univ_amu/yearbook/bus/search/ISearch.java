package fr.univ_amu.yearbook.bus.search;

import java.util.Collection;

/**
 * The ISearch interface provides an operation to filter a collection of objects on {@link fr.univ_amu.yearbook.bus.search.IPredicate criteria}.
 * 
 * @author ZONGO
 * @version 1.0
 *
 * @param <T> parameter type on which the interface operations should be applied.
 */
public interface ISearch<T> {
	
	/**
	 * filters a collection of objects according criteria.
	 * 
	 * @param objects the collection to be filtered.
	 * @param predicate defines the critria on which the collection should be filtered.
	 * @return the filtered collection.
	 */
	public Collection<T> filter(Collection<T> objects, IPredicate<T> predicate);
}
