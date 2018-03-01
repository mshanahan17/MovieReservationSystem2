<%--
   Created by IntelliJ IDEA.
   User: Matt
   Date: 1/20/2018
   Time: 10:14 AM
   To change this template use File | Settings | File Templates.
   --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
   <head>
      <title>Title</title>
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
      <h2>Order #: ${orderId}</h2>
      <table class="table table-bordered table-striped">
         <thead>
            <th>Movie</th>
            <th># of Tickets</th>
            <th>Total</th>
            <th>Theater: Number</th>
            <th>Showtime</th>
            <th></th>
            <th></th>
         </thead>
         <tbody>
              <c:forEach items="${orderItems}" var="order" varStatus="count">
         		<tr>
               <td>${order.movieShowing.movie.title}</td>
               <td>${order.ticketQuantity}</td>
               <td>$<fmt:formatNumber type="number" minFractionDigits="2" 
                  maxFractionDigits="2" value="${order.ticketQuantity * order.movieShowing.cost}" />
               </td>
               <td>${order.movieShowing.showroom.theater.name}</td>
               <td>${order.movieShowing.startTime}</td>
                  <td> 
                  	   <form action="MovieSearchResults" method="post">
                  			<input type="submit" name="buttons${count.index}" value="View">
                  	   </form>
                  </td>
                  <td> 
                  	   <form action="CancelOrder" method="post">
                  			<input type="submit" name="button${count.index}" value="Cancel">
                  	   </form>
                  </td>
               </tr>
      		  </c:forEach>
         </tbody>
      </table>
      <br>
      <h2> Order Total: $<fmt:formatNumber type="number" minFractionDigits="2" 
                  maxFractionDigits="2" value="${singleOrder.cost}"/>
      </h2>
      <h2> Order Date: ${singleOrder.date} </h2>
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