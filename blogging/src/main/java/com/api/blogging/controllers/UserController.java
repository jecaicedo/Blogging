package com.api.blogging.controllers;

import com.api.blogging.models.UserModel;
import com.api.blogging.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get_users")
    public ArrayList<UserModel> getUsers() {
        return this.userService.getUsers();
    }

    @PostMapping("/save_user")
    public UserModel saveUser(@RequestBody UserModel user) {
        return this.userService.saveUsers(user);
    }

}
