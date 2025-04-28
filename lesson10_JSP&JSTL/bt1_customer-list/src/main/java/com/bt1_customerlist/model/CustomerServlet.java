package com.bt1_customerlist.model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Trịnh Thu Liên", "1999-11-27", "Hải Dương", "https://smilemedia.vn/wp-content/uploads/2022/09/cach-chup-anh-the-dep-e1664379835782.jpg"));
        customers.add(new Customer("Lê Sơn Nhất", "1997-09-25", "Hà Nội", "https://bffmedia.vn/wp-content/uploads/2021/05/chup-anh-the-4.jpg"));
        customers.add(new Customer("Hoàng Thu Trang", "2000-10-15", "Hà Nội", "https://inkythuatso.com/uploads/thumbnails/800/2023/02/4-anh-the-nu-nen-trang-inkythuatso-27-10-37-10.jpg"));
        customers.add(new Customer("Phạm Mai Hương", "1996-03-18", "Hải Phòng", "https://chupanhthe.vn/img/chup-anh-the-chuan-quoc-te1.jpg"));

        request.setAttribute("customers", customers);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
