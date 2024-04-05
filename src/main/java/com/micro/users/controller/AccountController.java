package com.micro.users.controller;

import com.micro.users.model.AccountDTO;
import com.micro.users.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    private final AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<AccountDTO> getUserById(@PathVariable Long id) {
        Optional<AccountDTO> userOptional = accountService.getUserById(id);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/users")
    public ResponseEntity<List<AccountDTO>> getAllUsers() {
        List<AccountDTO> users = accountService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(users);
        }
    }
    @PostMapping("/users/add")
    public ResponseEntity<AccountDTO> createUser(@RequestBody AccountDTO user) {
        AccountDTO createdUser = accountService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    @PostMapping("/users/delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        accountService.deleteUser(id);
        return "deleted";
    }

}