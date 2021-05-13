package model;

import helper.ProductsHelper;

import java.util.List;

public class ProductsService {

    private final ProductsHelper helper;

    public ProductsService() {
        helper = new ProductsHelper();
    }

    public List<Product> products() {
        return helper.getProducts();
    }

    public Product product(int id) {
        return helper.getProduct(id);
    }

    public void createProduct(Product product) {
        helper.createProduct(product);
    }

    public void editProduct(Product product) {
        helper.editProduct(product);
    }

    public void deleteProduct(int id) {
        helper.deleteProduct(id);
    }

}
