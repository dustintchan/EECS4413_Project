/*
====================================================================================
Description: Includes functionality to update user's information individually.

Author: Gerald Ikem , Dustin Chan      
==================================================================================== 
*/

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
import Model.Address;
import Model.CreditCard;
import Model.User;
import DAO.AddressDAO;
import DAO.CreditCardDAO;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/jsp/" + "ProfileInformation.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String thisPage = "Profile";
        HttpSession session = request.getSession(true);

        if (!thisPage.equals(session.getAttribute("currentPage"))) {
            session.setAttribute("currentPage", thisPage);
            doGet(request, response);
        } else {
            String updateAction = request.getParameter("updateAction");

            if (updateAction != null) {
                updateInformationToDatabase(request, response, updateAction);
            }

            String moveTo = request.getParameter("movePageAction");
            String url = "";

            if (moveTo == null) {
                url = "/jsp/ProfileInformation.jsp";
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
                requestDispatcher.forward(request, response);
            } else if (moveTo.equals("viewProfile")) {
                session.setAttribute("currentPage", "");
                url = "ProfileServlet";
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
                requestDispatcher.forward(request, response);
            } else if (moveTo.equals("viewProfileHistory")) {
                url = "/jsp/ProfileHistory.jsp";
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
                requestDispatcher.forward(request, response);
            } else if (moveTo.equals("viewCatalog")) {
                url = "/CatalogServlet";
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
                requestDispatcher.forward(request, response);
            }
        }
    }

    private void updateInformationToDatabase(HttpServletRequest request, HttpServletResponse response, String updateAction) throws ServletException {
        HttpSession session = request.getSession(true);
        ServletContext context = getServletContext();
        UserDAO userDAO = new UserDAO(context);
        AddressDAO addressDAO = new AddressDAO(context);
        CreditCardDAO creditCardDAO = new CreditCardDAO(context);

        int userId = (int) session.getAttribute("userId");
        int cardId = (Integer) session.getAttribute("cardId");
        int addressId = (Integer) session.getAttribute("addressId");

        try {
            User user = userDAO.getUserById(userId);
            Address address = addressDAO.getAddressByUserId(userId);
            CreditCard creditCard = creditCardDAO.getCreditCardByUserId(userId);

            String errorMessage = null;

            switch (updateAction) {
                case "email":
                    String email = request.getParameter("email");
                    if (!email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                        errorMessage = "Invalid email format.";
                    } else {
                        user.setEmail(email);
                        userDAO.updateEmail(user.getId(), user.getEmail());
                    }
                    break;

                case "password":
                    String password = request.getParameter("password");
                    if (password.length() < 8) {
                        errorMessage = "Password must be at least 8 characters long.";
                    } else {
                        user.setPassword(password);
                        userDAO.updatePassword(user.getId(), user.getPassword());
                    }
                    break;

                case "cardNum":
                    String cardNum = request.getParameter("cardNum");
                    if (!cardNum.matches("\\d{16}")) {
                        errorMessage = "Card number must be 16 digits.";
                    } else {
                        creditCard.setCardNumber(cardNum);
                        creditCardDAO.updateCardNumber(creditCard.getId(), creditCard.getCardNumber());
                    }
                    break;

                case "cardName":
                    String cardName = request.getParameter("cardName");
                    if (cardName.matches(".*\\d.*")) {
                        errorMessage = "Cardholder name must not contain numbers.";
                    } else {
                        creditCard.setCardName(cardName);
                        creditCardDAO.updateCardName(creditCard.getId(), creditCard.getCardName());
                    }
                    break;
                    
                case "cardExpDate":
                    String cardExpDate = request.getParameter("cardExpDate");
                    if (cardExpDate.matches("\\d{4}")) {
                        errorMessage = "Card number must be 4 digits.";
                    } else {
                        creditCard.setCardExpDate(cardExpDate);
                        creditCardDAO.updateCardExpDate(cardId, cardExpDate);
                    }
                    break;

                case "cardSecCode":
                    String cardSecCode = request.getParameter("cardSecCode");
                    if (!cardSecCode.matches("\\d{3}")) {
                        errorMessage = "Security code must be 3 digits.";
                    } else {
                        creditCard.setCardSecCode(cardSecCode);
                        creditCardDAO.updateCardSecCode(creditCard.getId(), creditCard.getCardSecCode());
                    }
                    break;

                case "fname":
                    String fname = request.getParameter("fname");
                    if (fname.matches(".*\\d.*")) {
                        errorMessage = "First name must not contain numbers.";
                    } else {
                        user.setFirstName(fname);
                        userDAO.updateFirstName(user.getId(), user.getFirstName());
                    }
                    break;

                case "lname":
                    String lname = request.getParameter("lname");
                    if (lname.matches(".*\\d.*")) {
                        errorMessage = "Last name must not contain numbers.";
                    } else {
                        user.setLastName(lname);
                        userDAO.updateLastName(user.getId(), user.getLastName());
                    }
                    break;

                case "street":
                    String street = request.getParameter("street");
                    if (street.isEmpty()) {
                        errorMessage = "Street cannot be empty.";
                    } else {
                        address.setStreet(street);
                        addressDAO.updateStreet(address.getId(), address.getStreet());
                    }
                    break;

                case "city":
                    String city = request.getParameter("city");
                    if (city.isEmpty()) {
                        errorMessage = "City cannot be empty.";
                    } else {
                        address.setCity(city);
                        addressDAO.updateCity(address.getId(), address.getCity());
                    }
                    break;

                case "country":
                    String country = request.getParameter("country");
                    if (country.isEmpty()) {
                        errorMessage = "Country cannot be empty.";
                    } else {
                        address.setCountry(country);
                        addressDAO.updateCountry(address.getId(), address.getCountry());
                    }
                    break;

                case "province":
                    String province = request.getParameter("province");
                    if (province.isEmpty()) {
                        errorMessage = "Province cannot be empty.";
                    } else {
                        address.setProvince(province);
                        addressDAO.updateProvince(address.getId(), address.getProvince());
                    }
                    break;

                case "zip":
                    String zip = request.getParameter("zip");
                    if (!zip.matches("\\d{5}")) {
                        errorMessage = "ZIP code must be 5 digits.";
                    } else {
                        address.setZip(zip);
                        addressDAO.updateZip(address.getId(), address.getZip());
                    }
                    break;

                case "phone":
                    String phone = request.getParameter("phone");
                    if (!phone.matches("\\d{10}")) {
                        errorMessage = "Phone number must be 10 digits.";
                    } else {
                        address.setPhone(phone);
                        addressDAO.updatePhone(address.getId(), address.getPhone());
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Invalid update action: " + updateAction);
            }

            if (errorMessage != null) {
                request.setAttribute("errorMessage", errorMessage);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/ProfileInformation.jsp");
                requestDispatcher.forward(request, response);
            } else {
                session.setAttribute("user", user);
                session.setAttribute("address", address);
                session.setAttribute("creditCard", creditCard);
                request.setAttribute("successMessage", "Update successful!");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/ProfileInformation.jsp");
                requestDispatcher.forward(request, response);
            }

        } catch (Exception e) {
            throw new ServletException("Error processing update action", e);
        }
    }
}
