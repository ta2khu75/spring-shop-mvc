<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <c:set var="total" value="${order.total}" />
  <fmt:formatNumber value="${total}" type="currency" currencySymbol="₫" groupingUsed="true" var="formattedAmount" />
  <section class="h-100 gradient-custom">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-lg-10 col-xl-8">
          <div class="card" style="border-radius: 10px;">
            <div class="card-header px-4 py-5">
              <h5 class="text-muted mb-0">Thanks for your Order, <span style="color: #3b71ca;">${order.fullName}</span>!
              </h5>
            </div>
            <div class="card-body p-4">
              <c:forEach var="orderDetail" items="${orderDetails}">
                <c:set var="price" value="${orderDetail.product.price+50000}" />
                <fmt:formatNumber value="${price}" type="currency" currencySymbol="₫" groupingUsed="true"
                  var="formattedPrice" />
                <div class="card shadow-0 border mb-4">
                  <div class="card-body">
                    <div class="row">
                      <div class="col-md-2">
                        <img src="${orderDetail.product.imageUrl}" class="img-fluid" alt="Phone">
                      </div>
                      <div class="col-md-6 text-center d-flex justify-content-center align-items-center">
                        <p class="text-muted mb-0">${orderDetail.product.name}</p>
                      </div>
                      <div class="col-md-2 text-center d-flex justify-content-center align-items-center">
                        <p class="text-muted mb-0 small">Qty: ${orderDetail.quantity}</p>
                      </div>
                      <div class="col-md-2 text-center d-flex justify-content-center align-items-center">
                        <p class="text-muted mb-0 small">${formattedPrice}</p>
                      </div>
                    </div>
                  </div>
                </div>
              </c:forEach>
              <div class="d-flex justify-content-between pt-2">
                <p class="fw-bold mb-0">Order Details</p>
              </div>

              <div class="d-flex justify-content-between pt-2">
                <p class="text-muted mb-0">Invoice Number : ${order.id}</p>
              </div>

              <div class="d-flex justify-content-between">
                <p class="text-muted mb-0">Invoice Date : ${order.createDate}</p>
              </div>
            </div>
            <div class="card-footer border-0 px-4 py-5"
              style="background-color: #3b71ca; border-bottom-left-radius: 10px; border-bottom-right-radius: 10px;">
              <h5 class="d-flex align-items-center justify-content-end text-white text-uppercase mb-0">Total
                paid: <span class="h2 mb-0 ms-2">${formattedAmount}</span></h5>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>