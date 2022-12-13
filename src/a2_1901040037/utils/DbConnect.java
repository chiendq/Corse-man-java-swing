package a2_1901040037.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/a2_1901040037/database.sqlite3";
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            closeConnection();
        }
        return connection;
    }

    public static void closeConnection(){
        if (connection != null) {
            try {
                connection.close(); // <-- This is important
            } catch (SQLException e) {
                /* handle exception */
                e.printStackTrace();
                System.err.println(e.getMessage());
            }
        }
    }
}
