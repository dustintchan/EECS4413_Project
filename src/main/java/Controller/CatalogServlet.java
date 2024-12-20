/*
====================================================================================
 Description: Responsible for catalog filter changes and movement to itemDetails.jsp. Dispatches information
     to itemDetailsServelet that is required for item information to load.
 
 Author: Gerald Ikem, Dustin Chan
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
import Model.Item;

/**
 * Servlet implementation class CatalogServlet
 */
@WebServlet("/CatalogServlet")
public class CatalogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CatalogServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        try {
        	// Initialize the ItemDAO and fill in the javabeans
            ItemDAO itemDao = new ItemDAO(getServletContext());
            List<Item> items = itemDao.getAllItems();
            List<String> brands= itemDao.getDistinctBrands();

            ServletContext context = getServletContext();
            context.setAttribute("inventoryList", items);
            context.setAttribute("brandsList", brands);
            
        } catch (Exception e) {
            throw new ServletException("Failed to initialize the servlet.", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	//Load the JSP
    	HttpSession session = request.getSession(true);
        session.setAttribute("currentPage", "Catalog");

        String url = "/jsp/Catalog.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String thisPage = "Catalog";
		HttpSession session = request.getSession(true);
		
		// === Not currently on the page: load JSP via doGet ===
		if (!thisPage.equals(session.getAttribute("currentPage"))) {
            doGet(request, response);
		}
		// === Currently on the page: Handle form actions on the JSP ===
		else {
			
	        // Determine which page to move to
			String moveTo = request.getParameter("movePageAction");
			String url = "";

			// No movement from page
			if(moveTo == null) {
	            String category = request.getParameter("category");
	            String keyword = request.getParameter("searchByKeyword");
	            String sort = request.getParameter("sortBy");
	            String brand = request.getParameter("brand");
	            
				updateCatalogQuery(request, response, category, keyword, sort, brand);
			}
			// A clothing Item is pressed
			else if (moveTo.equals("viewDetails")) {
				
				//Obtain item ID from catalog.jsp & pass it along to ItemDetailsServlet
				String itemID = request.getParameter("itemID");
				session.setAttribute("itemID", itemID);

				// Move to item details
				url = "/ItemDetailsServlet";
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
				requestDispatcher.forward(request, response);
				
			 } 
			// M&H logo/'Shop Now' is pressed
			else {
			    // Reset filters & Show entire inventory
	            String category = null;
	            String keyword = null;
	            String sort = "none";
	            String brand = "allBrands";
				
	            updateCatalogQuery(request, response, category, keyword, sort, brand);
			}
		}
    }
    
    /* Update the list of items viewable on catalog.jsp based on the filters pressed by the user */
    private void updateCatalogQuery(HttpServletRequest request, HttpServletResponse response, String category, 
    		String searchByKeyword, String Sort, String brand) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession(true);
    	
    	try {
            ItemDAO itemDao = new ItemDAO(getServletContext());
            List<Item> items = null;
            //System.out.println(searchByKeyword);

            if (searchByKeyword != null && !searchByKeyword.trim().isEmpty()) {
                // Perform keyword search

                items = itemDao.searchItems(searchByKeyword.toLowerCase());
                if (items.isEmpty()) {
                    request.setAttribute("message", "No items found matching the keyword: " + searchByKeyword);
                }
            } else if (category != null && !category.trim().isEmpty()) {
                String[] parts = category.split("_");
                String type = parts[0];
                String categoryName = parts[1];

     

        
                items = itemDao.filterItemsByTypeAndCategory(type, categoryName);
            } else if (Sort != null && !Sort.trim().isEmpty() || brand != null && !brand.trim().isEmpty()){
            	
            	String[] parts = null;
                String SortBy = null;
                String SortOrder = null;
                
                if (Sort != null && Sort.contains("_")) {
                    parts = Sort.split("_");
                    SortBy = parts[0];
                    SortOrder = parts[1];
                }
                
                items = itemDao.SortAndBrand(SortBy, SortOrder,brand);
            	
            }
            	else {
                // Default: fetch all items
                items = itemDao.getAllItems();
            }

            if (items != null) {
                session.setAttribute("inventoryList", items);
            }
        } catch (SQLException e) {
            throw new ServletException("Error processing catalog filters and sorting", e);
        }
		
	    // Update the catalog JSP
	    String url =  "/jsp/" + "Catalog.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
    }
}
