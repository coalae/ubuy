
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.UUID"%>
<%@page import="swe2016.g6t4.controller.AuctionManagement"%>
<%@page import="swe2016.g6t4.model.Auction"%>
<%@page import="swe2016.g6t4.model.Bid"%>
<%@page import="swe2016.g6t4.model.User"%>
<%@page import="swe2016.g6t4.controller.UserManagement"%>
<%@page import="swe2016.g6t4.controller.StatisticManagement"%>

<!DOCTYPE html>
<html lang="en">
<head>
<%
	String auctionid = (String) request.getAttribute("auctionid");
%>
<meta charset="UTF-8">
<title>uBuy View this Auction</title>
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
			Auction View <br>
		</h2>
		<h3>
			of AuctionID
			<%
			out.println(" " + auctionid);
		%>
		</h3>
		<br>
		<%
			AuctionManagement am = new AuctionManagement();
		%>
		<%
			UserManagement um = new UserManagement();
		%>
		<%
			StatisticManagement stm = new StatisticManagement();
		%>
		<%
			UUID auctionuid = UUID.fromString(auctionid);
		%>
		<%
			Auction auctionToView = am.getAuctionById(auctionuid);
		%>

		<div align="center">
			<table class="table table-striped">
				<tr>
					<th><div align="center">
							<h3>Title</h3>
						</div></th>
					<th><div align="center">
							<h3>Description</h3>
						</div></th>
					<th><div align="center">
							<h3>Start price</h3>
						</div></th>
					<th><div align="center">
							<h3>Current price</h3>
						</div></th>
					<th><div align="center">
							<h3>Auction group</h3>
						</div></th>
					<th><div align="center">
							<h3>Auction days left</h3>
						</div></th>
					<th><div align="center">
							<h3>Owner</h3>
						</div></th>
				</tr>

				<tr>
					<td><div align="center">
							<h3><%=auctionToView.getTitle()%></h3>
						</div></td>
					<td><div align="center">
							<h3><%=auctionToView.getDescription()%></h3>
						</div></td>
					<td><div align="center">
							<h3><%=auctionToView.getStartPrice()%></h3>
						</div></td>
					<td><div align="center">
							<h3><%=am.getHighestBidAmount(auctionToView)%></h3>
						</div></td>
					<td><div align="center">
							<h3><%=auctionToView.getAuctionGroup()%></h3>
						</div></td>
					<td><div align="center">
							<h3><%=am.auctionDaysLeft(auctionToView)%></h3>
						</div></td>
					<td><div align="center">
							<h3><a href="UserProfileServlet?id=<%=auctionToView.getCreatedByUserId().toString()%>"><%=stm.getAuctionOwnerUsername(auctionToView)%></a></h3>
						</div></td>
				</tr>

			</table>
		</div>

		<br>
		
		<!-- Name des Höchstbieters soll dem Auktions-Inhaber angezeigt werden, falls Auktion verkauft ist -->
		<%
		if (user!=null && auctionToView.getCreatedByUserId().equals(user.getId()) && auctionToView.isSold()		) {
			Bid highestBid = auctionToView.getHighestBid();
			
			User winningUser;
			try  {
				winningUser = um.getUserById(highestBid.getUserId()); 
			} catch (IllegalArgumentException e){
				winningUser=null;
			}
		
			if (winningUser==null) {
				%><h4>Highest Bid was placed by: Deleted User</h4> <br><%
			} else {
				%><h4>Highest Bid was placed by: <a href="UserProfileServlet?id=<%=winningUser.getId().toString()%>"><%=winningUser.getUsername()%></a></h4><%
			}
		}%>
		
		
		

		<%
			//Button soll nur angezeigt werden, wenn die Auktion dem User gehört, oder wenn ein Admin auf diese Page kommt.
			//if ( (user != null  &&  user.getUsertype()==0) || (user != null  &&  user.getUsertype()==1) ) {
			if (user != null && (user.getUsertype() == 1 || auctionToView.getCreatedByUserId().equals(user.getId()))) {
		%>
		<div align="center">
			<h3>Please click if you want to DELETE this auction:</h3>
			<form action="DeleteAuctionServlet" Method="GET">
				<input type="submit" value="Delete"> <input type="hidden"
					name="auctionIdToDelete" value=<%=auctionToView.getId()%>>
			</form>
		</div>
		<%
			}
		%>

		<br>

		<%
			
			if (user != null && user.getUsertype() == 0 && !auctionToView.getCreatedByUserId().equals(user.getId())) {
				
		%>
		<div align="center">
			<h3>Please enter an amount and click if you want to PLACE A BID
				for this auction:</h3>
			<form action="AddBidToAuctionBidListServlet" Method="POST">
				<h4>Bid amount (must be higher than current price in table):</h4>
				<br> <input type="number" min = "0" step="0.01" name="amount"> <br> <input
					type="submit" value="Confirm Bid Amount"> <input
					type="hidden" name="auctionIdToAddBid"
					value=<%=auctionToView.getId()%>>
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