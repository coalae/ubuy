package swe2016.g6t4.controller;

import java.util.ArrayList;
import java.util.UUID;

import swe2016.g6t4.model.Auction;
import swe2016.g6t4.model.Bid;
import swe2016.g6t4.model.User;
import swe2016.g6t4.controller.AuctionManagement;
import swe2016.g6t4.model.UserRating;

public class StatisticManagement {

	// Instanzvariablen
	private UserManagement usermanagement;
	private AuctionManagement auctionmanagement;

	// Constructor
	public StatisticManagement() {
		usermanagement = new UserManagement();
		auctionmanagement = new AuctionManagement();
	}

	/**
	 * Methode, die die Anzahl an Bids eines Users zurueckgibt
	 * @param id Id des Users von dem die Anzahl der Bids gesucht wird
	 * @return Liefert Anzahl an Bids zurueck(int)
	 */
	public int allBidsByUser(UUID id) {
		ArrayList<Auction> auctionList = new ArrayList<Auction>(auctionmanagement.getAllAuctions());
		ArrayList<Bid> bidList = new ArrayList<Bid>();
		int allBidsByUser = 0;
		for(int i=0; i<auctionList.size(); i++){
			bidList = auctionList.get(i).getBidList();
			allBidsByUser += bidList.size();
		}
		return allBidsByUser;
	}

	/**
	 * Methode, die die gesamte Anzahl an Usern zurueckgibt
	 * @return Anzahl aller User(int) 
	 */
	public int getNumberAllUsers() {
		return usermanagement.getUserList().size();
	}

	/**
	 * Methode, die die gesamte Anzahl an aktiven Usern zurueckgibt
	 * @return Anzahl aktiver User(int)
	 */
	public int getNumberActiveUsers() {
		return usermanagement.getNumberActiveUsers();
	}

	/**
	 * Methode, die die Anzahl der gebannten User ermittelt
	 * @return Anzahl gebannter User(int)
	 */
	public int getNumberBannedUsers() {
		return usermanagement.getNumberBannedUsers();
	}

	/**
	 * Methode, die die Anzahl an Researchern ermittelt
	 * @return Anzahl an Researchern(int)
	 */
	public int Researchers() {
		return usermanagement.getNumberResearchers();
	}

	/**
	 * Methode, die die Anzahl an normalen Usern ermittelt
	 * @return Anzahl an normalen Usern(int)
	 */
	public int NormalUsers() {
		return usermanagement.getNumberNormalUsers();
	}

	/**
	 * Methode, die die Anzahl an Admins ermittelt
	 * @return Anzahl an Admins(int)
	 */
	public int getNumberAdmins() {
		return usermanagement.getNumberAdmins();
	}

	/**
	 * Methode, die die Anzahl an UserRatings ermittelt
	 * @return Anzahl an UserRatings(int)
	 */
	public int getNumberUserRatings() {
		return usermanagement.getAllUserRatings().size();
	}

	/**
	 * Methode, die das durchschnittliche UserRating ermittellt
	 * @return durchschnittlisches UserRating(int)
	 */
	public int getAverageUserRating() {
		ArrayList<UserRating> userRatings = new ArrayList<UserRating>(usermanagement.getAllUserRatings());
		int rating = 0;
		for(int i=0; i< userRatings.size(); i++){
			rating += userRatings.get(i).getRating();
		}
		rating /= userRatings.size();
		return rating;
	}

	/**
	 * Methode, die die Anzahl an AuctionGroups ermittelt
	 * @return Anzahl der AuctionGroups(int)
	 */
	public int getNumberAuctionGroups() {
		ArrayList<String> auctionGroupList = new ArrayList<String>(auctionmanagement.getAuctionGroups());
		return auctionGroupList.size();
	}

	/**
	 * Methode, die die Anzahl an Auktionen fuer eine gewuenschte Auktionsgruppe ermittelt
	 * @return Anzahl an Aktionen pro Auktionsgruppe(int)
	 */
	public int getNumberRatedAuctionsperAuctionGroup(String groupname) {
		return auctionmanagement.searchAuctionByCategory(groupname).size();
	}

	/**
	 * Methode, die die Anzahl an Geboten ermittelt
	 * @return Anzahl der Bids(int)
	 */
	public int getNumberAllBids() {
		ArrayList<Auction> auction = new ArrayList<Auction>(auctionmanagement.getAllAuctions());
		int bids = 0;
		for(int i=0; i<auction.size(); i++){
			bids += auctionmanagement.getAllBids(auction.get(i)).size();
		}
		return bids;
	}

	/**
	 * Methode, die die Anzahl an ExpiredAuctions ermittelt
	 * @return Anzahl der ExpiredAuctions(int)
	 */
	public int getNumberExpiredAuctions() {
		return auctionmanagement.getAllExpiredAuctions().size();
	}

	/**
	 * Methode, die die Anzahl an AktiveAuctions ermittelt
	 * @return Anzahl der AktiveAuctions(int)
	 */
	public int getNumberActiveAuctions() {
		return auctionmanagement.getAllActiveAuctions().size();
	}

	/**
	 * Methode, die die Anzahl an SoldAuctions ermittelt
	 * @return Anzahl der SoldAuctions(int)
	 */
	public int getNumberSoldAuctions() {
		return auctionmanagement.getAllSoldAuctions().size();
	}

	/**
	 * Methode die den durchschnittlichen Startpreis aller Auktionen berechnet
	 * @return AverageAuctionStartPrice(double)
	 */
	public double getAverageAuctionStartPrice() {
		ArrayList<Auction> auction = new ArrayList<Auction>(auctionmanagement.getAllAuctions());
		double average = 0;
		for(int i=0; i<auction.size(); i++){
			average += auction.get(i).getStartPrice();
		}
		average /= auction.size();
		return average;
	}

	/**
	 * Methode, die die durchschnittliche Dauer der zum Bieten verbliebenen Tage aller aktiven Auktionen berechnet
	 * @return AverageAuctionDaysLeft(int)
	 */
	public long getAverageAuctionDaysLeft() {
		ArrayList<Auction> allactiveauctions = new ArrayList<Auction>();
		allactiveauctions=auctionmanagement.getAllActiveAuctions();
		long auctiondaysleft=0;
		for(int i=0; i<allactiveauctions.size(); i++){
			auctiondaysleft += auctionmanagement.auctionDaysLeft(allactiveauctions.get(i));
		}
		
		if(auctiondaysleft==0) {
			return 0;
		}
		else { return auctiondaysleft/(allactiveauctions.size()); }
	}	
	
	 /**
	 * Die getAuctionOwnerUsername(Auction auction)-Methode sucht zu einer Auction den Username des Users, der sie erstellt hat.
	 * @param auction (als Auction).
	 * return username (als String).
	 */
	public String getAuctionOwnerUsername(Auction auction){
		UUID auctionOwnerId = auction.getCreatedByUserId();
		User auctionOwner = usermanagement.getUserById(auctionOwnerId);
		return auctionOwner.getUsername();
	}

}
