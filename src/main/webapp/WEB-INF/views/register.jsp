<section class="">
    <!-- Jumbotron -->
    <div class="px-4 py-5 px-md-5 text-center text-lg-start" style="background-color: hsl(0, 0%, 96%)">
        <div class="container">
            <div class="row gx-lg-5 align-items-center">
                <div class="col-lg-6 mb-5 mb-lg-0">
                    <h1 class="my-5 display-3 fw-bold ls-tight">
                        The best offer <br />
                        <span class="text-primary">for your business</span>
                    </h1>
                    <p style="color: hsl(217, 10%, 50.8%)">
                        Lorem ipsum dolor sit amet consectetur adipisicing elit.
                        Eveniet, itaque accusantium odio, soluta, corrupti aliquam
                        quibusdam tempora at cupiditate quis eum maiores libero
                        veritatis? Dicta facilis sint aliquid ipsum atque?
                    </p>
                </div>

                <div class="col-lg-6 mb-5 mb-lg-0">
                    <div class="card">
                        <div class="card-body py-5 px-md-5">
                            <form:form action="/register" method="POST" modelAttribute="user">
                                <!-- Email input -->
                                <div data-mdb-input-init class="form-outline mb-4">
                                    <form:input path="email" type="email" id="form3Example3" class="form-control" />
                                    <label class="form-label" for="form3Example3">Email address</label>
                                </div>
                                <div data-mdb-input-init class="form-outline mb-4">
                                    <form:input path="name" type="text" id="name" class="form-control" />
                                    <label class="form-label" for="name">Full name</label>
                                </div>
                                <!-- Password input -->
                                <div data-mdb-input-init class="form-outline mb-4">
                                    <form:input path="password" type="password" id="form3Example4" class="form-control" />
                                    <label class="form-label" for="form3Example4">Password</label>
                                </div>
                                <div data-mdb-input-init class="form-outline mb-4">
                                    <form:input path="confirmPassword" type="password" id="form3Example4" class="form-control" />
                                    <label class="form-label" for="form3Example4">Confirm password</label>
                                </div>

                                <!-- Checkbox -->
                                <div class="form-check d-flex justify-content-center mb-4">
                                    <input class="form-check-input me-2" type="checkbox" value="" id="form2Example33"
                                        checked />
                                    <label class="form-check-label" for="form2Example33">
                                        Subscribe to our newsletter
                                    </label>
                                </div>

                                <!-- Submit button -->
                                <button type="submit"
                                    class="btn btn-primary btn-block mb-4">
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