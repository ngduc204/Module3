package com.practiceexammodule3.controller;

import com.practiceexammodule3.model.Book;
import com.practiceexammodule3.service.BookDAO;
import com.practiceexammodule3.service.IBookDAO;
import com.practiceexammodule3.service.ILoanDAO;
import com.practiceexammodule3.service.LoanDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/return")
public class ReturnBookServlet extends HttpServlet {
    private ILoanDAO loanDAO;
    private IBookDAO bookDAO;

    @Override
    public void init() {
        String url = "jdbc:mysql://localhost:3306/library_management";
        String username = "root";
        String password = "123456";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            loanDAO = new LoanDAO(conn);
            bookDAO = new BookDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loanId = req.getParameter("loanId");
        String bookId = req.getParameter("bookId");

        loanDAO.returnBook(loanId);
        Book book = bookDAO.findById(bookId);
        bookDAO.updateQuantity(bookId, book.getQuantity() + 1);

        resp.sendRedirect("loans");
    }

    @Override
    public void destroy() {
        if (loanDAO != null) loanDAO.closeConnection();
        if (bookDAO != null) bookDAO.closeConnection();
    }
}