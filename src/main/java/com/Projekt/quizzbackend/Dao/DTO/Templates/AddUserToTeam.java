package com.Projekt.quizzbackend.Dao.DTO.Templates;

import com.Projekt.quizzbackend.User.Login.AuthRequest;

public class AddUserToTeam extends AuthRequest {

    private String teamName;
    private String newMember;


    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getNewMember() {
        return newMember;
    }

    public void setNewMember(String userName) {
        this.newMember = userName;
    }
}
