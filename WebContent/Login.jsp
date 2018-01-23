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
    <link rel="stylesheet" type="text/css" href="CSS/customer.css">
    <link rel="stylesheet" type="text/css" href="CSS/Login.css">
</head>
<body>
<header></header>
<nav>
    <a href="Registration.jsp">Register</a>
</nav>
    <div id="loginForm">
      <form action="Login" id="formLogin" method="post">
          <b>E-mail:</b><br>
          <input type="email" name="email"><br>
          <b>Password:</b><br>
          <input type="password" name="password"><br>
          <input type="submit">
      </form>
    </div>
    <div id="right">
    <img src="http://images.mentalfloss.com/sites/default/files/styles/mf_image_3x2/public/istock_000038870278_small.jpg?itok=Fe0p1BqX&resize=1100x740">
    </div>

</body>
</html>
