<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15/05/2025
  Time: 10:23 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lỗi</title>
</head>
<body>
<h1>Có lỗi xảy ra</h1>
<p>${errorMessage}</p>
<a href="${pageContext.request.contextPath}/matbang">Quay lại danh sách mặt bằng</a>
</body>
</html>
