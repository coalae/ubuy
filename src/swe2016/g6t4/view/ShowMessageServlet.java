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
 * Servlet implementation class ShowMessageServlet
 */
@WebServlet("/ShowMessageServlet")
public class ShowMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String messageId = request.getParameter("messageId");
		String origin = request.getParameter("origin");
		if (messageId==null || origin==null){
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		else {
			MessageManagement mm = new MessageManagement();
			
			try{
				UUID id = UUID.fromString(messageId);
				Message message = mm.getMessageById(id);
				if (origin.equals("in") || origin.equals("fb")){
					mm.modifyRead(message,true);
				}
				request.setAttribute("messageToShow", message);
				RequestDispatcher rd = request.getRequestDispatcher("showmessage.jsp");
				rd.forward(request, response);
			} catch (IllegalArgumentException e) {
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
				
			}
			
		}
	}

	

}
