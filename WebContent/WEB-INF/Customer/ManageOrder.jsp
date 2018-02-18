<%--
   Created by IntelliJ IDEA.
   User: Matt
   Date: 1/20/2018
   Time: 10:14 AM
   To change this template use File | Settings | File Templates.
   --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
         	<a href="CustomerHomePage.jsp">Home</a> 
         	<a href="ViewOrders.jsp">View Orders</a> 
         	<a href="ViewAndCheckoutShoppingCart.jsp">View Cart</a> 
         	<a href="../Login.jsp">Log Out</a>
      	 </nav>
      </header>
      <h2>Order #: 422334</h2>
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
            <tr>
               <td>Star Wars: The Last Jedi</td>
               <td>4</td>
               <td>$60.00</td>
               <td>Grand Theater: 1</td>
               <td>05/19/2019 12:30 PM</td>
               <td><a href="MovieDetailsSelection.jsp" class="btn btn-primary btn-sm" role="button">View</a> </td>
               <td><a href="CancelOrder.jsp" class="btn btn-primary btn-sm" role="button">Cancel</a> </td>
            </tr>
            <tr>
               <td>The Land Before Time</td>
               <td>2</td>
               <td>$30.00</td>
               <td>Grand Theater: 4</td>
               <td>05/22/2019 4:30 PM</td>
               <td><a href="MovieDetailsSelection.jsp" class="btn btn-primary btn-sm" role="button">View</a> </td>
               <td><a href="CancelOrder.jsp" class="btn btn-primary btn-sm" role="button">Cancel</a> </td>
            </tr>
         </tbody>
      </table>
      <br>
      <h2> Order Total: $155.34 </h2>
      <h2> Order Date: 04/22/2019 </h2>
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