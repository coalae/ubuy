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

import swe2016.g6t4.controller.AuctionManagement;

/**
 * SearchAuctionByCategoryServlet sucht anhand einer vom User eingegebenen Auction Group alle dazu verfügbaren Auktionen.
 */
@WebServlet("/SearchAuctionByCategoryServlet")
public class SearchAuctionByCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default-Constructor
     */
    public SearchAuctionByCategoryServlet() {
        super();
    }

	/**
	 * doGet leitet auf searchauctionbycategory.jsp weiter.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("searchauctionbycategory.jsp");
		view.forward(request, response);

	}

	/**
	 * doPost nimmt die eingegebene Auction Group entgegen und gibt die entsprechende Auktionsliste aus, falls Auktionen vorhanden sind.
	 * Wenn die Eingabe leer war, wird eine Fehlermeldung ausgegeben und auf index.jsp weitergeleitet.
	 * Wenn die Eingabe ok war, wird auf searchauctionbycategory2.jsp weitergeleitet, wo die Auktionsliste ausgegeben wird.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		AuctionManagement am = new AuctionManagement();
		response.setContentType("text/html");
		String categoryToSearch = (String) request.getParameter("category");
		
		if(categoryToSearch==null || categoryToSearch.equals("")) {
			String message = "Category field was empty! Search could not be executed.";
			request.setAttribute("message", message);
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		}
		
		if(categoryToSearch.equals("Lernmaterial") || categoryToSearch.equals("Nachhilfe")) {
			request.setAttribute("categoryToSearch", categoryToSearch);
			RequestDispatcher view3 = request.getRequestDispatcher("searchauctionbycategory2.jsp");
			view3.forward(request, response);
		}
		
		for(int i=0; i<am.getAuctionGroups().size();i++) { 
			if(am.getAuctionGroups().get(i).equals(categoryToSearch)) {
				request.setAttribute("categoryToSearch", categoryToSearch);
				RequestDispatcher view3 = request.getRequestDispatcher("searchauctionbycategory2.jsp");
				view3.forward(request, response);
			}
		}
		
			String message2 = "The group you chose is not available! Search could not be executed.";
			request.setAttribute("message2", message2);
			RequestDispatcher view2 = request.getRequestDispatcher("index.jsp");
			view2.forward(request, response);
			
	}
}