<%--
  Created by IntelliJ IDEA.
  User: Matt
  Date: 1/20/2018
  Time: 10:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" type="text/css" href="../CSS/customer.css">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<header>
		<img src="imaxBanner1.jpg" style="width: 100%" />
	</header>
	<nav>
		<a href="CustomerHomePage.jsp">Home</a> <a href="ViewOrders.jsp">View
			Orders</a> <a href="../Login.jsp">Log Out</a>
	</nav>
	<div class="main">
		<div id="movieSearch">
			<form name="movSearch" action="MovieSearchResults.jsp">
				Select Your Theater:<br> <select name="theaters">
					<option value="theater1">Theater 1</option>
					<option value="theater2">Theater 2</option>
					<option value="theater3">Theater 3</option>
					<option value="theater4">Theater 4</option>
					<option value="theater5">Theater 5</option>
				</select><br> Movie Search:<br> <input type="text" name="search"><br>
				<input type="submit">
			</form>
		</div>
	</div>
</body>
</html>
