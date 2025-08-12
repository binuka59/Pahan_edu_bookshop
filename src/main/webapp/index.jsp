<!DOCTYPE html>
<html lang="en">
<head>

    <meta name="description" content="" />
    <meta name="keywords" content="" />

    
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Inter:300,400,500,600,700,900" rel="stylesheet">
    <link rel="stylesheet" href="assets/login/fonts/icomoon/style.css">

    <link rel="stylesheet" href="assets/login/CSS/bootstrap.min.css">

    <link rel="stylesheet" href="assets/login/CSS/book.css">

    <link rel="stylesheet" href="assets/login/CSS/nav.css">

    <link rel="stylesheet" href="assets/login/CSS/form.css">

    <link rel="shortcut icon" href="assets/images/book.jpg">




    <title>Pahana BookShop</title>
</head>
<body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">
  <div class="Backgrounds">
 <%@ include file="WEB-INF/view/Login/header.jsp" %>


      <section class="vh-100">

          <div class="container py-5 h-100">

            <div class="row d-flex align-items-center justify-content-center h-100">

<h1>         L O G I N </h1>

              <div class="col-md-8 col-lg-7 col-xl-6">
                <img src="assets/images/pahana.png"class="img-fluid" alt="Background  image">
              </div>

              <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
                          <h3 style="color: red;padding-left:8rem;font-size:1.3rem;font-weight:bold;font-family:Georgia, 'Times New Roman', Times, serif;">
                              <% if (request.getAttribute("errorMessage") != null) { %>
                                  <%= request.getAttribute("errorMessage") %>
                              <% } %>
                        
                          </h3>

                <form action="Login?action=correct" method="post">
                  <!-- Email input -->
                  <div class="form">
                  <label class="form-label" for="form1Example13">Email address</label>
                  <input type="email" id="email" name="email"class="form-control form-control-lg" placeholder="Enter Email address Here"  required>



                  </div>

                  <!-- Password input -->
                  <div class="form">
                  <label class="form-label" for="form1Example23">Password</label>
                  <input type="password" id="password-field" name="password" class="form-control form-control-lg" placeholder="Enter password Here" required>
                   <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                  </div>
                  <script>
                  	  document.querySelector(".toggle-password").addEventListener("click", function () {
                      let passwordField = document.getElementById("password-field");
                      passwordField.type = passwordField.type === "password" ? "text" : "password";
                      });
                  </script>

                  <div class="d-flex">
                    <!-- Checkbox -->
                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" value="" id="form1Example3" checked />
                      <label class="form-check-label" for="form1Example3"> Remember me </label>
                    </div>

                    <a href="ChangeController?action=shows" class="forge">Forggot password?</a>
                  </div>
                  <div class=""> </div>
                  <!-- Submit button -->
                  <button type="submit" class="sigbtn"> Sign Up</button>

                </form>
              </div>
              <div class="container">
                <div class="copyright">
                    &copy; Copyright <span id="year"></span> <strong><span>Pahana Bookshop</span></strong>

                </div>
                <div class="credits">
                  Designed by<span> Binuka Lakshan </span>All Rights Reserved
                </div>
              </div>
            </div>
          </div>

        </section>
        <!-- ----------------------------------------------------------------------------------------------------------------------------- -->

    </div>
    <div id="preloader"></div>

    <!-- ========================================================js============================================================== -->
     <script>
         document.getElementById("year").textContent = new Date().getFullYear();
     </script>
   
      <script src="assets/login/js/loard.js"></script>
      <script src="assets/login/js/script1.js"></script>
      <script src="assets/login/js/jquery.min.js"></script>
      <script src="assets/login/js/loard.js"></script>
      <!-- ============================================================================================================================ -->

  </div>

</body>
</html>