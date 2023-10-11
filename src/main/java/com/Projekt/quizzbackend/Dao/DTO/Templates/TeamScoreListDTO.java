package com.Projekt.quizzbackend.Dao.DTO.Templates;

public class TeamScoreListDTO extends ScoreTeamDTO{

    private Integer teamsId;
    private String name;
    private String studiengang;

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
}
