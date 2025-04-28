<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Product Detail</title>
</head>
<body>
<h2>Product Detail</h2>
<p>ID: ${product.id}</p>
<p>Name: ${product.name}</p>
<p>Price: ${product.price}</p>
<p>Description: ${product.description}</p>
<p>Manufacturer: ${product.manufacturer}</p>
<a href="ProductServlet?action=list">Back to List</a>
</body>
</html>