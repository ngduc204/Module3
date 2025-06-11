package com.practiceexammodule3.controller;

import com.practiceexammodule3.model.Book;
import com.practiceexammodule3.service.BookDAO;
import com.practiceexammodule3.service.IBookDAO;

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

@WebServlet("/books")
public class BookListServlet extends HttpServlet {
    private IBookDAO bookDAO;

    @Override
    public void init() {
        String url = "jdbc:mysql://localhost:3306/library_management";
        String username = "root";
        String password = "123456";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            bookDAO = new BookDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookDAO.findAll();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/WEB-INF/views/book_list.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        if (bookDAO != null) bookDAO.closeConnection();
    }
}