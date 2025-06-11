<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Mượn sách</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function confirmBack() {
            return confirm("Bạn có chắc chắn muốn trở về danh sách?");
        }
    </script>
</head>
<body>
<div class="container mt-5">
    <h2>Mượn sách</h2>
    <form method="POST" action="borrow">
        <div class="mb-3">
            <label for="loanId" class="form-label">Mã mượn sách</label>
            <input type="text" class="form-control" id="loanId" name="loanId" required pattern="^MS-\d{4}$"
                   placeholder="MS-XXXX">
        </div>
        <div class="mb-3">
            <label for="bookTitle" class="form-label">Tên sách</label>
            <input type="text" class="form-control" id="bookTitle" value="${book.title}" readonly>
            <input type="hidden" name="bookId" value="${book.bookId}">
        </div>
        <div class="mb-3">
            <label for="studentId" class="form-label">Học sinh</label>
            <select class="form-select" id="studentId" name="studentId" required>
                <option value="">Chọn học sinh</option>
                <c:forEach var="student" items="${students}">
                    <option value="${student.studentId}">${student.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label for="borrowDate" class="form-label">Ngày mượn</label>
            <input type="text" class="form-control" id="borrowDate" value="${currentDate}" readonly>
        </div>
        <div class="mb-3">
            <label for="returnDate" class="form-label">Ngày trả</label>
            <input type="text" class="form-control" id="returnDate" name="returnDate" required placeholder="dd/MM/yyyy">
        </div>
        <button type="submit" class="btn btn-primary">Mượn sách</button>
        <a href="books" class="btn btn-secondary" onclick="return confirmBack()">Trở lại danh sách</a>
    </form>
</div>
</body>
</html>