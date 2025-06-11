package com.practiceexammodule3.controller;

import com.practiceexammodule3.model.Book;
import com.practiceexammodule3.model.Loan;
import com.practiceexammodule3.model.Student;
import com.practiceexammodule3.service.BookDAO;
import com.practiceexammodule3.service.IBookDAO;
import com.practiceexammodule3.service.ILoanDAO;
import com.practiceexammodule3.service.IStudentDAO;
import com.practiceexammodule3.service.LoanDAO;
import com.practiceexammodule3.service.StudentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/borrow")
public class BorrowBookServlet extends HttpServlet {
    private IBookDAO bookDAO;
    private IStudentDAO studentDAO;
    private ILoanDAO loanDAO;

    @Override
    public void init() {
        String url = "jdbc:mysql://localhost:3306/library_management";
        String username = "root";
        String password = "123456";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            bookDAO = new BookDAO(conn);
            studentDAO = new StudentDAO(conn);
            loanDAO = new LoanDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("bookId");
        Book book = bookDAO.findById(bookId);
        if (book == null || book.getQuantity() == 0) {
            req.setAttribute("error", "Sách không khả dụng hoặc đã hết!");
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }
        List<Student> students = studentDAO.findAll();
        req.setAttribute("book", book);
        req.setAttribute("students", students);
        req.setAttribute("currentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        req.getRequestDispatcher("/WEB-INF/views/borrow_book.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loanId = req.getParameter("loanId");
        String bookId = req.getParameter("bookId");
        String studentId = req.getParameter("studentId");
        String returnDateStr = req.getParameter("returnDate");

        if (!loanId.matches("MS-\\d{4}")) {
            req.setAttribute("error", "Mã mượn sách phải theo định dạng MS-XXXX!");
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }

        LocalDate borrowDate = LocalDate.now();
        LocalDate returnDate;
        try {
            returnDate = LocalDate.parse(returnDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if (returnDate.isBefore(borrowDate)) {
                req.setAttribute("error", "Ngày trả không được trước ngày mượn!");
                req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
                return;
            }
        } catch (Exception e) {
            req.setAttribute("error", "Định dạng ngày trả không hợp lệ!");
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            return;
        }

        Loan loan = new Loan();
        loan.setLoanId(loanId);
        loan.setBookId(bookId);
        loan.setStudentId(studentId);
        loan.setStatus(true);
        loan.setBorrowDate(borrowDate);
        loan.setReturnDate(returnDate);

        loanDAO.save(loan);
        Book book = bookDAO.findById(bookId);
        bookDAO.updateQuantity(bookId, book.getQuantity() - 1);

        resp.sendRedirect("books");
    }

    @Override
    public void destroy() {
        if (bookDAO != null) bookDAO.closeConnection();
        if (studentDAO != null) studentDAO.closeConnection();
        if (loanDAO != null) loanDAO.closeConnection();
    }
}