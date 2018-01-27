<%--
  Created by IntelliJ IDEA.
  User: Matt
  Date: 1/20/2018
  Time: 10:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Details</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" 
    integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
    crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../CSS/customer.css">
</head>
<body>
	<header>
		<img src="imaxBanner1.jpg" style="width: 100%" />
			<nav>
		<a href="CustomerHomePage.jsp">Home</a>
		<a href="MovieSearchResults.jsp">Back</a>
		<a href="../Login.jsp">Log Out</a>
	</nav>
	</header>
	<div class="container">
	
		<div class="row" style="margin-top:10px">
			<div class="col-md-4">
			<img src="starwars.jpg" style="width: 20vw; height:50vh">
			</div>
			<div class="col-md-8">
			<h1>Star Wars: The Last Jedi</h1>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
			Vestibulum mollis lectus arcu. Nulla consequat lobortis lacus, 
			sed venenatis libero molestie quis. Suspendisse potenti. 
			Suspendisse at sollicitudin quam, eu lobortis mauris. Donec 
			lobortis purus congue dolor hendrerit auctor. Mauris quis 
			sem mattis, bibendum velit sit amet, tempus lectus. Donec 
			ornare imperdiet tortor eget rutrum. Nulla semper elit in 
			tortor ullamcorper, non sagittis neque malesuada. Vestibulum 
			ante ipsum primis in faucibus orci luctus et ultrices posuere 
			cubilia Curae; Donec eu enim non metus ullamcorper accumsan 
			vitae vitae augue. Quisque at purus mollis, convallis urna id, 
			placerat est. Morbi ut iaculis tellus. Mauris at fermentum turpis. 
			Etiam dapibus, orci ut lacinia finibus, lectus augue egestas orci, e
			u aliquam massa metus non ligula. Nulla nec malesuada nunc.
			</p>
			<span><h3>Rating: </h3> &#9733 &#9733 &#9733 &#9733 &#9734</span>
			</div>
		</div>
		<div class="row">
			<div class="col-md-1">
			</div>
			<div class="col-md-10">
			<form action="ViewAndCheckoutShoppingCart.jsp">
				<table class="table table-bordered">
					<thead>
						<th>Theater</th>
						<th>Room</th>
						<th>Showtime</th>
						<th>Price</th>
						<th>Seats Left</th>
					</thead>
					<tbody>
						<td>Grand</td>
						<td>4</td>
						<td>7:30 pm</td>
						<td>$15.00</td>
						<td>20</td>
					</tbody>
				</table>
				<input type="submit" value="Add To Cart">
				</form>
			</div>
			<div class="col-md-1">
			</div>
		</div>
			<div class="row">
			<div class="col-md-1">
			</div>
			<div class="col-md-10">
				<table class="table table-bordered">
					<tbody id="reviews">
						<tr>
							<td>Matt Shanahan</td>
							<td>01/27/2018</td>
							<td>Lorem ipsum dolor sit amet, 
							consectetur adipiscing elit. Vivamus et 
							aliquam felis. Curabitur id pellentesque nisl, 
							ac mollis turpis. In.</td>
							<td>&#9733 &#9733 &#9733 &#9733 &#9734</td>
						</tr>
						<tr>
							<td>Matt Shanahan</td>
							<td>01/27/2018</td>
							<td>Lorem ipsum dolor sit amet, 
							consectetur adipiscing elit. Vivamus et 
							aliquam felis. Curabitur id pellentesque nisl, 
							ac mollis turpis. In.</td>
							<td>&#9733 &#9733 &#9733 &#9733 &#9734</td>
						</tr>
						<tr>
							<td>Matt Shanahan</td>
							<td>01/27/2018</td>
							<td>Lorem ipsum dolor sit amet, 
							consectetur adipiscing elit. Vivamus et 
							aliquam felis. Curabitur id pellentesque nisl, 
							ac mollis turpis. In.</td>
							<td>&#9733 &#9733 &#9733 &#9733 &#9734</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="col-md-1">
			</div>
		</div>
	</div>
		<footer class="footer">
		<div class="container">
			<span style="font: Britannic Bold; font-size: 12px; color: white">IMAX®
				and The IMAX Experience® are trademarks of IMAX Corporation.</span><br>
			<span style="font: Britannic Bold; font-size: 12px; color: white">
			<b>TO THE MAX!</b></span>
		</div>
	</footer>
</body>
</html>
