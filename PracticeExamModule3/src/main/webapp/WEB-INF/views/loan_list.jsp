<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sách đang mượn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function confirmReturn() {
            return confirm("Bạn có chắc chắn muốn trả sách này?");
        }
    </script>
</head>
<body>
<div class="container mt-5">
    <h2>Danh sách sách đang mượn</h2>
    <form method="GET" action="loans" class="mb-3">
        <div class="row">
            <div class="col-md-4">
                <input type="text" class="form-control" name="bookTitle" placeholder="Tìm theo tên sách"
                       value="${param.bookTitle}">
            </div>
            <div class="col-md-4">
                <input type="text" class="form-control" name="studentName" placeholder="Tìm theo tên học sinh"
                       value="${param.studentName}">
            </div>
            <div class="col-md-4">
                <button type="submit" class="btn btn-primary">Tìm kiếm</button>
            </div>
        </div>
    </form>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Mã mượn sách</th>
            <th>Mã sách</th>
            <th>Mã học sinh</th>
            <th>Ngày mượn</th>
            <th>Ngày trả</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="loan" items="${loans}">
            <tr>
                <td>${loan.loanId}</td>
                <td>${loan.bookId}</td>
                <td>${loan.studentId}</td>
                <td>${loan.borrowDate}</td>
                <td>${loan.returnDate}</td>
                <td>
                    <form method="POST" action="return" onsubmit="return confirmReturn()">
                        <input type="hidden" name="loanId" value="${loan.loanId}">
                        <input type="hidden" name="bookId" value="${loan.bookId}">
                        <button type="submit" class="btn btn-danger">Trả sách</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="books" class="btn btn-secondary">Quay lại danh sách sách</a>
</div>
</body>
</html>