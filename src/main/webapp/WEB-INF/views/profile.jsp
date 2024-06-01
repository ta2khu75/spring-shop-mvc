<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <div class="bg-primary">
        <div class="container py-4">
            <!-- Breadcrumb -->
            <nav class="d-flex">
                <h6 class="mb-0">
                    <a href="" class="text-white-50">Home</a>
                    <span class="text-white-50 mx-2"> > </span>
                    <a href="" class="text-white-50">User</a>
                    <span class="text-white-50 mx-2"> > </span>
                    <a href="" class="text-white"><u>User Profile</u></a>
                </h6>
            </nav>
            <!-- Breadcrumb -->
        </div>
    </div>
    <section style="background-color: #eee; height: 100vh">
        <div class="container py-5">
            <div class="row w-100">
                <div class="col-2">
                    <!-- Tab navs -->
                    <div class="nav flex-column nav-tabs text-center" id="v-tabs-tab" role="tablist"
                        aria-orientation="vertical">
                        <a data-mdb-tab-init class="nav-link active" id="v-tabs-home-tab" href="#v-tabs-home" role="tab"
                            aria-controls="v-tabs-home" aria-selected="true">Profile</a>
                        <a data-mdb-tab-init class="nav-link" id="v-tabs-profile-tab" href="#v-tabs-profile" role="tab"
                            aria-controls="v-tabs-profile" aria-selected="false">New Order</a>
                        <a data-mdb-tab-init class="nav-link" id="v-tabs-messages-tab" href="#v-tabs-messages"
                            role="tab" aria-controls="v-tabs-messages" aria-selected="false">Old Order</a>
                        <a data-mdb-tab-init class="nav-link" id="v-tabs-messages-tab" href="#v-tabs-cancel" role="tab"
                            aria-controls="v-tabs-messages" aria-selected="false">Edit
                            Profile</a>
                        <a data-mdb-tab-init class="nav-link" id="v-tabs-messages-tab" href="#v-tabs-complete"
                            role="tab" aria-controls="v-tabs-messages" aria-selected="false">Change Password</a>
                    </div>
                    <!-- Tab navs -->
                </div>

                <div class="col-9">
                    <!-- Tab content -->
                    <div class="tab-content" id="v-tabs-tabContent">
                        <div class="tab-pane fade show active" id="v-tabs-home" role="tabpanel"
                            aria-labelledby="v-tabs-home-tab">
                            <div class="row">
                                <div class="col-lg-4">
                                    <div class="card mb-4">
                                        <div class="card-body text-center">
                                            <img src="https://i.makeagif.com/media/4-20-2021/EFjuFv.gif" alt="avatar"
                                                class="rounded-circle img-fluid" style="width: 150px;">
                                            <h5 class="my-3">${user.name}</h5>
                                            <p class="text-muted mb-4">Bay Area, San Francisco, CA</p>
                                            <div class="d-flex justify-content-center mb-2">
                                                <button type="button" data-mdb-button-init data-mdb-ripple-init
                                                    class="btn btn-primary">Follow</button>
                                                <button type="button" data-mdb-button-init data-mdb-ripple-init
                                                    class="btn btn-outline-primary ms-1">Edit</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-8">
                                    <div class="card mb-4">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <p class="mb-0">Full Name</p>
                                                </div>
                                                <div class="col-sm-9">
                                                    <p class="text-muted mb-0">${user.name}</p>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <p class="mb-0">Email</p>
                                                </div>
                                                <div class="col-sm-9">
                                                    <p class="text-muted mb-0">${user.email}</p>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <p class="mb-0">Phone</p>
                                                </div>
                                                <div class="col-sm-9">
                                                    <p class="text-muted mb-0">(097) 234-5678</p>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <p class="mb-0">Mobile</p>
                                                </div>
                                                <div class="col-sm-9">
                                                    <p class="text-muted mb-0">(098) 765-4321</p>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <p class="mb-0">Address</p>
                                                </div>
                                                <div class="col-sm-9">
                                                    <p class="text-muted mb-0">Bay Area, San Francisco,
                                                        CA
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${message!=null}">
                                <div class="alert alert-info" role="alert">
                                    ${message}
                                </div>
                            </c:if>
                            <c:if test="${order!=null}">
                                <c:set var="total" value="${order.total}" />
                                <fmt:formatNumber value="${total}" type="currency" currencySymbol="₫"
                                    groupingUsed="true" var="formattedAmount" />
                                <div class="row">
                                    <div class="card mb-4">
                                        <div class="card-header d-flex justify-content-between align-items-center">
                                            <div>
                                                <strong>Order ID: ${order.id}</strong> <span
                                                    class="badge bg-warning">${order.status}</span><br>
                                                <small>Date: ${order.createDate}</small>
                                            </div>
                                            <div>
                                                <c:if test="${cancel}">
                                                    <form:form action="/order/cancel?id=${order.id}" method="get"
                                                        style="display: inline">
                                                        <input type="hidden" name="id" value="${order.id}" />
                                                        <button type="submit" class="btn btn-danger btn-sm"
                                                            onclick="return confirm('Are you sure you want to cancel this order?');">
                                                            Cancel order
                                                        </button>
                                                    </form:form>
                                                </c:if>
                                                <a href="/order/track?id=${order.id}"
                                                    class="btn btn-primary btn-sm">Track
                                                    Order</a>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <div class="d-flex justify-content-between">
                                                <p><strong>Contact:</strong> ${order.fullName}<br>
                                                    Phone: ${order.phoneNumber}
                                                </p>
                                                <p><strong>Shipping address:</strong> ${order.address}
                                                </p>
                                                <p><strong>Payment:</strong>${order.paymentMethod}<br>
                                                    Total paid: ${formattedAmount}
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                        <div class="tab-pane fade" id="v-tabs-profile" role="tabpanel"
                            aria-labelledby="v-tabs-profile-tab">
                            <div class="container">
                                <c:if test="${newOrders!=null}">
                                    <c:forEach var="order" items="${newOrders}">
                                        <c:set var="total" value="${order.total}" />
                                        <fmt:formatNumber value="${total}" type="currency" currencySymbol="₫"
                                            groupingUsed="true" var="formattedAmount" />
                                        <div class="row">
                                            <div class="card mb-4">
                                                <div
                                                    class="card-header d-flex justify-content-between align-items-center">
                                                    <div>
                                                        <strong>Order ID: ${order.id}</strong> <span
                                                            class="badge bg-warning">${order.status}</span><br>
                                                        <small>Date: ${order.createDate}</small>
                                                    </div>
                                                    <div>
                                                        <form:form action="/order/cancel?id=${order.id}" method="get"
                                                            style="display: inline">
                                                            <input type="hidden" name="id" value="${order.id}" />
                                                            <button type="submit" class="btn btn-danger btn-sm"
                                                                onclick="return confirm('Are you sure you want to cancel this order?');">
                                                                Cancel order
                                                            </button>
                                                        </form:form>
                                                        <a href="/order/track?id=${order.id}"
                                                            class="btn btn-primary btn-sm">Track
                                                            Order</a>
                                                    </div>
                                                </div>
                                                <div class="card-body">
                                                    <div class="d-flex justify-content-between">
                                                        <p><strong>Contact:</strong>
                                                            ${order.fullName}<br>
                                                            Phone: ${order.phoneNumber}
                                                        </p>
                                                        <p><strong>Shipping address:</strong>
                                                            ${order.address}
                                                        </p>
                                                        <p><strong>Payment:</strong>${order.paymentMethod}<br>
                                                            Total paid: ${formattedAmount}
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="v-tabs-messages" role="tabpanel"
                            aria-labelledby="v-tabs-messages-tab">
                            <div class="container">
                                <c:if test="${oldOrders!=null}">
                                    <c:forEach var="order" items="${oldOrders}">
                                        <c:set var="total" value="${order.total}" />
                                        <fmt:formatNumber value="${total}" type="currency" currencySymbol="₫"
                                            groupingUsed="true" var="formattedAmount" />
                                        <div class="row">
                                            <div class="card mb-4">
                                                <div
                                                    class="card-header d-flex justify-content-between align-items-center">
                                                    <div>
                                                        <strong>Order ID: ${order.id}</strong> <span
                                                            class="badge bg-warning">${order.status}</span><br>
                                                        <small>Date: ${order.createDate}</small>
                                                    </div>
                                                    <div>
                                                        <a href="/order/track?id=${order.id}"
                                                            class="btn btn-primary btn-sm">Track
                                                            Order</a>
                                                    </div>
                                                </div>
                                                <div class="card-body">
                                                    <div class="d-flex justify-content-between">
                                                        <p><strong>Contact:</strong>
                                                            ${order.fullName}<br>
                                                            Phone: ${order.phoneNumber}
                                                        </p>
                                                        <p><strong>Shipping address:</strong>
                                                            ${order.address}
                                                        </p>
                                                        <p><strong>Payment:</strong>${order.paymentMethod}<br>
                                                            Total paid: ${formattedAmount}
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="v-tabs-cancel" role="tabpanel"
                            aria-labelledby="v-tabs-messages-tab">
                            <div class="container">
                                <form:form action="/profile" method="POST" modelAttribute="user">
                                    <form:input path="id" type="hidden" />
                                    <form:input path="role" type="hidden" />
                                    <div class="mb-4">
                                        <label for="email" class="form-label">Email</label>
                                        <form:input path="email" id="email" class="form-control" required="true" />
                                        <form:errors class="text-danger" path="email" />
                                    </div>
                                    <div class="mb-4">
                                        <label for="name" class="form-label">Name</label>
                                        <form:input path="name" id="name" class="form-control" required="true" />
                                        <form:errors class="text-danger" path="name" />
                                    </div>
                                    <div class="text-center">
                                        <button type="submit" class="btn btn-primary">Save</button>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="v-tabs-complete" role="tabpanel"
                            aria-labelledby="v-tabs-messages-tab">
                            <div class="container">
                                <form action="/profile/change-password" method="post">

                                    <!-- Email input -->
                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="password" name="oldPassword" id="form3Example3"
                                            class="form-control" />
                                        <label class="form-label" for="form3Example3">Old
                                            Password</label>
                                    </div>

                                    <!-- Password input -->
                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="password" name="password" id="form3Example4"
                                            class="form-control" />
                                        <label class="form-label" for="form3Example4">Password</label>
                                    </div>
                                    <div data-mdb-input-init class="form-outline mb-4">
                                        <input type="password" name="confirmPassword" id="form3Example4"
                                            class="form-control" />
                                        <label class="form-label" for="form3Example4">Confirm
                                            Password</label>
                                    </div>
                                    <button class="btn btn-primary btn-block mb-4" type="submit">Change</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- Tab content -->
                </div>
            </div>
        </div>
    </section>