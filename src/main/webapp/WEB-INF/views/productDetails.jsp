<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <div class="bg-primary">
    <div class="container py-4">
      <!-- Breadcrumb -->
      <nav class="d-flex">
        <h6 class="mb-0">
          <a href="" class="text-white-50">Home</a>
          <span class="text-white-50 mx-2"> > </span>
          <a href="" class="text-white-50">Library</a>
          <span class="text-white-50 mx-2"> > </span>
          <a href="" class="text-white"><u>Data</u></a>
        </h6>
      </nav>
      <!-- Breadcrumb -->
    </div>
  </div>
  <c:set var="price" value="${product.price}" />
  <fmt:formatNumber value="${price}" type="currency" currencySymbol="₫" groupingUsed="true" var="formattedPrice" />

  <!-- content -->
  <section class="py-5">
    <div class="container">
      <div class="row gx-5">
        <aside class="col-lg-6">
          <div class="border rounded-4 mb-3 d-flex justify-content-center">
            <a data-fslightbox="mygalley" class="rounded-4" target="_blank" data-type="image"
              href="${product.imageUrl}">
              <img style="max-width: 100%; max-height: 100vh; margin: auto;" class="rounded-4 fit"
                src="${product.imageUrl}" />
            </a>
          </div>
          <div class="d-flex justify-content-center mb-3">
            <c:forEach var="productImage" items="${product.productImages}">
              <a data-fslightbox="mygalley" class="border mx-1 rounded-2" target="_blank" data-type="image"
                href="${productImage.imageUrl}" class="item-thumb">
                <img width="60" height="60" class="rounded-2" src="${productImage.imageUrl}" />
              </a>
            </c:forEach>
          </div>
          <!-- thumbs-wrap.// -->
          <!-- gallery-wrap .end// -->
        </aside>
        <main class="col-lg-6">
          <div class="ps-lg-3">
            <h4 class="title text-dark">
              ${product.name}
            </h4>
            <div class="d-flex flex-row my-3">
              <div class="text-warning mb-1 me-2">
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                <i class="fas fa-star-half-alt"></i>
                <span class="ms-1">
                  4.5
                </span>
              </div>
              <span class="text-muted"><i class="fas fa-shopping-basket fa-sm mx-1"></i>${product.numberOfSales}
                orders</span>
              <span class="text-success ms-2">In stock</span>
            </div>
            <div class="mb-3">
              <span class="h5">${formattedPrice}</span>
              <span class="text-muted">/per box</span>
            </div>

            <p>
              ${product.description}
            </p>

            <div class="row">
              <dt class="col-3">Quantity:</dt>
              <dd class="col-9">${product.quantity}</dd>
            </div>
            <hr />
            <form>
              <div class="row mb-4">
                <!-- col.// -->
                <div class="col-md-4 col-6 mb-3">
                  <label class="mb-2 d-block">Quantity</label>
                  <div class="input-group mb-3" style="width: 170px;">
                    <button class="btn btn-white border border-secondary px-3" type="button" id="button-decrement"
                      data-mdb-ripple-color="dark">
                      <i class="fas fa-minus"></i>
                    </button>
                    <input type="text" name="quantity" class="form-control text-center border border-secondary"
                      value="1" min="0" aria-label="Quantity" aria-describedby="button-decrement button-increment" />
                    <button class="btn btn-white border border-secondary px-3" type="button" id="button-increment"
                      data-mdb-ripple-color="dark">
                      <i class="fas fa-plus"></i>
                    </button>
                  </div>
                </div>
              </div>
              <!-- <button class="btn btn-warning shadow-0">Buy now</button> -->
              <button formaction="/cart/buy/${product.id}" class="btn btn-warning shadow-0"> Buy now </button>
              <button formaction="/cart/add/${product.id}" class="btn btn-primary shadow-0"> <i
                  class="me-1 fa fa-shopping-basket"></i> Add to cart </button>
            </form>
          </div>

      </div>
      </main>
    </div>
    </div>
  </section>
  <!-- content -->

  <section class="bg-light border-top py-4">
    <div class="container">
      <h3>Similar items</h3>
      <div class="row gx-4">
        <c:forEach var="product" items="${products}">
          <c:set var="price" value="${product.price}" />
          <fmt:formatNumber value="${price}" type="currency" currencySymbol="₫" groupingUsed="true"
            var="formattedPrice" />

          <div class="col-lg-3 col-md-6 col-sm-6">
            <div class="card my-2 shadow-0">
              <a href="/product-details/${product.id}" class="img-wrap">
                <div class="mask" style="height: 50px;">
                  <div class="d-flex justify-content-start align-items-start h-100 m-2">
                    <h6><span class="badge bg-success pt-2">Offer</span></h6>
                  </div>
                </div>
                <img src="${product.imageUrl}" class="card-img-top" style="aspect-ratio: 1 / 1">
              </a>
              <div class="card-body p-0 pt-3">
                <h5 class="card-title">${formattedPrice}</h5>
                <p class="card-text mb-0">${product.name}</p>
                <p class="text-muted">
                  Model: X-200
                </p>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
  </section>
  <!-- Footer -->
  <script>
    document.addEventListener('DOMContentLoaded', (event) => {
      const maxQuantity = ${ product.quantity };  // Replace this with your product.quantity
      const decrementButton = document.getElementById('button-decrement');
      const incrementButton = document.getElementById('button-increment');
      const quantityInput = document.querySelector('input[aria-label="Quantity"]');

      decrementButton.addEventListener('click', () => {
        let currentValue = parseInt(quantityInput.value) || 0;
        if (currentValue > parseInt(quantityInput.min)) {
          quantityInput.value = currentValue - 1;
        }
      });

      incrementButton.addEventListener('click', () => {
        let currentValue = parseInt(quantityInput.value) || 0;
        if (currentValue < maxQuantity) {
          quantityInput.value = currentValue + 1;
        }
      });

      quantityInput.addEventListener('input', () => {
        let value = quantityInput.value;
        if (!/^\d*$/.test(value)) {
          quantityInput.value = value.replace(/\D/g, '');
        }
        if (parseInt(quantityInput.value) > maxQuantity) {
          quantityInput.value = maxQuantity;
        }
        if (parseInt(quantityInput.value) < parseInt(quantityInput.min)) {
          quantityInput.value = quantityInput.min;
        }
      });
    });
  </script>