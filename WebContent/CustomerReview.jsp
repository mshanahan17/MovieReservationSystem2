<%--
   Created by IntelliJ IDEA.
   User: Matt
   Date: 1/20/2018
   Time: 9:57 AM
   To change this template use File | Settings | File Templates.
   --%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
   <head>
      <title>Movie Review</title>
      <link rel="stylesheet" type="text/css" href="CSS/customer.css">
      <link
         href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
         rel="stylesheet"
         integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
         crossorigin="anonymous">
   </head>
   <body>
      <header>
         <img src="pics/imaxBanner1.jpg" style="width: 100%" />
         <nav>
         	<a href="Login">Home</a> 
         	<a href="ViewOrders">View Orders</a> 
         	<a href="UpdateShoppingCart">View Cart</a> 
         	<a href="LogOut">Log Out</a>
      	 </nav>
      </header>
      <main role="main" class="container">
         <div id="loginForm">
            <form id="loginForm" action="Login"
               method="post" name="rivewForm">
               <fieldset>
                  <legend>Customer Review</legend>
                  <b>Review:</b><br> <input type="text" name="review"><br>
                  <select name="rating">
                  	<option value="1">1</option>
                  	<option value="2">2</option>
                  	<option value="3">3</option>
                  	<option value="4">4</option>
                  	<option value="5">5</option>
                  </select> <br> 
                     <input type="submit">
               </fieldset>
            </form>
         </div>
      </main>
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