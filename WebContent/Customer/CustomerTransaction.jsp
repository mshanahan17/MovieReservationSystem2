<%-- TODO: The page must display all information on the transaction page except the credit card information. --%>

<%--
  Created by IntelliJ IDEA.
  User: Matt
  Date: 1/20/2018
  Time: 10:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" 
    integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
    crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../CSS/customer.css">
</head>
<body>
	<header>
		<img src="imaxBanner1.jpg" style="width: 100%" />
		<nav>
			<a href="ViewOrders.jsp">View Orders</a> 
			<a href="../Login.jsp">Log Out</a>
		</nav>
	</header>
	
		<table class="table table-bordered table-striped">
			<thead>
				<th>Movie</th>
				<th># of Tickets</th>
				<th>Total</th>
				<th>Theater Room</th>
			</thead>
			<tbody>
				<tr>
					<td>Star Wars: The Last Jedi</td>
					<td>4</td>
					<td>$60.00</td>
					<td>Grand 3</td>
				</tr>
			</tbody>
		</table><br>
		
		<h3>Total: $60.00</h3><br>
		<fieldset>
		<legend>Payment Information</legend>
		<form action="CustomerTransactionConfirmation.jsp" id="checkoutForm">
			<input type="text" placeholder="First Name">
			<input type="text" placeholder="Last Name"><br>
			Select Card Type:<br> 
			<select name="creditCard">
			<option value="visa">Visa</option>
			<option value="masterCard">Master Card</option>
			<option value="discover">Discover</option>
			</select><br>
			<input type="text" placeholder="Card Number"><br>
			<input type="text" placeholder="Security Code">
			<input type="date"><br>
			Billing Address:<br>
			<input type="text" placeholder="Street Address"><br>
			<input type="text" placeholder="City"> 
			<select>
			<option value="AL">Alabama</option>
			<option value="AK">Alaska</option>
			<option value="AZ">Arizona</option>
			<option value="AR">Arkansas</option>
			<option value="CA">California</option>
			<option value="CO">Colorado</option>
			<option value="CT">Connecticut</option>
			<option value="DE">Delaware</option>
			<option value="DC">District Of Columbia</option>
			<option value="FL">Florida</option>
			<option value="GA">Georgia</option>
			<option value="HI">Hawaii</option>
			<option value="ID">Idaho</option>
			<option value="IL">Illinois</option>
			<option value="IN">Indiana</option>
			<option value="IA">Iowa</option>
			<option value="KS">Kansas</option>
			<option value="KY">Kentucky</option>
			<option value="LA">Louisiana</option>
			<option value="ME">Maine</option>
			<option value="MD">Maryland</option>
			<option value="MA">Massachusetts</option>
			<option value="MI">Michigan</option>
			<option value="MN">Minnesota</option>
			<option value="MS">Mississippi</option>
			<option value="MO">Missouri</option>
			<option value="MT">Montana</option>
			<option value="NE">Nebraska</option>
			<option value="NV">Nevada</option>
			<option value="NH">New Hampshire</option>
			<option value="NJ">New Jersey</option>
			<option value="NM">New Mexico</option>
			<option value="NY">New York</option>
			<option value="NC">North Carolina</option>
			<option value="ND">North Dakota</option>
			<option value="OH">Ohio</option>
			<option value="OK">Oklahoma</option>
			<option value="OR">Oregon</option>
			<option value="PA">Pennsylvania</option>
			<option value="RI">Rhode Island</option>
			<option value="SC">South Carolina</option>
			<option value="SD">South Dakota</option>
			<option value="TN">Tennessee</option>
			<option value="TX">Texas</option>
			<option value="UT">Utah</option>
			<option value="VT">Vermont</option>
			<option value="VA">Virginia</option>
			<option value="WA">Washington</option>
			<option value="WV">West Virginia</option>
			<option value="WI">Wisconsin</option>
			<option value="WY">Wyoming</option>
			</select> <br>
			<input type="text" placeholder="Zip"><br>
			Shipping Address:<br>
			<input type="text" placeholder="Street Address"><br>
			<input type="text" placeholder="City"> 
			<select>
			<option value="AL">Alabama</option>
			<option value="AK">Alaska</option>
			<option value="AZ">Arizona</option>
			<option value="AR">Arkansas</option>
			<option value="CA">California</option>
			<option value="CO">Colorado</option>
			<option value="CT">Connecticut</option>
			<option value="DE">Delaware</option>
			<option value="DC">District Of Columbia</option>
			<option value="FL">Florida</option>
			<option value="GA">Georgia</option>
			<option value="HI">Hawaii</option>
			<option value="ID">Idaho</option>
			<option value="IL">Illinois</option>
			<option value="IN">Indiana</option>
			<option value="IA">Iowa</option>
			<option value="KS">Kansas</option>
			<option value="KY">Kentucky</option>
			<option value="LA">Louisiana</option>
			<option value="ME">Maine</option>
			<option value="MD">Maryland</option>
			<option value="MA">Massachusetts</option>
			<option value="MI">Michigan</option>
			<option value="MN">Minnesota</option>
			<option value="MS">Mississippi</option>
			<option value="MO">Missouri</option>
			<option value="MT">Montana</option>
			<option value="NE">Nebraska</option>
			<option value="NV">Nevada</option>
			<option value="NH">New Hampshire</option>
			<option value="NJ">New Jersey</option>
			<option value="NM">New Mexico</option>
			<option value="NY">New York</option>
			<option value="NC">North Carolina</option>
			<option value="ND">North Dakota</option>
			<option value="OH">Ohio</option>
			<option value="OK">Oklahoma</option>
			<option value="OR">Oregon</option>
			<option value="PA">Pennsylvania</option>
			<option value="RI">Rhode Island</option>
			<option value="SC">South Carolina</option>
			<option value="SD">South Dakota</option>
			<option value="TN">Tennessee</option>
			<option value="TX">Texas</option>
			<option value="UT">Utah</option>
			<option value="VT">Vermont</option>
			<option value="VA">Virginia</option>
			<option value="WA">Washington</option>
			<option value="WV">West Virginia</option>
			<option value="WI">Wisconsin</option>
			<option value="WY">Wyoming</option>
			</select> <br>
			<input type="text" placeholder="Zip"><br>
		<input type="submit" value="Confirm Payment">
		<a href="ViewAndCheckoutShoppingCart.jsp">
   			<input type="button" value="Cancel Payment" />
		</a>
		</form>
		</fieldset>
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
