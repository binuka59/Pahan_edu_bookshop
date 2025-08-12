<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@ page import="java.util.List" %>
        <%@ page import="com.pahana.Model.Customer" %>
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
    <%@ include file="header.jsp"%>
    <section class="bookdetails" class="pb-4">
          <div class="container-lg">
            <div class="row">
              <div class=" my-4">
                <h2>👥 Customer Details</h2>
                <%
                    String error = (String) session.getAttribute("errorMessage");
                    if (error != null) {
                %>
                <h3 style="color: red;justify-item:center;text-align:center;font-size:1.3rem;font-weight:bold;font-family:Georgia, 'Times New Roman', Times, serif;">
                    <%= error %>
                </h3>
                <%
                        session.removeAttribute("errorMessage");
                    }
                %>

              </div>
            </div>

     </section>

        <div class="customer">

            <div class="container-fluid  box">

                <form action="CustomerController?action=addcustomer" method="post" enctype="multipart/form-data">
                   <div class="container">
                       <div class="row">

                           <div class=" col-md-3 form-group">
                               <label>Customer Image :</label>
                               <input type="file" name="image" class="form-control">
                           </div>

                            <div class=" col-md-6 form-group">
                               <label>Name :</label>
                               <input type="text" name="name" class="form-control" id="name" placeholder="Enter customer Name" required>
                               <div class="validate"></div>
                                <div class="valid-feedback">Valid.</div>
                                <div class="invalid-feedback">Please fill out this field.</div>
                            </div>


                            <div class="col-md-3 form-group mt-3 mt-md-0">
                                <label>Mobile:</label>
                                <input type="text" class="form-control" name="mobile"  placeholder="Enter Customer Mobile Number " minlength="10" maxlength="10"   title="Please enter exactly 10 digits" oninput="this.value = this.value.replace(/[^0-9]/g, '')"required>
                                <div class="validate"></div>
                            </div>

                            <div class="col-md-6 form-group mt-3 mt-md-0">
                              <label>E-mail Address:</label>
                              <input type="email" class="form-control" name="email" placeholder="Enter Customer email Address"  required>
                              <div class="validate"></div>
                            </div>

                            <div class="col-md-6 form-group mt-3 mt-md-0">
                                <label>Address:</label>
                                <input type="text" class="form-control" name="address"  placeholder="Enter Customer Address" required>
                                <div class="validate"></div>
                            </div>


                       </div>
                            <div class = "btnclass">
                                <button type="submit" class="bookbtn">Add Now</button>
                            </div>
                   </div>
                </form>
            </div>

          <div class="container-fluid  box">
            <table class="table ">
              <h4>List of Customer</h4>
              <thead>
                <tr class="table-info">
                  <th scope="col">Nu</th>
                  <th scope="col">Name</th>
                  <th scope="col">Email</th>
                  <th scope="col">Mobile</th>
                  <th scope="col">Address</th>
                  <th scope="col">Action</th>
                </tr>
              </thead>
            <%
                List<Customer> addList = (List<Customer>) request.getAttribute("LoginController");
                if (addList != null && !addList.isEmpty()) {
                    for (Customer customer : addList) {
                        int id = customer.getId(); // get ID
            %>
            <tbody>
              <tr>
                <td><%= id %></td>
                <td><%= customer.getName() %></td>
                <td><%= customer.getEmail() %></td>
                <td><%= customer.getMobile() %></td>
                <td><%= customer.getAddress() %></td>
                <td>
                  <!-- Edit Button linked to unique modal -->
                  <a href="#" class="btn btn-secondary" data-toggle="modal" data-target="#editModal_<%= id %>">Edit</a>

                  <!-- Unique Modal -->
                  <div class="modal fade" id="editModal_<%= id %>">
                    <div class="modal-dialog modal-xl">
                      <div class="modal-content">

                        <div class="modal-header">
                          <h4 class="modal-title">Customer Details</h4>
                        </div>

                        <form action="CustomerController?action=updatecustomer&custid=<%= id %>" method="post" enctype="multipart/form-data">
                          <div class="container">
                            <div class="row">
                              <div class="menu-item d-flex align-items-center">
                                <img class="flex-shrink-0 img-fluid rounded-circle" src="<%= customer.getImage() %>" alt="Current Image" style="width:100px;height:100px;">
                                <div class="w-100 d-flex flex-column text-start ps-4">
                                  <div class="d-flex justify-content-between border-bottom border-primary pb-2 mb-2">
                                    <div class="col-md-6 form-group">
                                      <label>Update Image:</label>
                                      <input type="file" name="eimage" class="form-control">
                                    </div>
                                  </div>
                                </div>
                              </div>

                              <div class="col-md-6 form-group mt-3">
                                <label>Name:</label>
                                <input type="text" class="form-control" name="ename" value="<%= customer.getName() %>">
                              </div>

                              <div class="col-md-6 form-group mt-3">
                                <label>Mobile:</label>
                                <input type="text" class="form-control" name="emobile" value="<%= customer.getMobile() %>">
                              </div>

                              <div class="col-md-6 form-group mt-3">
                                <label>Email:</label>
                                <input type="text" class="form-control" name="eemail" value="<%= customer.getEmail() %>">
                              </div>

                              <div class="col-md-6 form-group mt-3">
                                <label>Address:</label>
                                <input type="text" class="form-control" name="eaddress" value="<%= customer.getAddress() %>">
                              </div>
                            </div>
                          </div>

                          <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Update Details</button>
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                          </div>
                        </form>
                      </div>
                    </div>
                  </div>

                  <!-- Delete button (optional link or form) -->
                  <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal_<%= id %>" >Delete</button>
                    <div class="modal fade" id="deleteModal_<%= id %>">
                    <div class="modal-dialog modal-xl">
                      <div class="modal-content">

                        <div class="modal-header">
                          <h4 class="modal-title">Are You Want to Delete this Customer Details?</h4>
                        </div>


                          <div class="container">
                            <div class="row">
                              <div class="menu-item d-flex align-items-center">

                                <div class="w-100 d-flex flex-column text-start ps-4">
                                  <div class="d-flex justify-content-between border-bottom border-primary pb-2 mb-2">
                                    <div class="col-md-6 form-group">
                                      <div class="col-md-6 form-group mt-3">
                                        <label><%= customer.getName() %></label>

                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>


                          <div class="modal-footer">
                            <a href="CustomerController?action=deletecustomer&deleteid=<%= id %>" type="submit" class="btn btn-danger">Yes</a>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                          </div>

                      </div>
                    </div>
                  </div>
                </td>
              </tr>
            <%
                    }
                } else {
            %>
            <tr>
              <td colspan="6" style="text-align: center; font-weight: bold; font-size: 18px;">No Any Customer Details display.</td>
            </tr>
            <%
                }
            %>
            </tbody>

            </table>
         </div>
       </div>




      <section  class="banner section dark-background">

		      <div class="container" data-aos="fade-up" data-aos-delay="100">

		        <div class="swiper init-swiper">
		          <div class="swiper-wrapper">

		            <div class="swiper-slide">
		              <div class="testimonial-item">

		                <h3>When entering customer details,</h3>

		                <p>
		                  <span>check if they have been</span>
		                </p>
		                <h3>entered previously.</h3>
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