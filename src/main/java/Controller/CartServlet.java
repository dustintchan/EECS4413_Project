/*
====================================================================================
 Description: Creates a cart session. Is responsible for updating the shoppingCart.jsp when quantity is changed
    or item is removed. Is responsible for moving to checkout.jsp.
 
 Author: Dustin Chan
==================================================================================== 
*/

package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ItemDAO;
import Model.Cart;
import Model.Item;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        // Create initial cart session if none was created yet
		Cart cart = obtainCartSession(request, response);
	   
	    // Dispatch to ShoppingCart.jsp
	 	String url = "/jsp/" + "ShoppingCart.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String thisPage = "Cart";
		HttpSession session = request.getSession(true);
		
		// === Not currently on the page: load JSP via doGet ===
		if (!thisPage.equals(session.getAttribute("currentPage"))) {
            session.setAttribute("currentPage", thisPage);
            doGet(request, response);
		}
		// === Currently on the page: Handle form actions on the JSP ===
		else {
			String action = request.getParameter("action");
			String itemID = request.getParameter("itemId");
			String itemCartQuantity = request.getParameter("quantity");
			String moveTo = request.getParameter("movePageAction");
			String url = "";
			
			// Remove button is pressed: refresh page
			if(moveTo == null && action != null) {
				int itemIDInt = Integer.parseInt(itemID); 
				
				Cart cart = obtainCartSession(request, response);
				cart.changeQuantity(itemIDInt, 1);
				cart.remove(itemIDInt);
				
				refreshJSP(request, response);
			}
			// Quantity value changed: refresh page
			else if(moveTo == null && action == null && itemCartQuantity != null) {
			    int itemIDInt = Integer.parseInt(itemID); 
			    int quantityInt = Integer.parseInt(itemCartQuantity); 
				
				Cart cart = obtainCartSession(request, response);
			    cart.changeQuantity(itemIDInt, quantityInt);
			    
			    refreshJSP(request, response);
			}
			// Extra fallback case
			else if(moveTo == null) {
				refreshJSP(request, response);
			}
			// When shopping cart button is pressed again
			else if(moveTo.equals("viewCart")) {
				refreshJSP(request, response);
			}
			// Move to checkout page
			else if (moveTo.equals("viewCheckout")) {
			 	url = "/CheckoutServlet";
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
				requestDispatcher.forward(request, response);
			} 
			// Move to login page if not logged in
			else if (moveTo.equals("viewLogin")) {
			 	url = "/jsp/Login.jsp";
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
				requestDispatcher.forward(request, response);
			} 
		}
	}
	
	/* Refresh the JSP if no page movement was indicated */
	private void refreshJSP (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		HttpSession session = request.getSession(true);
		session.setAttribute("currentPage", ""); //Prevents Infinite Loop
		String url = "CartServlet";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}
	
	/* Retrieve the cart session, otherwise create one */
	private Cart obtainCartSession (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
		// Retrieve current HTTPSession object. If none, create one.
		HttpSession session = request.getSession(true);
		Cart cart;
	        
	    // Retrieve the shopping cart for this session, if any. Otherwise, create one.
	    synchronized (session) {  // synchronized to prevent concurrent updates
	       cart = (Cart) session.getAttribute("cart");
	       if (cart == null) {  // No cart, create one.
	           cart = new Cart();
	           session.setAttribute("cart", cart);  // Save it into session
	       }
	    }
	    
	    return cart;
	}
	
	

}
