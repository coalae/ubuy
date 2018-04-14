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

import swe2016.g6t4.model.Auction;
import swe2016.g6t4.model.Bid;

/**
* Die Klasse SerializedAuctionDAO implementiert die Methoden zum Einlesen und Speichern der Auction Objekte, in der alle bestehenden AUktionen
* verwaltet werden. 
* Die Klasse implementiert das Interface AuctionGroupDAO.
* @author  Team 4 (Gruppe 6)
* @version 1.0
* @since   2016-12-01 
*/
public class SerializedAuctionDAO implements AuctionDAO, Serializable {

	private static final long serialVersionUID = 1L;
	// instanzvariable
	/**Der Name der Datei, in der die Liste der Auktionen gespeichert werden soll.**/
	private String dateinamepfad;
	/**Eine Liste von Auction-Objekten, die verwendet wird, um die gespeicherten Auktionen zu verwalten.**/
	private ArrayList<Auction> speicherliste; 
	
	// constructor (speicherung in auctionlist.ser)
	/**Im Konstruktor wird der Name der Datei, in der die Auction-Objekte gespeichert werden sollen, festgelegt sowie der Instanzvariable
	 * eine leere Liste zugewiesen. Falls die Datei noch nicht existiert, wird sie angelegt.**/
	public SerializedAuctionDAO() {
		this.dateinamepfad="auctionlist.ser"; 
		speicherliste=new ArrayList<Auction>();
	}
	
	// implementierte methoden des interface
	// getAuctionList-methode
	/**
	 * getAuctionList() gibt die Liste aller Auctions zurueck. 
	 * @return ArrayList Liste aller Auctions.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Auction> getAuctionList() { // Liste (z. B. java.util.ArrayList) benutzen, gespeicherten Auction-Objekten return
		File datei = new File(dateinamepfad);
		if(datei.exists() && datei.canRead()) {
			try {
			FileInputStream fileinput = new FileInputStream(dateinamepfad);
			ObjectInputStream objectinput = new ObjectInputStream(fileinput);
			
			if(objectinput!=null) {
				speicherliste = (ArrayList<Auction>) objectinput.readObject(); 
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
	
	// getAuctionbyId-methode
	/**
	 * getAuctionById(UUID id) sucht anhand der Auction-ID ein Auction-Objekt in der Liste der gespeicherten Auction-Objekte und gibt dieses zurueck. 
	 * @param id (als UUID) ID des zu suchenden Objekts
	 * @return Auction anhand der ID gefundene Auktion (wenn ID nicht auffindbar, dann wird null zurueckgegeben oder 
	 * eine Exception geworfen, die besagt, dass die Auktion nicht existiert).
	 */
	@Override
	public Auction getAuctionById(UUID id) {    // anhand der AuctionUUID, ein Auction-Objekt zur�ckgeben. 
		if(id==null) {
			throw new IllegalArgumentException("�bergebene AUCTIONID war NULL.");
		}
		
		ArrayList<Auction> idsuchliste = getAuctionList();
		
		for(int i=0; i<idsuchliste.size();i++) {
			if(idsuchliste.get(i).getId().equals(id)) {
				return idsuchliste.get(i);
			}
		}
		
		throw new IllegalArgumentException("�bergebene AUCTIONID ist nicht vorhanden."); 
		// Handling im Servlet in try-catch-clause notwendig!


		/*for(Auction ac : idsuchliste){
			if (ac.getId() == id) {return ac;}
			return null;
		  }
		*/
	}
	
	/**
	 * getAuctionByCreatedByUserId(UUID userid) sucht anhand der createdByUser-ID (Instanzvariable von Auction-Objekt) 
	 * in der Liste der gespeicherten Auction-Objekte alle Auction-Objekte, die von dem fraglichen User erstellt wurden, und gibt diese zurueck. 
	 * Falls keine solchen Objekte existieren, wird eine leere Liste zurueckgegeben)
	 * @param userid die ID des Users, dessen Auktionen zurueckgegeben werden sollen
	 * @return ArrayList anhand der createdByUserId gefundene Auktionen
	 */
	@Override 
	public ArrayList<Auction> getAuctionByCreatedByUserId(UUID userid){
		if(userid==null) {
			throw new IllegalArgumentException("�bergebene USERID war NULL.");
		}
		
		ArrayList<Auction> idsuchliste = getAuctionList();
		ArrayList<Auction> auctionsCreatedByUserId = new ArrayList<Auction>(); 
		
		for(int i=0; i<idsuchliste.size();i++) {
			if(idsuchliste.get(i).getCreatedByUserId().equals(userid)) {
				auctionsCreatedByUserId.add(idsuchliste.get(i));
			}
		}
		return auctionsCreatedByUserId;
	}
	
