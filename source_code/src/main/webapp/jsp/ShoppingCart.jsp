<!-- 
====================================================================================
 Title: Shopping Cart View
 Description: Displays all items from the user's cart object. Calculates the order total and allows user to
         proceed to Checkout View.
 Author: Dustin Chan
==================================================================================== 
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!--  Import JSTL core library for if-else statements -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<link rel="StyleSheet" href="css/ShoppingCart.css" type="text/css" title="cse4413" media="screen, print"/>
<meta charset="UTF-8">
<title>Shopping Cart Page</title>
</head>
<body>

  <!-- Header Section -->
  <jsp:include page="Header.jsp" flush="true" />

  <!-- Main Page Content - Cart and Order Summary -->
  <div class="container">
    
    	<!-- Shopping Cart Section -->
    	<div class="cart-items">
      	<h2>Your Shopping Bag</h2>

	    <!-- Check if cart is empty --> 
	    
	    <c:if test="${cart.isEmpty()}">
    		<span class="item-name">Your shopping cart is empty.</span>
		</c:if>
	    <c:if test="${not cart.isEmpty()}">
   			<c:forEach var="cartItem" items="${sessionScope.cart.items}">
      	
      		<!--  Display Individual Cart item -->
      		<form action="CartServlet" method="post">
		    <div class="cart-item">
		    
		    	<div class="item-details">
		          	<!--  <img src="https://via.placeholder.com/75" alt="Product Image"> -->
		          	<img src="images/items/${cartItem.imgPath}" alt="Product Image">
		          	<div>
			            <span class="item-name">${cartItem.name}</span>
			            <span class="item-price">$${cartItem.price}</span>
		          	</div>
		        </div>
		        
		       	<div class="item-quantity">
		          	<span>Qty:</span>
		          	<input type="number" name="quantity" value="${cartItem.quantityInCart}" min="1" onchange="this.form.submit()">
		          	<button type="submit" name="action" value="remove">Remove</button>
		          	<input type="hidden" name="itemId" value="${cartItem.id}">
		        </div>
		    </div>
		    </form>
		    
       		</c:forEach>
	    </c:if>
	    </div>

    <!-- Total calculation Section -->
    
    <div class="subtotal">
      	<h3>Order Summary</h3>
      	<div>
	        <span>Subtotal:</span>
	        <%-- <label>${orderValueSubtotal}</label> --%>
	        <label for="orderValueSubtotal">$${sessionScope.cart.getSubtotal()}</label>
      	</div>
      	<div>
	        <span>Shipping:</span>
	        <%-- <label>${orderValueShipping}</label> --%>
	        <label for="orderValueShipping">$5.00</label>
      	</div>
      	<div>
	        <span>Tax:</span>
	        <%-- <label>${orderValueTax}</label> --%>
	        <label for="orderValueTax">$${sessionScope.cart.getTaxAmount()}</label>
      	</div>
      	<div class="total">
        	<span>Total:</span>
        	<%-- <label>${orderValueTotal}</label> --%>
        	<label for="orderValueTotal">$${sessionScope.cart.getTotal()}</label>
      	</div>
      	
      	 <!-- Disable checkout button if empty cart -->
      	<c:if test="${cart.isEmpty()}">
    		<button class="checkout-button-disabled" disabled>Proceed to Checkout</button>
		</c:if>
		<c:if test="${not cart.isEmpty() && user.id == null && bypass != 1}">
      	<form action="CartServlet" method="post">
      		<button type="submit" class="checkout-button" name="movePageAction" value="viewLogin">Login to Checkout</button>
      	</form>
      	</c:if>
		<c:if test="${not cart.isEmpty() && (user.id != null || bypass == 1)}">
      	<form action="CartServlet" method="post">
      		<button type="submit" class="checkout-button" name="movePageAction" value="viewCheckout">Proceed to Checkout</button>
      	</form>
      	</c:if>
   	</div>
   	
   </div>
</body>
</html>