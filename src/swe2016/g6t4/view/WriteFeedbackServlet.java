package swe2016.g6t4.view;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe2016.g6t4.controller.MessageManagement;
import swe2016.g6t4.model.Message;

/**
 * Servlet implementation class WriteFeedbackServlet
 */
@WebServlet("/WriteFeedbackServlet")
public class WriteFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sender = request.getParameter("sender");
		String text = request.getParameter("text");
		if (sender!=null && text!=null){
			MessageManagement mm = new MessageManagement();
			String subject;
			UUID id1 = UUID.randomUUID();
			String suggestedAuctionGroupName = request.getParameter("suggestedAuctionGroupName");
			if (suggestedAuctionGroupName==null){
				subject="FEEDBACK";
			}
			else {
				subject="AUCTION GROUP SUGGESTION: " + suggestedAuctionGroupName;
			}
			Message message = new Message(id1, sender, null, subject, text);
			mm.addMessage(message);
			request.setAttribute("message", "Thank you! Your Feedback has been sent.");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
		
	}

}
