package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;
    private User user;

    public Cart(User user) {
        this.products = new ArrayList<>();
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
