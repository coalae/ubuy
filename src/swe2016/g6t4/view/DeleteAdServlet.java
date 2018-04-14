package swe2016.g6t4.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe2016.g6t4.controller.SearchTutorAdManagement;

/**
 * DeleteAdServlet löscht eine bestehende SearchTutorAd aus der Liste.
 */
@WebServlet("/DeleteAdServlet")
public class DeleteAdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default-Constructor
     */
    public DeleteAdServlet() {
        super();
    }

	/**
	 * doGet löscht eine SearchTutorAd, die der eingeloggte, nicht-gesperrte User (normaler User oder Admin) in der Einzelansicht betrachtet
	 * und wo er auf den Button Delete geklickt hat.
	 * Entsprechende Meldungen über erfolgreiche oder nicht mögliche Löschung werden angezeigt und es wird auf adhandling.jsp weitergeleitet.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UUID adIdToDelete = UUID.fromString(request.getParameter("adIdToDelete"));
		
		PrintWriter out = response.getWriter();
		SearchTutorAdManagement stam = new SearchTutorAdManagement();

		try{
			stam.deleteSearchTutorAd(stam.getSearchTutorAdById(adIdToDelete));
			String message = "Success! The tutor search ad was deleted.";
			request.setAttribute("message", message);
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			out.println("AdID not found!");
			String message = "AdID not found! Nothing was deleted.";
			request.setAttribute("message", message);
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		}
		
	}

	/**
	 * doPost macht die Ausführung von doGet.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
