package swe2016.g6t4.controller;

import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import swe2016.g6t4.model.User;

public class SerializedUserDAO implements UserDAO, Serializable {

	private static final long serialVersionUID = 1L;
	// instanzvariable
	/**Der Name der Datei, in der die Liste der User gespeichert werden soll.**/
	private String dateinamepfad;
	/**Eine Liste von User-Objekten, die verwendet wird, um die gespeicherten User zu verwalten.**/
	private ArrayList<User> speicherliste; 
	
	// constructor (speicherung in userlist.ser)
	/**Im Konstruktor wird der Name der Datei, in der die User-Objekte gespeichert werden sollen, festgelegt sowie der Instanzvariable
	 * eine leere Liste zugewiesen. Falls die Datei noch nicht existiert, wird sie angelegt.**/
	public SerializedUserDAO() {
		this.dateinamepfad="userlist.ser"; 
		speicherliste=new ArrayList<User>();
	}
	
	// implementierte methoden des interface
	// getUserList-methode
	/**
	 * getUserList() gibt die Liste aller User zurueck. 
	 * @return ArrayList Liste aller User.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<User> getUserList() { // Liste (z. B. java.util.ArrayList) benutzen, gespeicherten User-Objekten return
		File datei = new File(dateinamepfad);
		if(datei.exists() && datei.canRead()) {
			try {
			FileInputStream fileinput = new FileInputStream(dateinamepfad);
			ObjectInputStream objectinput = new ObjectInputStream(fileinput);
			
			if(objectinput!=null) {
				speicherliste = (ArrayList<User>) objectinput.readObject(); 
				objectinput.close();
				fileinput.close();   
			}
			
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return speicherliste;
	}
	
	// getUserbyId-methode
	/**
	 * getUserById(UUID id) sucht anhand der User-ID ein User-Objekt und gibt dieses zurueck. 
	 * @param id (als UUID)
	 * @return User anhand der ID gefundener User (wenn ID nicht auffindbar, dann wird null zurueckgegeben bzw. 
	 * eine Exception geworfen, dass der User nicht existiert).
	 */
	@Override
	public User getUserById(UUID id) {    // anhand der UserUUID, ein User-Objekt zurückgeben. 
		if(id==null) {
			throw new IllegalArgumentException("Übergebene USERID war NULL.");
		}
		
		ArrayList<User> idsuchliste = getUserList();
		
		for(int i=0; i<idsuchliste.size();i++) {
			if(idsuchliste.get(i).getId().equals(id)) {
				return idsuchliste.get(i);
			}
		}
		
		// Handling im Servlet in try-catch-clause notwendig!
		throw new IllegalArgumentException("Übergebene USERID ist nicht vorhanden."); 
	}
	
	/**
	 * getUserByUsername(String username) sucht anhand des Username (unique) ein User-Objekt und gibt dieses zurueck. 
	 * @param username (als String)
	 * @return User anhand der ID gefundener User (wenn username nicht auffindbar ist, dann wird null zurueckgegeben bzw. 
	 * eine Exception geworfen, dass der User nicht existiert).
	 */
	public User getUserByUsername(String username){  // hinzugefuegt fuer Check, ob username schon belegt ist (muss noch im klassendigramm eingetragen werden)
		if(username==null || username.isEmpty() || username.equals("")) {
			throw new IllegalArgumentException("Übergebene USERID war NULL.");
		}
		
		ArrayList<User> suchliste = getUserList();
		
		for(int i=0; i<suchliste.size();i++) {
			if(suchliste.get(i).getUsername().equals(username)) {
				return suchliste.get(i);
			}
		}
		
		// Handling im Servlet in try-catch-clause notwendig!
		throw new IllegalArgumentException("Übergebener USERNAME ist nicht vorhanden.");	
	}
	
