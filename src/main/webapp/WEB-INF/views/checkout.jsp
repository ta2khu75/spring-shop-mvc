<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <div class="bg-primary">
    <div class="container py-4">
      <!-- Breadcrumb -->
      <nav class="d-flex">
        <h6 class="mb-0">
          <a href="" class="text-white-50">Home</a>
          <span class="text-white-50 mx-2"> > </span>
          <a href="" class="text-white-50">2. Shopping cart</a>
          <span class="text-white-50 mx-2"> > </span>
          <a href="" class="text-white"><u>3. Order info</u></a>
          <span class="text-white-50 mx-2"> > </span>
          <a href="" class="text-white-50">4. Payment</a>
        </h6>
      </nav>
      <!-- Breadcrumb -->
    </div>
  </div>
  <c:set var="total" value="${total}" />
  <fmt:formatNumber value="${total}" type="currency" currencySymbol="₫" groupingUsed="true" var="formattedAmount" />
  <section class="bg-light py-5">
    <div class="container">
      <div class="row">
        <div class="col-xl-8 col-lg-8 mb-4">
          <!-- Checkout -->
          <div class="card shadow-0 border">
            <form:form modelAttribute="order" method="post" action="/order" class="p-4">
              <h5 class="card-title mb-3">Guest checkout</h5>
              <div class="row">
                <div class="col-6 mb-3">
                  <p class="mb-0">Full Name</p>
                  <form:input type="text" path="fullName" id="typeText" placeholder="Type here" class="form-control" />
                  <form:errors class="text-danger" path="fullName" />
                  <div class="form-outline">
                  </div>
                </div>
                <div class="col-6 mb-3">
                  <p class="mb-0">Phone</p>
                  <form:input type="tel" path="phoneNumber" id="typePhone" placeholder="+84" class="form-control" />
                  <form:errors class="text-danger" path="phoneNumber" />
                </div>
              </div>
              <hr class="my-4" />

              <h5 class="card-title mb-3">Shipping info</h5>
              <div class="row">
                <div class="col-sm-12 mb-3">
                  <p class="mb-0">Address</p>
                  <form:textarea path="address" class="form-control" id="textAreaExample" rows="4" /><br>
                  <form:errors class="text-danger" path="address" />
                </div>
              </div>
              <div class="d-flex flex-row pb-3">
                <div class="d-flex align-items-center pe-2">,
                  <form:radiobutton class="form-check-input" path="paymentMethod" id="radioNoLabel1" value="PREPAID"
                    aria-label="..." />
                </div>
                <div class="rounded border d-flex w-100 p-3 align-items-center">
                  <p class="mb-0">
                  <div class="d-flex align-items-center">

                    <img width="50px"
                      src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQNnOe38ULSCaWpUUy1LffxTYa7wRa3oi7W99INM198rQ&s"
                      alt="">
                    <label for="radioNoLabel1" class="ms-2">VNPAY</label>
                  </div>
                  </p>
                </div>
              </div>

              <div class="d-flex flex-row">
                <div class="d-flex align-items-center pe-2">
                  <form:radiobutton class="form-check-input" path="paymentMethod" id="radioNoLabel2" value="COD"
                    aria-label="..." />
                </div>
                <div class="rounded border d-flex w-100 p-3 align-items-center">
                  <p class="mb-0">
                  <div class="d-flex align-items-center">
                    <img width="50px" src="https://cdn-icons-png.flaticon.com/512/1554/1554401.png" alt="">
                    <label class="ms-2" for="radioNoLabel2">Payment on delivery</label>
                  </div>
                  </p>
                </div>
              </div>
              <form:errors  path="paymentMethod" class="text-danger" />
              <div class="float-end mt-4">
                <a class="btn btn-light border" href="/checkout">Cancel</a>
                <button class="btn btn-success shadow-0 border" type="submit">Continue</button>
              </div>
            </form:form>
          </div>
          <!-- Checkout -->
        </div>
        <div class="col-xl-4 col-lg-4 d-flex justify-content-center justify-content-lg-end">
          <div class="ms-lg-4 mt-4 mt-lg-0" style="max-width: 320px;">
            <h6 class="mb-3">Summary</h6>
            <div class="d-flex justify-content-between">
              <p class="mb-2">Total price:</p>
              <p class="mb-2 fw-bold">${formattedAmount}</p>
            </div>
            <hr />
            <h6 class="text-dark my-4">Items in cart</h6>
            <c:if test="${carts!=null}">
              <c:forEach var="cart" items="${carts}">
                <c:set var="totalProduct" value="${cart.key.price*cart.value}" />
                <fmt:formatNumber value="${totalProduct}" type="currency" currencySymbol="₫" groupingUsed="true"
                  var="formattedTotalProduct" />
                <div class="d-flex align-items-center mb-4">
                  <div class="me-3 position-relative">
                    <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill badge-secondary">
                      ${cart.value}
                    </span>
                    <img src="${cart.key.imageUrl}" style="height: 96px; width: 96x;" class="img-sm rounded border" />
                  </div>
                  <div class="">
                    <a href="/product-details/${cart.key.id}" class="nav-link">
                      ${cart.key.name}
                    </a>
                    <div class="price text-muted">Total: ${formattedTotalProduct}</div>
                  </div>
                </div>
              </c:forEach>
            </c:if>
          </div>
        </div>
      </div>
    </div>
  </section>