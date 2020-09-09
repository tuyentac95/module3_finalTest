package com.codegym.service;

import com.codegym.model.Product;

import java.util.List;

public interface iProductDAO {
    public List<Product> selectAllProducts();

    public Product selectProductById(int id);

    public void insertProduct(Product product);

    public boolean updateProduct(Product product);

    public boolean deleteProduct(int id);

    public List<Product> selectProductByName(String name);
}
