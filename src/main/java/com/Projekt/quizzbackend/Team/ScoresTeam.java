package com.Projekt.quizzbackend.Team;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "scores_team", schema = "quizsystema", catalog = "")
public class ScoresTeam {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "scores_id", nullable = false)
    private Integer scoresId;
    @Basic
    @Column(name = "scoresTeam_Id", nullable = true)
    private Integer scoresTeamId;
    @Basic
    @Column(name = "punkte_gesamt", nullable = true)
    private Integer punkteGesamt;
    @Basic
    @Column(name = "punkte_monat", nullable = true)
    private Integer punkteMonat;
    @Basic
    @Column(name = "punkte_woche", nullable = true)
    private Integer punkteWoche;
    @Basic
    @Column(name = "frage_richtig", nullable = true)
    private Integer frageRichtig;
    @Basic
    @Column(name = "fragen_gesamt", nullable = true)
    private Integer fragenGesamt;

    public Integer getScoresTeamId() {
        return scoresTeamId;
    }

    public void setScoresTeamId(Integer scoresTeamId) {
        this.scoresTeamId = scoresTeamId;
    }

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

    public void setPunkteWoche(Integer punkteTag) {
        this.punkteWoche = punkteTag;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoresTeam that = (ScoresTeam) o;
        return Objects.equals(scoresTeamId, that.scoresTeamId) && Objects.equals(punkteGesamt, that.punkteGesamt) && Objects.equals(punkteMonat, that.punkteMonat) && Objects.equals(punkteWoche, that.punkteWoche) && Objects.equals(frageRichtig, that.frageRichtig) && Objects.equals(fragenGesamt, that.fragenGesamt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoresTeamId, punkteGesamt, punkteMonat, punkteWoche, frageRichtig, fragenGesamt);
    }

    public Integer getScoresId() {
        return scoresId;
    }

    public void setScoresId(Integer scoresId) {
        this.scoresId = scoresId;
    }
}
