<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Document</title>
            <!-- MDB -->
            <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.2.0/mdb.min.css" rel="stylesheet" />
            <!-- MDB -->
            <script type="text/javascript"
                src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.2.0/mdb.umd.min.js"></script>
        </head>

        <body>
            <div class="container-fluid">
                <h1 class="my-4 text-center">
                    <c:out value="${message}" />
                </h1>
                <div class="row">
                    <div class="col-lg-6 mb-4">
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
                                        <c:forEach var="user" items="${users}">
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
                                                        href="/crud/user/${user.id}">Edit</a> <a
                                                        class="btn btn-danger ms-2"
                                                        href="/crud/user/delete/${user.id}">Delete</a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-6 mb-4">
                        <div class="card">
                            <div class="card-header">
                                <h5 class="mb-0">Add/Edit User</h5>
                            </div>
                            <div class="card-body">
                                <form:form class="row g-3" modelAttribute="user" method="POST" action="/crud/user">
                                        <form:input path="id" type="hidden"/>
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
                                        <form:select path="role" id="role" class="form-select">
                                            <c:forEach var="role" items="${roles}">
                                                <option value="${role}">${role}</option>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                    <div class="col-md-12 text-center">
                                        <button type="submit" class="btn btn-primary mt-3">Submit</button>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- MDBootstrap JS -->
            <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.10.2/mdb.min.js"></script> -->
        </body>

        </html>