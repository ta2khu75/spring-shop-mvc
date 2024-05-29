<div class="container-fluid">
    <h1 class="my-4 text-center">
        <c:out value="${message}" />
    </h1>
    <div class="row">
        <div class="col-12 mb-4">
            <div class="card">
                <div class="card-header">
                    <h5 class="mb-0">Add/Edit User</h5>
                </div>
                <div class="card-body">
                    <form:form class="row g-3" modelAttribute="user" method="POST" action="/crud/user">
                        <form:input path="id" type="hidden" />
                        <div class="col-md-12">
                            <label for="name" class="form-label">Name</label>
                            <form:input path="name" id="name" class="form-control" required="true" />
                        </div>
                        <div class="col-md-12">
                            <label for="email" class="form-label">Email</label>
                            <form:input path="email" id="email" class="form-control" required="true" />
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
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        <div class="col-12 mb-4">
            <div class="card">
                <div class="card-header">
                    <h5 class="mb-0">User List</h5>
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
                                    <td class="text-center"><a class="btn btn-warning"
                                            href="/crud/user/${user.id}">Edit</a>
                                        <a class="btn btn-danger ms-2" href="/crud/user/delete/${user.id}">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
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
    </div>

</div>