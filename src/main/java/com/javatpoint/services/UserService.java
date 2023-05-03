package com.javatpoint.services;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.javatpoint.models.User;
 
@Service
public class UserService implements IUserService {
    @Override
    public List<User> findAll() {
        //creating an object of ArrayList
        ArrayList<User> users = new ArrayList<User>();
        //adding users to the List
        /*
        users.add(new User("Tom", "Keen", "nadawca"));
        users.add(new User("Elizabeth", "Keen"));
        users.add(new User("Reymond", "Reddington"));
        users.add(new User("Samar", "Navabi"));*/
        //returns a list of user
        return users;
    }
}