package com.pahana.Controller;

import com.pahana.Dao.CustomerDao;
import com.pahana.Dao.DbConnectionFactory;
import com.pahana.Dao.ItemDao;
import com.pahana.Model.Customer;
import com.pahana.Model.Item;
import com.pahana.Service.LoginService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class ItemController
 */
@MultipartConfig( // Enables file upload handling
		fileSizeThreshold =1024*1024*2,  // 2MB
		maxFileSize =1024*1024*10,       // 10MB
		maxRequestSize =1024*1024*50     // 50MB
)
public class ItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService loginService;

	public void init() throws ServletException {
		loginService = LoginService.getInstance();

	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("deleteitem"))
		{
			Integer deleteitemid = Integer.valueOf(request.getParameter("deleteitemid"));
			ItemDao.addDeleteitem(deleteitemid);
			response.sendRedirect(request.getContextPath() + "/LoginController?action=item");
		}
		else if (action.equals("deletecata"))
		{
			Integer deletecataid = Integer.valueOf(request.getParameter("deletecataid"));
			ItemDao.adddeletecategory(deletecataid);
			response.sendRedirect(request.getContextPath() + "/LoginController?action=item");

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("additems"))
		{
			boolean emailExists = false;
			String name = request.getParameter("Iname");
			String query = "SELECT * FROM bookcategory WHERE Bname=? ";

			try(Connection connection = DbConnectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

				statement.setString(1, name);
				ResultSet resultSet = statement.executeQuery();

				if (resultSet.next()) {
					emailExists = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (emailExists) {
				request.getSession().setAttribute("errorMessage", "This Item Already Inserted.");
				response.sendRedirect(request.getContextPath() + "/LoginController?action=item");
				return;
			}
			else
			{

				Part filePart = request.getPart("Iimage");
				String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

				String uploadPath = getServletContext().getRealPath("") + File.separator + "assets/user/img/Book/";
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) uploadDir.mkdir();


				String filePath = uploadPath + File.separator + fileName;
				filePart.write(filePath);


				String price = request.getParameter("Iprice");
				Integer quntity = Integer.valueOf(request.getParameter("Iquntity"));
				String description = request.getParameter("Idescription");
				String category = request.getParameter("Icategory");

				Item item = new Item(fileName, name, price, quntity, description, category);
				ItemDao.addItemDetails(item);

			}
			response.sendRedirect(request.getContextPath() + "/LoginController?action=item");
		}
		else if (action.equals("updateitems"))
		{
			Integer itemid = Integer.valueOf(request.getParameter("itemid"));
//
			String fileName = "";
			Part filePart = request.getPart("Iimage");
			if (filePart == null || filePart.getSize() == 0 || filePart.getSubmittedFileName().isEmpty()) {
				String query = "SELECT * FROM bookcategory WHERE Bid=? ";

				try (Connection connection = DbConnectionFactory.getConnection();
					 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

					preparedStatement.setInt(1, itemid);
					ResultSet resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						fileName = resultSet.getString("Bimage");


					}
				} catch (SQLException e) {
					e.printStackTrace();
				}


			} else {



				fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

				String uploadPath = getServletContext().getRealPath("") + File.separator + "assets/user/img/Book/";
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) uploadDir.mkdir();


				String filePath = uploadPath + File.separator + fileName;
				filePart.write(filePath);

//			    response.getWriter().println("File uploaded successfully: " + fileName);
			}
			System.out.println("controll item id"+itemid);
			String iname = request.getParameter("item");
			String price = request.getParameter("price");
			Integer quntity = Integer.valueOf(request.getParameter("quntity"));
			String category = request.getParameter("category");
			String description = request.getParameter("description");

			Item item = new Item(itemid,fileName, iname, price, quntity, description, category);
			ItemDao.addUpdatesitem(item);

			response.sendRedirect(request.getContextPath() + "/LoginController?action=item");

		}
		else if (action.equals("addcategory")) {
			Part filePart = request.getPart("cimage");
			if (filePart == null) {
				request.getSession().setAttribute("errorMessage", "Insert the Suitable  image.");
				response.sendRedirect(request.getContextPath() + "/LoginController?action=item");
				return;
			} else
			{




			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

			String uploadPath = getServletContext().getRealPath("") + File.separator + "assets/user/img/Category/";
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) uploadDir.mkdir();


			String filePath = uploadPath + File.separator + fileName;
			filePart.write(filePath);

			String booktype = request.getParameter("booktype");
			boolean emailExists = false;
			String query = "SELECT * FROM bookdetails WHERE Book_type=? ";

			try (Connection connection = DbConnectionFactory.getConnection();
				 PreparedStatement statement = connection.prepareStatement(query)) {

				statement.setString(1, booktype);
				ResultSet resultSet = statement.executeQuery();

				if (resultSet.next()) {
					emailExists = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (emailExists) {
				request.getSession().setAttribute("errorMessage", "This Category Already Inserted.");
				response.sendRedirect(request.getContextPath() + "/LoginController?action=item");
				return;
			} else {

				Item item = new Item(fileName, booktype);
				ItemDao.addCategory(item);

				response.sendRedirect(request.getContextPath() + "/LoginController?action=item");
			}
		}
		}
		else if (action.equals("updatecategory"))
		{
			Integer categoryid = Integer.valueOf(request.getParameter("categoryid"));
//
			String fileName = "";
			Part filePart = request.getPart("Caimage");
			if (filePart == null || filePart.getSize() == 0 || filePart.getSubmittedFileName().isEmpty()) {
				String query = "SELECT * FROM bookdetails WHERE id=? ";

				try (Connection connection = DbConnectionFactory.getConnection();
					 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

					preparedStatement.setInt(1, categoryid);
					ResultSet resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						fileName = resultSet.getString("image");


					}
				} catch (SQLException e) {
					e.printStackTrace();
				}


			} else {



				fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

				String uploadPath = getServletContext().getRealPath("") + File.separator + "assets/user/img/Category/";
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) uploadDir.mkdir();


				String filePath = uploadPath + File.separator + fileName;
				filePart.write(filePath);

//			    response.getWriter().println("File uploaded successfully: " + fileName);
			}

			String booktype = request.getParameter("item");


			Item item = new Item(categoryid,fileName,booktype);
			ItemDao.addUpdateCategory(item);

			response.sendRedirect(request.getContextPath() + "/LoginController?action=item");
		}
		doGet(request, response);
	}

}
