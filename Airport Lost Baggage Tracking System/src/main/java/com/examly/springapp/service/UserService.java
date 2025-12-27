package com.examly.springapp.service;

import com.examly.springapp.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final Map<Long, User> users = new LinkedHashMap<>();
    private long id = 1;

    public User addUser(User user) {
        user.setUserId(id);
        users.put(id, user);
        id++;
        return user;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User getUserById(Long id) {
        return users.get(id);
    }

    public User updateUser(Long id, User user) {
        User existing = users.get(id);
        if (existing != null) {
            existing.setUsername(user.getUsername());
            existing.setEmail(user.getEmail());
            existing.setRole(user.getRole());
            existing.setFullName(user.getFullName());
        }
        return existing;
    }

    public List<User> getUsersByRole(String role) {
        List<User> result = new ArrayList<>();
        for (User user : users.values()) {
            if (user.getRole() != null && user.getRole().equals(role)) {
                result.add(user);
            }
        }
        return result;
    }

    public User getUserByEmail(String email) {
        for (User user : users.values()) {
            if (user.getEmail() != null && user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}
