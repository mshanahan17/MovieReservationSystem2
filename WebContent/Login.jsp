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
	<img src="imaxBanner1.jpg" style="width:100%"/>
    <title>User Login</title>
</head>
<body background="background1.jpg">
<header style="width: 100%"></header>
<div class="main">
	<div id="loginForm">
		<form action="Login" method="post">
			<fieldset>
				<legend>Login</legend>
				<b>E-mail:</b><br>
				<input type="email" name="email"><br>
				<b>Password:</b><br>
				<input type="password" name="password"><br>
				<input type="submit">
				<a href="Registration.jsp">Register Account</a>
			</fieldset>
		</form>
    </div>
</div>
</body>
<footer style="background-color:black;">
	<p style="font:Britannic Bold; color:white;"> IMAX® and The IMAX Experience® are trademarks of IMAX Corporation.  </p>	
	<p style="font:Britannic Bold; color:white;"> <b>TO THE MAX</b>  </p>
</footer>
</html>
