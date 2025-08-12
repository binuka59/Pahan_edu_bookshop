package com.pahana.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pahana.Dao.DbConnectionFactory;
import com.pahana.Dao.BookDao;
import com.pahana.Model.Book;
import com.pahana.Model.Item;
import com.pahana.Service.BookService;
import com.pahana.Service.LoginService;

/**
 * Servlet implementation class BookController
 */

public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookservice;


	public void init() throws ServletException {
		bookservice = BookService.getInstance();

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String Viewid =request.getParameter("Viewid");
		if (action.equals("View")) {
			List<Book> BookList = new ArrayList<>();
			BookList = BookService.getBook(Viewid);
			request.setAttribute("BookController", BookList);
			request.getRequestDispatcher("WEB-INF/view/User/Bookdetail.jsp").forward(request, response);
		}
		else if(action.equals("Book"))
		{
			addingbook(request, response);

		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("addbook"))
		{
			String address = request.getParameter("address");
			String mobile = request.getParameter("mobile");
		} else if (action.equals("addquntity"))
		{
			Integer quntityid = Integer.valueOf(request.getParameter("quntityid"));
			Integer quntity = Integer.valueOf(request.getParameter("itemcount"));

			Book book = new Book(quntityid,quntity);
			BookDao.addquntiy(book);
			response.sendRedirect(request.getContextPath() + "/BookController?action=Book");


		}

		doGet(request, response);
	}


	private void addingbook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String Bookid =request.getParameter("Bookid");
		List<Book> addList = new ArrayList<>();
		addList = BookService.getaddBook(Bookid);
		request.setAttribute("BookController", addList);
		request.getRequestDispatcher("WEB-INF/view/User/Addbookdetails.jsp").forward(request, response);

	}
}
