package back.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static String url = "jdbc:mysql://localhost:3306/BookstorCZ";
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String username = "root";
    private static String password = "root";
    private static Connection con;
    private static String urlstring;

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database back.util.");
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return con;
    }
}