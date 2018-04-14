package swe2016.g6t4.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;

/**
* Die Klasse Bid implementiert Serializable. 
* Auf die privaten Instanzvariablen kann mit public Get- und Set-Methoden zugegriffen werden kann. Zusaetzlich gibt es eine SerialVersionUID.
* Die Klasse hat nur Setter und Getter, keine weiteren Methoden.  
* @author  Team 4 (Gruppe 6)
* @version 1.0
* @since   2016-30-11 
*/
public class Bid implements Serializable {
	
	/**
	 * Die Instanzvariablen des Bid-Objekts: bidId (UUID), userId (UUID), amount (double), bidDate (Calendar).
	 * Wegen dem implement Serializable gibt es auch eine serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	private UUID bidId;
	private UUID userId;
	private double amount;
	private Calendar bidDate;
	
	/**
	 * Der Konstruktor Bid hat als Parameter die Instanzvariablen und setzt diese mittels Set-Methoden.
	 * @param bidId ID des Gebotes (UUID).
	 * @param userId ID des Users, der das Gebot erstellt (UUID).
	 * @param amount Betrag, den der Ersteller des Gebotes bietet (double).
	 * @param bidDate Datum der Erstellung des Gebotes (Calendar).
	 */	
	public Bid(UUID bidId, UUID userId, double amount) {  
		setBidId(bidId);
		setUserId(userId);
		setAmount(amount);
		setBidDate(); // bidDate liegt immer am Tag des Gebots
	}

	//  Getter & Setter:
	/**
	 * Get-Methode fuer Instanzvariable bidId.
	 * @return ID des Gebotes (UUID).
	 */
	public UUID getBidId() {
		return bidId;
	}

	/**
	 * Set-Methode fuer Instanzvariable bidId. 
	 * @param bidId ID des Gebotes (UUID).   
	 * und darf nur von einem Bid-Objekt verwendet werden innerhalb einer Auction (wird in SerializedAuctionDAO geprüft 
	 * bei addBidtoAuctionBidList).
	 */
	public void setBidId(UUID bidId) {
		// Überprüfung, ob schon ein Bid-Objekt mit dieser bidID existiert, findet in SerializedAuctionDAO statt!
		this.bidId = bidId;
	}

	/**
	 * Get-Methode fuer Instanzvariable userId.
	 * @return ID des Users, der das Gebot erstellt (UUID).
	 */
	public UUID getUserId() {
		return userId;
	}

	/**
	 * Set-Methode fuer Instanzvariable userId. 
	 * @param userId ID des Users, der das Gebot erstellt (int). Die ID
	 * und darf nur von einem Objekt verwendet werden (wird in SerializedUserDAO geprüft bei addUser - man geht hier davon aus, 
	 * dass die userID richtig ist, sobald ein User erstellt wurde).
	 */
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	
	/**
	 * Get-Methode fuer Instanzvariable amount.
	 * @return amount Betrag des Gebotes, den der Ersteller bietet (double).
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Set-Methode fuer Instanzvariable amount. 
	 * @param amount Betrag des Gebotes, den der Ersteller bietet (double). Der übergebene Betrag muss groesser null sein.
	 */
	public void setAmount(double amount) {
		if(amount<=0) {
			throw new IllegalArgumentException("AMOUNT muss >0 sein!");
		}
		// Überprüfung, ob amount>=Auction.startPrice, findet in SerializedAuctionDAO bei der Speicherung des Bid statt bzw im AuctionManagement.
		this.amount = amount;
	}

	/**
	 * Get-Methode fuer Instanzvariable bidDate.
	 * @return Datum der Erstellung des Gebotes (Calendar).
	 */
	public Calendar getBidDate() {
		return bidDate;
	}

	/**
	 * Set-Methode fuer Instanzvariable bidDate. 
	 * @param bidDate Datum der Erstellung des Gebotes (Calendar). bidDate ist das Datum ("jetzt") der Geboterstellung.
	 */
	public void setBidDate() {
		Calendar jetzt = Calendar.getInstance();
		/* if((bidDate.get(Calendar.DAY_OF_MONTH)==(jetzt.get(Calendar.DAY_OF_MONTH))) && (bidDate.get(Calendar.MONTH)==(jetzt.get(Calendar.MONTH))) && (bidDate.get(Calendar.YEAR)==jetzt.get(Calendar.YEAR))){
			this.bidDate = bidDate;
		}
		else {
			throw new IllegalArgumentException("BIDDATE muss am Datum des Gebotes gesetzt werden.");
		}
		*/
		this.bidDate=jetzt;
	}

}
