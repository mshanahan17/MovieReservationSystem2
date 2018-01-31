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
      <link rel="stylesheet" type="text/css" href="../CSS/customer.css">
   </head>
   <body>
      <header>
         <img src="../pics/imaxBanner1.jpg" style="width: 100%" />
      </header>
      <nav> 
         <a href="ViewOrders.jsp">View Orders</a>
         <a href="../Login.jsp">Logout</a>
      </nav>
      <table class="table table-bordered table-striped">
         <thead>
            <th>Order #</th>
            <th>Movie</th>
            <th># of Tickets</th>
            <th>Total</th>
            <th>Theater Room</th>
         </thead>
         <tbody>
            <tr>
               <td> 443323 </td>
               <td>Star Wars: The Last Jedi</td>
               <td>4</td>
               <td>$60.00</td>
               <td>Grand 3</td>
            </tr>
         </tbody>
      </table>
      <br>
      <a href="CancellationConfirmation.jsp" class="btn btn-primary btn-sm" role="button">Confirm Cancellation</a>
      <a href="CustomerHomePage.jsp" class="btn btn-primary btn-sm" role="button">Discard Cancellation</a>
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