package swe2016.g6t4.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe2016.g6t4.controller.UserManagement;

/**
 * Dieses Servlet dient dem Einloggen von Admins / Usern / Researchern.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default-Konstruktor
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * doGet leitet zum login.jsp weiter.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("login.jsp");
		view.forward(request, response);
	}

	/**
	 * doPost wird mit betätigen des login-buttons aufgerufen. Daraufhin werden die Logindaten überprüft, und bei erfolg wird eine Session erzeugt.
	 * Daraufhin wird man dann mit entsprechender Erfolgs-, Fehlermeldung auf index.jsp weitergeleitet.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserManagement usr = new UserManagement();
		if (usr.login((String) request.getParameter("username"), (String) request.getParameter("password"))) {

			request.getSession().setAttribute("user", usr.getUserByUsername((String) request.getParameter("username")));
			request.setAttribute("message", "Logged in successfully!");
		} else {
			request.setAttribute("message", "Login failed, wrong username / password!");
			RequestDispatcher view = request.getRequestDispatcher("login.jsp");
			view.forward(request, response);
			return;
		}

		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
	}

}
