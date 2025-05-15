<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15/05/2025
  Time: 9:32 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh sách Mặt bằng</title>
</head>
<body>
<h1>Danh sách Mặt bằng</h1>
<table border="1">
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
                <a href="${pageContext.request.contextPath}/matbang?action=delete&maMatBang=${matBang.maMatBang}">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/matbang?action=create">Thêm mới mặt bằng</a>
</body>
</html>
