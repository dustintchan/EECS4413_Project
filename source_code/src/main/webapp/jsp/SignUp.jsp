<!-- 
====================================================================================
 Title: Sign Up View
 Description: Sign up UI. Displays when user clicks on sign up button found in Login.Jsp 
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
			<img src="images/M&H-Logo.png" width="170" height="106" alt="Temporary logo for concept"><br/>
		</div>
	
	    <div class="login-container">
	        <h1>Sign Up</h1>
	        <form action="SignUpServlet" method="post" class="login-form"> 
	        
	            <div class="input-group">
	            	<label for="email">Email:</label>
					<input type="text" name="email" required>
	            </div>
	            <div class="input-group">
	            	<label for="password">Password:</label>
					<input type="password" name="password" required>
	            </div>
	            <div class="input-group">
	            	<label for="passwordConfirm">Confirm Password:</label>
					<input type="password" name="passwordConfirm" required>
	            </div>
	            
	            <button type="submit" class="submit-btn" name="movePageAction" value="viewCatalog">Sign Up</button><br/><br/>
	            
	            <div class="remember-me">
	                <input type="checkbox" id="admin-signup" name="admin">
	                <label for="admin-signup">Sign up as admin?</label>
	            </div>
	            <div class="input-group">
	            	<label for="AdminSecCode">Enter Admin Security Code:</label>
					<input type="text" name="AdminSecCode">
	            </div>
	           
	         </form>
        </div>
        
    </div>
</body>
