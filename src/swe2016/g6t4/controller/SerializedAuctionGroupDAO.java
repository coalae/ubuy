package swe2016.g6t4.controller;

import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

/**
* Die Klasse SerializedAuctionGroupDAO implementiert die Methoden zum Einlesen und Speichern der AuctionGroupList, in der alle bestehenden Auktionsgruppen
* verwaltet werden. 
* Die Klasse implementiert das Interface AuctionGroupDAO.
* @author  Team 4 (Gruppe 6)
* @version 1.0
* @since   2016-12-01 
*/
public class SerializedAuctionGroupDAO implements AuctionGroupDAO, Serializable {

	private static final long serialVersionUID = 1L;
	// instanzvariable
	/**Der Name der Datei, in der die Liste der Auktionsgruppen gespeichert werden soll.**/
	private String dateinamepfad;
	/**Eine Liste von String-Objekten, die verwendet wird, um die gespeicherten Auktionsgruppen zu verwalten.**/
	private ArrayList<String> speicherliste; 
	
	// constructor (speicherung in file)
	/**Im Konstruktor wird der Name der Datei, in der die AuctionGroup-Objekte gespeichert werden sollen, festgelegt sowie der Instanzvariable
	 * eine leere Liste zugewiesen. Falls die Datei noch nicht existiert, wird sie angelegt.**/
	public SerializedAuctionGroupDAO() {
		this.dateinamepfad="auctiongrouplist2.ser"; 
		speicherliste=new ArrayList<String>();
	}
	
	// implementierte methoden des interface
	// getAuctionGroupList-methode
	/**
	 * getAuctionGroupList() gibt die Liste aller AuctionGroups zurueck. 
	 * @return ArrayList Liste aller AuctionGroups.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getAuctionGroupList() { // Liste (z. B. java.util.ArrayList) benutzen, gespeicherten Stringobjekten return
		File datei = new File(dateinamepfad);
		if(datei.exists() && datei.canRead()) {
			try {
			FileInputStream fileinput = new FileInputStream(dateinamepfad);
			ObjectInputStream objectinput = new ObjectInputStream(fileinput);
			
			if(objectinput!=null) {
				speicherliste = (ArrayList<String>) objectinput.readObject(); 
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
	
	// addAuctionGroup-methode
	/**
	 * addAuctionGroup(String groupname) fuegt eine neue AuctionGroup hinzu. Falls eine AuctionGroup mit dem gewuenschten Namen schon 
	 * existiert, wird eine Exception geworfen. 
	 * @param groupname (als String) der Name der AuctionGroup, die hinzugefuegt werden soll
	 */
	@Override
	public void addAuctionGroup(String newauctiongroup) {  // soll eine auctiongroup (String) persistent speichern
		
		if(newauctiongroup==null || newauctiongroup.equals("")) {
			throw new IllegalArgumentException ("Die übergebene AuctionGroup ist NULL, daher keine Speicherung möglich!");
		}
			
		for(int i=0; i<this.getAuctionGroupList().size(); i++){
			if(this.getAuctionGroupList().get(i).equals(newauctiongroup)) {
				throw new IllegalArgumentException ("Die übergebene AuctionGroup existiert schon, daher keine Speicherung möglich!");
			}
			
		}
		
		ArrayList<String> neueliste = new ArrayList<String>();
		neueliste.addAll(getAuctionGroupList());
		neueliste.add(newauctiongroup);	
			
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
	
	// deleteAuctionGroup-methode
	/**
	 * deleteAuctionGroup(String groupname) loescht die uebergebene AuctionGroup. Falls die AuctionGroup nicht vorhanden ist,
	 * wird eine Exception geworfen. 
	 * @param groupname (als String) der Name der zu loeschenden AuctionGroup
	 */
	@Override
	public void deleteAuctionGroup(String groupToDelete) {  // soll eine AuctionGroup von der persistenten Datenquelle löschen
        	
		if(groupToDelete==null || groupToDelete.equals("")) {
			throw new IllegalArgumentException ("Die übergebene AuctionGroup ist NULL, daher keine Löschung möglich!");
		}
		if(groupToDelete.equals("Lernmaterial") || groupToDelete.equals("Nachhilfe")) {
			throw new IllegalArgumentException ("Gruppe Lernmaterial und Nachhilfe müssen vorhanden sein, daher keine Löschung möglich!");
		}
		
		boolean groupToDeleteExists=false;
		for(int i=0; i<this.getAuctionGroupList().size(); i++){
			if(this.getAuctionGroupList().get(i).equals(groupToDelete)) {
				groupToDeleteExists=true;
			}
		}
			
		if(groupToDeleteExists==true) {
				ArrayList<String> neueliste = new ArrayList<String>();
				neueliste.addAll(getAuctionGroupList());
					
					for(int j=0; j<neueliste.size();j++) {
						if(neueliste.get(j).equals(groupToDelete)) {
							neueliste.remove(j);	  
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
			throw new IllegalArgumentException("Die zum Löschen übergebene AuctionGroup existiert nicht!");
		}
	}
	
}

	
	
			