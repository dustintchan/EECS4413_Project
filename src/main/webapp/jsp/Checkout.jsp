<!-- 
====================================================================================
 Title: Checkout View
 Description: Final confirmation before purchase of user credit card and shipping information. 
         User data will be loaded into input fields if already submitted into the database.
 Author: Dustin Chan
==================================================================================== 
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link rel="StyleSheet" href="css/Checkout.css" type="text/css" title="cse4413" media="screen, print"/>
<script src="js/utilities.js"></script>
<meta charset="UTF-8">
<title>Checkout Page</title>
</head>
<body>
	<!-- Header Section -->
    <jsp:include page="Header.jsp" flush="true" />

	<div class="page-spacing">

	    <div class="main-container">
	        <h1>Checkout    
	        <img src="images/M&H-Logo.png" width="50" height="31" alt="Temporary logo">
	        </h1>
	        
	        <form action="CheckoutServlet" method="post">
	        
	        <!-- TODO: Change EL to actual param names which will load user info (actual param names are still TBD)
	             Must perform actual credit card checks (i.e correct form - must be robust as outlined in pdf)
	             Must have an 'include' statement in servlet if user input form is incorrect -->
	        
	        	<!-- User Credit Card Information -->
	        	<h3>Payment Information</h3>
	        	<hr><br><br>
	        
	            <div class="input-group">
	            	<label for="cardNumber">Credit Card Number</label>
					<input type="text" class="full-width" placeholder="Enter your card number"  name="cardNumber" value="${sessionScope.creditCard.cardNumber}" required> 
	            </div>
	            <div class="input-group">
	            	<label for="cardName">Cardholder Name</label>
					<input type="text" class="full-width" placeholder="John Doe" name="cardName" value="${sessionScope.creditCard.cardName}" required>
	            </div>
				<div class="input-group">
		            <label for="cardExpDate">Expiry Date</label>
					<input type="text" class="quarter-width" placeholder="09/2004" name="cardExprDate" value="${sessionScope.creditCard.cardExpDate}" required>
	
					<label for="cardSecCode">Security Code</label>
					<input type="text" class="quarter-width" placeholder="XXX" name="cardSecCode" value="${sessionScope.creditCard.cardSecCode}" required>
				</div>

				<!-- User Shipping Information -->
				<br><br>
				<h3>Shipping Address</h3>
				<hr><br><br>

				<div class="input-group">
		            <label for="shippingFName">First Name</label>
					<input type="text" class="threeQuarter-width" name="shippingFName" value="${sessionScope.user.firstName}" required>
	
					<label for="shippingLName">Last Name</label>
					<input type="text" class="threeQuarter-width" name="shippingLName" value="${sessionScope.user.lastName}" required>
				</div>
				<div class="input-group">
	            	<label for="shippingStreet">Street Address</label>
					<input type="text" class="full-width" name="shippingStreet" value="${sessionScope.address.street}" required>
	            </div>
	            <div class="input-group">
	            	<label for="shippingCity">City</label>
					<input type="text" class="full-width" name="shippingCity" value="${sessionScope.address.city}" required>
	            </div>
	            <div class="input-group">
		            <label for="shippingCountry">Country</label>
					<input type="text" class="half-width" name="shippingCountry" value="${sessionScope.address.country}" required>
	
					<label for="shippingState">State</label>
					<input type="text" class="half-width" name="shippingState" value="${sessionScope.address.province}" required>
					
					<label for="shippingZIP">ZIP Code</label>
					<input type="text" class="half-width" name="shippingZIP" value="${sessionScope.address.zip}" required>
				</div>
				<div class="input-group">
	            	<label for="shippingPhone">Phone</label>
					<input type="text" class="full-width" placeholder="Phone (Optional)" name="shippingPhone" value="${sessionScope.address.phone}">
	            </div>
	            

	            <div class="button-container">
		            <button type="submit" class="back-btn" name="movePageAction" value="viewCart" formnovalidate>Back</button>
		            <button type="submit" class="confirm-btn" name="movePageAction" value="viewOrders" onclick="showPopup()">Confirm Order</button>
	            </div>
	            
	         </form>
	         
	         <!-- Popup -->
			 <div class="popup" id="popup">Order Complete!</div>    
        </div> 
    </div>
</body>
</html>