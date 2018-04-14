    <%@page import="java.util.ArrayList"%>
    <%@page import="swe2016.g6t4.controller.AuctionManagement"%>
    <%@page import="swe2016.g6t4.model.Auction"%>
    <%@page import="swe2016.g6t4.model.Bid"%>
    <%@page import="swe2016.g6t4.controller.UserManagement"%>
	<%@page import="swe2016.g6t4.model.User"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>uBuy Auction Groups</title>
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
					<a href="BackToIndexServlet"><img src="img/ubuy-logo.png"  style = "height:50px;"alt="" ></a>
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
					     <%}%>

						<%if (user != null  && user.getUsertype()==1 && user.isActive()) {%>
						<li><a class="menu" href="userManagement.jsp">User Management</a></li>
						<li><a class="menu active" href="AuctionGroupServlet">Auction Groups</a></li>
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

<br><br><br><br><br>

<div align="center">
			<br><br><br><br><br>							
<%String auctionGroupAction = (String) request.getAttribute("auctionGroupAction");%>
<%AuctionManagement am = new AuctionManagement();%>
<%UserManagement um = new UserManagement();%>

<%if(auctionGroupAction!=null && auctionGroupAction.equals("deletegroup")) { %>
		<br>
        <br>
	<div align="center">
					<form action="AuctionGroupServlet" Method="POST" >
					<br><br><br>
				    <h3> Please select the Auction Group you want to delete:</h3> 
					<h3> NOTE: The basic Auction Groups "Lernmaterial" and "Nachhilfe" cannot be deleted.</h3> 
					<br>
						  <select name="deletegroup">
  						  <%for(int i=0; i<am.getAuctionGroups().size();i++) {%>
  						  	 <%String group = am.getAuctionGroups().get(i);%>
  						   	 <option value="<%=group%>"><%=am.getAuctionGroups().get(i) %></option>  						   	 
  						  <%} %>
                          </select>
						  <br><br>
						  <input type="submit" value="Confirm Delete">
					</form>
					<br>
		</div>
	</div>					
		
<%} else if (auctionGroupAction!=null && auctionGroupAction.equals("addgroup")) {%>

		<br>
			<div align="center">
					<form action="AuctionGroupServlet" Method="POST" >
					<br><br><br>
						  <h3> Please enter the Auction Group you want to add:</h3> 
						  <br>
						  <input type="text" name="addgroup" >
						  <br><br> 
						  <input type="submit" value="Confirm Add">
					</form>
			</div>
			
		
		<br> <br> <br>
		<h3> The current Auction Groups are: </h3>
		<h3> Lernmaterial </h3>
		<h3> Nachhilfe </h3>
		
		<table class="table table-striped">		  
		 <%for(int i=0; i<am.getAuctionGroups().size(); i++){%>
		  	<tr>
		  		<td><div align="center"><h3><%=am.getAuctionGroups().get(i)%></h3></div></td>
		  	</tr>
		<%}%>
		</table>

	<%} else { %>
	<br><br><br>
					<h2> Auction Group Actions: </h2>
	
				<br><br><br>
				<table class="table table-striped">
				  <tr>
				    <th><div align="center"><h3>ADD AUCTION GROUP</h3></div></th>
				    <th><div align="center"><h3>DELETE AUCTION GROUP</h3></div></th>
				   </tr>

   			  	  <tr>
				  		<td>
				  		   <div align="center">
							<form action="AuctionGroupServlet" Method="GET" >
				    		  <br>
							  <input type="submit" value="Add Auction Group">
							  <input type="hidden" name="auctionGroupAction" value="addgroup">				 
							</form>	
						   </div>	
						</td>
				  		<td>
				  		   <div align="center">
							<form action="AuctionGroupServlet" Method="GET" >
				    		  <br>
							  <input type="submit" value="Delete Auction Group">
							  <input type="hidden" name="auctionGroupAction" value="deletegroup">				 
							</form>	
						   </div>	
						</td>						    

				  </tr>
				</table>
	<%}%>
	
	

					<br><br> 
					<br><br>
					<br><br>

			<!-- footer starts here -->
			<footer class="footer clearfix">
				<div class="container">
					<div class="row">
						<div class="col-xs-6 footer-para">
						</div>
					</div>
				</div>
			</footer>

<br><br><br><br><br><br>


	

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




