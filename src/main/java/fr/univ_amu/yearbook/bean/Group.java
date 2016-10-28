package fr.univ_amu.yearbook.bean;

/**
 * 
 * The Group class is a bean that reperesent a group<br> 
 * It describes a group with some attributes and represents a group from datasource.
 * @author ZONGO
 * @version 1.0
 */
public class Group {
	/**
	 * The identifier of this group.
	 */
	private int id;
	
	/**
	 * The name of this group.
	 */
	private String name;
	
	/**
	 * Group empty constructor.
	 */
	public Group(){
		
	}

	/**
	 * Returns the identifier of this Group.
	 * @return the identifier of this Group.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the value in parameter as the identifier of this Group.
	 * @param id the identifier for the Group.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the name of this Group.
	 * @return the name of this Group.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value in parameter as the name of this Group.
	 * @param name the name for this this Group.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
