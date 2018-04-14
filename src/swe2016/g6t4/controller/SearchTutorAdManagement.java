package swe2016.g6t4.controller;

import java.util.ArrayList;
import java.util.UUID;

import swe2016.g6t4.model.SearchTutorAd;

/**
* Die Klasse SearchTutorAdManagement besitzt Methoden zum Zugriff und Ausführen von Aktivitäten bzgl. SearchTutorAd-Objekten und deren Hinzufügen, Löschen etc. 
* @author  Team 4 (Gruppe 6)
* @version 1.0
* @since   2016-12-17
*/
public class SearchTutorAdManagement {
		
		/**
		 * Die Instanzvariable des SearchTutorAdManagement-Objekt: SearchTutorAddao (AuctionDAO).
		 */	
		private SearchTutorAdDAO searchtutoraddao;
		
		/**
		 * Der Konstruktor AuctionManagement setzt die Instanzvariablen mittels Set-Methoden.
		 */	
		public SearchTutorAdManagement(){
			setSearchTutorAdDAO();
		}
		
		// Getter & Setter		
		/**
		 * Get-Methode fuer Instanzvariable searchtutoraddao.
		 * @return searchtutoraddao  als AuctionDAO.
		 */
		public SearchTutorAdDAO getSearchtutoraddao() {
			return searchtutoraddao;
		}

		/**
		 * Set-Methode fuer Instanzvariable searchtutoraddao. 
		 */
		public void setSearchTutorAdDAO() {
			this.searchtutoraddao = new SerializedSearchTutorAdDAO();
		}

		// Methoden
		/**
		 *Anzeigen der SearchTutorAdList von SerializedDAO.
		 * @return SearchTutorAdList als ArrayList.
		 */
		public ArrayList<SearchTutorAd> getSearchTutorAdList() {
			return searchtutoraddao.getSearchTutorAdList();
		}
		
		/**
		 * Die addSearchTutorAd(SearchTutorAd ad)-Methode fügt eine neue SearchTutorAd hinzu zur SearchTutorAdList. 
		 * @param ad neue SearchTutorAd (als SearchTutorAd).  
		 */
		public void addSearchTutorAd(SearchTutorAd ad){

			if(ad.getAdId()==null) {
				throw new IllegalArgumentException("SearchTutorAd darf nicht leer sein!");
			}
					
			searchtutoraddao.addSearchTutorAd(ad);
	    }
		
		/**
		 * Die deleteSearchTutorAd(SearchTutorAd ad)-Methode loescht eine bestehende SearchTutorAd aus der SearchTutorAdList. 
		 * @param ad zu loeschende SearchTutorAd (als SearchTutorAd).  
		 */
		public void deleteSearchTutorAd(SearchTutorAd ad){
			searchtutoraddao.deleteSearchTutorAd(ad);
		}
		

		/**
		 * Die searchAdByKeyword(String keyword) sucht in der SearchTutorAdList Inserate nach einem bestimmten keyword in 
		 * der Instanzvariable course ab und gibt eine Liste mit SearchTutorAds, die das keyword enthalten, zurueck. 
		 * @param keyword Suchwort (als String).
		 */
		public ArrayList<SearchTutorAd> searchAdByKeyword(String keyword){		
			ArrayList<SearchTutorAd> adlist=searchtutoraddao.getSearchTutorAdList();
			ArrayList<SearchTutorAd> adlistSearchedKeyword=new ArrayList<SearchTutorAd>();

			for(int i=0; i<adlist.size();i++){
				if(adlist.get(i).getCourse().toLowerCase().contains(keyword.toLowerCase())){
					adlistSearchedKeyword.add(adlist.get(i));
				}
			}
			
			return adlistSearchedKeyword;
		}
			
		/**
		 * Die setMatched(SearchTutorAd ad)-Methode setzt die Instanzvariable adMatched auf TRUE. 
		 * @param ad (als SearchTutorAd).
		 */
		public void setMatched(UUID adId, UUID userIdSupply){
			searchtutoraddao.setAdMatched(adId, userIdSupply);
		}
		
