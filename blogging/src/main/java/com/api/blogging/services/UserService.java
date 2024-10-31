package com.api.blogging.services;

import com.api.blogging.models.UserModel;
import com.api.blogging.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    public ArrayList<UserModel> getUsers() {
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public UserModel saveUsers(UserModel user) {
        return userRepository.save(user);
    }

}
