package helper;

import layout.DatabaseDriver;
import model.Cart;
import model.Product;
import model.User;
import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;

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
            PreparedStatement ps = conn.prepareStatement("SELECT id, title, description, price, products.username as username FROM cart LEFT JOIN products ON cart.product_id=products.id WHERE cart.username = ?");
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

    public boolean addProduct(User user, int product_id) {
        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO cart(product_id, username) VALUES (?, ?)");
            ps.setInt(1, product_id);
            ps.setString(2, user.getUsername());
            ps.executeUpdate();
        } catch (DerbySQLIntegrityConstraintViolationException e) {
            return false;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }

        return true;
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
}
