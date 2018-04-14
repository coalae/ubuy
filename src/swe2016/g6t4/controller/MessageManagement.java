package swe2016.g6t4.controller;

import swe2016.g6t4.model.Message;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Die Klasse {@code MessageManagement} dient der Bearbeitung und Verwaltung von Objekten der Klasse {@code Message}.
 * @author  Team 4 (Gruppe 6)
 * @version 1.0
 * @since   2016-12-01
 * @see Message
 */
public class MessageManagement {
	
	/**Instanzvariable, die den Zugriff auf persistent gespeicherte Message-Objekte ermoeglicht */
	private MessageDAO messagedao;
	
	/**
	 * Im Konstruktor wird der Instanzvariable {@code messagedao} ein Objekt der Klasse {@code SerializedMessageDAO} zugewiesen.
	 * @see SerializedMessageDAO
	 */
	public MessageManagement(){
		messagedao = new SerializedMessageDAO();
	}
	
	/**
	 * Gibt den Wert der Instanzvariable {@code messagedao} zurueck.
	 * @return der Wert der Instanzvariable {@code messagedao}
	 */
	public MessageDAO getMessageDAO(){
		return messagedao;
	}
	
	/**
	 * Weist der Instanzvariable {@code messagedao} ein neues Objekt zu.
	 * @param messagedao das Objekt, das der Instanzvariable {@code messagedao} zugewiesen werden soll.
	 * @throws IllegalArgumentException falls {@code null} als Parameter uebergeben wurde
	 */
	public void setMessageDAO(MessageDAO messagedao) throws IllegalArgumentException{
		if (messagedao==null) throw new IllegalArgumentException("MessageDAO cannot be changed to NULL");
		this.messagedao=messagedao;
	}
	
	/**
	 * Gibt eine Liste aller gespeicherten Message-Objekte zurueck. Falls keine Objekte gespeichert sind, wird eine leere Liste zurueckgegeben.
	 * @return eine Liste aller gespeicherten Nachrichten
	 */
	public ArrayList<Message> getAllMessages(){
		return messagedao.getAllMessages();
	}
	
	/**
	 * * Sucht ein Message-Objekt anhand seiner ID. Falls das Objekt nicht existiert, wird eine Exception geworfen.
	 * @param id die ID der gesuchten Nachricht
	 * @return das Message-Objekt, dessen ID mit dem uebergebenen Parameter uebereinstimmt
	 */
	public Message getMessageById(UUID id) {
		return messagedao.getMessageById(id);
	}
	
	/**
	 * Gibt alle Nachrichten zurueck, die von einem bestimmten User erstellt wurden. Falls der User keine Nachrichten
	 * erstellt hat, wird eine leere Liste zurueckgegeben.
	 * @param sender der Username des Users, dessen erstellte Nachrichten gesucht werden sollen
	 * @return eine Liste aller Nachrichten des Users
	 */
	public ArrayList<Message> getMessagesBySender(String sender){
		return messagedao.getMessagesBySender(sender);
	}
	
	/**
	 * Gibt alle Nachrichten zurueck, die von dem User mit dem als Parameter uebergebenen Usernamen verfasst worden sind
	 * und keine Feedback-Nachrichten sind. Falls keine derartigen Nachrichten existieren, wird eine leere Liste zurueckgegeben.
	 * @param sender der Username des Users, dessen erstellte Nachrichten gesucht werden sollen
	 * @return eine Liste aller privaten Nachrichten, die von dem User erstellt wurden
	 */
	public ArrayList<Message> getPrivateMessagesBySender(String sender){
		ArrayList<Message> allMessages = messagedao.getMessagesBySender(sender);
		ArrayList<Message> privateMessages = new ArrayList<Message>();
		for(Message message: allMessages){
			if (!message.isFeedback()) privateMessages.add(message);
		}
		return privateMessages;
	}
	
