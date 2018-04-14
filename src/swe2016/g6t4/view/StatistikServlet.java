package swe2016.g6t4.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * StatistikServlet zeigt dem User vom Typ Admin entweder User-Statistik oder Auktions-Statistik an, je nachdem welchen Button er klickt.
 */
@WebServlet("/StatistikServlet")
public class StatistikServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default-Constructor
     */
    public StatistikServlet() {
        super();
    }

	/**
	 * doGet leitet entweder auf statistik.jsp weiter (wenn zun�chst der Button f�r die gew�nschte Statistik geklickt werden soll) und
	 * oder auf statistik2.jsp, wo die entsprechende �ber den Button-Klick gew�hlte Statistik angezeigt wird.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// falls auf einen button geklickt wurde und auction oder user statistik ausgew�hlt wurde	
		String statsToShow=null;
		statsToShow=request.getParameter("statsToShow");
		
		if(statsToShow!=null) {
			request.setAttribute("statsToShow", statsToShow);
			RequestDispatcher view = request.getRequestDispatcher("statistik2.jsp");
			view.forward(request, response);
		}
		
		// falls keine parameter �bergeben wurden
		RequestDispatcher view2 = request.getRequestDispatcher("statistik.jsp");
		view2.forward(request, response);
	}

	/**
	 * doPost macht die Ausf�hrung von doGet.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
