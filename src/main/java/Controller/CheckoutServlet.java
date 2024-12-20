/*
====================================================================================
 Description: Responsible for inputting user credit card or shipping information. Will load user information on textfields if
    they are already present. Includes checks for incorrect user input.
 
 Author: Dustin Chan, Gerald Ikem
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

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String url = "/jsp/" + "Checkout.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String thisPage = "Checkout";
		HttpSession session = request.getSession(true);
		
		// === Not currently on the page: load JSP via doGet ===
		if (!thisPage.equals(session.getAttribute("currentPage"))) {
            session.setAttribute("currentPage", thisPage);
            doGet(request, response);
		}
		// === Currently on the page: Handle form actions on the JSP ===
		else {
			
			// TODO check the format of credit card is correct (type/form/etc)
			
			// TODO record all credit card & shipping input and record it to user's database after submit button is clicked (order history is recorded in order servlet)
			

			
			// Move to new page if button is clicked on Checkout.jsp
			String moveTo = request.getParameter("movePageAction");
			String url = "";
			
			
			if(moveTo == null) {
				// Do nothing
			}
			else if (moveTo.equals("viewCart")) {
				url = "/CartServlet";
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
				requestDispatcher.forward(request, response);
			 } 
			//Submit orders (maybe do this in OrderServlet)
			else if (moveTo.equals("viewOrders")) {
			    // Refresh the page after 2 second delay for the popup notification
			    try {
			        Thread.sleep(3000); 
			    } catch (InterruptedException e) {
			        e.printStackTrace();
			    }
			 	//url = "/jsp/" + "Catalog.jsp";
			    url = "/OrderServlet";
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
				requestDispatcher.forward(request, response);
			 } 
		}
		
		
		

	}

}
