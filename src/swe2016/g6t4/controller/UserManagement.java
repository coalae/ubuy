package swe2016.g6t4.controller;

import java.util.ArrayList;
import java.util.UUID;

import swe2016.g6t4.model.Auction;
import swe2016.g6t4.model.Bid;
import swe2016.g6t4.model.User;
import swe2016.g6t4.model.UserRating;

/**
 * @author Team 4 (Gruppe 6)
 * @version 1.0
 * @since 2016-30-11
 */
public class UserManagement {

	/**
	 * Die Instanzvariablen: userdao (UserDAO), userratingdao (UserRatingDAO)
	 */
	private UserDAO userdao;
	private UserRatingDAO userratingdao;
	private AuctionManagement am;

	/**
	 * Der Konstruktor UserManagement erh�lt keine Parameter, und erzeugt ein
	 * UserDAO-, und ein UserRatingDAO-Objekt.
	 */
	public UserManagement() {
		this.userdao = new SerializedUserDAO();
		this.userratingdao = new SerializedUserRatingDAO();
		this.am = new AuctionManagement();
	}

	/**
	 * Methode zum �berpr�fen ob die Login-Daten Korrekt sind.
	 * 
	 * @param username
	 *            Username des Users(String).
	 * @param password
	 *            Passwort des Users(String)
	 * @return Liefert true sofern die Daten zueinander Passen, ansonsten false.
	 */
	public boolean login(String username, String password) {
		if (username.isEmpty() || password.isEmpty())
			return false;
		try {
			userdao.getUserByUsername(username);
		} catch (IllegalArgumentException e) {
			return false;
		}

		User user = this.getUserByUsername(username);
		if (user.getPassword().equals(password)) {
			return true;
		}
		return false;

	}

