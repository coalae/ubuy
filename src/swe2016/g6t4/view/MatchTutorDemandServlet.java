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
import swe2016.g6t4.model.SearchTutorAd;

/**
 * MatchTutorDemandServlet setzt die Suchanzeige-Instanzvariable adMatched auf TRUE
 */
@WebServlet("/MatchTutorDemandServlet")
public class MatchTutorDemandServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default-Constructor
     */
    public MatchTutorDemandServlet() {
        super();
    }

	/**
	 * doGet setzt adMatched auf TRUE.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UUID adIdToView = UUID.fromString(request.getParameter("adIdToView"));
		UUID matchUserIdSupply = UUID.fromString(request.getParameter("matchUserIdSupply"));
		PrintWriter out = response.getWriter();
		SearchTutorAdManagement stam = new SearchTutorAdManagement();

		try{
			stam.setMatched(adIdToView, matchUserIdSupply);
			String message = "Success! The tutor search ad was matched.";
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
