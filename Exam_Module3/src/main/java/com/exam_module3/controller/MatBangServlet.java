package com.exam_module3.controller;

import com.exam_module3.model.MatBang;
import com.exam_module3.service.MatBangDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/matbang")
public class MatBangServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MatBangDAO matBangDAO;
    private static final Logger LOGGER = Logger.getLogger(MatBangServlet.class.getName());

    @Override
    public void init() {
        matBangDAO = new MatBangDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertMatBang(request, response);
                    break;
                default:
                    listMatBangs(request, response);
                    break;
            }
        } catch (Exception ex) {
            LOGGER.severe("Error in doPost: " + ex.getMessage());
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "delete":
                    deleteMatBang(request, response);
                    break;
                default:
                    listMatBangs(request, response);
                    break;
            }
        } catch (SQLException ex) {
            LOGGER.severe("SQL Error in doGet: " + ex.getMessage());
            throw new ServletException(ex);
        } catch (Exception e) {
            LOGGER.severe("General Error in doGet: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void listMatBangs(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<MatBang> matBangList = matBangDAO.selectAllMatBang();
        request.setAttribute("matBangList", matBangList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/create.jsp");
        dispatcher.forward(request, response);
    }

    private void insertMatBang(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String maMatBang = request.getParameter("maMatBang");
        LOGGER.info("Processing insertMatBang with maMatBang: " + maMatBang);

        // Kiểm tra định dạng maMatBang
        if (maMatBang == null || !maMatBang.matches("MB[0-9]{3}")) {
            request.setAttribute("errorMessage", "Mã mặt bằng phải có định dạng MBxxx (ví dụ: MB001).");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/create.jsp");
            dispatcher.forward(request, response);
            return;
        }

        double dienTich;
        int tang;
        double giaTien;

        try {
            dienTich = Double.parseDouble(request.getParameter("dienTich"));
            tang = Integer.parseInt(request.getParameter("tang"));
            giaTien = Double.parseDouble(request.getParameter("giaTien"));
        } catch (NumberFormatException e) {
            LOGGER.warning("Invalid number format: " + e.getMessage());
            request.setAttribute("errorMessage", "Dữ liệu số không hợp lệ (diện tích, tầng, hoặc giá tiền).");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/create.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String loaiMatBang = request.getParameter("loaiMatBang");
        if (loaiMatBang == null || loaiMatBang.isEmpty()) {
            request.setAttribute("errorMessage", "Vui lòng chọn loại mặt bằng.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/create.jsp");
            dispatcher.forward(request, response);
            return;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date ngayBatDau;
        Date ngayKetThuc;

        try {
            ngayBatDau = formatter.parse(request.getParameter("ngayBatDau"));
            ngayKetThuc = formatter.parse(request.getParameter("ngayKetThuc"));
        } catch (ParseException e) {
            LOGGER.warning("Invalid date format: " + e.getMessage());
            request.setAttribute("errorMessage", "Định dạng ngày không hợp lệ. Vui lòng sử dụng dd/MM/yyyy.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/create.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            MatBang newMatBang = new MatBang(maMatBang, dienTich, tang, loaiMatBang, giaTien, ngayBatDau, ngayKetThuc);
            matBangDAO.addMatBang(newMatBang);
            LOGGER.info("Successfully added MatBang: " + maMatBang);
            listMatBangs(request, response); // Chuyển hướng đến danh sách sau khi thêm thành công
        } catch (SQLException e) {
            LOGGER.severe("SQL Error in insertMatBang: " + e.getMessage());
            request.setAttribute("errorMessage", "Lỗi khi thêm mặt bằng: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/create.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void deleteMatBang(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String maMatBang = request.getParameter("maMatBang");
        boolean deleted = matBangDAO.deleteMatBang(maMatBang);
        if (!deleted) {
            request.setAttribute("errorMessage", "Không tìm thấy mặt bằng với mã: " + maMatBang);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/error.jsp");
            dispatcher.forward(request, response);
            return;
        }
        listMatBangs(request, response);
    }
}