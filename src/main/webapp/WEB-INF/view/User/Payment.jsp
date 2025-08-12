<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@ page import="java.util.List" %>
        <%@ page import="com.pahana.Model.Payment" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pahan Bookshop</title>

<link rel="shortcut icon" href="assets/images/book.jpg">
  <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Inter:wght@100;200;300;400;500;600;700;800;900&family=Amatic+SC:wght@400;700&display=swap" rel="stylesheet">

  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />

        <link rel="stylesheet" href="assets/user/css/customer.css">
        <link rel="stylesheet" href="assets/user/css/category.css">
        <link rel="stylesheet" href="assets/user/css/style.css">
        <link rel="stylesheet" href="assets/user/css/addcus.css">
        <link rel="stylesheet" href="assets/user/css/modal.css">

    <link rel="stylesheet" href="assets/user/css/footer.css">

   <link href="assets/user/css/bootstrap.min.css" rel="stylesheet">
   <link href="assets/user/css/bootstrap-icons.css" rel="stylesheet">

           <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
           <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
           <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

   </head>
   <body>
        <%@ include file="header.jsp" %>
        <%
            double subtotal = 0.0;
            double fulAmount = 0.0;
            double subtotals = 0.0;
            double total = 0.0;


            List<Payment> MonthlyList = (List<Payment>) request.getAttribute("MonthlyList");
            List<Payment> DayList = (List<Payment>) request.getAttribute("DayList");
        %>

        <section class="bookdetails pb-4">
          <div class="container-lg">
            <div class="row">
              <div class="my-4 position-relative">
                <h2>📊 Daily Payment</h2>

                <%
                    if (DayList != null) {

                    }
                %>

                <%
                    String error = (String) session.getAttribute("errorMessage");
                    if (error != null) {
                %>
                <h3 style="color: red; text-align:center; font-size:1.3rem; font-weight:bold; font-family:Georgia;">
                    <%= error %>
                </h3>
                <%
                        session.removeAttribute("errorMessage");
                    }
                %>
              </div>
            </div>
          </div>
        </section>

        <div class="customer">
          <div class="container-fluid box" style="border:3px solid black;">
            <table class="table" style="text-align:center;">
              <h4>Daily Sale Report</h4>
              <thead>
                <tr class="table-info">
                  <th scope="col">Number</th>
                  <th scope="col">Customer Name</th>
                  <th scope="col">Total Amount</th>
                </tr>
              </thead>
              <tbody>
                <%
                    if (DayList != null && !DayList.isEmpty()) {
                        for (Payment payment : DayList) {
                            subtotal = payment.getSubtotal();
                            fulAmount += subtotal;
                %>
                <tr>
                  <td><%= payment.getId() %></td>
                  <td><%= payment.getItem() %></td>
                  <td><%= payment.getSubtotal() %>0</td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                  <td colspan="3" style="text-align: center; font-weight: bold; font-size: 18px;">
                    Not a Sale Today.
                  </td>
                </tr>
                <%
                    }
                %>
              </tbody>
            </table>
          </div>
        </div>

        <div class="top booking">
          <div class="container-fluid py-6">
            <div class="container">
              <div class="row">
                <div class="col-md-4 form-group mt-3 mt-md-0"></div>
                <div class="col-md-4  form-group mt-3 mt-md-0">
                  <a href="#" class="btn btn-info"
                     style="width:30rem; font-size:1.5rem; font-weight:bold; border-radius:.5rem; border:2px solid black;">
                   Total  : Rs. <%= fulAmount %>0
                  </a>
                </div>
             <div class="col-md-1 form-group mt-3 mt-md-0"></div>
             <div class="col-md-3 form-group mt-3 mt-md-0">
                <a href="DownloadExcel?action=daily" class="btn btn-success"
                   style="font-size:1.5rem; font-weight:bold; border-radius:.5rem; border:2px solid black;">
                    📥 Download Excel
                </a>
             </div>
              </div>
            </div>
          </div>
        </div>

     <section class="bookdetails" style="padding-top: 70px;">
       <div class="container-lg">
         <div class="row">
           <div class="position-relative">
             <h2 class="text-center">Monthly Sales</h2>
           </div>
         </div>
       </div>
     </section>

     <div class="customer">
       <div class="container-fluid box" style="text-align:center;border:3px solid black;">
         <table class="table">
           <h4>Monthly Sales Report</h4>
           <thead>
             <tr class="table-info">
               <th scope="col">List Number</th>
               <th scope="col">Date</th>
               <th scope="col">Total of Items</th>
             </tr>
           </thead>
           <tbody>
             <%
               if (MonthlyList != null) {
                   for (Payment payment : MonthlyList) {
                       subtotals = payment.getSubtotal();
                       total += subtotals;
             %>
             <tr>
               <td><%= payment.getId() %></td>
               <td><%= payment.getDate() %></td>
               <td><%= payment.getSubtotal() %>0</td>
             </tr>
             <%
                   }
               } else {
             %>
             <tr>
               <td colspan="3" style="text-align: center; font-weight: bold; font-size: 18px;">
                 No Monthly Sales Details to Display.
               </td>
             </tr>
             <%
               }
             %>
           </tbody>
         </table>
       </div>
     </div>

     <!-- Display Total Section -->
     <div class="top booking">
       <div class="container-fluid py-6">
         <div class="container">
           <div class="row">
             <div class="col-md-4 form-group mt-3 mt-md-0"></div>
             <div class="col-md-4 form-group mt-3 mt-md-0">
               <a href="#" class="btn btn-info"
                  style="width:30rem; font-size:1.5rem; font-weight:bold; border-radius:.5rem; border:2px solid black;">
                 Total: Rs. <%= total %>0
               </a>
             </div>
             <div class="col-md-1 form-group mt-3 mt-md-0"></div>
             <div class="col-md-3 form-group mt-3 mt-md-0">
                <a href="DownloadExcel?action=monthly" class="btn btn-success"
                   style="font-size:1.5rem; font-weight:bold; border-radius:.5rem; border:2px solid black;">
                   📥 Download Excel
                </a>
             </div>

           </div>
         </div>
       </div>
     </div>


      <section  class="banner section dark-background">

		      <div class="container" data-aos="fade-up" data-aos-delay="100">

		        <div class="swiper init-swiper">
		          <div class="swiper-wrapper">

		            <div class="swiper-slide">
		              <div class="testimonial-item">

		                <h3>Call 24-Hour Service Available</h3>
		                <p>
		                  <span>Contact us for your problems</span>
		                </p>
		                <h3>(077) 377 7666</h3>
		              </div>
		            </div>

		          </div>
		          <div class="swiper-pagination"></div>
		        </div>

		      </div>

		    </section>

		    <%@ include file="footer.jsp" %>
                 <script>
                     document.getElementById("year").textContent = new Date().getFullYear();
                 </script>
            <script src="assets/user/js/main.js"></script>
            <script src="assets/user/js/about.js"></script>
            <script src="assets/user/swiper/swiper-bundle.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
            <script src="assets/user/js/script.js"></script>
            <script src="assets/user/js/js/bootstrap.bundle.min.js"></script>

</body>
</html>