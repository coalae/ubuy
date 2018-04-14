package swe2016.g6t4.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * Objekte der Klasse {@code Message} repraesentieren Nachrichten, die von Usern versendet werden. Es kann sich dabei entweder
 * um private Nachrichten handeln, die an einen einzelnen User adressiert sind, oder um Feedback-Nachrichten.
 * Die Klasse implementiert das Interface {@code Serializable}.
 * @author  Team 4 (Gruppe 6)
 * @version 1.0
 * @since   2016-30-11 
 *
 */
public class Message implements Serializable {
	
	/** die serialVersionUID des Message-Objekts (da die Klasse das Interface {@code Serializable} implementiert) */
	private static final long serialVersionUID = 1L;
	/**die ID des Message-Objekts */
	private UUID id;
	/**der Username des Senders der Nachricht	 */
	private String sender; //username of sender
	/**der Username des Empfaengers der Nachricht */
	private String receiver; //username of receiver
	/**enthaelt Datum und Uhrzeit, zu der die Nachricht versendet wurde */
	private Calendar sent;
	/**der Betreff der Nachricht */
	private String subject;
	/**der Text der Nachricht */
	private String text;
	/**gibt an, ob es sich um eine Feedback-Nachricht handelt*/
	private boolean feedback;
	/**gibt an, ob die Nachricht bereits gelesen wurde*/
	private boolean read;
	
	/**
	 * Im Konstruktor werden den Instanzvariablen Werte durch Aufruf der entsprechenden Set-Methoden zugewiesen.
	 * @param id die ID des Objekts
	 * @param sender der Username des Senders
	 * @param receiver der Username des Empfaengers
	 * @param subject der Betreff der Nachricht
	 * @param text der Text der Nachricht
	 * @throws IllegalArgumentException falls Parameter mit ungueltigen Werten uebergeben wurden
	 */
	public Message(UUID id, String sender, String receiver, String subject, String text) throws IllegalArgumentException{
		setId(id);
		setSender(sender);
		setReceiver(receiver);
		setSent();
		setSubject(subject);
		setText(text);
		setRead(false);
		/*Standardzuweisungen fuer neu erstellte Nachrichten: sent ist immer momentaner Zeitpunkt, read immer false;
		Zuweisung von anderen Werten an diese Instanzvariablen im Konstruktor nicht vorgesehen
		setFeedback(...) wird innerhalb von setReceiver(...) aufgerufen, um Konsistenz zu gewaehrleisten (fuer Feedbacknachrichten
		muss Receiver immer null sein)
		*/
	}
	
	/**
	 * Gibt den Wert der Instanzvariable {@code id} zurueck.
	 * @return die ID der Nachricht
	 */
	public UUID getId(){
		return id;
	}
	/**
	 * Weist der Instanzvariable {@code id} einen neuen Wert zu.
	 * @param id die UUID, die als ID des Objekts festgelegt werden soll
	 * @throws IllegalArgumentException falls {@code null} als Parameter uebergeben wurde.
	 */
	public void setId(UUID id) throws IllegalArgumentException {
		if (id==null) throw new IllegalArgumentException("ID must not be NULL");
		this.id = id;
	}
	
	/**
	 * Gibt den Wert der Instanzvariable {@code sender} zurueck.
	 * @return der Username des Senders der Nachricht
	 */
	public String getSender() {
		return sender;
	}
	/**
	 * Weist der Instanzvariable {@code Sender} einen neuen Wert zu.
	 * @param sender der Username, der als Name des Senders der Nachricht festgelegt werden soll
	 * @throws IllegalArgumentException falls {@code null} oder ein leerer String als Parameter uebergeben wurde
	 */
	public void setSender(String sender) throws IllegalArgumentException {
		if ( (sender == null) || (sender.trim().equals("")) ) throw new IllegalArgumentException("Sender must not be empty");
		this.sender = sender;		
	}
	
	/**
	 * Gibt den Wert der Instanzvariable {@code receiver} zurueck.
	 * @return der Username des Senders der Nachricht oder {@code null}, falls es sich um eine Feedback-Nachricht handelt
	 */
	public String getReceiver() {
		return receiver;
	}
	/**
	 * Weist der Instanzvariable {@code receiver} einen neuen Wert zu.
	 * @param receiver der Username, der als Name des Empfaengers der Nachricht festgelegt werden soll. Falls {@code null} als
	 * Parameter uebergeben wurde, wird die Nachricht als Feedback-Nachricht interpretiert; daher wird hier die Methode
	 * {@code setFeedback(boolean)} aufgerufen.
	 * @throws IllegalArgumentException falls ein leerer String als Parameter uebergeben wurde.
	 * @see #setFeedback(boolean)
	 */
	public void setReceiver(String receiver) throws IllegalArgumentException {
		if (receiver==null){
			this.receiver=null;
			setFeedback(true);
		}
		else{
			if (receiver.trim().equals("")) throw new IllegalArgumentException("Receiver must not be empty");
			this.receiver=receiver;
			setFeedback(false);
		}
	}
	
