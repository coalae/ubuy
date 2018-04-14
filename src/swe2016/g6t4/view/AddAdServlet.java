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
import swe2016.g6t4.model.User;

/**
 * AddAdServlet fügt ein Inserat hinzu für den User, sofern es sich um einen eingeloggten nicht-gesperrten normalen User handelt.
 */
@WebServlet("/AddAdServlet")
public class AddAdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default-Constructor
     */
    public AddAdServlet() {
        super();
    }

	/**
	 * doGet leitet den User auf addad.jsp, wo ein Formular für die Eingabe der Inseratdaten wartet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		RequestDispatcher view = request.getRequestDispatcher("addad.jsp");
		view.forward(request, response);
	}

	/**
	 * doPost nimmt die Eingaben der Inseratdaten entgegen und versucht, das Inserat hinzuzufügen.
	 * Wenn Datenfelder leer sind, wird das dem User angezeigt und er wird mittels required-Attribute informiert.
	 * Wenn Datenfehler waren, wird das dem User angezeigt und er wird auf index.jsp weitergeleitet.
	 * Wenn die Inseraterstellung erfolgreich war, wird das dem User angezeigt und er wird auf index.jsp weitergeleitet.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		SearchTutorAdManagement stam = new SearchTutorAdManagement();
		response.setContentType("text/html");
		UUID adId = UUID.randomUUID();

		try{ 

		String course = (String) request.getParameter("course");
		double maxprice = Double.parseDouble(request.getParameter("maxPrice"));
		/*
		if(maxprice<=0) {
		    throw new IllegalArgumentException("maxprice is lower than or equal to 0.");
		}
		*/
		
		/*
			if(course==null || course.isEmpty() || course.equals("") ) {
						String message2 = "Error! Please try again. Ad not added.";
						request.setAttribute("message2", message2);
						RequestDispatcher view = request.getRequestDispatcher("index.jsp");
						view.forward(request, response);
					}
		*/			

	    UUID userIdDemand = UUID.fromString(request.getParameter("userIdDemand"));
						
					// SEARCH TUTOR AD ERSTELLEN
					try{
						SearchTutorAd newad = new SearchTutorAd(adId, userIdDemand, course, maxprice);
						stam.addSearchTutorAd(newad);
						String message = "Thank you! The search ad was added.";
						request.setAttribute("message", message);
						RequestDispatcher view = request.getRequestDispatcher("index.jsp");
						view.forward(request, response);
					} catch (NullPointerException e) {
						e.printStackTrace();
						out.println("Data error!");
						String message = "Data error! Search ad could not be added.";
						request.setAttribute("message", message);
						RequestDispatcher view = request.getRequestDispatcher("index.jsp");
						view.forward(request, response);
					}

		} catch (NumberFormatException e) {
			String message2 = "Empty field! One of the fields was empty, please try again. Search Ad not added.";
			request.setAttribute("message2", message2);
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		} catch (IllegalArgumentException e) {
			String message2 = "Max. price per hour must not be 0 or lower. Search ad not added.";
			request.setAttribute("message2", message2);
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		} catch (Exception e) {
			String message2 = "Wrong max. price per hour. Search ad not added.";
			e.printStackTrace();
			request.setAttribute("message2", message2);
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);			
		}
		
	}

}