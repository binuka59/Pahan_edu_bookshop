package com.pahana.Controller;

import com.pahana.Dao.DbConnectionFactory;
import com.pahana.Dao.ForgetDao;
import com.pahana.Model.Forget;
import com.pahana.Service.EmailService;
import com.pahana.Service.ForgetService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class ChangeController
 */
//@WebServlet("/ChangeController")
public class ChangeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ForgetService forgetService;

	public void init() throws ServletException {
		forgetService = ForgetService.getInstance();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("shows"))
		{
			request.getRequestDispatcher("WEB-INF/view/Login/forget.jsp").forward(request , response);
		}
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("enter"))
		{
			reset(request , response);


		} else if (action.equals("verify"))
		{
			changepass(request , response);
		} else if (action.equals("change")) {
			changepassword(request , response);
		}

		doGet(request, response);
	}
	protected void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");

		Forget forget = new Forget(email);
		forget.setEmail(email);

		

		try {
			// Fix: Correct SQL Query
			String query = "SELECT * FROM login WHERE Email=? ";
			Connection connection = DbConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				String email1 = rs.getString("Email");
				
				System.out.println("Your  email is"+email1);
				System.out.println("Your  old email is"+email);
				if (email1.equals(email)) {
					addcode(request,response);
	
					
				    request.getRequestDispatcher("WEB-INF/view/Login/verify.jsp").forward(request, response);
					return;
				}

				
			}else {
				request.setAttribute("errorMessage", "Your Password incorrect! Please try again.");

				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/Login/forget.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
		    e.printStackTrace();
		    throw new ServletException("Database error: " + e.getMessage());
		}


//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void addcode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SecureRandom secureRandom = new SecureRandom();
		int code = 100000 + secureRandom.nextInt(900000);
		String codeStr = String.valueOf(code);

		String email = request.getParameter("email");

		Forget forget = new Forget(codeStr,email);
		forget.setVerify(codeStr);
		forget.setEmail(email);
		ForgetDao.addData(forget);

		String messageBody = "Your password is Reset \n\n";
		messageBody += "Your Code: " + codeStr + "\n";
		messageBody += "THANK YOU...\n";
		messageBody += "Pahana Edu Bookshop \n";
		String subject = "Pahan edu BookShop Reset Code ";

		EmailService EmailService = new EmailService();
		EmailService.sendEmail(email,  messageBody, subject);


	}
	
	private void changepass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code="";
		for (int i=1;i<=6;i++)
		{
			String num = request.getParameter("verify"+i);
			code=code+num;

		}
		try {
			String email = Forget.getEmail();
			String Query ="SELECT * FROM login WHERE email =?";
			Connection connection = DbConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(Query);

			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();

			rs.next();
				int verifycode = rs.getInt("verify");

					int enteredCode = Integer.parseInt(code);
					if (enteredCode == verifycode) {
						request.getRequestDispatcher("WEB-INF/view/Login/newpassword.jsp").forward(request, response);
					} else {
						request.setAttribute("errorMessage", "Check & input correct verification code.");
						request.getRequestDispatcher("WEB-INF/view/Login/verify.jsp").forward(request, response);
					}




		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	private void changepassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String newpass = request.getParameter("newpassword");
        String hashpassword = PasswordUtil.hashPassword(newpass);
		String renewpass = request.getParameter("newrepassword");
		if(newpass.equals(renewpass))
		{
			Forget forget = new Forget(hashpassword);
			forget.setPassword(hashpassword);
			ForgetDao.changeData(forget);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Don't match your inserted password.Try Again.");
			request.getRequestDispatcher("WEB-INF/view/Login/newpassword.jsp").forward(request, response);
		}
	}


}
