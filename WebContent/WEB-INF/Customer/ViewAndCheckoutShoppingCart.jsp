<%--
   Created by IntelliJ IDEA.
   User: Matt
   Date: 1/20/2018
   Time: 10:10 AM
   To change this template use File | Settings | File Templates.
   --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
   <head>
      <title>View Cart</title>
      <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" 
         integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
         crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/customer.css">
      <script>
         function SomeDeleteRowFunction(o) {
            //no clue what to put here?
            	var p= o.parentNode.parentNode;
                p.parentNode.removeChild(p);
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
      <div class="main">
         <table class="table table-bordered table-striped">
            <thead>
               <th>Movie</th>
               <th>Poster</th>
               <th>Theater Room</th>
               <th>Showtime</th>
               <th># of Tickets</th>
               <th>Total</th>
               <th>Delete</th>
            </thead>
            <tbody>
               <tr>
                  <td>${movie.movie.title}</td>
                  <td><img src="data:image/gif; base64,${movie.movie.thumbnail}" style="max-height: 75px"></td>
                  <td>${movie.showroom.theater.name}</td>
                  <td>${movie.startTime}</td>
                  <td>${numTickets}</td>
                  <td>$<fmt:formatNumber type="number" minFractionDigits="2" 
                  maxFractionDigits="2" value="${movie.cost * numTickets}" /></td>
                  <td><input type="button" value="Remove" 
                     onclick="SomeDeleteRowFunction(this);"></td>
               </tr>
            </tbody>
         </table>
         <form action="Checkout" method="post">
            <h3>Total: $<fmt:formatNumber type="number" minFractionDigits="2" 
                  maxFractionDigits="2" value="${movie.cost * numTickets}" /></h3>
            <br>
            <input type="submit" value="Checkout">
         </form>
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