	// addUser-methode
	/**
	 * addUser(User user) fuegt einen neuen User hinzu. Falls ein User mit der gewuenschten ID oder dem gewuenschten username schon 
	 * existiert, wird eine Exception geworfen. 
	 * @param user (als User)
	 * @throws IllegalArgumentException wenn ein ungueltiges User-Objekt uebergeben wird
	 */
	@Override
	public void addUser(User newuser) {  // soll einen User persistent speichern
		
		if(newuser==null) {
			throw new IllegalArgumentException ("Der übergebene User ist NULL, daher keine Speicherung möglich!");
		}
					
		for(int i=0; i<getUserList().size();i++){
			if ( (getUserList().get(i).getId().equals(newuser.getId())) || (getUserList().get(i).getUsername().equals(newuser.getUsername())) ){
				throw new IllegalArgumentException("User kann nicht gespeichert werden - ID oder USERNAME wird bereits verwendet.");
			}
		}

			ArrayList<User> neueliste = new ArrayList<User>();
			neueliste.addAll(getUserList());
			neueliste.add(newuser);	
			
			try {
				FileOutputStream fileoutput = new FileOutputStream(dateinamepfad);
				ObjectOutputStream objectoutput = new ObjectOutputStream(fileoutput);
				objectoutput.writeObject(neueliste);
				objectoutput.close();
				fileoutput.close();  
			} catch (FileNotFoundException e) {
					e.printStackTrace();
			} catch (IOException e) {
					e.printStackTrace();
			} 
	}		
	
	// deleteUser-methode
	/**
	 * deleteUser(User user) loescht das uebergebene User-Objekt. Falls der User anhand seiner ID nicht auffindbar ist, wird eine
	 * Exception geworfen. 
	 * @param user (als User)
	 * @throws IllegalArgumentException wenn ein ungueltiges User-Objekt uebergeben wird
	 */
	@Override
	public void deleteUser(User userToDelete) {  // soll einen User von der persistenten Datenquelle löschen
        	
		if(userToDelete==null) {
			throw new IllegalArgumentException ("Der übergebene User ist NULL, daher keine Löschung möglich!");
		}
		
		boolean checkUserToDeleteExists = false;
		for(int i=0; i<getUserList().size();i++){
			if ( (getUserList().get(i).getId().equals(userToDelete.getId())) && (getUserList().get(i).getUsername().equals(userToDelete.getUsername())) ){
				checkUserToDeleteExists=true;
			}
		}
		
		if (checkUserToDeleteExists) {
			ArrayList<User> neueliste = new ArrayList<User>();
			neueliste.addAll(this.getUserList());
			
			for(int i=0; i<neueliste.size();i++) {
				if(neueliste.get(i).getId().equals(userToDelete.getId())) {
					neueliste.remove(neueliste.get(i));	 
				}
			}
			
			try {
				FileOutputStream fileoutput = new FileOutputStream(dateinamepfad);
				ObjectOutputStream objectoutput = new ObjectOutputStream(fileoutput);
				objectoutput.writeObject(neueliste);
				objectoutput.close();
				fileoutput.close();  
			} catch (FileNotFoundException e) {
					e.printStackTrace();
			} catch (IOException e) {
					e.printStackTrace();
			} 
		}
		else {
			throw new IllegalArgumentException ("Der zum Löschen übergebene User existiert nicht!");
		}
			
	}
	
	// modifyUserPassword-methode
	/**
	 * modifyUserPassword(User user, String password) weist dem password eines bestimmten Users den uebergebenen String zu. Falls das uebergebene 
	 * password leer oder der User nicht auffindbar ist, wird nichts geaendert und es wird eine Exception geworfen.
	 * @param password (als String)
	 * @throws IllegalArgumentException wenn ungueltige Objekte uebergeben werden
	 */
	public void modifyUserPassword(User user, String password) {
	
		if ( (password==null) || (password.equals("")) ) throw new IllegalArgumentException("Ungueltiges Passwort");
		if (user==null) throw new IllegalArgumentException("Ungueltiger User - User ist NULL");

		boolean checkUserToModifyExists = false;
		for(int i=0; i<getUserList().size();i++){
			if ( (getUserList().get(i).getId().equals(user.getId())) && (getUserList().get(i).getUsername().equals(user.getUsername())) ){
				checkUserToModifyExists=true;
			}
		}
		
		if (checkUserToModifyExists) {
			ArrayList<User> neueliste = new ArrayList<User>();
			neueliste.addAll(getUserList());
			
			for(int i=0; i<neueliste.size();i++) {
				if(neueliste.get(i).getId().equals(user.getId())) {
					User userChanged=neueliste.get(i);
					userChanged.setPassword(password);
					neueliste.set(i,userChanged);
				}
			}
			
		try {
			FileOutputStream fileoutput = new FileOutputStream(dateinamepfad);
			ObjectOutputStream objectoutput = new ObjectOutputStream(fileoutput);
			objectoutput.writeObject(neueliste);
			objectoutput.close();
			fileoutput.close();  
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		} 
		}
   else {
	   	throw new IllegalArgumentException ("Der zum Passwort-Ändern uebergebene User wurde nicht gefunden!");
		}
	
	}
}


