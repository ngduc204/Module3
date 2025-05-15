package com.exam_module3.controller;

import com.exam_module3.model.MatBang;
import com.exam_module3.service.MatBangDAO;

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

@WebServlet("/matbang")
public class MatBangServlet extends HttpServlet {
    private MatBangDAO matBangDAO;

    @Override
    public void init() throws ServletException {
        matBangDAO = new MatBangDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("create".equals(action)) {
            request.getRequestDispatcher("/view/create.jsp").forward(request, response);
        } else {
            try {
                List<MatBang> matBangList = matBangDAO.selectAllMatBang();
                request.setAttribute("matBangList", matBangList);
                request.getRequestDispatcher("/view/list.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Có lỗi xảy ra khi lấy dữ liệu mặt bằng.");
                request.getRequestDispatcher("/view/error.jsp").forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            String maMatBang = request.getParameter("maMatBang");
            try {
                if (matBangDAO.deleteMatBang(maMatBang)) {
                    response.sendRedirect("matbang");
                } else {
                    request.setAttribute("errorMessage", "Không tìm thấy mặt bằng với mã: " + maMatBang);
                    request.getRequestDispatcher("/view/error.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Có lỗi xảy ra khi xóa mặt bằng.");
                request.getRequestDispatcher("/view/error.jsp").forward(request, response);
            }
        } else {
            String maMatBang = request.getParameter("maMatBang");
            double dienTich = Double.parseDouble(request.getParameter("dienTich"));
            int tang = Integer.parseInt(request.getParameter("tang"));
            String loaiMatBang = request.getParameter("loaiMatBang");
            double giaTien = Double.parseDouble(request.getParameter("giaTien"));

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date ngayBatDau;
            Date ngayKetThuc;

            try {
                ngayBatDau = formatter.parse(request.getParameter("ngayBatDau"));
                ngayKetThuc = formatter.parse(request.getParameter("ngayKetThuc"));
            } catch (ParseException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Định dạng ngày không hợp lệ. Vui lòng sử dụng dd/MM/yyyy.");
                request.getRequestDispatcher("/view/error.jsp").forward(request, response);
                return;
            }

            try {
                matBangDAO.addMatBang(new MatBang(maMatBang, dienTich, tang, loaiMatBang, giaTien, ngayBatDau, ngayKetThuc));
                response.sendRedirect("matbang");
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Có lỗi xảy ra khi thêm mặt bằng.");
                request.getRequestDispatcher("/view/error.jsp").forward(request, response);
            }
        }
    }
}