<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh sách Mặt bằng</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 30px;
            max-width: 1200px;
        }
        .table th, .table td {
            vertical-align: middle;
        }
        .table-responsive {
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
        }
        .btn-create {
            margin-bottom: 20px;
        }
        h1 {
            color: #343a40;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center">Danh sách Mặt bằng</h1>
    <a href="${pageContext.request.contextPath}/matbang?action=create" class="btn btn-primary btn-create">Thêm mới mặt bằng</a>
    <div class="table-responsive">
        <table class="table table-hover table-bordered">
            <thead class="table-dark">
            <tr>
                <th>Mã mặt bằng</th>
                <th>Diện tích (m²)</th>
                <th>Tầng</th>
                <th>Loại mặt bằng</th>
                <th>Giá tiền (VNĐ)</th>
                <th>Ngày bắt đầu</th>
                <th>Ngày kết thúc</th>
                <th>Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="matBang" items="${matBangList}">
                <tr>
                    <td>${matBang.maMatBang}</td>
                    <td>${matBang.dienTich}</td>
                    <td>${matBang.tang}</td>
                    <td>${matBang.loaiMatBang}</td>
                    <td>${matBang.giaTien}</td>
                    <td>${matBang.ngayBatDau}</td>
                    <td>${matBang.ngayKetThuc}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/matbang?action=delete&maMatBang=${matBang.maMatBang}" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc muốn xóa mặt bằng này?')">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>