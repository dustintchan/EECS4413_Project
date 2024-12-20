/*
====================================================================================
 Description: Handles page redirection based on what buttons are pressed on the website header. Header filters will be handled
        in CatalogServlet.
        
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

import Model.User;

/**
 * Servlet implementation class HeaderServlet
 */
@WebServlet("/HeaderServlet")
public class HeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HeaderServlet() {
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
		
		// Handles page redirection based on what button was pressed on the header
		String moveTo = request.getParameter("movePageAction");
		String url = "";
		
		/* TODO 
		 * must add a check if user is logged in. Expand if statements so that if user is not logged in, 
		 * redirect to login page, then direct to the original page they were suppose to go to */
		
		 if(moveTo == null) {
			// Do nothing
		 }
		 else if (moveTo.equals("viewCatalog")) {
		 	url = "/CatalogServlet";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		 } 
		 //Lead to cart servlet, which will direct to ShoppingCart.Jsp
		 else if (moveTo.equals("viewCart")) {
		 	url = "/CartServlet";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		 }
		 else if (moveTo.equals("viewProfile")) {
		 	url = "/ProfileServlet";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		 }
		 else if (moveTo.equals("viewLogin")) {
		 	url = "/jsp/Login.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		 }
		 else if (moveTo.equals("viewProfileBypass")) {
			url = "/ProfileServlet";
			HttpSession session = request.getSession(true);
			session.setAttribute("bypass", 1);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		 }
	}


}
