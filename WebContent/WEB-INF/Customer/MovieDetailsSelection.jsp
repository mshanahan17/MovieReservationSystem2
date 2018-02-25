<%--
   Created by IntelliJ IDEA.
   User: Matt
   Date: 1/20/2018
   Time: 10:09 AM
   To change this template use File | Settings | File Templates.
   --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
   <head>
      <title>Details</title>
      <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" 
         integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
         crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/customer.css">
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
      <div class="container">
         <div id="main" class="row">
            <div class="col-md-5">
               <img src="data:image/gif; base64,${movie.movie.thumbnail}" style="height:50vh; max-width: 30vw"><br>
            </div>
            <div class="col-md-7">
               <h1>${movie.movie.title}</h1>
               <p>${movie.movie.description}
               </p>
               <span>
                  <h3>Rating: </h3>
                  ${movie.movie.rating}
               </span>
            </div>
         </div><br>
         <div class="row">
            <div class="col-md-1">
            </div>
            <div class="col-md-10">
               <form action="UpdateShoppingCart" method="post">
                  <table class="table table-bordered">
                     <thead>
                        <th>Theater</th>
                        <th>Showtime</th>
                        <th>Price</th>
                        <th>Seats Left</th>
                     </thead>
                     <tbody>
                        <td>${movie.showroom.theater.name}</td>
                        <td>${movie.startTime}</td>
                        <td>$<fmt:formatNumber type="number" minFractionDigits="2" 
                  		maxFractionDigits="2" value="${movie.cost}"/></td>
                        <td>${movie.showroom.capacity - movie.numOfPurchasedSeats}</td>
                     </tbody>
                  </table>
                  <h5>Ticket Quantity: <input type="text" size="1" name="ticketQty"></h5>
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
            	<form action="ReviewServlet" method="post">
            		<input type="submit" value="Add Review">
            	</form>
               <table class="table table-bordered">
                  <tbody id="reviews">
                     <tr>
                        <td>Matt Shanahan</td>
                        <td>01/27/2018</td>
                        <td>Lorem ipsum dolor sit amet,<br> 
                           consectetur adipiscing elit.
                        </td>
                        <td>&#9733 &#9733 &#9733 &#9733 &#9734</td>
                     </tr>
                     <tr>
                        <td>Matt Shanahan</td>
                        <td>01/27/2018</td>
                        <td>Lorem ipsum dolor sit amet,<br> 
                           consectetur adipiscing elit.
                        </td>
                        <td>&#9733 &#9733 &#9733 &#9733 &#9734</td>
                     </tr>
                     <tr>
                        <td>Matt Shanahan</td>
                        <td>01/27/2018</td>
                        <td>Lorem ipsum dolor sit amet,<br> 
                           consectetur adipiscing elit.
                        </td>
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