<%-- TODO: The page must display all information on the transaction page except the credit card information. --%>
<%--
   Created by IntelliJ IDEA.
   User: Matt
   Date: 1/20/2018
   Time: 10:10 AM
   To change this template use File | Settings | File Templates.
   --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
   <head>
      <title>Payment</title>
      <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" 
         integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
         crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/customer.css">
  <script>
    function validateForm()
    {
        var a=document.forms["Form"]["fName"].value;
        var b=document.forms["Form"]["lName"].value;
        var c=document.forms["Form"]["ccNum"].value;
        var d=document.forms["Form"]["secCode"].value;
        var e=document.forms["Form"]["billStreet"].value;
        var f=document.forms["Form"]["billCity"].value;
        var g=document.forms["Form"]["billZip"].value;
        var h=document.forms["Form"]["shipStreet"].value;
        var i=document.forms["Form"]["shipCity"].value;
        var j=document.forms["Form"]["shipZip"].value;

        if (a==null || a=="",b==null || b=="",c==null || c=="",d==null || d=="",
        	e==null || e=="",f==null || f=="",g==null || g=="",h==null || h=="",
        	i==null || i=="",j==null || j=="")
        {
            alert("Please Fill All Fields");
            return false;
        }
    }
    </script>
   </head>
   <body>
      <header>
         <img src="${pageContext.request.contextPath}/pics/imaxBanner1.jpg" style="width: 100%" />
         <nav>
         	<a href="Login">Home</a> 
         	<a href="ViewOrders">View Orders</a> 
         	<a href="UpdateShoppingCart">View Cart</a> 
         	<a href="LogOut">Log Out</a>
      	 </nav>
      </header>
      <h1>${user.firstName} ${user.lastName}</h1>
      <table class="table table-bordered table-striped">
         <thead>
            <th>Movie</th>
            <th># of Tickets</th>
            <th>Total</th>
            <th>Theater Room</th>
         </thead>
         <tbody>
               <c:forEach items="${shoppingCart}" var="order" varStatus="count">
               <tr>
                  <td>${order.movieShowing.movie.title}</td>
                  <td>${order.ticketQuantity}</td>
                  <td>$<fmt:formatNumber type="number" minFractionDigits="2" 
                  maxFractionDigits="2" value="${order.cost}" /></td>
                  <td>${order.movieShowing.showroom.theater.name}</td>
               </tr>
      		   </c:forEach>
         </tbody>
      </table>
      <br>
      <h3>Total: $<fmt:formatNumber type="number" minFractionDigits="2" 
                  maxFractionDigits="2" value="${total}" /></h3>
      <br>
      <fieldset>
         <legend>Payment Information</legend>
         <h3 style="color:red">${ccError} ${transactionError}</h3>
         <form action="CustomerTransactionConfirmation" id="checkoutForm" 
         method="post" name="Form" onSubmit="return validateForm()">
            <input type="text" placeholder="First Name" name="fName">
            <input type="text" placeholder="Last Name" name="lName"><br>
            Select Card Type:<br> 
            <select name="ccType">
               <option value="visa">Visa</option>
               <option value="masterCard">Master Card</option>
               <option value="discover">Discover</option>
            </select>
            <br>
            <input type="text" placeholder="Card Number" name="ccNum"><br>
            <input type="text" placeholder="Security Code" name="secCode">
            <input type="date" name="date"><br>
            Billing Address:<br>
            <input type="text" placeholder="Street Address" name="billStreet"><br>
            <input type="text" placeholder="City" name="billCity"> 
            <select name="billState">
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
            </select>
            <br>
            <input type="text" placeholder="Zip" name="billZip"><br>
            Shipping Address:<br>
            <input type="text" placeholder="Street Address" name="shipStreet"><br>
            <input type="text" placeholder="City" name="shipCity"> 
            <select name="shipState">
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
            </select>
            <br>
            <input type="text" placeholder="Zip" name="shipZip"><br>
            First Time Credit Card : <input type="checkbox" name="firstCC"><br>
            <input type="submit" value="Confirm Payment">
            <a href="UpdateShoppingCart">
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