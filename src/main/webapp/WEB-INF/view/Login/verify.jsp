<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>



    <meta name="description" content="" />
    <meta name="keywords" content="" />

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Inter:300,400,500,600,700,900" rel="stylesheet">
    <link rel="stylesheet" href="assets/login/fonts/icomoon/style.css">

    <link rel="stylesheet" href="assets/login/CSS/bootstrap.min.css">

    <link rel="stylesheet" href="assets/login/CSS/login.css">


    <link rel="stylesheet" href="assets/login/CSS/nav.css">

    <link rel="stylesheet" href="assets/login/CSS/form.css">

    <link rel="shortcut icon" href="assets/images/book.jpg">




    <title>Pahana Bookshop</title>
</head>
<body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">
    <div class="Background-images">
        <%@ include file="header.jsp" %>

      <section class="vh-100">

          <div class="container py-5 h-100">

            <div class="row d-flex align-items-center justify-content-center h-100">
<h1>        Verify Code </h1>
              <div class="col-md-8 col-lg-7 col-xl-6">
                <img src="assets/images/pahana.png"class="img-fluid" alt="Background  image">
              </div>

              <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">

              <form  action="ChangeController?action=verify" method="post">
                          <h3 style="color: red;padding-left:4rem;font-size:1.3rem;font-weight:bold;font-family:Georgia, 'Times New Roman', Times, serif;">
                              <% if (request.getAttribute("errorMessage") != null) { %>
                                  <%= request.getAttribute("errorMessage") %>
                              <% } %>
                          </h3>

                <div class="form verify">
                <label class="form-label" for="form1Example13">Insert 6 Digit Code:<span style="color:red">(Check your e-mail Address)</spam></label>
                    <div class="row">
                      <div class="col">
                        <input type="text" class="form-control controls"  name="verify1" maxlength="1"required oninput="moveNext(this, 'verify2')" required>
                      </div>
                      <div class="col">
                        <input type="text" class="form-control controls"  name="verify2" maxlength="1" required oninput="moveNext(this, 'verify3')" onkeydown="movePrev(event, this, 'verify1')"required>
                      </div>
                      <div class="col">
                        <input type="text" class="form-control controls"  name="verify3" maxlength="1" required oninput="moveNext(this, 'verify4')" onkeydown="movePrev(event, this, 'verify2')" required>
                      </div>
                      <div class="col">
                        <input type="text" class="form-control controls"  name="verify4" maxlength="1" required oninput="moveNext(this, 'verify5')" onkeydown="movePrev(event, this, 'verify3')"required>
                      </div>
                      <div class="col">
                        <input type="text" class="form-control controls"  name="verify5" maxlength="1" required oninput="moveNext(this, 'verify6')" onkeydown="movePrev(event, this, 'verify4')"required>
                      </div>
                      <div class="col">
                        <input type="text" class="form-control controls"  name="verify6" maxlength="1" onkeydown="movePrev(event, this, 'verify5')" required>
                      </div>
                    </div>
                    <script>
                    function moveNext(currentInput, nextInputName) {
                        if (currentInput.value.length === 1) {
                            let nextInput = document.getElementsByName(nextInputName)[0];
                            if (nextInput) {
                                nextInput.focus();
                            }
                        }
                    }

                    function movePrev(event, currentInput, prevInputName) {
                        if (event.key === "Backspace" && currentInput.value === "" && prevInputName) {
                            let prevInput = document.getElementsByName(prevInputName)[0];
                            if (prevInput) {
                                prevInput.focus();
                                prevInput.value = ""; // Clear the previous box for smooth input
                            }
                        }
                    }
                    </script>
                </div>

                <div class="divider d-flex align-items-center my-4">
                  <p class="text-center fw-bold mx-3 mb-0 text-muted"></p>
                </div>


                  <div class=""> </div>
                  <!-- Submit button -->
                  <button type="submit" class="sigbtn">Confirm</button>
                  <!-- ------------------------------------------------------link registation page----------------------------------- -->


                    <div class="account">
                      <label>Already Have an Account? </label>
                        <a href="Login?action=lognow">Login</a>
                  </div>
                  <!-- -------------------------------------------------------------------------------------------------- -->

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
      <script src="assets/login/js/bootstrap.min.js"></script>
      <script src="assets/login/js/script1.js"></script>
      <script src="assets/login/js/jquery.min.js"></script>
      <script src="assets/login/js/main.js"></script>
      <script src="assets/login/js/loard.js"></script>
      <!-- ============================================================================================================================ -->

  </div>

</body>
</html>