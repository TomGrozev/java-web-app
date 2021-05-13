package model;

import helper.CartHelper;
import helper.ProductsHelper;

import java.util.List;

public class CartService {

    private final CartHelper helper;

    public CartService() {
        helper = new CartHelper();
    }

    public Cart cart(User user) {
        return helper.getCart(user);
    }

//    public Product product(int id) {
//        return helper.getProduct(id);
//    }
//
//    public void createProduct(Product product) {
//        helper.createProduct(product);
//    }
//
//    public void deleteProduct(int id) {
//        helper.deleteProduct(id);
//    }

}
