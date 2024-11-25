package com.api.blogging.controllers;

import com.api.blogging.dto.UserDTO;
import com.api.blogging.dto.UserRequestDTO;
import com.api.blogging.models.UserModel;
import com.api.blogging.models.UserRoleModel;
import com.api.blogging.repositories.IRoleRepository;
import com.api.blogging.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/get_users")
    public List<UserDTO> getUsers() {
        List<UserModel> users = this.userService.getUsers();
        return users.stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPassword()))
                .collect(Collectors.toList());
    }

    @PostMapping("/save_user")
    public UserDTO saveUser(@RequestBody UserRequestDTO userRequestDTO) {

        Optional<UserRoleModel> roleOptional = roleRepository.findById((long)userRequestDTO.getRole());
        UserRoleModel role = roleOptional.orElseThrow(() -> new RuntimeException("Role not found"));

        UserModel user = UserModel.builder()
                .username(userRequestDTO.getUsername())
                .email(userRequestDTO.getEmail())
                .password(passwordEncoder.encode(userRequestDTO.getPassword()))
                .role(role)
                .build();

        return this.userService.saveUsers(user);
    }

    @GetMapping("/get_user/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPassword()))
                .orElse(null);
    }

    @PutMapping("/update_user/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userDetails) {

        Optional<UserRoleModel> roleOptional = roleRepository.findById((long)userDetails.getRole());
        UserRoleModel role = roleOptional.orElseThrow(() -> new RuntimeException("Role not found"));

        return userService.getUserById(id)
                .map(user -> {
                    // Actualiza los detalles del usuario
                    user.setUsername(userDetails.getUsername());
                    user.setEmail(userDetails.getEmail());
                    user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
                    user.setRole(role);
                    UserDTO updatedUser = userService.saveUsers(user);
                    return new UserDTO(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getEmail(), updatedUser.getPassword());
                })
                .orElse(null);
    }

    @DeleteMapping("/delete_user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