	/**
	 * Gibt alle Feedback-Nachrichten zurueck, die von dem User mit dem als Parameter uebergebenen Usernamen verfasst worden sind.
	 * Falls keine derartigen Nachrichten existieren, wird eine leere Liste zurueckgegeben.
	 * @param sender der Username des Users, dessen erstellte Feedback-Nachrichten gesucht werden sollen
	 * @return eine Liste aller Feedback-Nachrichten, die von dem User erstellt wurden
	 */
	public ArrayList<Message> getFeedbackMessagesBySender(String sender){
		ArrayList<Message> allMessages = messagedao.getMessagesBySender(sender);
		ArrayList<Message> feedbackMessages = new ArrayList<Message>();
		for(Message message: allMessages){
			if (message.isFeedback()) feedbackMessages.add(message);
		}
		return feedbackMessages;
	}
	
	/**
	 * Gibt alle Nachrichten zurueck, die an einen bestimmten User verschickt wurden. Falls keine Nachrichten an den User
	 * geschickt wurden, wird eine leere Liste zurueckgegeben.
	 * @param receiver der Username des Users, dessen empfangene Nachrichten gesucht werden sollen
	 * @return eine Liste aller an den User versendeten Nachrichten
	 */
	public ArrayList<Message> getMessagesByReceiver(String receiver) {
		return messagedao.getMessagesByReceiver(receiver);
	}
	
	/**
	 * Gibt alle Nachrichten zurueck, die Feedback an die Admins enthalten. Falls keine Feedback-Nachrichten existieren,
	 * wird eine leere Liste zurueckgegeben.
	 * @return eine Liste aller Feedback-Nachrichten
	 */
	public ArrayList<Message> getFeedback() {
		return messagedao.getFeedback();
	}
	
	/**
	 * Fuegt ein neues Message-Objekt zu den gespeicherten Nachrichten hinzu.
	 * @param message die neu zu speichernde Nachricht
	 */
	public void addMessage(Message message){
		messagedao.addMessage(message);
	}
	
	/**
	 * Loescht ein Message-Objekt aus der Datenquelle.
	 * @param message die zu loeschende Nachricht
	 */
	public void deleteMessage(Message message){
		messagedao.deleteMessage(message);
	}
	
	/**
	 * Aendert die Instanzvariable {@code read} einer gespeicherten Nachricht.
	 * @param message die Nachricht, die geaendert werden soll
	 * @param read der Wert, der der Instanzvariable {@code read} zugewiesen werden soll
	 */
	public void modifyRead(Message message, boolean read){
		messagedao.modifyRead(message, read);
	}
	
	/**
	 * Gibt die Anzahl der gespeicherten Message-Objekte zurueck.
	 * @return die Anzahl der gespeicherten Nachrichten
	 */
	public int getNumberOfAllMessages(){
		return messagedao.getAllMessages().size();
	}
	
	/**
	 * Gibt die Anzahl der gespeicherten Feedback-Nachrichten zurueck.
	 * @return die Anzahl der gespeicherten Feedback-Nachrichten
	 */
	public int getNumberOfFeedbackMessages(){
		int count = 0;
		ArrayList<Message> list = messagedao.getAllMessages();
		for (Message message: list){
			if (message.isFeedback()) count++;
		}
		return count;
	}
	
	/**
	 * Gibt die Anzahl der Nachrichten zurueck, die an den User mit dem als Parameter uebergebenen Usernamen verschickt
	 * wurden und noch nicht gelesen sind.
	 * @param receiver der Username des gewuenschten Users
	 * @return Anzahl der ungelesenen Nachrichten an den User
	 */
	public int getNumberOfUnreadMessages(String receiver){
		if ( (receiver==null) || (receiver.trim().equals("")) ) throw new IllegalArgumentException("Invalid Receiver"); 
		int count = 0;
		ArrayList<Message> list = messagedao.getAllMessages();
		for (Message message: list){
			if ( message.getReceiver()!=null && message.getReceiver().equals(receiver) && !(message.isRead()) ) count++;
		}
		return count;
	}
	
	/**
	 * Gibt die Anzahl der ungelesenen Feedback-Nachrichten zurueck.
	 * @return die Anzahl der ungelesenen Feedback-Nachrichten
	 */
	public int getNumberOfUnreadFeedbackMessages(){
		int count = 0;
		ArrayList<Message> list = messagedao.getAllMessages();
		for (Message message: list){
			if ( message.isFeedback() && !(message.isRead()) ) count++;
		}
		return count;
	}



}
