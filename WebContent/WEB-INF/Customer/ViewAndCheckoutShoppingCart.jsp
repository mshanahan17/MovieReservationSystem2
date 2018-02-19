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
                  <td>Star Wars: The Last Jedi</td>
                  <td>placeholder</td>
                  <td>Grand 3</td>
                  <td>01/28/2018 7:30 pm</td>
                  <td>4</td>
                  <td>$60.00</td>
                  <td><input type="button" value="Remove" 
                     onclick="SomeDeleteRowFunction(this);"></td>
               </tr>
            </tbody>
         </table>
         <form action="CustomerTransaction.jsp">
            <h3>Total: $60.00</h3>
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