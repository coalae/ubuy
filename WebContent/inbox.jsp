<%@page import="swe2016.g6t4.model.User"%>
<%@page import="swe2016.g6t4.model.Message"%>
<%@page import="swe2016.g6t4.controller.MessageManagement"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import= "java.util.UUID"%>
<%@page import="swe2016.g6t4.controller.UserManagement"%>



<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>uBuy Inbox</title>
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
	ArrayList<Message> receivedList = (ArrayList<Message>) request.getAttribute("messageList");
	if (user != null && user.isActive() && receivedList!=null) { 
	%>
	
	<div align = "center">
	<h1>Your Messages</h1> <br><br>
	</div>
	
	<div align = "right">
	<h3>
	<a><b>Inbox</b></a>  
	&nbsp;&nbsp;|&nbsp;&nbsp; 
	<a href="OutboxServlet">Outbox</a>
	<%if (user.getUsertype()==1) { %>
		&nbsp;&nbsp;|&nbsp;&nbsp;  
		<a href="FeedbackServlet">Feedback</a>
	<%} %>
	</h3>
	</div>
	
	<br><br><br><br>
	
	
	<table class="table table-striped">
		<tr>
			<th><h3>From</h3></th>
			<th><h3>Subject</h3></th>
			<th><h3>Sent</h3></th>
		</tr>
		<%
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		UserManagement um = new UserManagement();
		for(int i = receivedList.size()-1; i>=0; i--){
			Message message = receivedList.get(i);
			User sender = um.getUserByUsername(message.getSender());
		%>
			<tr>
				<%if (message.isRead()) {%>
					<%if (sender==null) {%> <td><%=message.getSender()%></td> <%}
					else { %><td><a href="UserProfileServlet?id=<%=sender.getId().toString()%>"><%=message.getSender()%></a></td><%} %>
					<td><a href="ShowMessageServlet?messageId=<%=message.getId()%>&origin=in"><%=message.getSubject() %></a></td>
					<td><%=sdf.format(message.getSent().getTime()) %></td>
				<%} else { //ungelesene Nachrichten werden fett geschrieben %>	
					<%if (sender==null) {%> <td><b><%=message.getSender()%></b></td> <%}
					else { %><td><a href="UserProfileServlet?id=<%=sender.getId().toString()%>"><b><%=message.getSender()%></b></a></td><%} %>
					<td><a href="ShowMessageServlet?messageId=<%=message.getId()%>&origin=in"><b><%=message.getSubject() %></b></a></td>
					<td><b><%=sdf.format(message.getSent().getTime()) %></b></td>
				<%}%>
			</tr>
		<%}%>	
	</table>	




	
	
	<%} // <!-- Ende: if (user!=null && user.isActive() && receivedList!=null) -->
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