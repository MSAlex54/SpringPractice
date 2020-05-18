package ru.geekbrains.server;

import ru.geekbrains.server.auth.AuthService;
import ru.geekbrains.server.auth.AuthServiceJdbcImpl;
import ru.geekbrains.server.persistance.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ChatServerApplication {
    public static void main(String[] args) throws SQLException {
//        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/network_chat?useUnicode=true&serverTimezone=UTC","root", "alex54");
//        UserRepository userRepository = new UserRepository(conn);
//        if (userRepository.getAllUsers().size() == 0) {
//            userRepository.insert(new User(-1, "ivan", "123"));
//            userRepository.insert(new User(-1, "petr", "345"));
//            userRepository.insert(new User(-1, "julia", "789"));
//        }
//        AuthService auth = new AuthServiceJdbcImpl(userRepository);
//        ChatServer chatServer = new ChatServer(auth);
        chatServer.start(7777);
    }
}
