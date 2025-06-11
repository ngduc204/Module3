<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Lỗi</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="alert alert-danger">
        <h4>Lỗi!</h4>
        <p>${error}</p>
        <a href="books" class="btn btn-primary">Quay lại danh sách sách</a>
    </div>
</div>
</body>
</html>