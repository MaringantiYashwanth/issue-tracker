package com.bugtracker.BugTracker.service;

import com.bugtracker.BugTracker.entity.User;
import com.bugtracker.BugTracker.enums.Role;
import com.bugtracker.BugTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) throws UserAlreadyExistsException {
        // Check if username already exists
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username '" + user.getUsername() + "' already exists");
        }
        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email '" + user.getEmail() + "' already exists");
        }

        user.setCreatedAt(LocalDateTime.now());

        // Set default role if not specified
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }

        return userRepository.save(user);
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User updateUser(String userId, User updateData) throws UserAlreadyExistsException {
        User existingUser = getUserById(userId);

        // Update username if provided and not already taken
        if (updateData.getUsername() != null && !updateData.getUsername().equals(existingUser.getUsername())) {
            if (existsByUsername(updateData.getUsername())) {
                throw new UserAlreadyExistsException("Username '" + updateData.getUsername() + "' already exists");
            }
            existingUser.setUsername(updateData.getUsername());
        }

        // Update email if provided and not already taken
        if (updateData.getEmail() != null && !updateData.getEmail().equals(existingUser.getEmail())) {
            if (existsByEmail(updateData.getEmail())) {
                throw new UserAlreadyExistsException("Email '" + updateData.getEmail() + "' already exists");
            }
            existingUser.setEmail(updateData.getEmail());
        }

        // Update role if provided
        if (updateData.getRole() != null) {
            existingUser.setRole(updateData.getRole());
        }

        return userRepository.save(existingUser);
    }

    public void deleteUser(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("Cannot delete user. User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }

    public List<User> getUsersByRole(Role role) {
        // Note: This would require adding this method to UserRepository
        // For now, we'll filter in service layer (not ideal for large datasets)
        return userRepository.findAll().stream()
                .filter(user -> user.getRole() == role)
                .toList();
    }
}
