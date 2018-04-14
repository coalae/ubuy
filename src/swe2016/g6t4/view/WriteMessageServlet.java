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
 * Servlet implementation class WriteMessageServlet
 */
@WebServlet("/WriteMessageServlet")
public class WriteMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sender = request.getParameter("sender");
		String receiver = request.getParameter("receiver");
		String subject = request.getParameter("subject");
		String text = request.getParameter("text");
		if (sender==null || receiver==null || subject==null || text==null){
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		else {
			MessageManagement mm = new MessageManagement();
			UUID id1 = UUID.randomUUID();
			Message m1 = new Message(id1,sender,receiver,subject,text);
			mm.addMessage(m1);
			response.sendRedirect("InboxServlet");
		}
	}

}
