<div class="container-fluid">
  <div class="card mb-5">
    <div class="card-header">
      <h2 class="text-center">Order Form</h2>
      <c:if test="${errorValid}">
        <div class="alert alert-danger" role="alert">
          Data invalid. Please try again!
        </div>
      </c:if>
      <c:if test="${errorNotExist}">
        <div class="alert alert-danger" role="alert">
          Order not exist!
        </div>
      </c:if>
      <c:if test="${errorDelete}">
        <div class="alert alert-danger" role="alert">
          The order has a foreign key issue
        </div>
      </c:if>
      <c:if test="${successDelete}">
        <div class="alert alert-success" role="alert">
          Order has been deleted!
        </div>
      </c:if>
      <c:if test="${successUpdate}">
        <div class="alert alert-success" role="alert">
          Order has been updated!
        </div>
      </c:if>
    </div>
    <div class="card-body">
      <form:form method="POST" action="/crud/order" modelAttribute="order">
        <form:input type="hidden" readonly="true" path="id" />
        <input type="text" name="pages" value="${pages}" hidden />
        <div class="form-outline mb-4">
          <label class="form-label" for="address">Address</label>
          <form:input readonly="true" path="address" id="address" class="form-control" required="true" />
          <form:errors class="text-danger" path="address" />
        </div>
        <div class="form-outline mb-4">
          <label class="form-label" for="phoneNumber">Phone Number</label>
          <form:input path="phoneNumber" id="phoneNumber" class="form-control" readonly="true" required="true" />
          <form:errors class="text-danger" path="phoneNumber" />
        </div>
        <div class="form-outline mb-4">
          <label class="form-label" for="total">Total</label>
          <form:input path="total" id="total" class="form-control" type="number" readonly="true" required="true" />
          <form:errors class="text-danger" path="total" />
        </div>
        <div class="form-outline mb-4">
          <label class="form-label" for="fullName">Full Name</label>
          <form:input readonly="true" path="fullName" id="fullName" class="form-control" required="true" />
          <form:errors class="text-danger" path="fullName" />
        </div>
        <div class="form-outline mb-4">
          <label class="form-label" for="status">Status</label>
          <form:select path="status" id="status" items="${statuses}" class="form-select" />
        </div>
        <div class="form-outline mb-4">
          <label class="form-label" for="paymentMethod">Payment Method</label>
          <form:select path="paymentMethod" items="${paymentMethods}" id="paymentMethod" class="form-select" />
        </div>
        <div class="form-outline mb-4">
          <label class="form-label" for="createDate">Create Date</label>
          <form:input path="createDate" id="createDate" class="form-control" readonly="true" />
        </div>
        <div class="form-outline mb-4">
          <label class="form-label" for="updateDate">Update Date</label>
          <form:input path="updateDate" id="updateDate" class="form-control" readonly="true" />
        </div>
        <div class="d-flex justify-content-center">
          <button type="submit" class="btn btn-primary">
            Save Order
          </button><a href="/crud/order" class="btn btn-secondary ms-2">Rest</a>
        </div>

      </form:form>
    </div>
  </div>

  <div class="card">
    <div class="card-header">
      <h2 class="text-center">Order List</h2>
    </div>
    <div class="card-body">
      <table class="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Address</th>
            <th>Phone Number</th>
            <th>Total</th>
            <th>Full Name</th>
            <th>Status</th>
            <th>Payment Method</th>
            <th>Create Date</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${orders.content}" var="order">
            <tr>
              <td>${order.id}</td>
              <td>${order.address}</td>
              <td>${order.phoneNumber}</td>
              <td>${order.total}</td>
              <td>${order.fullName}</td>
              <td>${order.status}</td>
              <td>${order.paymentMethod}</td>
              <td>${order.createDate}</td>
              <td class="d-flex justify-content-center">
                <a href="/crud/order/view?id=${order.id}" class="btn btn-info btn-sm">View</a>
                <!-- Edit button -->
                <form:form action="/crud/order/edit" method="get" style="display: inline">
                  <input type="hidden" name="pages" value="${pages}" />
                  <input type="hidden" name="id" value="${order.id}" />
                  <button type="submit" class="btn btn-warning btn-sm mx-3">
                    Edit
                  </button>
                </form:form>

                <!-- Delete button -->
                <form:form action="/crud/order/delete" method="get" style="display: inline">
                  <input type="hidden" name="pages" value="${pages}" />
                  <input type="hidden" name="_method" value="delete" />
                  <input type="hidden" name="id" value="${order.id}" />
                  <button type="submit" class="btn btn-danger btn-sm"
                    onclick="return confirm('Are you sure you want to delete this order?');">
                    Delete
                  </button>
                </form:form>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
    <div class="card-footer d-flex justify-content-center">
      <ul class="pagination">
        <li class="page-item">
          <a class="page-link" href="/crud/order" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        <c:forEach var="page" begin="0" end="${orders.totalPages-1}">
          <li class="page-item"><a class="page-link ${orders.number==page?'active':''}"
              href="/crud/order?pages=${page}">${page+1}</a>
          </li>
        </c:forEach>
        <li class="page-item">
          <a class="page-link" href="/crud/order?pages=${orders.totalPages-1}" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
    </div>
  </div>
</div>