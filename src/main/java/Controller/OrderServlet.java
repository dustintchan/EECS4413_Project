/*
====================================================================================
 Description: Occurs after Checkout.jsp. Does the following:
      * Add items to user purchase history.
      * Clear the user's cart 
      * Reduce quantity of items from database inventory
      * Append cart items into user's purchase history
      * Redirect the user back to the catalog.jsp

Author: Dustin Chan      
==================================================================================== 
*/

package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Cart;
import Model.PurchaseHistory;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Obtain the cart
		HttpSession session = request.getSession(true);
		Cart cart;
		PurchaseHistory history;
	        
	    // Retrieve the shopping cart for this session, if any. Otherwise, create one.
	    synchronized (session) {  // synchronized to prevent concurrent updates
	       cart = (Cart) session.getAttribute("cart");
	       if (cart == null) {  // No cart, create one.
	           cart = new Cart();
	           session.setAttribute("cart", cart);  // Save it into session
	       }
	    }
	    
	    // Retrieve the history for this session, if any. Otherwise, create one.
	    synchronized (session) {  // synchronized to prevent concurrent updates
	       history = (PurchaseHistory) session.getAttribute("history");
	       if (history == null) {  // No cart, create one.
	           history = new PurchaseHistory();
	           session.setAttribute("history", history);  // Save it into session
	       }
	    }
	    
	    // Append and clear the cart
	    history.appendCart(cart);
	    cart.clear();
		
		// Move back to catalog view
	 	String url = "/jsp/" + "Catalog.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}

}
