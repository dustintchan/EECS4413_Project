<!-- 
====================================================================================
 Title: Profile Information View
 Description: Contains logged in user information. Can edit and update creditcard and shipping information in this jsp.
 Author: Dustin Chan
==================================================================================== 
-->


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <link rel="StyleSheet" href="css/Profile.css" type="text/css" title="cse4413" media="screen, print"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profile Information View</title>
</head>
<body>

    <!-- Header Section -->
    <jsp:include page="Header.jsp" flush="true" />

    <!-- Body Section -->
    <div class="page-container">
    
        <!-- Sidebar Container -->
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
    
        <!-- Profile Information Container -->
        <div class="title-container">
            <h1>Account Management</h1>
        </div>
        
        <!-- Error and Success Messages -->
        <c:if test="${not empty requestScope.errorMessage}">
            <div class="error-message">${requestScope.errorMessage}</div>
        </c:if>
        <c:if test="${not empty requestScope.successMessage}">
            <div class="success-message">${requestScope.successMessage}</div>
        </c:if>
        
        <div class="white-container">
            <h2>Main account information</h2>
            
            <div class="profile-container">
            
            <form action="ProfileServlet" method="post">
            
                <!-- Username and password -->
                <div class="label-subtitle">Login Information:</div>
                <br>
                <div class="profile-row">
                    <div class="label">Email</div>
                    <div class="info"><input type="text" name="email" value="${sessionScope.user.email}"></div>
                    <button type="submit" class="change-btn" name="updateAction" value="email">Update</button>
                </div>
                <hr>
                <div class="profile-row">
                    <div class="label">Password</div>
                    <div class="info"><input type="password" name="password" value="${sessionScope.user.password}"></div>
                    <button type="submit" class="change-btn" name="updateAction" value="password">Update</button>
                </div>
                
                <!-- Credit card information -->
                <hr><br><br>
                <div class="label-subtitle">Credit Card Information:</div>
                <br>
                
                <div class="profile-row">
                    <div class="label">Credit Card Number</div>
                    <div class="info"><input type="text" name="cardNum" value="${sessionScope.creditCard.cardNumber}"></div>
                    <button type="submit" class="change-btn" name="updateAction" value="cardNum">Update</button>
                </div>
                <hr>
                <div class="profile-row">
                    <div class="label">Cardholder Name</div>
                    <div class="info"><input type="text" name="cardName" value="${sessionScope.creditCard.cardName}"></div>
                    <button type="submit" class="change-btn" name="updateAction" value="cardName">Update</button>
                </div>
                <hr>
                <div class="profile-row">
                    <div class="label">Credit Card Expiry Date</div>
                    <div class="info"><input type="text" name="cardExpDate" value="${sessionScope.creditCard.cardExpDate}"></div>
                    <button type="submit" class="change-btn" name="updateAction" value="cardExpDate">Update</button>
                </div>
                <hr>
                <div class="profile-row">
                    <div class="label">Security Code</div>
                    <div class="info"><input type="text" name="cardSecCode" value="${sessionScope.creditCard.cardSecCode}"></div>
                    <button type="submit" class="change-btn" name="updateAction" value="cardSecCode">Update</button>
                </div>
                
                <!-- Shipping information -->
                <hr><br><br>
                <div class="label-subtitle">Shipping Information:</div>
                <br>
                
                <div class="profile-row">
                    <div class="label">First Name</div>
                    <div class="info"><input type="text" name="fname" value="${sessionScope.user.firstName}"></div>
                    <button type="submit" class="change-btn" name="updateAction" value="fname">Update</button>
                </div>
                <hr>
                <div class="profile-row">
                    <div class="label">Last Name</div>
                    <div class="info"><input type="text" name="lname" value="${sessionScope.user.lastName}"></div>
                    <button type="submit" class="change-btn" name="updateAction" value="lname">Update</button>
                </div>
                <hr>
                <div class="profile-row">
                    <div class="label">Street Address</div>
                    <div class="info"><input type="text" name="street" value="${sessionScope.address.street}"></div>
                    <button type="submit" class="change-btn" name="updateAction" value="street">Update</button>
                </div>
                <hr>
                <div class="profile-row">
                    <div class="label">City</div>
                    <div class="info"><input type="text" name="city" value="${sessionScope.address.city}"></div>
                    <button type="submit" class="change-btn" name="updateAction" value="city">Update</button>
                </div>
                <hr>
                <div class="profile-row">
                    <div class="label">Country</div>
                    <div class="info"><input type="text" name="country" value="${sessionScope.address.country}"></div>
                    <button type="submit" class="change-btn" name="updateAction" value="country">Update</button>
                </div>
                <hr>
                <div class="profile-row">
                    <div class="label">State</div>
                    <div class="info"><input type="text" name="province" value="${sessionScope.address.province}"></div>
                    <button type="submit" class="change-btn" name="updateAction" value="province">Update</button>
                </div>
                <hr>
                <div class="profile-row">
                    <div class="label">Zip Code</div>
                    <div class="info"><input type="text" name="zip" value="${sessionScope.address.zip}"></div>
                    <button type="submit" class="change-btn" name="updateAction" value="zip">Update</button>
                </div>
                <hr>
                <div class="profile-row">
                    <div class="label">Phone</div>
                    <div class="info"><input type="text" name="phone" value="${sessionScope.address.phone}"></div>
                    <button type="submit" class="change-btn" name="updateAction" value="phone">Update</button>
                </div>
            </form>
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
