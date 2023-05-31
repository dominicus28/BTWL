package com.javatpoint.models;

import org.bson.types.ObjectId;
//import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
    
    @Id
    @JsonIgnore
    private ObjectId id;
    @Indexed(unique = true)
    private String login;
    private String email;
    private String name;
    private String surname;
    private String password;
    private String phNumber;    

    //default constructor
    public User() {
    
    }
    //constructor using fields
    public User(String name, String surname, String email, 
                String password, String phNumber)
    {
        super();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phNumber = phNumber;

    }
    
    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
    //getters and setters
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhNumber() {
        return phNumber;
    }
    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }
}
