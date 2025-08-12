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
    <link rel="stylesheet" href="assets/user/css/addcus.css">


    <link rel="stylesheet" href="assets/user/css/footer.css">

   <link href="assets/user/css/bootstrap.min.css" rel="stylesheet">
   <link href="assets/user/css/bootstrap-icons.css" rel="stylesheet">



</head>
<body>
<%@ include file="header.jsp"%>
    <section id="hero" class="hero">

      <div class="container">
        <div class="row gy-2 justify-content-center justify-content-lg-between">
          <div class="col-lg-5 order-2 order-lg-1 d-flex flex-column justify-content-center">
            <h1 data-aos="fade-up">Your Companion In The <br>Journey Of Learning...</h1>
            <p data-aos="fade-up" data-aos-delay="100">The light that guides to knowledge.</p>

          </div>
          <div class="col-lg-5 order-1 order-lg-2 hero-img" data-aos="zoom-out">
            <img src="assets/user/img/sp.png" class="img-fluid animated" alt="">
          </div>
        </div>
      </div>

    </section>

 <section class="bookdetails" class="pb-2">
      <div class="container-lg">
        <div class="row">
          <div class=" my-4">
            <h2>💼 Recent Book Caterory</h2>
            
          </div>
        </div>
      
        <div class="row">
                <%
           List<Book> BookList = (List<Book>) request.getAttribute("LoginController");

         if (BookList != null && !BookList.isEmpty()) {
             for (Book book : BookList) {
          %>  
          <div class="box">
            <article>
              <div class="image-holder zoom-effect">
                <a href="#">
                  <img src="<%= book.getImage() %>" alt="post" class="card-img-top" style="padding-top:1rem;width:200px;height:200px;">
                </a>
              </div>
              <div class="card-body">
                
                <div class="post-header">
                  <h3 class="post-title">
                    <a href="#" class="text-decoration-none"><%=book.getType() %> </a>
                  </h3>

                  		<a href="BookController?action=View&Viewid=<%= book.getId() %>" class="btn">View</a>
                  		

                  
                </div>
              </div>
            </article>
          </div>
          
          <%
             }
          } 
         %>
        </div>             
         
      </div>
    </section>

             <section class="service layout_padding-bottom">

                     <div class="service_container">
                       <div class="container ">
                         <div class="row">
                           <div class="col-md-3">
                             <div class="box ">
                               <div class="detail-box">

                                      <i class="bi bi-geo-fill"></i>
                                      <span>Quick Delivery</span>
                                      <p class="separator"> island Wide Delivery </p>
                               </div>
                             </div>
                           </div>
                           <div class="col-md-3">
                             <div class="box">

                               <div class="detail-box">
                                     <div class="services-block">
                                       <i class="bi bi-card-checklist"></i>
                                       <span>Customization</span>
                                       <p class="separator"> All In One place </p>
                                </div>
                               </div>
                             </div>
                           </div>
                           <div class="col-md-3">
                             <div class="box">

                               <div class="detail-box">
                                     <div class="services-block">
                                       <i class="bi bi-calendar-check"></i>
                                       <span> 100% Digital</span>
                                       <p class="separator"> Seamless & Smart Solutions! </p>
                                </div>
                               </div>
                             </div>
                           </div>
                           <div class="col-md-3">
                             <div class="box">

                               <div class="detail-box">
                                     <div class="services-block">
                                       <i class="bi bi-credit-card-fill"></i>
                                       <span>Easy Payment</span>
                                       <p class="separator">  Fast, Secure & Hassle-Free! </p>
                                </div>
                               </div>
                             </div>
                           </div>

                         </div>
                       </div>
                     </div>
                   </section>

			<section  class="banner section dark-background">
		
		      <div class="container" data-aos="fade-up" data-aos-delay="100">
		
		        <div class="swiper init-swiper">
		          <div class="swiper-wrapper">
		
		            <div class="swiper-slide">
		              <div class="testimonial-item">
		
		                <h3>Call 24-Hour Service Available</h3>
		
		                <p>
		                  <span>Contact us for your problem</span>
		                </p>
		                <h3>(077)377 7666</h3>
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