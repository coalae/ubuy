package swe2016.g6t4.controller;

import java.util.ArrayList;
import java.util.UUID;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

import swe2016.g6t4.model.UserRating;

/**
* Das Interface UserRatingDAO deklariert generische Methoden zum Einlesen und Speichern von UserRating-Objekten. 
* Klassen, die das Interface verwenden, muessen diese Methoden implementieren.
* @author  Team 4 (Gruppe 6)
* @version 1.0
* @since   2016-12-01 
*/
public class SerializedUserRatingDAO implements UserRatingDAO, Serializable {

	private static final long serialVersionUID = 1L;
	// instanzvariable
	/**Der Name der Datei, in der die UserRating-Objekte gespeichert werden sollen.**/
	private String dateinamepfad;
	/**Eine Liste von UserRating-Objekten, die verwendet wird, um die gespeicherten UserRatings zu verwalten.**/
	private ArrayList<UserRating> speicherliste; 
	
	// constructor (speicherung in userratings.ser)
	/**Im Konstruktor wird der Name der Datei, in der die UserRating-Objekte gespeichert werden sollen, festgelegt sowie der Instanzvariable
	 * eine leere Liste zugewiesen. Falls die Datei noch nicht existiert, wird sie angelegt.**/
	public SerializedUserRatingDAO() {
		this.dateinamepfad="userratings.ser"; 
		speicherliste=new ArrayList<UserRating>();
	}
		
	// implementierte methoden des interface
	// getUserRatingList-methode
	/**
	 * getUserRatingList() gibt die Liste aller UserRatings zurueck. 
	 * @return ArrayList Liste aller UserRatings.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<UserRating> getUserRatingList() { // Liste (z. B. java.util.ArrayList) benutzen, gespeicherten UserRating-Objekten return
		File datei = new File(dateinamepfad);
		if(datei.exists() && datei.canRead()) {
			try {
			FileInputStream fileinput = new FileInputStream(dateinamepfad);
			ObjectInputStream objectinput = new ObjectInputStream(fileinput);
			
			if(objectinput!=null) {
				speicherliste = (ArrayList<UserRating>) objectinput.readObject(); 
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
	
	// getUserRatingbyId-methode
	/**
	 * getUserRatingById(UUID id) sucht anhand der UserRating-ID ein UserRating-Objekt und gibt dieses zurueck. 
	 * @param id (als UUID) die ID des gesuchten Objekts
	 * @return UserRating anhand der ID gefundenes UserRating (wenn ID nicht auffindbar, dann wird null zurueckgegeben bzw. 
	 * eine Exception geworfen, dass das UserRating nicht existiert).
	 */
	@Override
	public UserRating getUserRatingById(UUID id) {    // anhand der UserRatingUUID, ein UserRating-Objekt zur�ckgeben. 
		if(id==null) {
			throw new IllegalArgumentException("�bergebene USERRATINGID war NULL.");
		}
		
		ArrayList<UserRating> idsuchliste = getUserRatingList();
		
		for(int i=0; i<idsuchliste.size();i++) {
			if(idsuchliste.get(i).getId().equals(id)) {
				return idsuchliste.get(i);
			}
		}
		
		throw new IllegalArgumentException("�bergebene USERRATINGID ist nicht vorhanden."); 
		// Handling im Servlet in try-catch-clause notwendig!
	}
	
	/**
	 * getUserRatingByRatedUser(UUID ratedUser) sucht alle UserRatings, die fuer ratedUser erstellt wurden, und gibt diese zurueck.
	 * @param ratedUser (als UUID)
	 * @return ArrayList Liste der Ratings fuer den uebergebenen ratedUser (wenn ratedUser nicht auffindbar, dann wird eine leere
	 * Liste zurueckgegeben).
	 */
	@Override 
	public ArrayList<UserRating> getUserRatingByRatedUser(UUID ratedUser){
		if(ratedUser==null) {
			throw new IllegalArgumentException("�bergebene RATEDUSERID war NULL.");
		}
		
		ArrayList<UserRating> idsuchliste = getUserRatingList();
		ArrayList<UserRating> ratingsByRatedUserId = new ArrayList<UserRating>(); 
		
		for(int i=0; i<idsuchliste.size();i++) {
			if(idsuchliste.get(i).getRatedUserId().equals(ratedUser)) {
				ratingsByRatedUserId.add(idsuchliste.get(i));
			}
		}
		return ratingsByRatedUserId;
	}
	
