<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Product List</title>
</head>
<body>
<h2>Product List</h2>
<form method="get" action="ProductServlet">
  <input type="text" name="name" placeholder="Search by name">
  <input type="submit" value="Search">
</form>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Price</th>
    <th>Description</th>
    <th>Manufacturer</th>
    <th>Actions</th>
  </tr>
  <c:forEach var="product" items="${products}">
    <tr>
      <td>${product.id}</td>
      <td>${product.name}</td>
      <td>${product.price}</td>
      <td>${product.description}</td>
      <td>${product.manufacturer}</td>
      <td>
        <a href="ProductServlet?action=detail&id=${product.id}">View</a>
        <a href="ProductServlet?action=delete&id=${product.id}">Delete</a>
      </td>
    </tr>
  </c:forEach>
</table>
<a href="add-product.jsp">Add New Product</a>
</body>
</html>