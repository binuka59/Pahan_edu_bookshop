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

    <link rel="stylesheet" href="assets/user/css/re.css">

    <link rel="stylesheet" href="assets/user/css/footer.css">

   <link href="assets/user/css/bootstrap.min.css" rel="stylesheet">
   <link href="assets/user/css/bootstrap-icons.css" rel="stylesheet">


        <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
   </head>
<body>
<%@ include file="header.jsp"%>

 <section class="bookdetails" class="pb-4">
      <div class="container">
        <div class="row">
          <div class=" my-2">
            <h2>Our Recent Book</h2>

          </div>
        </div>



        <div class="row">
                <%
           List<Book> BookList = (List<Book>) request.getAttribute("BookController");

         if (BookList != null && !BookList.isEmpty()) {
             for (Book book : BookList) {
          %>
            <div class="box">
              <form action="BookController?action=addquntity&quntityid=<%= book.getId() %>" method="post">
                <article>
                  <div class="image-holder">
                    <a href="#">
                      <img src="<%= book.getImage() %>" alt="post" class="card-img-top"style="padding-top:1rem;">
                    </a>
                  </div>
                  <div class="card-body">

                    <div class="post-header">
                      <h3 class="post-title">
                        <a href="#" class="text-decoration-none"><%=book.getName() %> </a>
                      </h3>
                      <h2> <%=book.getPrice() %></h2>
                      <h4> <%=book.getDescription() %></h4>
                     <%
                       int bookId = book.getId();
                       int maxQty = book.getQuntity();
                     %>

                     <h5 style="color:red;"> Available <%= maxQty %> items </h5>

                     <div class="value-button" onclick="decreaseValue(<%= bookId %>)" type="button">-</div>

                     <input type="number"  class="inputs" id="number-<%= bookId %>" name="itemcount" value="1" min="1" max="<%= maxQty %>" style="width:5rem;text-align:center;font-weight:bold;" />

                     <div class="value-button" onclick="increaseValue(<%= bookId %>)" type="button">+</div>

                     <button class="btn" type="submit">Add</button>

                     <script>
                       function increaseValue(bookId) {
                         var input = document.getElementById('number-' + bookId);
                         var value = parseInt(input.value, 10);
                         var max = parseInt(input.max, 10);

                         value = isNaN(value) ? 0 : value;
                         if (value < max) {
                           input.value = value + 1;
                         }
                       }

                       function decreaseValue(bookId) {
                         var input = document.getElementById('number-' + bookId);
                         var value = parseInt(input.value, 10);
                         value = isNaN(value) ? 0 : value;
                         if (value > 0) {
                           input.value = value - 1;
                         }
                       }
                     </script>



                      </div>
                  </div>
                </article>
              </form>
              </div>

              <%
                 }
              }
             %>
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
		                <h2>Call Now or Order Your Book<br>
		                Through reading, the world opens up.</h2>

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
		                <script src="assets/user/js/re.js"></script>
		                <script src="assets/user/js/main.js"></script>
                        <script src="assets/user/js/about.js"></script>
                        <script src="assets/user/swiper/swiper-bundle.min.js"></script>
                        <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
                        <script src="assets/user/js/script.js"></script>
                        <script src="assets/user/js/js/bootstrap.bundle.min.js"></script>

</body>
</html>