	/**
	 * getUserRatingByRatingUser(UUID ratingUser) sucht alle UserRatings, die von ratingUser erstellt wurden, und gibt diese zurueck. 
	 * @param ratingUser (als UUID)
	 * @return ArrayList Liste der von ratingUser erstellten UserRatings (wenn ratingUser nicht auffindbar, dann wird eine leere
	 * Liste zurueckgegeben).
	 */
	@Override 
	public ArrayList<UserRating> getUserRatingByRatingUser(UUID ratingUser){
		if(ratingUser==null) {
			throw new IllegalArgumentException("�bergebene RATINGUSERID war NULL.");
		}
		
		ArrayList<UserRating> idsuchliste = getUserRatingList();
		ArrayList<UserRating> ratingsByRatingUserId = new ArrayList<UserRating>(); 
		
		for(int i=0; i<idsuchliste.size();i++) {
			if(idsuchliste.get(i).getRatingUserId().equals(ratingUser)) {
				ratingsByRatingUserId.add(idsuchliste.get(i));
			}
		}
		return ratingsByRatingUserId;
	}
	
		
	// addUserRating-methode
	/**
	 * addUserRating(UserRating userrating) fuegt ein neues UserRating hinzu. Falls ein UserRating mit der gewuenschten ID schon existiert, wird
	 * eine Exception geworfen. 
	 * @param userrating (als UserRating)
	 * @throws IllegalArgumentException falls ein Auction-Objekt mit einer bereits verwendeten ID uebergeben wurde
	 */
	@Override
	public void addUserRating(UserRating newrating) {  // soll ein UserRating persistent speichern
		
		if(newrating==null || newrating.equals("")) {
			throw new IllegalArgumentException ("Das �bergebene UserRating ist NULL, daher keine Speicherung m�glich!");
		}
			
		for(int i=0; i<getUserRatingList().size();i++){
			if ( (getUserRatingList().get(i).getId().equals(newrating.getId())) ){
				throw new IllegalArgumentException("Das zum Speichern uebergebene UserRating existiert schon!");
			}
		}

			ArrayList<UserRating> neueliste = new ArrayList<UserRating>();
			neueliste.addAll(getUserRatingList());
			neueliste.add(newrating);	
			
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
	
	// deleteUserRating-methode
	/**
	 * deleteUserRating(UserRating ratingToDelete) loescht uebergebenes UserRating. Falls das UserRating-Objekt anhand seiner UUID nicht auffindbar ist, wird eine
	 * Exception geworfen. 
	 * @param ratingToDelete (als UserRating)
	 * @throws IllegalArgumentException falls das zu loeschende UserRating-Objekt nicht gefunden werden kann
	 */
	@Override
	public void deleteUserRating(UserRating ratingToDelete) {  // soll ein UserRating von der persistenten Datenquelle l�schen
        	
		if(ratingToDelete==null || ratingToDelete.equals("")) {
			throw new IllegalArgumentException ("Das �bergebene UserRating ist NULL, daher keine L�schung m�glich!");
		}
		
		boolean checkRatingToDeleteExists = false;
		for(int i=0; i<getUserRatingList().size();i++){
			if ( (getUserRatingList().get(i).getId().equals(ratingToDelete.getId())) ){
				checkRatingToDeleteExists=true;
			}
		}
		
		if (checkRatingToDeleteExists) {
			ArrayList<UserRating> neueliste = new ArrayList<UserRating>();
			neueliste.addAll(this.getUserRatingList());
			
			for(int i=0; i<neueliste.size();i++) {
				if(neueliste.get(i).getId().equals(ratingToDelete.getId())) {
					neueliste.remove(neueliste.get(i));	 
					break;
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
			throw new IllegalArgumentException ("Das zum Loeschen uebergebene UserRating existiert nicht!");
		}		
	}
}
	