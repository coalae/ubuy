<%@page import="swe2016.g6t4.model.User"%>
<%@page import="swe2016.g6t4.controller.UserManagement"%>
<%@page import="java.util.UUID"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>uBuy New Message</title>
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link href='http://fonts.googleapis.com/css?family=Open+Sans:600italic,400,800,700,300' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=BenchNine:300,400,700' rel='stylesheet' type='text/css'>
</head>





<body>
<!-- ====================================================
	header section -->
	<header class="top-header">
		<div align="center">
					<%User user = (User) session.getAttribute("user");%>
						
					<%if (user != null) {%>					
					<h4> Logged in as: <%=user.getUsername()%></h4>
					<%}%>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-xs-5 header-logo">
					<br>
					<a href="BackToIndexServlet"><img src="img/ubuy-logo.png" alt="" class="img-responsive logo"></a>
				</div>

				<div class="col-md-7">
					<nav class="navbar navbar-default">
					  <div class="container-fluid nav-bar">
					    <!-- Brand and toggle get grouped for better mobile display -->
					    <div class="navbar-header">
					      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					        <span class="sr-only">Toggle navigation</span>
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>
					      </button>
					    </div>

					    <!-- Collect the nav links, forms, and other content for toggling -->
					    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					      
					      <ul class="nav navbar-nav navbar-right">
					        <li><a class="menu" href="BackToIndexServlet" >Home</a></li>
	                        <li><a class="menu" href="AuctionsListServlet">View all Auctions</a></li>
					        <li><a class="menu" href="SearchAuctionByCategoryServlet">Search by Category</a></li>
					        <li><a class="menu" href="SearchAuctionByKeywordServlet">Search by Keyword</a></li>

						<%if (user == null) {%>					
					        <li><a class="menu" href="LoginServlet">Login</a></li>
					        <li><a class="menu" href="RegisterServlet">Register</a></li>
					     <%}%>

						<%if (user != null  &&  user.getUsertype()==0 && user.isActive()) {%>										     
					        <li><a class="menu" href="AddAuctionServlet"> Add Auction</a></li>
					        <li><a class="menu" href="AdManagerServlet"> Search Tutor Area</a></li>
					     <%}%>

						<%if (user != null  && user.getUsertype()==1 && user.isActive()) {%>
						<li><a class="menu" href="userManagement.jsp">User Management</a></li>
						<li><a class="menu" href="AuctionGroupServlet">Auction Groups</a></li>
					    <li><a class="menu" href="AdManagerServlet"> Search Tutor Area</a></li>
					     <%}%>
																     		            
						<%if (user != null  && user.getUsertype()==2 && user.isActive()) {%>										     		        
					        <li><a class="menu" href="StatistikServlet">Statistics</a></li>
						<%}%>
	
						<%if (user != null && user.isActive()) {%>					
					        <li><a class="menu" href="MyProfileServlet">My Profile</a></li>
					        <li><a class="menu" href="LogoutServlet">Logout</a></li>
					    <%}%>
					        					        
					      </ul>
					    </div><!-- /navbar-collapse -->
					  </div><!-- / .container-fluid -->
					</nav>
				</div>
			</div>
		</div>
	</header> <!-- end of header area -->
	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<%
	String ratedUserIdString = request.getParameter("ratedUserId");

	if (user!=null  && user.isActive() && ratedUserIdString!=null) {
		UUID ratedUserId = UUID.fromString(ratedUserIdString);
		UserManagement um = new UserManagement();
		User ratedUser = um.getUserById(ratedUserId);
	%>
	
	<h1>UserRating for User <%=ratedUser.getUsername() %></h1>
	
	<form action="CreateUserRatingServlet" method="POST">
		<h3>Your rating:</h3>
		<select name="rating" required="required">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
		</select> <br><br>
		<input type="hidden" name="ratedUserId" value="<%=ratedUserId%>">
		<h3>PLEASE INSERT YOUR RATING COMMENT:</h3>
		<textarea name="ratingComment" cols="200" rows="20" wrap="hard" required="required"></textarea> <br><br>
		<div align="center"><input type="submit" value="Send"></div>
	</form>
	
	
	
	
	
	
	<%} // Ende: if (user!=null && user.isActive() && ratedUserIdString!=null) 
	else {
		response.sendRedirect("index.jsp");
	}
	%> 
	
	<!-- footer starts here -->
	<footer class="footer clearfix">
		<div class="container">
			<div class="row">
				<div class="col-xs-6 footer-para">
				</div>
			</div>
		</div>
	</footer>
</body>
</html>