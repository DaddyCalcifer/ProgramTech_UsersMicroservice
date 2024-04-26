package com.micro.users.service;

import com.micro.users.model.AccountDTO;
import com.micro.users.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountDTO createUser(AccountDTO user) {
        return accountRepository.save(user);
    }

    public List<AccountDTO> getAllUsers() {
        return accountRepository.findAll();
    }
    public Page<AccountDTO> getAllUsers(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    public Optional<AccountDTO> getUserById(UUID id) {
        return accountRepository.findById(id);
    }

    public AccountDTO updateUser(UUID id, AccountDTO userDetails) {
        Optional<AccountDTO> user = accountRepository.findById(id);
        if (user.isPresent()) {
            AccountDTO existingUser = user.get();
            existingUser.setFirstName(userDetails.getFirstName());
            existingUser.setLastName(userDetails.getLastName());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setSurname(userDetails.getSurname());
            existingUser.setPasswordHash(userDetails.getPasswordHash());
            existingUser.setDeleted(userDetails.isDeleted());
            existingUser.setCreatedAt(userDetails.getCreatedAt());
            existingUser.setUpdatedAt(userDetails.getUpdatedAt());
            return accountRepository.save(existingUser);
        }
        return null;
    }

    public void deleteAllUsers() {
        accountRepository.deleteAll();
    }

    public boolean deleteUser(UUID id) {
        try {
            accountRepository.deleteSoftById(id);
        }catch (Exception ex){
            System.out.println("Request error: " + ex.getMessage());
            return false;
        }
        accountRepository.setUpdatedAtById(id,LocalDateTime.now());
        return true;
    }
    public boolean recoverUser(UUID id) {
        try {
            accountRepository.recoverUserById(id);
        }catch (Exception ex){
            System.out.println("Request error: " + ex.getMessage());
            return false;
        }
        accountRepository.setUpdatedAtById(id,LocalDateTime.now());
        return true;
    }
}
