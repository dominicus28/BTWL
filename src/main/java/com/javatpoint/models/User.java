package com.javatpoint.models;

public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phNumber;
    
    boolean ifAdministrator;
    boolean ifSender;
    boolean ifReceiver;
    boolean ifCourier;
    // todo interface Role

    //default constructor
    public User()
    {
    
    }
    //constructor using fields
    public User(int id, String name, String surname, boolean ifAdministrator, boolean ifSender,
    boolean ifReceiver, boolean ifCourier)
    {
        super();
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.ifAdministrator = ifAdministrator;
        this.ifSender = ifSender;
        this.ifReceiver = ifReceiver;
        this.ifCourier = ifCourier;
    }
    //getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public boolean getIfAdministrator() {
        return ifAdministrator;
    }
    public void setIfAdministrator(boolean ifAdministrator) {
        this.ifAdministrator = ifAdministrator;
    }
    public boolean getIfSender() {
        return ifSender;
    }
    public void setIfSender(boolean ifSender) {
        this.ifSender = ifSender;
    }
    public boolean getIfReceiver() {
        return ifReceiver;
    }
    public void setIfReceiver(boolean ifReceiver) {
        this.ifReceiver = ifReceiver;
    }
    public boolean getIfCourierr() {
        return ifCourier;
    }
    public void setIfCourier(boolean ifCourier) {
        this.ifCourier = ifCourier;
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

