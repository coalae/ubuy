package swe2016.g6t4.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import swe2016.g6t4.model.Auction;
import swe2016.g6t4.model.Bid;
import swe2016.g6t4.model.User;

/**
* Die Klasse AuctionManagement besitzt Methoden zum Zugriff und Ausf�hren von Aktivit�ten bzgl. Auction-Objekten und 
* der AuctionGroupList und deren Hinzuf�gen, L�schen etc. 
* @author  Team 4 (Gruppe 6)
* @version 1.0
* @since   2016-12-01 
*/
public class AuctionManagement {
	
	/**
	 * Die Instanzvariablen des AuctionManagement-Objekt: auctionGroupList (ArrayList), auctiondao (AuctionDAO), auctiongroupdao (AuctionGroupDAO).
	 */	
	private ArrayList<String> auctionGroupList; 
	private AuctionDAO auctiondao;
	private AuctionGroupDAO auctiongroupdao;
	
	/**
	 * Der Konstruktor AuctionManagement setzt die Instanzvariablen mittels Set-Methoden.
	 */	
	public AuctionManagement(){
		setAuctionGroupList(); 		
		setAuctionDAO();
		setAuctionGroupDAO();
	}
	
	// Getter & Setter
	/**
	 * Get-Methode fuer Instanzvariable auctionGroupList.
	 * @return auctionGroupList als ArrayList.
	 */
	public ArrayList<String> getAuctionGroupList() {
		return this.auctionGroupList;
	}
	
	/**
	 * Set-Methode fuer Instanzvariable auctionGroupList (Basic Groups: Lernmaterial, Nachhilfe). 
	 */
	public void setAuctionGroupList() {
		this.auctionGroupList = new ArrayList<String>();
		auctionGroupList.add("Lernmaterial");			
		auctionGroupList.add("Nachhilfe");
	}
		
	
	/**
	 * Get-Methode fuer Instanzvariable auctiondao.
	 * @return auctiondao als AuctionDAO.
	 */
	public AuctionDAO getAuctiondao() {
		return auctiondao;
	}

	/**
	 * Set-Methode fuer Instanzvariable auctiondao. 
	 */
	public void setAuctionDAO() {
		this.auctiondao = new SerializedAuctionDAO();
	}

	/**
	 * Get-Methode fuer Instanzvariable auctiongroupdao.
	 * @return auctiongroupdao als AuctionGroupDAO.
	 */
	public AuctionGroupDAO getAuctionGroupDAO() {
		return auctiongroupdao;
	}

	/**
	 * Set-Methode fuer Instanzvariable auctiongroupdao. 
	 */
	public void setAuctionGroupDAO() {
		this.auctiongroupdao = new SerializedAuctionGroupDAO();
	}
	
	// Methoden
	/**
	 *Anzeigen der AuctionGroupList von SerializedDAO.
	 * @return auctionGoups als ArrayList.
	 */
	public ArrayList<String> getAuctionGroups() {
		return auctiongroupdao.getAuctionGroupList();
	}
	
	/**
	 * Die addAuctionGroup(String group)-Methode f�gt eine neue AuctionGroup hinzu zur auctionGroupList. 
	 * @param group Name der neuen AuctionGroup (als String). Der �bergebene Name darf nicht leer sein (wird gepr�ft).
	 * Der neue Name darf auch nicht bereits in der AuctionGroups Liste in Serialized vorhanden sein (wird gepr�ft).
	 */
	public void addAuctionGroup(String group){
		if(group==null || group.isEmpty()){
			throw new IllegalArgumentException("AUCTIONGROUPNAME darf nicht leer sein!");
		}
		
		if(group.equals("Lernmaterial") || group.equals("Nachhilfe")){
			throw new IllegalArgumentException("LERNMATERIAL und NACHHILFE gibt es schon!");
		}
		
		for(int i=0; i<this.getAuctionGroups().size();i++){
			if(this.getAuctionGroups().get(i).equals(group)){
				throw new IllegalArgumentException("GRUPPE gibt es schon!");			
			}
		}
		
		auctiongroupdao.addAuctionGroup(group); // speichert neue gruppe persistent �ber SerializedAuctionGroupDAO
	}
	
