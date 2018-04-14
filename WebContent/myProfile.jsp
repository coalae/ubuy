<%@page import="swe2016.g6t4.controller.UserManagement"%>
<%@page import="swe2016.g6t4.model.User"%>
<%@page import="swe2016.g6t4.model.UserRating"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.UUID"%>
<%@page import="java.text.SimpleDateFormat"%>


<!DOCTYPE html>
<html lang="en">
<head>
<%@ page import="swe2016.g6t4.model.User"%>
<meta charset="UTF-8">
<title>uBuy MyProfile</title>
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:600italic,400,800,700,300'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=BenchNine:300,400,700'
	rel='stylesheet' type='text/css'>
<style>
table.userinfo, .userinfo td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 10px;
	margin: auto;
}
</style>
</head>
<body>

	<!-- ====================================================
	header section -->
<header class="top-header">
	<div class="container">
		<div align="center">
			<%
				User user = (User) session.getAttribute("user");
			%>

			<%
				if (user != null) {
			%>
			<h4>
				Logged in as:
				<%=user.getUsername()%></h4>
			<%
				}
			%>
		</div>
		<div class="row">
			<div class="col-xs-5 header-logo">
				<br> <a href="index.jsp"><img src="img/ubuy-logo.png"
					style="height: 50px;" alt=""></a>
			</div>

			<div class="col-md-7">
				<nav class="navbar navbar-default">
					<div class="container-fluid nav-bar">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed"
								data-toggle="collapse"
								data-target="#bs-example-navbar-collapse-1">
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse"
							id="bs-example-navbar-collapse-1">

							<ul class="nav navbar-nav navbar-right">
								<li><a class="menu" href="BackToIndexServlet">Home</a></li>
								<li><a class="menu" href="AuctionsListServlet">View all
										Auctions</a></li>
								<li><a class="menu" href="SearchAuctionByCategoryServlet">Search
										by Category</a></li>
								<li><a class="menu" href="SearchAuctionByKeywordServlet">Search
										by Keyword</a></li>

								<%
									if (user != null && user.getUsertype() == 0 && user.isActive()) {
								%>
								<li><a class="menu" href="AddAuctionServlet"> Add
										Auction</a></li>
						        <li><a class="menu" href="AdManagerServlet"> Search Tutor Area</a></li>
	
								<%
									}
								%>

								<%
									if (user != null && user.getUsertype() == 1 && user.isActive()) {
								%>
								<li><a class="menu" href="userManagement.jsp">User
										Management</a></li>
								<li><a class="menu" href="AuctionGroupServlet">Auction
										Groups</a></li>
								<li><a class="menu" href="AdManagerServlet"> Search Tutor Area</a></li>
								<%
									}
								%>

								<%
									if (user != null && user.getUsertype() == 2 && user.isActive()) {
								%>
								<li><a class="menu" href="StatistikServlet">Statistics</a></li>
								<%
									}
								%>

								<%
									if (user != null && user.isActive()) {
								%>
								<li><a class="menu active" href="MyProfileServlet">My
										Profile</a></li>
								<li><a class="menu" href="LogoutServlet">Logout</a></li>
								<%
									}
								%>
							</ul>
						</div>
						<!-- /navbar-collapse -->
					</div>
					<!-- / .container-fluid -->
				</nav>
			</div>
		</div>
	</div>
	</header>
	<!-- end of header area -->

	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

	<%
		try {
			User u = (User) request.getSession().getAttribute("user");
	%>

	<!-- Buttons "View my messages" und "Send Feedback" -->
	<div align="center">
	<form action="InboxServlet" method="GET">
		<input type="submit" value="View my messages">
	</form>
	<br>
	<form action="selectfeedback.jsp" method="GET">
		<input type="submit" value="Send Feedback">
	</form>
	<br><br>
	</div>


	<table border='1' class="userinfo">
		<tr>
			<td>Username</td>

			<td>
				<%
					out.print(u.getUsername());
				%>
			</td>
		</tr>
		<tr>
			<td>First Name</td>

			<td>
				<%
					out.print(u.getFirstname());
				%>
			</td>
		</tr>
		<tr>
			<td>Last Name</td>

			<td>
				<%
					out.print(u.getLastname());
				%>
			</td>
		</tr>
		<tr>
			<td>Account type</td>

			<td>
				<%
					int accountType = u.getUsertype();
						if (accountType == 0) {
							out.print("User");
						}
						if (accountType == 1) {
							out.print("Admin");
						}
						if (accountType == 2) {
							out.print("Researcher");
						}
				%>
			</td>
		</tr>
		<tr>
			<td>Accountstatus</td>

			<td>
				<%
					if (u.isActive()) {
							out.print("is active");
						} else {
							out.print("is banned");
						}
				%>
			</td>
		</tr>
	</table>

	<%
		} catch (NullPointerException e) {
			response.sendRedirect("index.jsp");
		}
	%>
	<%
		String wrongPassword = (String) request.getAttribute("wrongPassword");
	%>
	<%
		String notSamePassword = (String) request.getAttribute("notSamePassword");
	%>
	<br><br>
	<div align="center">
		<form action="MyProfileServlet" Method="POST">
			<h4>Old password:</h4>
			<input type="password" name="oldPassword" required="required">
			<br>
			<%
				if (wrongPassword != null) {
					out.print(wrongPassword + "<br>");
				}
			%>


			<h4>New password:</h4>
			<input type="password" name="newPassword" required="required">
			<br>

			<h4>Repeat new password:</h4>
			<input type="password" name="newPassword2" required="required">
			<br>
			<%
				if (notSamePassword != null) {
					out.print(notSamePassword + "<br>");
				}
			%>

			<br>


			<input type="submit" value="Change Password">
		</form>
	</div>
	<br><br><br>
	
	<%if (user!=null && user.getUsertype()==0) { %>
	<div align="center">
		<form action="myAuctions.jsp" Method="GET">
			<input type="submit" value="View my Auctions">
		</form>
	</div>
	<%} %>

	<br><br><br><br>
	
	
	<!-- Anzeige der UserRatings -->
	<%if (user!=null) {%>
	<div align="center"><h3>My User Ratings:</h3></div>
	<%
	UserManagement um = new UserManagement();
	ArrayList<UserRating> ratingList = um.getUserRatingByRatedUser(user.getId());
	if (ratingList.size()==0) {
	%>
		<div align="center"><h4>You have not yet been rated.</h4></div>
	<%
	} else  {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	%>
		<table class="table table-striped">
		<%for (int i = ratingList.size()-1; i>=0; i--){
			UserRating rating = ratingList.get(i); %>
			<tr>
				<td><%=sdf.format(rating.getDateCreated().getTime()) %></td>
				<%
				UUID ratingUserId = rating.getRatingUserId(); 
				try{
					User ratingUser = um.getUserById(ratingUserId); %>
					<td><a href="UserProfileServlet?id=<%=ratingUserId.toString()%>"><%=ratingUser.getUsername() %></a></td>
				<%} catch (IllegalArgumentException e){ %>
					<td>Deleted User</td>
				<%}%>
				
				
				
				
				<td>Rating: <%=rating.getRating() %></td>
				<td><pre><%=rating.getRatingComment() %></pre></td>
			</tr>
		
		<%} %>
		</table>
	<%	
	}
	} //Ende: (if user!=null)
	%>
	
	<br><br><br>



	<!-- footer starts here -->
	<footer class="footer clearfix">
		<div class="container">
			<div class="row">
				<div class="col-xs-6 footer-para"></div>
			</div>
		</div>
	</footer>

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>





	<!-- script tags
	============================================================= -->
	<script src="js/jquery-2.1.1.js"></script>
	<script src="http://maps.google.com/maps/api/js?sensor=true"></script>
	<script src="js/gmaps.js"></script>
	<script src="js/smoothscroll.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/custom.js"></script>

</body>
</html>