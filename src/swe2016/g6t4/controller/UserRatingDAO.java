package swe2016.g6t4.controller;

import java.util.ArrayList;
import java.util.UUID;

import swe2016.g6t4.model.User;
import swe2016.g6t4.model.UserRating;

/**
* Das Interface UserRatingDAO deklariert generische Methoden zum Einlesen und Speichern von UserRating-Objekten. 
* Klassen, die das Interface verwenden, muessen diese Methoden implementieren.
* @author  Team 4 (Gruppe 6)
* @version 1.0
* @since   2016-12-01 
*/
public interface UserRatingDAO {
	
	/**
	 * getUserRatingList() gibt die Liste aller UserRatings zurueck. 
	 * @return ArrayList Liste aller UserRatings.
	 */
	public ArrayList<UserRating> getUserRatingList(); 

	/**
	 * getUserRatingById(UUID id) sucht anhand der UserRating-ID ein UserRating-Objekt und gibt dieses zurueck. 
	 * @param id (als UUID) die ID des gesuchten Objekts
	 * @return UserRating anhand der ID gefundenes UserRating (wenn ID nicht auffindbar, dann wird null zurueckgegeben bzw. 
	 * eine Exception geworfen, dass das UserRating nicht existiert).
	 */
	public UserRating getUserRatingById(UUID id);
	
	/**
	 * getUserRatingByRatedUser(UUID ratedUser) sucht alle UserRatings, die fuer ratedUser erstellt wurden, und gibt diese zurueck.
	 * @param user (als UUID)
	 * @return ArrayList Liste der Ratings fuer den uebergebenen ratedUser (wenn ratedUser nicht auffindbar, dann wird eine leere
	 * Liste zurueckgegeben).
	 */
	public ArrayList<UserRating> getUserRatingByRatedUser(UUID user);  
	
	/**
	 * getUserRatingByRatingUser(UUID ratingUser) sucht alle UserRatings, die von ratingUser erstellt wurden, und gibt diese zurueck. 
	 * @param ratingUser (als UUID)
	 * @return ArrayList Liste der von ratingUser erstellten UserRatings (wenn ratingUser nicht auffindbar, dann wird eine leere
	 * Liste zurueckgegeben).
	 */
	public ArrayList<UserRating> getUserRatingByRatingUser(UUID ratingUser); 

	
	/**
	 * addUserRating(UserRating userrating) fuegt ein neues UserRating hinzu. Falls ein UserRating mit der gewuenschten ID schon existiert, wird
	 * eine Exception geworfen. 
	 * @param userrating (als UserRating)
	 */
	public void addUserRating(UserRating newrating) throws IllegalArgumentException;  
	
	/**
	 * deleteUserRating(UserRating userrating) loescht das uebergebene UserRating. Falls das UserRating anhand seiner ID nicht auffindbar ist, wird 
	 * eine Exception geworfen. 
	 * @param userrating (als UserRating)
	 */
	public void deleteUserRating(UserRating ratingToDelete) throws IllegalArgumentException;

	
}
