package codingpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static ThreadLocal<Connection> connectionThreadLocal = ThreadLocal.withInitial(() -> {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "user", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    });

    public static Connection getConnection() {
        return connectionThreadLocal.get();
    }

    public static void closeConnection() {
        try {
            connectionThreadLocal.get().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionThreadLocal.remove();
        }
    }
}
