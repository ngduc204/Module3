<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách sách</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Danh sách sách trong thư viện</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Mã sách</th>
            <th>Tên sách</th>
            <th>Tác giả</th>
            <th>Mô tả</th>
            <th>Số lượng</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.bookId}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.description}</td>
                <td>${book.quantity}</td>
                <td><a href="borrow?bookId=${book.bookId}" class="btn btn-primary">Mượn</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="loans" class="btn btn-secondary">Xem sách đang mượn</a>
</div>
</body>
</html>