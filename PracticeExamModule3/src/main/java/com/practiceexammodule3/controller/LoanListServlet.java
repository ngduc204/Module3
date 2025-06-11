package com.practiceexammodule3.controller;

import com.practiceexammodule3.model.Loan;
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
import java.util.List;

@WebServlet("/loans")
public class LoanListServlet extends HttpServlet {
    private ILoanDAO loanDAO;

    @Override
    public void init() {
        String url = "jdbc:mysql://localhost:3306/library_management";
        String username = "root";
        String password = "123456";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            loanDAO = new LoanDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookTitle = req.getParameter("bookTitle") != null ? req.getParameter("bookTitle") : "";
        String studentName = req.getParameter("studentName") != null ? req.getParameter("studentName") : "";
        List<Loan> loans = loanDAO.findBorrowingBooks(bookTitle, studentName);
        req.setAttribute("loans", loans);
        req.getRequestDispatcher("/WEB-INF/views/loan_list.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        if (loanDAO != null) loanDAO.closeConnection();
    }
}