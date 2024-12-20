<!-- 
====================================================================================
 Title: Additional Item Details
 Description: Accessed after clicking on an item protrait in catalog.jsp. Contains all information about the item
         from the database. This page has the ability to add to the cart, and give a popup when done so.
 Author: Dustin Chan
==================================================================================== 
-->


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Item Details - E-commerce</title>
    <link rel="StyleSheet" href="css/ItemDetails.css" type="text/css" title="cse4413" media="screen, print"/>
    <script src="js/utilities.js"></script>
</head>

<body>
    
    <!-- Header Section -->
    <jsp:include page="Header.jsp" flush="true" />
    
    <!-- Main Page Section -->
    <div class="container">
    
        <!-- Left side: Image -->
        <div class="item-image">
            <img src="images/items/${itemChosen.imgPath}" alt="https://via.placeholder.com/500">
        </div>

        <!-- Right side: Details -->
        <div class="item-details">
            <h1 class="item-name">${itemChosen.name}</h1>
            <p class="item-price">$${itemChosen.price}</p>
            <p class="item-description">${itemChosen.description}.</p>
            <p class="item-type">Type: ${itemChosen.type}</p>
            <p class="item-gender">Category: ${itemChosen.category}</p>

            <!-- Size options -->
            <form action="ItemDetailsServlet" method="post">
	            <div class="options">
	                <h3>Size</h3>
	                <div class="size-options">
	                    <button type="button" class="size-btn" name="selectedSize" value="S">S</button>
	                    <button type="button" class="size-btn" name="selectedSize" value="M">M</button>
	                    <button type="button" class="size-btn" name="selectedSize" value="L">L</button>
	                </div>
	            </div>
	
	            <!-- Color options -->
	            <div class="options">
	                <h3>Color</h3>
	                <div class="color-options">
	                    <button type="button" class="color-btn" name="selectedColour" value="Black" style="background-color: #000;"></button>
	                    <button type="button" class="color-btn" name="selectedColour" value="Red" style="background-color: #FF5733;"></button>
	                    <button type="button" class="color-btn" name="selectedColour" value="Green" style="background-color: #33FF57;"></button>
	                </div>
	            </div>
            </form>
            
            <!-- Quantity remaining -->
            <div class="quantity">
                <p><strong>Quantity Remaining:</strong> ${itemChosen.quantity}</p>
            </div>

            <!-- Add to cart button -->
            <form action="ItemDetailsServlet" method="post">
            	<button class="add-to-cart-btn" name="addAction" value="toCart" onclick="showPopup()">Add to Cart</button>
            </form>
        </div>
    </div>
    
    <!-- Return Button -->
    <form action="ItemDetailsServlet" method="post">
    <div class="return-btn-container">
        <button type="submit" class="return-btn" name="movePageAction" value="viewCatalog" >Continue Shopping</button>
    </div>
    </form>

	<!-- Popup -->
	<div class="popup" id="popup">Added to the cart!</div>
    
</body>
</html>