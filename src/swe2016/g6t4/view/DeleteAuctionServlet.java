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
 * DeleteAuctionServlet l�scht eine bestehende Auktion.
 */
@WebServlet("/DeleteAuctionServlet")
public class DeleteAuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default-Constructor
     */
    public DeleteAuctionServlet() {
        super();
    }

	/**
	 * doGet l�scht eine Auktion, die der eingeloggte, nicht-gesperrte User (normaler User oder Admin) in der Einzelansicht betrachtet
	 * und wo er auf den Button Delete geklickt hat.
	 * Entsprechende Meldungen �ber erfolgreiche oder nicht m�gliche L�schung werden angezeigt und es wird auf index.jsp weitergeleitet.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String auctionuid=request.getParameter("auctionIdToDelete");
		UUID auctionIdToDelete = UUID.fromString(auctionuid);
		
		PrintWriter out = response.getWriter();
		AuctionManagement am = new AuctionManagement();

		try{
			am.deleteAuction(am.getAuctionById(auctionIdToDelete));
			String message = "Success! The auction was deleted.";
			request.setAttribute("message", message);
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			out.println("AuctionID not found!");
			String message = "AuctionID not found! Nothing was deleted.";
			request.setAttribute("message", message);
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		}
		
	}

	/**
	 * doPost macht die Ausf�hrung von doGet.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
