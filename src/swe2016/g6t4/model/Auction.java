package swe2016.g6t4.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import swe2016.g6t4.model.Bid;

/**
* Die Klasse Auction implementiert Serializable. 
* Auf die privaten Instanzvariablen kann mit public Get- und Set-Methoden zugegriffen werden kann. Zusaetzlich gibt es eine SerialVersionUID.  
* @author  Team 4 (Gruppe 6)
* @version 1.0
* @since   2016-30-11 
*/
public class Auction implements Serializable {
	
	/**
	 * Die Instanzvariablen des Auction-Objekts: id (UUID), title (String), description (String), start (Calendar), end (Calendar)
	 * bidList (ArrayList), createdByUserId (UUID), expired (boolean), sold (boolean), auctionGroup (String), startPrice (double).
	 * Wegen Serializable gibt es eine serVersionUID (static final long).
	 */
	private static final long serialVersionUID = 1L;
	private UUID id;
	private String title;
	private String description;
	private Calendar start;
	private Calendar end;
	private ArrayList<Bid> bidList;
	private UUID createdByUserId;
	private boolean expired;
	private boolean sold;
	private String auctionGroup;
	private double startPrice;
	
	/**
	 * Der Konstruktor Auction hat als Parameter die Instanzvariablen und setzt diese mittels Set-Methoden.
	 * @param id ID der Auction (UUID).
	 * @param title Titel der Auction (String).
	 * @param description Naehere Beschreibung zum Titel der Auction (String).
	 * @param start Start der Auktionsperiode, in der die Auction aktiv ist (Calendar).
	 * @param end Ende der Auktionsperiode, in der die Auction aktiv ist (Calendar). 
	 * @param bidList Liste von Geboten f�r die Auction (ArrayList).
	 * @param createdByUserId ID des Users, der die Auction erstellt hat (UUID).
	 * @param expired True, wenn das end der Auction �berschritten wurde (boolean).
	 * @param sold True, wenn die Auction verkauft wurde (boolean).
	 * @param auctionGroup Auktionsgruppe (z.B. Lernmaterial, Nachhilfe), in der die Auction angeboten wird (String). 
	 * @param startPrice Preis, zu dem die Auction erstellt wird (double).
	 */	
	public Auction(UUID id, String title, String description, Calendar start, Calendar end, ArrayList<Bid> bidList, UUID createdByUserId, boolean expired, boolean sold, String auctionGroup, double startPrice) {
		setId(id);
		setTitle(title);
		setDescription(description);
		setStart(start);
		setEnd(end);
		setBidList(bidList);
		setCreatedByUserId(createdByUserId);
		setExpired(expired);
		setSold(sold);
		setAuctionGroup(auctionGroup);
		setStartPrice(startPrice);
	}

	// Getter & Setter:
	/**
	 * Get-Methode fuer Instanzvariable id.
	 * @return ID der Auction (UUID).
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Set-Methode fuer Instanzvariable id. 
	 * @param id ID der Auction, die uebergeben wurde (int). Die �bergebene ID muss gr��er Null sein (wird hier �berpr�ft) 
	 * und darf nur von einem Objekt verwendet werden (wird in SerializedAuctionDAO gepr�ft bei addAuction).
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * Get-Methode fuer Instanzvariable title.
	 * @return Titel der Auction (String).
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set-Methode fuer Instanzvariable title.
	 * @param title Titel der Auction, der uebergeben wurde (String). Der Titel darf nicht leer sein.
	 */
	public void setTitle(String title) {
		if (title==null || title.isEmpty()) {
			throw new IllegalArgumentException("TITLE darf nicht NULL oder LEER sein!");
		}
		this.title = title;
	}

	/**
	 * Get-Methode fuer Instanzvariable description.
	 * @return Beschreibung des Titels der Auction (String).
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set-Methode fuer Instanzvariable description.
	 * @param description Beschreibung des Titels der Auction, die uebergeben wurde (String). Die description darf nicht leer sein.
	 */
	public void setDescription(String description) {
		if (description==null || description.isEmpty()) {
			throw new IllegalArgumentException("DESCRIPTION darf nicht NULL oder LEER sein!");
		}
		this.description = description;
	}

	/**
	 * Get-Methode fuer Instanzvariable start.
	 * @return Startdatum der Auktionsperiode (Calendar).
	 */
	public Calendar getStart() {
		return start;
	}

	/**
	 * Set-Methode fuer Instanzvariable start.
	 * @param start Startdatum der Auktionsperiode (Calendar). Das Startdatum einer neuen Auction darf nicht in der Vergangenheit liegen.
	 */
	public void setStart(Calendar start) {
		Calendar jetzt = Calendar.getInstance();

		/*if(start==null) {
			throw new IllegalArgumentException ("STARTDATUM darf nicht leer sein!");
		}
		//else if(jetzt.getTimeInMillis()>start.getTimeInMillis()){
		else if (start.before(jetzt)){
			System.out.println(start.toString() + "    " + jetzt.toString());
			throw new IllegalArgumentException ("STARTDATUM einer neuen Auction darf nicht in der Vergangenheit liegen!");
		}
		else {
			throw new IllegalArgumentException ("STARTDATUM muss am heutigen Tag sein!");
		}
		*/
		this.start = jetzt;

	}
	
	/**
	 * Get-Methode fuer Instanzvariable end.
	 * @return Enddatum der Auktionsperiode (Calendar).
	 */
	public Calendar getEnd() {
		return end;
	}

