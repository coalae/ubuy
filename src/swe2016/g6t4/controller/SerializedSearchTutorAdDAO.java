package swe2016.g6t4.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import swe2016.g6t4.model.SearchTutorAd;
import swe2016.g6t4.model.User;

/**
* Die Klasse SerializedSearchTutorAdDAO implementiert die Methoden zum Einlesen und Speichern der Liste von Inseraten, in der alle bestehenden Inserate
* verwaltet werden. 
* Die Klasse implementiert das Interface SearchTutorAdDAO.
* @author  Team 4 (Gruppe 6)
* @version 1.0
* @since   2016-17-12
*/
public class SerializedSearchTutorAdDAO implements SearchTutorAdDAO, Serializable {

	private static final long serialVersionUID = 1L;
	// instanzvariable
	/**Der Name der Datei, in der die Liste der Inserate gespeichert werden soll.**/
	private String dateinamepfad;
	/**Eine Liste von SearchTutorAd-Objekten, die verwendet wird, um die gespeicherten SearchTutorAds zu verwalten.**/
	private ArrayList<SearchTutorAd> speicherliste; 
	
	// constructor (speicherung in searchtutorad.ser)
	/**Im Konstruktor wird der Name der Datei, in der die SearchTutorAd-Objekte gespeichert werden sollen, festgelegt sowie der Instanzvariable
	 * eine leere Liste zugewiesen. Falls die Datei noch nicht existiert, wird sie angelegt.**/
	public SerializedSearchTutorAdDAO() {
		this.dateinamepfad="searchtutorad.ser"; 
		speicherliste=new ArrayList<SearchTutorAd>();
	}
	
	// implementierte methoden des interface
	// getSearchTutorAdList-methode
	/**
	 * getSearchTutorAdList() gibt die Liste aller SearchTutorAd zurueck. 
	 * @return ArrayList Liste aller SearchTutorAds.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<SearchTutorAd> getSearchTutorAdList() { 
		File datei = new File(dateinamepfad);
		if(datei.exists() && datei.canRead()) {
			try {
			FileInputStream fileinput = new FileInputStream(dateinamepfad);
			ObjectInputStream objectinput = new ObjectInputStream(fileinput);
			
			if(objectinput!=null) {
				speicherliste = (ArrayList<SearchTutorAd>) objectinput.readObject(); 
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
	
	// getSearchTutorAdbyId-methode
	/**
	 * getSearchTutorAdById(UUID adId) sucht anhand der SearchTutorAd-ID ein SearchTutorAd-Objekt in der Liste der gespeicherten SearchTutorAd-Objekte und gibt dieses zurueck. 
	 * @param id (als UUID) ID des zu suchenden Objekts
	 * @return Auction anhand der ID gefundene Auktion (wenn ID nicht auffindbar, dann wird null zurueckgegeben oder 
	 * eine Exception geworfen, die besagt, dass die Auktion nicht existiert).
	 */
	@Override
	public SearchTutorAd getSearchTutorAdById(UUID adId) {    // anhand der SearchTutorAdUUID, ein SearchTutorAd-Objekt zurückgeben. 
		if(adId==null) {
			throw new IllegalArgumentException("Übergebene ADID war NULL.");
		}
		
		ArrayList<SearchTutorAd> idsuchliste = getSearchTutorAdList();
		
		for(int i=0; i<idsuchliste.size();i++) {
			if(idsuchliste.get(i).getAdId().equals(adId)) {
				return idsuchliste.get(i);
			}
		}
		
		throw new IllegalArgumentException("Übergebene ADID ist nicht vorhanden."); 
		// Handling im Servlet in try-catch-clause notwendig!
	}
	
	/**
	 * getSearchTutorAdByUserIdDEMAND(UUID userIdDemand) sucht anhand der userIdDemand (Instanzvariable von SearchTutorAd-Objekt) 
	 * in der Liste der gespeicherten SearchTutorAd-Objekte jene Objekte, die von der übergebenen UUID userIdDemand nachgefragt wurden.
	 * @param userIdDemand die ID des Users, dessen Auktionen zurueckgegeben werden sollen
	 * @return ArrayList SearchTutorAd anhand der userIdDemand gefundene SearchTutorAd
	 */
	@Override 
	public ArrayList<SearchTutorAd> getSearchTutorAdByUserIdDemand(UUID userIdDemand){
		if(userIdDemand==null) {
			throw new IllegalArgumentException("Übergebene userIdDemand war NULL.");
		}
		
		ArrayList<SearchTutorAd> idsuchliste = getSearchTutorAdList();
		ArrayList<SearchTutorAd> adsCreatedByUserIdDemand = new ArrayList<SearchTutorAd>(); 
		
		for(int i=0; i<idsuchliste.size();i++) {
			if(idsuchliste.get(i).getUserIdDemand() != null && idsuchliste.get(i).getUserIdDemand().equals(userIdDemand)) {
				adsCreatedByUserIdDemand.add(idsuchliste.get(i));
			}
		}
		return adsCreatedByUserIdDemand;
	}
	
