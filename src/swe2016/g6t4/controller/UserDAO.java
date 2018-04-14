package swe2016.g6t4.controller;
import java.util.ArrayList;
import java.util.UUID;

import swe2016.g6t4.model.User;

/**
* Das Interface UserDAO deklariert generische Methoden zum Einlesen und Speichern von User-Objekten. 
* Klassen, die das Interface verwenden, muessen diese Methoden implementieren.
* @author  Team 4 (Gruppe 6)
* @version 1.0
* @since   2016-12-01 
*/
public interface UserDAO {
	
	/**
	 * getUserList() gibt die Liste aller User zurueck. 
	 * @return ArrayList Liste aller User.
	 */
	public ArrayList<User> getUserList(); 
	
	/**
	 * getUserById(UUID id) sucht anhand der User-ID ein User-Objekt und gibt dieses zurueck. 
	 * @param id (als UUID)
	 * @return User anhand der ID gefundener User (wenn ID nicht auffindbar, dann wird null zurueckgegeben bzw. 
	 * eine Exception geworfen, dass der User nicht existiert).
	 */
	public User getUserById(UUID id);
	
	/**
	 * getUserByUsername(String username) sucht anhand des Username (unique) ein User-Objekt und gibt dieses zurueck. 
	 * @param username (als String)
	 * @return User anhand der ID gefundener User (wenn username nicht auffindbar ist, dann wird null zurueckgegeben bzw. 
	 * eine Exception geworfen, dass der User nicht existiert).
	 */
	public User getUserByUsername(String username);  // hinzugefuegt fuer Check, ob username schon belegt ist (muss noch im klassendigramm eingetragen werden)
	
	/**
	 * addUser(User user) fuegt einen neuen User hinzu. Falls ein User mit der gewuenschten ID oder dem gewuenschten username schon 
	 * existiert, wird eine Exception geworfen. 
	 * @param user (als User)
	 * @throws IllegalArgumentException wenn ein ungueltiges User-Objekt uebergeben wird
	 */
	public void addUser(User user) throws IllegalArgumentException;  
	
	/**
	 * deleteUser(User user) loescht das uebergebene User-Objekt. Falls der User anhand seiner ID nicht auffindbar ist, wird eine
	 * Exception geworfen. 
	 * @param user (als User)
	 * @throws IllegalArgumentException wenn ein ungueltiges User-Objekt uebergeben wird
	 */
	public void deleteUser(User user) throws IllegalArgumentException;
	
	/**
	 * modifyUserPassword(User user, String password) weist dem password eines bestimmten Users den uebergebenen String zu. Falls das uebergebene 
	 * password leer oder der User nicht auffindbar ist, wird nichts geaendert und es wird eine Exception geworfen.
	 * @param password (als String)
	 * @throws IllegalArgumentException wenn ungueltige Objekte uebergeben werden
	 */
	public void modifyUserPassword(User user, String password) throws IllegalArgumentException;
}
