package main.java.fr.univ_amu.yearbook.bean;

import java.sql.Date;

/**
 * <b>Person</b> est la classe qui permet de créer une personne
 * de l'annuaire.
 * 
 * <p>
 * Une personne de l'annuaire est caractérisée par :
 * <ul>
 * <li>Un identifiant unique.</li>
 * <li>Un nom.</li>
 * <li>Un prénom.</li>
 * <li>Un email unique.</li>
 * <li>Un site web unique.</li>
 * <li>Une date de naissance.</li>
 * <li>Un mot de passe.</li>
 * <li>L'identifiant d'un groupe.</li>
 * </ul>
 * </p>
 * 
 * @see java.sql.Date
 * 
 * @author Aboubacar Sidy DIALLO & Inoussa ZONGO
 * @version 1.0
 *
 */
public class Person {
	
	/**
	 * L'identifiant d'une personne. Cet id n'est pas modifiable.
	 * 
	 * @see Person#getId()
	 * @see Person#setId(long)
	 */
	private long id;
	
	/**
	 * Le nom d'une personne
	 * 
	 * @see Person#getLastName()
	 * @see Person#setLastName(String)
	 */
	private String lastName;
	
	/**
	 * Le prénom d'une personne.
	 * 
	 * @see Person#getFirstName()
	 * @see Person#setFirstName(String)
	 */
	private String firstName;
	
	/**
	 * L'email d'une personne.
	 * 
	 * @see Person#getEmail()
	 * @see Person#setEmail(String)
	 */
	private String email;
	
	/**
	 * Le site web d'une personne
	 * 
	 * @see Person#getHomePage()
	 * @see Person#setHomePage(String)
	 */
	private String homePage;
	
	/**
	 * La date de naissance d'une personne
	 * 
	 * @see Person#getBirthDate()
	 * @see Person#setBirthDate(Date)
	 */
	private Date birthDate;
	
	/**
	 * Le mot de passe d'une personne
	 * 
	 * @see Person#getPwd()
	 * @see Person#setPwd(String)
	 */
	private String pwd;
	
	/**
	 * L'identifiant du groupe auquel appartient la personne.
	 * 
	 * @see Person#getIdG()
	 * @see Person#setIdG(long idG)
	 */
	private long idG;
	
	/**
	 * Constructeur par défaut de la classe Person.
	 */
	public Person() {
		super();
	}
	
	/**
	 * Retourne l'id de la personne.
	 * Cet attribut ne peut être modifié.
	 * 
	 * @return 
	 * 		L'identifiant.
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Met à jour l'id de la personne.
	 * 
	 * @param id Le nouveau id.
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Retourne le prénom de la personne.
	 * 
	 * @return Le prénom.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Met à jour le prénom de la personne.
	 * 
	 * @param lastName Le nouveau prénom.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Retourne le nom de la personne.
	 * 
	 * @return Le nom.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Met à jour le nom de la personne.
	 * 
	 * @param firstName Le nouveau nom.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Retourne l'email de la personne.
	 * 
	 * @return L'email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Met à jour l'email de la personne.
	 * Cet email est unique.
	 * 
	 * @param email Le nouveau mail.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Retourne le site web de le personne.
	 * 
	 * @return Le site web.
	 */
	public String getHomePage() {
		return homePage;
	}

	/**
	 * Met à jour le site web de la personne.
	 * Cet site web est unique.
	 * 
	 * @param webSite Le nouveau site web.
	 */
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	
	/**
	 * Retourne la date de naissance de la personne.
	 * 
	 * @return La date de naissance.
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * Met à jour la date de naissance de la personne.
	 * 
	 * @param dateOfBirth La nouvelle date de naissance.
	 */
	public void setBirthDate(Date dateOfBirth) {
		this.birthDate = dateOfBirth;
	}

	/**
	 * Retourne le mot de passe de la personne.
	 * 
	 * @return Le mot de passe. 
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * Met à jour le mot de passe de la personne.
	 * 
	 * @param pwd Le nouveau mot de passe.
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * Retourne le groupe auquel appartient la personne.
	 * 
	 * @return L'identifiant du groupe auquel la persenne appartient.
	 */
	public long getIdG() {
		return idG;
	}

	/**
	 * Met à jour le numero du groupe auquel appartient la personne.
	 * 
	 * @param group Le nouveau groupe.
	 */
	public void setIdG(long idG) {
		this.idG = idG;
	}
}