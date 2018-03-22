<%-- TODO: The page must display all information on the transaction page except the credit card information. --%>
<%--
   Created by IntelliJ IDEA.
   User: Matt
   Date: 1/20/2018
   Time: 10:10 AM
   To change this template use File | Settings | File Templates.
   --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
   <head>
      <title>Payment</title>
      <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" 
         integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
         crossorigin="anonymous">
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/customer.css">
  <script>
    function validateForm()
    {
        var a=$("#fName").val();
        var b=$("#lName").val();
        var c=$("#ccNum").val();
        var d=$("#ccCode").val();
        var e=$("#billStreet").val();
        var f=$("#billCity").val();;
        var g=$("#billZip").val();;
        var h=$("#shipStreet").val();
        var i=$("#shipCity").val();;
        var j=$("#shipZip").val();;

        if (a==null || a=="",b==null || b=="",c==null || c=="",d==null || d=="",
        	e==null || e=="",f==null || f=="",g==null || g=="",h==null || h=="",
        	i==null || i=="",j==null || j=="")
        {
            alert("Please Fill All Fields");
            return false;
        }
        else{
        	confirm();
        }
    }
    function confirm(){
    	
		$.post("/Bank/BankingServlet", {
			fName: $("#fName").val(),
			lName: $("#lName").val(),
			ccType: $("#type").val(),
			ccNum: $("#ccNum").val(),
			ccCode: $("#ccCode").val(),
			ccDate: $("#ccDate").val(),
			billStreet: $("#billStreet").val(),
			billCity: $("#billCity").val(),
			billState: $("#billState").val(),
			billZip: $("#billZip").val(),
			shipStreet: $("#shipStreet").val(),
			shipCity: $("#shipCity").val(),
			shipState: $("#shipState").val(),
			shipZip: $("#shipZip").val(),
			total: $("#total").attr("value")
		}, function(data, status) {

			if(data == 0){
				$("#orderFail").html("<h5 style='color:red'>Transaction Was Not Successful!</h5>");				
			}
			if(data == 1){
				$('#orderSuccess').html("<h5 style='color:green'>Order Was Successful</h5>" +
								 "<button onclick='viewOrder()'>Print Order</button><br><br>" +
								 "<div id='viewOrder'></div>");
				placeOrder();
			}
			
		});
    }
    
    function placeOrder() {
    	$.post("PlaceOrder", {}, function(data, status) {
    		$("#viewOrder").html(data).hide();
		});
    }
    
    function viewOrder(){
    	$("#viewOrder").fadeIn(3000);
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
      <h1>${user.firstName} ${user.lastName}</h1>
      <div id="orderSuccess">
      <table class="table table-bordered table-striped">
         <thead>
            <th>Movie</th>
            <th># of Tickets</th>
            <th>Total</th>
            <th>Theater Room</th>
         </thead>
         <tbody>
               <c:forEach items="${shoppingCart}" var="order" varStatus="count">
               <tr>
                  <td>${order.movieShowing.movie.title}</td>
                  <td>${order.ticketQuantity}</td>
                  <td>$<fmt:formatNumber type="number" minFractionDigits="2" 
                  maxFractionDigits="2" value="${order.cost}" /></td>
                  <td>${order.movieShowing.showroom.theater.name}</td>
               </tr>
      		   </c:forEach>
         </tbody>
      </table>
      <br>
      <h3 id="total" value="${total}">Total: $<fmt:formatNumber type="number" minFractionDigits="2" 
                  maxFractionDigits="2" value="${total}" /></h3>
      <br>
      <div id="orderFail"></div>
      <div id="ccForm">
            <input id="fName" type="text" placeholder="First Name" name="fName">
            <input id="lName" type="text" placeholder="Last Name" name="lName"><br>
            Select Card Type:<br> 
            <select name="ccType" id="type">
               <option value="visa">Visa</option>
               <option value="masterCard">Master Card</option>
               <option value="discover">Discover</option>
            </select>
            <br>
            <input id="ccNum" type="text" placeholder="Card Number" name="ccNum"><br>
            <input id="ccCode" type="text" placeholder="Security Code" name="secCode">
            <input id="ccDate" type="date" name="date"><br>
            Billing Address:<br>
            <input id="billStreet" type="text" placeholder="Street Address" name="billStreet"><br>
            <input id="billCity" type="text" placeholder="City" name="billCity"> 
            <select id="billState" name="billState">
               <option value="AL">Alabama</option>
               <option value="AK">Alaska</option>
               <option value="AZ">Arizona</option>
               <option value="AR">Arkansas</option>
               <option value="CA">California</option>
               <option value="CO">Colorado</option>
               <option value="CT">Connecticut</option>
               <option value="DE">Delaware</option>
               <option value="DC">District Of Columbia</option>
               <option value="FL">Florida</option>
               <option value="GA">Georgia</option>
               <option value="HI">Hawaii</option>
               <option value="ID">Idaho</option>
               <option value="IL">Illinois</option>
               <option value="IN">Indiana</option>
               <option value="IA">Iowa</option>
               <option value="KS">Kansas</option>
               <option value="KY">Kentucky</option>
               <option value="LA">Louisiana</option>
               <option value="ME">Maine</option>
               <option value="MD">Maryland</option>
               <option value="MA">Massachusetts</option>
               <option value="MI">Michigan</option>
               <option value="MN">Minnesota</option>
               <option value="MS">Mississippi</option>
               <option value="MO">Missouri</option>
               <option value="MT">Montana</option>
               <option value="NE">Nebraska</option>
               <option value="NV">Nevada</option>
               <option value="NH">New Hampshire</option>
               <option value="NJ">New Jersey</option>
               <option value="NM">New Mexico</option>
               <option value="NY">New York</option>
               <option value="NC">North Carolina</option>
               <option value="ND">North Dakota</option>
               <option value="OH">Ohio</option>
               <option value="OK">Oklahoma</option>
               <option value="OR">Oregon</option>
               <option value="PA">Pennsylvania</option>
               <option value="RI">Rhode Island</option>
               <option value="SC">South Carolina</option>
               <option value="SD">South Dakota</option>
               <option value="TN">Tennessee</option>
               <option value="TX">Texas</option>
               <option value="UT">Utah</option>
               <option value="VT">Vermont</option>
               <option value="VA">Virginia</option>
               <option value="WA">Washington</option>
               <option value="WV">West Virginia</option>
               <option value="WI">Wisconsin</option>
               <option value="WY">Wyoming</option>
            </select>
            <br>
            <input id="billZip" type="text" placeholder="Zip" name="billZip"><br>
            Shipping Address:<br>
            <input id="shipStreet" type="text" placeholder="Street Address" name="shipStreet"><br>
            <input id="shipCity" type="text" placeholder="City" name="shipCity"> 
            <select id="shipState" name="shipState">
               <option value="AL">Alabama</option>
               <option value="AK">Alaska</option>
               <option value="AZ">Arizona</option>
               <option value="AR">Arkansas</option>
               <option value="CA">California</option>
               <option value="CO">Colorado</option>
               <option value="CT">Connecticut</option>
               <option value="DE">Delaware</option>
               <option value="DC">District Of Columbia</option>
               <option value="FL">Florida</option>
               <option value="GA">Georgia</option>
               <option value="HI">Hawaii</option>
               <option value="ID">Idaho</option>
               <option value="IL">Illinois</option>
               <option value="IN">Indiana</option>
               <option value="IA">Iowa</option>
               <option value="KS">Kansas</option>
               <option value="KY">Kentucky</option>
               <option value="LA">Louisiana</option>
               <option value="ME">Maine</option>
               <option value="MD">Maryland</option>
               <option value="MA">Massachusetts</option>
               <option value="MI">Michigan</option>
               <option value="MN">Minnesota</option>
               <option value="MS">Mississippi</option>
               <option value="MO">Missouri</option>
               <option value="MT">Montana</option>
               <option value="NE">Nebraska</option>
               <option value="NV">Nevada</option>
               <option value="NH">New Hampshire</option>
               <option value="NJ">New Jersey</option>
               <option value="NM">New Mexico</option>
               <option value="NY">New York</option>
               <option value="NC">North Carolina</option>
               <option value="ND">North Dakota</option>
               <option value="OH">Ohio</option>
               <option value="OK">Oklahoma</option>
               <option value="OR">Oregon</option>
               <option value="PA">Pennsylvania</option>
               <option value="RI">Rhode Island</option>
               <option value="SC">South Carolina</option>
               <option value="SD">South Dakota</option>
               <option value="TN">Tennessee</option>
               <option value="TX">Texas</option>
               <option value="UT">Utah</option>
               <option value="VT">Vermont</option>
               <option value="VA">Virginia</option>
               <option value="WA">Washington</option>
               <option value="WV">West Virginia</option>
               <option value="WI">Wisconsin</option>
               <option value="WY">Wyoming</option>
            </select>
            <br>
            <input id="shipZip" type="text" placeholder="Zip" name="shipZip"><br>
           <button onclick="validateForm()">Submit</button><br>
            <a href="UpdateShoppingCart">
            <input type="button" value="Cancel Payment" />
            </a>
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