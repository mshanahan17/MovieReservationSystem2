<%--
   Created by IntelliJ IDEA.
   User: Matt
   Date: 1/20/2018
   Time: 10:09 AM
   To change this template use File | Settings | File Templates.
   --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
   <head>
      <title>Results</title>
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
      <div id="main">
         <table class="table table-bordered table-striped" 
            id="results">
            <thead>
               <th>Theater Name</th>
               <th>Room Number</th>
               <th>Showtime</th>
               <th>Seats Available</th>
               <th>Price</th>
               <th>Poster</th>
               <th>Details</th>
            </thead>
            <tbody>
               <c:forEach items="${movieShowings}" var="movieShowing" varStatus="count" end="4">
         		  <tr>
                  <td>${movieShowing.showroom.theater.name}</td>
                  <td>${movieShowing.movie.title}</td>
                  <td>${movieShowing.startTime}</td>
                  <td>${movieShowing.showroom.capacity - movieShowing.numOfPurchasedSeats}</td>
                  <td>$<fmt:formatNumber type="number" minFractionDigits="2" 
                  maxFractionDigits="2" value="${movieShowing.cost}" /></td>
                  <td><img src="data:image/gif; base64,${movieShowing.movie.thumbnail}" style="max-height: 75px"></td>
                  <td> 
                  	   <form action="MovieSearchResults" method="post">
                  			<input type="submit" name="button${count.index}" value="View Details">
                  	   </form>
                  </td>
               </tr>
      		   </c:forEach>
            </tbody>
         </table>
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