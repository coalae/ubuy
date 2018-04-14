package swe2016.g6t4.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import swe2016.g6t4.controller.AuctionManagement;
import swe2016.g6t4.model.Auction;
import swe2016.g6t4.model.Bid;
import swe2016.g6t4.model.User;

/**
 * AddAuctionServlet fügt eine Auktion hinzu für den User, sofern es sich um einen eingeloggten nicht-gesperrten normale User handelt.
 */
@WebServlet("/AddAuctionServlet")
public class AddAuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default-Constructor
     */
    public AddAuctionServlet() {
        super();
    }

	/**
	 * doGet leitet den User auf addauction.jsp, wo ein Formular für die Eingabe der Auktionsdaten wartet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		RequestDispatcher view = request.getRequestDispatcher("addauction.jsp");
		view.forward(request, response);
	}

	/**
	 * doPost nimmt die Eingaben der Auktionsdaten entgegen und versucht, die Auktion hinzuzufügen.
	 * Wenn Datenfelder leer sind, wird das dem User angezeigt und er wird auf index.jsp weitergeleitet.
	 * Wenn Datenfehler waren, wird das dem User angezeigt und er wird auf index.jsp weitergeleitet.
	 * Wenn die Auktionserstellung erfolgreich war, wird das dem User angezeigt und er wird auf index.jsp weitergeleitet.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		AuctionManagement am = new AuctionManagement();
		response.setContentType("text/html");
		UUID auctionId = UUID.randomUUID();

		try{ 

		String title = (String) request.getParameter("title");
		String description = (String) request.getParameter("description");
		String enddatum = request.getParameter("enddatum");		
		String auctionGroup = (String) request.getParameter("auctiongroup");
		String startpricestr = request.getParameter("startprice");
		double startPrice = Double.parseDouble(request.getParameter("startprice"));
		if(startPrice<=0) {
		    throw new IllegalArgumentException("startprice <=0.");
		}
		    
			if(title==null || title.isEmpty() || title.equals("") || 
					   description==null || description.isEmpty() || description.equals("") ||
					   enddatum==null || enddatum.isEmpty() || enddatum.equals("") ||
					   auctionGroup==null || auctionGroup.isEmpty() || auctionGroup.equals("")) {
						String message2 = "Error! Please try again. Auction not added.";
						request.setAttribute("message2", message2);
						RequestDispatcher view = request.getRequestDispatcher("index.jsp");
						view.forward(request, response);
					}
					
					// Beispiel übergebenes Datum: 30/10/2016
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

					try{
						Date d=sdf.parse(enddatum);
					} catch(ParseException e) {
						String message2 = "Wrong date. Auction not added.";
						request.setAttribute("message2", message2);
						RequestDispatcher view = request.getRequestDispatcher("index.jsp");
						view.forward(request, response);
					} catch(StringIndexOutOfBoundsException e) {
						String message2 = "Wrong date. Auction not added.";
						request.setAttribute("message2", message2);
						RequestDispatcher view = request.getRequestDispatcher("index.jsp");
						view.forward(request, response);			
					}
					
					Calendar end = Calendar.getInstance();
					try {
						end.setTime(sdf.parse(enddatum));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					ArrayList<Bid> bidList=new ArrayList<Bid>();
					
					// nur für testzwecke: UUID createdByUserId = UUID.randomUUID();
					UUID createdByUserId = UUID.fromString(request.getParameter("createdByUserUID"));

					boolean expired = false;
					boolean sold = false;
						
					// AUCTION ERSTELLEN
					try{
						Auction newauction = new Auction(auctionId, title, description, Calendar.getInstance(), end, bidList, createdByUserId, expired, sold, 
								auctionGroup, startPrice);
						am.addAuction(newauction);
						String message = "Thank you! The auction was added.";
						request.setAttribute("message", message);
						RequestDispatcher view = request.getRequestDispatcher("index.jsp");
						view.forward(request, response);
					} catch (Exception e) {
						e.printStackTrace();
						out.println("Data error!");
						String message = "Data error! Auction could not be added.";
						request.setAttribute("message", message);
						RequestDispatcher view = request.getRequestDispatcher("index.jsp");
						view.forward(request, response);
					}

		} catch (NumberFormatException e) {
			String message2 = "Empty field! One of the fields was empty, please try again. Auction not added.";
			request.setAttribute("message2", message2);
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		} catch (IllegalArgumentException e) {
			String message2 = "Start Price must not be 0 or lower. Auction not added.";
			request.setAttribute("message2", message2);
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		} catch (Exception e) {
			String message2 = "Wrong start price. Auction not added.";
			request.setAttribute("message2", message2);
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);			
		}
		
		

	}

}