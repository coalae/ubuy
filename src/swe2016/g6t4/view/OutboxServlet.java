package swe2016.g6t4.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe2016.g6t4.controller.MessageManagement;
import swe2016.g6t4.model.User;

/**
 * Servlet implementation class OutboxServlet
 */
@WebServlet("/OutboxServlet")
public class OutboxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user==null) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		else{
			MessageManagement mm = new MessageManagement();
			request.setAttribute("messageList", mm.getPrivateMessagesBySender(user.getUsername()));
			request.setAttribute("feedbackList", mm.getFeedbackMessagesBySender(user.getUsername()));
			RequestDispatcher rd = request.getRequestDispatcher("outbox.jsp");
			rd.forward(request, response);

		}

	}

}
