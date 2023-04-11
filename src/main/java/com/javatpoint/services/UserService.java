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
        users.add(new User(1, "Tom", "Keen", false, false, false, true));
        users.add(new User(2, "Elizabeth", "Keen", false, true, false, false));
        users.add(new User(3, "Reymond", "Reddington", false, false, true, false));
        users.add(new User(4, "Samar", "Navabi", false, false, false, true));
        //returns a list of user
        return users;
    }
}