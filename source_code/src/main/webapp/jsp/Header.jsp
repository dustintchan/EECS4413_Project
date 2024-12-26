<!-- 
====================================================================================
 Title: Page Header
 Description: Handles site header bar. Includes Logo, Shopping cart, and Profile buttons. Also includes filters for catalog queries.
      Added ontop of the following JSPs:
      * Catalog.jsp
      * ShoppingCart.jsp
      * Checkout.jsp
      Can lead user to following jsps:
      * Catalog.jsp (logo)
      * ShoppingCart.jsp (cart icon)
      * Login (profile icon if not signed in)
      * Profile (profile icon if user is logged in)
      * Additional details (click on catalog item)
 Author: Dustin Chan
==================================================================================== 
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!--  Import JSTL core library for if-else & loop statements -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="StyleSheet" href="css/Header.css" type="text/css" title="cse4413" media="screen, print"/>
<script src="js/utilities.js"></script>
<title>Page Header</title>
</head>

<body>
	<div class="header">
	
		<!-- Logo -->
		<form action="HeaderServlet" method="post">
		
	    	<button type="submit" name="movePageAction" value="viewCatalog" class="logo">
	    		<img src="images/M&H-Logo.png" width="50" height="31" class="logo leftAlign"> 
	    	</button>
	    	
	    </form>
	    
	    <!--  Conditionally include the catalog-view filter elements -->
	    <c:if test="${not empty isCatalogView and isCatalogView == true}">
   			<jsp:include page="HeaderFilters.jsp" flush="true" />
	    </c:if>
	    
	    <!-- Right-aligned Buttons (Cart, Profile) -->
	    <form action="HeaderServlet" method="post">
	    
		    <div class="header-buttons">
		    
		    	<button type="submit" class="buttonText" name="movePageAction" value="viewProfileBypass">Bypass Login</button>
		    
		        <button type="submit" class="buttonText" name="movePageAction" value="viewCatalog">Shop Now</button>
		        
		        <button type="submit" class="buttonText" name="movePageAction" value="viewCart">
		            <img src="images/IconCart.png" width="30" height="30">
		        </button>
		        
		        <c:if test="${user.id == null}">
    			<button type="submit" class="buttonText" name="movePageAction" value="viewLogin"> 
		            <img src="images/IconProfile.PNG" width="30" height="30">
		        </button>
				</c:if>
				<c:if test="${user.id != null}">
		        <button type="submit" class="buttonText" name="movePageAction" value="viewProfile">
		            <img src="images/IconProfile.PNG" width="30" height="30">
		        </button>
		        </c:if>
			</div>
			
		</form>
	</div>
	
</body>
</html>