package com.Projekt.quizzbackend.Dao.DTO.Templates;

public class DropUserTeam extends AddUserToTeam{

    private String userToDrop;


    public String getUserToDrop() {
        return userToDrop;
    }

    public void setUserToDrop(String userToDrop) {
        this.userToDrop = userToDrop;
    }
}
