package main.java.fr.univ_amu.yearbook.bus.personManager.impl;

import java.util.Collection;

import main.java.fr.univ_amu.yearbook.bean.Person;
import main.java.fr.univ_amu.yearbook.bus.personManager.exception.PersonManagerException;
import main.java.fr.univ_amu.yearbook.interfaces.IPersonManager;

public class PersonManager implements IPersonManager {

	@Override
	public Person findPerson(long id) throws PersonManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Person> findAllPersons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdatePerson(Person p) throws PersonManagerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePerson(long id) throws PersonManagerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePerson(Person p) throws PersonManagerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAllPersons() {
		// TODO Auto-generated method stub

	}
}
