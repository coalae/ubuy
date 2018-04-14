package swe2016.g6t4.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe2016.g6t4.controller.AuctionManagement;
import swe2016.g6t4.model.Auction;
import swe2016.g6t4.model.Bid;
import swe2016.g6t4.model.User;

/**
 * AddBidToAuctionBidListServlet f�gt ein Gebot zu einer Auktion in der BidList hinzu. 
 */
@WebServlet("/AddBidToAuctionBidListServlet")
public class AddBidToAuctionBidListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default-Constructor
     */
    public AddBidToAuctionBidListServlet() {
        super();
    }

	/**
	 * doGet leitet auf index.jsp weiter.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
	}

	/**
	 * doPost nimmt einen Betrag entgegen und versucht, das Gebot hinzuzuf�gen. 
	 * Die M�glichkeit des Gebot-Erstellens wird nur jenen Usern angezeigt, die nicht der User sind, der diese Auktion erstellt hat.
	 * Wenn der Eingabebetrag leer war, wird eine Fehlermeldung ausgegeben und auf index.jsp weitergeleitet.
	 * Wenn das Gebot erfolgreich erstellt wurde, wird eine Meldung gezeigt und auf index.jsp weitergeleitet.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user==null) response.sendRedirect("index.jsp");
		UUID userId = user.getId();
		
		PrintWriter out = response.getWriter();
		AuctionManagement am = new AuctionManagement();
		response.setContentType("text/html");
		UUID bidId = UUID.randomUUID();
		//UUID userId = UUID.randomUUID(); // DUMMY ID ZUM TESTEN
		String amountst = (String) request.getParameter("amount");
		
		// falls die eingabe ein leerer string war
		if(amountst.equals("") || amountst==null || amountst.isEmpty()) {
			String message2 = "Amount field was empty! No bid was added to the auction.";
			request.setAttribute("message2", message2);
			RequestDispatcher view2 = request.getRequestDispatcher("index.jsp");
			view2.forward(request, response);
		} 
		
		double amount = Double.parseDouble(amountst);
		String auctionid = (String) request.getParameter("auctionIdToAddBid");
		UUID auctionIdToDelete = UUID.fromString(auctionid);


		try{
			Bid newbid = new Bid(bidId, userId, amount);
			am.addBidToAuctionBidList(am.getAuctionById(auctionIdToDelete), newbid);
			
			String message = "Success! Your bid was added to the auction.";
			request.setAttribute("message", message);
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
			String message = "Amount data must be higher than the current price! No bid was added to the auction. The entered BID AMOUNT was " + amount + ".";
			request.setAttribute("message", message);
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		}
		
	}

}
