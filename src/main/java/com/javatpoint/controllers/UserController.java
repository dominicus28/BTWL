package com.javatpoint.controllers;

import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javatpoint.models.User;
import com.javatpoint.repositories.UserRepository;
import com.javatpoint.services.IUserService;  

@RestController  
public class UserController {  
    @Autowired  
    private IUserService userService;  

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/users")
    public List<User> getUser()   
    {  
        //finds all the users  
        List<User> users = userRepository.findAll();  
        //returns the user list  
        return users;
    } 

    @PostMapping("/users")
    User createUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
  }

}
