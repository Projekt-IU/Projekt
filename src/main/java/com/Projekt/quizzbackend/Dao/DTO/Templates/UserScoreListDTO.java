package com.Projekt.quizzbackend.Dao.DTO.Templates;

public class UserScoreListDTO {

    private String username;
    private Integer scoreWoche;
    private Integer scoreMonat;
    private Integer scoreGesamt;
    private Integer frageRichtig;
    private Integer fragenGesamt;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getScoreWoche() {
        return scoreWoche;
    }

    public void setScoreWoche(Integer scoreWoche) {
        this.scoreWoche = scoreWoche;
    }

    public Integer getScoreMonat() {
        return scoreMonat;
    }

    public void setScoreMonat(Integer scoreMonat) {
        this.scoreMonat = scoreMonat;
    }

    public Integer getScoreGesamt() {
        return scoreGesamt;
    }

    public void setScoreGesamt(Integer scoreGesamt) {
        this.scoreGesamt = scoreGesamt;
    }

    public Integer getFrageRichtig() {
        return frageRichtig;
    }

    public void setFrageRichtig(Integer frageRichtig) {
        this.frageRichtig = frageRichtig;
    }

    public Integer getFragenGesamt() {
        return fragenGesamt;
    }

    public void setFragenGesamt(Integer fragenGesamt) {
        this.fragenGesamt = fragenGesamt;
    }
}
