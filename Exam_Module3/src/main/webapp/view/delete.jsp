<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15/05/2025
  Time: 9:33 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Xóa Mặt bằng</title>
</head>
<body>
<h1>Xóa Mặt bằng</h1>
<form action="${pageContext.request.contextPath}/matbang" method="post">
    <p>Bạn có chắc chắn muốn xóa mặt bằng với mã số <%= request.getParameter("maMatBang") %> không?</p>
    <input type="hidden" name="action" value="delete">
    <input type="hidden" name="maMatBang" value="<%= request.getParameter("maMatBang") %>">
    <input type="submit" value="Có">
    <a href="${pageContext.request.contextPath}/matbang">Không</a>
</form>
</body>
</html>
