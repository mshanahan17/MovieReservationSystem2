package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Address;
import model.CreditCard;
import model.User;
import model.UserDB;

/**
 * Servlet implementation class CustomerTransactionConfirmation
 */
@WebServlet("/CustomerTransactionConfirmation")
public class CustomerTransactionConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerTransactionConfirmation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String fName = ServletUtils.validateInput(request.getParameter("fName"), "");
		String lName = ServletUtils.validateInput(request.getParameter("lName"), "");
		String ccType = ServletUtils.validateInput(request.getParameter("ccType"), "");
		String ccNum = ServletUtils.validateInput(request.getParameter("ccNum"), "");
		String secCode = ServletUtils.validateInput(request.getParameter("secCode"), "");
		String date = request.getParameter("date");
		String billStreet = ServletUtils.validateInput(request.getParameter("billStreet"), "");
		String billCity = ServletUtils.validateInput(request.getParameter("billCity"), "");
		String billState = ServletUtils.validateInput(request.getParameter("billState"), "");
		String billZip = ServletUtils.validateInput(request.getParameter("billZip"), "");
		String shipStreet = ServletUtils.validateInput(request.getParameter("shipStreet"), "");
		String shipCity = ServletUtils.validateInput(request.getParameter("shipCity"), "");
		String shipState = ServletUtils.validateInput(request.getParameter("shipState"), "");
		String shipZip = ServletUtils.validateInput(request.getParameter("shipZip"), "");
		String firstCC = request.getParameter("firstCC");
		
		HttpSession session = request.getSession();
		session.removeAttribute("ccError");
		session.removeAttribute("transactionError");
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			request.getRequestDispatcher("Login.jsp").forward(request,  response);
			return;
		}
		
		CreditCard creditCard = new CreditCard();
		creditCard.setCardHolderName(fName + " " + lName);
		creditCard.setCardType(ccType);
		creditCard.setCardNumber(ccNum);
		creditCard.setCvv(secCode);
		creditCard.setExpirationDate(date);
		
		Address billingAddress = new Address();
		billingAddress.setCity(billCity);
		billingAddress.setState(billState);
		billingAddress.setStreetAddress(billStreet);
		billingAddress.setZip(billZip);
		Address shippingAddress = new Address();
		shippingAddress.setCity(shipCity);
		shippingAddress.setState(shipState);
		shippingAddress.setStreetAddress(shipStreet);
		shippingAddress.setZip(shipZip);
		
		UserDB userDB = new UserDB();
		/*
		 * Given user an initial credit card to use with a balanace of 150
		 * if this is their first card or new card.
		 * Also update their address if this is first time entering info
		 */
		if(firstCC != null) {
			creditCard.setBalance(150);
			user.setBillingAddress(billingAddress);
			user.setShippingAddress(shippingAddress);
			user.setCreditCard(creditCard);
			userDB.addCreditCardToUser(user, creditCard);
		}
		
		if(!userDB.validateCreditCard(user, creditCard)) {
			String invalidCard = "Credit Card Doesn't Match Records or No Card On File!";
			session.setAttribute("ccError", invalidCard);
			request.getRequestDispatcher("WEB-INF/Customer/CustomerTransaction.jsp").forward(request,  response);
			return;
		}
		
		double total = (double) session.getAttribute("total");
		
		if(!userDB.attemptTransaction(user, creditCard, total)) {
			String failedTransaction = "Insufficient Balance On Credit Card";
			session.setAttribute("transactionError", failedTransaction);
			request.getRequestDispatcher("WEB-INF/Customer/CustomerTransaction.jsp").forward(request,  response);
			return;
		}
		
		request.getRequestDispatcher("WEB-INF/Customer/CustomerTransactionConfirmation.jsp").forward(request,  response);
	}

}
