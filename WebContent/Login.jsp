<%--
  Created by IntelliJ IDEA.
  User: Matt
  Date: 1/20/2018
  Time: 9:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Login</title>
</head>
<body>
<header style="width: 100%"></header>
<div class="main">
    <div id="loginForm">
      <form action="Login" method="post">
          <b>E-mail:</b><br>
          <input type="email" name="email"><br>
          <b>Password:</b><br>
          <input type="password" name="password"><br>
          <input type="submit">
          <a href="Registration.jsp">Register Account</a>
      </form>
    </div>
</div>
</body>
</html>
