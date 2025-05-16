<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lỗi</title>
</head>
<body>
<h1>Có lỗi xảy ra</h1>
<p>${errorMessage}</p>
<a href="${pageContext.request.contextPath}/matbang">Trở về danh sách</a>
</body>
</html>