package swe2016.g6t4.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AdManagerServlet zeigt Operationen an, die der User im Inserat-Bereich für Tutorensuche machen kann.
 */
@WebServlet("/AdManagerServlet")
public class AdManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default-Constructor
     */
    public AdManagerServlet() {
        super();
    }

	/**
	 * doGet leitet auf adhandling.jsp weiter, wenn in der Navigationsleiste auf Ads geklickt wird.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view2 = request.getRequestDispatcher("adhandling.jsp");
		view2.forward(request, response);	
	}

	/**
	 * doPost macht eigene Ausführung.
	 * Wenn eine der Optionen ausgewaehlt wird auf dem adhandling.jsp, dann leitet es weiter zu der gewuenschten jsp der jeweiligen Operation.
	 * Man kann ADD AD, VIEW AD LIST oder MY ADS per Button-Klick auswaehlen.

	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// falls auf einen button geklickt wurde ADD AD oder VIEW AD LIST oder MY ADS ausgewählt wurde	
		String adOperation=request.getParameter("adOperation");
	
	  try { 
		if(adOperation!=null && adOperation.equals("addAd")) {
			request.setAttribute("adOperation", adOperation);
			RequestDispatcher view = request.getRequestDispatcher("addad.jsp");
			view.forward(request, response);
		}

		if(adOperation!=null && adOperation.equals("viewAdList")) {
			request.setAttribute("adOperation", adOperation);
			RequestDispatcher view = request.getRequestDispatcher("viewadlist.jsp");
			view.forward(request, response);
		}
		
		if(adOperation!=null && adOperation.equals("myAds")) {
			request.setAttribute("adOperation", adOperation);
			RequestDispatcher view = request.getRequestDispatcher("myads.jsp");
			view.forward(request, response);
		}
		// falls keine parameter übergeben wurden
		//RequestDispatcher view2 = request.getRequestDispatcher("index.jsp");
		// view2.forward(request, response);	
		// test, weil tomcat exception zeigte
	  } catch (IllegalStateException e) {
		  e.printStackTrace();
	  }
	  
	}

}
