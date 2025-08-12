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
    <link rel="stylesheet" href="assets/user/css/cart.css">


    <link rel="stylesheet" href="assets/user/css/footer.css">
    <link rel="stylesheet" href="assets/driver/css/payment.css">
   <link href="assets/user/css/bootstrap.min.css" rel="stylesheet">
   <link href="assets/user/css/bootstrap-icons.css" rel="stylesheet">

   </head>
<body>
<%@ include file="header.jsp"%>

 <section class="bookdetails" class="pb-4">
      <div class="container">
        <div class="row">
          <div class=" my-2">
            <h2>💳 Bill</h2>

          </div>
        </div>
      </div>
 </section>

<div class="card container" id="print-section" style="width:60rem;" >
  <div class="card-body mx-4">
    <div class="baner">
        <div style="text-align:center;">
          <h6>Pahana Edu Bookshop</h6>
          <h6> Main Road,Colombo</h6>
           <h6> 011-7777666</h6>
        </div>
    </div>
            <%
                List<Payment> addBillList = (List<Payment>) request.getAttribute("PaymentController");
                double subtotal = 0.0;
                double fulAmount = 0.0;
                double Balance = 0.0;

                if (addBillList != null && !addBillList.isEmpty()) {
                    Payment firstPayment = addBillList.get(0);
                    subtotal = firstPayment.getSubtotal();
                    fulAmount = firstPayment.getAmount();
                    Balance = firstPayment.getBalance();
            %>

            <!-- Bill Info -->
            <div class="row">
                <ul class="list-unstyled">
                    <li class="text-muted mt-1"><span class="text-black">Bill No:</span> #<%= firstPayment.getId() %></li>
                    <li class="text-black mt-1">Date: <%= firstPayment.getDate() %></li>
                </ul>
            </div>

            <!-- Column Headers -->
            <div class="row">
                <div class="col-xl-4"><h5>Item</h5></div>
                <div class="col-xl-3"><h5 class="float-end">Quantity</h5></div>
                <div class="col-xl-3"><h5 class="float-end">Price</h5></div>
                <div class="col-xl-2"><h5 class="float-end">SubTotal</h5></div>
                <hr>
            </div>

            <!-- Item Rows -->
            <%
                for (Payment payment : addBillList) {
                    int qty = payment.getQuntity();
                    double price = payment.getPrice();
                    double amount = qty * price;
            %>
                <div class="row">
                    <div class="col-xl-4"><p><%= payment.getItem() %></p></div>
                    <div class="col-xl-3"><p class="float-end"><%= qty %></p></div>
                    <div class="col-xl-3"><p class="float-end"><%= price %>0</p></div>
                    <div class="col-xl-2"><p class="float-end"><%= amount %>0</p></div>
                    <hr>
                </div>
            <%
                }
            %>


            <div class="row text-black">
                <hr style="border: 2px solid black;">
                <div class="col-xl-10"><p class="float-end fw-bold">Sub Total:</p></div>
                <div class="col-xl-2"><p class="float-end fw-bold"><%= subtotal %>0</p></div>
            </div>

            <div class="row text-black">
                <div class="col-xl-10"><p class="float-end fw-bold">Amount:</p></div>
                <div class="col-xl-2"><p class="float-end fw-bold"><%= fulAmount %>0</p></div>
            </div>

            <div class="row text-black">
                <div class="col-xl-10"><p class="float-end fw-bold">Balance:</p></div>
                <div class="col-xl-2"><p class="float-end fw-bold"><%= Balance %>0</p></div>
                <hr style="border: 2px solid black;">
            </div>

            <!-- Footer -->
            <div class="text-center" style="margin-top:10px;">
                <a><u class="text-info">Thank You</u></a>
                <p>Come Again</p>
            </div>



  </div>
</div>


<div class="top booking">
     <div class="container-fluid py-6">

            <div class="container">
                 <div class="row">

                     <div class="col-md-7 form-group mt-3 mt-md-0"></div>
                     <div class="col-md-2 form-group mt-3 mt-md-0"></div>

                        <div class="col-md-3 form-group mt-3 mt-md-0">
                        <a href="PaymentController?action=printnow&printid=<%= firstPayment.getCusid() %>" onclick="printBillOnly()" class="btn btn-secondary"
                                style="width:20rem; font-size:1.5rem; font-weight:bold; border-radius:.5rem; border:2px solid black;">
                           🖨️ Print
                        </a>
                        </div>




                 </div>
            </div>

     </div>
 </div>
             <%
                 }
             %>
<script>
  function printBillOnly() {
    var printContents = document.getElementById("print-section").innerHTML;
    var originalContents = document.body.innerHTML;

    // Replace body with just the print content
    document.body.innerHTML = printContents;

    window.print();

    // Restore the full page after printing
    document.body.innerHTML = originalContents;
    location.reload();
  }
</script>
<section  class="banner section dark-background">

		      <div class="container" data-aos="fade-up" data-aos-delay="100">

		        <div class="swiper init-swiper">
		          <div class="swiper-wrapper">

		            <div class="swiper-slide">
		              <div class="testimonial-item">

		                <h3>Select the customer via  </h3>
		                <h2>phone number<br>
		                before issuing bills</h2>

		                <p>
		                  <span>(011) 7 776 666</span>
		                </p>
		              </div>
		            </div>

		          </div>
		          <div class="swiper-pagination"></div>
		        </div>

		      </div>

		    </section>


		    <%@ include file="footer.jsp" %>
		                <script src="assets/user/js/main.js"></script>
                        <script src="assets/user/js/about.js"></script>
                        <script src="assets/user/swiper/swiper-bundle.min.js"></script>
                        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
                        <script src="assets/user/js/script.js"></script>
                        <script src="assets/user/js/js/bootstrap.bundle.min.js"></script>

</body>
</html>