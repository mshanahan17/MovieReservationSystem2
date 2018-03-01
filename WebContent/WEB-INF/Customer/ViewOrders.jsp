<%--
   Created by IntelliJ IDEA.
   User: Matt
   Date: 1/20/2018
   Time: 10:11 AM
   To change this template use File | Settings | File Templates.
   --%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
   <head>
      <title>Title</title>
      <link
         href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
         rel="stylesheet"
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
      <table class="table table-bordered table-striped">
         <thead>
            <th>Order Number</th>
            <th>Total</th>
            <th>Date</th>
            <th></th>
         </thead>
         <tbody>
         ${noOrders}
              <c:forEach items="${pastOrders}" var="order">
         		<tr>
                  <td>${order.id}</td>
                  <td>
                  $<fmt:formatNumber type="number" minFractionDigits="2" 
                  maxFractionDigits="2" value="${order.cost}" />
                  </td>
                  <td>${order.date}</td>
                  <td> 
                  	   <form action="ManageOrder" method="post">
                  			<input type="submit" name="button" value="View Order ${order.id}">
                  	   </form>
                  </td>
               </tr>
      		  </c:forEach>
         </tbody>
      </table>
      <br>
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