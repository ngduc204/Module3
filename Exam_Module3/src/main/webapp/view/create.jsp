<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15/05/2025
  Time: 9:32 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm Mặt bằng</title>
</head>
<body>
<h1>Thêm Mặt bằng</h1>
<form action="${pageContext.request.contextPath}/matbang" method="post">
    Mã mặt bằng: <input type="text" name="maMatBang" required pattern="[A-Z]{3}-[0-9]{2}-[0-9]{2}"><br>
    Diện tích (m²): <input type="number" name="dienTich" min="20" required><br>
    Tầng: <input type="number" name="tang" min="1" max="15" required><br>
    Loại mặt bằng: <select name="loaiMatBang" required>
    <option value="Văn phòng chia sẻ">Văn phòng chia sẻ</option>
    <option value="Văn phòng trọn gói">Văn phòng trọn gói</option>
</select><br>
    Giá tiền (VNĐ): <input type="number" name="giaTien" min="1000000" required><br>
    Ngày bắt đầu: <input type="text" name="ngayBatDau" placeholder="dd/MM/yyyy" required><br>
    Ngày kết thúc: <input type="text" name="ngayKetThuc" placeholder="dd/MM/yyyy" required><br>
    <input type="submit" value="Thêm Mới">
</form>
<a href="${pageContext.request.contextPath}/matbang">Trở về danh sách</a>
</body>
</html>
