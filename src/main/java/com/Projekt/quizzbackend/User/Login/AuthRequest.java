package com.Projekt.quizzbackend.User.Login;

public class AuthRequest {
    private String username;
    private String password;

    // Getter und Setter für username
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter und Setter für password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}