	/**
	 * Die deleteAuctionGroup(String group)-Methode l�scht eine bestehende AuctionGroup aus der auctionGroupList. 
	 * @param group Name der zu l�schenden AuctionGroup (als String). Der �bergebene Name darf nicht leer sein (wird hier gepr�ft).
	 * Wenn der �bergebene Name nicht in der auctionGroupList vorhanden ist, wird die Anfrage abgebrochen (wird in AuctionGroupDAO gepr�ft).
	 * Die Basic-Implementierung mit den Gruppen "Lernmaterial" und "Nachhilfe" kann nicht gel�scht werden, alle anderen Gruppen schon.
	 */
	public void deleteAuctionGroup(String group){
		if(group==null || group.isEmpty()){
			throw new IllegalArgumentException("AUCTIONGROUPNAME darf nicht leer sein!");
		}
		
		if(group.equals("Lernmaterial") || group.equals("Nachhilfe")){
			throw new IllegalArgumentException("BASISIMPLEMENTIERUNG " + group + " kann nicht gel�scht werden.");
		}
		
		// l�scht auctionGroup
		auctiongroupdao.deleteAuctionGroup(group); 
		for(int i=0; i<auctiongroupdao.getAuctionGroupList().size(); i++){
			if(auctiongroupdao.getAuctionGroupList().get(i).equals(group)){
				this.auctionGroupList.remove(group);
			}
		}
		
		// l�scht alle auctionen, die es in dieser auctionGroup gibt
		for(int i=0; i<auctiondao.getAuctionList().size(); i++){
			if(auctiondao.getAuctionList().get(i).getAuctionGroup().equals(group)){
				auctiondao.deleteAuction(auctiondao.getAuctionList().get(i));
			}
		}
	}
	
	/**
	 * Die addAuction(Auction auction)-Methode f�gt eine neue Auction hinzu zur AuctionList. 
	 * @param auction neue Auction (als Auction).  
	 */
	public void addAuction(Auction auction){

		if(auction.getAuctionGroup()==null ||auction.getAuctionGroup().isEmpty()) {
			throw new IllegalArgumentException("AUCTIONGROUP darf nicht leer sein!");
		}
		
		// wenn auctiongroup lernmaterial oder nachhilfe (i.e. basic groups) ist
		if(auction.getAuctionGroup().equals("Lernmaterial") || auction.getAuctionGroup().equals("Nachhilfe")) {
			auctiondao.addAuction(auction);
		}
		
		// wenn auctiongroup eine der zusatzgruppen in der auctiongroups liste im serializedDAO ist
		for(int i=0;i<this.getAuctionGroups().size();i++) {
			if(this.getAuctionGroups().get(i).equals(auction.getAuctionGroup())) {
				auctiondao.addAuction(auction);
			}
		}
		
	}
	
	/**
	 * Die delteAuction(Auction auction)-Methode l�scht eine bestehende Auction aus der AuctionList. 
	 * @param auction zu loeschende Auction (als Auction).  
	 */
	public void deleteAuction(Auction auction){
		auctiondao.deleteAuction(auction);
	}
	
	/**
	 * Die addBid(Bid bid, Auction auction)-Methode f�gt ein neues Bid zur bidList der Auction hinzu. 
	 * @param bid neues bid.
	 * @param auction betroffene Auction, zu der das Bid hinzugef�gt werden soll (als Auction).   
	 */
	public void addBid(Bid bid, Auction auction){
		Bid highestBidTillNow = this.getHighestBid(auction);
		// nur Bids, deren Amount gr��er als das bisher h�chste Bid sind, d�rfen hinzugef�gt werden
		if(highestBidTillNow.getAmount()<bid.getAmount()){
			auctiondao.addBidToAuctionBidList(bid, auction);
		}
	}
	
	/**
	 * Die searchAuctionByKeyword(String keyword) sucht in der AuctionList Auctionen nach einem bestimmten keyword in 
	 * den Instanzvariablen title und description ab und gibt eine Liste mit Auctionen, die das keyword enthalten, zur�ck. 
	 * @param keyword Suchwort (als String).
	 */
	public ArrayList<Auction> searchAuctionByKeyword(String keyword){		
		ArrayList<Auction> auctionlist=auctiondao.getAuctionList();
		ArrayList<Auction> auctionlistSearchedKeyword=new ArrayList<Auction>();

		for(int i=0; i<auctionlist.size();i++){
			if((auctionlist.get(i).getTitle().toLowerCase().contains(keyword.toLowerCase()) || auctionlist.get(i).getDescription().toLowerCase().contains(keyword.toLowerCase())) && !auctionlist.get(i).isExpired()){
				auctionlistSearchedKeyword.add(auctionlist.get(i));
			}
		}
		
		return auctionlistSearchedKeyword;
	}
	
