package helper;

import layout.DatabaseDriver;
import model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersHelper {
    public boolean userExists(User user) {
        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT username FROM users WHERE username = ?");
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    public void register(User user) {
        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users(username, password) VALUES (?, ?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    public User authenticate(String username, String password) {
        try {
            Connection conn = (new DatabaseDriver.Database()).getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return createUserObject(rs);
            }
            return null;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    private User createUserObject(ResultSet rs) throws SQLException {
        return new User(rs.getString("username"), rs.getString("password"));
    }
}
