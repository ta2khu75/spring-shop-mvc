<section class="">
    <!-- Jumbotron -->
    <div class="px-4 py-5 px-md-5 text-center text-lg-start" style="background-color: hsl(0, 0%, 96%)">
        <div class="container">
            <div class="row gx-lg-5 align-items-center">
                <div class="col-lg-6 mb-5 mb-lg-0">
                    <h1 class="my-5 mb-3 display-3 fw-bold ls-tight text-primary">
                        Tiki <br />
                        <span class="text-primary">Good & Fast</span>
                    </h1>
                    <p style="color: hsl(217, 10%, 50.8%)">
                        Shopping at Tiki has promotions every day<br>
                        Fast delivery within 2 hours and on time <br>
                        30 Days free return <br>
                        100% Selection <br>
                    </p>
                </div>

                <div class="col-lg-6 mb-5 mb-lg-0">
                    <div class="card">
                        <div class="card-body py-5 px-md-5">
                            <h2 class="mb-4">Register</h2>
                            <c:if test="${success}">
                                <div class="alert alert-success" role="alert">
                                    Register Successfully
                                </div>
                            </c:if>
                            <c:if test="${errorEmail}">
                                <div class="alert alert-danger" role="alert">
                                    Email exists already.
                                </div>
                            </c:if>
                            <c:if test="${errorPassword}">
                                <div class="alert alert-danger" role="alert">
                                    Password and confirm password not match.
                                </div>
                            </c:if>
                            <c:if test="${errorValid}">
                                <div class="alert alert-danger" role="alert">
                                    Please fill in all fields.
                                </div>
                            </c:if>
                            <form:form action="/register" method="POST" modelAttribute="user">
                                <!-- Email input -->
                                <div data-mdb-input-init class="form-outline">
                                    <form:input path="email" type="email" id="form3Example3" class="form-control" />
                                    <label class="form-label" for="form3Example3">Email address</label>
                                </div>
                                <form:errors class="text-danger mb-4" path="email" />
                                <div data-mdb-input-init class="form-outline mt-4">
                                    <form:input path="name" type="text" id="name" class="form-control" />
                                    <label class="form-label" for="name">Full name</label>
                                </div>
                                <form:errors class="text-danger mb-4" path="name" />
                                <!-- Password input -->
                                <div data-mdb-input-init class="form-outline mt-4">
                                    <form:input path="password" type="password" id="form3Example4"
                                        class="form-control" />
                                    <label class="form-label" for="form3Example4">Password</label>
                                </div>
                                <form:errors class="text-danger" path="password" />
                                <div data-mdb-input-init class="form-outline mt-4">
                                    <form:input path="confirmPassword" type="password" id="form3Example4"
                                        class="form-control" />
                                    <label class="form-label" for="form3Example4">Confirm password</label>
                                </div>
                                <form:errors class="text-danger" path="confirmPassword" />

                                <!-- Checkbox -->
                                <div class="form-check d-flex justify-content-center my-4">
                                    <input class="form-check-input me-2" type="checkbox" value="" id="form2Example33"
                                        checked />
                                    <label class="form-check-label" for="form2Example33">
                                        Subscribe to our newsletter
                                    </label>
                                </div>

                                <!-- Submit button -->
                                <button type="submit" class="btn btn-primary btn-block mb-4">
                                    Sign up
                                </button>
                                <div class="text-center">
                                    <p>Already member? <a href="/login">Login</a> here</p>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Jumbotron -->
</section>
<!-- Section: Design Block -->