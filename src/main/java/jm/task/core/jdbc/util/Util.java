package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class Util {
        private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
        private static final String URL = "jdbc:mysql://localhost:3306/my_Schema";
        private static final String USERNAME = "admin";
        private static final String PASSWORD = "root";

        public static Connection getConnection() {
            Connection connection = null;
            try {
                Class.forName(DB_DRIVER);
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
            e.getStackTrace();
            }
            return connection;
        }
        public void closeConnection(Connection connection) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }
    }



