<!-- 
====================================================================================
 Title: Login View
 Description: Login UI. Displays when user is not logged in and clicks on the shopping cart/profile/add to cart buttons. 
 Author: Dustin Chan
==================================================================================== 
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="StyleSheet" href="css/Login.css" type="text/css" title="cse4413" media="screen, print"/>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
	<div class="login-page">

		<!-- Logo section -->
	   	<div class="logo-container">
			<img src="images/M&H-Logo.png" width="170" height="106" alt="Temporary logo for concept"><br/>
		</div>
	
		<!-- Main container section -->
	    <div class="login-container">
	        <h1>Login</h1>
	        
	        <!-- Username & Password section -->
	        
	        <form action="LoginServlet" id="loginForm" method="post" class="login-form">
	            <div class="input-group">
					<input type="text" placeholder="Email" name="email" required>
	            </div>
	            <div class="input-group">
					<input type="password" placeholder="Password" name="password" required>
	            </div>
	            <div class="remember-me">
	                <input type="checkbox" id="remember-me" name="remember">
	                <label for="remember-me">Stay signed in</label>
	            </div>
	            <button type="submit" class="submit-btn" name="movePageAction" value="viewCatalog">Login</button>
	            <p class="forgot-password">
	                <a href="#">Forgot Password?</a><br/>
	            </p>
	         
	         <!-- Additional Options section -->
	         
	            <div class="sign-up">
       				 <button type="submit" name="movePageAction" value="viewSignUp" formnovalidate>Need an account? <b>Sign up</b></button>
    			</div>
	         </form>
        </div>
        
    </div>
</body>
</html>