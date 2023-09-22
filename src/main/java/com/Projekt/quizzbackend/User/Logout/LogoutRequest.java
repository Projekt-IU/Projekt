package com.Projekt.quizzbackend.User.Logout;

public class LogoutRequest {

    private int userId;
    private String username;

    // Getter und Setter für die Felder (username und userId)

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}