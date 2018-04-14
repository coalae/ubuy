package swe2016.g6t4.controller;

import java.util.ArrayList;
import java.util.UUID;
import swe2016.g6t4.model.Message;

/**
* Das Interface {@code MessageDAO} deklariert generische Methoden zum Einlesen und Speichern von Message-Objekten.
* @author  Team 4 (Gruppe 6)
* @version 1.0
* @since   2016-12-01 
*/
public interface MessageDAO {
	
	/**
	 * Gibt alle gespeicherten Message-Objekte zurueck. Falls keine Objekte gespeichert sind, wird eine leere Liste zurueckgegeben.
	 * @return eine Liste aller gespeicherten Nachrichten
	 */
	public ArrayList<Message> getAllMessages();
	
	/**
	 * Sucht ein Message-Objekt anhand seiner ID. Falls das Objekt nicht existiert, wird eine Exception geworfen.
	 * @param id die ID der gesuchten Nachricht
	 * @return das Message-Objekt, dessen ID mit dem uebergebenen Parameter uebereinstimmt
	 * @throws IllegalArgumentException falls keine Nachricht mit der uebergebenen ID gespeichert ist
	 */
	public Message getMessageById(UUID id) throws IllegalArgumentException;
	
	/**
	 * Gibt alle Nachrichten zurueck, die von einem bestimmten User erstellt wurden. Falls der User keine Nachrichten
	 * erstellt hat, wird eine leere Liste zurueckgegeben.
	 * @param sender der Username des Users, dessen erstellte Nachrichten gesucht werden sollen
	 * @return eine Liste aller Nachrichten des Users
	 */
	public ArrayList<Message> getMessagesBySender(String sender);
	
	/**
	 * Gibt alle Nachrichten zurueck, die an einen bestimmten User verschickt wurden. Falls keine Nachrichten an den User
	 * geschickt wurden, wird eine leere Liste zurueckgegeben.
	 * @param receiver der Username des Users, dessen empfangene Nachrichten gesucht werden sollen
	 * @return eine Liste aller an den User versendeten Nachrichten
	 */
	public ArrayList<Message> getMessagesByReceiver(String receiver);
	
	/**
	 * Gibt alle Nachrichten zurueck, die Feedback an die Admins enthalten. Falls keine Feedback-Nachrichten existieren,
	 * wird eine leere Liste zurueckgegeben.
	 * @return eine Liste aller Feedback-Nachrichten
	 */
	public ArrayList<Message> getFeedback();
	
	/**
	 * Fuegt ein neues Message-Objekt hinzu. Die ID des Objekts darf nicht bereits verwendet werden.
	 * @param message das zu speichernde Message-Objekt
	 * @throws IllegalArgumentException falls die ID der uebergebenen Nachricht bereits verwendet wird
	 */
	public void addMessage(Message message) throws IllegalArgumentException;
	
	/**
	 * Loescht ein Message-Objekt. Das Message-Objekt, dessen ID der ID des uebergebenen Objekts entspricht, wird geloescht.
	 * @param message die Nachricht, die geloescht werden soll
	 * @throws IllegalArgumentException falls die zu loeschende Nachricht nicht vorhanden ist
	 */
	public void deleteMessage(Message message) throws IllegalArgumentException;
	
	/**
	 * Aendert die Instanzvariable {@code read} einer gespeicherten Nachricht.
	 * @param message die Nachricht, die geaendert werden soll
	 * @param read der Wert, der der Instanzvariable {@code read} zugewiesen werden soll
	 * @throws IllegalArgumentException falls die Nachricht nicht vorhanden ist
	 */
	public void modifyRead(Message message, boolean read) throws IllegalArgumentException;
	
	

}
