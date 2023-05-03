package com.javatpoint.models;

public class User {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phNumber;
    private Role role;
    

    //default constructor
    public User() {
    
    }
    //constructor using fields
    public User(String name, String surname, String email, 
                String password, String phNumber, Role role)
    {
        super();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phNumber = phNumber;
        this.role = role;

    }
    //getters and setters
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
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}
