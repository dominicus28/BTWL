package com.javatpoint.controllers;

import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.RestController;

import com.javatpoint.models.User;
import com.javatpoint.services.IUserService;  

@RestController  
public class UserController {  
    @Autowired  
    private IUserService userService;  
    
    //mapping the getUser() method to /users
    @GetMapping(value = "/users")  
    public List<User> getUser()   
    {  
        //finds all the users  
        List<User> users = userService.findAll();  
        //returns the user list  
        return users;
    }  
}
