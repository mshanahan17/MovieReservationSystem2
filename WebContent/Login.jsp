<%--
  Created by IntelliJ IDEA.
  User: Matt
  Date: 1/20/2018
  Time: 9:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>User Login</title>
<link rel="stylesheet" type="text/css" href="CSS/customer.css">
<link rel="stylesheet" type="text/css" href="CSS/Login.css">
</head>
<body background="background1.jpg">
<header><img src="imaxBanner1.jpg" style="width: 100%" /></header>
	<nav>
		<a href="Registration.jsp">Register</a>
	</nav>
	<div class="main">
		<div id="loginForm">
			<form action="Login" method="post">
				<fieldset>
					<legend>Login</legend>
					<b>E-mail:</b><br> <input type="email" name="email"> <span
						style="color: red">${emailError}</span><br> <b>Password:</b><br>
					<input type="password" name="password"> <span
						style="color: red">${pwError}</span><br>
					<br> <input type="submit">
				</fieldset>
			</form>
		</div>
</body>
<footer>
	<p style="font: Britannic Bold; color: white;">IMAX® and The IMAX
		Experience® are trademarks of IMAX Corporation.</p>
	<p style="font: Britannic Bold; color: white;">
		<b>TO THE MAX</b>
	</p>
</footer>
</html>
