<div class="container">
  <h2>Order Form</h2>
  <form:form method="POST" action="/crud/order" modelAttribute="order">
    <form:input type="hidden" readonly="true" path="id" />
    <div class="form-outline mb-4">
      <form:input readonly="true" path="address" id="address" class="form-control" required="true" />
      <label class="form-label" for="address">Address</label>
    </div>
    <div class="form-outline mb-4">
      <form:input path="phoneNumber" id="phoneNumber" class="form-control" readonly="true" required="true" />
      <label class="form-label" for="phoneNumber">Phone Number</label>
    </div>
    <div class="form-outline mb-4">
      <form:input path="total" id="total" class="form-control" type="number" readonly="true" required="true" />
      <label class="form-label" for="total">Total</label>
    </div>
    <div class="form-outline mb-4">
      <form:input readonly="true" path="fullName" id="fullName" class="form-control" required="true" />
      <label class="form-label" for="fullName">Full Name</label>
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
      <form:input path="createDate" id="createDate" class="form-control" readonly="true" />
      <label class="form-label" for="createDate">Create Date</label>
    </div>
    <div class="form-outline mb-4">
      <form:input path="updateDate" id="updateDate" class="form-control" readonly="true" />
      <label class="form-label" for="updateDate">Update Date</label>
    </div>
    <button type="submit" class="btn btn-primary btn-block mb-4">
      Save Order
    </button>
  </form:form>
  <h2>Order List</h2>
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
        <th>Update Date</th>
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
          <td>${order.updateDate}</td>
          <td>
            <!-- Edit button -->
            <form:form action="/crud/order/edit" method="get" style="display: inline">
              <c:if test="${pages!=null}">
                <input type="hidden" name="pages" value="${pages}" />
              </c:if>
              <input type="hidden" name="id" value="${order.id}" />
              <button type="submit" class="btn btn-warning btn-sm">
                Edit
              </button>
            </form:form>

            <!-- Delete button -->
            <form:form action="/crud/order/delete" method="get" style="display: inline">
              <c:if test="${page!=null}">
                <input type="hidden" name="pages" value="${pages}" />
              </c:if>
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
  <ul class="pagination">
    <li class="page-item">
      <a class="page-link" href="/crud/order" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <c:forEach var="page" begin="0" end="${orders.totalPages-1}">
      <li class="page-item"><a class="page-link ${products.number==page?'active':''}"
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