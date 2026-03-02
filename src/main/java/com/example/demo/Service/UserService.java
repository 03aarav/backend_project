package com.example.demo.Service;

import com.example.demo.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(String id);
    Optional<User> getUserByEmail(String email);  // Added
    List<User> getAllUsers();
    User updateUser(String id, User updatedUser);
    void deleteUser(String id);
    boolean existsByEmail(String email);
    Optional<User> findByPhoneNo(String phoneNo);// Added
}


