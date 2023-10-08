package com.Projekt.quizzbackend.Dao.DTO.Templates;

public class UserToTeamListDTO {

    private Integer userID;
    private String userName;
    private String courseOfStudy;
    private boolean admin_team;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCourseOfStudy() {
        return courseOfStudy;
    }

    public void setCourseOfStudy(String courseOfStudy) {
        this.courseOfStudy = courseOfStudy;
    }

    public boolean isAdmin_team() {
        return admin_team;
    }

    public void setAdmin_team(boolean admin_team) {
        this.admin_team = admin_team;
    }
}
