package swe2016.g6t4.view;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe2016.g6t4.controller.UserManagement;
import swe2016.g6t4.model.User;

/**
 * Servlet implementation class UserProfileServlet
 */
@WebServlet("/UserProfileServlet")
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String showThisUser = request.getParameter("id"); 
		User user = (User) request.getSession().getAttribute("user");
		
		if (showThisUser!=null && user!=null){
			UserManagement um = new UserManagement();
			try {
				User userToShow = um.getUserById(UUID.fromString(showThisUser));
				if ( userToShow.getId().equals(user.getId()) ){
					RequestDispatcher rd = request.getRequestDispatcher("myProfile.jsp");
					rd.forward(request, response);
				}
				else {
					request.setAttribute("userToShow", userToShow);
					RequestDispatcher rd = request.getRequestDispatcher("userProfile.jsp");
					rd.forward(request, response);
				}
			
			}catch (IllegalArgumentException e){
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
			
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
