
    <header id="headers" class="headers fixed-top d-flex align-items-center">


        <nav class="header-nav ms-auto">
          <ul class="d-flex align-items-center">

<li class="nav-item dropdown">

  <a class="nav-link nav-icon" href="#" data-bs-toggle="dropdown">
    <i class="bi bi-bell"></i>
    <%
        List<String> NotyList = (List<String>) request.getAttribute("NotyList");
        int notyCount = (NotyList != null) ? NotyList.size() : 0;
    %>
    <span class="badge badge-number bg-danger"><%= notyCount %></span>
  </a>

  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow notifications">
    <li class="dropdown-header">
      You have <%= notyCount %> new notification<%= notyCount != 1 ? "s" : "" %>
      
    </li>

    <li><hr class="dropdown-divider"></li>

    <%
      if (notyCount > 0) {
        for (String item : NotyList) {
    %>
    <li class="notification-item">
      <i class="bi bi-x-circle text-danger"></i>
      <div>
        <h4><%= item %></h4>
        <p>This book is out of stock</p>
        <p>Just now</p>
      </div>
    </li>
    <li><hr class="dropdown-divider"></li>
    <%
        }
      } else {
    %>
    <li class="notification-item">
      <i class="bi bi-check-circle text-success"></i>
      <div>
        <h4>No Alerts</h4>
        <p>All stocks are sufficient</p>
        <p>-</p>
      </div>
    </li>
    <%
      }
    %>


  </ul><!-- End Notification Dropdown Items -->

</li>


                <%
                    String userName = (String) session.getAttribute("name");
                    Integer userId = (Integer) session.getAttribute("userId");


                %>




            <li class="nav-item dropdown pe-3">


              <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
                    <%

                        if (userName == null) {
                            response.sendRedirect("Login?action=logout");
                            return;
                        }
                    %>

                <span><%= userName %></span>

              </a>

              <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                <li class="dropdown-header">
                  <h6><%= userName %></h6>
                </li>

                <li>
                  <a class="dropdown-item d-flex align-items-center"  href="Login?action=logout">
                    <i class="bi bi-box-arrow-right"></i>
                    <span>Log_Out</span>
                  </a>
                </li>

              </ul>


            </li>

          </ul>
        </nav>

      </header>


          <header id="header" class="d-flex align-items-center">
             <div class="container d-flex align-items-center justify-content-between">

               <a class="navbar-brand" href="index.html">
                 <img src="assets/images/book.png" class="logo img-fluid" alt="logo">
                 <span>
                     <span1>P</span1>ahana <span1> E</span1>du <span1>B</span1>ookshop
                     <small>C o l o m b o</small>
                 </span>
              </a>

               <nav id="navbar" class="navbar">
                 <ul>
                   <li><a class="nav-link scrollto" href="Login?action=home">Home</a></li>
                   <li><a class="nav-link scrollto" href="Login?action=customer">Customer</a></li>
                   <li><a class="nav-link scrollto" href="Login?action=item">Items</a></li>
                   <li><a class="nav-link scrollto" href="Login?action=payment">Payment</a></li>
                   <li><a class="nav-link scrollto" href="Login?action=help">Help</a></li>
                   



                 </ul>
                 <i class="bi bi-list mobile-nav-toggle"></i>
               </nav>

             </div>
          </header>