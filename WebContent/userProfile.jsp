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
<title>uBuy User Profile</title>
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
table.userinfo {
	border-style: inset;
	border-color: #25774a;
	padding: 10px;
	margin: auto;
	width: 20%;
}
.userinfo tr {
	height: 50px;
	text-align: center;
	vertical-align: middle;
}
.userinfo tr:nth-child(odd){
	background-color: #ecf9f2;
}
.userinfo td{
	border-bottom: 1px solid black;
	border-top: 1px solid black;
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
								<li><a class="menu" href="MyProfileServlet">My
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
		User u = (User) request.getAttribute("userToShow");
		if (user!=null && user.isActive() && u!=null){
	%>
	
	<div align="center">
	<h1>User Profile of User <%=u.getUsername() %></h1>
	</div>
	<br><br>
	<!-- Button "Send Message" -->
	<%if (u.isActive()) { %>
	<div align="center">
	<form action="NewMessageServlet" method="POST">
		<input type="hidden" name="receiver" value="<%=u.getUsername() %>">
		<input type="submit" value="Send Message" style="margin-right: 30px">
	</form>
	</div>
	<%} %>
	<br><br><br>
	
	
	<!-- Anzeige Profilinformationen -->
	<table class="userinfo">
		<tr>
			<td><b><i>Username</i></b></td>
			<td><%=u.getUsername() %></td>
		</tr>
		<tr>
			<td><b><i>First Name</i></b></td>
			<td><%=u.getFirstname() %></td>
		</tr>
		<tr>
			<td><b><i>Last Name</i></b></td>
			<td><%=u.getLastname() %></td>
		</tr>
		<tr>
			<td><b><i>Account type</i></b></td>
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
			<td><b><i>Accountstatus</i></b></td>
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
	
	<br><br><br><br>
	
	
	
	<br><br>
	
	<!-- Anzeige der UserRatings -->
	<div align="center"><h3>User Ratings for <%=u.getUsername() %>:</h3></div>
	<br>
	
	<!-- Button "Rate this User" -->
	<%if (u.isActive()) { %>
	<div align="center">
	<form action="NewUserRatingServlet" method="POST">
		<input type="hidden" name="ratedUserId" value="<%=u.getId().toString() %>">
		<input type="submit" value="Rate this User" style="margin-left: 30px">
	</form>
	</div>
	<%} %>
	<br><br><br>
	
	<!-- Tabelle: vorhandene UserRatings -->
	<%
	UserManagement um = new UserManagement();
	ArrayList<UserRating> ratingList = um.getUserRatingByRatedUser(u.getId());
	if (ratingList.size()==0) {
	%>
		<div align="center"><h4>This User has not yet been rated.</h4></div>
	<%
	} else  {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	%>
		
		<table class="table table-striped">
		<%for (int i = ratingList.size()-1; i>=0; i--){
				UserRating rating = ratingList.get(i);
				 %>
			<tr>
				<td><%=sdf.format(rating.getDateCreated().getTime()) %></td>
				<%
				try{
					UUID ratingUserId = rating.getRatingUserId(); 
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
	%>
	
	<br><br><br><br><br><br>
	
	<!-- Button "Report User" -->
	<%if (u.isActive()) { %>
	<div align="center">
	<form action="reportuser.jsp" method="GET">
		<input type="hidden" name="reportedUsername" value="<%=u.getUsername() %>">
		<input type="submit" value="Report User">
	</form>
	</div>
	<%} %>
	
	

	<%
	} //Ende: if (user!=null && user.isActive() && u!=null)
	else {
		response.sendRedirect("index.jsp");
	}
	%>
	
	




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