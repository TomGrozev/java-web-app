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

    public void addProduct(User user, int product_id) {
        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO cart(product_id, username) VALUES (?, ?)");
            ps.setInt(1, product_id);
            ps.setString(2, user.getUsername());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    public void removeProduct(User user, int id) {
        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM cart WHERE product_id = ? AND username = ?");
            ps.setInt(1, id);
            ps.setString(2, user.getUsername());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

//    private Cart createCartObject(ResultSet rs) throws SQLException {
//        Cart cart = new Cart();
//        cart.setTitle(rs.getString("title"));
//        User user = new User(rs.getString("username"));
//        cart.setUser(user);
//        return cart;
//    }
}
