package swe2016.g6t4.controller;

import java.util.ArrayList;

/**
* Das Interface AuctionGroupDAO deklariert generische Methoden zum Einlesen und Speichern der AuctionGroupList, in der alle bestehenden Auktionsgruppen
* verwaltet werden. 
* Klassen, die das Interface verwenden, muessen diese Methoden implementieren.
* @author  Team 4 (Gruppe 6)
* @version 1.0
* @since   2016-12-01 
*/
public interface AuctionGroupDAO { 
	
	/**
	 * getAuctionGroupList() gibt die Liste aller AuctionGroups zurueck. 
	 * @return ArrayList Liste aller AuctionGroups.
	 */
	public ArrayList<String> getAuctionGroupList();   
	
	/**
	 * addAuctionGroup(String groupname) fuegt eine neue AuctionGroup hinzu. Falls eine AuctionGroup mit dem gewuenschten Namen schon 
	 * existiert, wird eine Exception geworfen. 
	 * @param groupname (als String) der Name der AuctionGroup, die hinzugefuegt werden soll
	 */
	public void addAuctionGroup(String groupname) throws IllegalArgumentException;  
	
	/**
	 * deleteAuctionGroup(String groupname) loescht die uebergebene AuctionGroup. Falls die AuctionGroup nicht vorhanden ist,
	 * wird eine Exception geworfen. 
	 * @param groupname (als String) der Name der zu loeschenden AuctionGroup
	 */
	public void deleteAuctionGroup(String groupname) throws IllegalArgumentException;
	
}
