package com.practiceexammodule3.service;

import com.practiceexammodule3.model.Book;

import java.util.List;

public interface IBookDAO {
    List<Book> findAll();

    Book findById(String id);

    void updateQuantity(String bookId, int quantity);

    void closeConnection();
}