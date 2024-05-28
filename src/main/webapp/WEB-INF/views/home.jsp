<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <!-- Container wrapper -->
    <div class="container justify-content-center justify-content-md-between">
      <!-- Toggle button -->
      <button class="navbar-toggler" type="button" data-mdb-toggle="collapse"
        data-mdb-target="#navbarLeftAlignExample" aria-controls="navbarLeftAlignExample" aria-expanded="false"
        aria-label="Toggle navigation">
        <i class="fas fa-bars"></i>
      </button>

      <!-- Collapsible wrapper -->
      <div class="collapse navbar-collapse" id="navbarLeftAlignExample">
        <!-- Left links -->
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link" href="#">Categories</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Hot offers</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Gift boxes</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Projects</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Menu item</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Menu name</a>
          </li>
          <!-- Navbar dropdown -->
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
              data-mdb-toggle="dropdown" aria-expanded="false">
              Others
            </a>
            <!-- Dropdown menu -->
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li>
                <a class="dropdown-item" href="#">Action</a>
              </li>
              <li>
                <a class="dropdown-item" href="#">Another action</a>
              </li>
              <li>
                <hr class="dropdown-divider" />
              </li>
              <li>
                <a class="dropdown-item" href="#">Something else here</a>
              </li>
            </ul>
          </li>
        </ul>
        <!-- Left links -->
      </div>
    </div>
    <!-- Container wrapper -->
  </nav>
<section class="mt-3">
    <div class="container">
        <main class="card p-3 shadow-2-strong">
            <div class="row">
                <div class="col-lg-3">
                    <nav class="nav flex-column nav-pills mb-md-2">
                        <c:forEach var="category" items="${categories}">
                            <a href="/list-product/${category}" class="nav-link my-0 py-2 ps-3 bg-white" href="#">
                                <c:out value="${category}" />
                            </a>
                        </c:forEach>
                    </nav>
                </div>
                <div class="col-lg-9">
                    <div id="carouselExampleControls" class="carousel slide" data-mdb-ride="carousel"
                        data-mdb-carousel-init>
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <div class="row">
                                    <img class="col-6"
                                        src="https://salt.tikicdn.com/cache/w750/ts/tikimsp/7a/73/f8/f7ad2fdbd9c7ba86b59090cde10054d2.jpg.webp"
                                        alt="">
                                    <img class="col-6"
                                        src="https://salt.tikicdn.com/cache/w750/ts/tikimsp/7a/28/82/824e9c098611d3230f193cc3bd0e39fb.png.webp"
                                        alt="">
                                </div>
                            </div>
                            <div class="carousel-item">
                                <div class="row">
                                    <img class="col-6"
                                        src="https://salt.tikicdn.com/cache/w750/ts/tikimsp/75/6a/65/eef65b093278758cb69a3c634004154f.jpg.webp"
                                        alt="">
                                    <img class="col-6"
                                        src="https://salt.tikicdn.com/cache/w750/ts/tikimsp/2a/83/96/aeb5145851d94fe6bb9d2bd75d17eab5.jpg.webp"
                                        alt="">
                                </div>
                            </div>
                            <div class="carousel-item">
                                <div class="row">
                                    <img class="col-6"
                                        src="https://salt.tikicdn.com/cache/w750/ts/tikimsp/32/dc/2d/b980869763d4f361509887c5af7de8ca.jpg.webp"
                                        alt="">
                                    <img class="col-6"
                                        src="https://salt.tikicdn.com/cache/w750/ts/tikimsp/6d/6d/02/d7b97223d8822c390dd256cd6210b619.jpg.webp"
                                        alt="">
                                </div>
                            </div>
                        </div>
                        <button class="carousel-control-prev" type="button" data-mdb-target="#carouselExampleControls"
                            data-mdb-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-mdb-target="#carouselExampleControls"
                            data-mdb-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <!-- container end.// -->
</section>
<!-- intro -->

<!-- Products -->
<section>
    <div class="container my-5">
        <header class="mb-4">
            <h3>New products</h3>
        </header>

        <div class="row">
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
<!-- Products -->

