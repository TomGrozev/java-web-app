package model;

import helper.CartHelper;

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

    public boolean addProduct(User user, int product_id) {
        return helper.addProduct(user, product_id);
    }

    public void removeProduct(User user, int id) {
        helper.removeProduct(user, id);
    }

}
