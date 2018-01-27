<%--
   Created by IntelliJ IDEA.
   User: Matt
   Date: 1/20/2018
   Time: 10:09 AM
   To change this template use File | Settings | File Templates.
   --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
   <head>
      <title>Results</title>
      <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" 
         integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
         crossorigin="anonymous">
      <link rel="stylesheet" type="text/css" href="../CSS/customer.css">
   </head>
   <body>
      <header>
         <img src="imaxBanner1.jpg" style="width: 100%" />
               <nav>
         <a href="CustomerHomePage.jsp">Home</a> 
         <a href="ViewOrders.jsp">View Orders</a> 
         <a href="../Login.jsp">Log Out</a>
      </nav>
      </header>
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
            <tr>
               <td>Grand Theater</td>
               <td>Theater 1</td>
               <td>12:30</td>
               <td>20</td>
               <td>$15.00</td>
               <td>placeholder</td>
               <td> <a href="MovieDetailsSelection.jsp" class="btn btn-primary btn-sm" role="button">View Details</a></td>
            </tr>
            <tr>
               <td>Grand Theater</td>
               <td>Theater 1</td>
               <td>12:30</td>
               <td>20</td>
               <td>$15.00</td>
               <td>placeholder</td>
               <td><a href="MovieDetailsSelection.jsp" class="btn btn-primary btn-sm" role="button">View Details</a> </td>
            </tr>
            <tr>
               <td>Grand Theater</td>
               <td>Theater 1</td>
               <td>12:30</td>
               <td>20</td>
               <td>$15.00</td>
               <td>placeholder</td>
               <td><a href="MovieDetailsSelection.jsp" class="btn btn-primary btn-sm" role="button">View Details</a> </td>
            </tr>
            <tr>
               <td>Grand Theater</td>
               <td>Theater 1</td>
               <td>12:30</td>
               <td>20</td>
               <td>$15.00</td>
               <td>placeholder</td>
               <td><a href="MovieDetailsSelection.jsp" class="btn btn-primary btn-sm" role="button">View Details</a> </td>
            </tr>
                        <tr>
               <td>Grand Theater</td>
               <td>Theater 1</td>
               <td>12:30</td>
               <td>20</td>
               <td>$15.00</td>
               <td>placeholder</td>
               <td><a href="MovieDetailsSelection.jsp" class="btn btn-primary btn-sm" role="button">View Details</a> </td>
            </tr>
                        <tr>
               <td>Grand Theater</td>
               <td>Theater 1</td>
               <td>12:30</td>
               <td>20</td>
               <td>$15.00</td>
               <td>placeholder</td>
               <td><a href="MovieDetailsSelection.jsp" class="btn btn-primary btn-sm" role="button">View Details</a> </td>
            </tr>
                        <tr>
               <td>Grand Theater</td>
               <td>Theater 1</td>
               <td>12:30</td>
               <td>20</td>
               <td>$15.00</td>
               <td>placeholder</td>
               <td><a href="MovieDetailsSelection.jsp" class="btn btn-primary btn-sm" role="button">View Details</a> </td>
            </tr>
                        <tr>
               <td>Grand Theater</td>
               <td>Theater 1</td>
               <td>12:30</td>
               <td>20</td>
               <td>$15.00</td>
               <td>placeholder</td>
               <td><a href="MovieDetailsSelection.jsp" class="btn btn-primary btn-sm" role="button">View Details</a> </td>
            </tr>
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