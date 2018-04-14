
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.UUID"%>
<%@page import="swe2016.g6t4.controller.SearchTutorAdManagement"%>
<%@page import="swe2016.g6t4.model.SearchTutorAd"%>
<%@page import="swe2016.g6t4.model.User"%>
<%@page import="swe2016.g6t4.controller.UserManagement"%>

<!DOCTYPE html>
<html lang="en">
<head>
<%
	String adid = (String) request.getAttribute("adid");
%>
<meta charset="UTF-8">
<title>uBuy View this Ad</title>
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:600italic,400,800,700,300'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=BenchNine:300,400,700'
	rel='stylesheet' type='text/css'>
</head>
<body>

	<!-- ====================================================
	header section -->
	<header class="top-header">
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
		<div class="container">
			<div class="row">
				<div class="col-xs-5 header-logo">
					<br> <a href="BackToIndexServlet"><img
						src="img/ubuy-logo.png" style="height: 50px;" alt=""></a>
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
									<li><a class="menu" href="AuctionsListServlet">View
											all Auctions</a></li>
									<li><a class="menu" href="SearchAuctionByCategoryServlet">Search
											by Category</a></li>
									<li><a class="menu" href="SearchAuctionByKeywordServlet">Search
											by Keyword</a></li>

									<%
										if (user == null) {
									%>
									<li><a class="menu" href="LoginServlet">Login</a></li>
									<li><a class="menu" href="RegisterServlet">Register</a></li>
									<%
										}
									%>

									<%
										if (user != null && user.getUsertype() == 0 && user.isActive()) {
									%>
									<li><a class="menu" href="AddAuctionServlet"> Add
											Auction</a></li>
					       			<li><a class="menu active" href="AdManagerServlet"> Search Tutor Area</a></li>

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
					       			<li><a class="menu active" href="AdManagerServlet"> Search Tutor Area</a></li>

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
									<li><a class="menu" href="MyProfileServlet">My Profile</a></li>
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

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div align="center">
		<br> <br>
		<h2>
			Search Tutor Ad View <br>
		</h2>
		<h3>
			of AdID
			<% out.println(" " + adid); %>
		</h3>
		<br>
		<%
			SearchTutorAdManagement stam = new SearchTutorAdManagement();
		%>
		<%
			UserManagement um = new UserManagement();
		%>
		<%
			UUID aduid = UUID.fromString(adid);
		%>
		<%
			SearchTutorAd adToView = stam.getSearchTutorAdById(aduid);
			UUID userIdDemand = adToView.getUserIdDemand();
			
		%>

		<div align="center">
			<table class="table table-striped">
				<tr>
					<th class="col-md-4"><div align="center">
							<h3>Course</h3>
						</div></th>
					<th class="col-md-4"><div align="center">
							<h3>Max. price/hour</h3>
						</div></th>
					<th class="col-md-4"><div align="center">
							<h3>Owner</h3>
						</div></th>
				</tr>

				<tr>
					<td><div align="center">
							<h3><%=adToView.getCourse()%></h3>
						</div></td>
					<td><div align="center">
							<h3><%=adToView.getMaxPrice()%></h3>
						</div></td>
					<td><div align="center">
					<% try {
						User userDemand = um.getUserById(userIdDemand);  
						String usernameOwner = userDemand.getUsername();
					%>
							<h3><a href="UserProfileServlet?id=<%=userIdDemand.toString()%>"><%=usernameOwner%></a></h3>
					<%} catch (IllegalArgumentException e){ %>
							<h3>Deleted User</h3>
					<%} %>
					</div></td>
				</tr>

			</table>
		</div>

		<br>

		<%
			//DELETE Button soll nur angezeigt werden, wenn das Inserat dem User gehört, oder wenn ein Admin auf diese Page kommt.
			//if ( (user != null  &&  user.getUsertype()==0) || (user != null  &&  user.getUsertype()==1) ) {
			if (user != null && (user.getUsertype() == 1 || adToView.getUserIdDemand().equals(user.getId()))) {
		%>
		<div align="center">
			<h3>Please click if you want to DELETE this Search Tutor Ad:</h3>
			<form action="DeleteAdServlet" Method="GET">
				<input type="submit" value="Delete"> <input type="hidden"
					name="adIdToDelete" value=<%=adid%>>
			</form>
		</div>
		<%
			}
		%>

		<br>

		<%
			
			if (user != null && user.getUsertype() == 0 && !adToView.getUserIdDemand().equals(user.getId())) {
				
		%>
		<div align="center">
			<h3>Please click if you want to BE A TUTOR for this person:</h3>
			<form action="MatchTutorDemandServlet" Method="GET">
			<br>
				<input type="submit" value="I want to be a tutor">
				<input type="hidden" name="adIdToView" value=<%=adid%>>
				<input type="hidden" name="matchUserIdSupply" value=<%=user.getId()%>>		 
			</form>	
		</div>	
		<%
			}
		%>

	</div>


	<br>
	<br>
	<br>
	<br>


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