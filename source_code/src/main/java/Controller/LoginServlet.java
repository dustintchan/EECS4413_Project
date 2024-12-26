package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import Model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String thisPage = "Login";
		HttpSession session = request.getSession(true);
		session.setAttribute("currentPage", thisPage);

			
		// Move to new page if button is clicked on Login.jsp
		String moveTo = request.getParameter("movePageAction");
		String url = "";
		
		if (moveTo == null) {
			url = "/jsp/" + "Login.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		 } 
		else if (moveTo.equals("viewSignUp")) {
			url = "SignUpServlet";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		 } 
		// If user clicks on "Login" button after putting in email and password
		else if (moveTo.equals("viewCatalog")) {
			
			
			
			//TODO (YOUR CODE) LOGIN SUTFF AND UPDATE DATABASE HERE
			
			String email = request.getParameter("email");
	        String password = request.getParameter("password");

	        ServletContext context = getServletContext();
	        UserDAO userDAO = new UserDAO(context);

	        try {
	            User user = userDAO.validateUser(email, password);
	            if (user != null) {
	                request.getSession().setAttribute("user", user);
	               // response.sendRedirect("Catalog.jsp");
	                session.setAttribute("userId", user.getId());
	                session.setAttribute("cardId", user.getId());
	                session.setAttribute("addressId", user.getId());// Store userId in session
	            } else {
	                request.setAttribute("errorMessage", "Invalid email or password.");
	                request.getRequestDispatcher("Login.jsp").forward(request, response);
	            }
	        } catch (Exception e) {
	            throw new ServletException("Error validating user", e);
	        }
			
	        
	        
	     // Move to catalog view after successful log in
		 	url = "CatalogServlet";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		 } 
	}

}

