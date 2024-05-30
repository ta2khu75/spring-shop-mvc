<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <section class="h-100 h-custom" style="background-color: #eee;">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col">
          <div class="card">
            <div class="card-body p-4">
              <div class="row">
                <c:forEach var="orderDetails" items="${order.orderDetails}">
                  <c:set var="price" value="${orderDetails.product.price*orderDetails.quantity}" />
                  <fmt:formatNumber value="${price}" type="currency" currencySymbol="₫" groupingUsed="true"
                    var="formattedPrice" />
                  <div class="card mb-3">
                    <div class="card-body">
                      <div class="d-flex justify-content-between">
                        <div class="d-flex flex-row align-items-center">
                          <div>
                            <img src="${orderDetails.product.imageUrl}" class="img-fluid rounded-3" alt="Shopping item"
                              style="width: 65px;">
                          </div>
                          <div class="ms-3">
                            <h6>${orderDetails.product.name}</h6>
                          </div>
                        </div>
                        <div class="d-flex flex-row align-items-center">
                          <div style="width: 50px;">
                            <h5 class="fw-normal mb-0">${orderDetails.quantity}</h5>
                          </div>
                          <!-- <div style="width: 80px;"> -->
                            <h5 class="mb-0">${formattedPrice}</h5>
                          <!-- </div> -->
                        </div>
                      </div>
                    </div>
                  </div>
                </c:forEach>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>