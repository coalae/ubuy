package swe2016.g6t4.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe2016.g6t4.controller.SerializedUserDAO;
import swe2016.g6t4.controller.UserDAO;
import swe2016.g6t4.controller.UserManagement;
import swe2016.g6t4.model.User;

/**
 * RegisterServlet ermöglicht einem Besucher der Seite sich einen Account
 * anzulegen.
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default-Constructor
	 */
	public RegisterServlet() {
		super();
	}

	/**
	 * doGet leitet auf register.jsp weiter.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		RequestDispatcher view = request.getRequestDispatcher("register.jsp");
		view.forward(request, response);
	}

	/**
	 * beim klicken des Buttons auf der register.jsp-Seite wird das doPost aufgerufen. Es werden alle Parameter übergeben die für einen
	 * neuen Nutzer notwendig sind. Abhängig davon ob die erstellung des neuen Nutzers erfolgreich oder erfolglos ist wird auf index.jsp
	 * weitergeleitet mit entsprechender Erfolgs-, Fehlermeldung.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserManagement usr = new UserManagement();

		boolean createAttempt = usr.registerUser((String) request.getParameter("username"),
				(String) request.getParameter("password"), (String) request.getParameter("firstName"),
				(String) request.getParameter("lastName"), Integer.parseInt(request.getParameter("accountType")));

		if (createAttempt) {
			request.setAttribute("message", "Account created successfully!");

		} else {
			request.setAttribute("message", "Username already taken!");
			RequestDispatcher view = request.getRequestDispatcher("register.jsp");
			view.forward(request, response);
			return;
		}
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);

	}

}
