<%--
   Created by IntelliJ IDEA.
   User: Matt
   Date: 1/20/2018
   Time: 10:09 AM
   To change this template use File | Settings | File Templates.
   --%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
   <head>
      <title>Change Password</title>
      <link
         href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
         rel="stylesheet"
         integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
         crossorigin="anonymous">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/customer.css">
      
        <script>
        
    function validateForm() {
        var a=$("#oldPass").val();
        var b=$("#newPass").val();
        var c=$("#confirmPass").val();

        if (a==null || a=="", b==null || b=="", c==null || c=="") {
            alert("Please Fill All Fields");
            return false;
        } else if(b != c) {
        	alert("New/Confirm Password Fields Do Not Match")
        } else {
        	oldPasswordIsCorrect()        	
        }
    }
   
    function oldPasswordIsCorrect() {
    	$.post("PasswordAuthServlet", { 
    		passAuth: $("#oldPass").val() },
    		function(data, status) {
    			if(data == 0){
    				console.log("Old password Was Correct")
    				changePassword()
    			}
    			if(data == 1){
    				alert("Old Password Was Incorrect")
    				return false;
    			}
    		});
    }
    
    function changePassword() {
    	$.post("PasswordChangeServlet", { 
    		newPass: $("#newPass").val() },
    		function(data, status) {
    			if(data == 1){
    				console.log("Change failed")
    				alert("PASSWORD CHANGE FAILED")
    			}
    			
    			if(data == 0) {
    				console.log("Change succeeded")
    				alert("PASSWORD SUCCESSFULLY CHANGED")
    			}
    		});
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
      <h2>Password Change</h2><br>
         
      </div>
      <div>
           	Old Password: <input name=oldPass id="oldPass" type="password"/><br>
           	New Password: <input name=newPass id="newPass" type="password"/><br>
           	Confirm Password: <input name=confirmPass id="confirmPass" type="password"/><br><br>         	
            <button onclick="validateForm()">Change Password</button>
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