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
 * Servlet implementation class CreateUserRatingServlet
 */
@WebServlet("/CreateUserRatingServlet")
public class CreateUserRatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ratedUserIdString = request.getParameter("ratedUserId");
		
		try{
			UUID ratedUserId = UUID.fromString(ratedUserIdString);
			UserManagement um = new UserManagement();
			User ratedUser = um.getUserById(ratedUserId);
			User ratingUser = (User) request.getSession().getAttribute("user");
			int rating = Integer.parseInt(request.getParameter("rating"));
			String comment = request.getParameter("ratingComment");
			
			um.rateUser(rating, comment, ratedUser, ratingUser);
			response.sendRedirect("UserProfileServlet?id="+ratedUserId); //
			
		}catch (Exception e){
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		
		
	}

}
