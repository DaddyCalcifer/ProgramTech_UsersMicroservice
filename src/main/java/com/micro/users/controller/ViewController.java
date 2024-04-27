package com.micro.users.controller;

import com.micro.users.model.AccountDTO;
import com.micro.users.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
public class ViewController {
    private final AccountService accountService;
    @Autowired
    public ViewController(AccountService accountService) {
        this.accountService = accountService;
    }
    @GetMapping("/user/{id}")
    public String userId(Model model,@PathVariable UUID id) {
        AccountDTO user = accountService.getUserById(id).get();
        model.addAttribute("surname", user.getSurname());
        model.addAttribute("name", user.getFirstName());
        model.addAttribute("patron", user.getPatron());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("createdAt", user.getCreatedAt().toString());
        return "index";
    }
    @GetMapping("/profile")
    public String userId(Model model) {
        return "index";
    }
}
