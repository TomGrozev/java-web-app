package helper;

import layout.DatabaseDriver;
import model.Product;
import model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsHelper {
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM products ORDER BY id DESC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(createProductObject(rs));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }

        return products;
    }

    public List<Product> getUserProducts(User user) {
        List<Product> products = new ArrayList<>();

        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE username = ? ORDER BY id DESC");
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(createProductObject(rs));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }

        return products;
    }

    public Product getProduct(int id) {
        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE id = ? ORDER BY id DESC");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = createProductObject(rs);
                return product;
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }

        return null;
    }

    public void createProduct(Product product) {
        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO products(title, description, price, username) VALUES (?, ?, ?, ?)");
            ps.setString(1, product.getTitle());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getUser().getUsername());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    public void editProduct(Product product) {
        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE products SET title = ?, description = ?, price = ? WHERE id = ?");
            ps.setString(1, product.getTitle());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    public void deleteProduct(int id) {
        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM products WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    public Product createProductObject(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setTitle(rs.getString("title"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getDouble("price"));
        User user = new User(rs.getString("username"));
        product.setUser(user);
        return product;
    }
}
