package com.javatpoint.services;
import java.util.List;

import com.javatpoint.models.User;

public interface IUserService {
    List<User> findAll();
}