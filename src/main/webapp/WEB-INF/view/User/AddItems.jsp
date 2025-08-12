<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@ page import="java.util.List" %>
        <%@ page import="com.pahana.Model.Item" %>
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
    <%
        List<Item> itemsList = (List<Item>) request.getAttribute("itemsList");
        List<Item> categoryList = (List<Item>) request.getAttribute("categoryList");
    %>

    <section class="bookdetails" class="pb-4">
          <div class="container-lg">
            <div class="row">
              <div class=" my-4">
                <h2>📚 Adding Items</h2>

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

                <form action="ItemController?action=additems" method="post" enctype="multipart/form-data">
                   <div class="container">
                       <div class="row">

                           <div class=" col-md-4 form-group">
                               <label>Item Image :</label>
                               <input type="file" name="Iimage" class="form-control" required>
                           </div>

                            <div class=" col-md-4 form-group">
                               <label>Item Name :</label>
                               <input type="text" name="Iname" class="form-control" id="name" placeholder="Enter Item Here" required>
                               <div class="validate"></div>
                                <div class="valid-feedback">Valid.</div>
                                <div class="invalid-feedback">Please fill out this field.</div>
                            </div>


                            <div class="col-md-4 form-group mt-3 mt-md-0">
                                <label>Item Price:</label>
                                <input type="text" class="form-control" name="Iprice"  placeholder="Enter Price" required>
                                <div class="validate"></div>
                            </div>

                            <div class="col-md-3 form-group mt-3 mt-md-0">
                                <label>Quntity:</label>
                                <input type="text" class="form-control" name="Iquntity"  placeholder="Enter Quntity of items" required>
                                <div class="validate"></div>
                            </div>

                            <div class="col-md-6 form-group mt-3 mt-md-0">
                              <label>Description:</label>
                              <input type="text" class="form-control" name="Idescription" placeholder="Enter Description here"  required>
                              <div class="validate"></div>
                            </div>

                            <div class="col-md-3 form-group mt-3 mt-md-0">
                              <label>Select Category :</label>
                                <select type="text" class="form-control" name="Icategory" placeholder="" >
                                    <option value="">Select Category</option>
                                    <%
                                        List<String> ItemList = (List<String>) request.getAttribute("ItemList");
                                        if (ItemList != null) {
                                            for (String Item : ItemList) {
                                    %>
                                            <option value="<%= Item %>"><%= Item %></option>
                                    <%
                                            }
                                        }
                                    %>

                                    <div class="validate"></div>
                                </select>
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
              <h4>List of Items</h4>
              <thead>
                <tr class="table-info">
                  <th scope="col">Nu</th>
                  <th scope="col">Item</th>
                  <th scope="col">Description</th>
                  <th scope="col">Price</th>
                  <th scope="col">Quntity</th>
                  <th scope="col">Category</th>
                  <th scope="col">Action</th>
                </tr>
              </thead>
                    <%
                      if (itemsList != null) {
                        for (Item item : itemsList) {
                    %>
            <tbody>
              <tr>
                <td><%= item.getId() %></td>
                <td><%= item.getName() %></td>
                <td><%= item.getDescription() %></td>
                <td><%= item.getPrice() %></td>
                <td><%= item.getQuntity() %></td>
                <td><%= item.getCategory() %></td>
                <td>
                  <!-- Edit Button linked to unique modal -->
                  <a href="#" class="btn btn-secondary" data-toggle="modal" data-target="#editModal_<%= item.getId()%>">Edit</a>

                  <!-- Unique Modal -->
                  <div class="modal fade" id="editModal_<%= item.getId() %>">
                    <div class="modal-dialog modal-xl">
                      <div class="modal-content">

                        <div class="modal-header">
                          <h4 class="modal-title"><%= item.getName() %> Item  Details</h4>
                        </div>

                        <form action="ItemController?action=updateitems&itemid=<%= item.getId() %>" method="post" enctype="multipart/form-data">
                          <div class="container">
                            <div class="row">
                              <div class="menu-item d-flex align-items-center">
                                <img class="flex-shrink-0 img-fluid rounded-circle" src="<%= item.getImage() %>" alt="Current Image" style="width:100px;height:100px;">
                                <div class="w-100 d-flex flex-column text-start ps-4">
                                  <div class="d-flex justify-content-between border-bottom border-primary pb-2 mb-2">
                                    <div class="col-md-6 form-group">
                                      <label>Update Image:</label>
                                      <input type="file" name="Iimage" class="form-control">
                                    </div>
                                  </div>
                                </div>
                              </div>

                              <div class="col-md-6 form-group mt-3">
                                <label>Item:</label>
                                <input type="text" class="form-control" name="item" value="<%= item.getName() %>">
                              </div>


                              <div class="col-md-6 form-group mt-3">
                                <label>Item price:</label>
                                <input type="text" class="form-control" name="price" value="<%= item.getPrice() %>">
                              </div>

                              <div class="col-md-6 form-group mt-3">
                                <label>Avilable Quntity:</label>
                                <input type="text" class="form-control" name="quntity" value="<%= item.getQuntity() %>">
                              </div>

                              <div class="col-md-6 form-group mt-3">
                                <label>Category type:</label>
                                <input type="text" class="form-control" name="category" value="<%= item.getCategory() %>">
                              </div>

                              <div class="col-md-12 form-group mt-3">
                                <label>Description:</label>
                                <input type="text" class="form-control" name="description" value="<%= item.getDescription() %>">
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
                  <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteitemModal_<%= item.getId() %>" >Delete</button>
                    <div class="modal fade" id="deleteitemModal_<%= item.getId() %>">
                    <div class="modal-dialog modal-xl">
                      <div class="modal-content">

                        <div class="modal-header">
                          <h4 class="modal-title">Are You Want to Delete this Items Details?</h4>
                        </div>


                          <div class="container">
                            <div class="row">
                              <div class="menu-item d-flex align-items-center">

                                <div class="w-100 d-flex flex-column text-start ps-4">
                                  <div class="d-flex justify-content-between border-bottom border-primary pb-2 mb-2">
                                    <div class="col-md-6 form-group">
                                      <div class="col-md-6 form-group mt-3">
                                        <label><%= item.getName() %></label>

                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>


                          <div class="modal-footer">
                            <a href="ItemController?action=deleteitem&deleteitemid=<%= item.getId() %>" type="submit" class="btn btn-danger">Yes</a>
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
              <td colspan="6" style="text-align: center; font-weight: bold; font-size: 18px;">No Any Items Details display.</td>
            </tr>
            <%
                }
            %>
            </tbody>

            </table>
         </div>
       </div>

    <section class="bookdetails" class="pb-4" style="padding-top: 70px;">
          <div class="container-lg">
            <div class="row">

                <div class="position-relative">
                  <h2 class="text-center">📦 Book Category</h2>
                  <button class="btn btn-info position-absolute top-0 end-0 w-25 py-1 " data-toggle="modal" data-target="#categoryModal">Add Items Category:</button>
                   <div class="modal fade" id="categoryModal">
                    <div class="modal-dialog modal-xl">
                      <div class="modal-content">

                        <div class="modal-header">
                          <h4 class="modal-title"> Adding Category form</h4>
                        </div>

                        <form action="ItemController?action=addcategory" method="post" enctype="multipart/form-data">
                          <div class="container">
                            <div class="row">
                              <div class="menu-item d-flex align-items-center">
                                <img class="flex-shrink-0 img-fluid rounded-circle" src="" alt="Current Image" style="width:100px;height:100px;">
                                <div class="w-100 d-flex flex-column text-start ps-4">
                                  <div class="d-flex justify-content-between border-bottom border-primary pb-2 mb-2">
                                    <div class="col-md-6 form-group">
                                      <label>Update Image:</label>
                                      <input type="file" name="cimage" class="form-control">
                                    </div>
                                  </div>
                                </div>
                              </div>

                              <div class="col-md-6 form-group mt-3">
                                <label>Type of category</label>
                                <input type="text" class="form-control" name="booktype" value="">
                              </div>

                            </div>
                          </div>

                          <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Add </button>
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                          </div>
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
            </div>
          </div>
    </section>

         <div class="container box" style="padding-top: 50px;">
            <table class="table" style="text-align:center;">
              <h4>List of Category</h4>
              <thead>
                <tr class="table-secondary">
                  <th scope="col">List Number</th>
                  <th scope="col">Type of a Category</th>
                  <th scope="col">Action</th>
                </tr>
              </thead>
                  <%
                    if (categoryList != null) {
                      for (Item category : categoryList) {
                  %>
            <tbody>
              <tr>
                <td><%= category.getId() %></td>
                <td><%= category.getCategory() %></td>
                <td>
                  <!-- Edit Button linked to unique modal -->
                  <a href="#" class="btn btn-secondary" data-toggle="modal" data-target="#typeModal_<%= category.getId() %>">Edit</a>

                  <!-- Unique Modal -->
                  <div class="modal fade" id="typeModal_<%= category.getId() %>">
                    <div class="modal-dialog modal-xl">
                      <div class="modal-content">

                        <div class="modal-header">
                          <h4 class="modal-title"><%= category.getId() %>   Details</h4>
                        </div>

                        <form action="ItemController?action=updatecategory&categoryid=<%= category.getId() %>" method="post" enctype="multipart/form-data">
                          <div class="container">
                            <div class="row">
                              <div class="menu-item d-flex align-items-center">
                                <img class="flex-shrink-0 img-fluid rounded-circle" src="<%= category.getImage() %>" alt="Current Image" style="width:100px;height:100px;">
                                <div class="w-100 d-flex flex-column text-start ps-4">
                                  <div class="d-flex justify-content-between border-bottom border-primary pb-2 mb-2">
                                    <div class="col-md-6 form-group">
                                      <label>Update Image:</label>
                                      <input type="file" name="Caimage" class="form-control">
                                    </div>
                                  </div>
                                </div>
                              </div>

                              <div class="col-md-6 form-group mt-3">
                                <label>Type of Category</label>
                                <input type="text" class="form-control" name="item" value="<%= category.getCategory() %>">
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
                  <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deletecataModal_<%= category.getId() %>" >Delete</button>
                    <div class="modal fade" id="deletecataModal_<%= category.getId() %>">
                    <div class="modal-dialog modal-xl">
                      <div class="modal-content">

                        <div class="modal-header">
                          <h4 class="modal-title">Are You Want to Delete this Items Details?</h4>
                        </div>


                          <div class="container">
                            <div class="row">
                              <div class="menu-item d-flex align-items-center">

                                <div class="w-100 d-flex flex-column text-start ps-4">
                                  <div class="d-flex justify-content-between border-bottom border-primary pb-2 mb-2">
                                    <div class="col-md-6 form-group">
                                      <div class="col-md-6 form-group mt-3">
                                        <label><%= category.getCategory() %></label>

                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>


                          <div class="modal-footer">
                            <a href="ItemController?action=deletecata&deletecataid=<%= category.getId() %>" type="submit" class="btn btn-danger">Yes</a>
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
              <td colspan="6" style="text-align: center; font-weight: bold; font-size: 18px;">No Any Items Details display.</td>
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

		                <h3>If you are unable to enter data,</h3>

		                <p>
		                  <span>please check</span>
		                </p>
		                 <h3> the top of the web page.</h3>
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