	/**
	 * getSearchTutorAdByUserIdSUPPLY(UUID userIdSupply) sucht anhand der userIdSupply (Instanzvariable von SearchTutorAd-Objekt) 
	 * in der Liste der gespeicherten SearchTutorAd-Objekte jene Objekte, die von der übergebenen UUID userIdSupply nachgefragt wurden.
	 * @param userIdSupply die ID des Users, dessen Auktionen zurueckgegeben werden sollen
	 * @return ArrayList SearchTutorAd anhand der userIdSupply gefundene SearchTutorAd
	 */
	@Override 
	public ArrayList<SearchTutorAd> getSearchTutorAdByUserIdSupply(UUID userIdSupply){
		if(userIdSupply==null) {
			throw new IllegalArgumentException("Übergebene userIdSupply war NULL.");
		}
		
		ArrayList<SearchTutorAd> idsuchliste = getSearchTutorAdList();
		ArrayList<SearchTutorAd> adsAnsweredByUserIdSupply = new ArrayList<SearchTutorAd>(); 

		//try {
		
		for(int i=0; i<idsuchliste.size();i++) {
			
			if(idsuchliste.get(i).getUserIdSupply() != null && idsuchliste.get(i).getUserIdSupply().equals(userIdSupply)) {
				adsAnsweredByUserIdSupply.add(idsuchliste.get(i));
			}
			//if (idsuchliste.get(i).getUserIdSupply() == null)
				//System.out.println("null " + i);
		}
		/*} catch (NullPointerException e) {
			e.printStackTrace();
		}*/
		return adsAnsweredByUserIdSupply;
	}
	
	// addSearchTutorAd-methode
	/**
	 * addSearchTutorAd(SearchTutorAd ad) fuegt ein neues Inserat hinzu. Falls ein SearchTutorAd-Objekt mit der gewuenschten ID schon gespeichert wurde, wird eine
	 * Exception geworfen. 
	 * @param ad (als SearchTutorAd)
	 * @throws IllegalArgumentException falls ein SearchTutorAd-Objekt mit einer bereits verwendeten ID uebergeben wurde
	 */
	@Override
	public void addSearchTutorAd(SearchTutorAd newad) {  // soll ein SearchTutorAd persistent speichern
		
		if(newad==null || newad.equals("")) {
			throw new IllegalArgumentException ("Die übergebene SearchTutorAd ist NULL, daher keine Speicherung möglich!");
		}
			
		for(int i=0; i<getSearchTutorAdList().size();i++){
			if ( (getSearchTutorAdList().get(i).getAdId().equals(newad.getAdId())) ){
				throw new IllegalArgumentException("Das zum Speichern uebergebene SearchTutorAd existiert schon!");
			}
		}

			ArrayList<SearchTutorAd> neueliste = new ArrayList<SearchTutorAd>();
			neueliste.addAll(getSearchTutorAdList());
			neueliste.add(newad);	
			
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
	
	// deleteSearchTutorAd-methode
	/**
	 * deleteSearchTutorAd(SearchTutorAd ad) loescht die uebergebene SearchTutorAd. Falls das SearchTutorAd-Objekt anhand seiner ID nicht auffindbar ist, wird eine
	 * Exception geworfen. 
	 * @param ad (als SearchTutorAd)
	 * @throws IllegalArgumentException falls das zu loeschende SearchTutorAd-Objekt nicht gefunden werden kann
	 */
	@Override
	public void deleteSearchTutorAd(SearchTutorAd adToDelete) {  // soll ein SearchTutorAd von der persistenten Datenquelle löschen
        	
		if(adToDelete==null || adToDelete.equals("")) {
			throw new IllegalArgumentException ("Das übergebene SearchTutorAd ist NULL, daher keine Löschung möglich!");
		}
		
		boolean checkAdToDeleteExists = false;
		for(int i=0; i<getSearchTutorAdList().size();i++){
			if ( (getSearchTutorAdList().get(i).getAdId().equals(adToDelete.getAdId())) ){
				checkAdToDeleteExists=true;
			}
		}
		
		if (checkAdToDeleteExists) {
			ArrayList<SearchTutorAd> neueliste = new ArrayList<SearchTutorAd>();
			neueliste.addAll(this.getSearchTutorAdList());
			
			for(int i=0; i<neueliste.size();i++) {
				if(neueliste.get(i).getAdId().equals(adToDelete.getAdId())) {
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
			throw new IllegalArgumentException ("Das zum Loeschen uebergebene SearchTutorAd existiert nicht!");
		}
			
	}
	
	// setAdMatched methode
	/**
	 * setAdMatched(UUID adId) setzt die Instanzvariable adMatched von false auf true.
	 * @param adId (als UUID)
	 * @throws IllegalArgumentException falls das zu loeschende SearchTutorAd-Objekt nicht gefunden werden kann
	 */
	@Override
	public void setAdMatched(UUID adId, UUID userIdSupply) {
		if (adId==null) throw new IllegalArgumentException("Ungueltige adId - adId ist NULL");

		boolean checkAdToModifyExists = false;
		for(int i=0; i<getSearchTutorAdList().size();i++){
			if (getSearchTutorAdList().get(i).getAdId().equals(adId)){
				checkAdToModifyExists=true;
			}
		}
		
		if (checkAdToModifyExists) {
			ArrayList<SearchTutorAd> neueliste = new ArrayList<SearchTutorAd>();
			neueliste.addAll(getSearchTutorAdList());
			
			for(int i=0; i<neueliste.size();i++) {
				if(neueliste.get(i).getAdId().equals(adId)) {
					SearchTutorAd adChanged=neueliste.get(i);
					adChanged.setAdMatched(true);
					adChanged.setUserIdSupply(userIdSupply);
					neueliste.set(i,adChanged);
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
	   	throw new IllegalArgumentException ("Das Matching hat nicht geklappt!");
		}
	}
		
}

