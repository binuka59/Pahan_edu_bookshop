package com.pahana.Controller;

import com.pahana.Dao.BookDao;
import com.pahana.Dao.DbConnectionFactory;
import com.pahana.Dao.PaymentDao;
import com.pahana.Model.Book;
import com.pahana.Model.Payment;
import com.pahana.Service.BookService;
import com.pahana.Service.LoginService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PaymentController
 */

public class PaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService loginService;

	public void init() throws ServletException {
		loginService = LoginService.getInstance();

	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("disbill")) {
			printbill(request, response);
		}
		else if(action.equals("printnow")) {
			Integer printid = Integer.valueOf(request.getParameter("printid"));
			PaymentDao.addprintbill(printid);
			response.sendRedirect(request.getContextPath() + "/LoginController?action=home");
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("bill"))
		{
			Integer mobile = Integer.valueOf(request.getParameter("mobile"));
			Double subtotal = Double.valueOf(request.getParameter("subtotal"));
			Double amount = Double.valueOf(request.getParameter("amount"));
			Double balance = Double.valueOf(request.getParameter("balance"));

			Payment payment = new Payment(mobile,subtotal,amount,balance);
			PaymentDao.addPayment(payment);
			response.sendRedirect(request.getContextPath() + "/PaymentController?action=disbill&mobile=" + mobile);

		}
		doGet(request, response);
	}
	private void printbill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer mobile = Integer.valueOf(request.getParameter("mobile"));
		List<Payment> addBillList = new ArrayList<>();
		addBillList = BookService.getaddbill(mobile);
		request.setAttribute("PaymentController", addBillList);
		request.getRequestDispatcher("WEB-INF/view/User/CalculateBill.jsp").forward(request, response);

	}

}
