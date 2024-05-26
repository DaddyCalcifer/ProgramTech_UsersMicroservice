package com.micro.users;

import com.micro.users.controller.AccountController;
import com.micro.users.model.AccountDTO;
import com.micro.users.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class AccountControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void testGetUserById() throws Exception {
        UUID userId = UUID.randomUUID();
        AccountDTO account = new AccountDTO();
        account.setId(userId);
        account.setFirstName("John");
        account.setSurname("Doe");

        when(accountService.getUserById(userId)).thenReturn(Optional.of(account));

        mockMvc.perform(get("/api/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId.toString()))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.surname").value("Doe"));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        AccountDTO account1 = new AccountDTO();
        account1.setId(UUID.randomUUID());
        account1.setFirstName("John");
        account1.setSurname("Doe");

        AccountDTO account2 = new AccountDTO();
        account2.setId(UUID.randomUUID());
        account2.setFirstName("Jane");
        account2.setSurname("Smith");

        Pageable pageable = PageRequest.of(0, 10);
        Page<AccountDTO> page = new PageImpl<>(Arrays.asList(account1, account2), pageable, 2);

        when(accountService.getAllUsers(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/users")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"));
    }

    @Test
    public void testCreateUser() throws Exception {
        AccountDTO account = new AccountDTO();
        account.setId(UUID.randomUUID());
        account.setFirstName("John");
        account.setSurname("Doe");

        when(accountService.createUser(any(AccountDTO.class))).thenReturn(account);

        mockMvc.perform(post("/api/users/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\", \"surname\":\"Doe\", \"lastName\":\"Smith\", \"email\":\"john.doe@example.com\", \"passwordHash\":\"password\", \"role\":1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.surname").value("Doe"));
    }

    @Test
    public void testDeleteUserById() throws Exception {
        UUID userId = UUID.randomUUID();
        AccountDTO account = new AccountDTO();
        account.setId(userId);
        account.setFirstName("John");
        account.setSurname("Doe");

        when(accountService.deleteUser(userId)).thenReturn(true);
        when(accountService.getUserById(userId)).thenReturn(Optional.of(account));

        mockMvc.perform(patch("/api/users/delete/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId.toString()));
    }

    @Test
    public void testRecoverUserById() throws Exception {
        UUID userId = UUID.randomUUID();
        AccountDTO account = new AccountDTO();
        account.setId(userId);
        account.setFirstName("John");
        account.setSurname("Doe");

        when(accountService.recoverUser(userId)).thenReturn(true);
        when(accountService.getUserById(userId)).thenReturn(Optional.of(account));

        mockMvc.perform(patch("/api/users/recover/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId.toString()));
    }

    @Test
    public void testUpdateUser() throws Exception {
        UUID userId = UUID.randomUUID();
        AccountDTO account = new AccountDTO();
        account.setId(userId);
        account.setFirstName("John");
        account.setSurname("Doe");

        when(accountService.getUserById(userId)).thenReturn(Optional.of(account));
        when(accountService.updateUser(any(UUID.class), any(AccountDTO.class))).thenReturn(account);

        mockMvc.perform(put("/api/users/update/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\", \"surname\":\"Doe\", \"lastName\":\"Smith\", \"email\":\"john.doe@example.com\", \"passwordHash\":\"password\", \"role\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.surname").value("Doe"));
    }

    @Test
    public void testGetUsersCount() throws Exception {
        when(accountService.getUsersCount()).thenReturn(10);

        mockMvc.perform(get("/api/users/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }
}
