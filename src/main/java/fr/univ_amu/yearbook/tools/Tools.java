package main.java.fr.univ_amu.yearbook.tools;

import java.util.ResourceBundle;

/**
 * <b>Tools</b> est la classe qui permet d'initialiser
 * les paramètres de connection à une base de données.
 * 
 * <p>
 * Tools est caractérisée par :
 * <ul>
 * <li>Un pilote.</li>
 * <li>Une url.</li>
 * <li>Un identifiant.</li>
 * <li>Un mot de passe.</li>
 * </ul>
 * </p>
 * 
 * @author Aboubacar Sidy DIALLO & Inoussa ZONGO
 * @version 1.0
 *
 */
public class Tools {
	
	/**
	 * Le pilote JDBC.
	 * 
	 * @see Tools#getPilote()
	 * @see Tools#setPilote(String)
	 */
	private String pilote;
	
	/**
	 * L'url de connexion à la base de données.
	 * 
	 * @see Tools#getUrl()
	 * @see Tools#setUrl(String)
	 */
	private String url;
	
	/**
	 * Le nom du proprétaire de la base.
	 * 
	 * @see Tools#getUser()
	 * @see Tools#setUser(String)
	 */
	private String user;
	
	/**
	 * Le mot de passe du propriétaire.
	 * 
	 * @see Tools#getPwd()
	 * @see Tools#setPwd(String)
	 */
	private String pwd;
	
	/**
	 * Constructeur par défaut de la classe Tools.
	 */
	public Tools() {
		super();
	}
	
	/**
	 * Initialise les paramètres de connection.
	 */
	public void init() {
		ResourceBundle smtp = ResourceBundle.getBundle("smtp");
		
		setPilote(smtp.getString("driver"));
		setUrl(smtp.getString("url"));
		setUser(smtp.getString("login"));
		setPwd(smtp.getString("password"));
	}
	
	/**
	 * Retourne le pilote de connection.
	 * 
	 * @return 
	 * 		Le pilote.
	 */
	public String getPilote() {
		return pilote;
	}
	
	/**
	 * Met à jour le pilote de connection.
	 * 
	 * @param pilote Le pilote.
	 */
	public void setPilote(String pilote) {
		this.pilote = pilote;
	}

	/**
	 * Retourne l'url de connection.
	 * 
	 * @return 
	 * 		L'url.
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Met à jour l'url de connection.
	 * 
	 * @param url L'url de connection.
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Retourne l'utilisateur de la session.
	 * 
	 * @return 
	 * 		L'utilisateur.
	 */
	public String getUser() {
		return user;
	}
	
	/**
	 * Met à jour l'utilisateur.
	 * 
	 * @param user L'utilisateur.
	 */
	public void setUser(String user) {
		this.user = user;
	}
	
	/**
	 * Retourne le mot de passe de l'utilisateur de la session.
	 * 
	 * @return 
	 * 		Le mot de passe.
	 */
	public String getPwd() {
		return pwd;
	}
	
	/**
	 * Met à jour le mot de passe de l'utilisateur.
	 * 
	 * @param password Le nouveau mot de passe.
	 */
	public void setPwd(String password) {
		this.pwd = password;
	}
}