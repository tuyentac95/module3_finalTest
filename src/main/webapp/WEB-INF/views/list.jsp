<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <style>
        th, td{
            margin: 0px;
            border: 1px black solid;
            padding: 5px;
        }
        th{
            background-color: darkblue;
            color: azure;
        }
    </style>
</head>
<body>
    <button onclick="window.location.href='/products?action=add'">Add product</button>
    <form action="/products">
        <input type="hidden" name="action" value="search"/>
        <input type="text" name="name" placeholder="search by name"/>
        <button type="submit">Search</button>
    </form>
    <c:if test="${message != null}">
        <c:out value="${message}"/>
        <button onclick="window.location.href='/products'">Back</button>
    </c:if>
   <fieldset>
       <legend>Products list</legend>
       <table>
           <tr>
               <th>#</th>
               <th>Name</th>
               <th>Price</th>
               <th>Quantity</th>
               <th>Color</th>
               <th>Description</th>
               <th>Category</th>
               <th colspan="2">Action</th>
           </tr>
           <c:forEach var="product" items="${products}">
               <tr>
                   <td><c:out value="${product.getId()}"/></td>
                   <td><c:out value="${product.getName()}"/></td>
                   <td><c:out value="${product.getPrice()}"/></td>
                   <td><c:out value="${product.getQuantity()}"/></td>
                   <td><c:out value="${product.getColor()}"/></td>
                   <td><c:out value="${product.getDescription()}"/></td>
                   <td><c:out value="${categories.get(products.indexOf(product))}"/></td>
                   <td><button onclick="window.location.href='/products?action=edit&id=${product.getId()}'">Edit</button></td>
                   <td><button onclick="window.location.href='/products?action=delete&id=${product.getId()}'">Delete</button></td>
               </tr>
           </c:forEach>
       </table>
   </fieldset>
</body>
</html>
