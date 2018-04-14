package swe2016.g6t4.model;

import java.io.Serializable;
import java.util.UUID;

/**
* Die Klasse SearchTutorAd implementiert Serializable. 
* Auf die privaten Instanzvariablen kann mit public Get- und Set-Methoden zugegriffen werden kann. Zusaetzlich gibt es eine SerialVersionUID.
* @author  Team 4 (Gruppe 6)
* @version 1.0
* @since   2016-17-12 
*/
public class SearchTutorAd implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Die Instanzvariablen des SearchTutorAd-Objekts: adId (UUID), userIdDemand (UUID), userIdSupply (UUID), course (String), maxPrice (double), adMatched (boolean).
	 * Wegen Serializable gibt es eine serVersionUID (static final long).
	 */
	// instanzvariablen
	private UUID adId;
	private UUID userIdDemand;
	private UUID userIdSupply;
	private String course;
	private double maxPrice;   // price per hour
	private boolean adMatched;

	/**
	 * Der Konstruktor SearchTutorAd hat als Parameter die Instanzvariablen und setzt diese mittels Set-Methoden bzw. zum Teil direkt.
	 * @param adId ID des SearchTutorAd (UUID).
	 * @param userIdDemand ID des Users, der das Inserat aufgibt (UUID).
	 * @param course Kurs, für den die Nachhilfe gesucht wird (String).
	 * @param maxPrice maximaler Preis, den der User, der das Inserat aufgibt, bereit ist zu zahlen (double).
	 */	
	// constructor
	public SearchTutorAd(UUID adId, UUID userIdDemand, String course, double maxPrice){
		this.setAdId(adId);
		this.setUserIdDemand(userIdDemand);
		this.setCourse(course); 
		this.setMaxPrice(maxPrice);
		this.userIdSupply=null;
		this.adMatched=false;
	}
	
	// setter & getter
	/**
	 * Get-Methode fuer Instanzvariable adId.
	 * @return ID des SearchTutorAd (UUID).
	 */
	public UUID getAdId() {
		return adId;
	}

	/**
	 * Set-Methode fuer Instanzvariable adId.
	 * @param adId ID des SearchTutorAd (UUID).
	 */
	public void setAdId(UUID adId) {
		this.adId = adId;
	}

	/**
	 * Get-Methode fuer Instanzvariable userIdDemand.
	 * @return ID des Users, der Nachhilfe sucht (UUID).
	 */
	public UUID getUserIdDemand() {
		return userIdDemand;
	}

	/**
	 * Set-Methode fuer Instanzvariable userIdDemand.
	 * @param userIdDemand ID des Users, der Nachhilfe sucht (UUID).
	 */
	public void setUserIdDemand(UUID userIdDemand) {
		this.userIdDemand = userIdDemand;
	}

	/**
	 * Get-Methode fuer Instanzvariable userIdSupply.
	 * @return ID des Users, der Nachhilfe anbietet (UUID).
	 */
	public UUID getUserIdSupply() {
		return userIdSupply;
	}

	/**
	 * Set-Methode fuer Instanzvariable userIdSupply.
	 * @param userIdSupply ID des Users, der Nachhilfe anbietet (UUID).
	 */
	public void setUserIdSupply(UUID userIdSupply) {
		this.userIdSupply = userIdSupply;
	}

	/**
	 * Get-Methode fuer Instanzvariable course.
	 * @return course Kurs, für den Nachhilfe gesucht wird (String).
	 */
	public String getCourse() {
		return course;
	}

	/**
	 * Set-Methode fuer Instanzvariable course.
	 * @param course Kurs, für den Nachhilfe gesucht wird (String).
	 */
	public void setCourse(String course) {
		this.course = course;
	}

	/**
	 * Get-Methode fuer Instanzvariable maxPrice.
	 * @return maxPrice Maximaler Preis, den der User, der einen Tutor sucht, bereit ist zu zahlen (double).
	 */
	public double getMaxPrice() {
		return maxPrice;
	}

	/**
	 * Set-Methode fuer Instanzvariable maxPrice.
	 * @param maxPrice Maximaler Preis, den der User, der einen Tutor sucht, bereit ist zu zahlen (double).
	 */
	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	/**
	 * Get-Methode fuer Instanzvariable adMatched.
	 * @return adMatched TRUE wenn ein Tutor sich gemeldet hat, FALSE wenn sich noch kein Tutor gemeldet hat (boolean).
	 */
	public boolean isAdMatched() {
		return adMatched;
	}

	/**
	 * Set-Methode fuer Instanzvariable adMatched.
	 * @param adMatched TRUE wenn ein Tutor sich gemeldet hat, FALSE wenn sich noch kein Tutor gemeldet hat (boolean).
	 */
	public void setAdMatched(boolean adMatched) {
		this.adMatched = adMatched;
	}
	
}