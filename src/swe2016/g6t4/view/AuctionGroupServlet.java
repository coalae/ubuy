package swe2016.g6t4.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe2016.g6t4.controller.AuctionManagement;

/**
 * AuctionGroupServlet leitet auf die jsps für das Löschen und Hinzufügen von Auction Groups
 */
@WebServlet("/AuctionGroupServlet")
public class AuctionGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default-Constructor
     */
    public AuctionGroupServlet() {
        super();
    }

	/**
	 * doGet leitet weiter auf auctiongroup.jsp, wo man das Löschen oder Hinzufügen ein Auction Group anfragen kann
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// falls auf einen button geklickt wurde und delete auction group oder add auction group ausgewählt wurde	
		String auctionGroupAction=null;
		auctionGroupAction=request.getParameter("auctionGroupAction");
		
		if(auctionGroupAction!=null) {
			request.setAttribute("auctionGroupAction", auctionGroupAction);
			RequestDispatcher view = request.getRequestDispatcher("auctiongroup.jsp");
			view.forward(request, response);
		}
		
		// falls keine parameter übergeben wurden und zunächst zur auswahl der auctionGroupAction kommen sollte
		RequestDispatcher view2 = request.getRequestDispatcher("auctiongroup.jsp");
		view2.forward(request, response);
	}

	/**
	 * doPost nimmt den Eingabeparameter für deletegroup oder addgroup (von auctiongroup.jsp) und führt die entsprechende Methode 
	 * aus dem AuctionManagement aus.
	 * Wenn es erfolgreich war, wird eine Meldung angezeigt und auf index.jsp zurückgeführt.
	 * Wenn es nicht erfolgreich war, wird eine Fehlermeldung angezeigt und auf index.jsp zurückgeführt.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuctionManagement am = new AuctionManagement();
		response.setContentType("text/html");
		
		try{
			String del = request.getParameter("deletegroup");
			String add = request.getParameter("addgroup");
		} catch (Exception e) {
			String message2 = "ERROR! Data field was empty!";
			request.setAttribute("message2", message2);
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		}

		// falls leere eingaben
		if(request.getParameter("deletegroup")==null && request.getParameter("addgroup")==null) {
			String message2 = "Data field was empty! Nothing was changed.";
			request.setAttribute("message2", message2);
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);	
		}

		// wenn DELETEGROUP gemacht werden soll 
		if(request.getParameter("deletegroup")!=null && !request.getParameter("deletegroup").equals("") && 
				!request.getParameter("deletegroup").isEmpty() && !request.getParameter("deletegroup").equals("Lernmaterial") &&
				!request.getParameter("deletegroup").equals("Nachhilfe")) {
			String deletegroup = (String) request.getParameter("deletegroup");
			try {
				
				// deletegroup löschen versuchen
				am.deleteAuctionGroup(deletegroup);
				// wenn löschen erfolgreich
				String message2 = "Success! Auction Group " + deletegroup + " was deleted.";
				request.setAttribute("message2", message2);
				RequestDispatcher view = request.getRequestDispatcher("index.jsp");
				view.forward(request, response);	
				
			} catch (Exception e) {
				String message2 = "Error! Auction Group " + deletegroup + " could not be deleted.";
				request.setAttribute("message2", message2);
				RequestDispatcher view = request.getRequestDispatcher("index.jsp");
				view.forward(request, response);
			}
			
		// wenn ADDGROUP gemacht werden soll 
		} else if (request.getParameter("addgroup")!=null && !request.getParameter("addgroup").equals("") && 
				!request.getParameter("addgroup").isEmpty() && !request.getParameter("addgroup").equals("Lernmaterial") &&
				!request.getParameter("addgroup").equals("Nachhilfe")) {
				String addgroup = (String) request.getParameter("addgroup");
				try {
					
					// addgroup hinzufügen versuchen
					am.addAuctionGroup(addgroup);
					// wenn hinzufügen erfolgreich
					String message = "Success! Auction Group " + addgroup + " was added.";
					request.setAttribute("message", message);
					RequestDispatcher view = request.getRequestDispatcher("index.jsp");
					view.forward(request, response);	
					
				} catch (Exception e) {
					String message = "Error! Auction Group " + addgroup + " could not be added.";
					request.setAttribute("message", message);
					RequestDispatcher view = request.getRequestDispatcher("index.jsp");
					view.forward(request, response);
				}	
				
		// in alle anderen fällen
		} else {
		  try {
				if(request.getParameter("addgroup").equals("Lernmaterial") || request.getParameter("addgroup").equals("Nachhilfe") ||
						request.getParameter("deletegroup").equals("Lernmaterial") || 
						request.getParameter("deletegroup").equals("Nachhilfe")) {
						String message = "Lernmaterial and Nachhilfe cannot be changed.";
						request.setAttribute("message", message);
						RequestDispatcher view = request.getRequestDispatcher("index.jsp");
						view.forward(request, response);	
				} else {
						String message = "Nothing was changed.";
						request.setAttribute("message", message);
						RequestDispatcher view = request.getRequestDispatcher("index.jsp");
						view.forward(request, response);	
			}
		  } catch (NullPointerException e) {
				String message = "Data was incorrect.";
				request.setAttribute("message", message);
				RequestDispatcher view = request.getRequestDispatcher("index.jsp");
				view.forward(request, response);	
		  }
		}

	}
}
