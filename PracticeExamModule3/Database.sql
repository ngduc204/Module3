CREATE DATABASE library_management;
USE library_management;

CREATE TABLE book (
    book_id VARCHAR(50) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    description TEXT,
    quantity INT NOT NULL
);

CREATE TABLE student (
    student_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    class_name VARCHAR(50) NOT NULL
);

CREATE TABLE loan (
    loan_id VARCHAR(50) PRIMARY KEY,
    book_id VARCHAR(50),
    student_id VARCHAR(50),
    status BOOLEAN NOT NULL DEFAULT TRUE, -- TRUE: đang mượn, FALSE: đã trả
    borrow_date DATE NOT NULL,
    return_date DATE,
    FOREIGN KEY (book_id) REFERENCES book(book_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id)
);