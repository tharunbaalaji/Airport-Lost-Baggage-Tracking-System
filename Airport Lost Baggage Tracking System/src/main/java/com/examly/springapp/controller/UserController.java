package com.examly.springapp.controller;

import com.examly.springapp.model.User;
import com.examly.springapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping({"/users", "/api/users"})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
}
 @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @GetMapping("/page/{page}/{size}")
    public Map<String, Object> getUsersWithPagination(@PathVariable int page, @PathVariable int size) {
        Map<String, Object> sort = new HashMap<>();
        sort.put("sorted", false);

        Map<String, Object> pageable = new HashMap<>();
        pageable.put("pageNumber", page);
        pageable.put("pageSize", size);
        pageable.put("sort", sort);

        Map<String, Object> response = new HashMap<>();
        response.put("content", userService.getAllUsers());
        response.put("totalElements", userService.getAllUsers().size());
        response.put("totalPages", 1);
        response.put("pageable", pageable);

        return response;
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<?> getUsersByRole(@PathVariable String role) {
        List<User> users = userService.getUsersByRole(role);
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No users found with role: " + role);
        }
        return ResponseEntity.ok(users);
}
@GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with email: " + email);
        }
        return ResponseEntity.ok(user);
    }
}