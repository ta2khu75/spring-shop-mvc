<section class="vh-100">
  <div class="container py-5 h-100">
    <div class="row d-flex align-items-center justify-content-center h-100">
      <div class="col-md-8 col-lg-7 col-xl-6">
        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg" class="img-fluid"
          alt="Phone image">
      </div>
      <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
        <h2 class="mb-4">Log in</h2>
        <c:if test="${error!=null}">
          <div class="alert alert-danger" role="alert">
            ${error}
          </div>
        </c:if>
        <c:if test="${errorCaptcha}">
          <div class="alert alert-danger" role="alert">
            Captcha code incorrect
          </div>
        </c:if>
        <c:if test="${errorLogin}">
          <div class="alert alert-danger" role="alert">
            Email or password is incorrect
          </div>
        </c:if>
        <c:if test="${errorValid}">
          <div class="alert alert-danger" role="alert">
            Email or password is invalid
          </div>
        </c:if>
        <c:if test="${errorLocked}">
          <div class="alert alert-danger" role="alert">
            Your account is locked. Please contact the administration
          </div>
        </c:if>
        <form:form method="post" action="/login" modelAttribute="user">
          <!-- Email input -->
          <div data-mdb-input-init class="form-outline mt-4">
            <form:input path="email" type="email" id="form1Example13" class="form-control form-control-lg" />
            <label class="form-label" for="form1Example13">Email address</label>
          </div>
          <form:errors class="text-danger" path="email" />

          <!-- Password input -->
          <div data-mdb-input-init class="form-outline mt-4">
            <form:input path="password" type="password" id="form1Example23" class="form-control form-control-lg" />
            <label class="form-label" for="form1Example23">Password</label>
          </div>
          <form:errors class="text-danger" path="password" />
          <div class="row mt-4">
            <div class="col-md-6">
              <div data-mdb-input-init class="form-outline">
                <input type="text" name="captcha" id="form3Example1m" class="form-control form-control-lg" />
                <label class="form-label" for="form3Example1m">Captcha code</label>
              </div>
            </div>
            <div class="col-md-6">
              <img src="${captcha}" alt="">
            </div>
          </div>
          <div class="d-flex justify-content-around align-items-center my-4">
            <!-- Checkbox -->
            <div class="form-check">
              <input class="form-check-input" type="checkbox" value="true" id="form1Example3" name="remember" />
              <label class="form-check-label" for="form1Example3"> Remember me </label>
            </div>
            <a href="#!">Forgot password?</a>
          </div>


          <!-- Submit button -->
          <button type="submit" class="btn btn-primary btn-lg btn-block">Sign in</button>
          <div class="divider d-flex align-items-center my-4">
            <p class="text-center fw-bold mx-3 mb-0 text-muted">New member? <a href="/register">Register</a> here</p>
          </div>
        </form:form>
      </div>
    </div>
  </div>
</section>