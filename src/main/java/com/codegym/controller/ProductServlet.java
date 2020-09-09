package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet",urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;

    public void init(){
        productDAO = new ProductDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action){
            case "add":
                addNewProduct(request,response);
                break;
            case "edit":
                editProductById(request,response);
            default:
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action){
            case "add":
                showAddProductForm(request,response);
                break;
            case "search":
                searchProductsByName(request,response);
                break;
            case "edit":
                showEditProductForm(request,response);
                break;
            case "delete":
                deleteProduct(request,response);
                break;
            default:
                showAllProducts(request,response);
                break;
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDAO.deleteProduct(id);

        List<Product> products = productDAO.selectAllProducts();

        List<String> categories = new ArrayList<>();
        for (Product product : products) {
            categories.add(productDAO.selectCategoryById(product.getCategory_id()));
        }

        request.setAttribute("products",products);
        request.setAttribute("categories",categories);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/list.jsp");
        dispatcher.forward(request,response);
    }

    private void editProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        List<String> list_categories = productDAO.selectAllCategories();
        int category_id = list_categories.indexOf(category)+1;
        Product updateProduct = new Product(id,name,price,quantity,color,description,category_id);
        productDAO.updateProduct(updateProduct);

        List<Product> products = productDAO.selectAllProducts();

        List<String> categories = new ArrayList<>();
        for (Product product : products) {
            categories.add(productDAO.selectCategoryById(product.getCategory_id()));
        }

        request.setAttribute("products",products);
        request.setAttribute("categories",categories);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/list.jsp");
        dispatcher.forward(request,response);
    }

    private void showEditProductForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.selectProductById(id);

        request.setAttribute("product",product);

        List<Integer> category_ids = productDAO.selectAllCategoryIds();
        List<String> categories = new ArrayList<>();
        for (Integer category_id : category_ids) {
            categories.add(productDAO.selectCategoryById(category_id));
        }
        request.setAttribute("categories",categories);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/edit.jsp");
        dispatcher.forward(request,response);
    }

    private void addNewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        List<String> list_categories = productDAO.selectAllCategories();
        int category_id = list_categories.indexOf(category)+1;
        Product newProduct = new Product(name,price,quantity,color,description,category_id);
        productDAO.insertProduct(newProduct);

        List<Product> products = productDAO.selectAllProducts();

        List<String> categories = new ArrayList<>();
        for (Product product : products) {
            categories.add(productDAO.selectCategoryById(product.getCategory_id()));
        }

        request.setAttribute("products",products);
        request.setAttribute("categories",categories);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/list.jsp");
        dispatcher.forward(request,response);
    }

    private void showAddProductForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Integer> category_ids = productDAO.selectAllCategoryIds();
        List<String> categories = new ArrayList<>();
        for (Integer id : category_ids) {
            categories.add(productDAO.selectCategoryById(id));
        }
        request.setAttribute("categories",categories);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/create.jsp");
        dispatcher.forward(request,response);
    }

    private void searchProductsByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        List<Product> products = productDAO.selectProductByName(name);

        List<String> categories = new ArrayList<>();
        for (Product product : products) {
            categories.add(productDAO.selectCategoryById(product.getCategory_id()));
        }

        request.setAttribute("products",products);
        request.setAttribute("categories",categories);
        request.setAttribute("message","search result for " + name);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/list.jsp");
        dispatcher.forward(request,response);
    }

    private void showAllProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productDAO.selectAllProducts();

        List<String> categories = new ArrayList<>();
        for (Product product : products) {
            categories.add(productDAO.selectCategoryById(product.getCategory_id()));
        }

        request.setAttribute("products",products);
        request.setAttribute("categories",categories);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/list.jsp");
        dispatcher.forward(request,response);
    }
}
