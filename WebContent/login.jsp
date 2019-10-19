<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="css/login.css" />
<script type="text/javascript"
  src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</head>
<body>
  <header>MotorMinder</header>
  <div id="loginContainer">
    <form id="loginForm" action="Login" method="POST">
      <p id="loginLabel">Login</p>

      <c:if test="${not empty errorMsg}">
        <h3 id="errorMsg">
          <c:out value="${errorMsg}"></c:out>
        </h3>
      </c:if>

      <input type="text" id="username" name="username"
        placeholder="Username"> <input type="password"
        id="password" name="password" placeholder="Password">
      <p>
        <input type="submit" id="submit" value="Submit">
      </p>
    </form>
    <br>
    <p>
      Don't have an account? <a href="create.jsp">Create an account
        today!</a>
    </p>
  </div>

</body>
</html>