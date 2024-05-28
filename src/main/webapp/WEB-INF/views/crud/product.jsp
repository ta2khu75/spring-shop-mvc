<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <!DOCTYPE html>
            <html>

            <head>
                <title>Create Product</title>
                <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.2.0/mdb.min.css" rel="stylesheet" />
                <script type="text/javascript"
                    src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.2.0/mdb.umd.min.js"></script>
            </head>

            <body>
                <div class="container mt-5">
                    <h2 class="text-center mb-4">Management Product</h2>

                    <p class="text-center">
                        <c:out value="${message}" />
                    </p>

                    <form:form modelAttribute="product" enctype="multipart/form-data" method="post"
                        action="/crud/product" class="product-form needs-validation">
                        <form:input path="id" type="hidden" />
                        <form:input path="imageUrl" type="hidden" />

                        <div class="mb-3">
                            <form:label path="name" class="form-label">Name:</form:label>
                            <form:input path="name" class="form-control" />
                        </div>
                        <div class="mb-3">
                            <form:label path="price" class="form-label">Price:</form:label>
                            <form:input path="price" type="number" class="form-control" />
                        </div>
                        <div class="mb-3">
                            <form:label path="quantity" class="form-label">Quantity:</form:label>
                            <form:input path="quantity" type="number" class="form-control" />
                        </div>
                        <div class="mb-3">
                            <form:label path="description" class="form-label">Description:</form:label>
                            <form:textarea path="description" class="form-control" rows="3"></form:textarea>
                        </div>

                        <div class="mb-3">
                            <label for="image" class="form-label">Image</label>
                            <input type="file" id="image" name="image" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label class="form-label d-block">Active:</label>
                            <form:radiobutton path="active" value="true" class="form-check-input me-1" /> Active
                            <form:radiobutton path="active" value="false" class="form-check-input ms-3 me-1" /> Non
                            Active
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Category:</label>
                            <form:select path="category" class="form-select">
                                <form:options items="${categories}" itemValue="name" itemLabel="name" />
                            </form:select>
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Save</button>
                            <a class="btn btn-secondary ms-2" href="/crud/product">Reset</a>
                        </div>
                    </form:form>

                    <h3 class="text-center mt-5">Product List</h3>
                    <table class="table table-striped table-hover mt-3">
                        <thead class="thead-dark">
                            <tr>
                                <th class="text-center">Id</th>
                                <th class="text-center">Name</th>
                                <th class="text-center">Number of Sales</th>
                                <th class="text-center">Price</th>
                                <th class="text-center">Quantity</th>
                                <th class="text-center">Active</th>
                                <th class="text-center">Category</th>
                                <th class="text-center">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${products}" var="product">
                                <tr>
                                    <td class="text-center">${product.id}</td>
                                    <td class="text-center">${product.name}</td>
                                    <td class="text-center">${product.numberOfSales}</td>
                                    <td class="text-center">${product.price}</td>
                                    <td class="text-center">${product.quantity}</td>
                                    <td class="text-center">
                                        <c:choose>
                                            <c:when test="${product.active}">
                                                <span class="badge badge-success">Active</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge badge-danger">Inactive</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="text-center">${product.category}</td>
                                    <td class="text-center">
                                        <a class="btn btn-info btn-sm" href="/crud/product-image/${product.id}">View</a>
                                        <a class="btn btn-warning btn-sm mx-3"
                                            href="/crud/product/${product.id}">Edit</a>
                                        <a class="btn btn-danger btn-sm"
                                            href="/crud/product/delete/${product.id}">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </body>

            </html>