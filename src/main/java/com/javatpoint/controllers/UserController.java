package com.javatpoint.controllers;

import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.javatpoint.models.User;
import com.javatpoint.repositories.UserRepository;
//import com.javatpoint.services.IUserService;  

@RestController  
public class UserController {  
    @Autowired  
    //private IUserService userService;  
    private MongoTemplate mongoTemplate;

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/users")
    public List<User> getUsers()   
    {  
        //finds all the users  
        List<User> users = userRepository.findAll();  
        //returns the user list  
        return users;
    } 

    @GetMapping(value = "/users/{id}")
    public User getUser(@PathVariable String email)   
    {  
        Query query = new Query();
        
        query.addCriteria(Criteria.where("email").is(email));
        
        return mongoTemplate.findOne(query, User.class);
    } 

    @PostMapping("/users")
    User createUser(@RequestBody User newUser) {
        //tu sprawdzenie
        return userRepository.save(newUser);
    }



}
