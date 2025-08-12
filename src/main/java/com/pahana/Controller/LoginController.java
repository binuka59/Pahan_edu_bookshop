package com.pahana.Controller;

import com.pahana.Dao.DbConnectionFactory;
import com.pahana.Model.Book;
import com.pahana.Model.Customer;
import com.pahana.Model.Item;
import com.pahana.Model.Payment;
import com.pahana.Service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService loginService;
       
	public void init() throws ServletException {
		loginService = LoginService.getInstance();
		
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> ItemList = new ArrayList<>();

		try (Connection conn = DbConnectionFactory.getConnection();
			 PreparedStatement stmt = conn.prepareStatement("SELECT Book_type FROM bookdetails");
			 ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				ItemList.add(rs.getString("Book_type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("ItemList", ItemList);


		List<String> NotyList = new ArrayList<>();

		try (Connection conn = DbConnectionFactory.getConnection();
			 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bookcategory WHERE Bquntity=0");
			 ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				NotyList.add(rs.getString("Bname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("NotyList", NotyList);






		String action = request.getParameter("action");
		 if (action.equals("show")) {
			 showregisterForm(request, response);
		 }
		 else if(action.equals("lognow"))
		 {
			showloginpage(request , response);
		 }
		 else if (action.equals("logout")) {
			 HttpSession session = request.getSession(false);
			 if (session != null) {
				 session.invalidate();
			 }
			 response.sendRedirect(request.getContextPath() + "/index.jsp");
		 }
		 else if (action.equals("home"))
		 {
			 showcustomerForm(request, response);
		 }
		 else if (action.equals("customer"))
		 {
			 List<Customer> CustomerList = new ArrayList<>();
			 CustomerList = LoginService.getAllCustomer();
			 request.setAttribute("LoginController", CustomerList);

			 request.getRequestDispatcher("WEB-INF/view/User/AddCustomer.jsp").forward(request, response);
		 }
		 else if (action.equals("item")) {
			 List<Item> ItemsList = LoginService.getAllItems();
			 request.setAttribute("itemsList", ItemsList);

			 List<Item> CategoryList = LoginService.getAllCategory();
			 request.setAttribute("categoryList", CategoryList);

			 request.getRequestDispatcher("WEB-INF/view/User/AddItems.jsp").forward(request, response);
		 }

		 else if (action.equals("payment"))
		 {
			 List<Payment> DayList = LoginService.getDaily();
			 request.setAttribute("DayList", DayList);

			 List<Payment> MonthlyList = LoginService.getMonthly();
			 request.setAttribute("MonthlyList", MonthlyList);
			 request.getRequestDispatcher("WEB-INF/view/User/Payment.jsp").forward(request, response);
		 }
		 else if (action.equals("help"))
		 {
			 request.getRequestDispatcher("WEB-INF/view/User/Help.jsp").forward(request, response);
		 }


//		response.getWriter().append("Served at: ").append(request.getContextPath());


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

	    if (action != null && action.equals("correct")) {
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        String hashedInput = PasswordUtil.hashPassword(password);

	        try (Connection connection = DbConnectionFactory.getConnection()) {

	            String query = "SELECT * FROM login WHERE Email=? AND Password=?";
	            PreparedStatement statement = connection.prepareStatement(query);
	            statement.setString(1, email);
	            statement.setString(2, hashedInput);
	            ResultSet rs = statement.executeQuery();

	            

	            if (rs.next()) {
					HttpSession session = request.getSession();
					session.setAttribute("userId", rs.getInt("id"));
					session.setAttribute("name", rs.getString("Name"));
					session.setAttribute("address", rs.getString("Address"));
					session.setAttribute("mobile", rs.getInt("Mobile"));

	                String status = rs.getString("Status");

	                if (status.equalsIgnoreCase("Admin")) {
	                	showcustomerForm(request, response);
	                } else {
	                    request.setAttribute("errorMessage", "Invalid role.");
	                    request.getRequestDispatcher("index.jsp").forward(request, response);
	                }

	            } else {
	            	
	            	    request.getSession().setAttribute("errorMessage", "Login Failed! Please try again.");
	            	    response.sendRedirect("index.jsp");
	            	    return;
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new ServletException("SQL error: " + e.getMessage());
	        }

	    } else if (action.equals("add")) {
	        String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        String rawPassword = request.getParameter("password");
	        String password = PasswordUtil.hashPassword(rawPassword); // 🔐 Hashed password
	        String status = "user";
	        
//	        System.out.println(name);
//	        System.out.println(email);
//	        System.out.println(password);
//	        System.out.println(rawPassword);

	        String checkEmailQuery = "SELECT COUNT(*) FROM login WHERE Email = ?";
	        String insertQuery = "INSERT INTO login (Name, Email, Password, Status) VALUES (?, ?, ?, ?)";

	        try (Connection connection = DbConnectionFactory.getConnection()) {

	            try (PreparedStatement checkStmt = connection.prepareStatement(checkEmailQuery)) {
	                checkStmt.setString(1, email);
	                try (ResultSet rs = checkStmt.executeQuery()) {
	                    if (rs.next() && rs.getInt(1) > 0) {
	                        request.setAttribute("errorMessage", "Email Already Exists. Try another email!");
	                        request.getRequestDispatcher("WEB-INF/view/Login/Register.jsp").forward(request, response);
	                        return;
	                    }
	                }
	            }

	            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
	                insertStmt.setString(1, name);
	                insertStmt.setString(2, email);
	                insertStmt.setString(3, password); 
	                insertStmt.setString(4, status);

	                int rowsAffected = insertStmt.executeUpdate();
	                if (rowsAffected > 0) {
	                    response.sendRedirect(request.getContextPath() + "/index.jsp");
	                } else {
	                    request.setAttribute("errorMessage", "Error creating account. Please try again.");
	                    request.getRequestDispatcher("/index.jsp").forward(request, response);
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "Database error occurred. Please try again.");
	            request.getRequestDispatcher("error.jsp").forward(request, response);
	        }
	    

	    
	        
	        doGet(request, response);
	    }

	}
	private void showcustomerForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Book> BookList = new ArrayList<>();
		BookList = LoginService.getAllBook();
		request.setAttribute("LoginController", BookList);
		request.getRequestDispatcher("WEB-INF/view/User/CustomerHome.jsp").forward(request, response);
	}
	
	private void showregisterForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/view/Login/Register.jsp").forward(request, response);
	}
	
	private void showloginpage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}


}
