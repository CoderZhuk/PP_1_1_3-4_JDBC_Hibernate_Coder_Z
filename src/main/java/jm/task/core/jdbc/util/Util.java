package jm.task.core.jdbc.util;

import org.hibernate.HibernateException;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import jm.task.core.jdbc.model.User;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
        private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
        private static final String URL = "jdbc:mysql://localhost:3306/my_schema";
        private static final String USERNAME = "admin";
        private static final String PASSWORD = "root";

        private static SessionFactory sessionFactory;

        public static Connection getConnection() {
            Connection connection = null;
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
            e.getStackTrace();
            }
            return connection;
        }
        public static org.hibernate.SessionFactory getSessionFactory() {
            if (sessionFactory == null) {
                try {
                    Configuration configuration = new Configuration()
                            .setProperty("hibernate.connection.driver_class", DRIVER)
                            .setProperty("hibernate.connection.url", URL)
                            .setProperty("hibernate.connection.username", USERNAME)
                            .setProperty("hibernate.connection.password", PASSWORD)
                            .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                            .addAnnotatedClass(User.class);

                    configuration.addAnnotatedClass(User.class);

                    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties()).build();

                    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
            return sessionFactory;
        }
//        public void closeConnection(Connection connection) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.getStackTrace();
//            }
//        }
    }