	// addAuction-methode
	/**
	 * addAuction(Auction auction) fuegt eine neue Auktion hinzu. Falls ein Auction-Objekt mit der gewuenschten ID schon gespeichert wurde, wird eine
	 * Exception geworfen. 
	 * @param auction (als Auction)
	 * @throws IllegalArgumentException falls ein Auction-Objekt mit einer bereits verwendeten ID uebergeben wurde
	 */
	@Override
	public void addAuction(Auction newauction) {  // soll eine Auction persistent speichern
		
		if(newauction==null || newauction.equals("")) {
			throw new IllegalArgumentException ("Die �bergebene Auction ist NULL, daher keine Speicherung m�glich!");
		}
			
		for(int i=0; i<getAuctionList().size();i++){
			if ( (getAuctionList().get(i).getId().equals(newauction.getId())) ){
				throw new IllegalArgumentException("Die zum Speichern uebergebene Auction existiert schon!");
			}
		}

			ArrayList<Auction> neueliste = new ArrayList<Auction>();
			neueliste.addAll(getAuctionList());
			neueliste.add(newauction);	
			
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
	
	// deleteAuction-methode
	/**
	 * deleteAuction(Auction auction) loescht die uebergebene Auktion. Falls das Auction-Objekt anhand seiner ID nicht auffindbar ist, wird eine
	 * Exception geworfen. 
	 * @param auction (als Auction)
	 * @throws IllegalArgumentException falls das zu loeschende Auction-Objekt nicht gefunden werden kann
	 */
	@Override
	public void deleteAuction(Auction auctionToDelete) {  // soll eine Auction von der persistenten Datenquelle l�schen
        	
		if(auctionToDelete==null || auctionToDelete.equals("")) {
			throw new IllegalArgumentException ("Die �bergebene Auction ist NULL, daher keine L�schung m�glich!");
		}
		
		boolean checkAuctionToDeleteExists = false;
		for(int i=0; i<getAuctionList().size();i++){
			if ( (getAuctionList().get(i).getId().equals(auctionToDelete.getId())) ){
				checkAuctionToDeleteExists=true;
			}
		}
		
		if (checkAuctionToDeleteExists) {
			ArrayList<Auction> neueliste = new ArrayList<Auction>();
			neueliste.addAll(this.getAuctionList());
			
			for(int i=0; i<neueliste.size();i++) {
				if(neueliste.get(i).getId().equals(auctionToDelete.getId())) {
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
			throw new IllegalArgumentException ("Die zum Loeschen uebergebene Auction existiert nicht!");
		}
			
	}
	
	// addBidToAuctionBidList-methode
	/**
	 * addBidToAuctionBidList(Bid bid, Auction auction) fuegt ein Gebot zu einer gewaehlten Auktion hinzu. Falls die Auktion nicht existiert, wird eine
	 * Exception geworfen. Falls bidId schon belegt ist, wird eine Exception geworfen. Falls ein User versucht, fuer seine eigene Auktion zu bieten
	 * (d.h. Bid.userId == Auction.createdByUserId), wird eine Exception geworfen. Falls die Auktion bereits abgelaufen ist (d.h. expired == true),
	 * wird eine Exception geworfen.
	 * @param bid (als Bid)
	 * @param auction (als Auction)
	 * @throws IllegalArgumentException falls ungueltige Bid- oder Auction- Objekte uebergeben werden
	 */
	public void addBidToAuctionBidList(Bid bid, Auction auction) { 
		if (bid==null || auction==null) {
			throw new IllegalArgumentException("AUCTION oder BID ist NULL, daher kann nicht gespeichert werden");
		}
		// F�R TESTZWECKE ALS KOMMENTAR
		/* if (bid.getUserId().equals(auction.getCreatedByUserId())) {
			throw new IllegalArgumentException("Abgeben von Geboten fuer die eigene Aktion ist nicht erlaubt");
		}
		*/
		if (auction.isExpired()) {
			throw new IllegalArgumentException("Bieten nicht moeglich - Auktion bereits beendet (expired)");
		}
	
		if (getAuctionById(auction.getId())!=null) {
				ArrayList<Auction> neueliste = new ArrayList<Auction>();
				neueliste.addAll(getAuctionList());
				
				for(int i=0; i<neueliste.size();i++) {
					if(neueliste.get(i).getId().equals(auction.getId())) {
						Auction auctionChanged=neueliste.get(i);
						auctionChanged.addBidToBidList(bid);
						neueliste.set(i,auctionChanged);
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
			throw new IllegalArgumentException ("Die zum Bid-Speichern uebergebene Auction wurde nicht gefunden!");
		}
		
	}

	/**
	 * Hiermit kann eine Auction geändert werden, um ggf. Gebote zu entfernen.
	 * @param Auction
	 */
	public void updateAuction(Auction auction) {
		deleteAuction(getAuctionById(auction.getId()));
		addAuction(auction);
		
	}

}
