package Controller;

import java.io.IOException;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AddressDAO;
import DAO.CreditCardDAO;
import DAO.UserDAO;
import Model.Address;
import Model.CreditCard;
import Model.User;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final AtomicInteger counter = new AtomicInteger(0);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/jsp/" + "SignUp.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String thisPage = "SignUp";
		HttpSession session = request.getSession(true);
		
		
		if (!thisPage.equals(session.getAttribute("currentPage"))) {
            session.setAttribute("currentPage", thisPage);
            doGet(request, response);
		}
		else
		{
		// Move to new page if button is clicked on Login.jsp
		String moveTo = request.getParameter("movePageAction");
		String url = "";
		System.out.println(moveTo);
		
		if (moveTo == null) {
			url = "/jsp/" + "SignUp.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		 } 
		else if (moveTo.equals("viewCatalog")) {
			
			
			
			//SIGN UP SUTFF AND UPDATE DATABASE HERE
			
	        String firstName = request.getParameter("firstName");
	        String lastName = request.getParameter("lastName");
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        String confirmPassword = request.getParameter("confirmPassword");
	        String adminSecCode = request.getParameter("AdminSecCode");

	    

	        String isAdmin = "0";
	        if("1111".equals(adminSecCode)) {
	        isAdmin = "1";
	        }
	        

	        ServletContext context = getServletContext();
	        UserDAO userDAO = new UserDAO(context);
	        AddressDAO addressDAO = new AddressDAO(context);
	        CreditCardDAO creditCardDAO = new CreditCardDAO(context);
	        
	        int userId = generatePassword();

	        try {
	            User newUser = new User(userId, firstName, lastName, email, password, isAdmin);
	            userDAO.addUser(newUser);

	            session.setAttribute("userId", userId); // Store userId in session

	           
	                Address nullAddress = new Address(userId, userId, "null", "null", "null", "null", "null","null" );
	                addressDAO.addAddress(nullAddress);
	                session.setAttribute("addressId", userId); // Store addressId in session

	                CreditCard nullCard = new CreditCard(userId, userId, "null", "null", "null", "null");
	                creditCardDAO.addCreditCard(nullCard); 
	                session.setAttribute("cardId", userId); // Store cardId in session
	    
	          //  response.sendRedirect("Catalog.jsp");
	        }
	         catch (Exception e) {
	            throw new ServletException("Error adding user", e);
	        }
			
			
			
	        
	        
			// Move to catalog view after successful sign up
		 	url = "CatalogServlet";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		 } 
		}
		
	}
	
	 public static int generatePassword() {
	        long timestamp = Instant.now().toEpochMilli();
	        int count = counter.getAndIncrement() % 1000; // Ensure it stays within 3 digits
	        return (int)((timestamp % 100000) * 1000 + count); // Unique 8-digit password
	    }

}
