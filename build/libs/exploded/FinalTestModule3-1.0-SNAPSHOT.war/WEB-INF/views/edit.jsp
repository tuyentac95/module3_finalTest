<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<h3>Edit product</h3>
<form method="post" action="/products">
    <p>ID</p>
    <input type="hidden" name="id" value="${product.getId()}" />
    <input type="text" value="${product.getId()}" disabled/>
    <p>Name</p>
    <input type="text" name="name" value="${product.getName()}"/>
    <p>Price</p>
    <input type="text" name="price" value="${product.getPrice()}"/>
    <p>Quantity</p>
    <input type="text" name="quantity" value="${product.getQuantity()}"/>
    <p>Color</p>
    <input type="text" name="color" value="${product.getColor()}"/>
    <p>Description</p>
    <input type="text" name="description" value="${product.getDescription()}"/>
    <p>Category</p>
    <select name="category">
        <c:forEach var="category" items="${categories}">
            <option><c:out value="${category}"/></option>
        </c:forEach>
    </select>
    <input type="hidden" name="action" value="edit"/>
    <button type="submit">Update</button>
</form>
<button onclick="window.location.href='/products'">Back</button>
</body>
</html>
