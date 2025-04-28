<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Discount Calculator</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        input[type="text"], input[type="number"] { width: 100%; padding: 8px; margin: 5px 0; }
        input[type="submit"] { padding: 10px 15px; }
    </style>
</head>
<body>
<h1>Product Discount Calculator</h1>
<form action="DiscountServlet" method="post">
    <label for="productDescription">Product Description:</label>
    <input type="text" id="productDescription" name="productDescription" required>

    <label for="listPrice">List Price:</label>
    <input type="number" id="listPrice" name="listPrice" required step="0.01">

    <label for="discountPercent">Discount Percent:</label>
    <input type="number" id="discountPercent" name="discountPercent" required step="0.01">

    <input type="submit" value="Calculate Discount">
</form>
</body>
</html>