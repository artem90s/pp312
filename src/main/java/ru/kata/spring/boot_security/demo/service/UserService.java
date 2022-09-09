package ru.kata.spring.boot_security.demo.service;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


@Component
public interface UserService {
    public boolean saveUser(User user);
    public boolean deleteUser(User user);
    public User updateUser(User user);
    public User getUser(Long id);

    public List<User> usersList();
    User findByUsername(String username);


}
