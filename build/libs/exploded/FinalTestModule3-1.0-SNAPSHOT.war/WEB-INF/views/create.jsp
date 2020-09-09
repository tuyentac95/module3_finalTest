<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<h3>Add new product</h3>
<form method="post" action="/products">
    <p>Name</p>
    <input type="text" name="name"/>
    <p>Price</p>
    <input type="text" name="price"/>
    <p>Quantity</p>
    <input type="text" name="quantity"/>
    <p>Color</p>
    <input type="text" name="color"/>
    <p>Description</p>
    <input type="text" name="description"/>
    <p>Category</p>
<%--    <input type="text" name="category_id"/>--%>
    <select name="category">
        <c:forEach var="category" items="${categories}">
            <option><c:out value="${category}"/></option>
        </c:forEach>
    </select>
    <input type="hidden" name="action" value="add"/>
    <button type="submit">Create</button>
</form>
<button onclick="window.location.href='/products'">Back</button>
</body>
</html>
