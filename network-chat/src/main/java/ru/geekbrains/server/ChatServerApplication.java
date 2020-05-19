package ru.geekbrains.server;

import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringContext.class); //Java-configuration
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");// xml-configuration + annotation-configuration - uncomment to use

        ChatServer chatServer =  context.getBean("chatServer",ChatServer.class);
        chatServer.start(7777);
    }
}
