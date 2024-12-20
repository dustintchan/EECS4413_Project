<!-- 
====================================================================================
 Title: Header Filter Addition
 Description: Only visible in CatalogView. Attached to header.jsp. Contains 3 dropdown menus in the header 
        used for filtering the catalog items.
 Author: Dustin Chan
==================================================================================== 
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="StyleSheet" href="css/Header.css" type="text/css" title="cse4413" media="screen, print"/>
<title>Header Filter</title>
</head>
<body>
	<!-- 3 Dropdown Menus and Search Bar -->
	<div class="header-container">
	<form id="dropdownFilters" action="CatalogServlet" method="post"> <!-- Update with your servlet name here -->
	
   	<div class="dropdown">
       <span>Men</span>
        <div class="dropdown-menu">
            <label><input type="radio" name="category" value="Men_allClothing" class="hidden-button" onclick="submitForm()">All Clothing</label>
            <label><input type="radio" name="category" value="Men_T-Shirts" class="hidden-button" onclick="submitForm()">T-Shirts</label>
            <label><input type="radio" name="category" value="Men_Hoodies & Sweatshirts" class="hidden-button" onclick="submitForm()">Hoodies & Sweatshirts</label>
            <label><input type="radio" name="category" value="Men_Hats" class="hidden-button" onclick="submitForm()">Hats</label>
            <label><input type="radio" name="category" value="Men_Leggings" class="hidden-button" onclick="submitForm()">Leggings</label>
        </div>
    	</div>
        <div class="dropdown">
            <span>Women</span>
            <div class="dropdown-menu">
                <label><input type="radio" name="category" value="Women_allClothing" class="hidden-button" onclick="submitForm()">All Clothing</label>
                <label><input type="radio" name="category" value="Women_T-Shirts" class="hidden-button" onclick="submitForm()">T-Shirts</label>
                <label><input type="radio" name="category" value="Women_Hoodies & Sweatshirts" class="hidden-button" onclick="submitForm()">Hoodies & Sweatshirts</label>
                <label><input type="radio" name="category" value="Women_Hats" class="hidden-button" onclick="submitForm()">Hats</label>
                <label><input type="radio" name="category" value="Women_dresses" class="hidden-button" onclick="submitForm()">Dresses</label>
                <label><input type="radio" name="category" value="Women_Leggings" class="hidden-button" onclick="submitForm()">Leggings</label>
                <label><input type="radio" name="category" value="Women_Skirts" class="hidden-button" onclick="submitForm()">Skirts</label>
            </div>
        </div>
        <div class="dropdown">
            <span>Unisex</span>
            <div class="dropdown-menu">
                <label><input type="radio" name="category" value="Unisex_allClothing" class="hidden-button" onclick="submitForm()">All Clothing</label>
                <label><input type="radio" name="category" value="Unisex_T-Shirts" class="hidden-button" onclick="submitForm()">T-Shirts</label>
                <label><input type="radio" name="category" value="Unisex_Hoodies & Sweatshirts" class="hidden-button" onclick="submitForm()">Hoodies & Sweatshirts</label>
                <label><input type="radio" name="category" value="Unisex_Hats" class="hidden-button" onclick="submitForm()">Hats</label>
                <label><input type="radio" name="category" value="Unisex_Leggings" class="hidden-button" onclick="submitForm()">Leggings</label>
            </div>
        </div>
        <input type="text" class="search" name="searchByKeyword" placeholder="Search for clothes...">
    </form>
    </div>
</body>
</html>