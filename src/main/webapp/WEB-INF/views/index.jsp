<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
      <%@ taglib uri="jakarta.tags.core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
          <meta charset="UTF-8">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <title>Document</title>
          <!-- Font Awesome -->
          <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet" />
          <!-- Google Fonts -->
          <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet" />
          <!-- MDB -->
          <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.2.0/mdb.min.css" rel="stylesheet" />
        </head>

        <body>
          <!--Main Navigation-->
          <header>
            <!-- Jumbotron -->
            <div class="p-3 text-center bg-white border-bottom">
              <div class="container">
                <div class="row gy-3">
                  <!-- Left elements -->
                  <div class="col-lg-2 col-sm-4 col-4">
                    <a href="/" class="float-start">
                      <img src="https://salt.tikicdn.com/ts/upload/0e/07/78/ee828743c9afa9792cf20d75995e134e.png"
                        height="35" />
                    </a>
                  </div>
                  <!-- Left elements -->

                  <!-- Center elements -->
                  <div class="order-lg-last col-lg-5 col-sm-8 col-8">
                    <div class="d-flex float-end">
                      <a href="/profile" class="me-1 border rounded py-1 px-3 nav-link d-flex align-items-center"> <i
                          class="fas fa-user-alt m-1 me-md-2"></i>
                        <p class="d-none d-md-block mb-0">Profile</p>
                      </a>
                      <a href="/login" class="me-1 border rounded py-1 px-3 nav-link d-flex align-items-center">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                          class="bi bi-box-arrow-in-right" viewBox="0 0 16 16">
                          <path fill-rule="evenodd"
                            d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0z" />
                          <path fill-rule="evenodd"
                            d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708z" />
                        </svg>
                        <p class="d-none d-md-block mb-0">Log in</p>
                      </a>
                      <a href="/cart" class="me-1 border rounded py-1 px-3 nav-link d-flex align-items-center"> <i
                          class="fas fa-shopping-cart m-1 me-md-2"></i>
                        <p class="d-none d-md-block mb-0">My cart</p>
                      </a>
                      <a href="/logout" class="me-1 border rounded py-1 px-3 nav-link d-flex align-items-center">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                          class="bi bi-box-arrow-right" viewBox="0 0 16 16">
                          <path fill-rule="evenodd"
                            d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0z" />
                          <path fill-rule="evenodd"
                            d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708z" />
                        </svg>
                        <p class="d-none d-md-block mb-0">Log out</p>
                      </a>
                    </div>
                  </div>
                  <!-- Center elements -->

                  <!-- Right elements -->
                  <div class="col-lg-5 col-md-12 col-12">
                    <form class="input-group float-center" action="/list-product" method="post">
                      <div class="form-outline">
                        <input type="search" id="form1" name="keyword" class="form-control" />
                        <label class="form-label" for="form1">Search</label>
                      </div>
                      <button type="submit" class="btn btn-primary shadow-0">
                        <i class="fas fa-search"></i>
                      </button>
                    </form>
                  </div>
                  <!-- Right elements -->
                </div>
              </div>
            </div>
          </header>
          <c:choose>
            <c:when test="${page.equals('home')}">
              <%@ include file="home.jsp" %>
            </c:when>
            <c:when test="${page.equals('product-details')}">
              <%@ include file="productDetails.jsp" %>
            </c:when>
            <c:when test="${page.equals('list-product')}">
              <%@ include file="listProduct.jsp" %>
            </c:when>
            <c:when test="${page.equals('cart')}">
              <%@ include file="cart.jsp" %>
            </c:when>
            <c:when test="${page.equals('checkout')}">
              <%@ include file="checkout.jsp" %>
            </c:when>
            <c:when test="${page.equals('profile')}">
              <%@ include file="profile.jsp" %>
            </c:when>
            <c:when test="${page.equals('register')}">
              <%@ include file="register.jsp" %>
            </c:when>
            <c:when test="${page.equals('login')}">
              <%@ include file="login.jsp" %>
            </c:when>
            <c:when test="${page.equals('404')}">
              <%@ include file="404.jsp" %>
            </c:when>
            <c:when test="${page.equals('error')}">
              <%@ include file="failed.jsp" %>
            </c:when>
            <c:when test="${page.equals('success')}">
              <%@ include file="success.jsp" %>
            </c:when>
            <c:when test="${page.equals('order-details')}">
              <%@ include file="orderDetails.jsp" %>
            </c:when>
            <c:otherwise>
            </c:otherwise>
          </c:choose>
          <!--  intro  -->

          <!-- Footer -->
          <footer class="text-center text-lg-start text-muted bg-primary mt-3">
            <!-- Section: Links  -->
            <section class="">
              <div class="container text-center text-md-start pt-4 pb-4">
                <!-- Grid row -->
                <div class="row mt-3">
                  <!-- Grid column -->
                  <div class="col-12 col-lg-3 col-sm-12 mb-2">
                    <!-- Content -->
                    <a href="https://mdbootstrap.com/" target="_blank" class="text-white h2">
                      MDB
                    </a>
                    <p class="mt-1 text-white">
                      © 2023 Copyright: MDBootstrap.com
                    </p>
                  </div>
                  <!-- Grid column -->

                  <!-- Grid column -->
                  <div class="col-6 col-sm-4 col-lg-2">
                    <!-- Links -->
                    <h6 class="text-uppercase text-white fw-bold mb-2">
                      Store
                    </h6>
                    <ul class="list-unstyled mb-4">
                      <li><a class="text-white-50" href="#">About us</a></li>
                      <li><a class="text-white-50" href="#">Find store</a></li>
                      <li><a class="text-white-50" href="#">Categories</a></li>
                      <li><a class="text-white-50" href="#">Blogs</a></li>
                    </ul>
                  </div>
                  <!-- Grid column -->

                  <!-- Grid column -->
                  <div class="col-6 col-sm-4 col-lg-2">
                    <!-- Links -->
                    <h6 class="text-uppercase text-white fw-bold mb-2">
                      Information
                    </h6>
                    <ul class="list-unstyled mb-4">
                      <li><a class="text-white-50" href="#">Help center</a></li>
                      <li><a class="text-white-50" href="#">Money refund</a></li>
                      <li><a class="text-white-50" href="#">Shipping info</a></li>
                      <li><a class="text-white-50" href="#">Refunds</a></li>
                    </ul>
                  </div>
                  <!-- Grid column -->

                  <!-- Grid column -->
                  <div class="col-6 col-sm-4 col-lg-2">
                    <!-- Links -->
                    <h6 class="text-uppercase text-white fw-bold mb-2">
                      Support
                    </h6>
                    <ul class="list-unstyled mb-4">
                      <li><a class="text-white-50" href="#">Help center</a></li>
                      <li><a class="text-white-50" href="#">Documents</a></li>
                      <li><a class="text-white-50" href="#">Account restore</a></li>
                      <li><a class="text-white-50" href="#">My orders</a></li>
                    </ul>
                  </div>
                  <!-- Grid column -->

                  <!-- Grid column -->
                  <div class="col-12 col-sm-12 col-lg-3">
                    <!-- Links -->
                    <h6 class="text-uppercase text-white fw-bold mb-2">Newsletter</h6>
                    <p class="text-white">Stay in touch with latest updates about our products and offers</p>
                    <div class="input-group mb-3">
                      <input type="email" class="form-control border" placeholder="Email" aria-label="Email"
                        aria-describedby="button-addon2" />
                      <button class="btn btn-light border shadow-0" type="button" id="button-addon2"
                        data-mdb-ripple-color="dark">
                        Join
                      </button>
                    </div>
                  </div>
                  <!-- Grid column -->
                </div>
                <!-- Grid row -->
              </div>
            </section>
            <!-- Section: Links  -->

            <div class="">
              <div class="container">
                <div class="d-flex justify-content-between py-4 border-top">
                  <!--- payment --->
                  <div>
                    <i class="fab fa-lg fa-cc-visa text-white"></i>
                    <i class="fab fa-lg fa-cc-amex text-white"></i>
                    <i class="fab fa-lg fa-cc-mastercard text-white"></i>
                    <i class="fab fa-lg fa-cc-paypal text-white"></i>
                  </div>
                  <!--- payment --->

                  <!--- language selector --->
                  <div class="dropdown dropup">
                    <a class="dropdown-toggle text-white" href="#" id="Dropdown" role="button"
                      data-mdb-toggle="dropdown" aria-expanded="false"> <i
                        class="flag-united-kingdom flag m-0 me-1"></i>English </a>

                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="Dropdown">
                      <li>
                        <a class="dropdown-item" href="#"><i class="flag-united-kingdom flag"></i>English <i
                            class="fa fa-check text-success ms-2"></i></a>
                      </li>
                      <li>
                        <hr class="dropdown-divider" />
                      </li>
                      <li>
                        <a class="dropdown-item" href="#"><i class="flag-poland flag"></i>Polski</a>
                      </li>
                      <li>
                        <a class="dropdown-item" href="#"><i class="flag-china flag"></i>中文</a>
                      </li>
                      <li>
                        <a class="dropdown-item" href="#"><i class="flag-japan flag"></i>日本語</a>
                      </li>
                      <li>
                        <a class="dropdown-item" href="#"><i class="flag-germany flag"></i>Deutsch</a>
                      </li>
                      <li>
                        <a class="dropdown-item" href="#"><i class="flag-france flag"></i>Français</a>
                      </li>
                      <li>
                        <a class="dropdown-item" href="#"><i class="flag-spain flag"></i>Español</a>
                      </li>
                      <li>
                        <a class="dropdown-item" href="#"><i class="flag-russia flag"></i>Русский</a>
                      </li>
                      <li>
                        <a class="dropdown-item" href="#"><i class="flag-portugal flag"></i>Português</a>
                      </li>
                    </ul>
                  </div>
                  <!--- language selector --->
                </div>
              </div>
            </div>
          </footer>
          <!-- Footer -->
          <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.2.0/mdb.umd.min.js"></script>
        </body>

        </html>