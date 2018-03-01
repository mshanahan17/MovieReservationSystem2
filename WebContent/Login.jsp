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
      <title>User Login</title>
      <link rel="stylesheet" type="text/css" href="CSS/footer.css">
      <link rel="stylesheet" type="text/css" href="CSS/customer.css">
      <link
         href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
         rel="stylesheet"
         integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
         crossorigin="anonymous">
      <script>
         function validateForm() {
         	var x = document.forms["userForm"]["email"].value;
         	var y = document.forms["userForm"]["password"].value;
         	if (x === "") {
         		alert("Username must be filled out");
         		return false;
         	}
         
         	if (y === "") {
         		alert("Password must be filled out");
         		return false;
         	}
         }
      </script>
   </head>
   <body background="pics/background1.jpg">
      <header>
         <img src="pics/imaxBanner1.jpg" style="width: 100%" />
         <nav>
            <a href="Registration.jsp">Register</a>
         </nav>
      </header>
      <main role="main" class="container">
         <div id="loginForm">
            <form id="loginForm" action="Login" onsubmit="return validateForm()"
               method="post" name="userForm">
               <fieldset>
                  <legend>Login</legend>
                  <b>E-mail:</b><br> <input type="email" name="email" value="${cookie.userId.value}"> 
                  
                  <span
                     style="color: red">${emailError}</span><br> <b>Password:</b><br>
                  <input type="password" name="password"> <span
                     style="color: red">${pwError}</span><br>
                     <input type="checkbox" name="rememberMe">Remember Me<br>
                     <input type="submit" name="login">
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