	/**
	 * Gibt den Wert der Instanzvariable {@code sent} zurueck.
	 * @return ein Calendar-Objekt, das den Zeitpunkt des Versendens der Nachricht enthaelt
	 */
	public Calendar getSent() {
		return sent;
	}
	/**
	 * Weist der Instanzvariable {@code sent} ein Calendar-Objekt zu, das den momentanen Zeitpunkt repraesentiert.
	 */
	public void setSent() {
		sent = Calendar.getInstance();
	}
	/**
	 * Weist der Instanzvariable {@code sent} den Wert des uebergebenen Parameters zu. Diese Methode dient der nachtraeglichen 
	 * Bearbeitung zu Testzwecken; im Konstruktor wird die Methode {@code setSent()} aufgerufen.
	 * @param sent Calendar-Objekt, das der Instanzvariable {@code sent} zugewiesen werden soll
	 * @see #setSent()
	 */
	public void setSent(Calendar sent) throws IllegalArgumentException {
		Calendar now = Calendar.getInstance();
		if (   (sent==null) || (sent.after(now))  ) throw new IllegalArgumentException("Invalid Date");
		this.sent = sent;
	}
	
	/**
	 * Gibt den Wert der Instanzvariable {@code subject} zurueck.
	 * @return der Betreff der Nachricht
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * Weist der Instanzvariable {@code subject} den Wert des uebergebenen Parameters zu.
	 * @param subject der String, der als Betreff der Nachricht festgelegt werden soll
	 * @throws IllegalArgumentException falls {@code null} als Parameter uebergeben wurde.
	 */
	public void setSubject(String subject) {
		if (subject==null) throw new IllegalArgumentException("Subject must not be NULL");
		this.subject = subject;
	}
	
	/**
	 * Gibt den Wert der Instanzvariable {@code text} zurueck.
	 * @return der Text der Nachricht
	 */
	public String getText() {
		return text;
	}
	/**
	 * Weist der Instanzvariable {@code text} den Wert des uebergebenen Parameters zu.
	 * @param text der String, der als Text der Nachricht festgelegt werden soll
	 * @throws IllegalArgumentException falls {@code null} als Parameter uebergeben wurde.
	 */
	public void setText(String text) {
		if (text==null) throw new IllegalArgumentException("Text must not be NULL");
		this.text = text;
	}
	
	/**
	 * Gibt den Wert der Instanzvariable {@code feedback} zurueck.
	 * @return true, falls es sich um eine Feedback-Nachricht handelt, ansonsten false 
	 */
	public boolean isFeedback() {
		return feedback;
	}
	/**
	 * Weist der Instanzvariable {@code feedback} den Wert des uebergebenen Parameters zu. Diese Methode wird innerhalb von
	 * {@code setReceiver(String)} aufgerufen, da Konsistenz zwischen den Werten der Instanzvariablen {@code feedback} und
	 * {@code receiver} gewaehrleistet sein muss. Ein gesonderter Aufruf ist im Allgemeinen nicht sinnvoll.
	 * @param feedback true, falls es sich um eine Feedback-Nachricht handelt, ansonsten false
	 * @throws IllegalArgumentException falls der Wert des uebergebenen Parameters inkonsistent mit dem Wert der Instanzvariable
	 * {@code receiver} ist
	 * @see #setReceiver(String)
	 */
	public void setFeedback(boolean feedback) throws IllegalArgumentException {
		if (   (feedback && (receiver==null))     ||        (!feedback && (receiver!=null))    ) {
			this.feedback = feedback;
		}
		else throw new IllegalArgumentException("Feedback cannot be changed");
	}

	/**
	 * Gibt den Wert der Instanzvariable {@code read} zurueck.
	 * @return true, falls die Nachricht schon gelesen wurde, ansonsten false
	 */
	public boolean isRead() {
		return read;
	}
	/**
	 * Weist der Instanzvariable {@code read} den Wert des uebergebenen Parameters zu.
	 * @param read true, falls die Nachricht bereits gelesen wurde, ansonsten false
	 */
	public void setRead(boolean read) {
		this.read = read;
	}
	
	//fuer Testausgaben
	/**
	 * Gibt die Werte der Instanzvariablen als String zurueck.
	 */
	public String toString(){	
		String eol = System.getProperty("line.separator");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		
		StringBuilder messageDetails = new StringBuilder();
		messageDetails.append("ID: " + id.toString() + eol
				+ "FROM: " + sender + eol
				+ "TO: "
				);
		if (receiver!=null) messageDetails.append(receiver + eol);
		else messageDetails.append("---" + eol);
		messageDetails.append("SENT: " + sdf.format(sent.getTime()) + eol
				+ "FEEDBACK: " + feedback + eol
				+ "READ: " + read + eol
				+ "SUBJECT: " + subject + eol
				+ "MESSAGE: " + text + eol
				+ eol
				);
		
		return messageDetails.toString();
	}

}
