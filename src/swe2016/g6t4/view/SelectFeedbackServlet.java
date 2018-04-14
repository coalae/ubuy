package swe2016.g6t4.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateFeedbackServlet
 */
@WebServlet("/SelectFeedbackServlet")
public class SelectFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String feedbacktype = request.getParameter("feedbacktype");
		if (feedbacktype==null){
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		else {
			if (feedbacktype.equals("auctionGroupSuggestion")){
				RequestDispatcher rd = request.getRequestDispatcher("suggestauctiongroup.jsp");
				rd.forward(request, response);
			}
			else if (feedbacktype.equals("feedback")) {
				RequestDispatcher rd = request.getRequestDispatcher("writefeedback.jsp");
				rd.forward(request, response);
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
		}
	}

}
