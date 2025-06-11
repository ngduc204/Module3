package com.practiceexammodule3.service;

import com.practiceexammodule3.model.Student;

import java.util.List;

public interface IStudentDAO {
    List<Student> findAll();

    void closeConnection();
}