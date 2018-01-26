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
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
			<script>
			function validateForm() {
			    var x = document.forms["userForm"]["email"].value;
			    var y = document.forms["userForm"]["password"].value;
			    if (x == "") {
			        alert("Username must be filled out");
			        return false;
			    }
			    
			    if (y == "") {
			        alert("Password must be filled out");
			        return false;
			    }
			}
	   </script>
</head>
<body background="background1.jpg">
	<header>
		<img src="imaxBanner1.jpg" style="width: 100%" />
			<nav>
		<a href="Registration.jsp">Register</a>
	</nav>
	</header>
	<main role="main" class="container">
		<div id="loginForm">
			<form action="Login" name="userForm"
			onsubmit="return validateForm()" method="post">
				<fieldset>
					<legend>Login</legend>
					<b>E-mail:</b><br> <input type="email" name="email"> <span
						style="color: red">${emailError}</span><br> <b>Password:</b><br>
					<input type="password" name="password"> <span
						style="color: red">${pwError}</span><br> <br> <input
						type="submit">
				</fieldset>
			</form>
		</div>
	</main>
	    <footer class="footer">
      <div class="container">
        		<p style="font: Britannic Bold; size: 10px; color: white;">IMAX® and The IMAX
			Experience® are trademarks of IMAX Corporation.</p>
		<p style="font: Britannic Bold; color: white;">
			<b>TO THE MAX</b>
		</p>
      </div>
    </footer>
	
</body>
</html>
