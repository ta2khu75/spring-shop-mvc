<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <div class="bg-primary mb-4">
        <div class="container py-4">
            <h6 class="mb-0">
                <a href="" class="text-white-50">Home</a>
                <span class="text-white-50 mx-2"> > </span>
                <a href="" class="text-white-50">Library</a>
                <span class="text-white-50 mx-2"> > </span>
                <a href="" class="text-white"><u>Data</u></a>
            </h6>
        </div>
    </div>

    <!-- sidebar + content -->
    <section class="">
        <div class="container">
            <div class="row">
                <!-- sidebar -->
                <div class="col-lg-3">
                    <!-- Toggle button -->
                    <button class="btn btn-outline-secondary mb-3 w-100 d-lg-none" type="button"
                        data-mdb-toggle="collapse" data-mdb-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span>Show filter</span>
                    </button>
                    <!-- Collapsible wrapper -->
                    <div class="collapse card d-lg-block mb-5" id="navbarSupportedContent">
                        <div class="accordion" id="accordionPanelsStayOpenExample">
                            <div class="accordion-item">
                                <h2 class="accordion-header" id="headingOne">
                                    <button class="accordion-button text-dark bg-light" type="button"
                                        data-mdb-toggle="collapse" data-mdb-target="#panelsStayOpen-collapseOne"
                                        aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
                                        Related items
                                    </button>
                                </h2>
                                <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show"
                                    aria-labelledby="headingOne">
                                    <div class="accordion-body">
                                        <ul class="list-unstyled">
                                            <c:forEach var="category" items="${categories}">
                                                <li><a href="/list-product?min=${min}&max=${max}&category=${category}&keyword=${keyword}"
                                                        class="text-dark">${category}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="accordion-item">
                                <h2 class="accordion-header" id="headingThree">
                                    <button class="accordion-button text-dark bg-light" type="button"
                                        data-mdb-toggle="collapse" data-mdb-target="#panelsStayOpen-collapseThree"
                                        aria-expanded="false" aria-controls="panelsStayOpen-collapseThree">
                                        Price
                                    </button>
                                </h2>
                                <div id="panelsStayOpen-collapseThree" class="accordion-collapse collapse show"
                                    aria-labelledby="headingThree">
                                    <form class="accordion-body" action="/list-product" method="get">
                                        <input type="text" name="sortType" hidden value="${sortType}">
                                        <input type="text" name="category" hidden value="${category}">
                                        <input type="text" name="keyword" hidden value="${keyword}">
                                        <div class="row mb-3">
                                            <div class="col-6">
                                                <p class="mb-0">
                                                    Min
                                                </p>
                                                <div class="form-outline">
                                                    <input type="number" value="${min}" name="min" id="typeNumber"
                                                        class="form-control" />
                                                    <c:if test="${min==null}">
                                                        <label class="form-label" for="typeNumber">$0</label>
                                                    </c:if>
                                                </div>
                                            </div>
                                            <div class="col-6">
                                                <p class="mb-0">
                                                    Max
                                                </p>
                                                <div class="form-outline">
                                                    <input type="number" value="${max}" name="max" id="typeNumber"
                                                        class="form-control" />
                                                    <c:if test="${max==null}">
                                                        <label class="form-label" for="typeNumber">$1,0000</label>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                        <button type="submit"
                                            class="btn btn-white w-100 border border-secondary">apply</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- sidebar -->
                <!-- content -->
                <div class="col-lg-9">
                    <header class="d-sm-flex align-items-center border-bottom mb-4 pb-3">
                        <div class="ms-auto">
                            <form id="myForm" class=" d-inline-block" action="/list-product" method="get">
                                <input type="text" name="category" hidden value="${category}">
                                <input type="text" name="keyword" hidden value="${keyword}">
                                <select name="sortType" class="form-select  w-auto border pt-1"
                                    onchange="document.getElementById('myForm').submit()">
                                    <option ${sortType.isBlank() ? 'selected' : '' } value="">Best Sale</option>
                                    <option ${sortType.equals('asc') ? 'selected' : '' } value="asc">Price low to hight
                                    </option>
                                    <option ${sortType.equals('desc') ? 'selected' : '' } value="desc">Price hight to
                                        low</option>
                                </select>
                            </form>
                            <div class="btn-group shadow-0 border">
                                <ul class="nav nav-tabs" id="ex1" role="tablist">
                                    <li class="nav-item" role="presentation">
                                        <a data-mdb-tab-init class="nav-link active" id="ex1-tab-1" href="#ex1-tabs-1"
                                            role="tab" aria-controls="ex1-tabs-1" aria-selected="true"><i
                                                class="fa fa-bars fa-lg"></i></a>
                                    </li>
                                    <li class="nav-item" role="presentation">
                                        <a data-mdb-tab-init class="nav-link" id="ex1-tab-2" href="#ex1-tabs-2"
                                            role="tab" aria-controls="ex1-tabs-2" aria-selected="false"><i
                                                class="fa fa-th fa-lg"></i></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </header>
                    <div class="tab-content" id="ex1-content">
                        <div class="tab-pane fade show active" id="ex1-tabs-1" role="tabpanel"
                            aria-labelledby="ex1-tab-1">
                            <c:if test="${size==0}">
                                <%@ include file="notFoundProduct.jsp" %>
                            </c:if>
                            <c:forEach var="product" items="${products.content}">
                                <c:set var="discount" value="${product.price}" />
                                <fmt:formatNumber value="${discount}" type="currency" currencySymbol="₫"
                                    groupingUsed="true" var="formattedAmount" />
                                <c:set var="price" value="${product.price+50000}" />
                                <fmt:formatNumber value="${price}" type="currency" currencySymbol="₫"
                                    groupingUsed="true" var="formattedPrice" />
                                <div class="row justify-content-center mb-3">
                                    <div class="col-md-12">
                                        <div class="card shadow-0 border rounded-3">
                                            <div class="card-body">
                                                <div class="row g-0">
                                                    <div class="col-xl-3 col-md-4 d-flex justify-content-center">
                                                        <div
                                                            class="bg-image hover-zoom ripple rounded ripple-surface me-md-3 mb-3 mb-md-0">
                                                            <img src="${product.imageUrl}" class="w-100" />
                                                            <a href="/product-details/${product.id}">
                                                                <div class="hover-overlay">
                                                                    <div class="mask"
                                                                        style="background-color: rgba(253, 253, 253, 0.15);">
                                                                    </div>
                                                                </div>
                                                            </a>
                                                        </div>
                                                    </div>
                                                    <div class="col-xl-6 col-md-5 col-sm-7">
                                                        <h5>${product.name}</h5>
                                                        <div class="d-flex flex-row">
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
                                                            <span class="text-muted">${product.numberOfSales}
                                                                orders</span>
                                                        </div>

                                                        <p class="text mb-4 mb-md-0">
                                                            ${product.description}
                                                        </p>
                                                    </div>
                                                    <div class="col-xl-3 col-md-3 col-sm-5">
                                                        <div class="d-flex flex-row align-items-center mb-1">
                                                            <h4 class="mb-1 me-1">${formattedAmount}
                                                            </h4>
                                                            <span class="text-danger"><s>${formattedPrice}</s></span>
                                                        </div>
                                                        <h6 class="text-success">Free shipping</h6>
                                                        <div class="mt-4">
                                                            <button class="btn btn-primary shadow-0" type="button">Buy
                                                                this</button>
                                                            <a href="#!"
                                                                class="btn btn-light border px-2 pt-2 icon-hover"><i
                                                                    class="fas fa-heart fa-lg px-1"></i></a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="tab-pane fade" id="ex1-tabs-2" role="tabpanel" aria-labelledby="ex1-tab-2">
                            <div class="row">
                                <c:forEach var="product" items="${products.content}">
                                    <c:set var="discount" value="${product.price}" />
                                    <fmt:formatNumber value="${discount}" type="currency" currencySymbol="₫"
                                        groupingUsed="true" var="formattedAmount" />
                                    <c:set var="price" value="${product.price+50000}" />
                                    <fmt:formatNumber value="${price}" type="currency" currencySymbol="₫"
                                        groupingUsed="true" var="formattedPrice" />
                                    <div class="col-lg-4 col-md-6 col-sm-6 d-flex">
                                        <div class="card w-100 my-2 shadow-2-strong">
                                            <a href="/product-details/${product.id}">
                                                <img src="${product.imageUrl}" class="card-img-top" />
                                            </a>
                                            <div class="card-body d-flex flex-column">
                                                <div class="d-flex flex-row">
                                                    <h5 class="mb-1 me-1">${formattedAmount}</h5>
                                                    <span class="text-danger"><s>${formattedPrice}</s></span>
                                                </div>
                                                <p class="card-text">${product.name}</p>
                                                <div class="card-footer d-flex align-items-end pt-3 px-0 pb-0 mt-auto">
                                                    <a href="#!" class="btn btn-primary shadow-0 me-1">Add
                                                        to cart</a>
                                                    <a href="#!" class="btn btn-light border icon-hover px-2 pt-2"><i
                                                            class="fas fa-heart fa-lg text-secondary px-1"></i></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <hr />

                    <!-- Pagination -->
                    <nav aria-label="Page navigation example" class="d-flex justify-content-center mt-3">
                        <ul class="pagination">
                            <li class="page-item">
                                <a class="page-link"
                                    href="/list-product?sortType=${sortType}&category=${category}&keyword=${keyword}&min=${min}&max=${max}"
                                    aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <c:forEach var="page" begin="0" end="${products.totalPages-1<0?0:products.totalPages-1}">
                                <li class="page-item"><a class="page-link ${products.number==page?'active':''}"
                                        href="/list-product?sortType=${sortType}&category=${category}&keyword=${keyword}&page=${page}&min=${min}&max=${max}">
                                        ${page+1} </a>
                                </li>
                            </c:forEach>
                            <li class="page-item">
                                <a class="page-link"
                                    href="/list-product?sortType=${sortType}&category=${category}&keyword=${keyword}&page=${products.totalPages-1}&min=${min}&max=${max}"
                                    aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                    <!-- Pagination -->
                </div>
            </div>
        </div>
    </section>
    <!-- Footer -->