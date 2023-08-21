package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = getConnection()) {
            String query = "CREATE TABLE IF NOT EXISTS user (id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    " name VARCHAR(20) NOT NULL, lastName VARCHAR(20) NOT NULL, age TINYINT NOT NULL)";
            connection.createStatement().executeUpdate(query);
            System.out.println("Table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS user");
            preparedStatement.execute();
            System.out.println("Table deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (name, lastname, age) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE user WHERE id = (?)");
            preparedStatement.setLong(1, id);
            System.out.println("user deleted" + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user");
            preparedStatement.execute();
            ResultSet resultSet =preparedStatement.getResultSet();
            while (resultSet.next() && !resultSet.wasNull()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("TRUNCATE table user");
            statement.execute();
            System.out.println("table clear");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
