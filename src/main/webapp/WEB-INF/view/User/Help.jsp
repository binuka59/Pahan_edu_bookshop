<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@ page import="java.util.List" %>

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


        <section class="bookdetails pb-4">
          <div class="container-lg">
            <div class="row">
              <div class="my-4 position-relative">
                <h2>🛠️ Supporting Intruction</h2>



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
                <h5>
                📌 Please follow the help services provided at the end of each web page..
                </h5>
                <h5>
                📌Select the customer via phone number before issuing bills.
                </h5>
                <h5>
                📌Maintain an uninterrupted internet connection..
                </h5>

                <h5>
                📌If you are unable to enter the data, please check the top of the web page. The error will be displayed there.
                </h5>


           </div>
        </div>

      <section  class="banner section dark-background">

		      <div class="container" data-aos="fade-up" data-aos-delay="100">

		        <div class="swiper init-swiper">
		          <div class="swiper-wrapper">

		            <div class="swiper-slide">
		              <div class="testimonial-item">

		                <h3>Contact </h3>

		                <p>
		                  <span>Mobile:(077) 3 776 666</span>
		                </p>
		                <h3>Email:Binuka@gmail.com</h3>
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