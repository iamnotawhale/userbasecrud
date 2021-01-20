package ru.zhigalin.userbasecrud.service;

import ru.zhigalin.userbasecrud.model.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    void add(User user);
    void delete(User user);
    void edit(User user);
    User getById(int id);
}
