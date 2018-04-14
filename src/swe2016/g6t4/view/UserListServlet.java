package swe2016.g6t4.view;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe2016.g6t4.controller.AuctionManagement;
import swe2016.g6t4.controller.UserManagement;
import swe2016.g6t4.model.User;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Default Constructor
     */
    public UserListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * doGet	
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserManagement umg = new UserManagement();
		
		User u = umg.getUserById(UUID.fromString(request.getParameter("UUID")));
		request.setAttribute("user", u);
		RequestDispatcher view = request.getRequestDispatcher("manageUser.jsp");
		view.forward(request, response);
	}

	/**
	 * doPost
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = (User)request.getSession().getAttribute("userToManage");
		UserManagement umg = new UserManagement();
		AuctionManagement amg = new AuctionManagement();
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		
		if(request.getParameter("blockUser")!=null){
			umg.banUser(u.getId());
			request.setAttribute("message", "User banned successfully");
		}
		if(request.getParameter("unblockUser")!=null){
			umg.unBanUser(u.getId());
			request.setAttribute("message", "User unbanned successfully");
		}
		if(request.getParameter("deleteUser")!=null){
			amg.deleteBidsByUser(u);
			umg.deleteUser(u);
			
			request.setAttribute("message", "User deleted successfully");
		}
		
		view.forward(request, response);
		
	}

}
