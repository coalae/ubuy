package swe2016.g6t4.controller;

import java.util.ArrayList;
import java.util.UUID;

import swe2016.g6t4.model.SearchTutorAd;

/**
* Das Interface SearchTutorAdDAO deklariert generische Methoden zum Einlesen und Speichern von SearchTutorAd-Objekten (Inserate für Nachhilfe). 
* Klassen, die das Interface verwenden, muessen diese Methoden implementieren.
* @author  Team 4 (Gruppe 6)
* @version 1.0
* @since   2016-12-17 
*/
public interface SearchTutorAdDAO {

	/**
	 * getSearchTutorAdList() gibt die Liste aller Inserate zurueck. Falls keine Inserate gespeichert sind, wird eine leere Liste zurueckgegeben.
	 * @return ArrayList Liste aller Inserate.
	 */
	public ArrayList<SearchTutorAd> getSearchTutorAdList(); 
	
	/**
	 * getSearchTutorAdById(UUID id) sucht anhand der adId ein derartiges Objekt in der Liste der gespeicherten SearchTutorAd-Objekte und gibt dieses zurueck. 
	 * @param adId (als UUID) ID des zu suchenden Objekts
	 * @return SearchTutorAd anhand der ID gefundene Inserat (wenn ID nicht auffindbar, dann wird null zurueckgegeben oder 
	 * eine Exception geworfen, die besagt, dass das Inserat nicht existiert).
	 */
	public SearchTutorAd getSearchTutorAdById(UUID adId);
	
	/**
	 * getSearchTutorAdByUserIdDEMAND(UUID userIdDemand) sucht anhand der userIdDemand (Instanzvariable von SearchTutorAd-Objekt) 
	 * in der Liste der gespeicherten SearchTutorAd-Objekte jene Objekte, die von der übergebenen UUID userIdDemand nachgefragt wurden.
	 * @param userIdDemand die ID des Users, dessen Auktionen zurueckgegeben werden sollen
	 * @return ArrayList SearchTutorAd anhand der userIdDemand gefundene SearchTutorAd
	 */
	public ArrayList<SearchTutorAd> getSearchTutorAdByUserIdDemand(UUID userIdDemand);
	
	/**
	 * getSearchTutorAdByUserIdSUPPLY(UUID userIdSupply) sucht anhand der userIdSupply (Instanzvariable von SearchTutorAd-Objekt) 
	 * in der Liste der gespeicherten SearchTutorAd-Objekte jene Objekte, die von der übergebenen UUID userIdSupply nachgefragt wurden.
	 * @param userIdSupply die ID des Users, dessen Auktionen zurueckgegeben werden sollen
	 * @return ArrayList SearchTutorAd anhand der userIdSupply gefundene SearchTutorAd
	 */
	public ArrayList<SearchTutorAd> getSearchTutorAdByUserIdSupply(UUID userIdSupply);
	
	/**
	 * addSearchTutorAd(SearchTutorAd ad) fuegt ein neues Inserat hinzu. Falls ein SearchTutorAd-Objekt mit der gewuenschten ID schon gespeichert wurde, wird eine
	 * Exception geworfen. 
	 * @param ad (als SearchTutorAd)
	 * @throws IllegalArgumentException falls ein SearchTutorAd-Objekt mit einer bereits verwendeten ID uebergeben wurde
	 */
	public void addSearchTutorAd(SearchTutorAd ad);  
	
	/**
	 * deleteSearchTutorAd(SearchTutorAd ad) loescht die uebergebene SearchTutorAd. Falls das SearchTutorAd-Objekt anhand seiner ID nicht auffindbar ist, wird eine
	 * Exception geworfen. 
	 * @param ad (als SearchTutorAd)
	 * @throws IllegalArgumentException falls das zu loeschende SearchTutorAd-Objekt nicht gefunden werden kann
	 */
	public void deleteSearchTutorAd(SearchTutorAd ad);
	
	/**
	 * setAdMatched(UUID adId, UUID userIdSupply) setzt die Instanzvariable adMatched von false auf true.
	 * @param adId (als UUID)
	 * @throws IllegalArgumentException falls das zu loeschende SearchTutorAd-Objekt nicht gefunden werden kann
	 */
	public void setAdMatched(UUID adId, UUID userIdSupply);
	
}
