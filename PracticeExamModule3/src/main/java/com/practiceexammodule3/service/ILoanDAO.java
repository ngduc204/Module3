package com.practiceexammodule3.service;

import com.practiceexammodule3.model.Loan;

import java.util.List;

public interface ILoanDAO {
    void save(Loan loan);

    List<Loan> findBorrowingBooks(String bookTitle, String studentName);

    void returnBook(String loanId);

    void closeConnection();
}