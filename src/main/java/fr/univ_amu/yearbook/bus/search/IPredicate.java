package fr.univ_amu.yearbook.bus.search;

/**
 * The IPredicate interface defines criterion which object must sastisfy.
 * 
 * @author ZONGO
 * @version 1.0
 *
 * @param <T> parameter type on which the interface operations should be applied.
 */
public interface IPredicate<T> {
	
	/**
	 * checks if an oject satifies some criteria.
	 * 
	 * @param object the object on which the criterion is defined.
	 * @return true if the object satisfies the criterion, false if not.
	 */
	public boolean apply(T object);
}
