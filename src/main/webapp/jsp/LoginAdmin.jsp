<!-- 
====================================================================================
 Title: Admin Log In View
 Description: Admin Login UI. Displays when user clicks on 'sign in as admin' button found in Login.Jsp 
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

	   	<div class="logo-container">
			<img src="M&H-Logo.png" width="170" height="106" alt="Temporary logo for concept"><br/>
		</div>
	
	    <div class="login-container">
	        <h1>Admin Login</h1>
	        
	        <!-- *Note: Feel free to change form action structuring to suite
	        * backend purposes more appropriately -->
	        
	        <!-- Username & Password section -->
	        
	        <form action="#####" method="post" class="login-form"> <!-- *Update with servlet* -->
	            <div class="input-group">
					<input type="text" placeholder="Username or Email" name="username" required>
	            </div>
	            <div class="input-group">
					<input type="password" placeholder="Password" name="password" required>
	            </div>
	            <div class="remember-me">
	                <input type="checkbox" id="remember-me" name="remember">
	                <label for="remember-me">Stay signed in</label>
	            </div>
	            <button type="submit" class="submit-btn">Login</button>
	            <p class="forgot-password">
	                <a href="#">Forgot Password?</a><br/>
	            </p>
	         </form>
	         
	         <!-- Additional Options section -->
	         
	         <form action="#####" method="post" class="login-form">
	            <p class="sign-up">
	            	<a href="#">Need an account? <b>Sign up</b></a><br/>
	            </p>
	         </form>
	         
	         <form action="#####" method="post" class="login-form">
	            <p class="admin-signin">
	            	<a href="#">Sign in as customer</a>
	            </p>
	         </form>
        </div>
        
    </div>
</body>
</html>