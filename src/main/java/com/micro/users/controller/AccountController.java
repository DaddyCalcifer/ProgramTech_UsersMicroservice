package com.micro.users.controller;

import com.micro.users.model.AccountDTO;
import com.micro.users.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AccountController {
    private final AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<AccountDTO> getUserById(@PathVariable UUID id) {
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
    @GetMapping("/users:role/{id}")
    public ResponseEntity<Long> getUserRoleById(@PathVariable UUID id) {
        Optional<Long> user_roleOptional = accountService.getUserRoleById(id);
        return user_roleOptional.map(role -> new ResponseEntity<>(role, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/users/add")
    public ResponseEntity<AccountDTO> createUser(@RequestBody AccountDTO user) {
        AccountDTO createdUser = accountService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    @PatchMapping("/users/delete/{id}")
    public ResponseEntity<AccountDTO> deleteUserById(@PathVariable UUID id) {
        if(accountService.deleteUser(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.getUserById(id).get());
        }
        else return ResponseEntity.notFound().build();
    }
    @PatchMapping("/users/recover/{id}")
    public ResponseEntity<AccountDTO> recoverUserById(@PathVariable UUID id) {
        if(accountService.recoverUser(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.getUserById(id).get());
        }
        else return ResponseEntity.notFound().build();
    }
}