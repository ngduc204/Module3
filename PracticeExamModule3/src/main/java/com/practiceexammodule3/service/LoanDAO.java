package com.practiceexammodule3.service;

import com.practiceexammodule3.model.Loan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO implements ILoanDAO {
    private Connection connection;

    public LoanDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Loan loan) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO loan (loan_id, book_id, student_id, status, borrow_date, return_date) VALUES (?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, loan.getLoanId());
            stmt.setString(2, loan.getBookId());
            stmt.setString(3, loan.getStudentId());
            stmt.setBoolean(4, loan.isStatus());
            stmt.setDate(5, java.sql.Date.valueOf(loan.getBorrowDate()));
            stmt.setDate(6, loan.getReturnDate() != null ? java.sql.Date.valueOf(loan.getReturnDate()) : null);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Loan> findBorrowingBooks(String bookTitle, String studentName) {
        List<Loan> loans = new ArrayList<>();
        String sql = "SELECT l.* FROM loan l JOIN book b ON l.book_id = b.book_id JOIN student s ON l.student_id = s.student_id " +
                "WHERE l.status = TRUE AND b.title LIKE ? AND s.name LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + bookTitle + "%");
            stmt.setString(2, "%" + studentName + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Loan loan = new Loan();
                loan.setLoanId(rs.getString("loan_id"));
                loan.setBookId(rs.getString("book_id"));
                loan.setStudentId(rs.getString("student_id"));
                loan.setStatus(rs.getBoolean("status"));
                loan.setBorrowDate(rs.getDate("borrow_date").toLocalDate());
                loan.setReturnDate(rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null);
                loans.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }

    @Override
    public void returnBook(String loanId) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE loan SET status = FALSE WHERE loan_id = ?")) {
            stmt.setString(1, loanId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}