	/**
	 * Die searchAuctionByCategory(String keyword) sucht in der AuctionList Auctionen nach einer bestimmten AuctionGroup in 
	 * der Instanzvariablen auctionGroup ab und gibt eine Liste mit Auctionen, die die auctionGroup enthalten, zur�ck. 
	 * @param category AuctionGroup (als String).
	 */
	public ArrayList<Auction> searchAuctionByCategory(String category){
		if(category.equals("") || category==null) {
			throw new IllegalArgumentException("CATEGORY String war leer oder null.");
		}
		
		ArrayList<Auction> auctionlist=auctiondao.getAuctionList();
		ArrayList<Auction> auctionlistSearchedCategory=new ArrayList<Auction>();

		for(int i=0; i<auctionlist.size();i++){
			if(auctionlist.get(i).getAuctionGroup().equals(category) && !auctionlist.get(i).isExpired()){
				auctionlistSearchedCategory.add(auctionlist.get(i));
			}
		}
		
		return auctionlistSearchedCategory;
	}
		
	/**
	 * Die auctionDaysLeft(Auction auction)-Methode berechnet, wieviel Zeit in Tagen noch verbleibt, bevor die Auction ablauft. 
	 * @param auction (als Auction).
	 */
	public long auctionDaysLeft(Auction auction){
		long auctionDaysLeft=0;
		Auction daysLeftCount=null;
		
		ArrayList<Auction> auctionlist=auctiondao.getAuctionList();
		for(int i=0; i<auctionlist.size();i++){
			if(auctionlist.get(i).getId().equals(auction.getId())){
				daysLeftCount=auctionlist.get(i);
			}
		}
		
		Calendar jetzt = Calendar.getInstance();
		// System.out.println(dateFormat.format(now.getTime())); 
		long jetztms= jetzt.getTimeInMillis();
		Calendar auctionEnd = daysLeftCount.getEnd();
		long auctionEndms = auctionEnd.getTimeInMillis();
		
		if(daysLeftCount.isExpired()==false){
			auctionDaysLeft=(auctionEndms-jetztms)/1000/60/60/24;
			return auctionDaysLeft;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * Die getHighestBid(Auction auction)-Methode gibt das h�chste Bid der �bergebenen Auction aus. Wenn es kein Bid gibt, dann null. 
	 * Wenn es mehrere Bids mit gleichem amount gibt, z�hlt das fr�heste davon.
	 * @param auction.
	 * @return bid.
	 */
	public Bid getHighestBid(Auction auction){
		
		Auction highestBidSearch=null;
		
		// sucht die �bergebene Auction in der AuctionList
		ArrayList<Auction> auctionlist=auctiondao.getAuctionList();
		for(int i=0; i<auctionlist.size();i++){
			if(auctionlist.get(i).equals(highestBidSearch)){
				highestBidSearch=auctionlist.get(i);
			}
		}
		
		Bid highestbid=null;
		double highestamount=0.0;
		
		// sucht in der Auction das h�chste Bid
		for(int i=0; i<auction.getBidList().size();i++){
			if(highestBidSearch.getBidList().get(i).getAmount()>highestamount){
				highestamount=highestBidSearch.getBidList().get(i).getAmount();
			}
		}
		
		for(int i=0; i<auction.getBidList().size();i++){
			if(highestBidSearch.getBidList().get(i).getAmount()==highestamount){
				highestbid=highestBidSearch.getBidList().get(i);
			}
		}
		
		return highestbid; // falls highestbid=null -> Handling im SerializedDAO
	}
	
	/**
	 * Die getHighestBid(Auction auction)-Methode gibt das h�chste Bid der �bergebenen Auction aus. Wenn es kein Bid gibt, dann null. 
	 * Wenn es mehrere Bids mit gleichem amount gibt, z�hlt das fr�heste davon.
	 * @param auction (als Auction).
	 * @return bid (als Bid).
	 */
	public double getHighestBidAmount(Auction auction){
				
		// sucht die �bergebene Auction in der AuctionList
		/* ArrayList<Auction> auctionlist=auctiondao.getAuctionList();
		
		boolean checkAuctionExists=false;
		for(int i=0; i<auctionlist.size();i++){
			if(auctionlist.get(i).equals(auction)){
				checkAuctionExists=true;
			}
			break;
		}
		*/
		
		double highestamount=auction.getStartPrice();
		
		if(auction.getBidList().size()>0) {
			// sucht in der �bergebenen Auction das h�chste Bid und gibt dessen Betrag zur�ck
			for(int i=0; i<auction.getBidList().size();i++){
				if(auction.getBidList().get(i).getAmount()>highestamount){
					highestamount=auction.getBidList().get(i).getAmount();
				}
			}
			return highestamount;
		}
		else {
			double startpreis=auction.getStartPrice();
			return startpreis;
		}
	}
	
	/**
	 * Die getAllAuctions()-Methode gibt eine Liste aller Auctionen aus.
	 * @return ArrayList von Auktionen.
	 */
	public ArrayList<Auction> getAllAuctions(){
		
		return auctiondao.getAuctionList();
		
	}
	
	/**
	 * Die getAllBids()-Methode gibt eine Liste aller Bids der �bergebenen Auction aus.
	 * @return ArrayList von Bids.
	 */
	public ArrayList<Bid> getAllBids(Auction auction){
				
		Auction auctionGetAllBids=null;
		
		ArrayList<Auction> auctionlist=auctiondao.getAuctionList();
		for(int i=0; i<auctionlist.size();i++){
			if(auctionlist.get(i).equals(auction)){
				auctionGetAllBids=auctionlist.get(i);
			}
		}
		
		 return auctionGetAllBids.getBidList();		
	}
	
	/**
	 * Die getAllExpiredAuctions()-Methode gibt eine Liste aller abgelaufenen Auctionen aus.
	 * @return ArrayList vom Typ Auction.
	 */
	public ArrayList<Auction> getAllExpiredAuctions(){
		
		ArrayList<Auction> allexpiredlist= new ArrayList<Auction>();
		
		ArrayList<Auction> allauctionslist=auctiondao.getAuctionList();
		
		for(int i=0; i<allauctionslist.size();i++){
			if(allauctionslist.get(i).isExpired()){
				allexpiredlist.add(allauctionslist.get(i));
			}
		}

		return allexpiredlist;
	}
	
	 /**
	 * Die getAllActiveAuctions()-Methode gibt eine Liste aller nicht-abgelaufenen Auctionen aus.
	 * @return ArrayList.
	 */
	public ArrayList<Auction> getAllActiveAuctions(){
		
		ArrayList<Auction> allactivelist=new ArrayList<Auction>();
		
		ArrayList<Auction> allauctionslist=auctiondao.getAuctionList();
		
		for(int i=0; i<allauctionslist.size();i++){
			if(!allauctionslist.get(i).isExpired()){
				allactivelist.add(allauctionslist.get(i));
			}
		}

		return allactivelist;
	}
	
	 /**
	 * Die getAllSoldAuctions()-Methode gibt eine Liste aller verkauften (i.e. && expired) Auctionen aus.
	 * @return ArrayList.
	 */
	public ArrayList<Auction> getAllSoldAuctions(){
		
		ArrayList<Auction> allsoldlist= new ArrayList<Auction>();
		
		ArrayList<Auction> allauctionslist=auctiondao.getAuctionList();
		
		for(int i=0; i<allauctionslist.size();i++){
			if(allauctionslist.get(i).isSold()){
				allsoldlist.add(allauctionslist.get(i));
			}
		}
		
		return allsoldlist;
	}
	
	 /**
	 * Die checkExpired(Auction auction)-Methode schaut, ob die �bergebene Auction ablaufen ist.
	 * @param auction (als Auction)
	 * @return expired (als boolean).
	 */
	public boolean checkExpired(Auction auction){

		Auction auctionCheckExpired=auctiondao.getAuctionById(auction.getId());
	
		return auctionCheckExpired.isExpired();
	}
	
	 /**
	 * Die checkSold(Auction auction)-Methode schaut, ob die �bergebene Auction verkauft wurde (&& abgelaufen ist).
	 * @param auction (als Auction).
	 * @return sold (als boolean).
	 */
	public boolean checkSold(Auction auction){
		Auction auctionCheckSold=auctiondao.getAuctionById(auction.getId());
		
		return auctionCheckSold.isSold();
	}
	
	 /**
	 * Die getAuctionById(int id)-Methode sucht zu einer �bergebenen AuctionsID die Auction.
	 * @param id (als int).
	 * @return auctionbyid (als Auction).
	 */
	public Auction getAuctionById(UUID id){
				
		return auctiondao.getAuctionById(id);
	}
	
	public void deleteBidsByUser(User u){
		ArrayList<Auction> aList = auctiondao.getAuctionList();
		for(int i=0; i<aList.size(); i++){
			ArrayList<Bid>bList = aList.get(i).getBidList();
			boolean gotChanged = false;
			
			for(int j =0; j<bList.size(); j++){
				if(bList.get(j).getUserId().equals(u.getId())){
					bList.remove(j);
					gotChanged = true;
				}
				if(gotChanged){
					aList.get(i).setBidList(bList);
					auctiondao.updateAuction(aList.get(i));
				}
			}
		}
	}
	
	 /**
	 * Die addBidToAuctionBidList(Auction auction, Bid bid)-Methode erstellt ein neues Bid zu einer �bergebenen Auction.
	 * @param auction (als Auction).
	 * @param bid (als Bid).
	 */
	public void addBidToAuctionBidList(Auction auction, Bid bid){
		this.auctiondao.addBidToAuctionBidList(bid, auction);
	}
		
}