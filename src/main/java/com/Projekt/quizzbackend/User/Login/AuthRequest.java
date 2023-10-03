package com.Projekt.quizzbackend.User.Login;

public class AuthRequest {
    private String username;
    private String password;

    private Integer anfrageId;
    private String anfrageName;

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

    public Integer getAnfrageId() {
        return anfrageId;
    }
    public void setAnfrageId(Integer anfrageId) {
        this.anfrageId = anfrageId;
    }
    public String getAnfrageName() {
        return anfrageName;
    }
    public void setAnfrageName(String anfrageName) {
        this.anfrageName = anfrageName;
    }

}