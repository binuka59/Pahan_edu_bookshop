package com.pahana.Controller;

import com.pahana.Dao.CustomerDao;
import com.pahana.Dao.DbConnectionFactory;
import com.pahana.Model.Customer;
import com.pahana.Service.LoginService;
import java.sql.ResultSet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class CustomerController
 */
@MultipartConfig( // Enables file upload handling
		fileSizeThreshold =1024*1024*2,  // 2MB
		maxFileSize =1024*1024*10,       // 10MB
		maxRequestSize =1024*1024*50     // 50MB
)
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService loginService;

	public void init() throws ServletException {
		loginService = LoginService.getInstance();

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("deletecustomer"))
		{
			Integer deleteid = Integer.valueOf(request.getParameter("deleteid"));
			CustomerDao.addremovedetails(deleteid);
			response.sendRedirect(request.getContextPath() + "/LoginController?action=customer");
		}
		else if (action.equals("getNameByMobile")) {
			String mobile = request.getParameter("mobile");
			String name = "";

			String query = "SELECT Cname FROM customer WHERE Cmobile = ?";
			try (Connection connection = DbConnectionFactory.getConnection();
				 PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setString(1, mobile);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					name = rs.getString("Cname");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			response.setContentType("text/plain");
			response.getWriter().write(name);
			return;
		}
		else if (action.equals("getPriceByItemNo")) {
			String itemNo = request.getParameter("itemno");
			String price = "";

			String query = "SELECT Bprice FROM bookcategory WHERE Bid = ?";
			try (Connection connection = DbConnectionFactory.getConnection();
				 PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setString(1, itemNo);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					price = rs.getString("Bprice");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			response.setContentType("text/plain");
			response.getWriter().write(price);
			return;
		}
		else if (action.equals("checkQuantity")) {
			int itemId = Integer.parseInt(request.getParameter("itemnu"));
			int userQty = Integer.parseInt(request.getParameter("qty"));
			int availableQty = 0;

			String query = "SELECT Bquntity FROM bookcategory WHERE Bid = ?";
			try (Connection con = DbConnectionFactory.getConnection();
				 PreparedStatement stmt = con.prepareStatement(query)) {
				stmt.setInt(1, itemId);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					availableQty = rs.getInt("Bquntity");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (userQty > availableQty) {
				response.getWriter().write("Out of stoke");
			} else {
				response.getWriter().write("valid");
			}
			return;
		}




		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");


		if (action.equals("updatecustomer")) {
			Integer custid = Integer.valueOf(request.getParameter("custid"));
			System.out.println("id is" + custid);
			String fileName = "";
			Part filePart = request.getPart("eimage");
			if (filePart == null || filePart.getSize() == 0 || filePart.getSubmittedFileName().isEmpty()) {
				String query = "SELECT * FROM customer WHERE Cid=? ";

				try (Connection connection = DbConnectionFactory.getConnection();
					 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

					preparedStatement.setInt(1, custid);
					ResultSet resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						 fileName = resultSet.getString("Cimage");


					}
				} catch (SQLException e) {
					e.printStackTrace();
				}


			} else {



				fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

				String uploadPath = getServletContext().getRealPath("") + File.separator + "assets/user/img/Customer/";
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) uploadDir.mkdir();


				String filePath = uploadPath + File.separator + fileName;
				filePart.write(filePath);

//			    response.getWriter().println("File uploaded successfully: " + fileName);
			}

				String ename = request.getParameter("ename");
				Integer emobile = Integer.valueOf(request.getParameter("emobile"));
				String eaddress = request.getParameter("eaddress");
				String eemail = request.getParameter("eemail");

//				System.out.println("file"+ename);
//				System.out.println("file"+emobile);
//				System.out.println("file"+eaddress);
//				System.out.println("file"+eemail);

				Customer customer = new Customer(custid, fileName, ename, eemail, emobile, eaddress);
				CustomerDao.addCustomerDetails(customer);

			   response.sendRedirect(request.getContextPath() + "/LoginController?action=customer");

		}else if(action.equals("addcustomer"))
		{
			boolean emailExists = false;
			String email = request.getParameter("email");
			String query = "SELECT * FROM customer WHERE Cemail=? ";

			try(Connection connection = DbConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

				statement.setString(1, email);
				ResultSet resultSet = statement.executeQuery();

				if (resultSet.next()) {
					emailExists = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (emailExists) {
				request.getSession().setAttribute("errorMessage", "Email Already Inserted.");
				response.sendRedirect(request.getContextPath() + "/LoginController?action=customer");
				return;
			}
			else
			{
				String fileName="";
				Part filePart = request.getPart("image");
				if (filePart == null || filePart.getSize() == 0 || filePart.getSubmittedFileName().isEmpty())
				{
					fileName="user.jpg";
				}
				else
				{
					fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

					String uploadPath = getServletContext().getRealPath("") + File.separator + "assets/user/img/Customer/";
					File uploadDir = new File(uploadPath);
					if (!uploadDir.exists()) uploadDir.mkdir();

					String filePath = uploadPath + File.separator + fileName;
					filePart.write(filePath);
				}


				String name = request.getParameter("name");
				Integer mobile = Integer.valueOf(request.getParameter("mobile"));
				String address = request.getParameter("address");


				Customer customer = new Customer(fileName, name, email, mobile, address);
				CustomerDao.addCustomer(customer);
				response.sendRedirect(request.getContextPath() + "/LoginController?action=customer");
			}
		}

		doGet(request, response);
	}

}
