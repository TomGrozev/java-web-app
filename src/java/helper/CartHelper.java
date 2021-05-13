package helper;

import layout.DatabaseDriver;
import model.Cart;
import model.Product;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartHelper {
    public Cart getCart(User user) {
        if (user == null) return null;

        Cart cart = new Cart(user);

        ProductsHelper productsHelper = new ProductsHelper();

        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT id, title, description, products.username as username FROM cart LEFT JOIN products ON cart.product_id=products.id WHERE cart.username = ?");
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = productsHelper.createProductObject(rs);
                cart.addProduct(product);
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }

        return cart;
    }

//    public void createProduct(Product product) {
//        try {
//            Connection conn = (new DatabaseDriver.Database()).getConnection();
//            PreparedStatement ps = conn.prepareStatement("INSERT INTO products(title, description, username) VALUES (?, ?, ?)");
//            ps.setString(1, product.getTitle());
//            ps.setString(2, product.getDescription());
//            ps.setString(3, product.getUser().getUsername());
//            ps.executeUpdate();
//        } catch (SQLException throwables) {
//            throw new RuntimeException(throwables);
//        }
//    }
//
//    public void deleteProduct(int id) {
//        try {
//            Connection conn = (new DatabaseDriver.Database()).getConnection();
//            PreparedStatement ps = conn.prepareStatement("DELETE FROM products WHERE id = ?");
//            ps.setInt(1, id);
//            ps.executeUpdate();
//        } catch (SQLException throwables) {
//            throw new RuntimeException(throwables);
//        }
//    }

//    private Cart createCartObject(ResultSet rs) throws SQLException {
//        Cart cart = new Cart();
//        cart.setTitle(rs.getString("title"));
//        User user = new User(rs.getString("username"));
//        cart.setUser(user);
//        return cart;
//    }
}
