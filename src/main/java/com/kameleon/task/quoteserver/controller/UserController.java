package com.kameleon.task.quoteserver.controller;

import com.kameleon.task.quoteserver.model.User;
import com.kameleon.task.quoteserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public List<User> getAllUsers(){return userService.getAllUsers();}

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id")Integer id){
        return userService.getUserById(id);
    }

    @PostMapping("/user")
    public void createUser(@RequestBody User user){
        userService.createUser(user);
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody User user){
        userService.updateOrCreate(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        userService.deleteUser(id);
    }
}
