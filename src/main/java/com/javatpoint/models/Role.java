package com.javatpoint.models;

public class Role {
    private User user;  // sender, receiver or courier
    private String privileges;  //
    
    public Role () {
    
    }

    public Role(User user, String privileges) {
        this.user = user;
        this.privileges = privileges;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }
}
