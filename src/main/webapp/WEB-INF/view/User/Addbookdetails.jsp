<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@ page import="java.util.List" %>
        <%@ page import="com.pahana.Model.Book" %>
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
            <h2>📝 Booking Details</h2>

          </div>
        </div>
      </div>
 </section>

<div class="card container">
  <div class="card-body mx-4">
    <div class="container">
          <div class="row ">

            <div class="col-xl-4">
              <h5>Item</h5>
            </div>
            <div class="col-xl-3">
              <h5 class="float-end">Quntity</h5>
              </p>
            </div>
            <div class="col-xl-3">
              <h5 class="float-end">Price</h5>
              </p>
            </div>
            <div class="col-xl-2">
              <h5 class="float-end">SubTotal</h5>
              </p>
            </div>
            <hr>
          </div>

            <%
                List<Book> addList = (List<Book>) request.getAttribute("BookController");
                double amount = 0.0;

                if (addList != null && !addList.isEmpty()) {
                    for (Book book : addList) {
                        amount += book.getSubtotal();
            %>
      <div class="row">

        <div class="col-xl-4">
          <p><%= book.getName()%></p>
        </div>
        <div class="col-xl-3">
          <p class="float-end"><%= book.getQuntity()%>
          </p>
        </div>
        <div class="col-xl-3">
          <p class="float-end"><%= book.getPrice()%>0
          </p>
        </div>
        <div class="col-xl-2">
          <p class="float-end"><%= book.getSubtotal()%>0
          </p>
        </div>
        <hr>
      </div>
              <%
                 }
               }
             %>

            <div class="row text-black">
            <hr style="border: 2px solid black;">
              <div class="col-xl-10">
                <p class="float-end fw-bold">Total:
                </p>
              </div>

              <div class="col-xl-2">
                <p class="float-end fw-bold">RS:<%= amount %>0
                </p>
              </div>
            <hr style="border: 2px solid black;">
            </div>

    </div>
  </div>
</div>

<div class="top booking">
     <div class="container-fluid py-6">
       <form action="PaymentController?action=bill" method="post">
            <div class="container">
                 <div class="row">
                     <div class=" col-md-3 form-group mt-3 mt-md-0">
                        <label>Mobile Number:</label>
                           <input type="text" name="mobile" id="mobile" class="form-control" placeholder="customer mobile number"
                            minlength="10" maxlength="10" title="Please enter exactly 10 digits"
                            oninput="this.value = this.value.replace(/[^0-9]/g, '')"
                            onkeyup="getCustomerName()" required>
                            <div class="invalid-feedback">Please fill out this field.</div>
                     </div>

                     <div class="col-md-4 form-group mt-3 mt-md-0"></div>

                     <div class="col-md-5 form-group mt-3 mt-md-0">
                         <label>Name:</label>
                            <input type="text" name="name" id="name" class="form-control" required readonly>
                           <div class="invalid-feedback">Please fill out this field.</div>
                     </div>

                     <div class="col-md-7 form-group mt-3 mt-md-0"></div>

                     <div class="col-md-2 form-group mt-3 mt-md-0">
                         <label>Sub Total:</label></div>

                     <div class="col-md-3 form-group mt-3 mt-md-0">
                            <input type="text" name="subtotal" value="<%= amount %>0" class="form-control" required readonly>
                           <div class="invalid-feedback">Please fill out this field.</div>
                     </div>

                     <div class="col-md-7 form-group mt-3 mt-md-0"></div>

                     <div class="col-md-2 form-group mt-3 mt-md-0">
                         <label>Amount:</label></div>

                     <div class="col-md-3 form-group mt-3 mt-md-0">
                            <input type="text" name="amount" id="amount" class="form-control" oninput="calculateBalance()" required>
                           <div class="invalid-feedback">Please fill out this field.</div>
                     </div>

                     <div class="col-md-7 form-group mt-3 mt-md-0"></div>

                     <div class="col-md-2 form-group mt-3 mt-md-0">
                         <label>Balance:</label></div>

                     <div class="col-md-3 form-group mt-3 mt-md-0">
                            <input type="text" name="balance" id="balance" class="form-control" readonly required>
                           <div class="invalid-feedback">Please fill out this field.</div>
                     </div>

                     <div class="col-md-7 form-group mt-3 mt-md-0"></div>
                     <div class="col-md-2 form-group mt-3 mt-md-0"></div>

                     <div class="col-md-3 form-group mt-3 mt-md-0">
                            <button class="btn btn-info" type="submit" style="width:20rem;font-size:1.5rem;font-weight:bold;border-radius:.5rem;border:2px solid black;">✔️ Done</button>

                     </div>

                        <script>
                          function getCustomerName() {
                            var mobile = document.getElementById("mobile").value;

                            if (mobile.length === 10 && /^\d{10}$/.test(mobile)) { // ensure only digits
                              var xhr = new XMLHttpRequest();
                              xhr.open("GET", "CustomerController?action=getNameByMobile&mobile=" + encodeURIComponent(mobile), true);

                              xhr.onreadystatechange = function () {
                                if (xhr.readyState === 4 && xhr.status === 200) {
                                  document.getElementById("name").value = xhr.responseText.trim();
                                }
                              };

                              xhr.send();
                            } else {
                              document.getElementById("name").value = "";
                            }
                          }
                        </script>
                        <script>
                          let totalAmount = <%= amount %>;

                          function calculateBalance() {
                            let amountInput = document.getElementById("amount");
                            let balanceInput = document.getElementById("balance");

                            let amount = parseFloat(amountInput.value);
                            if (!isNaN(amount)) {
                              let balance = amount - totalAmount;
                              balanceInput.value = balance.toFixed(2);
                            } else {
                              balanceInput.value = "";
                            }
                          }
                        </script>

                 </div>
            </div>
       </form>
     </div>
 </div>

        <section  class="banner section dark-background">

		      <div class="container" data-aos="fade-up" data-aos-delay="100">

		        <div class="swiper init-swiper">
		          <div class="swiper-wrapper">

		            <div class="swiper-slide">
		              <div class="testimonial-item">

		                <h3>Select the customer via  </h3>
		                <h2>phone number</h2>
		                <p>
		                  <span>before issuing bills</span>
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