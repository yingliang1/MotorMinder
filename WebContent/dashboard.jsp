<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="javax.servlet.http.*, javax.servlet.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>

  	<link rel="stylesheet" type="text/css" href="css/dashboard.css">
</head>
<body>
<% session = request.getSession();
	String username = (String) session.getAttribute("userRealName");
	String username1=(String) session.getAttribute("username");
	%>
	<fieldset id="naviagtion_fieldset">
	<legend>Navigation</legend>
	<div class="dropdown">
	<h2>Hello, <span><%=username %></span>
	<img width="30px" height="30px"src="img/user3.png"  alt="edit" />
	 <div class="dropdown-content">
	<p><img width="30px" height="30px"src="img/user1.png"  alt="edit" />
	<a href=<%=request.getContextPath() +"/UserUpdate" %>?username=<%=username1 %>>Personal Information</a></p>
	<p><img width="30px" height="30px"src="img/user2.png"  alt="edit" />
	<a href="userpass.jsp">PassWord Change</a></p>
	<a href=<%=request.getContextPath() +"/LogoutServlet" %>>Logout</a>
	</div>
	</h2>
	</div>

	</fieldset>
	<fieldset id="data_fieldset">
	<legend>Data</legend>
	<div>

		<%@ include file="dashboard.html" %>
	</div>
	</fieldset>
	<fieldset>
	<legend>Online Customer Service</legend>
	<jsp:include page="chat.jsp"/>
	</fieldset>
</body>
	<script src="js/angular.js"></script>
	<script type="text/javascript" src="js/dashboard_table.js"></script>
</html>