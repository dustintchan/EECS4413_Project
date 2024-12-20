<!-- 
====================================================================================
 Title: Profile Purchase History View
 Description: Contains logged in user information. Displays all cart items after purchase is complete
 Author: Dustin Chan
==================================================================================== 
-->


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <!--  Import JSTL core library for if-else & Loop statements -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <link rel="StyleSheet" href="css/Profile.css" type="text/css" title="cse4413" media="screen, print"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profile Purchase History View</title>
  
</head>

<body>
    <!-- Header Section -->
	<jsp:include page="Header.jsp" flush="true" />

    <div class="page-container">
    
        <!-- Side-bar Container -->
        <form action="ProfileServlet" method="post">
        <div class="sidebar">
            <div class="sidebar-title-container">
                <h2>User Information</h2>
            </div>
            <div class="sidebar-buttons-container">
                <button type="submit" class="sidebar-btn" name="movePageAction" value="viewProfile">Profile</button>
                <button type="submit" class="sidebar-btn" name="movePageAction" value="viewProfileHistory">Purchase History</button>
            </div>
        </div>
        </form>
    
	    <!-- Main Content -->
	  	<div class="container">
	    
	    	<!-- Shopping Cart Section -->
	    	<div class="cart-items">
		    <h2>Your Purchase History</h2>
		
			    <!-- Individual Cart Item Section -->
			    
			    <!-- TODO: must implement history object in class -->
			    <c:forEach var="item" items="${sessionScope.history.items}">
			      
			    <div class="cart-item">
			    	<div class="item-details">
			          	<img src="images/items/${item.imgPath}" alt="Product Image">
			          	<div>
				            <span class="item-name">${item.name}</span>
				            <span class="item-price">$${item.price}</span>
			          	</div>
			        </div>
			        <div class="item-quantity">
			          	<span>Qty:</span>
			          	<span class="item-name">${item.quantityInCart}</span>
			        </div>
			    </div>
			    
				</c:forEach>
	    	</div>
	    </div>
    </div>  

	<!-- Return Button -->
    <form action="ProfileServlet" method="post">
    <div class="return-btn-container">
        <button type="submit" class="return-btn" name="movePageAction" value="viewCatalog" >Return to M&H</button>
    </div>
    </form>
</body>


</html>