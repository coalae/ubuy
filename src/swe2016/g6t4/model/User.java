package swe2016.g6t4.model;

import java.io.Serializable;
import java.util.UUID;

/**
* Die Klasse User implementiert Serializable. 
* Auf die privaten Instanzvariablen kann mit public Get- und Set-Methoden zugegriffen werden kann. Zusaetzlich gibt es eine SerialVersionUID.
* Die Klasse hat nur Setter und Getter, keine weiteren Methoden.  
* @author  Team 4 (Gruppe 6)
* @version 1.0
* @since   2016-30-11 
*/
public class User implements Serializable {

	/**
	 * Die Instanzvariablen des User-Objekts: username (String), password (String), firstname (String), lastname (String), 
	 * usertype (int), id (UUID), active (boolean).
	 * Wegen dem implement Serializable gibt es auch eine serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	private String username; 
	private String password; 
	private String firstname; 
	private String lastname; 
	private int usertype; 
	private UUID id; 
	private boolean active;
	
	/**
	 * Der Konstruktor User hat als Parameter die Instanzvariablen und setzt diese mittels Set-Methoden.
	 * @param username Name des Users (String).
	 * @param password Passwort des Users (String).
	 * @param firstname Vorname des Users (String).
	 * @param lastname Nachname des Users (String).
	 * @param usertype Typ des Users (normaler User0, Admin1, Forscher2) (int).
	 * @param id ID des Users (UUID).
	 * @param active True wenn User aktiv, false wenn User gesperrt (boolean).
	 */	
	public User(String username, String password, String firstname, String lastname, int usertype, UUID id, boolean active) {
		setUsername(username);
		setPassword(password);
		setFirstname(firstname);
		setLastname(lastname);
		setUsertype(usertype);
		setId(id);
		setActive(active);
	}
	
	//  Getter & Setter:
	/**
	 * Get-Methode fuer Instanzvariable username.
	 * @return username des Users (String).
	 */
	public String getUsername() {
		return username;
	}	
	
	/**
	 * Set-Methode fuer Instanzvariable username. 
	 * @param username Username des Users (String). Der übergebene Username darf nicht leer sein (wird hier überprüft) 
	 * und darf nur von einem User-Objekt verwendet werden (wird in SerializedUserDAO geprüft bei addUser).
	 */
	public void setUsername(String username) {
		if (username==null || username.isEmpty()) {
			throw new IllegalArgumentException("USERNAME darf nicht NULL oder LEER sein!");
		}
		this.username = username;
	}
	
	/**
	 * Get-Methode fuer Instanzvariable password.
	 * @return password Passwort des Users (String).
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Set-Methode fuer Instanzvariable password. 
	 * @param password Passwort des Users (String). Das übergebene Passwort darf nicht leer sein.
	 */
	public void setPassword(String password) {
		if (password==null || password.isEmpty()) {
			throw new IllegalArgumentException("PASSWORD darf nicht NULL oder LEER sein!");
		}	
		this.password = password;
	}
	
	/**
	 * Get-Methode fuer Instanzvariable firstname.
	 * @return firstname Vorname des Users (String).
	 */
	public String getFirstname() {
		return firstname;
	}
	
	/**
	 * Set-Methode fuer Instanzvariable firstname. 
	 * @param firstname Vorname des Users (String). Der übergebene Vorname darf nicht leer sein.
	 */
	public void setFirstname(String firstname) {
		if (firstname==null || firstname.isEmpty()) {
			throw new IllegalArgumentException("FIRSTNAME darf nicht NULL oder LEER sein!");
		}
		this.firstname = firstname;
	}
	
	/**
	 * Get-Methode fuer Instanzvariable lastname.
	 * @return lastname Nachname des Users (String). 
	 */
	public String getLastname() {
		return lastname;
	}
	
	/**
	 * Set-Methode fuer Instanzvariable lastname. 
	 * @param lastname Nachname des Users (String). Der übergebene Nachname darf nicht leer sein.
	 */
	public void setLastname(String lastname) {
		if (lastname==null || lastname.isEmpty()) {
			throw new IllegalArgumentException("LASTNAME darf nicht NULL oder LEER sein!");
		}		
		this.lastname = lastname;
	}
	
	/**
	 * Get-Methode fuer Instanzvariable usertype.
	 * @return usertype Usertype des Users (int) (normaler User 0, Admin 1, Forscher 2).
	 */
	public int getUsertype() {
		return usertype;
	}
	
	/**
	 * Set-Methode fuer Instanzvariable usertype. 
	 * @param usertype Usertype des Users (int) (normaler User 0, Admin 1, Forscher 2). Der übergebene usertype 
	 * darf nur 0 oder 1 oder 2 sein.
	 */
	public void setUsertype(int usertype) {
		if(usertype==0 || usertype==1 || usertype==2) {
			this.usertype = usertype;
		}
		else {
			throw new IllegalArgumentException("USERTYPE darf nur die Werte 0 (normaler User), 1 (Admin) oder 2 (Forscher) annehmen.");
		}
	}
	
	/**
	 * Get-Methode fuer Instanzvariable id.
	 * @return ID des Users (UUID).
	 */
	public UUID getId() {
		return id;
	}
	
	/**
	 * Set-Methode fuer Instanzvariable id. 
	 * @param id ID des Users (UUID).  
	 */
	public void setId(UUID id) {
		this.id = id;
	}
	
	/**
	 * Get-Methode fuer Instanzvariable active.
	 * @return active True wenn User active ist, false wenn User gesperrt ist (boolean).
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * Set-Methode fuer Instanzvariable active. 
	 * @param active True wenn User active ist, false wenn User gesperrt ist (boolean).
	 */
	public void setActive(boolean active) {
		this.active = active;
	} 
	
}
