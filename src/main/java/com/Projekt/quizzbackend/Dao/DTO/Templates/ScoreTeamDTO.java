package com.Projekt.quizzbackend.Dao.DTO.Templates;
public class ScoreTeamDTO {
    private Integer punkteGesamt;
    private Integer punkteMonat;
    private Integer punkteWoche;
    private Integer frageRichtig;
    private Integer fragenGesamt;

    // Getter und Setter
    public Integer getPunkteGesamt() {
        return punkteGesamt;
    }

    public void setPunkteGesamt(Integer punkteGesamt) {
        this.punkteGesamt = punkteGesamt;
    }

    public Integer getPunkteMonat() {
        return punkteMonat;
    }

    public void setPunkteMonat(Integer punkteMonat) {
        this.punkteMonat = punkteMonat;
    }

    public Integer getPunkteWoche() {
        return punkteWoche;
    }

    public void setPunkteWoche(Integer punkteWoche) {
        this.punkteWoche = punkteWoche;
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