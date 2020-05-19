package ru.geekbrains.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.geekbrains.server.auth.AuthService;
import ru.geekbrains.server.auth.AuthServiceJdbcImpl;
import ru.geekbrains.server.persistance.UserRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class SpringContext {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/network_chat?useUnicode=true&serverTimezone=UTC");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("alex54");
        return ds;
    }

    @Bean
    public UserRepository userRepository(DataSource dataSource) throws SQLException {
        return new UserRepository(dataSource);
    }

    @Bean
    public AuthService authService(UserRepository userRepository) throws SQLException {
        return new AuthServiceJdbcImpl(userRepository);
    }

    @Bean
    public ChatServer chatServer(AuthService authService) throws SQLException {
        return new ChatServer(authService);
    }
}
