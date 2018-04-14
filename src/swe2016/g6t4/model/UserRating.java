package swe2016.g6t4.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;

/**
* Die Klasse UserRating implementiert Serializable. 
* Auf die privaten Instanzvariablen kann mit public Get- und Set-Methoden zugegriffen werden kann. Zusaetzlich gibt es eine SerialVersionUID.
* Die Klasse hat nur Setter und Getter, keine weiteren Methoden.  
* @author  Team 4 (Gruppe 6)
* @version 1.0
* @since   2016-30-11 
*/
public class UserRating implements Serializable {
	
	/**
	 * Die Instanzvariablen des UserRating-Objekts: id (UUID), ratedUserId (UUID), ratingUserId (UUID), ratedAuctionId (UUID), 
	 * rating (int, 1 bis 5 nach Schulnotensystem), ratingComment (String).
	 * Da die Klasse das Interface Serializable implementiert, hat sie auch eine serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	private UUID id;
	private UUID ratedUserId;
	private UUID ratingUserId;
	private Calendar dateCreated;
	private int rating;
	private String ratingComment;
	
	/**
	 * Der Konstruktor UserRating bekommt als Parameter die Instanzvariablen und setzt diese mittels Set-Methoden.
	 * @param id ID des UserRatings (UUID).
	 * @param ratedUserId ID des User, der bewertet wird (UUID).
	 * @param ratingUserId ID des User, der die Bewertung erstellt (UUID).
	 * @param ratedAuctionId ID der Auction, die bewertet wird (UUID).
	 * @param rating Bewertung nach Schulnotensystem 1 bis 5 fuer User-Auction-Kombination (int).
	 * @param ratingComment Bewertung als String-Kommentar fuer User-Auction-Kombination (String).
	 */	
	public UserRating(UUID id, UUID ratedUserId, UUID ratingUserId, int rating, String ratingComment) {
		setId(id);
		setRatedUserId(ratedUserId);
		setRatingUserId(ratingUserId);
		setDateCreated();
		setRating(rating);
		setRatingComment(ratingComment);
	}

	//  Getter & Setter:
	/**
	 * Get-Methode fuer Instanzvariable id.
	 * @return id des UserRatings (int).
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Set-Methode fuer Instanzvariable id. 
	 * @param id ID des UserRatings (int). Die id muss >0 sein (wird hier ueberprueft) 
	 * und darf nur von einem UserRating-Objekt verwendet werden (wird in SerializedUserDAO gepr�ft bei addUserRating).
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * Get-Methode fuer Instanzvariable ratedUserId.
	 * @return ratedUserId ID des User, der bewertet wird (UUID).
	 */
	public UUID getRatedUserId() {
		return ratedUserId;
	}

	/**
	 * Set-Methode fuer Instanzvariable ratedUserId. 
	 * @param ratedUserId ID des User, der bewertet wird (UUID).
	 */
	public void setRatedUserId(UUID ratedUserId) {
		/*if(ratedUser==null || (ratedUser.getId()!=this.ratedAuction.getCreatedByUserId())) {
			throw new IllegalArgumentException("RATEDUSER darf nicht leer sein und seine User-ID muss mit der createdByUserId Instanzvariable der ratedAuction �bereinstimmen!");
		}
		*/
		if(ratedUserId==null) throw new IllegalArgumentException("Ungueltige ratedUserId");
		this.ratedUserId = ratedUserId;
	}

	/**
	 * Get-Methode fuer Instanzvariable ratingUserId.
	 * @return ratingUser ID des User, der die Bewertung erstellt (UUID).
	 */
	public UUID getRatingUserId() {
		return ratingUserId;
	}

	/**
	 * Set-Methode fuer Instanzvariable ratingUserId. 
	 * @param ratingUserId ID des User, der die Bewertung erstellt (UUID). Die ID darf nicht {@code null} sein und es ist verboten, 
	 * seine eigenen Auctionen zu bewerten.
	 */
	public void setRatingUserId(UUID ratingUserId) {
		if(ratingUserId==null){
			throw new IllegalArgumentException("Ungueltige ratingUserId");
		}
		else if(ratedUserId.equals(this.ratingUserId)){
			throw new IllegalArgumentException("RATEDUSER und RATINGUSER duerfen nicht selbe USER-ID haben - man darf seine eigenen Auctionen nicht bewerten!");
		}
		
		this.ratingUserId = ratingUserId;
	}

	

	/**
	 * Get-Methode fuer Instanzvariable rating.
	 * @return rating Bewertung von 1 bis 5 nach Schulnotensystem bzgl. User-Auction-Kombination (int).
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Set-Methode fuer Instanzvariable rating. 
	 * @param rating Bewertung von 1 bis 5 nach Schulnotensystem bzgl. User-Auction-Kombination (int). Die Bewertung
	 * darf nur die Werte 1,2,3,4,5 annehmen.
	 */
	public void setRating(int rating) {
		if(rating==1 || rating==2 || rating==3 || rating==4 || rating==5) {
			this.rating = rating;
		}
		else{
			throw new IllegalArgumentException("RATING kann nur die Werte 1,2,3,4 oder 5 nach Schulnotensystem haben.");
		}
	}

	/**
	 * Get-Methode fuer Instanzvariable ratingComment.
	 * @return ratingComment Bewertungskommentar des ratingUsers (String).
	 */
	public String getRatingComment() {
		return ratingComment;
	}

	/**
	 * Set-Methode fuer Instanzvariable ratingComment. 
	 * @param ratingComment Bewertungskommentar des ratingUsers (String). Der Kommentar kann auch leer bleiben, wenn man nichts 
	 * einfuegen moechte.
	 */
	public void setRatingComment(String ratingComment) {
		this.ratingComment = ratingComment;
	}

	/**
	 * Get-Methode fuer Instanzvariable dateCreated.
	 * @return dateCreated das Datum der Erstellung des UserRatings
	 */
	public Calendar getDateCreated() {
		return dateCreated;
	}

	/**
	 * Set-Methode fuer Instanzvariable dateCreated.
	 * Weist der Instanzvariable dateCreated den momentanen Zeitpunkt zu
	 */
	public void setDateCreated() {
		dateCreated = Calendar.getInstance();
	}
		
}