	/**
	 * Methode zum Anlegen eines neuen Users.
	 * 
	 * @param username
	 *            Username des Users(String).
	 * @param password
	 *            Passwort des Users(String)
	 * @param firstname
	 *            Vorname des Users(String).
	 * @param lastname
	 *            Nachname des Users(String).
	 * @param usertype
	 *            Typ des Users(Enum)
	 * @return Statusmeldung (boolean) ob der User erfolgreich angelegt wurde.
	 *         TODO: Sicherstellen, dass der Username nicht doppelt vorkommt.
	 */
	public boolean registerUser(String username, String password, String firstname, String lastname, int usertype) {

		if (username.isEmpty() || password.isEmpty() || firstname.isEmpty() || lastname.isEmpty()
				|| (usertype != 0 && usertype != 1 && usertype != 2))
			return false;

		ArrayList<User> userList = this.userdao.getUserList();
		boolean isUsed = false;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUsername().equals(username))
				isUsed = true;
		}

		if (isUsed)
			return false;

		UUID id = UUID.randomUUID();
		User neuerUser = new User(username, password, firstname, lastname, usertype, id, true);
		this.userdao.addUser(neuerUser);

		return true;

	}

	/**
	 * Methode zur Suche nach Usern per id(UUID)
	 * 
	 * @param id
	 *            Einzigartige UserID(UUID)
	 * @return User(user) wird zur�ckgegeben. Sofern der User nicht vorhanden
	 *         ist null.
	 */
	public User getUserById(UUID id) {
		return this.userdao.getUserById(id);
	}

	/**
	 * Methode zum L�schen eines Users. Sinnvollerweise sind auch alle Auktionen
	 * des Users zu l�schen.
	 * 
	 * @param user
	 *            Der zu l�schende User(User) wird �bergeben.
	 */
	public void deleteUser(User user) {
		ArrayList<Auction> auctionList = am.getAllAuctions();
		for (int i = 0; i < auctionList.size(); i++) {
			if (auctionList.get(i).getCreatedByUserId().equals(user.getId())) {
				am.deleteAuction(auctionList.get(i));
			}
		}
		this.userdao.deleteUser(user);
	}

	/**
	 * Methode zum Bannen eines Users.
	 * 
	 * @param id
	 *            ID(UUID) des zu bannenden Users wird �bergeben.
	 */
	public void banUser(UUID id) {
		User toBan = getUserById(id);
		userdao.deleteUser(toBan);
		toBan.setActive(false);
		userdao.addUser(toBan);
	}

	/**
	 * Methode zum Entbannen eines Users.
	 * 
	 * @param id
	 *            ID(UUID) des zu entbannenden Users wird �bergeben.
	 */
	public void unBanUser(UUID id) {
		User toBan = getUserById(id);
		userdao.deleteUser(toBan);
		toBan.setActive(true);
		userdao.addUser(toBan);
	}

	// nicht im Klassendiagramm enthalten, userObjekt muss hier auch �bergeben
	// werden.
	/**
	 * Methode zum �ndern des Passwortes eines Users.
	 * 
	 * @param user
	 *            User(user) wird �bergeben dessen Passwort zu �ndern ist.
	 * @param password
	 *            Passwort(String) welches als neues Passwort zu setzen ist.
	 */
	public void editPassword(User user, String password) {
		user.setPassword(password);
	}

	public void reportUser(String username) {

	}

	public void reportAuction(int auctionId) {

	}

	/**
	 * Methode zur Erhebung der Anzahl der User.
	 * 
	 * @return Anzahl der User als int.
	 */
	public int getNumberAllUsers() {
		return this.userdao.getUserList().size();
	}

	/**
	 * Methode zur Erhebung der Anzahl der aktiven User.
	 * 
	 * @return Anzahl der aktiven User als int.
	 */
	public int getNumberActiveUsers() {
		int activeUser = 0;
		ArrayList<User> list = this.userdao.getUserList();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isActive())
				activeUser++;
		}

		return activeUser;
	}

	/**
	 * Methode zur Erhebung der Anzahl der gebannten User.
	 * 
	 * @return Anzahl der gebannten User als int.
	 */
	public int getNumberBannedUsers() {
		return getNumberAllUsers() - getNumberActiveUsers();
	}

	/**
	 * Methode zur Erhebung der Anzahl der Researcher.
	 * 
	 * @return Anzahl der Researcher als int.
	 */
	public int getNumberResearchers() {
		int reseracher = 0;
		ArrayList<User> list = this.userdao.getUserList();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUsertype() == 2)
				reseracher++;
		}

		return reseracher;
	}

	/**
	 * Methode zur Erhebung der Anzahl der normalen User.
	 * 
	 * @return Anzahl der normalen User als int.
	 */
	public int getNumberNormalUsers() {
		int user = 0;
		ArrayList<User> list = this.userdao.getUserList();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUsertype() == 0)
				user++;
		}

		return user;
	}

	/**
	 * Methode zur Erhebung der Anzahl der Admins.
	 * 
	 * @return Anzahl der Admins als int.
	 */
	public int getNumberAdmins() {
		int admin = 0;
		ArrayList<User> list = this.userdao.getUserList();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUsertype() == 1)
				admin++;
		}

		return admin;
	}

	// einfach addAuctionGroup aus auctionDao?
	public void requestNewAuctionGroup(String groupname) {

	}
	// UUID id, User ratedUser, User ratingUser, Auction ratedAuction, int
	// rating, String ratingComment

	public void rateUser(int rating, String comment, User ratedUser, User ratingUser) throws IllegalArgumentException {
		if ((rating<1) || (rating>5)) throw new IllegalArgumentException("Invalid rating, must be between 1 and 5");
		if ( (comment==null) || (ratedUser==null) || (ratingUser==null) ) throw new IllegalArgumentException("NULL cannot be saved");
		
		UserRating usr = new UserRating(UUID.randomUUID(), ratedUser.getId(), ratingUser.getId(), rating, comment);
		userratingdao.addUserRating(usr);
		

	}

	public ArrayList<UserRating> getAllUserRatings() {
		return userratingdao.getUserRatingList();
	}

	public UserRating getUserRatingByRatingId(UUID id) {
		return userratingdao.getUserRatingById(id);
	}

	public ArrayList<UserRating> getUserRatingByRatedUser(UUID user) {
		return userratingdao.getUserRatingByRatedUser(user);
	}

	public void addBidToUserBidList(Bid bid) {

	}

	public void modifyUserPassword(User user, String password) {

	}

	public boolean checkSessionKey() {
		return false;
	}

	public User getUserByUsername(String username) {
		ArrayList<User> idsuchliste = getUserList();

		for (int i = 0; i < idsuchliste.size(); i++) {
			if (idsuchliste.get(i).getUsername().equalsIgnoreCase(username)) {
				return idsuchliste.get(i);
			}
		}
		return null;

	}

	public ArrayList<User> getUserList() {
		return this.userdao.getUserList();
	}
}
