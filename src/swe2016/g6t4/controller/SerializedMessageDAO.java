package swe2016.g6t4.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.UUID;

import swe2016.g6t4.model.Message;

/**
 * Die Klasse {@code SerializedMessageDAO implementiert das Interface {@code MessageDAO}. Zur persistenten Speicherung von
 * Objekten der Klasse {@code Message} wird die Java-Standardserialisierung verwendet.
 * @author  Team 4 (Gruppe 6)
 * @version 1.0
 * @since   2016-12-01 
 * @see Message
 * @see MessageDAO
 * 
 */
public class SerializedMessageDAO implements MessageDAO {
	
	/**Eine Liste, die zur Verwaltung der gespeicherten Message-Objekte verwendet wird */
	private ArrayList<Message> messageList;
	/**Der Name der Datei, in der die Message-Objekte gespeichert werden*/
	private String filename;
	
	/**
	 * Die Liste aller derzeit gespeicherten Message-Objekte wird aus der Datenquelle ausgelesen und der Instanzvariable
	 * {@code messageList} zugewiesen. Sollten Probleme beim Dateizugriff auftreten oder die Datei nicht existieren, wird
	 * {@code messageList} eine leere Liste zugewiesen.
	 */
	@SuppressWarnings("unchecked")
	private void updateMessageList(){
		File file = new File(filename);
		if (file.canRead()){
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
				messageList = (ArrayList<Message>) ois.readObject();
				ois.close();
			}catch (Exception e){
				e.printStackTrace();
				messageList = new ArrayList<Message>();
			}
		}
		else {
			messageList = new ArrayList<Message>();
		}
	}
	
	/**
	 * Die von der Instanzvariable {@code messageList} referenzierte Liste wird persistent gespeichert.
	 */
	private void saveMessageList(){
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
			oos.writeObject(messageList);
			oos.close();			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Im Konstruktor werden den Instanzvariablen Werte zugewiesen. Der Instanzvariable {@code filename} wird der Name der Datei
	 * zugewiesen, in der die Message-Objekte gespeichert werden sollen. Der Instanzvariable {@code messageList} wird eine leere
	 * Liste zugewiesen.
	 */
	public SerializedMessageDAO(){
		filename="message.ser";
		messageList = new ArrayList<Message>();
	}

	@Override
	public ArrayList<Message> getAllMessages() {
		updateMessageList();
		return messageList;
	}

	@Override
	public Message getMessageById(UUID id) throws IllegalArgumentException {
		if (id==null) throw new IllegalArgumentException("Invalid ID");
		updateMessageList();
		for (Message message: messageList){
			if (message.getId().equals(id)) return message;
		}
		throw new IllegalArgumentException("Message not found");
	}

	@Override
	public ArrayList<Message> getMessagesBySender(String sender) {
		if ((sender==null) || sender.trim().equals("")) throw new IllegalArgumentException("Invalid Sender");
		ArrayList<Message> senderList = new ArrayList<Message>();
		updateMessageList();
		for (Message message: messageList){
			if (message.getSender().equals(sender)) senderList.add(message);
		}
		return senderList;
	}

	@Override
	public ArrayList<Message> getMessagesByReceiver(String receiver) {
		if ((receiver==null) || receiver.trim().equals("")) throw new IllegalArgumentException("Invalid Receiver");
		//Feedback-Nachrichten: receiver ist null, werden aber ueber getFeedback() abgefragt
		ArrayList<Message> receiverList = new ArrayList<Message>();
		updateMessageList();
		for (Message message: messageList){
			if ((message.getReceiver()!=null) && (message.getReceiver().equals(receiver))) receiverList.add(message);
		}
		return receiverList;
	}

	@Override
	public ArrayList<Message> getFeedback() {
		ArrayList<Message> feedbackList = new ArrayList<Message>();
		updateMessageList();
		for (Message message: messageList){
			if (message.isFeedback()) feedbackList.add(message);
		}
		return feedbackList;
	}

	@Override
	public void addMessage(Message message) throws IllegalArgumentException {
		if (message==null) throw new IllegalArgumentException("NULL cannot be saved");
		updateMessageList();
		for (Message m: messageList){
			if (m.getId().equals(message.getId())){
				throw new IllegalArgumentException("Message cannot be saved - ID not available");
			}
		}
		messageList.add(message);
		saveMessageList();
	}

	@Override
	public void deleteMessage(Message message) throws IllegalArgumentException {
		if (message==null) throw new IllegalArgumentException("NULL cannot be deleted");
		updateMessageList();
		for (Message m: messageList){
			if (m.getId().equals(message.getId())){
				messageList.remove(m);
				saveMessageList();
				return;
			}
		}
		throw new IllegalArgumentException("Message not found - could not be deleted");
	}
	
	public void modifyRead(Message message, boolean read) throws IllegalArgumentException {
		updateMessageList();
		for (Message m: messageList){
			if (m.getId().equals(message.getId())){
				m.setRead(read);
				saveMessageList();
				return;
			}
		}
		throw new IllegalArgumentException("Message not found - READ could not be changed");
	}

	
}
