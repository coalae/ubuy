package swe2016.g6t4.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * BackToIndexServlet leitet auf die Hauptseite zur�ck.
 */
@WebServlet("/BackToIndexServlet")
public class BackToIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default-Constructor (ruft Constructor aus Oberklasse HttpServlet auf)
     */
    public BackToIndexServlet() {
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
	 * doPost macht die Ausf�hrung von doGet.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