		 /**
		 * Die getAllActiveSearchTutorAds()-Methode gibt eine Liste aller nicht-matched Inserate aus.
		 * @return ArrayList (vom Typ SearchTutorAd).
		 */
		public ArrayList<SearchTutorAd> getAllActiveSearchTutorAds(){
			
			ArrayList<SearchTutorAd> allactivelist=new ArrayList<SearchTutorAd>();
			
			ArrayList<SearchTutorAd> alladslist=searchtutoraddao.getSearchTutorAdList();
			
			for(int i=0; i<alladslist.size();i++){
				if(alladslist.get(i).isAdMatched()==false){
					allactivelist.add(alladslist.get(i));
				}
			}

			return allactivelist;
		}
		
		 /**
		 * Die getAllMatchedSearchTutorAds()-Methode gibt eine Liste aller matched Inserate aus.
		 * @return ArrayList (vom Typ SearchTutorAd).
		 */
		public ArrayList<SearchTutorAd> getAllMatchedSearchTutorAds(){
			
			ArrayList<SearchTutorAd> allmatchedlist=new ArrayList<SearchTutorAd>();
			
			ArrayList<SearchTutorAd> alladslist=searchtutoraddao.getSearchTutorAdList();
			
			for(int i=0; i<alladslist.size();i++){
				if(alladslist.get(i).isAdMatched()){
					allmatchedlist.add(alladslist.get(i));
				}
			}

			return allmatchedlist;
		}
		
		
		 /**
		 * Die checkMatched(SearchTutorAd ad)-Methode schaut, ob die übergebene SearchTutorAd ablaufen ist.
		 * @param ad (als SearchTutorAd)
		 * @return matched (als boolean).
		 */
		public boolean checkMatched(SearchTutorAd ad){

			SearchTutorAd adCheckMatched=searchtutoraddao.getSearchTutorAdById(ad.getAdId());
		
			return adCheckMatched.isAdMatched();
		}
		
		 /**
		 * Die getSearchTutorAdById(UUID adId)-Methode sucht zu einer übergebenen SearchTutorAdID die SearchTutorAd.
		 * @param adId (als UUID).
		 * @return adById (als SearchTutorAd).
		 */
		public SearchTutorAd getSearchTutorAdById(UUID id){
			return searchtutoraddao.getSearchTutorAdById(id);
		}
		
		 /**
		 * getSearchTutorAdByUserIdSUPPLY(UUID userIdSupply) sucht anhand der userIdSupply (Instanzvariable von SearchTutorAd-Objekt) 
		 * in der Liste der gespeicherten SearchTutorAd-Objekte jene Objekte, die von der übergebenen UUID userIdSupply nachgefragt wurden.
		 * @param userIdSupply die ID des Users, dessen Auktionen zurueckgegeben werden sollen
		 * @return ArrayList SearchTutorAd anhand der userIdSupply gefundene SearchTutorAd
		 */
		public ArrayList<SearchTutorAd> getSearchTutorAdByUserIdSupply(UUID userIdSupply){
			return searchtutoraddao.getSearchTutorAdByUserIdSupply(userIdSupply);
		}

		/**
		 * getSearchTutorAdByUserIdDEMAND(UUID userIdDemand) sucht anhand der userIdDemand (Instanzvariable von SearchTutorAd-Objekt) 
		 * in der Liste der gespeicherten SearchTutorAd-Objekte jene Objekte, die von der übergebenen UUID userIdDemand nachgefragt wurden.
		 * @param userIdDemand die ID des Users, dessen Auktionen zurueckgegeben werden sollen
		 * @return ArrayList SearchTutorAd anhand der userIdDemand gefundene SearchTutorAd
		 */
		public ArrayList<SearchTutorAd> getSearchTutorAdByUserIdDemand(UUID userIdDemand){
			return searchtutoraddao.getSearchTutorAdByUserIdDemand(userIdDemand);
		}

}
