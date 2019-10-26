<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Category</title>
</head>
<body>

<c:url value="/categories/save" var="createCategoryUrl" />
<form action="${createCategoryUrl}" method="post">
    <%-- Очень часто применяемый способ передачи id через форму --%>
    <input type="hidden" name="id" id="id" value="${category.id}">
    <p>
        <label for="name">Name</label>
        <input type="text" id="name" name="name" value="${category.name}" />
    </p>
    <p>
        <label for="description">Description</label>
        <input type="text" id="description" name="description" value="${category.description}" />
    </p>
    <input type="submit" />
</form>

<c:url value="/products/create" var="createProductUrl">
    <%-- Нам нужно указать, продукт какой категории мы создаем --%>
    <c:param name="categoryId" value="${category.id}"/>
</c:url>
<a href="${createProductUrl}">Create new Product</a>

<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
    </tr>

    <c:forEach items="${category.products}" var="prod">
        <tr>
            <td>${prod.id}</td>
            <c:url value="/products/edit" var="editProductUrl">
                <c:param name="id" value="${prod.id}"/>
            </c:url>
            <td><a href="${editProductUrl}">${prod.name}</a></td>
            <td>${prod.description}</td>
            <td>${prod.price}</td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
