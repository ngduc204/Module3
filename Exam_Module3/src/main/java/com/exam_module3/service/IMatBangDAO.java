package com.exam_module3.service;

import com.exam_module3.model.MatBang;

import java.sql.SQLException;
import java.util.List;

public interface IMatBangDAO {
    void addMatBang(MatBang matBang) throws SQLException;
    List<MatBang> selectAllMatBang() throws SQLException;
    boolean deleteMatBang(String maMatBang) throws SQLException;
}