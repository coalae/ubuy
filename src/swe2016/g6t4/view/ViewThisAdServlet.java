package swe2016.g6t4.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ViewThisAdServlet zeigt die Einzelansicht eines Inserats an.
 */
@WebServlet("/ViewThisAdServlet")
public class ViewThisAdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default-Constructor
     */
    public ViewThisAdServlet() {
        super();
    }

	/**
	 * doGet leitet mittels Parameter der AdId auf viewthisad.jsp weiter, wo man eine Einzelansicht bekommt - man muss dafür
	 * eingeloggt sein.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adid=request.getParameter("param1");
		request.setAttribute("adid", adid);
		RequestDispatcher view = request.getRequestDispatcher("viewthisad.jsp");
		view.forward(request, response);
 	}

	/**
	 * doPost macht die Ausführung von doGet.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