<!-- Feature -->
<section class="">
    <div class="container">
        <div class="row gy-4">
            <div class="col-lg-6">
                <div class="card-banner bg-gray h-100"
                    style="
                                                min-height: 200px;
                                                background-size: cover;
                                                background-position: center;
                                                width: 100%;
                                                background-repeat: no-repeat;
                                                top: 50%;
                                                background-image: url('https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/banners/banner-item2.webp');">
                    <div class="p-3 p-lg-5" style="max-width: 70%;">
                        <h3 class="text-dark">Best products & brands in our store at 80% off</h3>
                        <p>That's true but not always</p>
                        <button class="btn btn-warning shadow-0" href="#"> Claim offer </button>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="row mb-3 mb-sm-4 g-3 g-sm-4">
                    <div class="col-6 d-flex">
                        <div class="card w-100 bg-primary" style="min-height: 200px;">
                            <div class="card-body">
                                <h5 class="text-white">Gaming toolset</h5>
                                <p class="text-white-50">Technology for cyber sport</p>
                                <a class="btn btn-outline-light btn-sm" href="#">Learn more</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-6 d-flex">
                        <div class="card w-100 bg-primary" style="min-height: 200px;">
                            <div class="card-body">
                                <h5 class="text-white">Quality sound</h5>
                                <p class="text-white-50">All you need for music</p>
                                <a class="btn btn-outline-light btn-sm" href="#">Learn more</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- row.// -->

                <div class="card bg-success" style="min-height: 200px;">
                    <div class="card-body">
                        <h5 class="text-white">Buy 2 items, With special gift</h5>
                        <p class="text-white-50" style="max-width: 400px;">Buy one, get one free marketing strategy
                            helps
                            your
                            business improves the brand by sharing the profits</p>
                        <a class="btn btn-outline-light btn-sm" href="#">Learn more</a>
                    </div>
                </div>
            </div>
            <!-- col.// -->
        </div>
        <!-- row.// -->
    </div>
    <!-- container end.// -->
</section>
<!-- Feature -->

<!-- Recently viewed -->
<section class="mt-5 mb-4">
    <div class="container text-dark">
        <header class="">
            <h3 class="section-title">Recently viewed</h3>
        </header>

        <div class="row gy-3">
            <div class="col-lg-2 col-md-4 col-4">
                <a href="#" class="img-wrap">
                    <img height="200" width="200" class="img-thumbnail"
                        src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/1.webp" />
                </a>
            </div>
            <!-- col.// -->
            <div class="col-lg-2 col-md-4 col-4">
                <a href="#" class="img-wrap">
                    <img height="200" width="200" class="img-thumbnail"
                        src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/2.webp" />
                </a>
            </div>
            <!-- col.// -->
            <div class="col-lg-2 col-md-4 col-4">
                <a href="#" class="img-wrap">
                    <img height="200" width="200" class="img-thumbnail"
                        src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/3.webp" />
                </a>
            </div>
            <!-- col.// -->
            <div class="col-lg-2 col-md-4 col-4">
                <a href="#" class="img-wrap">
                    <img height="200" width="200" class="img-thumbnail"
                        src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/4.webp" />
                </a>
            </div>
            <!-- col.// -->
            <div class="col-lg-2 col-md-4 col-4">
                <a href="#" class="img-wrap">
                    <img height="200" width="200" class="img-thumbnail"
                        src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/5.webp" />
                </a>
            </div>
            <!-- col.// -->
            <div class="col-lg-2 col-md-4 col-4">
                <a href="#" class="img-wrap">
                    <img height="200" width="200" class="img-thumbnail"
                        src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/6.webp" />
                </a>
            </div>
        </div>
    </div>
</section>
<!-- Recently viewed -->

<section>
    <div class="container">
        <div class="px-4 pt-3 border">
            <div class="row pt-1">
                <div class="col-lg-3 col-md-6 mb-3 d-flex">
                    <div class="d-flex align-items-center">
                        <div class="badge badge-warning p-2 rounded-4 me-3">
                            <i class="fas fa-thumbs-up fa-2x fa-fw"></i>
                        </div>
                        <span class="info">
                            <h6 class="title">Reasonable prices</h6>
                            <p class="mb-0">Have you ever finally just</p>
                        </span>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 mb-3 d-flex">
                    <div class="d-flex align-items-center">
                        <div class="badge badge-warning p-2 rounded-4 me-3">
                            <i class="fas fa-plane fa-2x fa-fw"></i>
                        </div>
                        <span class="info">
                            <h6 class="title">Worldwide shipping</h6>
                            <p class="mb-0">Have you ever finally just</p>
                        </span>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 mb-3 d-flex">
                    <div class="d-flex align-items-center">
                        <div class="badge badge-warning p-2 rounded-4 me-3">
                            <i class="fas fa-star fa-2x fa-fw"></i>
                        </div>
                        <span class="info">
                            <h6 class="title">Best ratings</h6>
                            <p class="mb-0">Have you ever finally just</p>
                        </span>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 mb-3 d-flex">
                    <div class="d-flex align-items-center">
                        <div class="badge badge-warning p-2 rounded-4 me-3">
                            <i class="fas fa-phone-alt fa-2x fa-fw"></i>
                        </div>
                        <span class="info">
                            <h6 class="title">Help center</h6>
                            <p class="mb-0">Have you ever finally just</p>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>