package swe2016.g6t4.controller;

import java.util.ArrayList;
import java.util.UUID;

import swe2016.g6t4.model.Auction;
import swe2016.g6t4.model.Bid;


/**
* Das Interface AuctionDAO deklariert generische Methoden zum Einlesen und Speichern von Auction-Objekten (Auktionen). 
* Klassen, die das Interface verwenden, muessen diese Methoden implementieren.
* @author  Team 4 (Gruppe 6)
* @version 1.0
* @since   2016-12-01 
*/
public interface AuctionDAO {

	/**
	 * getAuctionList() gibt die Liste aller Auktionen zurueck. Falls keine Auktionen gespeichert sind, wird eine leere Liste zurueckgegeben.
	 * @return ArrayList Liste aller Auktionen.
	 */
	public ArrayList<Auction> getAuctionList(); 
	
	/**
	 * getAuctionById(UUID id) sucht anhand der Auction-ID ein Auction-Objekt in der Liste der gespeicherten Auction-Objekte und gibt dieses zurueck. 
	 * @param id (als UUID) ID des zu suchenden Objekts
	 * @return Auction anhand der ID gefundene Auktion (wenn ID nicht auffindbar, dann wird null zurueckgegeben oder 
	 * eine Exception geworfen, die besagt, dass die Auktion nicht existiert).
	 */
	public Auction getAuctionById(UUID id);
	
	/**
	 * getAuctionByCreatedByUserId(UUID userid) sucht anhand der createdByUser-ID (Instanzvariable von Auction-Objekt) 
	 * in der Liste der gespeicherten Auction-Objekte alle Auction-Objekte, die von dem fraglichen User erstellt wurden, und gibt diese zurueck. 
	 * Falls keine solchen Objekte existieren, wird eine leere Liste zurueckgegeben)
	 * @param userid die ID des Users, dessen Auktionen zurueckgegeben werden sollen
	 * @return ArrayList Auction anhand der createdByUserId gefundene Auktionen
	 */
	public ArrayList<Auction> getAuctionByCreatedByUserId(UUID userid);
	
	/**
	 * addAuction(Auction auction) fuegt eine neue Auktion hinzu. Falls ein Auction-Objekt mit der gewuenschten ID schon gespeichert wurde, wird eine
	 * Exception geworfen. 
	 * @param auction (als Auction)
	 * @throws IllegalArgumentException falls ein Auction-Objekt mit einer bereits verwendeten ID uebergeben wurde
	 */
	public void addAuction(Auction auction);  
	
	/**
	 * deleteAuction(Auction auction) loescht die uebergebene Auktion. Falls das Auction-Objekt anhand seiner ID nicht auffindbar ist, wird eine
	 * Exception geworfen. 
	 * @param auction (als Auction)
	 * @throws IllegalArgumentException falls das zu loeschende Auction-Objekt nicht gefunden werden kann
	 */
	public void deleteAuction(Auction auction);
	
	/**
	 * addBidToAuctionBidList(Bid bid, Auction auction) fuegt ein Gebot zu einer gewaehlten Auktion hinzu. Falls die Auktion nicht existiert, wird eine
	 * Exception geworfen. Falls bidId schon belegt ist, wird eine Exception geworfen. Falls ein User versucht, fuer seine eigene Auktion zu bieten
	 * (d.h. Bid.userId == Auction.createdByUserId), wird eine Exception geworfen. Falls die Auktion bereits abgelaufen ist (d.h. expired == true),
	 * wird eine Exception geworfen.
	 * @param bid (als Bid)
	 * @param auction (als Auction)
	 * @throws IllegalArgumentException falls ungueltige Bid- oder Auction- Objekte uebergeben werden
	 */
	public void addBidToAuctionBidList(Bid bid, Auction auction); 
	
	/**
	 * Hiermit kann eine Auction geändert werden, um ggf. Gebote zu entfernen.
	 * @param Auction
	 */
	public void updateAuction(Auction auction);

}
