package com.bugtracker.BugTracker.controller;

import com.bugtracker.BugTracker.dto.PasswordUpdateRequest;
import com.bugtracker.BugTracker.dto.UserRegistrationRequest;
import com.bugtracker.BugTracker.dto.UserUpdateRequest;
import com.bugtracker.BugTracker.entity.User;
import com.bugtracker.BugTracker.enums.Role;
import com.bugtracker.BugTracker.repository.UserRepository;
import com.bugtracker.BugTracker.service.UserAlreadyExistsException;
import com.bugtracker.BugTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationRequest request) throws UserAlreadyExistsException {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole() != null ? request.getRole() : Role.USER);

        User createdUser = userService.createUser(user);
        // Remove password from response
        createdUser.setPassword(null);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        // Remove passwords from response
        users.forEach(user -> user.setPassword(null));
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        user.setPassword(null); // Remove password from response
        return ResponseEntity.ok(user);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        user.setPassword(null); // Remove password from response
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        user.setPassword(null); // Remove password from response
        return ResponseEntity.ok(user);
    }

    @GetMapping("/exists/username/{username}")
    public ResponseEntity<Boolean> checkUsernameExists(@PathVariable String username) {
        boolean exists = userService.existsByUsername(username);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> checkEmailExists(@PathVariable String email) {
        boolean exists = userService.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }

//    @GetMapping("/profile")
//    public ResponseEntity<User> getCurrentUserProfile(Principal principal) {
//        User user = userService.getUserByUsername(principal.getName());
//        user.setPassword(null); // Remove password from response
//        return ResponseEntity.ok(user);
//    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable String userId, @RequestBody UserUpdateRequest request) throws UserAlreadyExistsException {

        User updateData = new User();
        updateData.setUsername(request.getUsername());
        updateData.setEmail(request.getEmail());
        updateData.setRole(request.getRole());

        User updatedUser = userService.updateUser(userId, updateData);
        updatedUser.setPassword(null); // Remove password from response

        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/{userId}/password")
    public ResponseEntity<Void> updatePassword(
            @PathVariable String userId,
            @RequestBody PasswordUpdateRequest request) {

        return ResponseEntity.ok().build();
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable Role role) {
        List<User> users = userService.getUsersByRole(role);
        // Remove passwords from response
        users.forEach(user -> user.setPassword(null));
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
