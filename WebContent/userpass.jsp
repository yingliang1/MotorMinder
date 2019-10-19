<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create account</title>
<link rel="stylesheet" href="css/userpass.css"/>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="js/userpass.js"></script>
</head>
<body>
<div id="login">
<p id="text">MotorMinder</p>	
	<form id="createForm" action="UserPass" method="post">
	
	<c:if test="${not empty errorMsg}">
        <h3 id="errorMsg">
          <c:out value="${errorMsg}"></c:out>
        </h3>
      </c:if>	
	<div id="test">
		<p id="text2">Password Change</p>
		<div id="user">
		   <table>
	<tr>
          <td >Password</td> <td><input type="password" name="password" id="password"/></td>
      </tr>
      <tr>
          <td >Password Confirm</td> <td><input type="password" name="passwordConfirm" id="passwordConfirm"/></td>
      </tr>
       <tr ><td  colspan="2" height="100px"> <input type="submit" id="submit"value="submit" /></td></tr>
      </table>
		 <a id="ads" href="dashboard.jsp">back</a>
		 </div>
		</div>
	</form>
</div>

</body>
</html>