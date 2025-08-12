package com.pahana.Controller;

import com.pahana.Model.Payment;
import com.pahana.Service.LoginService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadExcel
 */

public class DownloadExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadExcel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("monthly")) {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=MonthlySalesReport.xls");

			PrintWriter out = response.getWriter();


			out.println("List Number\tDate\tTotal of Items");

			// Get monthly list from service
			List<Payment> monthlyList = LoginService.getMonthly(); // Adjust to your service

			double total = 0.0;

			for (Payment payment : monthlyList) {
				out.println(payment.getId() + "\t" + payment.getDate() + "\t" + payment.getSubtotal());
				total += payment.getSubtotal();
			}

			// Optionally add a total row
			out.println("\tTotal:\t" + total);
			out.flush();
			out.close();
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}
		else if(action.equals("daily"))
		{
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=DailySalesReport.xls");

			PrintWriter out = response.getWriter();
			out.println("List Number\t\tCustomer Name\tTotal Amount");

			List<Payment> dailyList = LoginService.getDaily(); // Replace with your actual service
			double total = 0.0;

			for (Payment payment : dailyList) {
				out.println(payment.getId() + "\t\t" + payment.getItem() + "\t" + payment.getSubtotal());
				total += payment.getSubtotal();
			}

			out.println("\tTotal:\t" + total);
			out.flush();
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
