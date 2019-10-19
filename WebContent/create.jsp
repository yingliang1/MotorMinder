<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create account</title>
<link rel="stylesheet" href="css/create.css"/>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="js/create.js"></script>
</head>
<body>
<div id="login">
<p id="text">MotorMinder</p>  
    <form id="createForm" action="SignUpServlet" method="post">
    
    <c:if test="${not empty errorMsg}">
        <h3 id="errorMsg">
          <c:out value="${errorMsg}"></c:out>
        </h3>
      </c:if>
        <div id="test">
        <p id="text2">New? Create Account!</p>
        <p><input type="text" name="userName" id="userName" placeholder="Username"></p>
        <p><input type="password" name="password" id="password" placeholder="Password "></p>
        <p><input type="password" name="passwordConfirm" id="passwordConfirm" placeholder="Password Confirm"></p>
        <p><input type="text" name="firstName" id="firstName" placeholder="First Name"></p>
        <p><input type="text" name="lastName" id="lastName" placeholder="Last Name"></p>
        <p><input type="text" name="email" id="email" placeholder="Email"></p>
        <p><input type="text" name="phoneNumber" id="phoneNumber" placeholder="Phone Number"></p>
        <p><input type="submit" id="submit" value="sumbit"></p>
        </div>
    </form>
</div>
</body>
</html>