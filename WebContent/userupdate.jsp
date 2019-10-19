<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/userupdate.css"/>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="js/user.js"></script>
</head>
<body>

<%! String username = "";
	String firstName = "";
	String lastName = "";
	String email = "";
	String phoneNum = "";
	String idUser = "";
%>

  <%
  idUser =(String) request.getAttribute("idUser");
   username =(String) request.getAttribute("username");
   firstName =(String) request.getAttribute("firstName");
   lastName =(String) request.getAttribute("lastName");
   email =(String) request.getAttribute("email");
   phoneNum =(String) request.getAttribute("phoneNum");
   %>
  

<form id="createForm" action="UserChange" method="post">
<c:if test="${not empty errorMsg}">
        <h3 id="errorMsg">
          <c:out value="${errorMsg}"></c:out>
        </h3>
      </c:if>
      
<div id="login">
<p id="text">MotorMinder</p>	
	<div id="test">
		<p id="text2">Personal Information</p>
		<div id="user">
		<input type="hidden" value=<%=idUser%> name="idUser"> 
         <table>
	<tr>
          <td >UserName</td> <td><input type="text" value=<%=username%> name="username" id="userName" readonly="readonly"/></td>
      </tr>
      <tr>
          <td >firstName</td> <td><input type="text" value=<%=firstName%> name="firstName" id="firstName"/></td>
      </tr>
      <tr>
          <td >LastName</td> <td><input type="text" value=<%=lastName%> name="lastName" id="lastName"/></td>
      </tr>
      <tr>
          <td >Email</td> <td><input type="text" value=<%=email%> name="email" id="email"/></td>
      </tr>
      <tr>
          <td >PhoneNumber</td> <td><input type="text" value=<%=phoneNum %> name="phoneNum" id="phoneNumber"/></td>
      </tr>
           <tr ><td  colspan="2" height="100px"> <input type="submit" id="submit"value="submit" /></td></tr>
      </table>
     </div>
    <a id="ads" href="dashboard.jsp">back</a>
     </div>
</div>
 	</form>
  </body>
</html>