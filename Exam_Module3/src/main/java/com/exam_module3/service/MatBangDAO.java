package com.exam_module3.service;

import com.exam_module3.model.MatBang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MatBangDAO implements IMatBangDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/RentalManagement";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";
    private static final Logger LOGGER = Logger.getLogger(MatBangDAO.class.getName());

    private static final String INSERT_MAT_BANG_SQL = "INSERT INTO MatBang (maMatBang, dienTich, tang, loaiMatBang, giaTien, ngayBatDau, ngayKetThuc) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_ALL_MAT_BANG = "SELECT * FROM MatBang ORDER BY dienTich ASC;";
    private static final String DELETE_MAT_BANG_SQL = "DELETE FROM MatBang WHERE maMatBang = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            LOGGER.info("Successfully connected to database");
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.severe("Failed to connect to database: " + e.getMessage());
            throw new RuntimeException("Database connection error", e);
        }
        return connection;
    }

    @Override
    public void addMatBang(MatBang matBang) throws SQLException {
        LOGGER.info("Adding MatBang: " + matBang.getMaMatBang());
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MAT_BANG_SQL)) {
            preparedStatement.setString(1, matBang.getMaMatBang());
            preparedStatement.setDouble(2, matBang.getDienTich());
            preparedStatement.setInt(3, matBang.getTang());
            preparedStatement.setString(4, matBang.getLoaiMatBang());
            preparedStatement.setDouble(5, matBang.getGiaTien());
            preparedStatement.setDate(6, new java.sql.Date(matBang.getNgayBatDau().getTime()));
            preparedStatement.setDate(7, new java.sql.Date(matBang.getNgayKetThuc().getTime()));
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                LOGGER.info("Successfully inserted MatBang: " + matBang.getMaMatBang());
            } else {
                LOGGER.warning("No rows affected when inserting MatBang: " + matBang.getMaMatBang());
                throw new SQLException("Failed to insert MatBang");
            }
        } catch (SQLException e) {
            LOGGER.severe("SQL Error in addMatBang: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<MatBang> selectAllMatBang() throws SQLException {
        List<MatBang> matBangs = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MAT_BANG)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MatBang matBang = new MatBang(
                        resultSet.getString("maMatBang"),
                        resultSet.getDouble("dienTich"),
                        resultSet.getInt("tang"),
                        resultSet.getString("loaiMatBang"),
                        resultSet.getDouble("giaTien"),
                        resultSet.getDate("ngayBatDau"),
                        resultSet.getDate("ngayKetThuc")
                );
                matBangs.add(matBang);
            }
        }
        return matBangs;
    }

    @Override
    public boolean deleteMatBang(String maMatBang) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_MAT_BANG_SQL)) {
            statement.setString(1, maMatBang);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}