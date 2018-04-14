package swe2016.g6t4.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe2016.g6t4.controller.UserManagement;
import swe2016.g6t4.model.User;

/**
 * MyProfileServlet stellt eine Profilseite eines eingeloggten Nutzers dar. Dort
 * kann man sich seine eigenen Accountdaten ansehen, sein Passwort ändern und
 * seine eigenen Auktionen ansehen.
 */
@WebServlet("/MyProfileServlet")
public class MyProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default Konstruktor
	 * 
	 */
	public MyProfileServlet() {
		super();
	}

	/**
	 * doGet leitet zu myProfile.jsp weiter. Für den Fall dass der User nicht korrekt eingeloggt ist wirft request.getSession() eine 
	 * nullpointerException die entsprechend behandelt wird. 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			User u = (User) request.getSession().getAttribute("user");
			RequestDispatcher view = request.getRequestDispatcher("myProfile.jsp");
			view.forward(request, response);
		} catch (NullPointerException e) {
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			request.setAttribute("message", "Not logged in!");
			view.forward(request, response);
		}

	}

	/**
	 * doPost wird durch den change-password Button auf myprofile.jsp aufgerufen. Hierbei wird versucht das Passwort zu ändern. 
	 * Es kommt zu einer Weiterleitung mit entsprechender Erfolgs-, Fehlermeldung.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User u = (User) request.getSession().getAttribute("user");
		RequestDispatcher view = request.getRequestDispatcher("myProfile.jsp");
		if (!request.getParameter("newPassword").equals(request.getParameter("newPassword2"))) {
			request.setAttribute("notSamePassword", "The new passwords don't match");
			view.forward(request, response);
			return;
		}

		if (!u.getPassword().equals((String) request.getParameter("oldPassword"))) {
			request.setAttribute("wrongPassword", "Wrong password, try again!");
			view.forward(request, response);
			return;
		}

		UserManagement umg = new UserManagement();
		umg.editPassword(u, (String) request.getParameter("newPassword"));

		request.setAttribute("wrongPassword", "Password changed successfully!");
		view.forward(request, response);

	}

}
