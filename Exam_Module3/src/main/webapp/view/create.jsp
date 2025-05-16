<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm Mặt bằng</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 30px;
            max-width: 600px;
        }
        .form-container {
            background: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #343a40;
            margin-bottom: 20px;
        }
        .btn-back {
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center">Thêm Mặt bằng</h1>
    <div class="form-container">
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>
        <form action="${pageContext.request.contextPath}/matbang" method="post">
            <input type="hidden" name="action" value="create">
            <div class="mb-3">
                <label for="maMatBang" class="form-label">Mã mặt bằng</label>
                <input type="text" class="form-control" id="maMatBang" name="maMatBang" required pattern="MB[0-9]{3}" placeholder="VD: MB001" value="${param.maMatBang}">
            </div>
            <div class="mb-3">
                <label for="dienTich" class="form-label">Diện tích (m²)</label>
                <input type="number" class="form-control" id="dienTich" name="dienTich" min="20" required placeholder="Tối thiểu 20" value="${param.dienTich}">
            </div>
            <div class="mb-3">
                <label for="tang" class="form-label">Tầng</label>
                <input type="number" class="form-control" id="tang" name="tang" min="1" max="15" required placeholder="Từ 1 đến 15" value="${param.tang}">
            </div>
            <div class="mb-3">
                <label for="loaiMatBang" class="form-label">Loại mặt bằng</label>
                <select class="form-select" id="loaiMatBang" name="loaiMatBang" required>
                    <option value="" disabled ${param.loaiMatBang == null ? 'selected' : ''}>Chọn loại mặt bằng</option>
                    <option value="Văn phòng chia sẻ" ${param.loaiMatBang == 'Văn phòng chia sẻ' ? 'selected' : ''}>Văn phòng chia sẻ</option>
                    <option value="Văn phòng trọn gói" ${param.loaiMatBang == 'Văn phòng trọn gói' ? 'selected' : ''}>Văn phòng trọn gói</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="giaTien" class="form-label">Giá tiền (VNĐ)</label>
                <input type="number" class="form-control" id="giaTien" name="giaTien" min="1000000" required placeholder="Tối thiểu 1,000,000" value="${param.giaTien}">
            </div>
            <div class="mb-3">
                <label for="ngayBatDau" class="form-label">Ngày bắt đầu</label>
                <input type="text" class="form-control" id="ngayBatDau" name="ngayBatDau" required placeholder="dd/MM/yyyy" value="${param.ngayBatDau}">
            </div>
            <div class="mb-3">
                <label for="ngayKetThuc" class="form-label">Ngày kết thúc</label>
                <input type="text" class="form-control" id="ngayKetThuc" name="ngayKetThuc" required placeholder="dd/MM/yyyy" value="${param.ngayKetThuc}">
            </div>
            <button type="submit" class="btn btn-success w-100">Thêm mới</button>
        </form>
        <a href="${pageContext.request.contextPath}/matbang" class="btn btn-secondary btn-back w-100">Trở về danh sách</a>
    </div>
</div>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>