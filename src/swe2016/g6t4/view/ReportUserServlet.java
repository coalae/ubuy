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
import swe2016.g6t4.controller.UserManagement;
import swe2016.g6t4.model.Message;
import swe2016.g6t4.model.User;

/**
 * Servlet implementation class ReportUserServlet
 */
@WebServlet("/ReportUserServlet")
public class ReportUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sender = request.getParameter("sender");
		String reportedUsername = request.getParameter("reportedUsername");
		String text = request.getParameter("text");
		
		if (sender!=null && reportedUsername!=null && text!=null){
			UserManagement um = new UserManagement();
			User reportedUser = um.getUserByUsername(reportedUsername);
			if (reportedUser!=null) {	//null, falls User nicht gefunden wird
				MessageManagement mm = new MessageManagement();
				UUID id1 = UUID.randomUUID();
				String subject = "USER REPORT of User " + reportedUsername;
				String messagetext = "Reported User: " + reportedUser.getFirstname() + " " + reportedUser.getLastname()
										+ ", Username: " + reportedUsername + ", ID: " + reportedUser.getId().toString()
										+ "<br><br>" + text;
				Message message = new Message(id1, sender, null, subject, messagetext);
				mm.addMessage(message);
				request.setAttribute("message", "Thank you! Your User Report has been sent.");
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
		

	}

}
