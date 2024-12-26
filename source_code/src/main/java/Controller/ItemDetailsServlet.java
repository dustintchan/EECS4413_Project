/*
====================================================================================
 Description: Responsible for loading additional item information to send to its associated jsp. Includes functionality to add
      current item being displayed to the cart. Also includes a timed delay to allow enough time for a popup to appear.
 
 Author: Dustin Chan
==================================================================================== 
*/

package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Cart;
import Model.Item;

/**
 * Servlet implementation class ItemDetailsServlet
 */
@WebServlet("/ItemDetailsServlet")
public class ItemDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Obtain item ID from CatalogServlet
		HttpSession session = request.getSession(true);
		String itemID = (String) session.getAttribute("itemID");
		int itemIDInt = Integer.parseInt(itemID); 
		
		//Send item info to the jsp
		Item item = getItemByID(itemIDInt);
		session.setAttribute("itemChosen", item);
		
		// Load the item details jsp
		String url = "/jsp/" + "ItemDetails.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String thisPage = "Details";
		HttpSession session = request.getSession(true);
		
		// === Not currently on the page: load JSP via doGet ===
		if (!thisPage.equals(session.getAttribute("currentPage"))) {
            session.setAttribute("currentPage", thisPage);
            doGet(request, response);
		}
		// === Currently on the page: Handle form actions on the JSP ===
		else {
			
			String action = request.getParameter("addAction");
			
			//Add to cart button is pressed
			if(action != null) {
				addItemToCart(request, response);
			}
			// Add to cart button is not pressed
			else {
				// Move to new page if button is clicked on Checkout.jsp
				String moveTo = request.getParameter("movePageAction");
				String url = "";
				
				//When "Add to cart" button is pressed
				if(moveTo == null) {
					//Refresh the page
					url = "/jsp/" + "ItemDetails.jsp";
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
					requestDispatcher.forward(request, response);
				}
				else if (moveTo.equals("viewCatalog")) {
					url = "/CatalogServlet";
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
					requestDispatcher.forward(request, response);
				 } 
			}
		}
		
	}
	
	/* Used to load item information onto jsp */
	private Item getItemByID(int id) {
		
		ServletContext context = getServletContext();
		List<Item> items = (List<Item>) context.getAttribute("inventoryList");
		
		for (Item item : items) {
			if (item.getId() == id) {
	             return item;
	        }
		}
		return null;
	}
	
	/* Occur when "Add to cart" button is pressed. Handles addition to cart & pop up notification */
	private void addItemToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
	    
	    // Retrieve the item & add to cart
		String itemID = (String) session.getAttribute("itemID");
		int itemIDInt = Integer.parseInt(itemID); 
		Item item = getItemByID(itemIDInt);
	    
	    cart.add(item);
	    
	    // Refresh the page after 2 second delay for the popup notification
	    try {
	        Thread.sleep(2000); 
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    String url = "/jsp/ItemDetails.jsp";
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
	    requestDispatcher.forward(request, response);	
	}

}