	/**
	 * Set-Methode fuer Instanzvariable end.
	 * @param end Enddatum der Auktionsperiode (Calendar). Das Enddatum muss nach dem Startdatum liegen.
	 */
	public void setEnd(Calendar end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		String endd=sdf.format(end.getTime());
		String startd=sdf.format(start.getTime());

		if(end.before(this.start) || end==null) {
			throw new IllegalArgumentException("ENDDATUM muss nach dem Startdatum liegen!" + "ENDE: " + endd + " START: " + startd);
		}
		else {
			this.end = end;
		}	
	}

	/**
	 * Get-Methode fuer Instanzvariable bidList.
	 * @return Liste der Gebote fuer die Auction (ArrayList).
	 */
	public ArrayList<Bid> getBidList() {
		return bidList;
	}

	/**
	 * Set-Methode fuer Instanzvariable bidList.
	 * @param bidList Geboteliste der betreffenden Auction (ArrayList).
	 */
	public void setBidList(ArrayList<Bid> bidList) {
		this.bidList = new ArrayList<Bid>();
		this.bidList = bidList;
	}

	/**
	 * Get-Methode fuer Instanzvariable createdByUserId.
	 * @return User-ID des Users, der die Auction erstellt hat (int).
	 */
	public UUID getCreatedByUserId() {
		return createdByUserId;
	}

	/**
	 * Set-Methode fuer Instanzvariable createdByUserId.
	 * @param createdByUserId User-ID des Users, der die Auction erstellt (int). createdByUserId muss groesser Null sein.
	 */
	public void setCreatedByUserId(UUID createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	/**
	 * Get-Methode fuer Instanzvariable expired.
	 * @return True wenn Auction abgelaufen bzw. False wenn Auction nicht abgelaufen (boolean).
	 */
	public boolean isExpired() {
		Calendar jetzt = Calendar.getInstance();
		if(this.end.before(jetzt)){
			this.expired = true;
		}
		else {
			this.expired = false;
		}
		
		return expired;
	}

	/**
	 * Set-Methode fuer Instanzvariable expired.
	 * @param expired True wenn Auction abgelaufen bzw. False wenn Auction nicht abgelaufen (boolean). 
	 * Im Konstruktor wird expired immer bei neuen Auctionen immer als false uebergeben. 
	 */
	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	/**
	 * Get-Methode fuer Instanzvariable sold.
	 * @return True wenn Auction abgelaufen und verkauft bzw. False wenn Auction abgelaufen und nicht verkauft (boolean).
	 */
	public boolean isSold() {
		if(this.isExpired()==true && this.bidList.size()>0){
			this.sold = true;
		}
		else {
			this.sold = false;
		}
		
		return sold;
	}

	/**
	 * Set-Methode fuer Instanzvariable sold.
	 * @param sold True wenn Auction abgelaufen und verkauft bzw. False wenn Auction abgelaufen und nicht verkauft (boolean).
	 */
	public void setSold(boolean sold) {
		this.sold = sold;
	}

	/**
	 * Get-Methode fuer Instanzvariable auctionGroup.
	 * @return Name der Auktionsgruppe, in der die Auktion erstellt wurde (String).
	 */
	public String getAuctionGroup() {
		return auctionGroup;
	}

	/**
	 * Set-Methode fuer Instanzvariable auctionGroup.
	 * @param auctionGroup Name der Auktionsgruppe, in der die Auction erstellt wird (String). 
	 * Die gew�nschte Auktionsgruppe darf nicht leer sein und muss in der auctionGroupList vorhanden sein, sonst darf man sie nicht w�hlen. 
	 */
	public void setAuctionGroup(String auctionGroup) {
		
		if(auctionGroup==null || auctionGroup.isEmpty()) {
			throw new IllegalArgumentException("AUCTIONGROUP darf nicht leer sein!");
		}
		
		this.auctionGroup = auctionGroup;
	}

	/**
	 * Get-Methode fuer Instanzvariable startPrice.
	 * @return Startpreis, zu dem die Auktion erstellt wurde (double).
	 */
	public double getStartPrice() {
		return startPrice;
	}

	/**
	 * Set-Methode fuer Instanzvariable startPrice.
	 * @param startPrice Startpreis, zu dem die Auction erstellt wird (double). Startpreis muss groesser null sein.
	 */
	public void setStartPrice(double startPrice) {
		if (startPrice <= 0) {
			throw new IllegalArgumentException("STARTPRICE muss groesser als Null sein!");
		}
		this.startPrice = startPrice;
	}
	
	/**
	 * addBidToBidList f�gt Bid zur bidList der Auction hinzu.
	 * @param bid Bid, das hinzugef�gt werden soll.
	 */
	public void addBidToBidList(Bid newbid) {
		for(int i=0; i<this.getBidList().size();i++){
			if(this.getBidList().get(i).getAmount()>=newbid.getAmount()){
				throw new IllegalArgumentException("Newbid amount muss groesser als jedes existierende Bid sein.");
			}
		}
		this.bidList.add(newbid);
	}
	
	public String auctionEndAsString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy"); 
		String endAsString = sdf.format(this.end.getTime());
		return endAsString;
	}
	
	/**
	 * Gibt das hoechste Gebot fuer die Auktion zurueck. Wenn noch kein Gebot abgegeben wurde, wird null zurueckgegeben.
	 * @return das hoechste Gebot in der bidList der Auktion
	 */
	public Bid getHighestBid(){
		if (bidList.size()==0) return null;
		
		Bid highestBid = bidList.get(0);
		for (Bid bid: bidList){
			if (bid.getAmount() > highestBid.getAmount()) highestBid=bid;
		}
		return highestBid;
	}
	
}
