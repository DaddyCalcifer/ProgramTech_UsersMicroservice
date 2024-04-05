package com.micro.users.service;

import com.micro.users.model.AccountDTO;
import com.micro.users.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    // Create a new user
    public AccountDTO createUser(AccountDTO user) {
        return accountRepository.save(user);
    }

    // Get all users
    public List<AccountDTO> getAllUsers() {
        return accountRepository.findAll();
    }

    // Get user by ID
    public Optional<AccountDTO> getUserById(Long id) {
        return accountRepository.findById(id);
    }

    // Update user
    public AccountDTO updateUser(Long id, AccountDTO userDetails) {
        Optional<AccountDTO> user = accountRepository.findById(id);
        if (user.isPresent()) {
            AccountDTO existingUser = user.get();
            existingUser.setFirstName(userDetails.getFirstName());
            existingUser.setLastName(userDetails.getLastName());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setSurname(userDetails.getSurname());
            existingUser.setPasswordHash(userDetails.getPasswordHash());
            existingUser.setRole(userDetails.getRole());
            existingUser.setActivityStatus(userDetails.getActivityStatus());
            existingUser.setDeleted(userDetails.isDeleted());
            existingUser.setCurrentEvent(userDetails.getCurrentEvent());
            existingUser.setCreatedAt(userDetails.getCreatedAt());
            existingUser.setUpdatedAt(userDetails.getUpdatedAt());
            return accountRepository.save(existingUser);
        }
        return null;
    }

    // Delete all users
    public void deleteAllUsers() {
        accountRepository.deleteAll();
    }

    // Delete user
    public void deleteUser(Long id) {
        accountRepository.deleteById(id);
    }
}
