<%--
   Created by IntelliJ IDEA.
   User: Matt
   Date: 1/20/2018
   Time: 10:20 AM
   To change this template use File | Settings | File Templates.
   --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
   <head>
      <title>User Registration</title>
      <link
         href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
         rel="stylesheet"
         integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
         crossorigin="anonymous">
      <link rel="stylesheet" type="text/css" href="CSS/customer.css">
      
            <script>
         function validateForm() {
         	var fName = document.forms["userForm"]["fName"].value;
         	var lName = document.forms["userForm"]["lName"].value;
         	var email = document.forms["userForm"]["email"].value;
         	var password = document.forms["userForm"]["password"].value;
         	if (fName == "" || lName == "" || email == "" || password == "") {
         		alert("All fields must be filled out!");
         		return false;
         	}
         }
      </script>
   </head>
   <body>
      <header>
         <img src="pics/imaxBanner1.jpg" style="width: 100%" />
         <nav>
            <a href="Login.jsp">Back</a>
         </nav>
      </header>
      <div class="main">
         <div id="regForm">
            <fieldset style="width: 50vw">
               <legend>User Registration:</legend>
               <form action="Registration" method="post" name="userForm"
               onSubmit="return validateForm()">
                  <b>First Name:</b><br>
                  <input type="text" name="fName"><br>
                  <b>Last Name:</b><br>
                  <input type="text" name="lName"><br>
                  <b>E-mail:</b><br>
                  <input type="email" name="email"><br>
                  <b>Re-Enter Email:</b><br>
                  <input type="email" name="email"><span style="color: red">${emailError}</span><br>
                  <b>Password:</b><br>
                  <input type="password" name="password"><br>
                  <b>Re-Enter Password:</b><br>
                  <input type="password" name="password"><span style="color: red">${pwError}</span><br>
                  <input type="submit">
               </form>
            </fieldset>
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