<section class="py-5">
  <div class="container">
    <div class="row gx-5">
      <aside class="col-lg-6">
        <div class="border rounded-4 mb-3 d-flex justify-content-center">
          <a data-fslightbox="mygalley" class="rounded-4" target="_blank" data-type="image"
            href="https://mdbcdn.b-cdn.net/img/bootstrap-ecommerce/items/detail1/big.webp">
            <img style="max-width: 100%; max-height: 100vh; margin: auto;" class="rounded-4 fit"
              src="${product.imageUrl}" />
          </a>
        </div>
        <div class="d-flex justify-content-center mb-3">
          <c:forEach var="productImage" items="${productImages}">
            <button type="button" class="btn btn-light mx-2" data-mdb-ripple-init data-mdb-modal-init
              data-mdb-target="#image${productImage.id}">
              <img width="60" height="60" class="rounded-2" src="${productImage.imageUrl}" />
            </button>
            <div class="modal fade" id="image${productImage.id}" tabindex="-1" aria-labelledby="exampleModalLabel"
              aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Modal title${productImage.id}</h5>
                    <button type="button" class="btn-close" data-mdb-ripple-init data-mdb-dismiss="modal"
                      aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <form method="post" action="/crud/product-image/${product.id}/${productImage.id}"
                      enctype="multipart/form-data">
                      <input type="file" name="image" class="form-control">
                      <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-mdb-ripple-init
                          data-mdb-dismiss="modal">Close</button>
                        <button formmethod="get"
                          formaction="/crud/product-image/${product.id}/delete/${productImage.id}"
                          class="btn btn-danger" data-mdb-ripple-init>Save changes</button>
                        <button type="submit" class="btn btn-warning" data-mdb-ripple-init>Save changes</button>
                      </div>
                    </form>
                  </div>

                </div>
              </div>
            </div>
          </c:forEach>
          <c:if test="${productImages.size() < 5}">
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary" data-mdb-ripple-init data-mdb-modal-init
              data-mdb-target="#exampleModal">
              <i class="fa-solid fa-plus fs-3"></i>
            </button>

            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
              aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Upload Image Product</h5>
                    <button type="button" class="btn-close" data-mdb-ripple-init data-mdb-dismiss="modal"
                      aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <form action="/crud/product-image/${product.id}" enctype="multipart/form-data" method="post">
                      <input accept="image/*" type="file" name="image">
                      <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-mdb-ripple-init
                          data-mdb-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary" data-mdb-ripple-init>Save changes</button>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>

          </c:if>
        </div>
        <!-- thumbs-wrap.// -->
        <!-- gallery-wrap .end// -->
      </aside>
      <main class="col-lg-6">
        <div class="ps-lg-3">
          <h4 class="title text-dark">
            ${product.name}
            <!-- Quality Men's Hoodie for Winter, Men's Fashion <br />
            Casual Hoodie -->
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
            <span class="h5">${product.price}</span>
            <span class="text-muted">/per box</span>
          </div>

          <p>
            ${product.description}
            <!-- Modern look and quality demo item is a streetwear-inspired collection that continues to break away from the conventions of mainstream fashion. Made in Italy, these black and brown clothing low-top shirts for
            men. -->
          </p>

          <div class="row">
            <dt class="col-3">Quantity:</dt>
            <dd class="col-9">${product.quantity}</dd>
          </div>
          <hr />

          <div class="row mb-4">
            <!-- col.// -->
            <div class="col-md-4 col-6 mb-3">
              <label class="mb-2 d-block">Quantity</label>
              <div class="input-group mb-3" style="width: 170px;">
                <button class="btn btn-white border border-secondary px-3" type="button" id="button-addon1"
                  data-mdb-ripple-color="dark">
                  <i class="fas fa-minus"></i>
                </button>
                <input type="text" class="form-control text-center border border-secondary" placeholder="14"
                  aria-label="Example text with button addon" aria-describedby="button-addon1" />
                <button class="btn btn-white border border-secondary px-3" type="button" id="button-addon2"
                  data-mdb-ripple-color="dark">
                  <i class="fas fa-plus"></i>
                </button>
              </div>
            </div>
          </div>
          <a href="#" class="btn btn-warning shadow-0"> Buy now </a>
          <a href="#" class="btn btn-primary shadow-0"> <i class="me-1 fa fa-shopping-basket"></i> Add to cart </a>
          <a href="#" class="btn btn-light border border-secondary py-2 icon-hover px-3"> <i
              class="me-1 fa fa-heart fa-lg"></i> Save </a>
        </div>
      </main>
    </div>
  </div>
</section>