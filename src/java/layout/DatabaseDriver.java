package layout;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseDriver {

    private static Connection connection = null;

    public static class Database {
        public Connection getConnection() {
            if (connection == null) {
                try {
                    Context context = new InitialContext();
                    DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/myDataSource");
                    connection = ds.getConnection();
                } catch (NamingException | SQLException e) {
                    e.printStackTrace();
                }
            }
            return connection;
        }
    }
}
