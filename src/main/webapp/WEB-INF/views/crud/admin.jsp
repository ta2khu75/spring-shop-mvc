<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
        <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
                <%@ taglib uri="jakarta.tags.core" prefix="c" %>
                    <!DOCTYPE html>
                    <html lang="en">

                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <title>Document</title>
                        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
                            rel="stylesheet" />
                        <!-- Google Fonts -->
                        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
                            rel="stylesheet" />
                        <!-- MDB -->
                        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.2.0/mdb.min.css"
                            rel="stylesheet" />
                        <style>
                            body {
                                background-color: #fbfbfb;
                            }

                            @media (min-width: 991.98px) {
                                main {
                                    padding-left: 240px;
                                }
                            }

                            /* Sidebar */
                            .sidebar {
                                position: fixed;
                                top: 0;
                                bottom: 0;
                                left: 0;
                                padding: 58px 0 0;
                                /* Height of navbar */
                                box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
                                width: 240px;
                                z-index: 600;
                            }

                            @media (max-width: 991.98px) {
                                .sidebar {
                                    width: 100%;
                                }
                            }

                            .sidebar .active {
                                border-radius: 5px;
                                box-shadow: 0 2px 5px 0 rgb(0 0 0 / 16%), 0 2px 10px 0 rgb(0 0 0 / 12%);
                            }

                            .sidebar-sticky {
                                position: relative;
                                top: 0;
                                height: calc(100vh - 48px);
                                padding-top: 0.5rem;
                                overflow-x: hidden;
                                overflow-y: auto;
                                /* Scrollable contents if viewport is shorter than content. */
                            }
                        </style>
                    </head>

                    <body>
                        <!--Main Navigation-->
                        <header>
                            <!-- Sidebar -->
                            <nav id="sidebarMenu" class="collapse d-lg-block sidebar collapse bg-white">
                                <div class="position-sticky">
                                    <div class="list-group list-group-flush mx-3 mt-4">
                                        <a href="/admin" class="list-group-item list-group-item-action py-2"
                                            data-mdb-ripple-init aria-current="true">
                                            <i class="fas fa-tachometer-alt fa-fw me-3"></i><span>Main dashboard</span>
                                        </a>
                                        <a href="/admin/traffic"
                                            class="list-group-item list-group-item-action py-2 active"
                                            data-mdb-ripple-init>
                                            <i class="fas fa-chart-area fa-fw me-3"></i><span>Website traffic </span>
                                        </a>
                                        <a href="/admin/seo" class="list-group-item list-group-item-action py-2"
                                            data-mdb-ripple-init>
                                            <i class="fas fa-chart-pie fa-fw me-3"></i><span>SEO</span>
                                        </a>
                                        <a href="/crud/order" class="list-group-item list-group-item-action py-2"
                                            data-mdb-ripple-init><i
                                                class="fas fa-chart-bar fa-fw me-3"></i><span>Orders</span></a>
                                        <a href="/crud/product" class="list-group-item list-group-item-action py-2"
                                            data-mdb-ripple-init><i
                                                class="fas fa-calendar fa-fw me-3"></i><span>Products</span></a>
                                        <a href="/crud/user" class="list-group-item list-group-item-action py-2"
                                            data-mdb-ripple-init><i
                                                class="fas fa-users fa-fw me-3"></i><span>Users</span></a>
                                    </div>
                                </div>
                            </nav>
                            <!-- Sidebar -->

                            <!-- Navbar -->
                            <nav id="main-navbar" class="navbar navbar-expand-lg navbar-light bg-white fixed-top">
                                <!-- Container wrapper -->
                                <div class="container-fluid">
                                    <!-- Toggle button -->
                                    <button class="navbar-toggler" type="button" data-mdb-collapse-init
                                        data-mdb-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false"
                                        aria-label="Toggle navigation">
                                        <i class="fas fa-bars"></i>
                                    </button>

                                    <!-- Brand -->
                                    <a class="navbar-brand" href="/admin">
                                        <img src="https://salt.tikicdn.com/ts/upload/0e/07/78/ee828743c9afa9792cf20d75995e134e.png"
                                            height="35" alt="" loading="lazy" />
                                    </a>
                                    <!-- Search form -->
                                    <form class="d-none d-md-flex input-group w-auto my-auto">
                                        <input autocomplete="off" type="search" class="form-control rounded"
                                            placeholder='Search (ctrl + "/" to focus)' style="min-width: 225px" />
                                        <span class="input-group-text border-0"><i class="fas fa-search"></i></span>
                                    </form>

                                    <!-- Right links -->
                                    <ul class="navbar-nav ms-auto d-flex flex-row">
                                        <!-- Notification dropdown -->
                                        <li class="nav-item dropdown">
                                            <a class="nav-link me-3 me-lg-0 dropdown-toggle hidden-arrow" href="#"
                                                id="navbarDropdownMenuLink" role="button" data-mdb-dropdown-init
                                                aria-expanded="false">
                                                <i class="fas fa-bell"></i>
                                                <span class="badge rounded-pill badge-notification bg-danger">1</span>
                                            </a>
                                            <ul class="dropdown-menu dropdown-menu-end"
                                                aria-labelledby="navbarDropdownMenuLink">
                                                <li><a class="dropdown-item" href="#">Some news</a></li>
                                                <li><a class="dropdown-item" href="#">Another news</a></li>
                                                <li>
                                                    <a class="dropdown-item" href="#">Something else</a>
                                                </li>
                                            </ul>
                                        </li>

                                        <!-- Icon -->
                                        <li class="nav-item">
                                            <a class="nav-link me-3 me-lg-0" href="#">
                                                <i class="fas fa-fill-drip"></i>
                                            </a>
                                        </li>
                                        <!-- Icon -->
                                        <li class="nav-item me-3 me-lg-0">
                                            <a class="nav-link" href="#">
                                                <i class="fab fa-github"></i>
                                            </a>
                                        </li>

                                        <!-- Icon dropdown -->
                                        <li class="nav-item dropdown">
                                            <a class="nav-link me-3 me-lg-0 dropdown-toggle hidden-arrow" href="#"
                                                id="navbarDropdown" role="button" data-mdb-dropdown-init
                                                aria-expanded="false">
                                                <i class="united kingdom flag m-0"></i>
                                            </a>
                                            <ul class="dropdown-menu dropdown-menu-end"
                                                aria-labelledby="navbarDropdown">
                                                <li>
                                                    <a class="dropdown-item" href="#"><i
                                                            class="united kingdom flag"></i>English
                                                        <i class="fa fa-check text-success ms-2"></i></a>
                                                </li>
                                                <li>
                                                    <hr class="dropdown-divider" />
                                                </li>
                                                <li>
                                                    <a class="dropdown-item" href="#"><i
                                                            class="poland flag"></i>Polski</a>
                                                </li>
                                                <li>
                                                    <a class="dropdown-item" href="#"><i class="china flag"></i>中文</a>
                                                </li>
                                                <li>
                                                    <a class="dropdown-item" href="#"><i class="japan flag"></i>日本語</a>
                                                </li>
                                                <li>
                                                    <a class="dropdown-item" href="#"><i
                                                            class="germany flag"></i>Deutsch</a>
                                                </li>
                                                <li>
                                                    <a class="dropdown-item" href="#"><i
                                                            class="france flag"></i>Français</a>
                                                </li>
                                                <li>
                                                    <a class="dropdown-item" href="#"><i
                                                            class="spain flag"></i>Español</a>
                                                </li>
                                                <li>
                                                    <a class="dropdown-item" href="#"><i
                                                            class="russia flag"></i>Русский</a>
                                                </li>
                                                <li>
                                                    <a class="dropdown-item" href="#"><i
                                                            class="portugal flag"></i>Português</a>
                                                </li>
                                            </ul>
                                        </li>

                                        <!-- Avatar -->
                                        <li class="nav-item dropdown">
                                            <a class="nav-link dropdown-toggle hidden-arrow d-flex align-items-center"
                                                href="#" id="navbarDropdownMenuLink" role="button"
                                                data-mdb-dropdown-init aria-expanded="false">
                                                <img src="https://mdbootstrap.com/img/Photos/Avatars/img (31).jpg"
                                                    class="rounded-circle" height="22" alt="" loading="lazy" />
                                            </a>
                                            <ul class="dropdown-menu dropdown-menu-end"
                                                aria-labelledby="navbarDropdownMenuLink">
                                                <li><a class="dropdown-item" href="#">My profile</a></li>
                                                <li><a class="dropdown-item" href="#">Settings</a></li>
                                                <li><a class="dropdown-item" href="/logout">Logout</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                                <!-- Container wrapper -->
                            </nav>
                            <!-- Navbar -->
                        </header>
                        <!--Main Navigation-->

                        <!--Main layout-->
                        <main style="margin-top: 58px">
                            <div class="container-fluid pt-4">
                                <!-- Section: Main chart -->
                                <section class="mb-4">
                                    <div class="card">
                                        <div class="card-header py-3">
                                            <h5 class="mb-0 text-center"><strong>${page}</strong></h5>
                                        </div>
                                        <div class="card-body">
                                            <!-- <canvas class="my-4 w-100" id="myChart" height="380"></canvas> -->
                                            <!-- <div class="my-4 w-100"> -->
                                            <c:choose>
                                                <c:when test="${page.equals('')}">
                                                    <canvas class="my-4 w-100" id="myChart" height="380"></canvas>
                                                </c:when>
                                                <c:when test="${page.equals('order')}">
                                                    <%@ include file="order.jsp" %>
                                                </c:when>
                                                <c:when test="${page.equals('product')}">
                                                    <%@ include file="product.jsp" %>
                                                </c:when>
                                                <c:when test="${page.equals('user')}">
                                                    <%@ include file="user.jsp" %>
                                                </c:when>
                                                <c:when test="${page.equals('product-details')}">
                                                    <%@ include file="productImage.jsp" %>
                                                </c:when>
                                                <c:when test="${page.equals('traffic')}">
                                                    <%@ include file="traffic.jsp" %>
                                                </c:when>
                                                <c:when test="${page.equals('seo')}">
                                                    <%@ include file="seo.jsp" %>
                                                </c:when>
                                                <c:otherwise>
                                                    <%@ include file="dashboard.jsp" %>
                                                </c:otherwise>
                                            </c:choose>
                                            <!-- </div> -->
                                        </div>
                                    </div>
                                </section>
                            </div>
                        </main>
                        <script type="text/javascript"
                            src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.2.0/mdb.umd.min.js"></script>

                        <!--Main layout-->
                    </body>

                    </html>