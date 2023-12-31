package jm.task.core.jdbc;

//import static jm.task.core.jdbc.util.Util.createSchema;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import static jm.task.core.jdbc.util.Util.getConnection;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alex", "Zhuk", (byte) 32);
        userService.saveUser("Boris", "Petrov", (byte) 25);
        userService.saveUser("Moris", "Metrov", (byte) 45);
        userService.saveUser("Alexsis", "Zhukov", (byte) 18);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
