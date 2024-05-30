<div class="container-fluid">
    <div class="card mb-5">
        <div class="card-header">
            <h2 class="mb-0 text-center">User Form</h2>
            <c:if test="${successUpdate}">
                <div class="alert alert-success" role="alert">
                    Update user successfully
                </div>
            </c:if>
            <c:if test="${successDelete}">
                <div class="alert alert-success" role="alert">
                    Delete user successfully
                </div>
            </c:if>
            <c:if test="${errorNotExist}">
                <div class="alert alert-danger" role="alert">
                    User not exist
                </div>
            </c:if>
            <c:if test="${errorValid}">
                <div class="alert alert-danger" role="alert">
                    Data invalid. please check your data.
                </div>
            </c:if>
            <c:if test="${errorDelete}">
                <div class="alert alert-danger" role="alert">
                    The user has a foreign key issue
                </div>
            </c:if>
             <c:if test="${errorLocked}">
                <div class="alert alert-danger" role="alert">
                    Your account is locked. Please contact admin.
                </div>
            </c:if>
        </div>
        <div class="card-body">
            <form:form class="row g-3" modelAttribute="user" method="POST" action="/crud/user">
                <form:input path="id" type="hidden" />
                <input type="text" name="pages" value="${pages}" hidden />
                <div class="col-md-12">
                    <label for="name" class="form-label">Name</label>
                    <form:input readonly="true" path="name" id="name" class="form-control" required="true" />
                    <form:errors class="text-danger" path="name" />
                </div>
                <div class="col-md-12">
                    <label for="email" class="form-label">Email</label>
                    <form:input readonly="true" path="email" id="email" class="form-control" required="true" />
                    <form:errors class="text-danger" path="email" />
                </div>
                <div class="col-md-12">
                    <div class="form-check">
                        <form:checkbox path="locked" id="locked" class="form-check-input" />
                        <label for="locked" class="form-check-label">Locked</label>
                    </div>
                </div>
                <div class="col-md-12">
                    <label for="role" class="form-label">Role</label>
                    <form:select path="role" items="${roles}" id="role" class="form-select" />
                </div>
                <div class="col-md-12 text-center">
                    <button type="submit" class="btn btn-primary mt-3">Submit</button>
                    <a class="btn btn-secondary ms-2" href="/crud/user">Reset</a>
                </div>
            </form:form>
        </div>
    </div>
    <div class="card">
        <div class="card-header">
            <h2 class="text-center">User List</h2>
        </div>
        <div class="card-body">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col" class="text-center">Id</th>
                        <th scope="col" class="text-center">Name</th>
                        <th scope="col" class="text-center">Email</th>
                        <th scope="col" class="text-center">Locked</th>
                        <th scope="col" class="text-center">Role</th>
                        <th scope="col" class="text-center">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${users.content}">
                        <tr>
                            <th scope="row">
                                <c:out value="${user.id}" />
                            </th>
                            <td class="text-center">
                                <c:out value="${user.name}" />
                            </td>
                            <td class="text-center">
                                <c:out value="${user.email}" />
                            </td>
                            <td class="text-center">
                                <c:out value="${user.locked}" />
                            </td>
                            <td class="text-center">
                                <c:out value="${user.role}" />
                            </td>
                            <td class="d-flex justify-content-center"><a class="btn btn-warning"
                                    href="/crud/user/${user.id}?pages=${pages}">Edit</a>
                                <form:form action="/crud/user/delete" method="get" style="display: inline">
                                    <input type="hidden" name="pages" value="${pages}" />
                                    <input type="hidden" name="_method" value="delete" />
                                    <input type="hidden" name="id" value="${user.id}" />
                                    <button type="submit" class="btn btn-danger ms-2"
                                        onclick="return confirm('Are you sure you want to delete this user?');">
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
                    <a class="page-link" href="/crud/user" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach var="page" begin="0" end="${users.totalPages-1}">
                    <li class="page-item"><a class="page-link ${users.number==page?'active':''}"
                            href="/crud/user?pages=${page}">${page+1}</a>
                    </li>
                </c:forEach>
                <li class="page-item">
                    <a class="page-link" href="/crud/user?pages=${users.totalPages-1}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>