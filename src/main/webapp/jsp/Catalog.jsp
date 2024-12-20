<!-- 
====================================================================================
 Title: Catalog view
 Description: The main view & home-page. Outputs inventory based on what is forwarded by controller servlet.
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
<link rel="StyleSheet" href="css/Catalog.css" type="text/css" title="cse4413" media="screen, print"/>
<title>Catalog View</title>
</head>
<body>
    <!-- Set a request attribute so header.jsp knows to add additional elements -->
    <% request.setAttribute("isCatalogView", true); %>
	<jsp:include page="Header.jsp" flush="true" />
	
    <!-- Sorting filters -->
    <form id="sortFilters" action="CatalogServlet" method="post">

       <div class="sortingFilters">
          <label class="subTitle">Alternatively Filter By: &ensp;&ensp;</label>
          <label>Sort: &ensp;</label>
          <select name="sortBy">
              <option value="none" selected>None</option>
              <option value="name_Asc">Alphabetically (Ascending)</option>
              <option value="name_Desc">Alphabetically (Descending)</option>
              <option value="price_Asc">Price (Ascending)</option>
              <option value="price_Desc">Price (Descending)</option>
          </select>

          <label>&emsp; Brands: &ensp;</label>
          <select name="brand">
          
              <option value="allBrands">All Brands</option>
              <!-- Loop over the list of brands (using EL) -->
              <c:forEach var="brand" items="${applicationScope.brandsList}">
                  <option value="${brand}">${brand}</option>
              </c:forEach>
              
          </select>
          
          <span>&ensp;</span>
          <button type="submit">Filter</button>
     </div>
     </form>

	<!-- Main Content Section (Catalog) -->
    <div class="catalog">
        
       	<!-- Catalog Item Loop -->
       	<c:forEach var="item" items="${inventoryList}">
           	
           	<form action="CatalogServlet" method="post">
           	
           	<input type="hidden" name="itemID" value="${item.id}">
           	
           	<button type="submit" class="catalog-item" name="movePageAction" value="viewDetails">
                <!-- <img src="https://via.placeholder.com/200x250" alt="Item Image"> -->
                <img src="images/items/${item.imgPath}" alt="${item.imgPath}">
                <h3>${item.name}</h3>
                <p class="brand">${item.brand}</p>
                <p class="price">$${item.price}</p>
          	</button>
          	
          	</form>
          	
       	</c:forEach>  
    </div>
	
</body>
</html>