package com.bt_product_management.service;

import com.bt_product_management.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private List<Product> products = new ArrayList<>();
    private int currentId = 1;

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public void addProduct(Product product) {
        product.setId(currentId++);
        products.add(product);
    }

    @Override
    public void updateProduct(Product product) {
        for (Product p : products) {
            if (p.getId() == product.getId()) {
                p.setName(product.getName());
                p.setPrice(product.getPrice());
                p.setDescription(product.getDescription());
                p.setManufacturer(product.getManufacturer());
                break;
            }
        }
    }

    @Override
    public void deleteProduct(int id) {
        products.removeIf(product -> product.getId() == id);
    }

    @Override
    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> searchProducts(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }
}