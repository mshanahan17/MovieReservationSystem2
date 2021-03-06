<%--
   Created by IntelliJ IDEA.
   User: Matt
   Date: 1/20/2018
   Time: 10:09 AM
   To change this template use File | Settings | File Templates.
   --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
   <head>
      <title>Details</title>
      <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" 
         integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
         crossorigin="anonymous">
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/customer.css">

<script>
function addToCart(){
	
	$.post("UpdateShoppingCart", {
		ticketQty: $("#ticketQty").val(),
		checkOut: "0"
	}, function(data, status) {

		if(data == 1){
			$('#ticketOrder').html("<h5 style='color:green'>Movie Added To Cart</h5>" +
							 "<form action='UpdateShoppingCart' method='post'> " +
						     "  	<input type='submit' value='View Cart'> " +
						      "</form>");
		}
		else {
			$("#error").text(data);
		}
		
	});
}

function reviewForm(){
	var reviewHtml = "<b>Review:</b><br> <input type='text' id='review' style='width:30vw; margin:5px'><br> " +
    				 "<b style='margin: 5px'>Rating:</b>" +
    				 "<select id='rating' style='margin: 5px'> " +
    				 "<option value='1'>1</option>" +
    				 "<option value='2'>2</option>" +
    				 "<option value='3'>3</option>" +
    	 			 "<option value='4'>4</option>" +
    				 "<option value='5'>5</option>" +
    				 "</select> <br> " +
       				 "<button onclick='addReview()'>Submit Review</button>";
	$("#reviewForm").html(reviewHtml);
}

function addReview(){
	$.post("CustomerReviewServlet", {
		review: $("#review").val(),
		rating: $("#rating").val()
	}, function(data, status) {
		if(data == 0){
			$("#reviewForm").html("<h4 style='color: red'>An Error Occured Submitting Your Review</h4>");
		}
		else {
			$("#movieReviews").html(data).hide().fadeIn(3000);
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
      <h1>${user.firstName} ${user.lastName}</h1>
      <div class="container">
         <div id="main" class="row">
            <div class="col-md-5">
               <img src="data:image/gif; base64,${movie.movie.thumbnail}" style="height:50vh; max-width: 30vw"><br>
            </div>
            <div class="col-md-7">
               <h1>${movie.movie.title}</h1>
               <p>${movie.movie.description}
               </p>
                  <h3>Rating: ${movie.movie.rating}</h3>
            </div>
         </div><br>
         <div class="row">
            <div class="col-md-1">
            </div>
            <div class="col-md-10">
               <div id="ticketOrder">
                  <table class="table table-bordered">
                     <thead>
                        <th>Theater</th>
                        <th>Showtime</th>
                        <th>Price</th>
                        <th>Seats Left</th>
                     </thead>
                     <tbody>
                        <td>${movie.showroom.theater.name}</td>
                        <td>${movie.startTime}</td>
                        <td>$<fmt:formatNumber type="number" minFractionDigits="2" 
                  		maxFractionDigits="2" value="${movie.cost}"/></td>
                        <td>${movie.showroom.capacity - movie.numOfPurchasedSeats}</td>
                     </tbody>
                  </table>
                  <h4 style="color:red" id="error"></h4>
                  <h5>Ticket Quantity: 
					<select name="ticketQty" id="ticketQty">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					</select></h5>
                  <button onclick="addToCart()">Add To Cart</button><br><br>
               </div>
            </div>
            <div class="col-md-1">
            </div>
         </div>
         <div class="row">
            <div class="col-md-1">
            </div>
            <div class="col-md-10">
            <button onclick="reviewForm()">Add Review</button>
            <div id="reviewForm"></div>
            <div id="movieReviews">
               <table class="table table-bordered">
                  <tbody id="reviews">
                  <c:forEach items="${reviews}" var="review" end="5">
         		  <tr>
                  <td>${review.user.firstName} ${review.user.lastName}</td>
                  <td>${review.date}</td>
                  <td>${review.content}</td>
                  <td>${review.rating}</td>
               </tr>
      		   </c:forEach>
                  </tbody>
               </table>
               </div>
            </div>
            <div class="col-md-1">
            </div>
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