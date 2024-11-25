package com.api.blogging.services;

import com.api.blogging.dto.UserDTO;
import com.api.blogging.models.UserModel;
import com.api.blogging.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    public ArrayList<UserModel> getUsers() {
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public Optional<UserModel> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserDTO saveUsers(UserModel user) {
        UserModel savedUser = userRepository.save(user);
        UserDTO responseDTO = new UserDTO();
        responseDTO.setId(savedUser.getId());
        responseDTO.setUsername(savedUser.getUsername());
        responseDTO.setEmail(savedUser.getEmail());
        responseDTO.setPassword(savedUser.getPassword());
        return responseDTO;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
