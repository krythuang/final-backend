package com.kevin.yanivcounter.service;

import com.kevin.yanivcounter.model.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user);
    public List<User> getAllUsers();
    public User getUserById(int userId);
    public void deleteUser(int userId);
}
