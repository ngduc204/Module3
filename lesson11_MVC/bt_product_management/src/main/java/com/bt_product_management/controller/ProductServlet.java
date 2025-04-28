package com.bt_product_management.controller;

import com.bt_product_management.model.Product;
import com.bt_product_management.service.ProductService;
import com.bt_product_management.service.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Nếu không có action, mặc định là hiển thị danh sách
        if (action == null || action.isEmpty()) {
            action = "list";
        }

        switch (action) {
            case "list":
                List<Product> products = productService.getAllProducts();
                request.setAttribute("products", products);
                RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
                dispatcher.forward(request, response);
                break;
            case "delete":
                int idToDelete = Integer.parseInt(request.getParameter("id"));
                productService.deleteProduct(idToDelete);
                response.sendRedirect("products");
                break;
            case "detail":
                int idToView = Integer.parseInt(request.getParameter("id"));
                Product product = productService.getProductById(idToView);
                request.setAttribute("product", product);
                RequestDispatcher detailDispatcher = request.getRequestDispatcher("product-detail.jsp");
                detailDispatcher.forward(request, response);
                break;
            case "search":
                String name = request.getParameter("name");
                List<Product> searchedProducts = productService.searchProducts(name);
                request.setAttribute("products", searchedProducts);
                RequestDispatcher searchDispatcher = request.getRequestDispatcher("product-list.jsp");
                searchDispatcher.forward(request, response);
                break;
            default:
                response.sendRedirect("products");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            String manufacturer = request.getParameter("manufacturer");
            Product product = new Product(0, name, price, description, manufacturer);
            productService.addProduct(product);
            response.sendRedirect("products");
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            String manufacturer = request.getParameter("manufacturer");
            Product product = new Product(id, name, price, description, manufacturer);
            productService.updateProduct(product);
            response.sendRedirect("products");
        } else {
            response.sendRedirect("products");
        }
    }
}