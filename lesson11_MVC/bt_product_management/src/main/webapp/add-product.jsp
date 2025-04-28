<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Add Product</title>
</head>
<body>
<h2>Add New Product</h2>
<form method="post" action="ProductServlet">
    <input type="hidden" name="action" value="add">
    Name: <input type="text" name="name" required><br>
    Price: <input type="number" step="0.01" name="price" required><br>
    Description: <input type="text" name="description" required><br>
    Manufacturer: <input type="text" name="manufacturer" required><br>
    <input type="submit" value="Add Product">
</form>
<a href="ProductServlet?action=list">Back to List</a>
</body>
</html>