package com.Projekt.quizzbackend.Dao.DTO.Templates;
public class TeamDTO {
    private Integer teamsId;
    private String name;
    private String studiengang;
    private Integer adminUserId;
    private String adminUsername;

    private ScoreTeamDTO scoreTeam;

    // Getter und Setter
    public Integer getTeamsId() {
        return teamsId;
    }

    public void setTeamsId(Integer teamsId) {
        this.teamsId = teamsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudiengang() {
        return studiengang;
    }

    public void setStudiengang(String studiengang) {
        this.studiengang = studiengang;
    }

    public Integer getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public ScoreTeamDTO getScoreTeam() {
        return scoreTeam;
    }

    public void setScoreTeam(ScoreTeamDTO scoreTeam) {
        this.scoreTeam = scoreTeam;
    }
}