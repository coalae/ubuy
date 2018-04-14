package swe2016.g6t4.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewMessageServlet
 */
@WebServlet("/NewMessageServlet")
public class NewMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String receiver = request.getParameter("receiver");
		String subject = request.getParameter("subject");
		if (receiver==null){
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		} 
		else {
			if(subject!=null){
				if(subject.startsWith("RE: ")){
					request.setAttribute("subject", subject);
				}
				else {
					String newsubject = "RE: " + subject;
					request.setAttribute("subject", newsubject);
				}
			}
			RequestDispatcher rd = request.getRequestDispatcher("writemessage.jsp");
			rd.forward(request, response);
		}
	}

}
